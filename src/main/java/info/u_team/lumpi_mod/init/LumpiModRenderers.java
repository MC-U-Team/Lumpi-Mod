package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.entity.render.LoadedLumpiRenderer;
import info.u_team.lumpi_mod.entity.render.LumpiRenderer;
import info.u_team.lumpi_mod.entity.render.LumpiSpitRenderer;
import info.u_team.lumpi_mod.entity.render.SteelLumpiRenderer;
import info.u_team.u_team_core.util.registry.ClientRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class LumpiModRenderers {
	
	private static void setup(FMLClientSetupEvent event) {
		ClientRegistry.registerEntityRenderer(LumpiModEntityTypes.LUMPI, LumpiRenderer::new);
		ClientRegistry.registerEntityRenderer(LumpiModEntityTypes.LOADED_LUMPI, LoadedLumpiRenderer::new);
		ClientRegistry.registerEntityRenderer(LumpiModEntityTypes.STEEL_LUMPI, SteelLumpiRenderer::new);
		
		ClientRegistry.registerEntityRenderer(LumpiModEntityTypes.LUMPI_SPIT, LumpiSpitRenderer::new);
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(LumpiModRenderers::setup);
	}
	
}
