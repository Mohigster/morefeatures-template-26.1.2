package com.mohigster.morefeatures.renderer.special.trident;

import com.mohigster.morefeatures.data.resources.references.MFEntityTypeIds;
import com.mohigster.morefeatures.entity.model.MFModelLayer;
import com.mohigster.morefeatures.renderer.special.trident.core.AbstractTridentUnbaked;
import com.mojang.serialization.MapCodec;

public class BismuthTridentUnbaked extends AbstractTridentUnbaked {
    private BismuthTridentUnbaked(){
        super(MFEntityTypeIds.BISMUTH_TRIDENT, MFModelLayer.BISMUTH_TRIDENT);
    }

    public static final MapCodec<BismuthTridentUnbaked> MAP_CODEC = unitStable(new BismuthTridentUnbaked());

    @Override
    public MapCodec<BismuthTridentUnbaked> type() {
        return MAP_CODEC;
    }
}
