package info.u_team.lumpi_mod.init;

import info.u_team.lumpi_mod.particle.LumpiSpitParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;

public class LumpiModParticles {
	
	private static void particleFactoryRegister(ParticleFactoryRegisterEvent event) {
		final ParticleManager manager = Minecraft.getInstance().particles;
		
		manager.registerFactory(LumpiModParticleTypes.LUMPI_SPIT.get(), LumpiSpitParticle.Factory::new);
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(LumpiModParticles::particleFactoryRegister);
	}
	
}
