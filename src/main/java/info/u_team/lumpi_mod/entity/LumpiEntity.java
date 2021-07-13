package info.u_team.lumpi_mod.entity;

import java.util.UUID;

import info.u_team.lumpi_mod.init.LumpiModEntityTypes;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
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
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class LumpiEntity extends WolfEntity implements IRangedAttackMob {
	
	private boolean stopSpitting;
	
	public LumpiEntity(EntityType<? extends LumpiEntity> type, World world) {
		super(type, world);
		setCustomName(new StringTextComponent("Lumpi"));
		setCustomNameVisible(true);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.func_233666_p_() //
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.4) //
				.createMutableAttribute(Attributes.MAX_HEALTH, 12) //
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 6);
	}
	
	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new SitGoal(this));
		goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
		goalSelector.addGoal(4, new LumpiRangedAttackGoal(this, 1.25, 40, 20));
		goalSelector.addGoal(5, new MeleeAttackGoal(this, 0.5, true));
		goalSelector.addGoal(6, new FollowOwnerGoal(this, 1, 10, 2, false));
		goalSelector.addGoal(7, new BreedGoal(this, 1));
		goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(9, new BegGoal(this, 8));
		goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8));
		goalSelector.addGoal(10, new LookRandomlyGoal(this));
		
		targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		targetSelector.addGoal(3, new HurtByTargetGoal(this).setCallsForHelp());
		targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::func_233680_b_));
		targetSelector.addGoal(5, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, TARGET_ENTITIES));
		targetSelector.addGoal(6, new ResetAngerGoal<>(this, true));
	}
	
	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
		final LlamaSpitEntity llamaspitentity = new LlamaSpitEntity(EntityType.LLAMA_SPIT, world);
		llamaspitentity.setShooter(this);
		llamaspitentity.setPosition(getPosX() - (getWidth() + 1.0F) * 0.5D * MathHelper.sin(renderYawOffset * ((float) Math.PI / 180F)), getPosYEye() - 0.1F, getPosZ() + (getWidth() + 1.0F) * 0.5D * MathHelper.cos(renderYawOffset * ((float) Math.PI / 180F)));
		
		final double d0 = target.getPosX() - getPosX();
		final double d1 = target.getPosYHeight(0.3333333333333333D) - llamaspitentity.getPosY();
		final double d2 = target.getPosZ() - getPosZ();
		final float f = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
		llamaspitentity.shoot(d0, d1 + f, d2, 1.5F, 10.0F);
		if (!isSilent()) {
			world.playSound((PlayerEntity) null, getPosX(), getPosY(), getPosZ(), SoundEvents.ENTITY_LLAMA_SPIT, getSoundCategory(), 1.0F, 1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
		}
		
		world.addEntity(llamaspitentity);
		
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
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	private class LumpiRangedAttackGoal extends RangedAttackGoal {
		
		public LumpiRangedAttackGoal(IRangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) {
			super(attacker, movespeed, maxAttackTime, maxAttackDistanceIn);
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
