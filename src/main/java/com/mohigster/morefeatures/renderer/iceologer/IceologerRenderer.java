package com.mohigster.morefeatures.renderer.iceologer;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.entity.custom.IceologerEntity;
import com.mohigster.morefeatures.entity.model.IceologerModel;
import com.mohigster.morefeatures.entity.renderstate.IceologerRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.monster.illager.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class IceologerRenderer extends MobRenderer<IceologerEntity, IceologerRenderState, IceologerModel> {
    private static final Identifier ICEOLOGER =
            Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "textures/entity/iceologer/iceologer.png");

    public IceologerRenderer(EntityRendererProvider.Context context) {
        super(context, new IceologerModel(context.bakeLayer(IceologerModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public Identifier getTextureLocation(IceologerRenderState renderState) {
        return ICEOLOGER;
    }

    @Override
    public IceologerRenderState createRenderState() {
        return new IceologerRenderState();
    }

    public void extractRenderState(final IceologerEntity entity, final IceologerRenderState state, final float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.isCastingSpell = entity.isCastingSpell();
    }
}
