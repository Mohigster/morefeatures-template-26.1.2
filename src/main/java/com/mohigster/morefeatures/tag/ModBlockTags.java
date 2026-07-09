package com.mohigster.morefeatures.tag;

import com.mohigster.morefeatures.block.references.ModBlockItemIds;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class ModBlockTags {
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
    public static final TagKey<Block> VERTICAL_SLABS = morefeaturesTag("vertical_slabs");
    public static final TagKey<Block> AXE_MINEABLE_VERTICAL_SLABS = morefeaturesTag("axe_mineable_vertical_slabs");
    public static final TagKey<Block> PICKAXE_MINEABLE_VERTICAL_SLABS = morefeaturesTag("pickaxe_mineable_vertical_slabs");
    public static final TagKey<Block> WOOL_VERTICAL_SLABS = ModBlockItemTags.WOOL_VERTICAL_SLABS.block();
    public static final TagKey<Block> CUT_COPPER_VERTICAL_SLABS = ModBlockItemTags.CUT_COPPER_VERTICAL_SLABS.block();

    private ModBlockTags() {
    }

    private static TagKey<Block> morefeaturesTag(String name) {
        return BlockTags.create(Identifier.fromNamespaceAndPath("morefeatures", name));
    }

    public static TagKey<Block> create(Identifier name) {
        return TagKey.create(Registries.BLOCK, name);
    }
}
