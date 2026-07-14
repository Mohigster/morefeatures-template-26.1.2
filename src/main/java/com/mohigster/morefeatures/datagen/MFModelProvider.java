package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.asset.MFEquipmentAssets;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.family.MFBlockFamilies;
import com.mohigster.morefeatures.datagen.models.MFBlockModelGenerators;
import com.mohigster.morefeatures.datagen.models.MFItemModelGenerators;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.item.custom.trim.MFMaterialAssetGroups;
import com.mohigster.morefeatures.item.custom.trim.MFTrimMaterials;
import net.minecraft.client.data.models.*;
import net.minecraft.client.data.models.model.*;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.level.block.*;

import java.util.ArrayList;
import java.util.List;

public class MFModelProvider extends ModelProvider {

    public MFModelProvider(PackOutput output) {
        super(output, MoreFeatures.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        // ITEMS

        itemModels.generateFlatItem(MFItems.RAW_ALUMINIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.ALUMINIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.RAW_MAGNESIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.MAGNESIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.RAW_AZURITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.AZURITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.RAW_BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.RAW_FLUORITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.FLUORITE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BRINE_ROD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_EQUIPMENT.get(0).asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_EQUIPMENT.get(1).asItem(), ModelTemplates.FLAT_HANDHELD_ITEM);
        MFItemModelGenerators.generateTrimmableItem(itemModels, MFItems.BISMUTH_HELMET.get(), MFEquipmentAssets.BISMUTH, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        MFItemModelGenerators.generateTrimmableItem(itemModels, MFItems.BISMUTH_CHESTPLATE.get(), MFEquipmentAssets.BISMUTH, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        MFItemModelGenerators.generateTrimmableItem(itemModels, MFItems.BISMUTH_LEGGINGS.get(), MFEquipmentAssets.BISMUTH, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        MFItemModelGenerators.generateTrimmableItem(itemModels, MFItems.BISMUTH_BOOTS.get(), MFEquipmentAssets.BISMUTH, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModels.generateFlatItem(MFItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_SCRAP.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.METAL_DETECTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.CARBON_FIBER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MFItems.EVERFROST.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.RAW_EVERFROST.get(), ModelTemplates.FLAT_ITEM);
        MFItemModelGenerators.customModelWithFlatInvTexture(itemModels, MFItems.ICE_WAND.get());
        MFItemModelGenerators.customModelWithFlatInvTexture(itemModels, MFItems.FIRE_WAND.get());
        MFItemModelGenerators.customModelWithFlatInvTexture(itemModels, MFItems.HEALING_WAND.get());
        MFItemModelGenerators.customModelWithFlatInvTexture(itemModels, MFItems.EARTH_WAND.get());
        MFItemModelGenerators.customModelWithFlatInvTexture(itemModels, MFItems.LIGHTNING_WAND.get());

        // Used the generateFlatItem() line to get carbon_bow.json, then to prevent datagen deleting it,
        // I moved that file to the permanent resources file instead of the generated resources file.
        // I then ran the generateBow() line. Keeping generateFlatItem() commented there for reference

        // Must do this because generateBow() gives the models for when the bow is being pulled,
        // but generateFlatItem() gives the model for when it isn't. However, using both methods
        // at the same time causes DataGen to fail. I don't know why, but it does.

        // Same logic applies to crossbow and elytra, and their bismuth equivalents

//        itemModels.generateFlatItem(ModItems.CARBON_BOW.get(), ModelTemplates.BOW);
//        itemModels.generateFlatItem(ModItems.CARBON_CROSSBOW.get(), ModelTemplates.CROSSBOW);
//        itemModels.generateFlatItem(ModItems.CARBON_ELYTRA.get(), ModelTemplates.FLAT_ITEM);
//        itemModels.generateFlatItem(ModItems.BISMUTH_BOW.get(), ModelTemplates.BOW);
        itemModels.generateBow(MFItems.CARBON_BOW.get());
        itemModels.generateCrossbow(MFItems.CARBON_CROSSBOW.get());
        itemModels.generateElytra(MFItems.CARBON_ELYTRA.get());
        itemModels.generateBow(MFItems.BISMUTH_BOW.get());
        itemModels.generateSpear(MFItems.BISMUTH_SPEAR.get());
        itemModels.generateShield(MFItems.CARBON_SHIELD.get());
        itemModels.generateTrident(MFItems.CARBON_TRIDENT.get());
        itemModels.generateFlatItem(MFItems.BISMUTH_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_NAUTILUS_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.CARBON_WOLF_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateTrident(MFItems.BISMUTH_TRIDENT.get());
        itemModels.generateFlatItem(MFItems.MUSIC_DISC_AQUAMARINE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BLOODWOOD_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BLOODWOOD_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.TAINTED_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.TAINTED_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.PALM_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.PALM_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.FROSTED_CORE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.ICEOLOGER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.declareCustomModelItem(MFBlocks.ICICLE.asItem());

        // BLOCKS

        blockModels.createTrivialCube(MFBlocks.ALUMINIUM_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.RAW_ALUMINIUM_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.ALUMINIUM_ORE.get());
        blockModels.createTrivialCube(MFBlocks.DEEPSLATE_ALUMINIUM_ORE.get());
        blockModels.createTrivialCube(MFBlocks.MAGNESIUM_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.RAW_MAGNESIUM_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.MAGNESIUM_ORE.get());
        blockModels.createTrivialCube(MFBlocks.DEEPSLATE_MAGNESIUM_ORE.get());
        blockModels.createTrivialCube(MFBlocks.BISMUTH_ORE.get());
        blockModels.createTrivialCube(MFBlocks.AZURITE_ORE.get());
        blockModels.createTrivialCube(MFBlocks.DEEPSLATE_AZURITE_ORE.get());
        blockModels.createTrivialCube(MFBlocks.NETHER_AZURITE_ORE.get());
        blockModels.createTrivialCube(MFBlocks.END_AZURITE_ORE.get());
        blockModels.createTrivialCube(MFBlocks.RAW_AZURITE_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.FLUORITE_ORE.get());
        blockModels.createTrivialCube(MFBlocks.DEEPSLATE_FLUORITE_ORE.get());
        blockModels.createTrivialCube(MFBlocks.NETHER_FLUORITE_ORE.get());
        blockModels.createTrivialCube(MFBlocks.END_FLUORITE_ORE.get());
        blockModels.createTrivialCube(MFBlocks.RAW_FLUORITE_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.BISMUTH_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.RAW_BISMUTH_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.EVERFROST_BLUE_ICE_ORE.get());
        blockModels.createTrivialCube(MFBlocks.EVERFROST_PACKED_ICE_ORE.get());
        MFBlockModelGenerators.createAge3Block(blockModels, MFBlocks.CONJURED_ICE.get(), true);
        blockModels.woodProvider(MFBlocks.TAINTED_LOG.get()).logWithHorizontal(MFBlocks.TAINTED_LOG.get()).wood(MFBlocks.TAINTED_WOOD.get());
        blockModels.woodProvider(MFBlocks.STRIPPED_TAINTED_LOG.get()).logWithHorizontal(MFBlocks.STRIPPED_TAINTED_LOG.get()).wood(MFBlocks.STRIPPED_TAINTED_WOOD.get());
        blockModels.createShelf(MFBlocks.TAINTED_SHELF.get(), MFBlocks.STRIPPED_TAINTED_LOG.get());
        blockModels.createTrivialBlock(MFBlocks.TAINTED_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(MFBlocks.TAINTED_SAPLING.get(), MFBlocks.POTTED_TAINTED_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.TAINTED_VERTICAL_SLAB.get(), MFBlocks.TAINTED_PLANKS.get());
        blockModels.woodProvider(MFBlocks.BLOODWOOD_LOG.get()).logWithHorizontal(MFBlocks.BLOODWOOD_LOG.get()).wood(MFBlocks.BLOODWOOD.get());
        blockModels.woodProvider(MFBlocks.STRIPPED_BLOODWOOD_LOG.get()).logWithHorizontal(MFBlocks.STRIPPED_BLOODWOOD_LOG.get()).wood(MFBlocks.STRIPPED_BLOODWOOD.get());
        blockModels.createShelf(MFBlocks.BLOODWOOD_SHELF.get(), MFBlocks.STRIPPED_BLOODWOOD_LOG.get());
        blockModels.createTrivialBlock(MFBlocks.BLOODWOOD_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(MFBlocks.BLOODWOOD_SAPLING.get(), MFBlocks.POTTED_BLOODWOOD_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.BLOODWOOD_VERTICAL_SLAB.get(), MFBlocks.BLOODWOOD_PLANKS.get());
        blockModels.woodProvider(MFBlocks.PALM_LOG.get()).logWithHorizontal(MFBlocks.PALM_LOG.get()).wood(MFBlocks.PALM_WOOD.get());
        blockModels.woodProvider(MFBlocks.STRIPPED_PALM_LOG.get()).logWithHorizontal(MFBlocks.STRIPPED_PALM_LOG.get()).wood(MFBlocks.STRIPPED_PALM_WOOD.get());
        blockModels.createShelf(MFBlocks.PALM_SHELF.get(), MFBlocks.STRIPPED_PALM_LOG.get());
        blockModels.createTrivialBlock(MFBlocks.PALM_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(MFBlocks.PALM_SAPLING.get(), MFBlocks.POTTED_PALM_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.PALM_VERTICAL_SLAB.get(), MFBlocks.PALM_PLANKS.get());
        blockModels.createTrivialCube(MFBlocks.MAGIC_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.EVIL_PORTAL.get());
        blockModels.createFurnace(MFBlocks.COMPRESSOR_BLOCK.get(), TexturedModel.ORIENTABLE);
        blockModels.createPlantWithDefaultItem(MFBlocks.ROSE.get(), MFBlocks.POTTED_ROSE.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createPlantWithDefaultItem(MFBlocks.BLUE_ROSE.get(), MFBlocks.POTTED_BLUE_ROSE.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.woodProvider(MFBlocks.DECREPIT_LOG.get()).logWithHorizontal(MFBlocks.DECREPIT_LOG.get()).wood(MFBlocks.DECREPIT_WOOD.get());
        blockModels.woodProvider(MFBlocks.STRIPPED_DECREPIT_LOG.get()).logWithHorizontal(MFBlocks.STRIPPED_DECREPIT_LOG.get()).wood(MFBlocks.STRIPPED_DECREPIT_WOOD.get());
        blockModels.createShelf(MFBlocks.DECREPIT_SHELF.get(), MFBlocks.STRIPPED_DECREPIT_LOG.get());
        blockModels.createTrivialBlock(MFBlocks.DECREPIT_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(MFBlocks.DECREPIT_SAPLING.get(), MFBlocks.POTTED_DECREPIT_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.DECREPIT_VERTICAL_SLAB.get(), MFBlocks.DECREPIT_PLANKS.get());
        blockModels.woodProvider(MFBlocks.PALLID_LOG.get()).logWithHorizontal(MFBlocks.PALLID_LOG.get()).wood(MFBlocks.PALLID_WOOD.get());
        blockModels.woodProvider(MFBlocks.STRIPPED_PALLID_LOG.get()).logWithHorizontal(MFBlocks.STRIPPED_PALLID_LOG.get()).wood(MFBlocks.STRIPPED_PALLID_WOOD.get());
        blockModels.createShelf(MFBlocks.PALLID_SHELF.get(), MFBlocks.STRIPPED_PALLID_LOG.get());
        blockModels.createTrivialBlock(MFBlocks.PALLID_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(MFBlocks.PALLID_SAPLING.get(), MFBlocks.POTTED_PALLID_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.PALLID_VERTICAL_SLAB.get(), MFBlocks.PALLID_PLANKS.get());
        blockModels.createPlantWithDefaultItem(MFBlocks.DECREPIT_ROOTS.get(), MFBlocks.POTTED_DECREPIT_ROOTS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createPlantWithDefaultItem(MFBlocks.PALLID_ROOTS.get(), MFBlocks.POTTED_PALLID_ROOTS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createSpeleothem(MFBlocks.ICICLE.get());
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.AZURITE_VERTICAL_SLAB.get(), MFBlocks.AZURITE_BLOCK.get());
        blockModels.createShelf(MFBlocks.AZURITE_SHELF.get(), MFBlocks.RAW_AZURITE_BLOCK.get());
        MFBlockModelGenerators.createNyliumLikeBlock(blockModels, MFBlocks.PALLID_NULLIUM.get(), Blocks.END_STONE); // Call blockModels as a parameter so that we can use blockStateOutput and modelOutput. This will be necessary for all custom model generation methods
        MFBlockModelGenerators.createNyliumLikeBlock(blockModels, MFBlocks.DECREPIT_NULLIUM.get(), Blocks.END_STONE);
        MFBlockModelGenerators.createAnchor(blockModels, MFBlocks.VOID_ANCHOR.get());

        /* Vanilla vertical slab models */

        /* WOODEN */

        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.OAK_VERTICAL_SLAB.get(), Blocks.OAK_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.SPRUCE_VERTICAL_SLAB.get(), Blocks.SPRUCE_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.BIRCH_VERTICAL_SLAB.get(), Blocks.BIRCH_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.JUNGLE_VERTICAL_SLAB.get(), Blocks.JUNGLE_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.ACACIA_VERTICAL_SLAB.get(), Blocks.ACACIA_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.DARK_OAK_VERTICAL_SLAB.get(), Blocks.DARK_OAK_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.CRIMSON_VERTICAL_SLAB.get(), Blocks.CRIMSON_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.WARPED_VERTICAL_SLAB.get(), Blocks.WARPED_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.MANGROVE_VERTICAL_SLAB.get(), Blocks.MANGROVE_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.CHERRY_VERTICAL_SLAB.get(), Blocks.CHERRY_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.BAMBOO_VERTICAL_SLAB.get(), Blocks.BAMBOO_PLANKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.BAMBOO_MOSAIC_VERTICAL_SLAB.get(), Blocks.BAMBOO_MOSAIC);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.PALE_OAK_VERTICAL_SLAB.get(), Blocks.PALE_OAK_PLANKS);

        /* STONE & DEEPSLATE */

        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.STONE_VERTICAL_SLAB.get(), Blocks.STONE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.COBBLESTONE_VERTICAL_SLAB.get(), Blocks.COBBLESTONE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), Blocks.MOSSY_COBBLESTONE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.STONE_BRICK_VERTICAL_SLAB.get(), Blocks.STONE_BRICKS);
        MFBlockModelGenerators.createSmoothStoneVerticalSlab(blockModels);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB.get(), Blocks.MOSSY_STONE_BRICKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB.get(), Blocks.COBBLED_DEEPSLATE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB.get(), Blocks.POLISHED_DEEPSLATE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB.get(), Blocks.DEEPSLATE_BRICKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB.get(), Blocks.DEEPSLATE_TILES);

        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.GRANITE_VERTICAL_SLAB.get(), Blocks.GRANITE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB.get(), Blocks.POLISHED_GRANITE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.DIORITE_VERTICAL_SLAB.get(), Blocks.DIORITE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB.get(), Blocks.POLISHED_DIORITE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.ANDESITE_VERTICAL_SLAB.get(), Blocks.ANDESITE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB.get(), Blocks.POLISHED_ANDESITE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.TUFF_VERTICAL_SLAB.get(), Blocks.TUFF);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.POLISHED_TUFF_VERTICAL_SLAB.get(), Blocks.POLISHED_TUFF);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.TUFF_BRICK_VERTICAL_SLAB.get(), Blocks.TUFF_BRICKS);

        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.SANDSTONE_VERTICAL_SLAB.get(), Blocks.SANDSTONE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB.get(), Blocks.SMOOTH_SANDSTONE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB.get(), Blocks.CUT_SANDSTONE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.RED_SANDSTONE_VERTICAL_SLAB.get(), Blocks.RED_SANDSTONE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB.get(), Blocks.SMOOTH_RED_SANDSTONE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB.get(), Blocks.CUT_RED_SANDSTONE);

        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.SULFUR_VERTICAL_SLAB.get(), Blocks.SULFUR);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB.get(), Blocks.POLISHED_SULFUR);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.SULFUR_BRICK_VERTICAL_SLAB.get(), Blocks.SULFUR_BRICKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.CINNABAR_VERTICAL_SLAB.get(), Blocks.CINNABAR);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB.get(), Blocks.POLISHED_CINNABAR);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB.get(), Blocks.CINNABAR_BRICKS);

        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.NETHER_BRICK_VERTICAL_SLAB.get(), Blocks.NETHER_BRICKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.RED_NETHER_BRICK_VERTICAL_SLAB.get(), Blocks.RED_NETHER_BRICKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.BLACKSTONE_VERTICAL_SLAB.get(), Blocks.BLACKSTONE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB.get(), Blocks.POLISHED_BLACKSTONE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB.get(), Blocks.POLISHED_BLACKSTONE_BRICKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.END_STONE_BRICK_VERTICAL_SLAB.get(), Blocks.END_STONE_BRICKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.PURPUR_VERTICAL_SLAB.get(), Blocks.PURPUR_BLOCK);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.QUARTZ_VERTICAL_SLAB.get(), Blocks.QUARTZ_BLOCK);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB.get(), Blocks.SMOOTH_QUARTZ);

        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.PRISMARINE_VERTICAL_SLAB.get(), Blocks.PRISMARINE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.PRISMARINE_BRICK_VERTICAL_SLAB.get(), Blocks.PRISMARINE_BRICKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.DARK_PRISMARINE_VERTICAL_SLAB.get(), Blocks.DARK_PRISMARINE);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.BRICK_VERTICAL_SLAB.get(), Blocks.BRICKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.MUD_BRICK_VERTICAL_SLAB.get(), Blocks.MUD_BRICKS);
        MFBlockModelGenerators.createVerticalSlab(blockModels, MFBlocks.RESIN_BRICK_VERTICAL_SLAB.get(), Blocks.RESIN_BRICKS);

        MFBlockModelGenerators.generateWeatheredAndWaxedCopper( // generates all models for every cut copper vertical slab, including all weathered states and their waxed variants.
                // WeatheringCopperCollection.zipApply cannot be used because the waxed blocks don't have a unique texture, but it assumes they do, causing it to find no texture for the waxed variants and default to the placeholder texture
                blockModels,
                MFBlocks.CUT_COPPER_VERTICAL_SLAB,
                Blocks.CUT_COPPER,
                MFBlockModelGenerators::createVerticalSlab
        );

        ColorCollection.VALUES.forEach( // This one call generates all the models for every wool colour, and dynamically adapts if Mojang ever adds a new wool colour to the game. No new code necessary at all!
                colour -> MFBlockModelGenerators.createVerticalSlab(
                        blockModels,
                        MFBlocks.WOOL_VERTICAL_SLAB.pick(colour).get(),
                        Blocks.WOOL.pick(colour)
                )
        );

        // Block families—createTrivialCube is unnecessary for Azurite block
        // etc. because their models are created by the block family.

        blockModels.family(MFBlocks.AZURITE_BLOCK.get())
                .generateFor(MFBlockFamilies.getAzuriteFamily());
        blockModels.family(MFBlocks.FLUORITE_BLOCK.get())
                .generateFor(MFBlockFamilies.getFluoriteFamily());
        blockModels.family(MFBlocks.BLOODWOOD_PLANKS.get())
                .generateFor(MFBlockFamilies.getBloodwoodFamily());
        blockModels.family(MFBlocks.TAINTED_PLANKS.get())
                .generateFor(MFBlockFamilies.getTaintedFamily());
        blockModels.family(MFBlocks.PALM_PLANKS.get())
                .generateFor(MFBlockFamilies.getPalmFamily());
        blockModels.family(MFBlocks.DECREPIT_PLANKS.get())
                .generateFor(MFBlockFamilies.getDecrepitFamily());
        blockModels.family(MFBlocks.PALLID_PLANKS.get())
                .generateFor(MFBlockFamilies.getPallidFamily());
    }
}
