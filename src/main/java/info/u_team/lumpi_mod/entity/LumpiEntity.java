package info.u_team.lumpi_mod.entity;

import java.util.UUID;

import info.u_team.lumpi_mod.init.LumpiModEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class LumpiEntity extends TameableEntity {
	
	public LumpiEntity(EntityType<? extends TameableEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public AgeableEntity createChild(ServerWorld world, AgeableEntity mate) {
		final LumpiEntity lumpi = LumpiModEntities.LUMPI.get().create(world);
		final UUID uuid = getOwnerId();
		if (uuid != null) {
			lumpi.setOwnerId(uuid);
			lumpi.setTamed(true);
		}
		return lumpi;
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
}
