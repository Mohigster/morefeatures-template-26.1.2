package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.tag.ModBlockTags;
import com.mohigster.morefeatures.tag.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.minecraft.world.entity.monster.breeze.Breeze;

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
                .add(ModBlocks.BISMUTH_ORE.get());

        tag(ModBlockTags.IS_LIQUID)
                .add(Blocks.WATER);

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.TAINTED_PLANKS.get())
                .add(ModBlocks.STRIPPED_TAINTED_WOOD.get())
                .add(ModBlocks.TAINTED_WOOD.get())
                .add(ModBlocks.STRIPPED_TAINTED_LOG.get())
                .add(ModBlocks.TAINTED_LOG.get())
                .add(ModBlocks.TAINTED_STAIRS.get())
                .add(ModBlocks.TAINTED_SLAB.get())
                .add(ModBlocks.BLOODWOOD_PLANKS.get())
                .add(ModBlocks.STRIPPED_BLOODWOOD.get())
                .add(ModBlocks.BLOODWOOD.get())
                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                .add(ModBlocks.BLOODWOOD_LOG.get())
                .add(ModBlocks.BLOODWOOD_STAIRS.get())
                .add(ModBlocks.BLOODWOOD_SLAB.get())
                .add(ModBlocks.PALM_PLANKS.get())
                .add(ModBlocks.PALM_WOOD.get())
                .add(ModBlocks.PALM_LOG.get())
                .add(ModBlocks.STRIPPED_PALM_LOG.get())
                .add(ModBlocks.STRIPPED_PALM_WOOD.get())
                .add(ModBlocks.PALM_STAIRS.get())
                .add(ModBlocks.PALM_SLAB.get());

        tag(BlockTags.FENCES)
                .add(ModBlocks.PALM_FENCE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.PALM_FENCE_GATE.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.TAINTED_LOG.get())
                .add(ModBlocks.STRIPPED_TAINTED_LOG.get())
                .add(ModBlocks.TAINTED_WOOD.get())
                .add(ModBlocks.STRIPPED_TAINTED_WOOD.get())
                .add(ModBlocks.BLOODWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                .add(ModBlocks.BLOODWOOD.get())
                .add(ModBlocks.STRIPPED_BLOODWOOD.get())
                .add(ModBlocks.PALM_WOOD.get())
                .add(ModBlocks.PALM_LOG.get())
                .add(ModBlocks.STRIPPED_PALM_LOG.get())
                .add(ModBlocks.STRIPPED_PALM_WOOD.get())
                .add(ModBlocks.PALM_PLANKS.get());

        tag(ModBlockTags.BLOODWOOD_LOGS)
                .add(ModBlocks.STRIPPED_BLOODWOOD.get())
                .add(ModBlocks.BLOODWOOD.get())
                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                .add(ModBlocks.BLOODWOOD_LOG.get());

        tag(ModBlockTags.TAINTED_LOGS)
                .add(ModBlocks.STRIPPED_TAINTED_WOOD.get())
                .add(ModBlocks.TAINTED_WOOD.get())
                .add(ModBlocks.STRIPPED_TAINTED_LOG.get())
                .add(ModBlocks.TAINTED_LOG.get());

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
                .add(ModBlocks.BLOODWOOD_LEAVES.get());

        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.TAINTED_SAPLING.get())
                .add(ModBlocks.BLOODWOOD_SAPLING.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.TAINTED_PLANKS.get())
                .add(ModBlocks.BLOODWOOD_PLANKS.get());

        tag(BlockTags.STAIRS)
                .add(ModBlocks.TAINTED_STAIRS.get())
                .add(ModBlocks.BLOODWOOD_STAIRS.get());

        tag(BlockTags.SLABS)
                .add(ModBlocks.TAINTED_SLAB.get())
                .add(ModBlocks.BLOODWOOD_SLAB.get());

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
                .add(ModBlocks.AZURITE_PRESSURE_PLATE.get());

        tag(BlockTags.BUTTONS)
                .add(ModBlocks.AZURITE_BUTTON.get());

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
