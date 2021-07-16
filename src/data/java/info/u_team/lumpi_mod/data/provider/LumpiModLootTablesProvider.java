package info.u_team.lumpi_mod.data.provider;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import info.u_team.lumpi_mod.init.LumpiModEntityTypes;
import info.u_team.lumpi_mod.init.LumpiModItems;
import info.u_team.u_team_core.data.CommonLootTablesProvider;
import info.u_team.u_team_core.data.GenerationData;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.util.ResourceLocation;

public class LumpiModLootTablesProvider extends CommonLootTablesProvider {
	
	public LumpiModLootTablesProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
		registerEntity(LumpiModEntityTypes.LUMPI, addLumpiEntityLootTable(0, 1), consumer);
		registerEntity(LumpiModEntityTypes.LOADED_LUMPI, addLumpiEntityLootTable(1, 3), consumer);
		registerEntity(LumpiModEntityTypes.STEEL_LUMPI, addLumpiEntityLootTable(2, 5), consumer);
		
		registerEntity(LumpiModEntityTypes.LUMPI_SPIT, addEmptyEntityLootTable(), consumer);
	}
	
	private LootTable addLumpiEntityLootTable(int min, int max) {
		return LootTable.builder() //
				.setParameterSet(LootParameterSets.ENTITY) //
				.addLootPool(LootPool.builder() //
						.rolls(RandomValueRange.of(min, max)) //
						.addEntry(ItemLootEntry.builder(LumpiModItems.HOTDOG.get()))) //
				.build();
	}
	
	private LootTable addEmptyEntityLootTable() {
		return LootTable.builder() //
				.setParameterSet(LootParameterSets.ENTITY) //
				.build();
	}
	
	private static void registerEntity(Supplier<? extends EntityType<?>> supplier, LootTable lootTable, BiConsumer<ResourceLocation, LootTable> consumer) {
		registerEntity(supplier.get(), lootTable, consumer);
	}
	
	private static void registerEntity(EntityType<?> entity, LootTable lootTable, BiConsumer<ResourceLocation, LootTable> consumer) {
		final ResourceLocation registryName = entity.getRegistryName();
		consumer.accept(new ResourceLocation(registryName.getNamespace(), "entites/" + registryName.getPath()), lootTable);
	}
}
