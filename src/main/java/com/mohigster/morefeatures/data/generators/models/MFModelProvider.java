package com.mohigster.morefeatures.data.generators.models;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.collection.gemstone.GemstoneCollection;
import com.mohigster.morefeatures.block.collection.vanilla.VanillaWoodCollection;
import com.mohigster.morefeatures.data.material.MFEquipmentAssets;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.block.family.MFBlockFamilies;
import com.mohigster.morefeatures.item.MFItems;
import net.minecraft.client.data.models.*;
import net.minecraft.client.data.models.model.*;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import org.jspecify.annotations.NonNull;

public class MFModelProvider extends ModelProviderExtended {
    public MFModelProvider(PackOutput output) {
        super(output, MoreFeatures.MODID);
    }

    @Override
    public void registerModels(
            @NonNull MFBlockModelGenerators mfBlockModels,
            @NonNull MFItemModelGenerators mfItemModels,
            @NonNull BlockModelGenerators blockModels,
            @NonNull ItemModelGenerators itemModels
    ) {
        // ITEMS
        itemModels.generateFlatItem(MFItems.RAW_ALUMINIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.ALUMINIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.RAW_MAGNESIUM.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.MAGNESIUM_INGOT.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.RAW_GEM.azurite().get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.GEM.azurite().get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.RAW_BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.RAW_GEM.fluorite().get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.GEM.fluorite().get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BRINE_ROD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_SWORD.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_PICKAXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        mfItemModels.generateTrimmableItem(MFItems.BISMUTH_HELMET.get(), MFEquipmentAssets.BISMUTH, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        mfItemModels.generateTrimmableItem(MFItems.BISMUTH_CHESTPLATE.get(), MFEquipmentAssets.BISMUTH, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        mfItemModels.generateTrimmableItem(MFItems.BISMUTH_LEGGINGS.get(), MFEquipmentAssets.BISMUTH, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        mfItemModels.generateTrimmableItem(MFItems.BISMUTH_BOOTS.get(), MFEquipmentAssets.BISMUTH, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModels.generateFlatItem(MFItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_SCRAP.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.METAL_DETECTOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.CARBON_FIBER.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_AXE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_SHOVEL.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_HOE.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(MFItems.EVERFROST.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.RAW_EVERFROST.get(), ModelTemplates.FLAT_ITEM);
        mfItemModels.customModelWithFlatInvTexture(MFItems.ICE_WAND.get());
        mfItemModels.customModelWithFlatInvTexture(MFItems.FIRE_WAND.get());
        mfItemModels.customModelWithFlatInvTexture(MFItems.HEALING_WAND.get());
        mfItemModels.customModelWithFlatInvTexture(MFItems.EARTH_WAND.get());
        mfItemModels.customModelWithFlatInvTexture(MFItems.LIGHTNING_WAND.get());
        mfItemModels.customModelWithFlatInvTexture(MFItems.TIME_WAND.get());
        itemModels.generateShield(MFItems.BISMUTH_SHIELD.get());

        // Used the generateFlatItem() line to get carbon_bow.json, then to prevent datagen deleting it,
        // I moved that file to the permanent resources file instead of the generated resources file.
        // I then ran the generateBow() line.

        // Must do this because generateBow() gives the models for when the bow is being pulled,
        // but generateFlatItem() gives the model for when it isn't. However, using both methods
        // at the same time causes DataGen to fail. I don't know why, but it does.

        // Same logic applies to crossbow and elytra, and their bismuth equivalents

        itemModels.generateBow(MFItems.CARBON_BOW.get());
        itemModels.generateCrossbow(MFItems.CARBON_CROSSBOW.get());
        itemModels.generateElytra(MFItems.CARBON_ELYTRA.get());
        itemModels.generateElytra(MFItems.BISMUTH_ELYTRA.get());
        itemModels.generateBow(MFItems.BISMUTH_BOW.get());
        itemModels.generateSpear(MFItems.BISMUTH_SPEAR.get());
        itemModels.generateShield(MFItems.CARBON_SHIELD.get());
        itemModels.generateTrident(MFItems.CARBON_TRIDENT.get());
        itemModels.generateFlatItem(MFItems.BISMUTH_HORSE_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.BISMUTH_NAUTILUS_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.CARBON_WOLF_ARMOR.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateTrident(MFItems.BISMUTH_TRIDENT.get());
        itemModels.generateFlatItem(MFItems.MUSIC_DISC_AQUAMARINE.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(MFItems.MUSIC_DISC_SNOW_QUEEN.get(), ModelTemplates.FLAT_ITEM);
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
        blockModels.createTrivialCube(MFBlocks.ORE.azurite().get());
        blockModels.createTrivialCube(MFBlocks.DEEPSLATE_ORE.azurite().get());
        blockModels.createTrivialCube(MFBlocks.NETHER_ORE.azurite().get());
        blockModels.createTrivialCube(MFBlocks.END_ORE.azurite().get());
        blockModels.createTrivialCube(MFBlocks.RAW_GEM_BLOCK.azurite().get());
        blockModels.createTrivialCube(MFBlocks.ORE.fluorite().get());
        blockModels.createTrivialCube(MFBlocks.DEEPSLATE_ORE.fluorite().get());
        blockModels.createTrivialCube(MFBlocks.NETHER_ORE.fluorite().get());
        blockModels.createTrivialCube(MFBlocks.END_ORE.fluorite().get());
        blockModels.createTrivialCube(MFBlocks.RAW_GEM_BLOCK.fluorite().get());
        blockModels.createTrivialCube(MFBlocks.BISMUTH_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.RAW_BISMUTH_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.EVERFROST_BLUE_ICE_ORE.get());
        blockModels.createTrivialCube(MFBlocks.EVERFROST_PACKED_ICE_ORE.get());

        mfBlockModels.createBerryBush(MFBlocks.BLUE_BERRY_BUSH.get(), MFItems.BLUE_BERRY.get());

        mfBlockModels.createAge3CubeBlock(MFBlocks.CONJURED_ICE.get(), true);

        blockModels.createTrivialBlock(MFBlocks.TAINTED_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(MFBlocks.TAINTED_SAPLING.get(), MFBlocks.POTTED_SAPLING.tainted().get(), BlockModelGenerators.PlantType.NOT_TINTED);

        blockModels.createTrivialBlock(MFBlocks.BLOODWOOD_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(MFBlocks.BLOODWOOD_SAPLING.get(), MFBlocks.POTTED_SAPLING.bloodwood().get(), BlockModelGenerators.PlantType.NOT_TINTED);

        blockModels.createTrivialBlock(MFBlocks.PALM_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(MFBlocks.PALM_SAPLING.get(), MFBlocks.POTTED_SAPLING.palm().get(), BlockModelGenerators.PlantType.NOT_TINTED);

        blockModels.createTrivialCube(MFBlocks.CHARRED_WART_BLOCK.get());
        blockModels.createPlantWithDefaultItem(MFBlocks.CHARRED_FUNGUS.get(), MFBlocks.POTTED_SAPLING.charred().get(), BlockModelGenerators.PlantType.NOT_TINTED);

        blockModels.createTrivialBlock(MFBlocks.DECREPIT_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(MFBlocks.DECREPIT_SAPLING.get(), MFBlocks.POTTED_SAPLING.decrepit().get(), BlockModelGenerators.PlantType.NOT_TINTED);

        blockModels.createTrivialBlock(MFBlocks.PALLID_LEAVES.get(), TexturedModel.LEAVES);
        blockModels.createPlantWithDefaultItem(MFBlocks.PALLID_SAPLING.get(), MFBlocks.POTTED_SAPLING.pallid().get(), BlockModelGenerators.PlantType.NOT_TINTED);

        blockModels.createTrivialCube(MFBlocks.MAGIC_BLOCK.get());
        blockModels.createTrivialCube(MFBlocks.EVIL_PORTAL.get());
        blockModels.createFurnace(MFBlocks.COMPRESSOR_BLOCK.get(), TexturedModel.ORIENTABLE);
        blockModels.createPlantWithDefaultItem(MFBlocks.ROSE.get(), MFBlocks.POTTED_ROSE.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createPlantWithDefaultItem(MFBlocks.BLUE_ROSE.get(), MFBlocks.POTTED_BLUE_ROSE.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createPlantWithDefaultItem(MFBlocks.CHARRED_ROOTS.get(), MFBlocks.POTTED_CHARRED_ROOTS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createPlantWithDefaultItem(MFBlocks.DECREPIT_ROOTS.get(), MFBlocks.POTTED_DECREPIT_ROOTS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createPlantWithDefaultItem(MFBlocks.PALLID_ROOTS.get(), MFBlocks.POTTED_PALLID_ROOTS.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        blockModels.createGrowingPlant(MFBlocks.SCORCHED_VINES.get(), MFBlocks.SCORCHED_VINES_PLANT.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createGrowingPlant(MFBlocks.SMOLDERED_VINES.get(), MFBlocks.SMOLDERED_VINES_PLANT.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        blockModels.registerSimpleFlatItemModel(MFBlocks.SCORCHED_VINES.get(), "_plant");
        blockModels.registerSimpleFlatItemModel(MFBlocks.SMOLDERED_VINES.get(), "_plant");

        blockModels.createSpeleothem(MFBlocks.ICICLE.get());

        mfBlockModels.createVerticalSlab(MFBlocks.GEMSTONE_VERTICAL_SLAB.azurite().get(), MFBlocks.GEMSTONE_BLOCK.azurite().get());
        blockModels.createShelf(MFBlocks.GEMSTONE_SHELF.azurite().get(), MFBlocks.RAW_GEM_BLOCK.azurite().get());

        mfBlockModels.createVerticalSlab(MFBlocks.GEMSTONE_VERTICAL_SLAB.fluorite().get(), MFBlocks.GEMSTONE_BLOCK.fluorite().get());
        blockModels.createShelf(MFBlocks.GEMSTONE_SHELF.fluorite().get(), MFBlocks.RAW_GEM_BLOCK.fluorite().get());

        mfBlockModels.createNyliumLikeBlock(MFBlocks.PALLID_NULLIUM.get(), Blocks.END_STONE);
        mfBlockModels.createNyliumLikeBlock(MFBlocks.DECREPIT_NULLIUM.get(), Blocks.END_STONE);

        blockModels.createNyliumBlock(MFBlocks.CHARRED_NYLIUM.get());

        mfBlockModels.createAnchor(MFBlocks.VOID_ANCHOR.get());

        mfBlockModels.createPortal(MFBlocks.PORTAL.get());

        /* Vanilla vertical slab models */

        /* WOODEN */

        VanillaWoodCollection.SETS.forEach(set ->
                mfBlockModels.createVerticalSlab(
                        MFBlocks.VANILLA_WOOD_VERTICAL_SLAB.pick(set).get(),
                        set.planks().get()
                )
        );

        VanillaWoodCollection.SETS.forEach(set ->
                mfBlockModels.createPillar(
                        MFBlocks.VANILLA_WOOD_PILLAR.pick(set).get(),
                        set.planks().get()
                )
        );

        /* STONE & DEEPSLATE */

        mfBlockModels.createVerticalSlab(MFBlocks.STONE_VERTICAL_SLAB.get(), Blocks.STONE);
        mfBlockModels.createVerticalSlab(MFBlocks.COBBLESTONE_VERTICAL_SLAB.get(), Blocks.COBBLESTONE);
        mfBlockModels.createVerticalSlab(MFBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), Blocks.MOSSY_COBBLESTONE);
        mfBlockModels.createVerticalSlab(MFBlocks.STONE_BRICK_VERTICAL_SLAB.get(), Blocks.STONE_BRICKS);
        mfBlockModels.createSmoothStoneVerticalSlab();
        mfBlockModels.createVerticalSlab(MFBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB.get(), Blocks.MOSSY_STONE_BRICKS);
        mfBlockModels.createVerticalSlab(MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB.get(), Blocks.COBBLED_DEEPSLATE);
        mfBlockModels.createVerticalSlab(MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB.get(), Blocks.POLISHED_DEEPSLATE);
        mfBlockModels.createVerticalSlab(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB.get(), Blocks.DEEPSLATE_BRICKS);
        mfBlockModels.createVerticalSlab(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB.get(), Blocks.DEEPSLATE_TILES);

        mfBlockModels.createVerticalSlab(MFBlocks.GRANITE_VERTICAL_SLAB.get(), Blocks.GRANITE);
        mfBlockModels.createVerticalSlab(MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB.get(), Blocks.POLISHED_GRANITE);
        mfBlockModels.createVerticalSlab(MFBlocks.DIORITE_VERTICAL_SLAB.get(), Blocks.DIORITE);
        mfBlockModels.createVerticalSlab(MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB.get(), Blocks.POLISHED_DIORITE);
        mfBlockModels.createVerticalSlab(MFBlocks.ANDESITE_VERTICAL_SLAB.get(), Blocks.ANDESITE);
        mfBlockModels.createVerticalSlab(MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB.get(), Blocks.POLISHED_ANDESITE);
        mfBlockModels.createVerticalSlab(MFBlocks.TUFF_VERTICAL_SLAB.get(), Blocks.TUFF);
        mfBlockModels.createVerticalSlab(MFBlocks.POLISHED_TUFF_VERTICAL_SLAB.get(), Blocks.POLISHED_TUFF);
        mfBlockModels.createVerticalSlab(MFBlocks.TUFF_BRICK_VERTICAL_SLAB.get(), Blocks.TUFF_BRICKS);

        mfBlockModels.createVerticalSlab(MFBlocks.SANDSTONE_VERTICAL_SLAB.get(), Blocks.SANDSTONE);
        mfBlockModels.createVerticalSlab(MFBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB.get(), Blocks.SMOOTH_SANDSTONE);
        mfBlockModels.createVerticalSlab(MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB.get(), Blocks.CUT_SANDSTONE);
        mfBlockModels.createVerticalSlab(MFBlocks.RED_SANDSTONE_VERTICAL_SLAB.get(), Blocks.RED_SANDSTONE);
        mfBlockModels.createVerticalSlab(MFBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB.get(), Blocks.SMOOTH_RED_SANDSTONE);
        mfBlockModels.createVerticalSlab(MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB.get(), Blocks.CUT_RED_SANDSTONE);

        mfBlockModels.createVerticalSlab(MFBlocks.SULFUR_VERTICAL_SLAB.get(), Blocks.SULFUR);
        mfBlockModels.createVerticalSlab(MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB.get(), Blocks.POLISHED_SULFUR);
        mfBlockModels.createVerticalSlab(MFBlocks.SULFUR_BRICK_VERTICAL_SLAB.get(), Blocks.SULFUR_BRICKS);
        mfBlockModels.createVerticalSlab(MFBlocks.CINNABAR_VERTICAL_SLAB.get(), Blocks.CINNABAR);
        mfBlockModels.createVerticalSlab(MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB.get(), Blocks.POLISHED_CINNABAR);
        mfBlockModels.createVerticalSlab(MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB.get(), Blocks.CINNABAR_BRICKS);

        mfBlockModels.createVerticalSlab(MFBlocks.NETHER_BRICK_VERTICAL_SLAB.get(), Blocks.NETHER_BRICKS);
        mfBlockModels.createVerticalSlab(MFBlocks.RED_NETHER_BRICK_VERTICAL_SLAB.get(), Blocks.RED_NETHER_BRICKS);
        mfBlockModels.createVerticalSlab(MFBlocks.BLACKSTONE_VERTICAL_SLAB.get(), Blocks.BLACKSTONE);
        mfBlockModels.createVerticalSlab(MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB.get(), Blocks.POLISHED_BLACKSTONE);
        mfBlockModels.createVerticalSlab(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB.get(), Blocks.POLISHED_BLACKSTONE_BRICKS);
        mfBlockModels.createVerticalSlab(MFBlocks.END_STONE_BRICK_VERTICAL_SLAB.get(), Blocks.END_STONE_BRICKS);
        mfBlockModels.createVerticalSlab(MFBlocks.PURPUR_VERTICAL_SLAB.get(), Blocks.PURPUR_BLOCK);
        mfBlockModels.createVerticalSlab(MFBlocks.QUARTZ_VERTICAL_SLAB.get(), Blocks.QUARTZ_BLOCK);
        mfBlockModels.createVerticalSlab(MFBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB.get(), Blocks.SMOOTH_QUARTZ);

        mfBlockModels.createVerticalSlab(MFBlocks.PRISMARINE_VERTICAL_SLAB.get(), Blocks.PRISMARINE);
        mfBlockModels.createVerticalSlab(MFBlocks.PRISMARINE_BRICK_VERTICAL_SLAB.get(), Blocks.PRISMARINE_BRICKS);
        mfBlockModels.createVerticalSlab(MFBlocks.DARK_PRISMARINE_VERTICAL_SLAB.get(), Blocks.DARK_PRISMARINE);
        mfBlockModels.createVerticalSlab(MFBlocks.BRICK_VERTICAL_SLAB.get(), Blocks.BRICKS);
        mfBlockModels.createVerticalSlab(MFBlocks.MUD_BRICK_VERTICAL_SLAB.get(), Blocks.MUD_BRICKS);
        mfBlockModels.createVerticalSlab(MFBlocks.RESIN_BRICK_VERTICAL_SLAB.get(), Blocks.RESIN_BRICKS);

        mfBlockModels.createPillar(MFBlocks.TEST_PILLAR_BLOCK.get(), Blocks.OAK_PLANKS);
        mfBlockModels.createTemporalDilator(MFBlocks.TEMPORAL_DILATOR.get());

        WeatheringCopperCollection.STATES.forEach(
                state -> {
                    // Waxed variants reuse the weathering texture, so the texture source is the same for both
                    Block textureSource = Blocks.CUT_COPPER.weathering().pick(state);

                    mfBlockModels.createVerticalSlab(
                            MFBlocks.CUT_COPPER_VERTICAL_SLAB.weathering().pick(state).get(),
                            textureSource
                    );
                    mfBlockModels.createVerticalSlab(
                            MFBlocks.CUT_COPPER_VERTICAL_SLAB.waxed().pick(state).get(),
                            textureSource
                    );
                }
        );

        ColorCollection.VALUES.forEach( // This one call generates all the models for every wool colour, and dynamically adapts if Mojang ever adds a new wool colour to the game. No new code necessary at all!
                colour -> mfBlockModels.createVerticalSlab(
                        MFBlocks.WOOL_VERTICAL_SLAB.pick(colour).get(),
                        Blocks.WOOL.pick(colour)
                )
        );

        WoodTypeCollection.SETS.forEach(
                set -> mfBlockModels.createVerticalSlab(
                        MFBlocks.WOODEN_VERTICAL_SLAB.pick(set).get(),
                        MFBlocks.PLANKS.pick(set).get()
                )
        );

        WoodTypeCollection.SETS.forEach(
                set -> blockModels.createShelf(
                        MFBlocks.WOODEN_SHELF.pick(set).get(),
                        MFBlocks.STRIPPED_LOG.pick(set).get()
                )
        );

        WoodTypeCollection.SETS.forEach(
                type -> {
                    Block log = MFBlocks.LOG.pick(type).get();
                    Block wood = MFBlocks.WOOD.pick(type).get();
                    Block strippedLog = MFBlocks.STRIPPED_LOG.pick(type).get();
                    Block strippedWood = MFBlocks.STRIPPED_WOOD.pick(type).get();
                    blockModels.woodProvider(log).logWithHorizontal(log).wood(wood);
                    blockModels.woodProvider(strippedLog).logWithHorizontal(strippedLog).wood(strippedWood);
                }
        );

        ColorCollection.VALUES.forEach(
                colour -> mfBlockModels.createVerticalSlab(
                        MFBlocks.CONCRETE_VERTICAL_SLAB.pick(colour).get(),
                        Blocks.CONCRETE.pick(colour)
                )
        );

        WeatheringCopperCollection.STATES.forEach(
                state -> {
                    Block textureSource = Blocks.CUT_COPPER.weathering().pick(state);

                    mfBlockModels.createPillar(
                            MFBlocks.CUT_COPPER_PILLAR.weathering().pick(state).get(),
                            textureSource
                    );

                    mfBlockModels.createPillar(
                            MFBlocks.CUT_COPPER_PILLAR.waxed().pick(state).get(),
                            textureSource
                    );
                }
        );

        ColorCollection.VALUES.forEach(
                colour -> mfBlockModels.createPillar(
                        MFBlocks.CONCRETE_PILLAR.pick(colour).get(),
                        Blocks.CONCRETE.pick(colour)
                )
        );

        GemstoneCollection.GEMS.forEach(gem ->
                blockModels.family(MFBlocks.GEMSTONE_BLOCK.pick(gem).get())
                        .generateFor(MFBlockFamilies.getGemstoneFamily().pick(gem)));

        WoodTypeCollection.SETS.forEach(set ->
                blockModels.family(MFBlocks.PLANKS.pick(set).get())
                        .generateFor(MFBlockFamilies.getWoodFamily().pick(set)));
    }
}
