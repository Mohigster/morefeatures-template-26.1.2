package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.references.MFBlockIds;
import com.mohigster.morefeatures.references.MFBlockItemIds;
import com.mohigster.morefeatures.tag.MFBlockTags;
import com.mohigster.morefeatures.tag.MFItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.references.BlockIds;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;
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

        tag(BlockTags.SHEARS_MAJOR_BREAKING_SPEED)
                .addTag(MFBlockTags.WOOL_VERTICAL_SLABS);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(MFBlockItemIds.ALUMINIUM_BLOCK.block())
                .add(MFBlockItemIds.ALUMINIUM_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_ALUMINIUM_ORE.block())
                .add(MFBlockItemIds.RAW_ALUMINIUM_BLOCK.block())
                .add(MFBlockItemIds.RAW_MAGNESIUM_BLOCK.block())
                .add(MFBlockItemIds.MAGNESIUM_BLOCK.block())
                .add(MFBlockItemIds.MAGNESIUM_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_MAGNESIUM_ORE.block())
                .add(MFBlockItemIds.MAGIC_BLOCK.block())
                .add(MFBlockItemIds.BISMUTH_BLOCK.block())
                .add(MFBlockItemIds.RAW_BISMUTH_BLOCK.block())
                .add(MFBlockItemIds.COMPRESSOR_BLOCK.block())
                .add(MFBlockItemIds.EVERFROST_PACKED_ICE_ORE.block())
                .add(MFBlockItemIds.EVERFROST_BLUE_ICE_ORE.block())
                .add(MFBlockItemIds.BISMUTH_ORE.block())
                .addTag(MFBlockTags.NULLIUM)
                .add(MFBlockItemIds.VOID_ANCHOR.block())
                .addTag(MFBlockTags.AZURITE)
                .addTag(MFBlockTags.FLUORITE)
                .addTag(MFBlockTags.PICKAXE_MINEABLE_VERTICAL_SLABS);

        tag(MFBlockTags.AZURITE)
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

        tag(MFBlockTags.FLUORITE)
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

        tag(MFBlockTags.COMPRESSOR_FLUIDS)
                .add(BlockIds.WATER);

        tag(MFBlockTags.NULLIUM)
                .add(MFBlockItemIds.DECREPIT_NULLIUM.block())
                .add(MFBlockItemIds.PALLID_NULLIUM.block());

        tag(MFBlockTags.SUPPORTS_END_ROOTS)
                .addTag(MFBlockTags.NULLIUM)
                .addTag(BlockTags.SUPPORTS_VEGETATION);

        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(MFBlockTags.IS_MODDED_WOOD)
                .addTag(MFBlockTags.AXE_MINEABLE_VERTICAL_SLABS);

        tag(BlockTags.FENCES)
                .add(MFBlockItemIds.FLUORITE_FENCE.block())
                .add(MFBlockItemIds.AZURITE_FENCE.block())
                .add(MFBlockItemIds.BLOODWOOD_FENCE.block())
                .add(MFBlockItemIds.TAINTED_FENCE.block())
                .add(MFBlockItemIds.PALM_FENCE.block())
                .add(MFBlockItemIds.DECREPIT_FENCE.block())
                .add(MFBlockItemIds.PALLID_FENCE.block());

        tag(MFBlockTags.GEMSTONE_FENCES)
                .add(MFBlockItemIds.AZURITE_FENCE.block())
                .add(MFBlockItemIds.FLUORITE_FENCE.block());

        tag(BlockTags.WOODEN_FENCES)
                .add(MFBlockItemIds.BLOODWOOD_FENCE.block())
                .add(MFBlockItemIds.TAINTED_FENCE.block())
                .add(MFBlockItemIds.PALM_FENCE.block())
                .add(MFBlockItemIds.DECREPIT_FENCE.block())
                .add(MFBlockItemIds.PALLID_FENCE.block());

        tag(BlockTags.FENCE_GATES)
                .add(MFBlockItemIds.BLOODWOOD_FENCE_GATE.block())
                .add(MFBlockItemIds.TAINTED_FENCE_GATE.block())
                .add(MFBlockItemIds.PALM_FENCE_GATE.block())
                .add(MFBlockItemIds.DECREPIT_FENCE_GATE.block())
                .add(MFBlockItemIds.PALLID_FENCE_GATE.block());

        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .addTag(MFBlockTags.BLOODWOOD_LOGS)
                .addTag(MFBlockTags.TAINTED_LOGS)
                .addTag(MFBlockTags.PALM_LOGS)
                .addTag(MFBlockTags.DECREPIT_LOGS)
                .addTag(MFBlockTags.PALLID_LOGS);

        tag(MFBlockTags.BLOODWOOD_LOGS)
                .add(MFBlockItemIds.STRIPPED_BLOODWOOD.block())
                .add(MFBlockItemIds.BLOODWOOD.block())
                .add(MFBlockItemIds.STRIPPED_BLOODWOOD_LOG.block())
                .add(MFBlockItemIds.BLOODWOOD_LOG.block());

        tag(MFBlockTags.BLOODWOOD)
                .add(MFBlockItemIds.BLOODWOOD_PLANKS.block())
                .add(MFBlockItemIds.BLOODWOOD_STAIRS.block())
                .add(MFBlockItemIds.BLOODWOOD_SLAB.block())
                .add(MFBlockItemIds.BLOODWOOD_BUTTON.block())
                .add(MFBlockItemIds.BLOODWOOD_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.BLOODWOOD_FENCE.block())
                .add(MFBlockItemIds.BLOODWOOD_FENCE_GATE.block())
                .add(MFBlockItemIds.BLOODWOOD_SHELF.block())
                .add(MFBlockIds.BLOODWOOD_SIGN)
                .add(MFBlockIds.BLOODWOOD_WALL_SIGN)
                .add(MFBlockIds.BLOODWOOD_HANGING_SIGN)
                .add(MFBlockIds.BLOODWOOD_WALL_HANGING_SIGN);

        tag(MFBlockTags.TAINTED_LOGS)
                .add(MFBlockItemIds.STRIPPED_TAINTED_WOOD.block())
                .add(MFBlockItemIds.TAINTED_WOOD.block())
                .add(MFBlockItemIds.STRIPPED_TAINTED_LOG.block())
                .add(MFBlockItemIds.TAINTED_LOG.block());

        tag(MFBlockTags.TAINTED)
                .add(MFBlockItemIds.TAINTED_PLANKS.block())
                .add(MFBlockItemIds.TAINTED_STAIRS.block())
                .add(MFBlockItemIds.TAINTED_SLAB.block())
                .add(MFBlockItemIds.TAINTED_BUTTON.block())
                .add(MFBlockItemIds.TAINTED_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.TAINTED_FENCE.block())
                .add(MFBlockItemIds.TAINTED_FENCE_GATE.block())
                .add(MFBlockItemIds.TAINTED_SHELF.block())
                .add(MFBlockIds.TAINTED_SIGN)
                .add(MFBlockIds.TAINTED_WALL_SIGN)
                .add(MFBlockIds.TAINTED_HANGING_SIGN)
                .add(MFBlockIds.TAINTED_WALL_HANGING_SIGN);

        tag(MFBlockTags.PALM_LOGS)
                .add(MFBlockItemIds.STRIPPED_PALM_LOG.block())
                .add(MFBlockItemIds.PALM_LOG.block())
                .add(MFBlockItemIds.STRIPPED_PALM_WOOD.block())
                .add(MFBlockItemIds.PALM_WOOD.block());

        tag(MFBlockTags.PALM)
                .add(MFBlockItemIds.PALM_PLANKS.block())
                .add(MFBlockItemIds.PALM_STAIRS.block())
                .add(MFBlockItemIds.PALM_SLAB.block())
                .add(MFBlockItemIds.PALM_BUTTON.block())
                .add(MFBlockItemIds.PALM_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.PALM_FENCE.block())
                .add(MFBlockItemIds.PALM_FENCE_GATE.block())
                .add(MFBlockItemIds.PALM_TRAPDOOR.block())
                .add(MFBlockItemIds.PALM_DOOR.block())
                .add(MFBlockItemIds.PALM_SHELF.block())
                .add(MFBlockIds.PALM_SIGN)
                .add(MFBlockIds.PALM_WALL_SIGN)
                .add(MFBlockIds.PALM_HANGING_SIGN)
                .add(MFBlockIds.PALM_WALL_HANGING_SIGN);

        tag(MFBlockTags.DECREPIT_LOGS)
                .add(MFBlockItemIds.STRIPPED_DECREPIT_LOG.block())
                .add(MFBlockItemIds.DECREPIT_LOG.block())
                .add(MFBlockItemIds.STRIPPED_DECREPIT_WOOD.block())
                .add(MFBlockItemIds.DECREPIT_WOOD.block());

        tag(MFBlockTags.DECREPIT)
                .add(MFBlockItemIds.DECREPIT_PLANKS.block())
                .add(MFBlockItemIds.DECREPIT_STAIRS.block())
                .add(MFBlockItemIds.DECREPIT_SLAB.block())
                .add(MFBlockItemIds.DECREPIT_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.DECREPIT_BUTTON.block())
                .add(MFBlockItemIds.DECREPIT_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.DECREPIT_FENCE.block())
                .add(MFBlockItemIds.DECREPIT_FENCE_GATE.block())
                .add(MFBlockItemIds.DECREPIT_SHELF.block())
                .add(MFBlockIds.DECREPIT_SIGN)
                .add(MFBlockIds.DECREPIT_WALL_SIGN)
                .add(MFBlockIds.DECREPIT_HANGING_SIGN)
                .add(MFBlockIds.DECREPIT_WALL_HANGING_SIGN);

        tag(MFBlockTags.PALLID_LOGS)
                .add(MFBlockItemIds.STRIPPED_PALLID_LOG.block())
                .add(MFBlockItemIds.PALLID_LOG.block())
                .add(MFBlockItemIds.STRIPPED_PALLID_WOOD.block())
                .add(MFBlockItemIds.PALLID_WOOD.block());

        tag(MFBlockTags.PALLID)
                .add(MFBlockItemIds.PALLID_PLANKS.block())
                .add(MFBlockItemIds.PALLID_STAIRS.block())
                .add(MFBlockItemIds.PALLID_SLAB.block())
                .add(MFBlockItemIds.PALLID_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.PALLID_BUTTON.block())
                .add(MFBlockItemIds.PALLID_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.PALLID_FENCE.block())
                .add(MFBlockItemIds.PALLID_FENCE_GATE.block())
                .add(MFBlockItemIds.PALLID_SHELF.block())
                .add(MFBlockIds.PALLID_SIGN)
                .add(MFBlockIds.PALLID_WALL_SIGN)
                .add(MFBlockIds.PALLID_HANGING_SIGN)
                .add(MFBlockIds.PALLID_WALL_HANGING_SIGN);

        tag(MFBlockTags.IS_MODDED_WOOD) // This tag allows me to easily distinguish vanilla vs modded wood types in code
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

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
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

        tag(BlockTags.LEAVES)
                .add(MFBlocks.TAINTED_LEAVES.getKey())
                .add(MFBlocks.BLOODWOOD_LEAVES.getKey())
                .add(MFBlocks.PALM_LEAVES.getKey())
                .add(MFBlocks.DECREPIT_LEAVES.getKey());

        tag(BlockTags.PLANKS)
                .add(MFBlocks.TAINTED_PLANKS.getKey())
                .add(MFBlocks.BLOODWOOD_PLANKS.getKey())
                .add(MFBlocks.PALM_PLANKS.getKey())
                .add(MFBlocks.DECREPIT_PLANKS.getKey())
                .add(MFBlocks.PALLID_PLANKS.getKey());

        tag(BlockTags.STAIRS)
                .add(MFBlocks.TAINTED_STAIRS.getKey())
                .add(MFBlocks.BLOODWOOD_STAIRS.getKey())
                .add(MFBlocks.PALM_STAIRS.getKey())
                .add(MFBlocks.DECREPIT_STAIRS.getKey())
                .add(MFBlocks.PALLID_STAIRS.getKey());

        tag(BlockTags.SLABS)
                .add(MFBlocks.TAINTED_SLAB.getKey())
                .add(MFBlocks.BLOODWOOD_SLAB.getKey())
                .add(MFBlocks.PALM_SLAB.getKey())
                .add(MFBlocks.DECREPIT_SLAB.getKey())
                .add(MFBlocks.PALLID_SLAB.getKey());

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(MFBlockItemIds.BISMUTH_ORE.block())
                .add(MFBlockItemIds.BISMUTH_BLOCK.block())
                .add(MFBlockItemIds.RAW_BISMUTH_BLOCK.block());

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(MFBlockItemIds.ALUMINIUM_BLOCK.block())
                .add(MFBlockItemIds.MAGNESIUM_BLOCK.block())
                .add(MFBlockItemIds.FLUORITE_BLOCK.block())
                .add(MFBlockItemIds.BISMUTH_BLOCK.block())
                .add(MFBlockItemIds.AZURITE_BLOCK.block());

        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(MFBlockItemIds.BLOODWOOD_LOG.block())
                .add(MFBlockItemIds.TAINTED_LOG.block())
                .add(MFBlockItemIds.PALM_LOG.block());

        tag(BlockTags.LOGS)
                .add(MFBlockItemIds.BLOODWOOD_LOG.block())
                .add(MFBlockItemIds.TAINTED_LOG.block())
                .add(MFBlockItemIds.PALM_LOG.block())
                .add(MFBlockItemIds.DECREPIT_LOG.block())
                .add(MFBlockItemIds.PALLID_LOG.block());

        tag(BlockTags.PRESSURE_PLATES)
                .add(MFBlockItemIds.AZURITE_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.BLOODWOOD_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.TAINTED_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.PALM_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.DECREPIT_PRESSURE_PLATE.block())
                .add(MFBlockItemIds.PALLID_PRESSURE_PLATE.block());

        tag(BlockTags.BUTTONS)
                .add(MFBlockItemIds.AZURITE_BUTTON.block())
                .add(MFBlockItemIds.FLUORITE_BUTTON.block())
                .add(MFBlockItemIds.BLOODWOOD_BUTTON.block())
                .add(MFBlockItemIds.TAINTED_BUTTON.block())
                .add(MFBlockItemIds.PALM_BUTTON.block())
                .add(MFBlockItemIds.DECREPIT_BUTTON.block())
                .add(MFBlockItemIds.PALLID_BUTTON.block());

        tag(BlockTags.WALLS)
                .add(MFBlockItemIds.AZURITE_WALL.block())
                .add(MFBlockItemIds.FLUORITE_WALL.block());

        tag(BlockTags.FLOWER_POTS)
                .add(MFBlockIds.POTTED_ROSE)
                .add(MFBlockIds.POTTED_BLUE_ROSE)
                .add(MFBlockIds.POTTED_BLOODWOOD_SAPLING)
                .add(MFBlockIds.POTTED_TAINTED_SAPLING)
                .add(MFBlockIds.POTTED_PALM_SAPLING)
                .add(MFBlockIds.POTTED_DECREPIT_SAPLING)
                .add(MFBlockIds.POTTED_PALLID_SAPLING);

        tag(MFBlockTags.VERTICAL_SLABS)
                .addTag(MFBlockTags.AXE_MINEABLE_VERTICAL_SLABS)
                .addTag(MFBlockTags.PICKAXE_MINEABLE_VERTICAL_SLABS)
                .addTag(MFBlockTags.WOOL_VERTICAL_SLABS);

        tag(MFBlockTags.AXE_MINEABLE_VERTICAL_SLABS)
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
                .add(MFBlockItemIds.DECREPIT_VERTICAL_SLAB.block())
                .add(MFBlockItemIds.PALLID_VERTICAL_SLAB.block());

        tag(MFBlockTags.PICKAXE_MINEABLE_VERTICAL_SLABS)
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

        tag(BlockTags.FLOWERS)
                .add(MFBlockItemIds.ROSE.block())
                .add(MFBlockItemIds.BLUE_ROSE.block());

        tag(BlockTags.WOODEN_SHELVES)
                .add(MFBlockItemIds.BLOODWOOD_SHELF.block())
                .add(MFBlockItemIds.TAINTED_SHELF.block())
                .add(MFBlockItemIds.PALM_SHELF.block())
                .add(MFBlockItemIds.DECREPIT_SHELF.block())
                .add(MFBlockItemIds.PALLID_SHELF.block());

        tag(MFBlockTags.GEMSTONE_SHELVES)
                .add(MFBlockItemIds.AZURITE_SHELF.block())
                .add(MFBlockItemIds.FLUORITE_SHELF.block());

        tag(BlockTags.SPELEOTHEMS)
                .add(MFBlockItemIds.ICICLE.block());

        tag(MFBlockTags.METAL_DETECTOR_FINDABLE)
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

        tag(MFBlockTags.METAL_DETECTOR_LOW_COST)
                .add(MFBlockItemIds.MAGNESIUM_ORE.block())
                .add(MFBlockItemIds.DEEPSLATE_MAGNESIUM_ORE.block())
                .add(BlockItemIds.IRON_ORE.block())
                .add(BlockItemIds.DEEPSLATE_IRON_ORE.block());

        tag(MFBlockTags.METAL_DETECTOR_MEDIUM_COST)
                .add(BlockItemIds.GOLD_ORE.block())
                .add(BlockItemIds.NETHER_GOLD_ORE.block())
                .add(BlockItemIds.DEEPSLATE_GOLD_ORE.block());

        tag(MFBlockTags.METAL_DETECTOR_HIGH_COST)
                .add(BlockItemIds.ANCIENT_DEBRIS.block());

        tag(MFBlockTags.METAL_DETECTOR_BISMUTH_COST)
                .add(MFBlockItemIds.BISMUTH_ORE.block());
    }
}
