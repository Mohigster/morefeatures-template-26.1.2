package com.mohigster.morefeatures.renderer.special.trident.core;

import com.mohigster.morefeatures.entity.model.MFTridentModel;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

public abstract class AbstractTridentUnbaked implements NoDataSpecialModelRenderer.Unbaked {
    private final ModelLayerLocation location;
    private final ResourceKey<EntityType<?>> type;

    public AbstractTridentUnbaked(ResourceKey<EntityType<?>> type, ModelLayerLocation location) {
        this.location = location;
        this.type = type;
    }

    @Override
    public CustomTridentSpecialRenderer bake(
            final SpecialModelRenderer.BakingContext context
    ) {
        return new CustomTridentSpecialRenderer(
                new MFTridentModel(context.entityModelSet()
                        .bakeLayer(this.location)),
                this.type);
    }

    protected static <T extends AbstractTridentUnbaked> MapCodec<T> unitStable(T unbaked){
        return MapCodec.unit(unbaked).stable();
    }
}
