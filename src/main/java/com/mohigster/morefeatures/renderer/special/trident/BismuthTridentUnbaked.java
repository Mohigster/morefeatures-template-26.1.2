package com.mohigster.morefeatures.renderer.special.trident;

import com.mohigster.morefeatures.data.resources.references.MFEntityTypeIds;
import com.mohigster.morefeatures.entity.model.MFModelLayer;
import com.mohigster.morefeatures.renderer.special.trident.core.AbstractTridentUnbaked;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

public class BismuthTridentUnbaked extends AbstractTridentUnbaked {
    private BismuthTridentUnbaked(){}

    public static final MapCodec<BismuthTridentUnbaked> MAP_CODEC = MapCodec.unit(new BismuthTridentUnbaked());

    @Override
    protected @NonNull ModelLayerLocation getModelLocation() {
        return MFModelLayer.BISMUTH_TRIDENT;
    }

    @Override
    protected @NonNull ResourceKey<EntityType<?>> getTrident() {
        return MFEntityTypeIds.BISMUTH_TRIDENT;
    }

    @NullMarked
    @Override
    public MapCodec<BismuthTridentUnbaked> type() {
        return MAP_CODEC;
    }
}
