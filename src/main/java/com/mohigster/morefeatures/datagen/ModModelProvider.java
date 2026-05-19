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
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(4).asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(5).asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_SCRAP.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModBlocks.BLOODWOOD_SAPLING.get().asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.METAL_DETECTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CARBON_FIBER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
//        itemModels.generateFlatItem(ModItems.CARBON_BOW.get(), ModelTemplates.FLAT_HANDHELD_ITEM);


        // Used the generateFlatItem() line to get carbon_bow.json, then to prevent datagen deleting it,
        // I moved that file to the permanent resources file instead of the generated resources file.
        // Then ran the generateBow() line. Keeping generateFlatItem() commented there for reference

        // Must do this because generateBow() gives the models for when the bow is being pulled,
        // but generateFlatItem() gives the model for when it isn't. However, using both methods
        // at the same time causes DataGen to fail. I don't know why, but it does.

        // Same logic applies to crossbow and elytra

//        itemModels.generateFlatItem(ModItems.CARBON_BOW.get(), ModelTemplates.BOW);
//        itemModels.generateFlatItem(ModItems.CARBON_CROSSBOW.get(), ModelTemplates.CROSSBOW);
//        itemModels.generateFlatItem(ModItems.CARBON_ELYTRA.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateBow(ModItems.CARBON_BOW.get());
        itemModels.generateCrossbow(ModItems.CARBON_CROSSBOW.get());
        itemModels.generateElytra(ModItems.CARBON_ELYTRA.get());
        itemModels.generateSpear(ModItems.BISMUTH_SPEAR.get());
        itemModels.generateFlatItem(ModItems.BISMUTH_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_NAUTILUS_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CARBON_WOLF_ARMOR.get(), ModelTemplates.FLAT_ITEM);

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
        blockModels.createTrivialCube(ModBlocks.RAW_AZURITE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.FLUORITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.DEEPSLATE_FLUORITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.NETHER_FLUORITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.END_FLUORITE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.RAW_FLUORITE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.BISMUTH_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RAW_BISMUTH_BLOCK.get());
        blockModels.woodProvider(ModBlocks.BLOODWOOD_LOG.get()).logWithHorizontal(ModBlocks.BLOODWOOD_LOG.get()).wood(ModBlocks.BLOODWOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()).wood(ModBlocks.STRIPPED_BLOODWOOD.get());
        blockModels.createTrivialBlock(ModBlocks.BLOODWOOD_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createCrossBlock(ModBlocks.BLOODWOOD_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createTrivialCube(ModBlocks.MAGIC_BLOCK.get());
        blockModels.family(ModBlocks.AZURITE_BLOCK.get())
                .stairs(ModBlocks.AZURITE_STAIRS.get())
                .slab(ModBlocks.AZURITE_SLAB.get());
        blockModels.family(ModBlocks.FLUORITE_BLOCK.get())
                .stairs(ModBlocks.FLUORITE_STAIRS.get())
                .slab(ModBlocks.FLUORITE_SLAB.get());
        blockModels.family(ModBlocks.BLOODWOOD_PLANKS.get())
                .stairs(ModBlocks.BLOODWOOD_STAIRS.get())
                .slab(ModBlocks.BLOODWOOD_SLAB.get());
    }

}
