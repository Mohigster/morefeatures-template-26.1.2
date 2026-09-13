package com.mohigster.morefeatures.data.resources.references;

import com.mohigster.morefeatures.block.collection.gemstone.GemstoneCollection;
import com.mohigster.morefeatures.block.collection.vanilla.VanillaWoodCollection;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.block.WeatheringCopperCollection;

public class MFBlockItemIds {

    // --- Bloodwood ---
    public static final BlockItemId BLOODWOOD_LEAVES = createId("bloodwood_leaves");
    public static final BlockItemId BLOODWOOD_SAPLING = createId("bloodwood_sapling");

    // --- Tainted ---
    public static final BlockItemId TAINTED_LEAVES = createId("tainted_leaves");
    public static final BlockItemId TAINTED_SAPLING = createId("tainted_sapling");

    // --- Palm ---
    public static final BlockItemId PALM_LEAVES = createId("palm_leaves");
    public static final BlockItemId PALM_SAPLING = createId("palm_sapling");

    // --- Charred ---
    public static final BlockItemId CHARRED_ROOTS = createId("charred_roots");
    public static final BlockItemId CHARRED_WART_BLOCK = createId("charred_wart_block");
    public static final BlockItemId CHARRED_FUNGUS = createId("charred_fungus");
    public static final BlockItemId CHARRED_NYLIUM = createId("charred_nylium");

    public static final BlockItemId SCORCHED_VINES = createId("scorched_vines");
    public static final BlockItemId SMOLDERED_VINES = createId("smoldered_vines");

    // --- Decrepit ---
    public static final BlockItemId DECREPIT_LEAVES = createId("decrepit_leaves");
    public static final BlockItemId DECREPIT_SAPLING = createId("decrepit_sapling");
    public static final BlockItemId DECREPIT_ROOTS = createId("decrepit_roots");
    public static final BlockItemId DECREPIT_NULLIUM = createId("decrepit_nullium");

    // --- Pallid ---
    public static final BlockItemId PALLID_LEAVES = createId("pallid_leaves");
    public static final BlockItemId PALLID_SAPLING = createId("pallid_sapling");
    public static final BlockItemId PALLID_ROOTS = createId("pallid_roots");
    public static final BlockItemId PALLID_NULLIUM = createId("pallid_nullium");

    // --- Aluminium ---
    public static final BlockItemId ALUMINIUM_BLOCK = createId("aluminium_block");
    public static final BlockItemId ALUMINIUM_ORE = createId("aluminium_ore");
    public static final BlockItemId DEEPSLATE_ALUMINIUM_ORE = createId("deepslate_aluminium_ore");
    public static final BlockItemId RAW_ALUMINIUM_BLOCK = createId("raw_aluminium_block");

    // --- Magnesium ---
    public static final BlockItemId MAGNESIUM_BLOCK = createId("magnesium_block");
    public static final BlockItemId MAGNESIUM_ORE = createId("magnesium_ore");
    public static final BlockItemId RAW_MAGNESIUM_BLOCK = createId("raw_magnesium_block");
    public static final BlockItemId DEEPSLATE_MAGNESIUM_ORE = createId("deepslate_magnesium_ore");

    // --- Bismuth ---
    public static final BlockItemId BISMUTH_BLOCK = createId("bismuth_block");
    public static final BlockItemId BISMUTH_ORE = createId("bismuth_ore");
    public static final BlockItemId RAW_BISMUTH_BLOCK = createId("raw_bismuth_block");

    // --- Everfrost ---
    public static final BlockItemId EVERFROST_PACKED_ICE_ORE = createId("everfrost_packed_ice_ore");
    public static final BlockItemId EVERFROST_BLUE_ICE_ORE = createId("everfrost_blue_ice_ore");

    // --- Flowers ---
    public static final BlockItemId ROSE = createId("rose");
    public static final BlockItemId BLUE_ROSE = createId("blue_rose");

    // --- Special Blocks ---
    public static final BlockItemId COMPRESSOR_BLOCK = createId("compressor_block");
    public static final BlockItemId VOID_ANCHOR = createId("void_anchor");
    public static final BlockItemId MAGIC_BLOCK = createId("magic_block");
    public static final BlockItemId EVIL_PORTAL = createId("evil_portal");
    public static final BlockItemId ICICLE = createId("icicle");
    public static final BlockItemId TEMPORAL_DILATOR = createId("temporal_dilator");

