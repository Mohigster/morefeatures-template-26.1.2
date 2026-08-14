package com.mohigster.morefeatures.data.resources.references.dimension;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public class MFDimensionIds {
    public static final ResourceKey<Level> EVILDIM = create("evildim");
    public static final ResourceKey<Level> BOREALIS = create("borialis");

    private static ResourceKey<Level> create(String name){
        return ResourceKey.create(Registries.DIMENSION, MFIdentifier.withMfNamespace(name));
    }
}
