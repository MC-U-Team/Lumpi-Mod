package info.u_team.lumpi_mod.entity.render;

import info.u_team.lumpi_mod.LumpiMod;
import info.u_team.lumpi_mod.entity.LumpiEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class LoadedLumpiRenderer extends LumpiRenderer {
	
	private static final ResourceLocation LUMPI_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog_loaded.png");
	private static final ResourceLocation TAMED_LUMPI_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog_loaded_tame.png");
	private static final ResourceLocation ANGRY_LUMPI_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog_loaded_angry.png");
	
	public LoadedLumpiRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public ResourceLocation getEntityTexture(LumpiEntity entity) {
		if (entity.isTamed()) {
			return TAMED_LUMPI_TEXTURES;
		} else if (entity.isAngry()) {
			return ANGRY_LUMPI_TEXTURES;
		} else {
			return LUMPI_TEXTURES;
		}
	}
}