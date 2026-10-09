package com.mohigster.morefeatures.data.tag;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class MFBlockTags {
    // Ores
    public static final TagKey<Block> AZURITE = mfTag("ores/azurite");
    public static final TagKey<Block> FLUORITE = mfTag("ores/fluorite");
    public static final TagKey<Block> ALUMINIUM = mfTag("ores/aluminium");
    public static final TagKey<Block> MAGNESIUM = mfTag("ores/magnesium");
    public static final TagKey<Block> BISMUTH = mfTag("ores/bismuth");

    // Gemstone blocks
    public static final TagKey<Block> GEMSTONE_SHELVES = mfTag("gemstone_shelves");
    public static final TagKey<Block> GEMSTONE_FENCES = mfTag("gemstone_fences");

    // Wood
    public static final TagKey<Block> BLOODWOOD = mfTag("wood/bloodwood");
    public static final TagKey<Block> TAINTED = mfTag("wood/tainted");
    public static final TagKey<Block> PALM = mfTag("wood/palm");
    public static final TagKey<Block> DECREPIT = mfTag("wood/decrepit");
    public static final TagKey<Block> PALLID = mfTag("wood/pallid");
    public static final TagKey<Block> CHARRED = mfTag("wood/charred");
    public static final TagKey<Block> IS_MODDED_WOOD = mfTag("wood/all_modded");

    public static final TagKey<Block> FLAMMABLE_WOOD = mfTag("wood/flammable");

    // Support roots
    public static final TagKey<Block> SUPPORTS_CHARRED_ROOTS = mfTag("supports_charred_roots");
    public static final TagKey<Block> SUPPORTS_CHARRED_FUNGUS = mfTag("supports_charred_fungus");
    public static final TagKey<Block> SUPPORTS_END_ROOTS = mfTag("supports_end_roots");

    // Vertical slabs
    public static final TagKey<Block> VERTICAL_SLABS = mfTag("vertical_slabs/all");
    public static final TagKey<Block> AXE_MINEABLE_VERTICAL_SLABS = mfTag("vertical_slabs/mineable/axe");
    public static final TagKey<Block> PICKAXE_MINEABLE_VERTICAL_SLABS = mfTag("vertical_slabs/mineable/pickaxe");
    public static final TagKey<Block> WOOL_VERTICAL_SLABS = MFBlockItemTags.WOOL_VERTICAL_SLABS.block();
    public static final TagKey<Block> CUT_COPPER_VERTICAL_SLABS = MFBlockItemTags.CUT_COPPER_VERTICAL_SLABS.block();
    public static final TagKey<Block> CONCRETE_VERTICAL_SLABS = MFBlockItemTags.CONCRETE_VERTICAL_SLABS.block();

    // Connectable
    public static final TagKey<Block> VERTICAL_SLAB_CONNECTABLE = mfTag("connectable/vertical_slab");
    public static final TagKey<Block> PILLAR_CONNECTABLE = mfTag("connectable/pillar");

    // Metal detector
    public static final TagKey<Block> METAL_DETECTOR_FINDABLE = mfTag("metal_detector/findable");
    public static final TagKey<Block> METAL_DETECTOR_LOW_COST = mfTag("metal_detector/cost/low");
    public static final TagKey<Block> METAL_DETECTOR_MEDIUM_COST = mfTag("metal_detector/cost/medium");
    public static final TagKey<Block> METAL_DETECTOR_HIGH_COST = mfTag("metal_detector/cost/high");
    public static final TagKey<Block> METAL_DETECTOR_BISMUTH_COST = mfTag("metal_detector/cost/bismuth"); // Bismuth is exceptionally rare and gets its own tag and cost

    // Pillars
    public static final TagKey<Block> CUT_COPPER_PILLARS = MFBlockItemTags.CUT_COPPER_PILLARS.block();
    public static final TagKey<Block> CONCRETE_PILLARS = MFBlockItemTags.CONCRETE_PILLARS.block();

    // Infiniburn
    public static final TagKey<Block> INFINIBURN_EVIL = mfTag("infiniburn_evil");
    public static final TagKey<Block> INFINIBURN_BOREALIS = mfTag("infiniburn_borealis");

    // Miscellaneous
    public static final TagKey<Block> COMPRESSOR_FLUIDS = mfTag("compressor_fluids");
    public static final TagKey<Block> NULLIUM = mfTag("nullium");

    private static TagKey<Block> mfTag(String name) {
        return TagKey.create(Registries.BLOCK, MFIdentifier.withMfNamespace(name));
    }
}
