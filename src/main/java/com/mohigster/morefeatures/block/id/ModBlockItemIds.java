package com.mohigster.morefeatures.block.id;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

public class ModBlockItemIds {
    // --- Bloodwood ---
    public static final BlockItemId BLOODWOOD_LOG = create("bloodwood_log");
    public static final BlockItemId BLOODWOOD = create("bloodwood");
    public static final BlockItemId BLOODWOOD_PLANKS = create("bloodwood_planks");
    public static final BlockItemId STRIPPED_BLOODWOOD = create("stripped_bloodwood");
    public static final BlockItemId STRIPPED_BLOODWOOD_LOG = create("stripped_bloodwood_log");
    public static final BlockItemId BLOODWOOD_STAIRS = create("bloodwood_stairs");
    public static final BlockItemId BLOODWOOD_SLAB = create("bloodwood_slab");
    public static final BlockItemId BLOODWOOD_LEAVES = create("bloodwood_leaves");
    public static final BlockItemId BLOODWOOD_SAPLING = create("bloodwood_sapling");

    // --- Tainted ---
    public static final BlockItemId TAINTED_LOG = create("tainted_log");
    public static final BlockItemId TAINTED_WOOD = create("tainted_wood");
    public static final BlockItemId TAINTED_PLANKS = create("tainted_planks");
    public static final BlockItemId STRIPPED_TAINTED_WOOD = create("stripped_tainted_wood");
    public static final BlockItemId STRIPPED_TAINTED_LOG = create("stripped_tainted_log");
    public static final BlockItemId TAINTED_STAIRS = create("tainted_stairs");
    public static final BlockItemId TAINTED_SLAB = create("tainted_slab");
    public static final BlockItemId TAINTED_LEAVES = create("tainted_leaves");
    public static final BlockItemId TAINTED_SAPLING = create("tainted_sapling");

    // --- Palm ---
    public static final BlockItemId PALM_LOG = create("palm_log");
    public static final BlockItemId PALM_WOOD = create("palm_wood");
    public static final BlockItemId PALM_PLANKS = create("palm_planks");
    public static final BlockItemId STRIPPED_PALM_WOOD = create("stripped_palm_wood");
    public static final BlockItemId STRIPPED_PALM_LOG = create("stripped_palm_log");
    public static final BlockItemId PALM_STAIRS = create("palm_stairs");
    public static final BlockItemId PALM_SLAB = create("palm_slab");
    public static final BlockItemId PALM_FENCE = create("palm_fence");
    public static final BlockItemId PALM_FENCE_GATE = create("palm_fence_gate");
    public static final BlockItemId PALM_LEAVES = create("palm_leaves");
    public static final BlockItemId PALM_SAPLING = create("palm_sapling");

    // --- Pallid ---
    public static final BlockItemId PALLID_LOG = create("pallid_log");
    public static final BlockItemId PALLID_WOOD = create("pallid_wood");
    public static final BlockItemId PALLID_PLANKS = create("pallid_planks");
    public static final BlockItemId STRIPPED_PALLID_WOOD = create("stripped_pallid_wood");
    public static final BlockItemId STRIPPED_PALLID_LOG = create("stripped_pallid_log");
    public static final BlockItemId PALLID_STAIRS = create("pallid_stairs");
    public static final BlockItemId PALLID_SLAB = create("pallid_slab");
    public static final BlockItemId PALLID_FENCE = create("pallid_fence");
    public static final BlockItemId PALLID_FENCE_GATE = create("pallid_fence_gate");
    public static final BlockItemId PALLID_LEAVES = create("pallid_leaves");
    public static final BlockItemId PALLID_SAPLING = create("pallid_sapling");

    // --- Decrepit ---
    public static final BlockItemId DECREPIT_LOG = create("decrepit_log");
    public static final BlockItemId DECREPIT_WOOD = create("decrepit_wood");
    public static final BlockItemId DECREPIT_PLANKS = create("decrepit_planks");
    public static final BlockItemId STRIPPED_DECREPIT_WOOD = create("stripped_decrepit_wood");
    public static final BlockItemId STRIPPED_DECREPIT_LOG = create("stripped_decrepit_log");
    public static final BlockItemId DECREPIT_STAIRS = create("decrepit_stairs");
    public static final BlockItemId DECREPIT_SLAB = create("decrepit_slab");
    public static final BlockItemId DECREPIT_FENCE = create("decrepit_fence");
    public static final BlockItemId DECREPIT_FENCE_GATE = create("decrepit_fence_gate");
    public static final BlockItemId DECREPIT_LEAVES = create("decrepit_leaves");
    public static final BlockItemId DECREPIT_SAPLING = create("decrepit_sapling");

    // --- Aluminium ---
    public static final BlockItemId ALUMINIUM_BLOCK = create("aluminium_block");
    public static final BlockItemId ALUMINIUM_ORE = create("aluminium_ore");
    public static final BlockItemId DEEPSLATE_ALUMINIUM_ORE = create("deepslate_aluminium_ore");
    public static final BlockItemId RAW_ALUMINIUM_BLOCK = create("raw_aluminium_block");

    // --- Magnesium ---
    public static final BlockItemId MAGNESIUM_BLOCK = create("magnesium_block");
    public static final BlockItemId MAGNESIUM_ORE = create("magnesium_ore");
    public static final BlockItemId RAW_MAGNESIUM_BLOCK = create("raw_magnesium_block");
    public static final BlockItemId DEEPSLATE_MAGNESIUM_ORE = create("deepslate_magnesium_ore");

    // --- Bismuth ---
    public static final BlockItemId BISMUTH_BLOCK = create("bismuth_block");
    public static final BlockItemId BISMUTH_ORE = create("bismuth_ore");
    public static final BlockItemId RAW_BISMUTH_BLOCK = create("raw_bismuth_block");

    // --- Flowers ---
    public static final BlockItemId ROSE = create("rose");
    public static final BlockItemId BLUE_ROSE = create("blue_rose");

    // --- Special Blocks ---
    public static final BlockItemId COMPRESSOR_BLOCK = create("compressor_block");
    public static final BlockItemId VOID_ANCHOR = create("void_anchor");
    public static final BlockItemId MAGIC_BLOCK = create("magic_block");
    public static final BlockItemId EVIL_PORTAL = create("evil_portal");
    public static final BlockItemId ICICLE = create("icicle");

    public static BlockItemId create(String blockName, String itemName) {
        return BlockItemId.create(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, blockName), Identifier.fromNamespaceAndPath(MoreFeatures.MODID, itemName));
    }

    public static BlockItemId create(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name);
        return BlockItemId.create(id, id);
    }
}
