package com.mohigster.morefeatures.renderer.main;

import com.mohigster.morefeatures.entity.model.MFTridentModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import net.minecraft.client.renderer.entity.state.ThrownTridentRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EntityType;


public class MFTridentRenderer extends ThrownTridentRenderer {
    private final MFTridentModel model;
    private final ResourceKey<EntityType<?>> tridentKey;

    public MFTridentRenderer(
            EntityRendererProvider.Context context,
            ResourceKey<EntityType<?>> tridentKey,
            ModelLayerLocation layerToBake
    ) {
        super(context);
        this.tridentKey = tridentKey;
        this.model = MFTridentModel.create(context, layerToBake);
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
        submitNodeCollector.order(0).submitModel(this.getModel(), Unit.INSTANCE, poseStack,
                this.getTexture(), state.lightCoords,
                OverlayTexture.NO_OVERLAY, state.outlineColor, null
        );
        if (state.isFoil) {
            submitNodeCollector.order(1)
                    .submitModel(
                            this.getModel(),
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

    protected MFTridentModel getModel(){
        return this.model;
    }

    protected ResourceKey<EntityType<?>> getTridentKey() {
        return this.tridentKey;
    }

    protected Identifier getTexture(){
        return MFTridentModel.getTexture(this.getTridentKey());
    }
}
