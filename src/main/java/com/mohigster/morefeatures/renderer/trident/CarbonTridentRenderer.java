package com.mohigster.morefeatures.renderer.trident;

import com.mohigster.morefeatures.entity.model.CarbonTridentModel;
import com.mohigster.morefeatures.references.MFIdentifier;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mohigster.morefeatures.model.MFModelLayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraft.client.renderer.entity.state.ThrownTridentRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;


public class CarbonTridentRenderer extends ThrownTridentRenderer {
    public static final Identifier CARBON_TRIDENT_LOCATION = MFIdentifier.withMfNamespace("textures/entity/trident/carbon_trident.png");

    private final CarbonTridentModel model;

    public CarbonTridentRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new CarbonTridentModel(context.bakeLayer(MFModelLayer.CARBON_TRIDENT));
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
                            RenderTypes.entityGlint(),
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
