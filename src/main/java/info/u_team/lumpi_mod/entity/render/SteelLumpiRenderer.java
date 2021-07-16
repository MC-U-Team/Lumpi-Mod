package info.u_team.lumpi_mod.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import info.u_team.lumpi_mod.LumpiMod;
import info.u_team.lumpi_mod.entity.LumpiEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SteelLumpiRenderer extends LumpiRenderer {
	
	private static final ResourceLocation LUMPI_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog_loaded.png");
	private static final ResourceLocation TAMED_LUMPI_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog_loaded_tame.png");
	private static final ResourceLocation ANGRY_LUMPI_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog_loaded_angry.png");
	
	public SteelLumpiRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void render(LumpiEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer buffer, int packedLight) {
		entityModel.setTint(0.2F, 0.2F, 0.2F);
		super.render(entity, entityYaw, partialTicks, matrixStackIn, buffer, packedLight);
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