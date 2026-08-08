package com.mohigster.morefeatures.entity.model;

import com.mohigster.morefeatures.entity.renderstate.IceologerRenderState;
import com.mohigster.morefeatures.data.references.MFIdentifier;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import org.jspecify.annotations.NonNull;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class IceologerModel extends EntityModel<IceologerRenderState> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			MFIdentifier.withMfNamespace("iceologer"), "main"
	);

	private final ModelPart head;
	private final ModelPart hood;
	private final ModelPart body;
	private final ModelPart robe;
	private final ModelPart main;
	private final ModelPart leg;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart arm;
	private final ModelPart left;
	private final ModelPart right;
	private final ModelPart crossed_arm;
	private final ModelPart mirrored;


	public IceologerModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.hood = this.head.getChild("hood");
		this.body = root.getChild("body");
		this.robe = this.body.getChild("robe");
		this.main = this.body.getChild("main");
		this.leg = root.getChild("leg");
		this.left_leg = this.leg.getChild("left_leg");
		this.right_leg = this.leg.getChild("right_leg");
		this.arm = root.getChild("arm");
		this.left = this.arm.getChild("left");
		this.right = this.arm.getChild("right");
		this.crossed_arm = this.arm.getChild("crossed_arm");
		this.mirrored = this.crossed_arm.getChild("mirrored");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
						.texOffs(24, 1).addBox(-1.0F, -3.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hood = head.addOrReplaceChild("hood", CubeListBuilder.create()
						.texOffs(59, 5).addBox(-5.0F, -11.0F, -5.0F, 10.0F, 11.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(),
				PartPose.offset(-2.0F, 24.0F, 0.0F));

		// robe() group folder
		PartDefinition robeGroup = body.addOrReplaceChild("robe", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		// The actual inner robe cube (Depth is 6, so Z is shifted forward to -3.0F to center it)
		robeGroup.addOrReplaceChild("robe_cube", CubeListBuilder.create()
						.texOffs(4, 39).addBox(-3.0F, -24.0F, -3.0F, 10.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mainGroup = body.addOrReplaceChild("main", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		// The actual inner main_body cube
		mainGroup.addOrReplaceChild("main_body", CubeListBuilder.create()
						.texOffs(28, 18).addBox(-2.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg = partdefinition.addOrReplaceChild("leg", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, -3.0F));

		PartDefinition left_leg = leg.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 2.0F));

		PartDefinition right_leg = leg.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, 0.0F, 1.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

		PartDefinition arm = partdefinition.addOrReplaceChild("arm", CubeListBuilder.create(),
				PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition left = arm.addOrReplaceChild("left", CubeListBuilder.create()
						.texOffs(39, 35).addBox(4.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right = arm.addOrReplaceChild("right", CubeListBuilder.create()
						.texOffs(39, 35).addBox(-8.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition crossed_arm = arm.addOrReplaceChild("crossed_arm", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_shoulder = crossed_arm.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(62, 38).addBox(-3.0F, -3.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(53, 55).addBox(1.0F, 1.0F, -2.0F, 10.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 2.0F, -1.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition mirrored = crossed_arm.addOrReplaceChild("mirrored", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_shoulder = mirrored.addOrReplaceChild("right_shoulder", CubeListBuilder.create().texOffs(62, 38).mirror().addBox(-3.0F, -3.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, 2.0F, -1.0F, -0.7854F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void setupAnim(@NonNull IceologerRenderState renderState) {
		super.setupAnim(renderState);
		float animationSpeed = renderState.walkAnimationSpeed;
		float animationPos = renderState.walkAnimationPos;
		this.head.yRot = renderState.yRot * (float) (Math.PI / 180.0);
		this.head.xRot = renderState.xRot * (float) (Math.PI / 180.0);
		if (!renderState.isCastingSpell) {
			this.right.xRot = Mth.cos(animationPos * 0.6662F + (float) Math.PI) * 2.0F * animationSpeed * 0.5F;
			this.right.yRot = 0.0F;
			this.right.zRot = 0.0F;
			this.left.xRot = Mth.cos(animationPos * 0.6662F) * 2.0F * animationSpeed * 0.5F;
			this.left.yRot = 0.0F;
			this.left.zRot = 0.0F;
		}
		this.right_leg.xRot = Mth.cos(animationPos * 0.6662F) * 1.4F * animationSpeed * 0.5F;
		this.right_leg.yRot = 0.0F;
		this.right_leg.zRot = 0.0F;
		this.left_leg.xRot = Mth.cos(animationPos * 0.6662F + (float) Math.PI) * 1.4F * animationSpeed * 0.5F;
		this.left_leg.yRot = 0.0F;
		this.left_leg.zRot = 0.0F;

		AbstractIllager.IllagerArmPose pose = renderState.armPose;

		if (renderState.isCastingSpell) {
			this.right.z = 0.0F;
			this.right.x = -5.0F;
			this.left.z = 0.0F;
			this.left.x = 5.0F;
			this.right.xRot = Mth.cos(renderState.ageInTicks * 0.6662F) * 0.25F;
			this.left.xRot = Mth.cos(renderState.ageInTicks * 0.6662F) * 0.25F;
			this.right.zRot = (float) (Math.PI * 3.0 / 4.0);
			this.left.zRot = (float) (-Math.PI * 3.0 / 4.0);
			this.right.yRot = 0.0F;
			this.left.yRot = 0.0F;
		}

		boolean crossedArms = pose == AbstractIllager.IllagerArmPose.CROSSED;
		this.crossed_arm.visible = crossedArms && !renderState.isCastingSpell;
		this.left.visible = !crossedArms || renderState.isCastingSpell;
		this.right.visible = !crossedArms || renderState.isCastingSpell;
	}
}