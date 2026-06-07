package com.mohigster.morefeatures.renderer.iceologer;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.entity.renderstate.IceologerRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.Identifier;

public class IceologerHoodLayer extends RenderLayer<IceologerRenderState, IllagerModel<IceologerRenderState>> {
    private static final Identifier HOOD_TEXTURE =
            Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "textures/entity/iceologer_hood.png");

    public IceologerHoodLayer(RenderLayerParent<IceologerRenderState, IllagerModel<IceologerRenderState>> renderer) {
        super(renderer);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, IceologerRenderState state, float v, float v1) {
        // Only render if the entity isn't invisible
        if (!state.isInvisible) {
            // 1. Snag the parent model (the base Illager model)
            IllagerModel<IceologerRenderState> model = this.getParentModel();

            // 2. FORCE the vanilla hat/hood layer to be visible for this render pass
            model.getHat().visible = true;

            // 3. Render the hood texture over the top of the model
            renderColoredCutoutModel(model, HOOD_TEXTURE, poseStack, submitNodeCollector, i, state, -1);

            // 4. Turn it back off so it doesn't accidentally bleed into other illager renders
            model.getHat().visible = false;
        }
    }

    private void renderColoredCutoutModel(IllagerModel<IceologerRenderState> model, Identifier hoodTexture, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, IceologerRenderState state, int i1) {
    }
}