    // ----- VANILLA VERTICAL SLABS -----

    // --- Stone & Deepslate ---
    public static final BlockItemId STONE_VERTICAL_SLAB = createId("stone_vertical_slab");
    public static final BlockItemId COBBLESTONE_VERTICAL_SLAB = createId("cobblestone_vertical_slab");
    public static final BlockItemId MOSSY_COBBLESTONE_VERTICAL_SLAB = createId("mossy_cobblestone_vertical_slab");
    public static final BlockItemId SMOOTH_STONE_VERTICAL_SLAB = createId("smooth_stone_vertical_slab");
    public static final BlockItemId STONE_BRICK_VERTICAL_SLAB = createId("stone_brick_vertical_slab");
    public static final BlockItemId MOSSY_STONE_BRICK_VERTICAL_SLAB = createId("mossy_stone_brick_vertical_slab");
    public static final BlockItemId COBBLED_DEEPSLATE_VERTICAL_SLAB = createId("cobbled_deepslate_vertical_slab");
    public static final BlockItemId POLISHED_DEEPSLATE_VERTICAL_SLAB = createId("polished_deepslate_vertical_slab");
    public static final BlockItemId DEEPSLATE_BRICK_VERTICAL_SLAB = createId("deepslate_brick_vertical_slab");
    public static final BlockItemId DEEPSLATE_TILE_VERTICAL_SLAB = createId("deepslate_tile_vertical_slab");

    // --- Other stone type ---
    public static final BlockItemId GRANITE_VERTICAL_SLAB = createId("granite_vertical_slab");
    public static final BlockItemId POLISHED_GRANITE_VERTICAL_SLAB = createId("polished_granite_vertical_slab");
    public static final BlockItemId DIORITE_VERTICAL_SLAB = createId("diorite_vertical_slab");
    public static final BlockItemId POLISHED_DIORITE_VERTICAL_SLAB = createId("polished_diorite_vertical_slab");
    public static final BlockItemId ANDESITE_VERTICAL_SLAB = createId("andesite_vertical_slab");
    public static final BlockItemId POLISHED_ANDESITE_VERTICAL_SLAB = createId("polished_andesite_vertical_slab");
    public static final BlockItemId TUFF_VERTICAL_SLAB = createId("tuff_vertical_slab");
    public static final BlockItemId POLISHED_TUFF_VERTICAL_SLAB = createId("polished_tuff_vertical_slab");
    public static final BlockItemId TUFF_BRICK_VERTICAL_SLAB = createId("tuff_brick_vertical_slab");

    // --- Sandstone ---
    public static final BlockItemId SANDSTONE_VERTICAL_SLAB = createId("sandstone_vertical_slab");
    public static final BlockItemId SMOOTH_SANDSTONE_VERTICAL_SLAB = createId("smooth_sandstone_vertical_slab");
    public static final BlockItemId CUT_SANDSTONE_VERTICAL_SLAB = createId("cut_sandstone_vertical_slab");
    public static final BlockItemId RED_SANDSTONE_VERTICAL_SLAB = createId("red_sandstone_vertical_slab");
    public static final BlockItemId SMOOTH_RED_SANDSTONE_VERTICAL_SLAB = createId("smooth_red_sandstone_vertical_slab");
    public static final BlockItemId CUT_RED_SANDSTONE_VERTICAL_SLAB = createId("cut_red_sandstone_vertical_slab");

    // -- Sulfur & Cinnabar ---
    public static final BlockItemId SULFUR_VERTICAL_SLAB = createId("sulfur_vertical_slab");
    public static final BlockItemId POLISHED_SULFUR_VERTICAL_SLAB = createId("polished_sulfur_vertical_slab");
    public static final BlockItemId SULFUR_BRICK_VERTICAL_SLAB = createId("sulfur_brick_vertical_slab");
    public static final BlockItemId CINNABAR_VERTICAL_SLAB = createId("cinnabar_vertical_slab");
    public static final BlockItemId POLISHED_CINNABAR_VERTICAL_SLAB = createId("polished_cinnabar_vertical_slab");
    public static final BlockItemId CINNABAR_BRICK_VERTICAL_SLAB = createId("cinnabar_brick_vertical_slab");

