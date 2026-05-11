package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MoreFeatures.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // ITEMS
        itemModels.generateFlatItem(ModItems.RAW_ALUMINIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ALUMINIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);


        // BLOCKS
        blockModels.createTrivialCube(ModBlocks.ALUMINIUM_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RAW_ALUMINIUM_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.ALUMINIUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MAGNESIUM_BLOCK.get());
    }
}
