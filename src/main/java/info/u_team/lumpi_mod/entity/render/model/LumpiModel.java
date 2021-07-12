package info.u_team.lumpi_mod.entity.render.model;

import com.google.common.collect.ImmutableList;

import info.u_team.lumpi_mod.entity.LumpiEntity;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class LumpiModel extends TintedAgeableModel<LumpiEntity> {
	
	private final ModelRenderer WolfHead;
	private final ModelRenderer Breadpiece1;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg3;
	private final ModelRenderer Leg4;
	private final ModelRenderer Ear1;
	private final ModelRenderer Ear2;
	private final ModelRenderer Nose;
	private final ModelRenderer RightBreadPiece;
	private final ModelRenderer LeftBreadPiece;
	private final ModelRenderer Hotdog;
	
	public LumpiModel() {
		textureWidth = 128;
		textureHeight = 128;
		
		this.WolfHead = new ModelRenderer(this, 0, 0);
		this.WolfHead.addBox(-3.0F, -3.0F, -2.0F, 4, 4, 4);
		this.WolfHead.setRotationPoint(0.0F, 13.5F, -7.0F);
		this.WolfHead.setTextureSize(128, 128);
		this.WolfHead.mirror = true;
		this.setRotation(this.WolfHead, 0.0F, 0.0F, 0.0F);
		this.Breadpiece1 = new ModelRenderer(this, 21, 17);
		this.Breadpiece1.addBox(-3.0F, -7.0F, -1.5F, 6, 14, 3);
		this.Breadpiece1.setRotationPoint(-2.0F, 16.0F, 2.0F);
		this.Breadpiece1.setTextureSize(128, 128);
		this.Breadpiece1.mirror = true;
		this.setRotation(this.Breadpiece1, 1.570796F, 0.0F, 0.0F);
		this.Leg1 = new ModelRenderer(this, 0, 18);
		this.Leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
		this.Leg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
		this.Leg1.setTextureSize(128, 128);
		this.Leg1.mirror = true;
		this.setRotation(this.Leg1, 0.0F, 0.0F, 0.0F);
		this.Leg2 = new ModelRenderer(this, 0, 18);
		this.Leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
		this.Leg2.setRotationPoint(0.5F, 16.0F, 7.0F);
		this.Leg2.setTextureSize(128, 128);
		this.Leg2.mirror = true;
		this.setRotation(this.Leg2, 0.0F, 0.0F, 0.0F);
		this.Leg3 = new ModelRenderer(this, 0, 18);
		this.Leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
		this.Leg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
		this.Leg3.setTextureSize(128, 128);
		this.Leg3.mirror = true;
		this.setRotation(this.Leg3, 0.0F, 0.0F, 0.0F);
		this.Leg4 = new ModelRenderer(this, 0, 18);
		this.Leg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2);
		this.Leg4.setRotationPoint(0.5F, 16.0F, -4.0F);
		this.Leg4.setTextureSize(128, 128);
		this.Leg4.mirror = true;
		this.setRotation(this.Leg4, 0.0F, 0.0F, 0.0F);
		this.Ear1 = new ModelRenderer(this, 16, 14);
		this.Ear1.addBox(-3.0F, -2.0F, -1.5F, 1, 3, 3);
		this.Ear1.setRotationPoint(-1.0F, 12.5F, -7.0F);
		this.Ear1.setTextureSize(128, 128);
		this.Ear1.mirror = true;
		this.setRotation(this.Ear1, 0.0F, 0.0F, 0.0F);
		this.Ear2 = new ModelRenderer(this, 16, 14);
		this.Ear2.addBox(2.0F, -2.0F, -1.5F, 1, 3, 3);
		this.Ear2.setRotationPoint(-1.0F, 12.5F, -7.0F);
		this.Ear2.setTextureSize(128, 128);
		this.Ear2.mirror = false;
		this.setRotation(this.Ear2, 0.0F, 0.0F, 0.0F);
		this.Nose = new ModelRenderer(this, 0, 8);
		this.Nose.addBox(-2.0F, 0.0F, -4.0F, 2, 2, 2);
		this.Nose.setRotationPoint(0.0F, 12.5F, -7.0F);
		this.Nose.setTextureSize(128, 128);
		this.Nose.mirror = true;
		this.setRotation(this.Nose, 0.0F, 0.0F, 0.0F);
		this.RightBreadPiece = new ModelRenderer(this, 0, 50);
		this.RightBreadPiece.addBox(1.0F, -7.0F, 1.5F, 2, 14, 2);
		this.RightBreadPiece.setTextureSize(128, 128);
		this.RightBreadPiece.mirror = true;
		this.LeftBreadPiece = new ModelRenderer(this, 0, 34);
		this.LeftBreadPiece.addBox(-3.0F, -7.0F, 1.5F, 2, 14, 2);
		this.LeftBreadPiece.setTextureSize(128, 128);
		this.LeftBreadPiece.mirror = true;
		this.Hotdog = new ModelRenderer(this, 0, 66);
		this.Hotdog.addBox(-1.0F, -7.0F, 2.0F, 2, 14, 2, 0.2F);
		this.Hotdog.setTextureSize(128, 128);
		this.Hotdog.mirror = true;
		this.Breadpiece1.addChild(this.RightBreadPiece);
		this.Breadpiece1.addChild(this.LeftBreadPiece);
		this.Breadpiece1.addChild(this.Hotdog);
		
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(WolfHead);
	}
	
	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(Breadpiece1, Leg1, Leg2, Leg3, Leg4, Ear1, Ear2, Nose);
	}
	
	@Override
	public void setRotationAngles(LumpiEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.WolfHead.rotateAngleX = headPitch / 57.295776F;
		this.WolfHead.rotateAngleY = netHeadYaw / 57.295776F;
		this.Ear1.rotateAngleX = this.WolfHead.rotateAngleX;
		this.Ear1.rotateAngleY = this.WolfHead.rotateAngleY;
		this.Ear2.rotateAngleX = this.WolfHead.rotateAngleX;
		this.Ear2.rotateAngleY = this.WolfHead.rotateAngleY;
		this.Nose.rotateAngleX = this.WolfHead.rotateAngleX;
		this.Nose.rotateAngleY = this.WolfHead.rotateAngleY;
	}
	
	@Override
	public void setLivingAnimations(LumpiEntity entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		if (entityIn.isQueuedToSit()) {
			this.Breadpiece1.setRotationPoint(-1.0F, 18.0F, 0.0F);
			this.Breadpiece1.rotateAngleX = 0.7853982F;
			this.Leg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
			this.Leg1.rotateAngleX = 4.712389F;
			this.Leg2.setRotationPoint(0.5F, 22.0F, 2.0F);
			this.Leg2.rotateAngleX = 4.712389F;
			this.Leg3.rotateAngleX = 5.811947F;
			this.Leg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
			this.Leg4.rotateAngleX = 5.811947F;
			this.Leg4.setRotationPoint(0.51F, 17.0F, -4.0F);
		} else {
			this.Breadpiece1.setRotationPoint(-1.0F, 14.0F, 2.0F);
			this.Breadpiece1.rotateAngleX = 1.5707964F;
			this.Leg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
			this.Leg2.setRotationPoint(0.5F, 16.0F, 7.0F);
			this.Leg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
			this.Leg4.setRotationPoint(0.5F, 16.0F, -4.0F);
			this.Leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.Leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
			this.Leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount;
			this.Leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		}
		
		this.WolfHead.rotateAngleZ = entityIn.getInterestedAngle(partialTick) + entityIn.getShakeAngle(partialTick, 0.0F);
		this.Ear1.rotateAngleZ = this.WolfHead.rotateAngleZ;
		this.Ear2.rotateAngleZ = this.WolfHead.rotateAngleZ;
		this.Nose.rotateAngleZ = this.WolfHead.rotateAngleZ;
		this.Breadpiece1.rotateAngleZ = entityIn.getShakeAngle(partialTick, -0.16F);
		
	}
	
}
