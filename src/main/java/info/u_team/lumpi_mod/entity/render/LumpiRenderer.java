package info.u_team.lumpi_mod.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import info.u_team.lumpi_mod.LumpiMod;
import info.u_team.lumpi_mod.entity.LumpiEntity;
import info.u_team.lumpi_mod.entity.render.model.LumpiModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class LumpiRenderer extends MobRenderer<LumpiEntity, LumpiModel> {
	
	private static final ResourceLocation LUMPI_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog.png");
	private static final ResourceLocation TAMED_LUMPI_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog_tame.png");
	private static final ResourceLocation ANGRY_LUMPI_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog_angry.png");
	
	public LumpiRenderer(EntityRendererManager renderManager) {
		super(renderManager, new LumpiModel(), 0.5F);
	}
	
	@Override
	protected float handleRotationFloat(LumpiEntity entity, float partialTicks) {
		return entity.getTailRotation();
	}
	
	@Override
	public void render(LumpiEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer buffer, int packedLight) {
		entityModel = new LumpiModel();
		
		
		if (entity.isWolfWet()) {
			final float shading = entity.getShadingWhileWet(partialTicks);
			entityModel.setTint(shading, shading, shading);
		}
		
		super.render(entity, entityYaw, partialTicks, matrixStackIn, buffer, packedLight);
		
		if (entity.isWolfWet()) {
			entityModel.setTint(1, 1, 1);
		}
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