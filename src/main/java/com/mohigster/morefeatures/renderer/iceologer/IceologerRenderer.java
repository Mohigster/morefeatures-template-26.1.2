package com.mohigster.morefeatures.renderer.iceologer;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.entity.custom.IceologerEntity;
import com.mohigster.morefeatures.entity.renderstate.IceologerRenderState;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class IceologerRenderer extends MobRenderer<IceologerEntity, IceologerRenderState, IllagerModel<IceologerRenderState>> {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "textures/entity/iceologer.png");
    public IceologerRenderer(EntityRendererProvider.Context context, IllagerModel<IceologerRenderState> model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public Identifier getTextureLocation(IceologerRenderState iceologerRenderState) {
        return TEXTURE;
    }

    @Override
    public IceologerRenderState createRenderState() {
        return new IceologerRenderState();
    }

    @Override
    public void extractRenderState(IceologerEntity entity, IceologerRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        // Extracts standard walking speeds, arm poses, and animations
        state.armPose = entity.getArmPose();
    }
}
