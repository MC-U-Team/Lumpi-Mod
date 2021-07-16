package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.entity.LumpiEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class LumpiModEntityTypeAttributes {
	
	private static void entityAttributionCreation(EntityAttributeCreationEvent event) {
		event.put(LumpiModEntityTypes.LUMPI.get(), LumpiEntity.registerAttributes(0.5, 12, 6).create());
		event.put(LumpiModEntityTypes.LOADED_LUMPI.get(), LumpiEntity.registerAttributes(0.4, 18, 6).create());
		event.put(LumpiModEntityTypes.STEEL_LUMPI.get(), LumpiEntity.registerAttributes(0.5, 100, 8).create());
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(LumpiModEntityTypeAttributes::entityAttributionCreation);
	}
	
}
