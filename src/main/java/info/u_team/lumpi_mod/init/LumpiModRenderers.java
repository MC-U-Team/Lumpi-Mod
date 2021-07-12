package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.entity.render.LumpiRenderer;
import info.u_team.u_team_core.util.registry.ClientRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class LumpiModRenderers {
	
	private static void setup(FMLClientSetupEvent event) {
		ClientRegistry.registerEntityRenderer(LumpiModEntities.LUMPI, LumpiRenderer::new);
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(LumpiModRenderers::setup);
	}
	
}
