package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.block.family.ModBlockFamilies;
import com.mohigster.morefeatures.datagen.models.ModBlockModelGenerators;
import com.mohigster.morefeatures.datagen.models.ModItemModelGenerators;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.client.data.models.*;
import net.minecraft.client.data.models.model.*;

import net.minecraft.data.PackOutput;
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
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(2).asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(3).asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(4).asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_EQUIPMENT.get(5).asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_SCRAP.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.METAL_DETECTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CARBON_FIBER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EVERFROST.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.RAW_EVERFROST.get(), ModelTemplates.FLAT_ITEM);
        ModItemModelGenerators.customModelWithFlatInvTexture(itemModels, ModItems.ICE_WAND.get());
        ModItemModelGenerators.customModelWithFlatInvTexture(itemModels, ModItems.FIRE_WAND.get());
        ModItemModelGenerators.customModelWithFlatInvTexture(itemModels, ModItems.HEALING_WAND.get());
        ModItemModelGenerators.customModelWithFlatInvTexture(itemModels, ModItems.EARTH_WAND.get());
        ModItemModelGenerators.customModelWithFlatInvTexture(itemModels, ModItems.LIGHTNING_WAND.get());

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
        itemModels.generateBow(ModItems.CARBON_BOW.get());
        itemModels.generateCrossbow(ModItems.CARBON_CROSSBOW.get());
        itemModels.generateElytra(ModItems.CARBON_ELYTRA.get());
        itemModels.generateBow(ModItems.BISMUTH_BOW.get());
        itemModels.generateSpear(ModItems.BISMUTH_SPEAR.get());
        itemModels.generateShield(ModItems.CARBON_SHIELD.get());
        itemModels.generateTrident(ModItems.CARBON_TRIDENT.get());
        itemModels.generateFlatItem(ModItems.BISMUTH_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BISMUTH_NAUTILUS_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.CARBON_WOLF_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateTrident(ModItems.BISMUTH_TRIDENT.get());
        itemModels.generateFlatItem(ModItems.MUSIC_DISC_AQUAMARINE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PALM_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.PALM_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.FROSTED_CORE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.ICEOLOGER_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
        itemModels.declareCustomModelItem(ModBlocks.ICICLE.asItem());

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
        blockModels.createTrivialCube(ModBlocks.EVERFROST_BLUE_ICE_ORE.get());
        blockModels.createTrivialCube(ModBlocks.EVERFROST_PACKED_ICE_ORE.get());
        ModBlockModelGenerators.createAge3Block(blockModels, ModBlocks.CONJURED_ICE.get(), true);
        blockModels.woodProvider(ModBlocks.TAINTED_LOG.get()).logWithHorizontal(ModBlocks.TAINTED_LOG.get()).wood(ModBlocks.TAINTED_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_TAINTED_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_TAINTED_LOG.get()).wood(ModBlocks.STRIPPED_TAINTED_WOOD.get());
        blockModels.createTrivialBlock(ModBlocks.TAINTED_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(ModBlocks.TAINTED_SAPLING.get(), ModBlocks.POTTED_TAINTED_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.woodProvider(ModBlocks.BLOODWOOD_LOG.get()).logWithHorizontal(ModBlocks.BLOODWOOD_LOG.get()).wood(ModBlocks.BLOODWOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()).wood(ModBlocks.STRIPPED_BLOODWOOD.get());
        blockModels.createTrivialBlock(ModBlocks.BLOODWOOD_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(ModBlocks.BLOODWOOD_SAPLING.get(), ModBlocks.POTTED_BLOODWOOD_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.woodProvider(ModBlocks.PALM_LOG.get()).logWithHorizontal(ModBlocks.PALM_LOG.get()).wood(ModBlocks.PALM_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_PALM_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_PALM_LOG.get()).wood(ModBlocks.STRIPPED_PALM_WOOD.get());
        blockModels.createTrivialBlock(ModBlocks.PALM_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(ModBlocks.PALM_SAPLING.get(), ModBlocks.POTTED_PALM_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createTrivialCube(ModBlocks.MAGIC_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.EVIL_PORTAL.get());
        blockModels.createFurnace(ModBlocks.COMPRESSOR_BLOCK.get(), TexturedModel.ORIENTABLE);
        blockModels.createPlantWithDefaultItem(ModBlocks.ROSE.get(), ModBlocks.POTTED_ROSE.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createPlantWithDefaultItem(ModBlocks.BLUE_ROSE.get(), ModBlocks.POTTED_BLUE_ROSE.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createShelf(ModBlocks.PALM_SHELF.get(), ModBlocks.PALM_PLANKS.get());
        blockModels.woodProvider(ModBlocks.DECREPIT_LOG.get()).logWithHorizontal(ModBlocks.DECREPIT_LOG.get()).wood(ModBlocks.DECREPIT_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_DECREPIT_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_DECREPIT_LOG.get()).wood(ModBlocks.STRIPPED_DECREPIT_WOOD.get());
        blockModels.createTrivialBlock(ModBlocks.DECREPIT_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(ModBlocks.DECREPIT_SAPLING.get(), ModBlocks.POTTED_DECREPIT_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.woodProvider(ModBlocks.PALLID_LOG.get()).logWithHorizontal(ModBlocks.PALLID_LOG.get()).wood(ModBlocks.PALLID_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_PALLID_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_PALLID_LOG.get()).wood(ModBlocks.STRIPPED_PALLID_WOOD.get());
        blockModels.createTrivialBlock(ModBlocks.PALLID_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(ModBlocks.DECREPIT_ROOTS.get(), ModBlocks.POTTED_DECREPIT_ROOTS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createPlantWithDefaultItem(ModBlocks.PALLID_ROOTS.get(), ModBlocks.POTTED_PALLID_ROOTS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createPlantWithDefaultItem(ModBlocks.PALLID_SAPLING.get(), ModBlocks.POTTED_PALLID_SAPLING.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createSpeleothem(ModBlocks.ICICLE.get());
        ModBlockModelGenerators.createNyliumLikeBlock(blockModels, ModBlocks.PALLID_NULLIUM.get(), Blocks.END_STONE); // Call blockModels as a parameter so that we can use blockStateOutput and modelOutput. This will be necessary for all custom model generation methods
        ModBlockModelGenerators.createNyliumLikeBlock(blockModels, ModBlocks.DECREPIT_NULLIUM.get(), Blocks.END_STONE);
        ModBlockModelGenerators.createAnchor(blockModels, ModBlocks.VOID_ANCHOR.get());

        /* Vanilla vertical slab models */

        /* WOODEN */
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.OAK_VERTICAL_SLAB.get(), Blocks.OAK_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.SPRUCE_VERTICAL_SLAB.get(), Blocks.SPRUCE_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.BIRCH_VERTICAL_SLAB.get(), Blocks.BIRCH_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.JUNGLE_VERTICAL_SLAB.get(), Blocks.JUNGLE_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.ACACIA_VERTICAL_SLAB.get(), Blocks.ACACIA_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.DARK_OAK_VERTICAL_SLAB.get(), Blocks.DARK_OAK_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.CRIMSON_VERTICAL_SLAB.get(), Blocks.CRIMSON_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.WARPED_VERTICAL_SLAB.get(), Blocks.WARPED_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.MANGROVE_VERTICAL_SLAB.get(), Blocks.MANGROVE_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.CHERRY_VERTICAL_SLAB.get(), Blocks.CHERRY_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.BAMBOO_VERTICAL_SLAB.get(), Blocks.BAMBOO_PLANKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.BAMBOO_MOSAIC_VERTICAL_SLAB.get(), Blocks.BAMBOO_MOSAIC);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.PALE_OAK_VERTICAL_SLAB.get(), Blocks.PALE_OAK_PLANKS);
        /* STONE & DEEPSLATE */
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.STONE_VERTICAL_SLAB.get(), Blocks.STONE);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.COBBLESTONE_VERTICAL_SLAB.get(), Blocks.COBBLESTONE);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), Blocks.MOSSY_COBBLESTONE);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.STONE_BRICK_VERTICAL_SLAB.get(), Blocks.STONE_BRICKS);
        ModBlockModelGenerators.createSmoothStoneVerticalSlab(blockModels);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB.get(), Blocks.MOSSY_STONE_BRICKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB.get(), Blocks.COBBLED_DEEPSLATE);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB.get(), Blocks.POLISHED_DEEPSLATE);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB.get(), Blocks.DEEPSLATE_BRICKS);
        ModBlockModelGenerators.createVerticalSlab(blockModels, ModBlocks.DEEPSLATE_TILE_VERTICAL_SLAB.get(), Blocks.DEEPSLATE_TILES);

        // Block families—createTrivialCube is unnecessary for Azurite block
        // etc. because their models are created by the block family.

        blockModels.family(ModBlocks.AZURITE_BLOCK.get())
                .stairs(ModBlocks.AZURITE_STAIRS.get())
                .slab(ModBlocks.AZURITE_SLAB.get())
                .pressurePlate(ModBlocks.AZURITE_PRESSURE_PLATE.get())
                .button(ModBlocks.AZURITE_BUTTON.get());
        blockModels.family(ModBlocks.FLUORITE_BLOCK.get())
                .stairs(ModBlocks.FLUORITE_STAIRS.get())
                .slab(ModBlocks.FLUORITE_SLAB.get());
        blockModels.family(ModBlocks.BLOODWOOD_PLANKS.get())
                .generateFor(ModBlockFamilies.getBloodwoodFamily());
        blockModels.family(ModBlocks.TAINTED_PLANKS.get())
                .generateFor(ModBlockFamilies.getTaintedFamily());
        blockModels.family(ModBlocks.PALM_PLANKS.get())
                .generateFor(ModBlockFamilies.getPalmFamily());
        blockModels.family(ModBlocks.DECREPIT_PLANKS.get())
                .generateFor(ModBlockFamilies.getDecrepitFamily());
        blockModels.family(ModBlocks.PALLID_PLANKS.get())
                .generateFor(ModBlockFamilies.getPallidFamily());
    }
}