    // --- Nether & End ---
    public static final BlockItemId NETHER_BRICK_VERTICAL_SLAB = createId("nether_brick_vertical_slab");
    public static final BlockItemId RED_NETHER_BRICK_VERTICAL_SLAB = createId("red_nether_brick_vertical_slab");
    public static final BlockItemId BLACKSTONE_VERTICAL_SLAB = createId("blackstone_vertical_slab");
    public static final BlockItemId POLISHED_BLACKSTONE_VERTICAL_SLAB = createId("polished_blackstone_vertical_slab");
    public static final BlockItemId POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB = createId("polished_blackstone_brick_vertical_slab");
    public static final BlockItemId END_STONE_BRICK_VERTICAL_SLAB = createId("end_stone_brick_vertical_slab");
    public static final BlockItemId PURPUR_VERTICAL_SLAB = createId("purpur_vertical_slab");
    public static final BlockItemId QUARTZ_VERTICAL_SLAB = createId("quartz_vertical_slab");
    public static final BlockItemId SMOOTH_QUARTZ_VERTICAL_SLAB = createId("smooth_quartz_vertical_slab");

    // --- Misc ---
    public static final BlockItemId PRISMARINE_VERTICAL_SLAB = createId("prismarine_vertical_slab");
    public static final BlockItemId PRISMARINE_BRICK_VERTICAL_SLAB = createId("prismarine_brick_vertical_slab");
    public static final BlockItemId DARK_PRISMARINE_VERTICAL_SLAB = createId("dark_prismarine_vertical_slab");
    public static final BlockItemId BRICK_VERTICAL_SLAB = createId("brick_vertical_slab");
    public static final BlockItemId MUD_BRICK_VERTICAL_SLAB = createId("mud_brick_vertical_slab");
    public static final BlockItemId RESIN_BRICK_VERTICAL_SLAB = createId("resin_brick_vertical_slab");

    // ----- BLOCK COLLECTIONS -----
    public static final VanillaWoodCollection<BlockItemId> VANILLA_WOOD_VERTICAL_SLAB = createVanillaWoodId("vertical_slab");
    public static final VanillaWoodCollection<BlockItemId> VANILLA_WOOD_PILLAR = createVanillaWoodId("pillar");

    public static final WeatheringCopperCollection<BlockItemId> CUT_COPPER_VERTICAL_SLAB = createSimpleCopperId("cut_copper_vertical_slab");
    public static final ColorCollection<BlockItemId> WOOL_VERTICAL_SLAB = createSimpleColouredId("wool_vertical_slab");
    public static final ColorCollection<BlockItemId> CONCRETE_VERTICAL_SLAB = createSimpleColouredId("concrete_vertical_slab");

    public static final WeatheringCopperCollection<BlockItemId> CUT_COPPER_PILLAR = createSimpleCopperId("cut_copper_pillar");
    public static final ColorCollection<BlockItemId> CONCRETE_PILLAR = createSimpleColouredId("concrete_pillar");

    public static final WoodTypeCollection<BlockItemId> PLANKS = createSimpleWoodId("planks");
    public static final WoodTypeCollection<BlockItemId> WOODEN_STAIRS = createSimpleWoodId("stairs");
    public static final WoodTypeCollection<BlockItemId> WOODEN_SLAB = createSimpleWoodId("slab");
    public static final WoodTypeCollection<BlockItemId> WOODEN_VERTICAL_SLAB = createSimpleWoodId("vertical_slab");
    public static final WoodTypeCollection<BlockItemId> WOODEN_FENCE = createSimpleWoodId("fence");
    public static final WoodTypeCollection<BlockItemId> WOODEN_FENCE_GATE = createSimpleWoodId("fence_gate");
    public static final WoodTypeCollection<BlockItemId> WOODEN_PRESSURE_PLATE = createSimpleWoodId("pressure_plate");
    public static final WoodTypeCollection<BlockItemId> WOODEN_BUTTON = createSimpleWoodId("button");
    public static final WoodTypeCollection<BlockItemId> WOODEN_DOOR = createSimpleWoodId("door");
    public static final WoodTypeCollection<BlockItemId> WOODEN_TRAPDOOR = createSimpleWoodId("trapdoor");
    public static final WoodTypeCollection<BlockItemId> WOODEN_SHELF = createSimpleWoodId("shelf");
    public static final WoodTypeCollection<BlockItemId> WOODEN_SIGN = createSimpleWoodId("sign");
    public static final WoodTypeCollection<BlockItemId> WOODEN_HANGING_SIGN = createSimpleWoodId("hanging_sign");

