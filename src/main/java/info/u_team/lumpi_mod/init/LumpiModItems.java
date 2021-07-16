package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.LumpiMod;
import info.u_team.lumpi_mod.item.HotdogItem;
import info.u_team.lumpi_mod.item.LumpiSpawnEggItem;
import info.u_team.u_team_core.item.USpawnEggItem;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class LumpiModItems {
	
	public static final CommonDeferredRegister<Item> ITEMS = CommonDeferredRegister.create(ForgeRegistries.ITEMS, LumpiMod.MODID);
	
	public static final RegistryObject<USpawnEggItem> LUMPI_SPAWN_EGG = ITEMS.register("lumpi_spawn_egg", () -> new LumpiSpawnEggItem(LumpiModEntityTypes.LUMPI, Rarity.UNCOMMON, 0x8a4127, 0xd1b4b4));
	public static final RegistryObject<USpawnEggItem> LOADED_LUMPI_SPAWN_EGG = ITEMS.register("loaded_lumpi_spawn_egg", () -> new LumpiSpawnEggItem(LumpiModEntityTypes.LOADED_LUMPI, Rarity.RARE, 0x2e160d, 0xd1b4b4));
	public static final RegistryObject<USpawnEggItem> STEEL_LUMPI_SPAWN_EGG = ITEMS.register("steel_lumpi_spawn_egg", () -> new LumpiSpawnEggItem(LumpiModEntityTypes.STEEL_LUMPI, Rarity.EPIC, 0x261c1c, 0x615e5e));
	
	public static final RegistryObject<HotdogItem> HOTDOG = ITEMS.register("hotdog", HotdogItem::new);
	
	public static void registerMod(IEventBus bus) {
		ITEMS.register(bus);
	}
	
}
