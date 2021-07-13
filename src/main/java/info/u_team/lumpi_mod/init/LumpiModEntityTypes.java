package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.LumpiMod;
import info.u_team.lumpi_mod.entity.LumpiEntity;
import info.u_team.lumpi_mod.entity.LumpiSpitEntity;
import info.u_team.u_team_core.util.registry.EntityTypeDeferredRegister;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;

public class LumpiModEntityTypes {
	
	public static final EntityTypeDeferredRegister ENTITY_TYPES = EntityTypeDeferredRegister.create(LumpiMod.MODID);
	
	public static final RegistryObject<EntityType<LumpiEntity>> LUMPI = ENTITY_TYPES.register("lumpi", () -> EntityType.Builder.create(LumpiEntity::new, EntityClassification.AMBIENT).size(0.6F, 0.85F).trackingRange(64).updateInterval(3).setShouldReceiveVelocityUpdates(true));
	
	public static final RegistryObject<EntityType<LumpiSpitEntity>> LUMPI_SPIT = ENTITY_TYPES.register("lumpi_spit", () -> EntityType.Builder.<LumpiSpitEntity> create(LumpiSpitEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).trackingRange(4).updateInterval(10).setShouldReceiveVelocityUpdates(false).setCustomClientFactory(LumpiSpitEntity::new));
	
	public static void registerMod(IEventBus bus) {
		ENTITY_TYPES.register(bus);
	}
	
}
