package com.mohigster.morefeatures.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> INCORRECT_FOR_BISMUTH_TOOL = morefeaturesTag("incorrect_for_bismuth_tool");
    public static final TagKey<Block> NEEDS_BISMUTH_TOOL = morefeaturesTag("needs_bismuth_tool");
    public static final TagKey<Block> BLOODWOOD_LOGS = morefeaturesTag("bloodwood_logs");
    public static final TagKey<Block> BLOODWOOD = morefeaturesTag("bloodwood");
    public static final TagKey<Block> TAINTED_LOGS = morefeaturesTag("tainted_logs");
    public static final TagKey<Block> TAINTED = morefeaturesTag("tainted");
    public static final TagKey<Block> PALM_LOGS = morefeaturesTag("palm_logs");
    public static final TagKey<Block> PALM = morefeaturesTag("palm");
    public static final TagKey<Block> DECREPIT_LOGS = morefeaturesTag("decrepit_logs");
    public static final TagKey<Block> DECREPIT = morefeaturesTag("decrepit");
    public static final TagKey<Block> PALLID_LOGS = morefeaturesTag("pallid_logs");
    public static final TagKey<Block> PALLID = morefeaturesTag("pallid");
    public static final TagKey<Block> COMPRESSOR_FLUIDS = morefeaturesTag("is_liquid");
    public static final TagKey<Block> IS_MODDED_WOOD = morefeaturesTag("is_modded_wood");
    public static final TagKey<Block> NULLIUM_BLOCKS = morefeaturesTag("nullium_blocks");

    private ModBlockTags() {
    }

    private static TagKey<Block> morefeaturesTag(String name) {
        return BlockTags.create(Identifier.fromNamespaceAndPath("morefeatures", name));
    }

    public static TagKey<Block> create(Identifier name) {
        return TagKey.create(Registries.BLOCK, name);
    }
}
