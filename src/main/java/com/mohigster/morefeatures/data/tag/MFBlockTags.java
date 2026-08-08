package com.mohigster.morefeatures.data.tag;

import com.mohigster.morefeatures.data.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class MFBlockTags {
    // Ores
    public static final TagKey<Block> AZURITE = morefeaturesTag("ores/azurite");
    public static final TagKey<Block> FLUORITE = morefeaturesTag("ores/fluorite");
    public static final TagKey<Block> ALUMINIUM = morefeaturesTag("ores/aluminium");
    public static final TagKey<Block> MAGNESIUM = morefeaturesTag("ores/magnesium");
    public static final TagKey<Block> BISMUTH = morefeaturesTag("ores/bismuth");

    // Gemstone blocks
    public static final TagKey<Block> GEMSTONE_SHELVES = morefeaturesTag("gemstone_shelves");
    public static final TagKey<Block> GEMSTONE_FENCES = morefeaturesTag("gemstone_fences");

    // Wood
    public static final TagKey<Block> BLOODWOOD = morefeaturesTag("wood/bloodwood");
    public static final TagKey<Block> TAINTED = morefeaturesTag("wood/tainted");
    public static final TagKey<Block> PALM = morefeaturesTag("wood/palm");
    public static final TagKey<Block> DECREPIT = morefeaturesTag("wood/decrepit");
    public static final TagKey<Block> PALLID = morefeaturesTag("wood/pallid");
    public static final TagKey<Block> CHARRED = morefeaturesTag("wood/charred");
    public static final TagKey<Block> IS_MODDED_WOOD = morefeaturesTag("wood/all_modded");

    public static final TagKey<Block> FLAMMABLE_WOOD = morefeaturesTag("wood/flammable");

    // Support roots
    public static final TagKey<Block> SUPPORTS_CHARRED_ROOTS = morefeaturesTag("supports_charred_roots");
    public static final TagKey<Block> SUPPORTS_CHARRED_FUNGUS = morefeaturesTag("supports_charred_fungus");
    public static final TagKey<Block> SUPPORTS_END_ROOTS = morefeaturesTag("supports_end_roots");

    // Vertical slabs
    public static final TagKey<Block> VERTICAL_SLABS = morefeaturesTag("vertical_slabs/all");
    public static final TagKey<Block> AXE_MINEABLE_VERTICAL_SLABS = morefeaturesTag("vertical_slabs/mineable/axe");
    public static final TagKey<Block> PICKAXE_MINEABLE_VERTICAL_SLABS = morefeaturesTag("vertical_slabs/mineable/pickaxe");
    public static final TagKey<Block> WOOL_VERTICAL_SLABS = MFBlockItemTags.WOOL_VERTICAL_SLABS.block();
    public static final TagKey<Block> CUT_COPPER_VERTICAL_SLABS = MFBlockItemTags.CUT_COPPER_VERTICAL_SLABS.block();
    public static final TagKey<Block> CONCRETE_VERTICAL_SLABS = MFBlockItemTags.CONCRETE_VERTICAL_SLABS.block();

    // Connectable
    public static final TagKey<Block> VERTICAL_SLAB_CONNECTABLE = morefeaturesTag("connectable/vertical_slab");
    public static final TagKey<Block> PILLAR_CONNECTABLE = morefeaturesTag("connectable/pillar");

    // Metal detector
    public static final TagKey<Block> METAL_DETECTOR_FINDABLE = morefeaturesTag("metal_detector/findable");
    public static final TagKey<Block> METAL_DETECTOR_LOW_COST = morefeaturesTag("metal_detector/cost/low");
    public static final TagKey<Block> METAL_DETECTOR_MEDIUM_COST = morefeaturesTag("metal_detector/cost/medium");
    public static final TagKey<Block> METAL_DETECTOR_HIGH_COST = morefeaturesTag("metal_detector/cost/high");
    public static final TagKey<Block> METAL_DETECTOR_BISMUTH_COST = morefeaturesTag("metal_detector/cost/bismuth"); // Bismuth is exceptionally rare and gets its own tag and cost

    // Pillars
    public static final TagKey<Block> CUT_COPPER_PILLARS = MFBlockItemTags.CUT_COPPER_PILLARS.block();
    public static final TagKey<Block> CONCRETE_PILLARS = MFBlockItemTags.CONCRETE_PILLARS.block();

    // Miscellaneous
    public static final TagKey<Block> COMPRESSOR_FLUIDS = morefeaturesTag("compressor_fluids");
    public static final TagKey<Block> NULLIUM = morefeaturesTag("nullium");

    private static TagKey<Block> morefeaturesTag(String name) {
        return TagKey.create(Registries.BLOCK, MFIdentifier.withMfNamespace(name));
    }
}
