package com.mohigster.morefeatures.datagen.tag;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.references.MFBlockIds;
import com.mohigster.morefeatures.references.MFBlockItemIds;
import com.mohigster.morefeatures.tag.MFBlockItemTags;
import com.mohigster.morefeatures.tag.MFBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.references.BlockIds;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MFBlockTagsProvider extends BlockTagsProvider {

    public MFBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        /*
         * This provider generates tags that allow me to apply every
         * copper or wool vertical slab to any block tag all at once,
         * without having to add each individual block in the collection
         */
        new MFBlockItemTagsProvider(tagId -> BlockItemTagsProvider.wrapForBlocks(this.tag(tagId.block()))).run();

        this.tag(BlockTags.SHEARS_MAJOR_BREAKING_SPEED)
                .addTag(MFBlockTags.WOOL_VERTICAL_SLABS);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(MFBlockTags.ALUMINIUM)
                .addTag(MFBlockTags.MAGNESIUM)
                .addTag(MFBlockTags.BISMUTH)
                .addTag(MFBlockTags.AZURITE)
                .addTag(MFBlockTags.FLUORITE)
                .addTag(MFBlockTags.NULLIUM)
                .addTag(MFBlockTags.PICKAXE_MINEABLE_VERTICAL_SLABS)
                .addTag(MFBlockTags.CONCRETE_PILLARS)
                .addTag(MFBlockTags.CUT_COPPER_PILLARS)
                .add(MFBlockItemIds.MAGIC_BLOCK.block())
                .add(MFBlockItemIds.COMPRESSOR_BLOCK.block())
                .add(MFBlockItemIds.EVERFROST_PACKED_ICE_ORE.block())
                .add(MFBlockItemIds.EVERFROST_BLUE_ICE_ORE.block())
                .add(MFBlockItemIds.VOID_ANCHOR.block());

        this.tag(MFBlockTags.AZURITE)
                .add(MFBlockItemIds.AZURITE_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_AZURITE_ORE.block())
                .add(MFBlockItemIds.NETHER_AZURITE_ORE.block())
                .add(MFBlockItemIds.END_AZURITE_ORE.block())
                .add(MFBlockItemIds.AZURITE_BLOCK.block())
                .add(MFBlockItemIds.RAW_AZURITE_BLOCK.block())
                .add(MFBlockItemIds.AZURITE_STAIRS.block())
                .add(MFBlockItemIds.AZURITE_SLAB.block())
                .add(MFBlockItemIds.AZURITE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.AZURITE_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.AZURITE_BUTTON.block())
                .add(MFBlockItemIds.AZURITE_DOOR.block())
                .add(MFBlockItemIds.AZURITE_TRAPDOOR.block())
                .add(MFBlockItemIds.AZURITE_FENCE.block())
                .add(MFBlockItemIds.AZURITE_FENCE_GATE.block())
                .add(MFBlockItemIds.AZURITE_WALL.block())
                .add(MFBlockIds.AZURITE_SIGN)
                .add(MFBlockIds.AZURITE_WALL_SIGN)
                .add(MFBlockIds.AZURITE_HANGING_SIGN)
                .add(MFBlockIds.AZURITE_WALL_HANGING_SIGN)
                .add(MFBlockItemIds.AZURITE_SHELF.block());

        this.tag(MFBlockTags.FLUORITE)
                .add(MFBlockItemIds.FLUORITE_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_FLUORITE_ORE.block())
                .add(MFBlockItemIds.NETHER_FLUORITE_ORE.block())
                .add(MFBlockItemIds.END_FLUORITE_ORE.block())
                .add(MFBlockItemIds.FLUORITE_BLOCK.block())
                .add(MFBlockItemIds.RAW_FLUORITE_BLOCK.block())
                .add(MFBlockItemIds.FLUORITE_STAIRS.block())
                .add(MFBlockItemIds.FLUORITE_SLAB.block())
                .add(MFBlockItemIds.FLUORITE_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.FLUORITE_BUTTON.block())
                .add(MFBlockItemIds.FLUORITE_DOOR.block())
                .add(MFBlockItemIds.FLUORITE_TRAPDOOR.block())
                .add(MFBlockItemIds.FLUORITE_FENCE.block())
                .add(MFBlockItemIds.FLUORITE_FENCE_GATE.block())
                .add(MFBlockItemIds.FLUORITE_WALL.block())
                .add(MFBlockIds.FLUORITE_SIGN)
                .add(MFBlockIds.FLUORITE_WALL_SIGN)
                .add(MFBlockIds.FLUORITE_HANGING_SIGN)
                .add(MFBlockIds.FLUORITE_WALL_HANGING_SIGN)
                .add(MFBlockItemIds.FLUORITE_SHELF.block());

        this.tag(MFBlockTags.ALUMINIUM)
                .add(MFBlockItemIds.ALUMINIUM_BLOCK.block())
                .add(MFBlockItemIds.ALUMINIUM_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_ALUMINIUM_ORE.block())
                .add(MFBlockItemIds.RAW_ALUMINIUM_BLOCK.block());

        this.tag(MFBlockTags.MAGNESIUM)
                .add(MFBlockItemIds.MAGNESIUM_BLOCK.block())
                .add(MFBlockItemIds.MAGNESIUM_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_MAGNESIUM_ORE.block())
                .add(MFBlockItemIds.RAW_MAGNESIUM_BLOCK.block());

        this.tag(MFBlockTags.BISMUTH)
                .add(MFBlockItemIds.BISMUTH_BLOCK.block())
                .add(MFBlockItemIds.BISMUTH_ORE.block())
                .add(MFBlockItemIds.RAW_BISMUTH_BLOCK.block());

        this.tag(MFBlockTags.COMPRESSOR_FLUIDS)
                .add(BlockIds.WATER);

        this.tag(MFBlockTags.NULLIUM)
                .add(MFBlockItemIds.DECREPIT_NULLIUM.block())
                .add(MFBlockItemIds.PALLID_NULLIUM.block());

        this.tag(MFBlockTags.SUPPORTS_END_ROOTS)
                .addTag(MFBlockTags.NULLIUM)
                .addTag(BlockTags.SUPPORTS_VEGETATION);

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(MFBlockTags.IS_MODDED_WOOD)
                .addTag(MFBlockTags.AXE_MINEABLE_VERTICAL_SLABS);

        this.tag(BlockTags.FENCES)
                .add(MFBlockItemIds.FLUORITE_FENCE.block())
                .add(MFBlockItemIds.AZURITE_FENCE.block())
                .addTag(MFBlockItemTags.CUSTOM_WOODEN_FENCES.block());

        this.tag(MFBlockTags.GEMSTONE_FENCES)
                .add(MFBlockItemIds.AZURITE_FENCE.block())
                .add(MFBlockItemIds.FLUORITE_FENCE.block());

        this.tag(BlockTags.WOODEN_FENCES)
                .addTag(MFBlockItemTags.CUSTOM_WOODEN_FENCES.block());

        this.tag(BlockTags.FENCE_GATES)
                .addTag(MFBlockItemTags.WOODEN_FENCE_GATES.block());

        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .addTag(MFBlockTags.BLOODWOOD_LOGS)
                .addTag(MFBlockTags.TAINTED_LOGS)
                .addTag(MFBlockTags.PALM_LOGS)
                .addTag(MFBlockTags.DECREPIT_LOGS)
                .addTag(MFBlockTags.PALLID_LOGS);


        this.tag(MFBlockTags.BLOODWOOD)
                .add(MFBlockItemIds.PLANKS.bloodwood().block())
                .add(MFBlockItemIds.WOODEN_STAIRS.bloodwood().block())
                .add(MFBlockItemIds.WOODEN_SLAB.bloodwood().block())
                .add(MFBlockItemIds.WOODEN_BUTTON.bloodwood().block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.bloodwood().block())
                .add(MFBlockItemIds.WOODEN_FENCE.bloodwood().block())
                .add(MFBlockItemIds.WOODEN_FENCE_GATE.bloodwood().block())
                .add(MFBlockItemIds.WOODEN_SHELF.bloodwood().block())
                .add(MFBlockIds.BLOODWOOD_SIGN)
                .add(MFBlockIds.BLOODWOOD_WALL_SIGN)
                .add(MFBlockIds.BLOODWOOD_HANGING_SIGN)
                .add(MFBlockIds.BLOODWOOD_WALL_HANGING_SIGN);

        this.tag(MFBlockTags.TAINTED)
                .add(MFBlockItemIds.PLANKS.tainted().block())
                .add(MFBlockItemIds.WOODEN_STAIRS.tainted().block())
                .add(MFBlockItemIds.WOODEN_SLAB.tainted().block())
                .add(MFBlockItemIds.WOODEN_BUTTON.tainted().block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.tainted().block())
                .add(MFBlockItemIds.WOODEN_FENCE.tainted().block())
                .add(MFBlockItemIds.WOODEN_FENCE_GATE.tainted().block())
                .add(MFBlockItemIds.WOODEN_SHELF.tainted().block())
                .add(MFBlockIds.TAINTED_SIGN)
                .add(MFBlockIds.TAINTED_WALL_SIGN)
                .add(MFBlockIds.TAINTED_HANGING_SIGN)
                .add(MFBlockIds.TAINTED_WALL_HANGING_SIGN);

        this.tag(MFBlockTags.PALM)
                .add(MFBlockItemIds.PLANKS.palm().block())
                .add(MFBlockItemIds.WOODEN_STAIRS.palm().block())
                .add(MFBlockItemIds.WOODEN_SLAB.palm().block())
                .add(MFBlockItemIds.WOODEN_BUTTON.palm().block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.palm().block())
                .add(MFBlockItemIds.WOODEN_FENCE.palm().block())
                .add(MFBlockItemIds.WOODEN_FENCE_GATE.palm().block())
                .add(MFBlockItemIds.WOODEN_TRAPDOOR.palm().block())
                .add(MFBlockItemIds.WOODEN_DOOR.palm().block())
                .add(MFBlockItemIds.WOODEN_SHELF.palm().block())
                .add(MFBlockIds.PALM_SIGN)
                .add(MFBlockIds.PALM_WALL_SIGN)
                .add(MFBlockIds.PALM_HANGING_SIGN)
                .add(MFBlockIds.PALM_WALL_HANGING_SIGN);

        this.tag(MFBlockTags.DECREPIT)
                .add(MFBlockItemIds.PLANKS.decrepit().block())
                .add(MFBlockItemIds.WOODEN_STAIRS.decrepit().block())
                .add(MFBlockItemIds.WOODEN_SLAB.decrepit().block())
                .add(MFBlockItemIds.WOODEN_VERTICAL_SLAB.decrepit().block())
                .add(MFBlockItemIds.WOODEN_BUTTON.decrepit().block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.decrepit().block())
                .add(MFBlockItemIds.WOODEN_FENCE.decrepit().block())
                .add(MFBlockItemIds.WOODEN_FENCE_GATE.decrepit().block())
                .add(MFBlockItemIds.WOODEN_SHELF.decrepit().block())
                .add(MFBlockIds.DECREPIT_SIGN)
                .add(MFBlockIds.DECREPIT_WALL_SIGN)
                .add(MFBlockIds.DECREPIT_HANGING_SIGN)
                .add(MFBlockIds.DECREPIT_WALL_HANGING_SIGN);

        this.tag(MFBlockTags.PALLID)
                .add(MFBlockItemIds.PLANKS.pallid().block())
                .add(MFBlockItemIds.WOODEN_STAIRS.pallid().block())
                .add(MFBlockItemIds.WOODEN_SLAB.pallid().block())
                .add(MFBlockItemIds.WOODEN_VERTICAL_SLAB.pallid().block())
                .add(MFBlockItemIds.WOODEN_BUTTON.pallid().block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.pallid().block())
                .add(MFBlockItemIds.WOODEN_FENCE.pallid().block())
                .add(MFBlockItemIds.WOODEN_FENCE_GATE.pallid().block())
                .add(MFBlockItemIds.WOODEN_SHELF.pallid().block())
                .add(MFBlockIds.PALLID_SIGN)
                .add(MFBlockIds.PALLID_WALL_SIGN)
                .add(MFBlockIds.PALLID_HANGING_SIGN)
                .add(MFBlockIds.PALLID_WALL_HANGING_SIGN);

        this.tag(MFBlockTags.IS_MODDED_WOOD) // This tag allows me to easily distinguish vanilla vs modded wood types in code
                .addTag(MFBlockTags.BLOODWOOD_LOGS)
                .addTag(MFBlockTags.BLOODWOOD)
                .addTag(MFBlockTags.TAINTED_LOGS)
                .addTag(MFBlockTags.TAINTED)
                .addTag(MFBlockTags.PALM_LOGS)
                .addTag(MFBlockTags.PALM)
                .addTag(MFBlockTags.DECREPIT_LOGS)
                .addTag(MFBlockTags.DECREPIT)
                .addTag(MFBlockTags.PALLID_LOGS)
                .addTag(MFBlockTags.PALLID);

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(MFBlockItemIds.ALUMINIUM_BLOCK.block())
                .add(MFBlockItemIds.ALUMINIUM_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_ALUMINIUM_ORE.block())
                .add(MFBlockItemIds.RAW_ALUMINIUM_BLOCK.block())
                .add(MFBlockItemIds.RAW_MAGNESIUM_BLOCK.block())
                .add(MFBlockItemIds.MAGNESIUM_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_MAGNESIUM_ORE.block())
                .add(MFBlockItemIds.COMPRESSOR_BLOCK.block())
                .add(MFBlockItemIds.EVERFROST_PACKED_ICE_ORE.block())
                .add(MFBlockItemIds.EVERFROST_BLUE_ICE_ORE.block())
                .add(MFBlockItemIds.MAGNESIUM_BLOCK.block())
                .add(MFBlockItemIds.VOID_ANCHOR.block())
                .addTag(MFBlockTags.AZURITE)
                .addTag(MFBlockTags.FLUORITE);

        this.tag(BlockTags.LEAVES)
                .add(MFBlocks.TAINTED_LEAVES.getKey())
                .add(MFBlocks.BLOODWOOD_LEAVES.getKey())
                .add(MFBlocks.PALM_LEAVES.getKey())
                .add(MFBlocks.DECREPIT_LEAVES.getKey());

        this.tag(BlockTags.PLANKS)
                .add(MFBlocks.PLANKS.bloodwood().getKey())
                .add(MFBlocks.PLANKS.tainted().getKey())
                .add(MFBlocks.PLANKS.palm().getKey())
                .add(MFBlocks.PLANKS.decrepit().getKey())
                .add(MFBlocks.PLANKS.pallid().getKey());

        this.tag(BlockTags.STAIRS).addTag(MFBlockItemTags.WOODEN_STAIRS.block());

        this.tag(BlockTags.SLABS).addTag(MFBlockItemTags.WOODEN_SLABS.block());

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(MFBlockItemIds.BISMUTH_ORE.block())
                .add(MFBlockItemIds.BISMUTH_BLOCK.block())
                .add(MFBlockItemIds.RAW_BISMUTH_BLOCK.block());

        this.tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(MFBlockItemIds.ALUMINIUM_BLOCK.block())
                .add(MFBlockItemIds.MAGNESIUM_BLOCK.block())
                .add(MFBlockItemIds.FLUORITE_BLOCK.block())
                .add(MFBlockItemIds.BISMUTH_BLOCK.block())
                .add(MFBlockItemIds.AZURITE_BLOCK.block());

        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(MFBlockItemIds.LOG.bloodwood().block())
                .add(MFBlockItemIds.LOG.tainted().block())
                .add(MFBlockItemIds.LOG.palm().block());

        this.tag(BlockTags.LOGS)
                .add(MFBlockItemIds.LOG.bloodwood().block())
                .add(MFBlockItemIds.LOG.tainted().block())
                .add(MFBlockItemIds.LOG.palm().block())
                .add(MFBlockItemIds.LOG.decrepit().block())
                .add(MFBlockItemIds.LOG.pallid().block());

        this.tag(BlockTags.PRESSURE_PLATES)
                .add(MFBlockItemIds.AZURITE_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.bloodwood().block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.tainted().block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.palm().block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.charred().block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.decrepit().block())
                .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.pallid().block());

        this.tag(BlockTags.BUTTONS)
                .add(MFBlockItemIds.AZURITE_BUTTON.block())
                .add(MFBlockItemIds.FLUORITE_BUTTON.block())
                .add(MFBlockItemIds.WOODEN_BUTTON.bloodwood().block())
                .add(MFBlockItemIds.WOODEN_BUTTON.tainted().block())
                .add(MFBlockItemIds.WOODEN_BUTTON.palm().block())
                .add(MFBlockItemIds.WOODEN_BUTTON.decrepit().block())
                .add(MFBlockItemIds.WOODEN_BUTTON.pallid().block());

        this.tag(BlockTags.WALLS)
                .add(MFBlockItemIds.AZURITE_WALL.block())
                .add(MFBlockItemIds.FLUORITE_WALL.block());

        this.tag(BlockTags.FLOWER_POTS)
                .add(MFBlockIds.POTTED_ROSE)
                .add(MFBlockIds.POTTED_BLUE_ROSE)
                .add(MFBlockIds.POTTED_BLOODWOOD_SAPLING)
                .add(MFBlockIds.POTTED_TAINTED_SAPLING)
                .add(MFBlockIds.POTTED_PALM_SAPLING)
                .add(MFBlockIds.POTTED_DECREPIT_SAPLING)
                .add(MFBlockIds.POTTED_PALLID_SAPLING);

        this.tag(MFBlockTags.VERTICAL_SLABS)
                .addTag(MFBlockTags.AXE_MINEABLE_VERTICAL_SLABS)
                .addTag(MFBlockTags.PICKAXE_MINEABLE_VERTICAL_SLABS)
                .addTag(MFBlockTags.WOOL_VERTICAL_SLABS);

        this.tag(MFBlockTags.AXE_MINEABLE_VERTICAL_SLABS)
                .add(MFBlockItemIds.OAK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.SPRUCE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.BIRCH_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.JUNGLE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.ACACIA_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.DARK_OAK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.CRIMSON_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.WARPED_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.MANGROVE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.CHERRY_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.BAMBOO_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.BAMBOO_MOSAIC_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.PALE_OAK_VERTICAL_SLAB.block())

                .addTag(MFBlockItemTags.CUSTOM_WOODEN_VERTICAL_SLABS.block());

        this.tag(MFBlockTags.PICKAXE_MINEABLE_VERTICAL_SLABS)
                .add(MFBlockItemIds.AZURITE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.FLUORITE_VERTICAL_SLAB.block())

                .add(MFBlockItemIds.STONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.COBBLESTONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.MOSSY_COBBLESTONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.SMOOTH_STONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.STONE_BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.MOSSY_STONE_BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.COBBLED_DEEPSLATE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.POLISHED_DEEPSLATE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.DEEPSLATE_BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.DEEPSLATE_TILE_VERTICAL_SLAB.block())

                .add(MFBlockItemIds.GRANITE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.POLISHED_GRANITE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.DIORITE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.POLISHED_DIORITE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.ANDESITE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.POLISHED_ANDESITE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.TUFF_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.POLISHED_TUFF_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.TUFF_BRICK_VERTICAL_SLAB.block())

                .add(MFBlockItemIds.SANDSTONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.CUT_SANDSTONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.SMOOTH_SANDSTONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.RED_SANDSTONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.CUT_RED_SANDSTONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB.block())

                .add(MFBlockItemIds.SULFUR_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.POLISHED_SULFUR_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.SULFUR_BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.CINNABAR_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.POLISHED_CINNABAR_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.CINNABAR_BRICK_VERTICAL_SLAB.block())

                .add(MFBlockItemIds.NETHER_BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.RED_NETHER_BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.BLACKSTONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.POLISHED_BLACKSTONE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.END_STONE_BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.PURPUR_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.QUARTZ_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.SMOOTH_QUARTZ_VERTICAL_SLAB.block())

                .add(MFBlockItemIds.PRISMARINE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.PRISMARINE_BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.DARK_PRISMARINE_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.MUD_BRICK_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.RESIN_BRICK_VERTICAL_SLAB.block())

                .addTag(MFBlockTags.CUT_COPPER_VERTICAL_SLABS);

        this.tag(BlockTags.FLOWERS)
                .add(MFBlockItemIds.ROSE.block())
                .add(MFBlockItemIds.BLUE_ROSE.block());

        this.tag(BlockTags.WOODEN_SHELVES)
                .add(MFBlockItemIds.WOODEN_SHELF.bloodwood().block())
                .add(MFBlockItemIds.WOODEN_SHELF.tainted().block())
                .add(MFBlockItemIds.WOODEN_SHELF.palm().block())
                .add(MFBlockItemIds.WOODEN_SHELF.decrepit().block())
                .add(MFBlockItemIds.WOODEN_SHELF.pallid().block());

        this.tag(MFBlockTags.GEMSTONE_SHELVES)
                .add(MFBlockItemIds.AZURITE_SHELF.block())
                .add(MFBlockItemIds.FLUORITE_SHELF.block());

        this.tag(BlockTags.SPELEOTHEMS)
                .add(MFBlockItemIds.ICICLE.block());

        this.tag(MFBlockTags.METAL_DETECTOR_FINDABLE)
                .add(MFBlockItemIds.ALUMINIUM_ORE.block())
                .add(MFBlockItemIds.MAGNESIUM_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_ALUMINIUM_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_MAGNESIUM_ORE.block())
                .add(MFBlockItemIds.BISMUTH_ORE.block())
                .add(BlockItemIds.IRON_ORE.block())
                .add(BlockItemIds.COPPER_ORE.block())
                .add(BlockItemIds.GOLD_ORE.block())
                .add(BlockItemIds.DEEPSLATE_IRON_ORE.block())
                .add(BlockItemIds.DEEPSLATE_COPPER_ORE.block())
                .add(BlockItemIds.DEEPSLATE_GOLD_ORE.block())
                .add(BlockItemIds.ANCIENT_DEBRIS.block())
                .add(BlockItemIds.NETHER_GOLD_ORE.block());

        this.tag(MFBlockTags.METAL_DETECTOR_LOW_COST)
                .add(MFBlockItemIds.MAGNESIUM_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_MAGNESIUM_ORE.block())
                .add(BlockItemIds.IRON_ORE.block())
                .add(BlockItemIds.DEEPSLATE_IRON_ORE.block());

        this.tag(MFBlockTags.METAL_DETECTOR_MEDIUM_COST)
                .add(BlockItemIds.GOLD_ORE.block())
                .add(BlockItemIds.NETHER_GOLD_ORE.block())
                .add(BlockItemIds.DEEPSLATE_GOLD_ORE.block());

        this.tag(MFBlockTags.METAL_DETECTOR_HIGH_COST)
                .add(BlockItemIds.ANCIENT_DEBRIS.block());

        this.tag(MFBlockTags.METAL_DETECTOR_BISMUTH_COST)
                .add(MFBlockItemIds.BISMUTH_ORE.block());

        this.tag(BlockTags.NYLIUM).add(MFBlockItemIds.CHARRED_NYLIUM.block());

        this.tag(MFBlockTags.SUPPORTS_CHARRED_ROOTS).addTag(BlockTags.SUPPORTS_WARPED_ROOTS);
    }
}
