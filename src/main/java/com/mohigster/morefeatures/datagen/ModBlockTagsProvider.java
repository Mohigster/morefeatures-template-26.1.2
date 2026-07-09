package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.block.references.ModBlockItemIds;
import com.mohigster.morefeatures.tag.ModBlockItemTags;
import com.mohigster.morefeatures.tag.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BlockItemTagAppender;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.references.BlockIds;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.block.WeatheringCopperCollection;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import javax.lang.model.element.Element;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        /*
         * This provider generates tags that allow me to apply every
         * copper or wool vertical slab to any block tag all at once,
         * without having to add each individual block in the collection
         */
        new ModBlockItemTagsProvider(tagId -> BlockItemTagsProvider.wrapForBlocks(this.tag(tagId.block()))).run();

        tag(BlockTags.SHEARS_MAJOR_BREAKING_SPEED)
                .addTag(ModBlockTags.WOOL_VERTICAL_SLABS);

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ALUMINIUM_BLOCK.getKey())
                .add(ModBlocks.ALUMINIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.getKey())
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK.getKey())
                .add(ModBlocks.RAW_MAGNESIUM_BLOCK.getKey())
                .add(ModBlocks.MAGNESIUM_BLOCK.getKey())
                .add(ModBlocks.MAGNESIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.getKey())
                .add(ModBlocks.AZURITE_BLOCK.getKey())
                .add(ModBlocks.FLUORITE_BLOCK.getKey())
                .add(ModBlocks.RAW_AZURITE_BLOCK.getKey())
                .add(ModBlocks.RAW_FLUORITE_BLOCK.getKey())
                .add(ModBlocks.NETHER_FLUORITE_ORE.getKey())
                .add(ModBlocks.NETHER_AZURITE_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_AZURITE_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_FLUORITE_ORE.getKey())
                .add(ModBlocks.END_FLUORITE_ORE.getKey())
                .add(ModBlocks.END_AZURITE_ORE.getKey())
                .add(ModBlocks.FLUORITE_ORE.getKey())
                .add(ModBlocks.AZURITE_ORE.getKey())
                .add(ModBlocks.MAGIC_BLOCK.getKey())
                .add(ModBlocks.BISMUTH_BLOCK.getKey())
                .add(ModBlocks.RAW_BISMUTH_BLOCK.getKey())
                .add(ModBlocks.AZURITE_STAIRS.getKey())
                .add(ModBlocks.AZURITE_SLAB.getKey())
                .add(ModBlocks.AZURITE_BUTTON.getKey())
                .add(ModBlocks.AZURITE_PRESSURE_PLATE.getKey())
                .add(ModBlocks.FLUORITE_STAIRS.getKey())
                .add(ModBlocks.FLUORITE_SLAB.getKey())
                .add(ModBlocks.COMPRESSOR_BLOCK.getKey())
                .add(ModBlocks.EVERFROST_PACKED_ICE_ORE.getKey())
                .add(ModBlocks.EVERFROST_BLUE_ICE_ORE.getKey())
                .add(ModBlocks.BISMUTH_ORE.getKey())
                .add(ModBlocks.DECREPIT_NULLIUM.getKey())
                .add(ModBlocks.PALLID_NULLIUM.getKey())
                .add(ModBlocks.VOID_ANCHOR.getKey())
                .addTag(ModBlockTags.PICKAXE_MINEABLE_VERTICAL_SLABS);

        tag(ModBlockTags.COMPRESSOR_FLUIDS)
                .add(BlockIds.WATER);

        tag(ModBlockTags.NULLIUM_BLOCKS)
                .add(ModBlocks.DECREPIT_NULLIUM.getKey())
                .add(ModBlocks.PALLID_NULLIUM.getKey());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(ModBlockTags.IS_MODDED_WOOD)
                .addTag(ModBlockTags.AXE_MINEABLE_VERTICAL_SLABS);

        tag(BlockTags.FENCES)
                .add(ModBlocks.BLOODWOOD_FENCE.getKey())
                .add(ModBlocks.TAINTED_FENCE.getKey())
                .add(ModBlocks.PALM_FENCE.getKey())
                .add(ModBlocks.DECREPIT_FENCE.getKey())
                .add(ModBlocks.PALLID_FENCE.getKey());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.BLOODWOOD_FENCE_GATE.getKey())
                .add(ModBlocks.TAINTED_FENCE_GATE.getKey())
                .add(ModBlocks.PALM_FENCE_GATE.getKey())
                .add(ModBlocks.DECREPIT_FENCE_GATE.getKey())
                .add(ModBlocks.PALLID_FENCE_GATE.getKey());

        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .addTag(ModBlockTags.BLOODWOOD_LOGS)
                .addTag(ModBlockTags.TAINTED_LOGS)
                .addTag(ModBlockTags.PALM_LOGS)
                .addTag(ModBlockTags.DECREPIT_LOGS)
                .addTag(ModBlockTags.PALLID_LOGS);

        tag(ModBlockTags.BLOODWOOD_LOGS)
                .add(ModBlocks.STRIPPED_BLOODWOOD.getKey())
                .add(ModBlocks.BLOODWOOD.getKey())
                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.getKey())
                .add(ModBlocks.BLOODWOOD_LOG.getKey());

        tag(ModBlockTags.BLOODWOOD)
                .add(ModBlocks.BLOODWOOD_PLANKS.getKey())
                .add(ModBlocks.BLOODWOOD_STAIRS.getKey())
                .add(ModBlocks.BLOODWOOD_SLAB.getKey())
                .add(ModBlocks.BLOODWOOD_BUTTON.getKey())
                .add(ModBlocks.BLOODWOOD_PRESSURE_PLATE.getKey())
                .add(ModBlocks.BLOODWOOD_FENCE.getKey())
                .add(ModBlocks.BLOODWOOD_FENCE_GATE.getKey())
                .add(ModBlocks.BLOODWOOD_SIGN.getKey())
                .add(ModBlocks.BLOODWOOD_WALL_SIGN.getKey())
                .add(ModBlocks.BLOODWOOD_HANGING_SIGN.getKey())
                .add(ModBlocks.BLOODWOOD_WALL_HANGING_SIGN.getKey());

        tag(ModBlockTags.TAINTED_LOGS)
                .add(ModBlocks.STRIPPED_TAINTED_WOOD.getKey())
                .add(ModBlocks.TAINTED_WOOD.getKey())
                .add(ModBlocks.STRIPPED_TAINTED_LOG.getKey())
                .add(ModBlocks.TAINTED_LOG.getKey());

        tag(ModBlockTags.TAINTED)
                .add(ModBlocks.TAINTED_PLANKS.getKey())
                .add(ModBlocks.TAINTED_STAIRS.getKey())
                .add(ModBlocks.TAINTED_SLAB.getKey())
                .add(ModBlocks.TAINTED_BUTTON.getKey())
                .add(ModBlocks.TAINTED_PRESSURE_PLATE.getKey())
                .add(ModBlocks.TAINTED_FENCE.getKey())
                .add(ModBlocks.TAINTED_FENCE_GATE.getKey())
                .add(ModBlocks.TAINTED_SIGN.getKey())
                .add(ModBlocks.TAINTED_WALL_SIGN.getKey())
                .add(ModBlocks.TAINTED_HANGING_SIGN.getKey())
                .add(ModBlocks.TAINTED_WALL_HANGING_SIGN.getKey());

        tag(ModBlockTags.PALM_LOGS)
                .add(ModBlocks.STRIPPED_PALM_LOG.getKey())
                .add(ModBlocks.PALM_LOG.getKey())
                .add(ModBlocks.STRIPPED_PALM_WOOD.getKey())
                .add(ModBlocks.PALM_WOOD.getKey());

        tag(ModBlockTags.PALM)
                .add(ModBlocks.PALM_PLANKS.getKey())
                .add(ModBlocks.PALM_STAIRS.getKey())
                .add(ModBlocks.PALM_SLAB.getKey())
                .add(ModBlocks.PALM_BUTTON.getKey())
                .add(ModBlocks.PALM_PRESSURE_PLATE.getKey())
                .add(ModBlocks.PALM_FENCE.getKey())
                .add(ModBlocks.PALM_FENCE_GATE.getKey())
                .add(ModBlocks.PALM_TRAPDOOR.getKey())
                .add(ModBlocks.PALM_DOOR.getKey())
                .add(ModBlocks.PALM_SHELF.getKey())
                .add(ModBlocks.PALM_SIGN.getKey())
                .add(ModBlocks.PALM_WALL_SIGN.getKey())
                .add(ModBlocks.PALM_HANGING_SIGN.getKey())
                .add(ModBlocks.PALM_WALL_HANGING_SIGN.getKey());

        tag(ModBlockTags.DECREPIT_LOGS)
                .add(ModBlocks.STRIPPED_DECREPIT_LOG.getKey())
                .add(ModBlocks.DECREPIT_LOG.getKey())
                .add(ModBlocks.STRIPPED_DECREPIT_WOOD.getKey())
                .add(ModBlocks.DECREPIT_WOOD.getKey());

        tag(ModBlockTags.DECREPIT)
                .add(ModBlocks.DECREPIT_PLANKS.getKey())
                .add(ModBlocks.DECREPIT_STAIRS.getKey())
                .add(ModBlocks.DECREPIT_SLAB.getKey())
                .add(ModBlocks.DECREPIT_BUTTON.getKey())
                .add(ModBlocks.DECREPIT_PRESSURE_PLATE.getKey())
                .add(ModBlocks.DECREPIT_FENCE.getKey())
                .add(ModBlocks.DECREPIT_FENCE_GATE.getKey())
                .add(ModBlocks.DECREPIT_SHELF.getKey())
                .add(ModBlocks.DECREPIT_SIGN.getKey())
                .add(ModBlocks.DECREPIT_WALL_SIGN.getKey())
                .add(ModBlocks.DECREPIT_HANGING_SIGN.getKey())
                .add(ModBlocks.DECREPIT_WALL_HANGING_SIGN.getKey());

        tag(ModBlockTags.PALLID_LOGS)
                .add(ModBlockItemIds.STRIPPED_PALLID_LOG.block())
                .add(ModBlockItemIds.PALLID_LOG.block())
                .add(ModBlockItemIds.STRIPPED_PALLID_WOOD.block())
                .add(ModBlockItemIds.PALLID_WOOD.block());

        tag(ModBlockTags.PALLID)
                .add(ModBlockItemIds.PALLID_PLANKS.block())
                .add(ModBlockItemIds.PALLID_STAIRS.block())
                .add(ModBlockItemIds.PALLID_SLAB.block())
                .add(ModBlockItemIds.PALLID_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.PALLID_BUTTON.block())
                .add(ModBlockItemIds.PALLID_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.PALLID_FENCE.block())
                .add(ModBlockItemIds.PALLID_FENCE_GATE.block())
                .add(ModBlockItemIds.PALLID_SHELF.block())
                .add(ModBlocks.PALLID_SIGN.getKey())
                .add(ModBlocks.PALLID_WALL_SIGN.getKey())
                .add(ModBlocks.PALLID_HANGING_SIGN.getKey())
                .add(ModBlocks.PALLID_WALL_HANGING_SIGN.getKey());

        tag(ModBlockTags.IS_MODDED_WOOD) // This tag allows me to easily distinguish vanilla vs modded wood types in code
                .addTag(ModBlockTags.BLOODWOOD_LOGS)
                .addTag(ModBlockTags.BLOODWOOD)
                .addTag(ModBlockTags.TAINTED_LOGS)
                .addTag(ModBlockTags.TAINTED)
                .addTag(ModBlockTags.PALM_LOGS)
                .addTag(ModBlockTags.PALM)
                .addTag(ModBlockTags.DECREPIT_LOGS)
                .addTag(ModBlockTags.DECREPIT)
                .addTag(ModBlockTags.PALLID_LOGS)
                .addTag(ModBlockTags.PALLID);

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ALUMINIUM_BLOCK.getKey())
                .add(ModBlocks.ALUMINIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.getKey())
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK.getKey())
                .add(ModBlocks.RAW_MAGNESIUM_BLOCK.getKey())
                .add(ModBlocks.MAGNESIUM_ORE.getKey())
                .add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.getKey())
                .add(ModBlocks.COMPRESSOR_BLOCK.getKey())
                .add(ModBlocks.EVERFROST_PACKED_ICE_ORE.getKey())
                .add(ModBlocks.EVERFROST_BLUE_ICE_ORE.getKey())
                .add(ModBlocks.MAGNESIUM_BLOCK.getKey())
                .add(ModBlocks.VOID_ANCHOR.getKey());

        tag(BlockTags.LEAVES)
                .add(ModBlocks.TAINTED_LEAVES.getKey())
                .add(ModBlocks.BLOODWOOD_LEAVES.getKey())
                .add(ModBlocks.PALM_LEAVES.getKey())
                .add(ModBlocks.DECREPIT_LEAVES.getKey());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.TAINTED_PLANKS.getKey())
                .add(ModBlocks.BLOODWOOD_PLANKS.getKey())
                .add(ModBlocks.PALM_PLANKS.getKey())
                .add(ModBlocks.DECREPIT_PLANKS.getKey())
                .add(ModBlocks.PALLID_PLANKS.getKey());

        tag(BlockTags.STAIRS)
                .add(ModBlocks.TAINTED_STAIRS.getKey())
                .add(ModBlocks.BLOODWOOD_STAIRS.getKey())
                .add(ModBlocks.PALM_STAIRS.getKey())
                .add(ModBlocks.DECREPIT_STAIRS.getKey())
                .add(ModBlocks.PALLID_STAIRS.getKey());

        tag(BlockTags.SLABS)
                .add(ModBlocks.TAINTED_SLAB.getKey())
                .add(ModBlocks.BLOODWOOD_SLAB.getKey())
                .add(ModBlocks.PALM_SLAB.getKey())
                .add(ModBlocks.DECREPIT_SLAB.getKey())
                .add(ModBlocks.PALLID_SLAB.getKey());

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.BISMUTH_ORE.getKey())
                .add(ModBlocks.AZURITE_ORE.getKey())
                .add(ModBlocks.RAW_AZURITE_BLOCK.getKey())
                .add(ModBlocks.DEEPSLATE_AZURITE_ORE.getKey())
                .add(ModBlocks.NETHER_AZURITE_ORE.getKey())
                .add(ModBlocks.END_AZURITE_ORE.getKey())
                .add(ModBlocks.AZURITE_BLOCK.getKey())
                .add(ModBlocks.FLUORITE_ORE.getKey())
                .add(ModBlocks.END_FLUORITE_ORE.getKey())
                .add(ModBlocks.NETHER_FLUORITE_ORE.getKey())
                .add(ModBlocks.RAW_FLUORITE_BLOCK.getKey())
                .add(ModBlocks.FLUORITE_BLOCK.getKey())
                .add(ModBlocks.BISMUTH_BLOCK.getKey())
                .add(ModBlocks.RAW_BISMUTH_BLOCK.getKey())
                .add(ModBlocks.AZURITE_BUTTON.getKey())
                .add(ModBlocks.AZURITE_PRESSURE_PLATE.getKey())
                .add(ModBlocks.DEEPSLATE_FLUORITE_ORE.getKey());

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.ALUMINIUM_BLOCK.getKey())
                .add(ModBlocks.MAGNESIUM_BLOCK.getKey())
                .add(ModBlocks.FLUORITE_BLOCK.getKey())
                .add(ModBlocks.BISMUTH_BLOCK.getKey())
                .add(ModBlocks.AZURITE_BLOCK.getKey());

        tag(BlockTags.LOGS)
                .add(ModBlocks.BLOODWOOD_LOG.getKey())
                .add(ModBlocks.TAINTED_LOG.getKey())
                .add(ModBlocks.PALM_LOG.getKey())
                .add(ModBlocks.DECREPIT_LOG.getKey())
                .add(ModBlocks.PALLID_LOG.getKey());

        tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlockItemIds.AZURITE_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.BLOODWOOD_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.TAINTED_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.PALM_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.DECREPIT_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.PALLID_PRESSURE_PLATE.block());

        tag(BlockTags.BUTTONS)
                .add(ModBlockItemIds.AZURITE_BUTTON.block())
                .add(ModBlockItemIds.BLOODWOOD_BUTTON.block())
                .add(ModBlockItemIds.TAINTED_BUTTON.block())
                .add(ModBlockItemIds.PALM_BUTTON.block())
                .add(ModBlockItemIds.DECREPIT_BUTTON.block())
                .add(ModBlockItemIds.PALLID_BUTTON.block());

        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_ROSE.getKey())
                .add(ModBlocks.POTTED_BLUE_ROSE.getKey())
                .add(ModBlocks.POTTED_TAINTED_SAPLING.getKey())
                .add(ModBlocks.POTTED_BLOODWOOD_SAPLING.getKey());

        tag(ModBlockTags.VERTICAL_SLABS)
                .addTag(ModBlockTags.AXE_MINEABLE_VERTICAL_SLABS)
                .addTag(ModBlockTags.PICKAXE_MINEABLE_VERTICAL_SLABS)
                .addTag(ModBlockTags.WOOL_VERTICAL_SLABS);

        tag(ModBlockTags.AXE_MINEABLE_VERTICAL_SLABS)
                .add(ModBlockItemIds.OAK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.SPRUCE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.BIRCH_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.JUNGLE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.ACACIA_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.DARK_OAK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.CRIMSON_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.WARPED_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.MANGROVE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.CHERRY_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.BAMBOO_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.BAMBOO_MOSAIC_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.PALE_OAK_VERTICAL_SLAB.block());

        tag(ModBlockTags.PICKAXE_MINEABLE_VERTICAL_SLABS)
                .add(ModBlockItemIds.STONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.COBBLESTONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.MOSSY_COBBLESTONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.SMOOTH_STONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.STONE_BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.MOSSY_STONE_BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.COBBLED_DEEPSLATE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.POLISHED_DEEPSLATE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.DEEPSLATE_BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.DEEPSLATE_TILE_VERTICAL_SLAB.block())

                .add(ModBlockItemIds.GRANITE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.POLISHED_GRANITE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.DIORITE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.POLISHED_DIORITE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.ANDESITE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.POLISHED_ANDESITE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.TUFF_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.POLISHED_TUFF_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.TUFF_BRICK_VERTICAL_SLAB.block())

                .add(ModBlockItemIds.SANDSTONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.CUT_SANDSTONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.SMOOTH_SANDSTONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.RED_SANDSTONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.CUT_RED_SANDSTONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB.block())

                .add(ModBlockItemIds.SULFUR_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.POLISHED_SULFUR_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.SULFUR_BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.CINNABAR_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.POLISHED_CINNABAR_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.CINNABAR_BRICK_VERTICAL_SLAB.block())

                .add(ModBlockItemIds.NETHER_BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.RED_NETHER_BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.BLACKSTONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.POLISHED_BLACKSTONE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.END_STONE_BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.PURPUR_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.QUARTZ_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.SMOOTH_QUARTZ_VERTICAL_SLAB.block())

                .add(ModBlockItemIds.PRISMARINE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.PRISMARINE_BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.DARK_PRISMARINE_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.MUD_BRICK_VERTICAL_SLAB.block())
                .add(ModBlockItemIds.RESIN_BRICK_VERTICAL_SLAB.block())

                .addTag(ModBlockTags.CUT_COPPER_VERTICAL_SLABS);

        tag(BlockTags.FLOWERS)
                .add(ModBlocks.ROSE.getKey())
                .add(ModBlocks.BLUE_ROSE.getKey());

        tag(BlockTags.SPELEOTHEMS)
                .add(ModBlocks.ICICLE.getKey());
    }
}
