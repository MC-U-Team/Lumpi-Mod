package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.LumpiMod;
import info.u_team.u_team_core.util.registry.CommonDeferredRegister;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class LumpiModParticleTypes {
	
	public static final CommonDeferredRegister<ParticleType<?>> PARTICLE_TYPES = CommonDeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, LumpiMod.MODID);
	
	public static final RegistryObject<BasicParticleType> LUMPI_SPIT = PARTICLE_TYPES.register("lumpi_spit", () -> new BasicParticleType(true));
	
	public static void registerMod(IEventBus bus) {
		PARTICLE_TYPES.register(bus);
	}
	
}
