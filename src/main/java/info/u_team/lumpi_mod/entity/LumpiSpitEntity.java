package info.u_team.lumpi_mod.entity;

import info.u_team.lumpi_mod.init.LumpiModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages.SpawnEntity;
import net.minecraftforge.fml.network.NetworkHooks;

public class LumpiSpitEntity extends ProjectileEntity {
	
	public LumpiSpitEntity(EntityType<? extends LumpiSpitEntity> type, World world) {
		super(type, world);
	}
	
	public LumpiSpitEntity(World world, LumpiEntity lumpiEntity) {
		this(LumpiModEntityTypes.LUMPI_SPIT.get(), world);
		setShooter(lumpiEntity);
		setPosition(lumpiEntity.getPosX() - (lumpiEntity.getWidth() + 1) * 0.5 * MathHelper.sin(lumpiEntity.renderYawOffset * ((float) Math.PI / 180)), lumpiEntity.getPosYEye() - 0.1, lumpiEntity.getPosZ() + (lumpiEntity.getWidth() + 1) * 0.5 * MathHelper.cos(lumpiEntity.renderYawOffset * ((float) Math.PI / 180)));
	}
	
	public LumpiSpitEntity(SpawnEntity message, World world) {
		this(LumpiModEntityTypes.LUMPI_SPIT.get(), world);
		
		final double posX = message.getPosX();
		final double posY = message.getPosY();
		final double posZ = message.getPosZ();
		
		// divide by 8000 as its done in the spawn message
		final double velX = message.getVelX() / 8000D;
		final double velY = message.getVelY() / 8000D;
		final double velZ = message.getVelZ() / 8000D;
		
		for (int index = 0; index < 7; ++index) {
			final double distance = 0.4 + 0.1 * index;
			world.addParticle(ParticleTypes.SPIT, posX, posY, posZ, velX * distance, velY, velZ * distance);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void tick() {
		super.tick();
		
		final Vector3d motion = getMotion();
		
		final RayTraceResult result = ProjectileHelper.func_234618_a_(this, this::func_230298_a_);
		if (result != null && result.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, result)) {
			onImpact(result);
		}
		
		updatePitchAndYaw();
		
		if (world.func_234853_a_(getBoundingBox()).noneMatch(BlockState::isAir)) {
			remove();
		} else if (isInWaterOrBubbleColumn()) {
			remove();
		} else {
			setMotion(motion.scale(0.99));
			if (!hasNoGravity()) {
				setMotion(getMotion().add(0, -0.03, 0));
			}
			
			setPosition(getPosX() + motion.x, getPosY() + motion.y, getPosZ() + motion.z);
		}
	}
	
	@Override
	protected void onEntityHit(EntityRayTraceResult result) {
		super.onEntityHit(result);
		final Entity shooter = getShooter();
		if (shooter instanceof LivingEntity) {
			result.getEntity().attackEntityFrom(DamageSource.causeIndirectDamage(this, (LivingEntity) shooter).setProjectile(), 4);
		}
		
	}
	
	@Override
	protected void func_230299_a_(BlockRayTraceResult result) {
		super.func_230299_a_(result);
		if (!world.isRemote) {
			remove();
		}
	}
	
	@Override
	protected void registerData() {
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}
