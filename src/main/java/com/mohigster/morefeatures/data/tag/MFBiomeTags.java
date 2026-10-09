package com.mohigster.morefeatures.data.tag;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class MFBiomeTags {
    public static final TagKey<Biome> DESERT = mfTag("desert");
    public static final TagKey<Biome> EVIL_FOREST = mfTag("evil_forest");
    public static final TagKey<Biome> END_FOREST = mfTag("end_forest");
    public static final TagKey<Biome> NETHER_FOREST = mfTag("nether_forest");
    public static final TagKey<Biome> OCEAN_CITADEL_BIOMES = mfTag("has_structure/ocean_citadel_biomes");

    private static TagKey<Biome> mfTag(String name) {
        return create(MFIdentifier.withMfNamespace(name));
    }

    private static TagKey<Biome> create(Identifier id) {
        return TagKey.create(Registries.BIOME, id);
    }
}
