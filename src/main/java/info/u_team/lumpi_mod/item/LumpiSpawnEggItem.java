package info.u_team.lumpi_mod.item;

import java.util.function.Supplier;

import info.u_team.lumpi_mod.init.LumpiModItemsGroups;
import info.u_team.u_team_core.item.USpawnEggItem;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Rarity;

public class LumpiSpawnEggItem extends USpawnEggItem {
	
	public LumpiSpawnEggItem(Supplier<? extends EntityType<?>> type, Rarity rarity, int primaryColor, int secondaryColor) {
		super(LumpiModItemsGroups.GROUP, new Properties().rarity(rarity), type, primaryColor, secondaryColor);
	}
	
}
