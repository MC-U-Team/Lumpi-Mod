package info.u_team.lumpi_mod.handler;

import info.u_team.lumpi_mod.entity.LumpiEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.IWorld;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class SpawnEventHandler {
	
	private static void onSpecialSpawn(LivingSpawnEvent.SpecialSpawn event) {
		final IWorld world = event.getWorld();
		if (world.isRemote()) {
			return;
		}
		
		final Entity entity = event.getEntity();
		if (!(entity instanceof LumpiEntity)) {
			return;
		}
		final SpawnReason reason = event.getSpawnReason();
		if (reason != SpawnReason.DISPENSER && reason != SpawnReason.MOB_SUMMONED && reason != SpawnReason.SPAWN_EGG) {
			return;
		}
		world.playSound(null, entity.getPosition(), SoundEvents.ENTITY_WOLF_HOWL, SoundCategory.NEUTRAL, 1, 0.9F);
	}
	
	public static void registerForge(IEventBus bus) {
		bus.addListener(SpawnEventHandler::onSpecialSpawn);
	}
	
}
