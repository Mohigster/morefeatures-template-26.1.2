package com.mohigster.morefeatures.data.resources.references.dimension;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.DimensionType;

public class MFDimensionTypeIds {
    public static final ResourceKey<DimensionType> EVILDIM = createDimType("evildim");
    public static final ResourceKey<DimensionType> BOREALIS = createDimType("borealis");

    private static ResourceKey<DimensionType> createDimType(String name){
        return ResourceKey.create(Registries.DIMENSION_TYPE, MFIdentifier.withMfNamespace(name + "_type"));
    }
}
