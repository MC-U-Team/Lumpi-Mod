package info.u_team.lumpi_mod.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import info.u_team.lumpi_mod.entity.LumpiSpitEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.LlamaSpitModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LumpiSpitRenderer extends EntityRenderer<LumpiSpitEntity> {
	
	private static final ResourceLocation LLAMA_SPIT_TEXTURE = new ResourceLocation("textures/entity/llama/spit.png");
	
	private final LlamaSpitModel<LumpiSpitEntity> model = new LlamaSpitModel<>();
	
	public LumpiSpitRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void render(LumpiSpitEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
		matrixStack.push();
		
		matrixStack.translate(0, 0.15, 0);
		matrixStack.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationYaw, entity.rotationYaw) - 90));
		matrixStack.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch)));
		
		matrixStack.scale(5.5F, 1.5F, 1.5F);
		
		model.setRotationAngles(entity, partialTicks, 0, -0.1F, 0, 0);
		model.render(matrixStack, buffer.getBuffer(model.getRenderType(getEntityTexture(entity))), packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 0, 1);
		
		matrixStack.pop();
		
		super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
	}
	
	@Override
	public ResourceLocation getEntityTexture(LumpiSpitEntity entity) {
		return LLAMA_SPIT_TEXTURE;
	}
}