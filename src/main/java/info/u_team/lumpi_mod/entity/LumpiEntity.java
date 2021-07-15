package info.u_team.lumpi_mod.entity;

import java.util.UUID;

import info.u_team.lumpi_mod.init.LumpiModEntityTypes;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.BoostHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.IRideable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BegGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.NonTamedTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class LumpiEntity extends WolfEntity implements IRangedAttackMob, IRideable {
	
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(LumpiEntity.class, DataSerializers.VARINT);
	
	private final BoostHelper boostHelper;
	
	private boolean stopSpitting;
	
	public LumpiEntity(EntityType<? extends LumpiEntity> type, World world) {
		super(type, world);
		boostHelper = new BoostHelper(dataManager, BOOST_TIME, null);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_() //
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4) //
				.createMutableAttribute(Attributes.MAX_HEALTH, 12) //
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 6);
	}
	
	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if (BOOST_TIME.equals(key) && world.isRemote) {
			boostHelper.updateData();
		}
		super.notifyDataManagerChange(key);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(BOOST_TIME, 0);
	}
	
	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new SitGoal(this));
		goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
		goalSelector.addGoal(4, new LumpiRangedAttackGoal(this, 0.75, 5, 30, 20));
		goalSelector.addGoal(4, new MeleeAttackGoal(this, 1, true));
		goalSelector.addGoal(5, new FollowOwnerGoal(this, 1, 10, 2, false));
		goalSelector.addGoal(6, new BreedGoal(this, 1));
		goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(8, new BegGoal(this, 8));
		goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 8));
		goalSelector.addGoal(9, new LookRandomlyGoal(this));
		
		targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		targetSelector.addGoal(3, new HurtByTargetGoal(this).setCallsForHelp());
		targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::func_233680_b_));
		targetSelector.addGoal(5, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, TARGET_ENTITIES));
		targetSelector.addGoal(6, new ResetAngerGoal<>(this, true));
	}
	
	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
		final LumpiSpitEntity spit = new LumpiSpitEntity(world, this);
		
		final double x = target.getPosX() - getPosX();
		final double y = target.getPosYHeight(0.3333333333333333) - spit.getPosY();
		final double z = target.getPosZ() - getPosZ();
		
		final float addedY = MathHelper.sqrt(x * x + z * z) * 0.2F + 0.5F;
		spit.shoot(x, y + addedY, z, 1, 5);
		
		if (!isSilent()) {
			world.playSound(null, getPosX(), getPosY(), getPosZ(), SoundEvents.ENTITY_LLAMA_SPIT, getSoundCategory(), 1, 1 + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
		}
		world.addEntity(spit);
		
		// Randomly stop spitting after some spits
		stopSpitting = rand.nextInt(2) == 0;
	}
	
	@Override
	public void setTamed(boolean tamed) {
		super.setTamed(tamed);
		
		if (tamed) {
			getAttribute(Attributes.MAX_HEALTH).setBaseValue(25);
			setHealth(25);
		} else {
			getAttribute(Attributes.MAX_HEALTH).setBaseValue(12);
		}
		
		getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(6);
	}
	
	@Override
	public LumpiEntity createChild(ServerWorld world, AgeableEntity mate) {
		final LumpiEntity lumpiEntity = LumpiModEntityTypes.LUMPI.get().create(world);
		final UUID uuid = getOwnerId();
		if (uuid != null) {
			lumpiEntity.setOwnerId(uuid);
			lumpiEntity.setTamed(true);
		}
		return lumpiEntity;
	}
	
	@Override
	public boolean canMateWith(AnimalEntity otherAnimal) {
		if (otherAnimal == this) {
			return false;
		} else if (!isTamed()) {
			return false;
		} else if (!(otherAnimal instanceof LumpiEntity)) {
			return false;
		} else {
			final LumpiEntity lumpiEntity = (LumpiEntity) otherAnimal;
			if (!lumpiEntity.isTamed()) {
				return false;
			} else if (lumpiEntity.isEntitySleeping()) {
				return false;
			} else {
				return isInLove() && lumpiEntity.isInLove();
			}
		}
	}
	
	@Override
	public boolean boost() {
		return boostHelper.boost(getRNG());
	}
	
	@Override
	public void travelTowards(Vector3d travelVector) {
		super.travel(travelVector);
	}
	
	@Override
	public void travel(Vector3d travelVector) {
		ride(this, boostHelper, travelVector);
	}
	
	@Override
	public boolean ride(MobEntity mount, BoostHelper helper, Vector3d travelVector) {
		if (!mount.isAlive()) {
			return false;
		} else {
			final Entity rider = getControllingPassenger();
			if (mount.isBeingRidden() && mount.canBeSteered() && rider instanceof PlayerEntity) {
				mount.rotationYaw = rider.rotationYaw;
				mount.prevRotationYaw = mount.rotationYaw;
				mount.rotationPitch = rider.rotationPitch * 0.5F;
				mount.setRotation(mount.rotationYaw, mount.rotationPitch);
				mount.renderYawOffset = mount.rotationYaw;
				mount.rotationYawHead = mount.rotationYaw;
				mount.stepHeight = 1;
				mount.jumpMovementFactor = mount.getAIMoveSpeed() * 0.1F;
				if (helper.saddledRaw && helper.field_233611_b_++ > helper.boostTimeRaw) {
					helper.saddledRaw = false;
				}
				
				if (mount.canPassengerSteer()) {
					float speed = getMountedSpeed();
					if (helper.saddledRaw) {
						speed += speed * 1.15F * MathHelper.sin((float) helper.field_233611_b_ / (float) helper.boostTimeRaw * (float) Math.PI);
					}
					
					final PlayerEntity player = (PlayerEntity) rider;
					speed *= player.moveForward;
					
					mount.setAIMoveSpeed(speed);
					travelTowards(new Vector3d(0.0D, 0.0D, 1.0D));
					mount.newPosRotationIncrements = 0;
				} else {
					mount.func_233629_a_(mount, false);
					mount.setMotion(Vector3d.ZERO);
				}
				
				return true;
			} else {
				mount.stepHeight = 0.5F;
				mount.jumpMovementFactor = 0.02F;
				travelTowards(travelVector);
				return false;
			}
		}
	}
	
	@Override
	public float getMountedSpeed() {
		return (float) (getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.55F);
	}
	
	@Override
	public boolean canBeSteered() {
		final Entity controller = getControllingPassenger();
		if (controller instanceof LivingEntity) {
			return isTamed() && isOwner((LivingEntity) controller) && !isEntitySleeping();
		}
		return false;
	}
	
	@Override
	public Entity getControllingPassenger() {
		return getPassengers().isEmpty() ? null : getPassengers().get(0);
	}
	
	@Override
	public ActionResultType getEntityInteractionResult(PlayerEntity player, Hand hand) {
		final ItemStack stack = player.getHeldItem(hand);
		if (isTamed() && !isBreedingItem(stack) && !(stack.getItem() instanceof DyeItem) && !isBeingRidden() && !player.isSecondaryUseActive()) {
			if (!world.isRemote()) {
				player.startRiding(this);
			}
			return ActionResultType.func_233537_a_(world.isRemote());
		}
		return super.getEntityInteractionResult(player, hand);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	protected class LumpiRangedAttackGoal extends RangedAttackGoal {
		
		protected LumpiRangedAttackGoal(IRangedAttackMob attacker, double movespeed, int minAttackTime, int maxAttackTime, float maxAttackDistanceIn) {
			super(attacker, movespeed, minAttackTime, maxAttackTime, maxAttackDistanceIn);
		}
		
		@Override
		public boolean shouldExecute() {
			if (stopSpitting) {
				stopSpitting = false;
				return false;
			}
			return super.shouldExecute();
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			if (stopSpitting) {
				return false;
			}
			return super.shouldContinueExecuting();
		}
	}
	
}
