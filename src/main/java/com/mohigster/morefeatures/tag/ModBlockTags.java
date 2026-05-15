package com.mohigster.morefeatures.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> INCORRECT_FOR_BISMUTH_TOOL = morefeaturesTag("incorrect_for_bismuth_tool");
    public static final TagKey<Block> NEEDS_BISMUTH_TOOL = morefeaturesTag("needs_bismuth_tool");

    private ModBlockTags() {
    }

    private static TagKey<Block> morefeaturesTag(String name) {
        return BlockTags.create(Identifier.fromNamespaceAndPath("morefeatures", name));
    }
    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.withDefaultNamespace(name));
    }

    public static TagKey<Block> create(Identifier name) {
        return TagKey.create(Registries.BLOCK, name);
    }
}
