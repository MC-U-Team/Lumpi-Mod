package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.LumpiMod;
import info.u_team.u_team_core.item.USpawnEggItem;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class LumpiModItems {
	
	public static final CommonDeferredRegister<Item> ITEMS = CommonDeferredRegister.create(ForgeRegistries.ITEMS, LumpiMod.MODID);
	
	public static final RegistryObject<USpawnEggItem> LUMPI_SPAWN_EGG = ITEMS.register("lumpi_spawn_egg", () -> new USpawnEggItem(LumpiModItemsGroups.GROUP, new Properties(), LumpiModEntities.LUMPI, 0x8a4127, 0xc46c6c));
	
	public static void registerMod(IEventBus bus) {
		ITEMS.register(bus);
	}
	
}
