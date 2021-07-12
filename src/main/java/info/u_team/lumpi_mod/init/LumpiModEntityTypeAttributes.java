package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.entity.LumpiEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class LumpiModEntityTypeAttributes {
	
	private static void entityAttributionCreation(EntityAttributeCreationEvent event) {
		event.put(LumpiModEntities.LUMPI.get(), LumpiEntity.registerAttributes().create());
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(LumpiModEntityTypeAttributes::entityAttributionCreation);
	}
	
}
