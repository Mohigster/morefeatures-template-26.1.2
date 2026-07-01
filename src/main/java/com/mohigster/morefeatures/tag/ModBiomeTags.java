package com.mohigster.morefeatures.tag;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModBiomeTags{
    public static final TagKey<Biome> DESERT = morefeaturesTag("desert");

    private static TagKey<Biome> morefeaturesTag(String name) {
        return TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name));
    }
}
