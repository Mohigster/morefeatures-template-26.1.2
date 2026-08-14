package com.mohigster.morefeatures.renderer.special.shield;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.renderer.special.shield.core.AbstractShieldUnbaked;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.component.DataComponentMap;

public class BismuthShieldUnbaked extends AbstractShieldUnbaked {
    public static final MapCodec<BismuthShieldUnbaked> MAP_CODEC = MapCodec.unit(new BismuthShieldUnbaked());

    @Override
    protected String getShieldMaterialName() {
        return "bismuth";
    }

    @Override
    protected String getModId() {
        return MoreFeatures.MODID;
    }

    @Override
    public MapCodec<? extends SpecialModelRenderer.Unbaked<DataComponentMap>> type() {
        return MAP_CODEC;
    }
}
