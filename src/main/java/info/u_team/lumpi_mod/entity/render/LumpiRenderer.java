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
	
	private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog.png");
	private static final ResourceLocation TAMED_WOLF_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog_tame.png");
	private static final ResourceLocation ANGRY_WOLF_TEXTURES = new ResourceLocation(LumpiMod.MODID, "textures/entity/hotdog_angry.png");
	
	public LumpiRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new LumpiModel(), 0.5F);
	}
	
	protected float handleRotationFloat(LumpiEntity livingBase, float partialTicks) {
		return livingBase.getTailRotation();
	}
	
	public void render(LumpiEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if (entityIn.isWolfWet()) {
			float f = entityIn.getShadingWhileWet(partialTicks);
			this.entityModel.setTint(f, f, f);
		}
		
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		if (entityIn.isWolfWet()) {
			this.entityModel.setTint(1.0F, 1.0F, 1.0F);
		}
		
	}
	
	public ResourceLocation getEntityTexture(LumpiEntity entity) {
		if (entity.isTamed()) {
			return TAMED_WOLF_TEXTURES;
		} else {
			return entity.isAngry() ? ANGRY_WOLF_TEXTURES : WOLF_TEXTURES;
		}
	}
}