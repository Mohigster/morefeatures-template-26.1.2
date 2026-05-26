package com.mohigster.morefeatures.entity.projectile;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.model.ModModelLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraft.client.renderer.entity.state.ThrownTridentRenderState;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;

import static com.mohigster.morefeatures.entity.projectile.CarbonTridentRenderer.CARBON_TRIDENT_LOCATION;


public class BismuthTridentRenderer extends ThrownTridentRenderer {
    public static final Identifier BISMUTH_TRIDENT_LOCATION = Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "textures/entity/trident/bismuth_trident.png");

    private final BismuthTridentModel model;

    public BismuthTridentRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new BismuthTridentModel(context.bakeLayer(ModModelLayer.BISMUTH_TRIDENT));
    }

    @Override
    public void submit(
            ThrownTridentRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            CameraRenderState camera
    ) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(state.xRot + 90.0F));
        submitNodeCollector.order(0)
                .submitModel(this.model, Unit.INSTANCE, poseStack, BISMUTH_TRIDENT_LOCATION, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        if (state.isFoil) {
            submitNodeCollector.order(1)
                    .submitModel(
                            this.model,
                            Unit.INSTANCE,
                            poseStack,
                            ItemFeatureRenderer.getFoilRenderType(this.model.renderType(BISMUTH_TRIDENT_LOCATION), false),
                            state.lightCoords,
                            OverlayTexture.NO_OVERLAY,
                            state.outlineColor,
                            null
                    );
        }
        poseStack.popPose();
    }

    @Override
    public ThrownTridentRenderState createRenderState() {
        return new ThrownTridentRenderState();
    }


}
