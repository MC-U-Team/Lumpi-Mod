package info.u_team.lumpi_mod.particle;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PoofParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LumpiSpitParticle extends PoofParticle {
	
	private LumpiSpitParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ, IAnimatedSprite spriteWithAge) {
		super(world, x, y, z, motionX, motionY, motionZ, spriteWithAge);
		particleGravity = 0.6F;
		particleRed = 1;
		particleGreen = 1;
		particleBlue *= 10;
	}
	
	@Override
	public void tick() {
		super.tick();
		motionY -= 0.004D + 0.04D * particleGravity;
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
		
		private final IAnimatedSprite spriteSet;
		
		public Factory(IAnimatedSprite spriteSet) {
			this.spriteSet = spriteSet;
		}
		
		@Override
		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new LumpiSpitParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet);
		}
	}
}