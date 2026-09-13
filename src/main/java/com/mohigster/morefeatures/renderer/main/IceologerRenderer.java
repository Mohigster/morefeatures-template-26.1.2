package com.mohigster.morefeatures.renderer.main;

import com.mohigster.morefeatures.entity.custom.IceologerEntity;
import com.mohigster.morefeatures.entity.model.IceologerModel;
import com.mohigster.morefeatures.entity.renderstate.IceologerRenderState;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class IceologerRenderer extends MobRenderer<IceologerEntity, IceologerRenderState, IceologerModel> {
    private static final Identifier ICEOLOGER =
            MFIdentifier.withMfNamespace("textures/entity/iceologer/iceologer.png");

    private static final IceologerRenderState RENDER_STATE = new IceologerRenderState();

    public IceologerRenderer(EntityRendererProvider.Context context) {
        super(context, new IceologerModel(context.bakeLayer(IceologerModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public Identifier getTextureLocation(IceologerRenderState renderState) {
        return ICEOLOGER;
    }

    @Override
    public IceologerRenderState createRenderState() {
        return RENDER_STATE;
    }

    @Override
    public void extractRenderState(final IceologerEntity entity, final IceologerRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.setCastingSpell(entity.isCastingSpell());
    }
}
