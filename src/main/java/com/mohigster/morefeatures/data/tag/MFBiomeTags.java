package com.mohigster.morefeatures.data.tag;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class MFBiomeTags {
    public static final TagKey<Biome> DESERT = morefeaturesTag("desert");
    public static final TagKey<Biome> EVIL_FOREST = morefeaturesTag("evil_forest");
    public static final TagKey<Biome> END_FOREST = morefeaturesTag("end_forest");
    public static final TagKey<Biome> NETHER_FOREST = morefeaturesTag("nether_forest");
    public static final TagKey<Biome> OCEAN_CITADEL_BIOMES = morefeaturesTag("has_structure/ocean_citadel_biomes");

    private static TagKey<Biome> morefeaturesTag(String name) {
        return create(MFIdentifier.withMfNamespace(name));
    }

    public static TagKey<Biome> create(Identifier id) {
        return TagKey.create(Registries.BIOME, id);
    }
}
