package com.mohigster.morefeatures.tag;

import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class MFBlockTags {
    public static final TagKey<Block> AZURITE = morefeaturesTag("azurite");
    public static final TagKey<Block> FLUORITE = morefeaturesTag("fluorite");
    public static final TagKey<Block> GEMSTONE_SHELVES = morefeaturesTag("gemstone_shelves");
    public static final TagKey<Block> GEMSTONE_FENCES = morefeaturesTag("gemstone_fences");
    public static final TagKey<Block> WOOD_FENCES = morefeaturesTag("wood_fences");
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
    public static final TagKey<Block> COMPRESSOR_FLUIDS = morefeaturesTag("compressor_fluids");
    public static final TagKey<Block> IS_MODDED_WOOD = morefeaturesTag("is_modded_wood");
    public static final TagKey<Block> NULLIUM = morefeaturesTag("nullium");
    public static final TagKey<Block> SUPPORTS_END_ROOTS = morefeaturesTag("supports_end_roots");
    public static final TagKey<Block> VERTICAL_SLABS = morefeaturesTag("vertical_slabs");
    public static final TagKey<Block> AXE_MINEABLE_VERTICAL_SLABS = morefeaturesTag("axe_mineable_vertical_slabs");
    public static final TagKey<Block> PICKAXE_MINEABLE_VERTICAL_SLABS = morefeaturesTag("pickaxe_mineable_vertical_slabs");
    public static final TagKey<Block> WOOL_VERTICAL_SLABS = MFBlockItemTags.WOOL_VERTICAL_SLABS.block();
    public static final TagKey<Block> CUT_COPPER_VERTICAL_SLABS = MFBlockItemTags.CUT_COPPER_VERTICAL_SLABS.block();

    private static TagKey<Block> morefeaturesTag(String name) {
        return create(MFIdentifier.withMfNamespace(name));
    }

    public static TagKey<Block> create(Identifier id) {
        return TagKey.create(Registries.BLOCK, id);
    }
}
