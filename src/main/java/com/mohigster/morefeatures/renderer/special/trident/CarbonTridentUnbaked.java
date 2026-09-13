package com.mohigster.morefeatures.renderer.special.trident;

import com.mohigster.morefeatures.data.resources.references.MFEntityTypeIds;
import com.mohigster.morefeatures.entity.model.MFModelLayer;
import com.mohigster.morefeatures.renderer.special.trident.core.AbstractTridentUnbaked;
import com.mojang.serialization.MapCodec;

public class CarbonTridentUnbaked extends AbstractTridentUnbaked {
    private CarbonTridentUnbaked(){
        super(MFEntityTypeIds.CARBON_TRIDENT, MFModelLayer.CARBON_TRIDENT);
    }

    public static final MapCodec<CarbonTridentUnbaked> MAP_CODEC = unitStable(new CarbonTridentUnbaked());

    @Override
    public MapCodec<CarbonTridentUnbaked> type() {
        return MAP_CODEC;
    }
}
