package info.u_team.lumpi_mod.item;

import info.u_team.lumpi_mod.init.LumpiModEntityTypes;
import info.u_team.lumpi_mod.init.LumpiModItemsGroups;
import info.u_team.u_team_core.item.USpawnEggItem;
import net.minecraft.item.Rarity;

public class LumpiSpawnEggItem extends USpawnEggItem {
	
	public LumpiSpawnEggItem() {
		super(LumpiModItemsGroups.GROUP, new Properties().rarity(Rarity.UNCOMMON), LumpiModEntityTypes.LUMPI, 0x8a4127, 0xd1b4b4);
	}
	
}
