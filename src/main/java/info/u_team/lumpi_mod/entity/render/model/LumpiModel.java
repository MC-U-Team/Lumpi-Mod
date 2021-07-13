package info.u_team.lumpi_mod.entity.render.model;

import com.google.common.collect.ImmutableList;

import info.u_team.lumpi_mod.entity.LumpiEntity;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class LumpiModel extends TintedAgeableModel<LumpiEntity> {
	
	private final ModelRenderer head;
	
	private final ModelRenderer body;
	private final ModelRenderer backLeftLeg;
	private final ModelRenderer backRightLeg;
	private final ModelRenderer frontLeftLeg;
	private final ModelRenderer frontRightLeg;
	
	private final ModelRenderer leftEar;
	private final ModelRenderer rightEar;
	private final ModelRenderer nose;
	
	public LumpiModel() {
		textureWidth = 128;
		textureHeight = 128;
		
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3, -3, -2, 4, 4, 4);
		head.setRotationPoint(0, 13.5F, -7);
		head.setTextureSize(textureWidth, textureHeight);
		
		body = new ModelRenderer(this, 21, 17);
		body.addBox(-3, -7, -1.5F, 6, 14, 3);
		body.setRotationPoint(-1, 14, 2);
		body.setTextureSize(textureWidth, textureHeight);
		
		final ModelRenderer upperBody = new ModelRenderer(this, 0, 66);
		upperBody.addBox(-1, -7, 2, 2, 14, 2, 0.2F);
		upperBody.setTextureSize(textureWidth, textureHeight);
		body.addChild(upperBody);
		
		final ModelRenderer rightBody = new ModelRenderer(this, 0, 50);
		rightBody.addBox(1, -7, 1.5F, 2, 14, 2);
		rightBody.setTextureSize(textureWidth, textureHeight);
		body.addChild(rightBody);
		
		final ModelRenderer leftBody = new ModelRenderer(this, 0, 34);
		leftBody.addBox(-3, -7, 1.5F, 2, 14, 2);
		leftBody.setTextureSize(textureWidth, textureHeight);
		body.addChild(leftBody);
		
		backLeftLeg = new ModelRenderer(this, 0, 18);
		backLeftLeg.addBox(-1, -1, -1, 2, 8, 2, 0, 1, 0);
		backLeftLeg.setRotationPoint(-2.5F, 16, 7);
		backLeftLeg.setTextureSize(textureWidth, textureHeight);
		
		backRightLeg = new ModelRenderer(this, 0, 18);
		backRightLeg.addBox(-1, -1, -1, 2, 8, 2, 0, 1, 0);
		backRightLeg.setRotationPoint(0.5F, 16, 7);
		backRightLeg.setTextureSize(textureWidth, textureHeight);
		
		frontLeftLeg = new ModelRenderer(this, 0, 18);
		frontLeftLeg.addBox(-1, -1, -1.5F, 2, 8, 2, 0, 1, 0);
		frontLeftLeg.setRotationPoint(-2.5F, 16, -2.5F);
		frontLeftLeg.setTextureSize(textureWidth, textureHeight);
		
		frontRightLeg = new ModelRenderer(this, 0, 18);
		frontRightLeg.addBox(-1, -1, -1.5F, 2, 8, 2, 0, 1, 0);
		frontRightLeg.setRotationPoint(0.5F, 16, -2.5F);
		frontRightLeg.setTextureSize(textureWidth, textureHeight);
		
		leftEar = new ModelRenderer(this, 16, 14);
		leftEar.addBox(-3, -2, -1.5F, 1, 3, 3);
		leftEar.setRotationPoint(-1, 12.5F, -7);
		leftEar.setTextureSize(textureWidth, textureHeight);
		
		rightEar = new ModelRenderer(this, 16, 14);
		rightEar.addBox(2, -2, -1.5F, 1, 3, 3);
		rightEar.setRotationPoint(-1, 12.5F, -7);
		rightEar.setTextureSize(textureWidth, textureHeight);
		
		nose = new ModelRenderer(this, 0, 8);
		nose.addBox(-2, 0, -4, 2, 2, 2);
		nose.setRotationPoint(0, 12.5F, -7);
		nose.setTextureSize(textureWidth, textureHeight);
	}
	
	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(head);
	}
	
	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(body, backLeftLeg, backRightLeg, frontLeftLeg, frontRightLeg, leftEar, rightEar, nose);
	}
	
	@Override
	public void setRotationAngles(LumpiEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleX = headPitch / 57.295776F;
		head.rotateAngleY = netHeadYaw / 57.295776F;
		
		leftEar.rotateAngleX = head.rotateAngleX;
		leftEar.rotateAngleY = head.rotateAngleY;
		
		rightEar.rotateAngleX = head.rotateAngleX;
		rightEar.rotateAngleY = head.rotateAngleY;
		
		nose.rotateAngleX = head.rotateAngleX;
		nose.rotateAngleY = head.rotateAngleY;
	}
	
	@Override
	public void setLivingAnimations(LumpiEntity entity, float limbSwing, float limbSwingAmount, float partialTick) {
		if (entity.isEntitySleeping()) {
			body.setRotationPoint(-1, 18, 0);
			body.rotateAngleX = 0.7853982F;
			
			backLeftLeg.setRotationPoint(-2.5F, 22, 2);
			backLeftLeg.rotateAngleX = 4.712389F;
			
			backRightLeg.setRotationPoint(0.5F, 22, 2);
			backRightLeg.rotateAngleX = 4.712389F;
			
			frontLeftLeg.setRotationPoint(-2.49F, 17, -4);
			frontLeftLeg.rotateAngleX = 5.811947F;
			
			frontRightLeg.setRotationPoint(0.51F, 17, -4);
			frontRightLeg.rotateAngleX = 5.811947F;
		} else {
			body.setRotationPoint(-1, 14, 2);
			body.rotateAngleX = 1.5707964F;
			
			backLeftLeg.setRotationPoint(-2.5F, 16, 7);
			backLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			
			backRightLeg.setRotationPoint(0.5F, 16, 7);
			backRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
			
			frontLeftLeg.setRotationPoint(-2.5F, 16, -2.5F);
			frontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
			
			frontRightLeg.setRotationPoint(0.5F, 16, -2.5F);
			frontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		}
		
		head.rotateAngleZ = entity.getInterestedAngle(partialTick) + entity.getShakeAngle(partialTick, 0);
		
		leftEar.rotateAngleZ = head.rotateAngleZ;
		rightEar.rotateAngleZ = head.rotateAngleZ;
		nose.rotateAngleZ = head.rotateAngleZ;
		
		body.rotateAngleZ = entity.getShakeAngle(partialTick, -0.16F);
	}
	
}