    public static final WoodTypeCollection<BlockItemId> LOG = createSimpleWoodId("log");
    public static final WoodTypeCollection<BlockItemId> WOOD = createSimpleWoodId("wood");

    // Stripped wood is a bit different. It uses stripped_woodtype_log or stripped_woodtype_wood.
    // Since the wood type is in the middle of the id, it uses a separate method to add the stripped prefix
    public static final WoodTypeCollection<BlockItemId> STRIPPED_LOG = createStrippedWoodId("log");
    public static final WoodTypeCollection<BlockItemId> STRIPPED_WOOD = createStrippedWoodId("wood");

    public static final GemstoneCollection<BlockItemId> ORE = createSimpleGemstoneId("ore");
    public static final GemstoneCollection<BlockItemId> DEEPSLATE_ORE = createPrefixedGemstoneId("deepslate", "ore");
    public static final GemstoneCollection<BlockItemId> NETHER_ORE = createPrefixedGemstoneId("nether", "ore");
    public static final GemstoneCollection<BlockItemId> END_ORE = createPrefixedGemstoneId("end", "ore");
    public static final GemstoneCollection<BlockItemId> BLOCK = createSimpleGemstoneId("block");
    public static final GemstoneCollection<BlockItemId> RAW_BLOCK = createPrefixedGemstoneId("raw", "block");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_STAIRS = createSimpleGemstoneId("stairs");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_SLAB = createSimpleGemstoneId("slab");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_VERTICAL_SLAB = createSimpleGemstoneId("vertical_slab");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_FENCE = createSimpleGemstoneId("fence");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_FENCE_GATE = createSimpleGemstoneId("fence_gate");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_PRESSURE_PLATE = createSimpleGemstoneId("pressure_plate");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_BUTTON = createSimpleGemstoneId("button");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_WALL = createSimpleGemstoneId("wall");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_DOOR = createSimpleGemstoneId("door");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_TRAPDOOR = createSimpleGemstoneId("trapdoor");
    public static final GemstoneCollection<BlockItemId> GEMSTONE_SHELF = createSimpleGemstoneId("shelf");

    public static final BlockItemId TEST_COLUMN = createId("test_column");

    private static BlockItemId createId(String name) {
        Identifier id = MFIdentifier.withMfNamespace(name);
        return BlockItemId.create(id, id);
    }

    private static ColorCollection<BlockItemId> createSimpleColouredId(String name) {
        return ColorCollection.prefixWithColor(ColorCollection.create(name)).map(MFBlockItemIds::createId);
    }

    private static WeatheringCopperCollection<BlockItemId> createSimpleCopperId(String name) {
        return WeatheringCopperCollection.prefixWithState(WeatheringCopperCollection.create(name)).map(MFBlockItemIds::createId);
    }

    private static WoodTypeCollection<BlockItemId> createSimpleWoodId(String name) {
        return WoodTypeCollection.prefixWithSet(WoodTypeCollection.createForAll(name)).map(MFBlockItemIds::createId);
    }

    private static WoodTypeCollection<BlockItemId> createStrippedWoodId(String name) {
        return WoodTypeCollection.prefixWithSet("stripped", WoodTypeCollection.createForAll(name)).map(MFBlockItemIds::createId);
    }

    private static VanillaWoodCollection<BlockItemId> createVanillaWoodId(String name) {
        return VanillaWoodCollection.prefixWithSet(VanillaWoodCollection.createForAll(name)).map(MFBlockItemIds::createId);
    }

    private static GemstoneCollection<BlockItemId> createSimpleGemstoneId(String name) {
        return GemstoneCollection.prefixWithGem(GemstoneCollection.createForAll(name)).map(MFBlockItemIds::createId);
    }

    private static GemstoneCollection<BlockItemId> createPrefixedGemstoneId(String prefix, String name) {
        return GemstoneCollection.prefixWithGem(prefix, GemstoneCollection.createForAll(name)).map(MFBlockItemIds::createId);
    }
}
