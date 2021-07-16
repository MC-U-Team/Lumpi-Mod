package info.u_team.lumpi_mod.item;

import info.u_team.lumpi_mod.init.LumpiModItemsGroups;
import info.u_team.u_team_core.item.UItem;
import net.minecraft.item.Food;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class HotdogItem extends UItem {
	
	private static final Food FOOD = new Food.Builder() //
			.hunger(5) //
			.saturation(3) //
			.meat() //
			.setAlwaysEdible() //
			.effect(() -> new EffectInstance(Effects.NAUSEA, 200, 1), 0.1F) //
			.effect(() -> new EffectInstance(Effects.HEALTH_BOOST, 800, 3), 1) //
			.build();
	
	public HotdogItem() {
		super(LumpiModItemsGroups.GROUP, new Properties().rarity(Rarity.UNCOMMON).food(FOOD));
	}
	
}
