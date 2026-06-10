package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.tag.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ALUMINIUM_BLOCK.get())
                .add(ModBlocks.ALUMINIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get())
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK.get())
                .add(ModBlocks.RAW_MAGNESIUM_BLOCK.get())
                .add(ModBlocks.MAGNESIUM_BLOCK.get())
                .add(ModBlocks.MAGNESIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get())
                .add(ModBlocks.AZURITE_BLOCK.get())
                .add(ModBlocks.FLUORITE_BLOCK.get())
                .add(ModBlocks.RAW_AZURITE_BLOCK.get())
                .add(ModBlocks.RAW_FLUORITE_BLOCK.get())
                .add(ModBlocks.NETHER_FLUORITE_ORE.get())
                .add(ModBlocks.NETHER_AZURITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_AZURITE_ORE.get())
                .add(ModBlocks.DEEPSLATE_FLUORITE_ORE.get())
                .add(ModBlocks.END_FLUORITE_ORE.get())
                .add(ModBlocks.END_AZURITE_ORE.get())
                .add(ModBlocks.FLUORITE_ORE.get())
                .add(ModBlocks.AZURITE_ORE.get())
                .add(ModBlocks.MAGIC_BLOCK.get())
                .add(ModBlocks.BISMUTH_BLOCK.get())
                .add(ModBlocks.RAW_BISMUTH_BLOCK.get())
                .add(ModBlocks.AZURITE_STAIRS.get())
                .add(ModBlocks.AZURITE_SLAB.get())
                .add(ModBlocks.AZURITE_BUTTON.get())
                .add(ModBlocks.AZURITE_PRESSURE_PLATE.get())
                .add(ModBlocks.FLUORITE_STAIRS.get())
                .add(ModBlocks.FLUORITE_SLAB.get())
                .add(ModBlocks.COMPRESSOR_BLOCK.get())
                .add(ModBlocks.EVERFROST_PACKED_ICE_ORE.get())
                .add(ModBlocks.EVERFROST_BLUE_ICE_ORE.get())
                .add(ModBlocks.BISMUTH_ORE.get())
                .add(ModBlocks.DECREPIT_NULLIUM.get())
                .add(ModBlocks.PALLID_NULLIUM.get());

        tag(ModBlockTags.COMPRESSOR_FLUIDS)
                .add(Blocks.WATER);

        tag(ModBlockTags.NULLIUM_BLOCKS)
                .add(ModBlocks.DECREPIT_NULLIUM.get())
                .add(ModBlocks.PALLID_NULLIUM.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(ModBlockTags.IS_MODDED_WOOD);

        tag(BlockTags.FENCES)
                .add(ModBlocks.PALM_FENCE.get())
                .add(ModBlocks.DECREPIT_FENCE.get())
                .add(ModBlocks.PALLID_FENCE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.PALM_FENCE_GATE.get())
                .add(ModBlocks.DECREPIT_FENCE_GATE.get())
                .add(ModBlocks.PALLID_FENCE_GATE.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(ModBlockTags.BLOODWOOD_LOGS)
                .addTag(ModBlockTags.TAINTED_LOGS)
                .addTag(ModBlockTags.PALM_LOGS)
                .addTag(ModBlockTags.DECREPIT_LOGS)
                .addTag(ModBlockTags.PALLID_LOGS);

        tag(ModBlockTags.BLOODWOOD_LOGS)
                .add(ModBlocks.STRIPPED_BLOODWOOD.get())
                .add(ModBlocks.BLOODWOOD.get())
                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                .add(ModBlocks.BLOODWOOD_LOG.get());

        tag(ModBlockTags.BLOODWOOD)
                .add(ModBlocks.BLOODWOOD_PLANKS.get())
                .add(ModBlocks.BLOODWOOD_STAIRS.get())
                .add(ModBlocks.BLOODWOOD_SLAB.get())
                .add(ModBlocks.BLOODWOOD_HANGING_SIGN.get())
                .add(ModBlocks.BLOODWOOD_WALL_HANGING_SIGN.get());

        tag(ModBlockTags.TAINTED_LOGS)
                .add(ModBlocks.STRIPPED_TAINTED_WOOD.get())
                .add(ModBlocks.TAINTED_WOOD.get())
                .add(ModBlocks.STRIPPED_TAINTED_LOG.get())
                .add(ModBlocks.TAINTED_LOG.get());

        tag(ModBlockTags.TAINTED)
                .add(ModBlocks.TAINTED_PLANKS.get())
                .add(ModBlocks.TAINTED_STAIRS.get())
                .add(ModBlocks.TAINTED_SLAB.get())
                .add(ModBlocks.TAINTED_HANGING_SIGN.get())
                .add(ModBlocks.TAINTED_WALL_HANGING_SIGN.get());

        tag(ModBlockTags.PALM_LOGS)
                .add(ModBlocks.STRIPPED_PALM_LOG.get())
                .add(ModBlocks.PALM_LOG.get())
                .add(ModBlocks.STRIPPED_PALM_WOOD.get())
                .add(ModBlocks.PALM_WOOD.get());

        tag(ModBlockTags.PALM)
                .add(ModBlocks.PALM_PLANKS.get())
                .add(ModBlocks.PALM_STAIRS.get())
                .add(ModBlocks.PALM_SLAB.get())
                .add(ModBlocks.PALM_BUTTON.get())
                .add(ModBlocks.PALM_PRESSURE_PLATE.get())
                .add(ModBlocks.PALM_FENCE.get())
                .add(ModBlocks.PALM_FENCE_GATE.get())
                .add(ModBlocks.PALM_TRAPDOOR.get())
                .add(ModBlocks.PALM_DOOR.get())
                .add(ModBlocks.PALM_SHELF.get())
                .add(ModBlocks.PALM_SIGN.get())
                .add(ModBlocks.PALM_WALL_SIGN.get())
                .add(ModBlocks.PALM_HANGING_SIGN.get())
                .add(ModBlocks.PALM_WALL_HANGING_SIGN.get());

        tag(ModBlockTags.DECREPIT_LOGS)
                .add(ModBlocks.STRIPPED_DECREPIT_LOG.get())
                .add(ModBlocks.DECREPIT_LOG.get())
                .add(ModBlocks.STRIPPED_DECREPIT_WOOD.get())
                .add(ModBlocks.DECREPIT_WOOD.get());

        tag(ModBlockTags.DECREPIT)
                .add(ModBlocks.DECREPIT_PLANKS.get())
                .add(ModBlocks.DECREPIT_STAIRS.get())
                .add(ModBlocks.DECREPIT_SLAB.get())
                .add(ModBlocks.DECREPIT_BUTTON.get())
                .add(ModBlocks.DECREPIT_PRESSURE_PLATE.get())
                .add(ModBlocks.DECREPIT_FENCE.get())
                .add(ModBlocks.DECREPIT_FENCE_GATE.get());

        tag(ModBlockTags.PALLID_LOGS)
                .add(ModBlocks.STRIPPED_PALLID_LOG.get())
                .add(ModBlocks.PALLID_LOG.get())
                .add(ModBlocks.STRIPPED_PALLID_WOOD.get())
                .add(ModBlocks.PALLID_WOOD.get());

        tag(ModBlockTags.PALLID)
                .add(ModBlocks.PALLID_PLANKS.get())
                .add(ModBlocks.PALLID_STAIRS.get())
                .add(ModBlocks.PALLID_SLAB.get())
                .add(ModBlocks.PALLID_BUTTON.get())
                .add(ModBlocks.PALLID_PRESSURE_PLATE.get())
                .add(ModBlocks.PALLID_FENCE.get())
                .add(ModBlocks.PALLID_FENCE_GATE.get());

        tag(ModBlockTags.IS_MODDED_WOOD)
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
                .add(ModBlocks.ALUMINIUM_BLOCK.get())
                .add(ModBlocks.ALUMINIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get())
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK.get())
                .add(ModBlocks.RAW_MAGNESIUM_BLOCK.get())
                .add(ModBlocks.MAGNESIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get())
                .add(ModBlocks.COMPRESSOR_BLOCK.get())
                .add(ModBlocks.EVERFROST_PACKED_ICE_ORE.get())
                .add(ModBlocks.EVERFROST_BLUE_ICE_ORE.get())
                .add(ModBlocks.MAGNESIUM_BLOCK.get());

        tag(BlockTags.LEAVES)
                .add(ModBlocks.TAINTED_LEAVES.get())
                .add(ModBlocks.BLOODWOOD_LEAVES.get())
                .add(ModBlocks.PALM_LEAVES.get())
                .add(ModBlocks.DECREPIT_LEAVES.get());

        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.TAINTED_SAPLING.get())
                .add(ModBlocks.BLOODWOOD_SAPLING.get())
                .add(ModBlocks.PALM_SAPLING.get())
                .add(ModBlocks.DECREPIT_SAPLING.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.TAINTED_PLANKS.get())
                .add(ModBlocks.BLOODWOOD_PLANKS.get())
                .add(ModBlocks.PALM_PLANKS.get())
                .add(ModBlocks.DECREPIT_PLANKS.get())
                .add(ModBlocks.PALLID_PLANKS.get());

        tag(BlockTags.STAIRS)
                .add(ModBlocks.TAINTED_STAIRS.get())
                .add(ModBlocks.BLOODWOOD_STAIRS.get())
                .add(ModBlocks.PALM_STAIRS.get())
                .add(ModBlocks.DECREPIT_STAIRS.get())
                .add(ModBlocks.PALLID_STAIRS.get());

        tag(BlockTags.SLABS)
                .add(ModBlocks.TAINTED_SLAB.get())
                .add(ModBlocks.BLOODWOOD_SLAB.get())
                .add(ModBlocks.PALM_SLAB.get())
                .add(ModBlocks.DECREPIT_SLAB.get())
                .add(ModBlocks.PALLID_SLAB.get());

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.BISMUTH_ORE.get())
                .add(ModBlocks.AZURITE_ORE.get())
                .add(ModBlocks.RAW_AZURITE_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_AZURITE_ORE.get())
                .add(ModBlocks.NETHER_AZURITE_ORE.get())
                .add(ModBlocks.END_AZURITE_ORE.get())
                .add(ModBlocks.AZURITE_BLOCK.get())
                .add(ModBlocks.FLUORITE_ORE.get())
                .add(ModBlocks.END_FLUORITE_ORE.get())
                .add(ModBlocks.NETHER_FLUORITE_ORE.get())
                .add(ModBlocks.RAW_FLUORITE_BLOCK.get())
                .add(ModBlocks.FLUORITE_BLOCK.get())
                .add(ModBlocks.BISMUTH_BLOCK.get())
                .add(ModBlocks.RAW_BISMUTH_BLOCK.get())
                .add(ModBlocks.AZURITE_BUTTON.get())
                .add(ModBlocks.AZURITE_PRESSURE_PLATE.get())
                .add(ModBlocks.DEEPSLATE_FLUORITE_ORE.get());


        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(Blocks.COPPER_BLOCK)
                .add(Blocks.WAXED_COPPER_BLOCK)
                .add(Blocks.EXPOSED_COPPER)
                .add(Blocks.WAXED_EXPOSED_COPPER)
                .add(Blocks.WEATHERED_COPPER)
                .add(Blocks.WAXED_WEATHERED_COPPER)
                .add(Blocks.OXIDIZED_COPPER)
                .add(Blocks.WAXED_OXIDIZED_COPPER)
                .add(ModBlocks.ALUMINIUM_BLOCK.get())
                .add(ModBlocks.MAGNESIUM_BLOCK.get())
                .add(ModBlocks.FLUORITE_BLOCK.get())
                .add(ModBlocks.BISMUTH_BLOCK.get())
                .add(ModBlocks.AZURITE_BLOCK.get());

        tag(BlockTags.PRESSURE_PLATES)
                .add(ModBlocks.AZURITE_PRESSURE_PLATE.get())
                .add(ModBlocks.PALM_PRESSURE_PLATE.get())
                .add(ModBlocks.DECREPIT_PRESSURE_PLATE.get());

        tag(BlockTags.BUTTONS)
                .add(ModBlocks.AZURITE_BUTTON.get())
                .add(ModBlocks.PALM_BUTTON.get())
                .add(ModBlocks.DECREPIT_BUTTON.get())
                .add(ModBlocks.PALLID_BUTTON.get());

        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_ROSE.get())
                .add(ModBlocks.POTTED_BLUE_ROSE.get())
                .add(ModBlocks.POTTED_TAINTED_SAPLING.get())
                .add(ModBlocks.POTTED_BLOODWOOD_SAPLING.get());

        tag(BlockTags.FLOWERS)
                .add(ModBlocks.ROSE.get())
                .add(ModBlocks.BLUE_ROSE.get());
    }
}
