package com.mohigster.morefeatures.renderer.special.trident;

import com.mohigster.morefeatures.data.resources.references.MFEntityTypeIds;
import com.mohigster.morefeatures.entity.model.MFModelLayer;
import com.mohigster.morefeatures.renderer.special.trident.core.AbstractTridentUnbaked;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import org.jspecify.annotations.NullMarked;

public class CarbonTridentUnbaked extends AbstractTridentUnbaked {
    private CarbonTridentUnbaked(){}

    public static final MapCodec<CarbonTridentUnbaked> MAP_CODEC = MapCodec.unit(new CarbonTridentUnbaked());

    @Override
    protected ModelLayerLocation getModelLocation() {
        return MFModelLayer.CARBON_TRIDENT;
    }

    @Override
    protected ResourceKey<EntityType<?>> getTrident() {
        return MFEntityTypeIds.CARBON_TRIDENT;
    }

    @NullMarked
    @Override
    public MapCodec<CarbonTridentUnbaked> type() {
        return MAP_CODEC;
    }
}
