package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
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
                .add(ModBlocks.BISMUTH_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ALUMINIUM_BLOCK.get())
                .add(ModBlocks.ALUMINIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get())
                .add(ModBlocks.RAW_ALUMINIUM_BLOCK.get())
                .add(ModBlocks.RAW_MAGNESIUM_BLOCK.get())
                .add(ModBlocks.MAGNESIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get())
                .add(ModBlocks.MAGNESIUM_BLOCK.get());

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
                .add(ModBlocks.AZURITE_BLOCK.get());
    }
}
