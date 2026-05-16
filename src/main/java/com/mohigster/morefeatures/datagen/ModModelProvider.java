package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, MoreFeatures.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        // ITEMS

        itemModels.generateFlatItem(ModItems.RAW_ALUMINIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ALUMINIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_MAGNESIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.MAGNESIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_AZURITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.AZURITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_FLUORITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.FLUORITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BRINE_ROD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(0).asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(1).asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(2).asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(3).asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(4).asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(5).asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(6).asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(7).asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(8).asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_SCRAP.get(), ModelTemplates.FLAT_ITEM);

        // BLOCKS

        blockModels.createTrivialCube(ModBlocks.ALUMINIUM_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RAW_ALUMINIUM_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.ALUMINIUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.MAGNESIUM_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RAW_MAGNESIUM_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.MAGNESIUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_ORE.get());
        blockModels.createTrivialCube(ModBlocks.AZURITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_AZURITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.NETHER_AZURITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.END_AZURITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.AZURITE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RAW_AZURITE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.FLUORITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_FLUORITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.NETHER_FLUORITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.END_FLUORITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.FLUORITE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RAW_FLUORITE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RAW_BISMUTH_BLOCK.get());
        blockModels.woodProvider(ModBlocks.BLOODWOOD_LOG.get()).logWithHorizontal(ModBlocks.BLOODWOOD_LOG.get()).wood(ModBlocks.BLOODWOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()).wood(ModBlocks.STRIPPED_BLOODWOOD.get());
        blockModels.createTrivialCube(ModBlocks.BLOODWOOD_PLANKS.get());
        blockModels.createTrivialBlock(ModBlocks.BLOODWOOD_LEAVES.get(), TexturedModel.LEAVES);

        blockModels.createTrivialCube(ModBlocks.BLOODWOOD_SAPLING.get());

//        blockModels.createPlantWithDefaultItem(ModBlocks.BLOODWOOD_SAPLING.get(), ModBlocks.BLOODWOOD_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
    }

}
