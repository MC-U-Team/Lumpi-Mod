package info.u_team.lumpi_mod.data.provider;

import info.u_team.lumpi_mod.init.LumpiModItems;
import info.u_team.u_team_core.data.CommonItemModelsProvider;
import info.u_team.u_team_core.data.GenerationData;

public class LumpiModItemModelsProvider extends CommonItemModelsProvider {
	
	public LumpiModItemModelsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerModels() {
		spawnEgg(LumpiModItems.LUMPI_SPAWN_EGG.get());
		spawnEgg(LumpiModItems.LOADED_LUMPI_SPAWN_EGG.get());
		spawnEgg(LumpiModItems.STEEL_LUMPI_SPAWN_EGG.get());
		
		simpleGenerated(LumpiModItems.HOTDOG.get());
	}
	
}
