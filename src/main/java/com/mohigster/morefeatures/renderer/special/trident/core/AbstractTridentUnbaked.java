package com.mohigster.morefeatures.renderer.special.trident.core;

import com.mohigster.morefeatures.entity.model.MFTridentModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

public abstract class AbstractTridentUnbaked implements NoDataSpecialModelRenderer.Unbaked{
    protected abstract ModelLayerLocation getModelLocation();
    protected abstract ResourceKey<EntityType<?>> getTrident();

    public CustomTridentSpecialRenderer bake(
            final SpecialModelRenderer.BakingContext context
    ) {
        return new CustomTridentSpecialRenderer(
                new MFTridentModel(context.entityModelSet()
                        .bakeLayer(this.getModelLocation())),
                this.getTrident());
    }
}
