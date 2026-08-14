package com.mohigster.morefeatures.data.resources.references.dimension;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;

public class MFLevelStemIds {
    public static final ResourceKey<LevelStem> BOREALIS = create("borialis");
    public static final ResourceKey<LevelStem> EVILDIM = create("evildim");

    private static ResourceKey<LevelStem> create(String name){
        return ResourceKey.create(Registries.LEVEL_STEM, MFIdentifier.withMfNamespace(name));
    }
}
