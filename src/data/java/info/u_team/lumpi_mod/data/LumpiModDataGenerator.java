package info.u_team.lumpi_mod.data;

import info.u_team.lumpi_mod.LumpiMod;
import info.u_team.lumpi_mod.data.provider.LumpiModItemModelsProvider;
import info.u_team.lumpi_mod.data.provider.LumpiModLanguagesProvider;
import info.u_team.lumpi_mod.data.provider.LumpiModLootTablesProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = LumpiMod.MODID, bus = Bus.MOD)
public class LumpiModDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final GenerationData data = new GenerationData(LumpiMod.MODID, event);
		if (event.includeServer()) {
			data.addProvider(LumpiModLootTablesProvider::new);
		}
		if (event.includeClient()) {
			data.addProvider(LumpiModItemModelsProvider::new);
			data.addProvider(LumpiModLanguagesProvider::new);
		}
	}
}
