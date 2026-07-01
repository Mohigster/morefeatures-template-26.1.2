package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.tag.ModBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.references.BlockIds;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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
                .add(ModBlocks.VOID_ANCHOR.getKey());

        tag(ModBlockTags.COMPRESSOR_FLUIDS)
                .add(BlockIds.WATER);

        tag(ModBlockTags.NULLIUM_BLOCKS)
                .add(ModBlocks.DECREPIT_NULLIUM.getKey())
                .add(ModBlocks.PALLID_NULLIUM.getKey());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(ModBlockTags.IS_MODDED_WOOD);

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
                .add(ModBlocks.DECREPIT_SIGN.getKey())
                .add(ModBlocks.DECREPIT_WALL_SIGN.getKey())
                .add(ModBlocks.DECREPIT_HANGING_SIGN.getKey())
                .add(ModBlocks.DECREPIT_WALL_HANGING_SIGN.getKey());

        tag(ModBlockTags.PALLID_LOGS)
                .add(ModBlocks.STRIPPED_PALLID_LOG.getKey())
                .add(ModBlocks.PALLID_LOG.getKey())
                .add(ModBlocks.STRIPPED_PALLID_WOOD.getKey())
                .add(ModBlocks.PALLID_WOOD.getKey());

        tag(ModBlockTags.PALLID)
                .add(ModBlocks.PALLID_PLANKS.getKey())
                .add(ModBlocks.PALLID_STAIRS.getKey())
                .add(ModBlocks.PALLID_SLAB.getKey())
                .add(ModBlocks.PALLID_BUTTON.getKey())
                .add(ModBlocks.PALLID_PRESSURE_PLATE.getKey())
                .add(ModBlocks.PALLID_FENCE.getKey())
                .add(ModBlocks.PALLID_FENCE_GATE.getKey());

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
                .add(ModBlocks.AZURITE_PRESSURE_PLATE.getKey())
                .add(ModBlocks.PALM_PRESSURE_PLATE.getKey())
                .add(ModBlocks.DECREPIT_PRESSURE_PLATE.getKey());

        tag(BlockTags.BUTTONS)
                .add(ModBlocks.AZURITE_BUTTON.getKey())
                .add(ModBlocks.PALM_BUTTON.getKey())
                .add(ModBlocks.DECREPIT_BUTTON.getKey())
                .add(ModBlocks.PALLID_BUTTON.getKey());

        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_ROSE.getKey())
                .add(ModBlocks.POTTED_BLUE_ROSE.getKey())
                .add(ModBlocks.POTTED_TAINTED_SAPLING.getKey())
                .add(ModBlocks.POTTED_BLOODWOOD_SAPLING.getKey());

        tag(BlockTags.FLOWERS)
                .add(ModBlocks.ROSE.getKey())
                .add(ModBlocks.BLUE_ROSE.getKey());

        tag(BlockTags.SPELEOTHEMS)
                .add(ModBlocks.ICICLE.getKey());
    }
}
