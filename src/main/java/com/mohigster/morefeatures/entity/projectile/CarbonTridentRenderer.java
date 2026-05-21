package com.mohigster.morefeatures.entity.projectile;

import com.mohigster.morefeatures.MoreFeatures;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mohigster.morefeatures.model.ModModelLayer;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraft.client.renderer.entity.state.ThrownTridentRenderState;
import net.minecraft.client.renderer.feature.ItemFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;


public class CarbonTridentRenderer extends ThrownTridentRenderer {
    public static final Identifier CARBON_TRIDENT_LOCATION = Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "textures/entity/trident/carbon_trident.png");

    private final CarbonTridentModel model;

    public CarbonTridentRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new CarbonTridentModel(context.bakeLayer(ModelLayers.TRIDENT));
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
                .submitModel(this.model, Unit.INSTANCE, poseStack, CARBON_TRIDENT_LOCATION, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        if (state.isFoil) {
            submitNodeCollector.order(1)
                    .submitModel(
                            this.model,
                            Unit.INSTANCE,
                            poseStack,
                            ItemFeatureRenderer.getFoilRenderType(this.model.renderType(CARBON_TRIDENT_LOCATION), false),
                            state.lightCoords,
                            OverlayTexture.NO_OVERLAY,
                            state.outlineColor,
                            null
                    );
        }
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public ThrownTridentRenderState createRenderState() {
        return new ThrownTridentRenderState();
    }


}
