package info.u_team.lumpi_mod.data.provider;

import info.u_team.lumpi_mod.init.LumpiModEntityTypes;
import info.u_team.lumpi_mod.init.LumpiModItems;
import info.u_team.lumpi_mod.init.LumpiModItemsGroups;
import info.u_team.u_team_core.data.CommonLanguagesProvider;
import info.u_team.u_team_core.data.GenerationData;

public class LumpiModLanguagesProvider extends CommonLanguagesProvider {
	
	public LumpiModLanguagesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	public void addTranslations() {
		// English
		add(LumpiModItemsGroups.GROUP, "Lumpi Mod");
		
		addItem(LumpiModItems.LUMPI_SPAWN_EGG, "Lumpi Spawn Egg");
		addItem(LumpiModItems.LOADED_LUMPI_SPAWN_EGG, "Loaded Lumpi Spawn Egg");
		
		addEntityType(LumpiModEntityTypes.LUMPI, "Lumpi");
		addEntityType(LumpiModEntityTypes.LOADED_LUMPI, "Loaded Lumpi");
		
		addEntityType(LumpiModEntityTypes.LUMPI_SPIT, "Lumpi Spit");
	}
	
}
