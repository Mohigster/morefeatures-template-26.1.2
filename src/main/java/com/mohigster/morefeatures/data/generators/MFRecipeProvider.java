package com.mohigster.morefeatures.data.generators;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.gemstone.GemstoneCollection;
import com.mohigster.morefeatures.block.collection.vanilla.VanillaWoodCollection;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.block.family.MFBlockFamilies;
import com.mohigster.morefeatures.data.tag.MFBlockItemTags;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.data.tag.MFItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.block.WeatheringCopperCollection;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MFRecipeProvider extends RecipeProvider {
    public MFRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @NullMarked
        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new MFRecipeProvider(registries, output);
        }

        @NullMarked
        @Override
        public String getName() {
            return "MoreFeatures Recipes";
        }
    }

    protected void generateForMFBlockFamilies(FeatureFlagSet flagSet) {
        MFBlockFamilies.getAllNonGemstoneFamilies()
                .forEach(family -> this.generateRecipes(family, flagSet));
    }

    @Override
    protected void buildRecipes(){

        this.generateForMFBlockFamilies(
                FeatureFlagSet.of(FeatureFlags.VANILLA)
        );

        // Aluminium recipes

        this.shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.ALUMINIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.ALUMINIUM_INGOT.get())
                .unlockedBy(getHasName(MFItems.ALUMINIUM_INGOT.get()), has(MFItems.ALUMINIUM_INGOT))
                .group("aluminium")
                .save(output);

        this.shapeless(RecipeCategory.MISC, MFItems.RAW_ALUMINIUM.get(), 9)
                .requires(MFBlocks.RAW_ALUMINIUM_BLOCK)
                .unlockedBy(getHasName(MFBlocks.RAW_ALUMINIUM_BLOCK.get()), has(MFBlocks.RAW_ALUMINIUM_BLOCK))
                .group("aluminium")
                .save(output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.RAW_ALUMINIUM_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.RAW_ALUMINIUM.get())
                .unlockedBy(getHasName(MFItems.RAW_ALUMINIUM.get()), has(MFItems.RAW_ALUMINIUM))
                .group("aluminium")
                .save(output);

        this.shapeless(RecipeCategory.MISC, MFItems.ALUMINIUM_INGOT.get(), 9)
                .requires(MFBlocks.ALUMINIUM_BLOCK)
                .unlockedBy(getHasName(MFBlocks.ALUMINIUM_BLOCK.get()), has(MFBlocks.ALUMINIUM_BLOCK))
                .group("aluminium")
                .save(output);

        // Magnesium recipes

        this.shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.RAW_MAGNESIUM_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.RAW_MAGNESIUM.get())
                .unlockedBy(getHasName(MFItems.RAW_MAGNESIUM.get()), has(MFItems.RAW_MAGNESIUM))
                .group("magnesium")
                .save(output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.MAGNESIUM_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.MAGNESIUM_INGOT.get())
                .unlockedBy(getHasName(MFItems.MAGNESIUM_INGOT.get()), has(MFItems.MAGNESIUM_INGOT))
                .group("magnesium")
                .save(output);

        this.shapeless(RecipeCategory.MISC, MFItems.RAW_MAGNESIUM.get(), 9)
                .requires(MFBlocks.RAW_MAGNESIUM_BLOCK)
                .unlockedBy(getHasName(MFBlocks.RAW_MAGNESIUM_BLOCK.get()), has(MFBlocks.RAW_MAGNESIUM_BLOCK))
                .group("magnesium")
                .save(output);

        this.shapeless(RecipeCategory.MISC, MFItems.MAGNESIUM_INGOT.get(), 9)
                .requires(MFBlocks.MAGNESIUM_BLOCK)
                .unlockedBy(getHasName(MFBlocks.MAGNESIUM_BLOCK.get()), has(MFBlocks.MAGNESIUM_BLOCK))
                .group("magnesium")
                .save(output);

        // Fluorite recipes

        GemstoneCollection.zipApply(MFBlocks.GEMSTONE_BLOCK, MFItems.GEM, this::simpleShaped);
        GemstoneCollection.zipApply(MFBlocks.RAW_GEM_BLOCK, MFItems.RAW_GEM, this::simpleShaped);
        GemstoneCollection.zipApply(MFItems.GEM, MFBlocks.GEMSTONE_BLOCK, this::simpleShapeless);
        GemstoneCollection.zipApply(MFItems.RAW_GEM, MFBlocks.RAW_GEM_BLOCK, this::simpleShapeless);
        GemstoneCollection.zipApply(MFBlocks.GEMSTONE_VERTICAL_SLAB, MFBlocks.GEMSTONE_BLOCK, this::verticalSlabCrafting);
        GemstoneCollection.zipApply(MFBlocks.GEMSTONE_VERTICAL_SLAB, MFBlocks.GEMSTONE_BLOCK, this::verticalSlabStonecutting);

        GemstoneCollection.GEMS.forEach(gem ->
                this.shapeless(RecipeCategory.MISC, MFItems.GEM.pick(gem).get(), 18)
                        .requires(MFBlocks.GEMSTONE_BLOCK.pick(gem))
                        .requires(Items.BLAZE_ROD)
                        .requires(Items.BREEZE_ROD)
                        .requires(MFItems.BRINE_ROD)
                        .unlockedBy(getHasName(MFBlocks.GEMSTONE_BLOCK.pick(gem)), has(MFBlocks.GEMSTONE_BLOCK.pick(gem)))
                        .group("gemstone")
                        .save(output, "morefeatures:" + gem.getName() + "_from_blaze_rod_and_breeze_rod_and_brine_rod"));

        // Wood recipes

        WoodTypeCollection.zipApply(MFBlocks.PLANKS, MFBlockItemTags.LOGS, this::planksFromLogs);

        WoodTypeCollection.zipApply(MFBlocks.WOOD, MFBlocks.LOG, this::woodFromLogs);

        WoodTypeCollection.zipApply(MFBlocks.STRIPPED_WOOD, MFBlocks.STRIPPED_LOG, this::woodFromLogs);

        // Bismuth recipes

        this.shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.BISMUTH_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.BISMUTH.get())
                .unlockedBy(getHasName(MFItems.BISMUTH.get()), has(MFItems.BISMUTH))
                .group("bismuth")
                .save(output);

        this.shapeless(RecipeCategory.MISC, MFItems.BISMUTH.get(), 9)
                .requires(MFBlocks.BISMUTH_BLOCK)
                .unlockedBy(getHasName(MFBlocks.BISMUTH_BLOCK.get()), has(MFBlocks.BISMUTH_BLOCK))
                .group("bismuth")
                .save(output);

        this.shapeless(RecipeCategory.MISC, MFItems.RAW_BISMUTH.get(), 9)
                .requires(MFBlocks.RAW_BISMUTH_BLOCK)
                .unlockedBy(getHasName(MFBlocks.RAW_BISMUTH_BLOCK.get()), has(MFBlocks.RAW_BISMUTH_BLOCK))
                .group("bismuth")
                .save(output);

        this.shapeless(RecipeCategory.MISC, MFItems.BISMUTH.get(), 18)
                .requires(MFBlocks.BISMUTH_BLOCK)
                .requires(Items.BLAZE_ROD)
                .requires(Items.BREEZE_ROD)
                .requires(MFItems.BRINE_ROD)
                .unlockedBy(getHasName(MFBlocks.BISMUTH_BLOCK.get()), has(MFBlocks.BISMUTH_BLOCK))
                .group("bismuth")
                .save(output, "morefeatures:bismuth_from_blaze_rod_and_breeze_rod_and_brine_rod");

        this.shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.RAW_BISMUTH_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.RAW_BISMUTH.get())
                .unlockedBy(getHasName(MFItems.RAW_BISMUTH.get()), has(MFItems.RAW_BISMUTH))
                .group("bismuth")
                .save(output);

        this.shaped(RecipeCategory.MISC, MFItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("NBN")
                .pattern("NEN")
                .pattern("NNN")
                .define('B', MFItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE.get())
                .define('N', Items.NETHERITE_SCRAP)
                .define('E', Blocks.END_STONE.asItem())
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                .group("bismuth")
                .save(output);

        this.shapeless(RecipeCategory.MISC, MFItems.BISMUTH.get())
                .requires(MFItems.BISMUTH_SCRAP, 4)
                .requires(Items.DIAMOND, 4)
                .unlockedBy(getHasName(MFItems.BISMUTH_SCRAP.get()), has(MFItems.BISMUTH_SCRAP))
                .group("bismuth")
                .save(output, "morefeatures:bismuth_from_bismuth_scraps_and_diamonds");

        // Magic block recipe

        this.shaped(RecipeCategory.MISC, MFBlocks.MAGIC_BLOCK)
                .pattern("DBD")
                .pattern("BAB")
                .pattern("NFN")
                .define('B', MFItems.BISMUTH)
                .define('N', Items.NETHERITE_SCRAP)
                .define('A', MFItems.GEM.azurite())
                .define('F', MFItems.GEM.fluorite())
                .define('D', Items.DIAMOND)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                .group("magic")
                .save(output);

        // Metal detector recipe

        this.shaped(RecipeCategory.TOOLS, MFItems.METAL_DETECTOR)
                .pattern("  S")
                .pattern("MS ")
                .pattern("BMI")
                .define('B', MFItems.BISMUTH.get())
                .define('S', Items.STICK)
                .define('M', MFItems.MAGNESIUM_INGOT.get())
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(MFItems.MAGNESIUM_INGOT.get()), has(MFItems.MAGNESIUM_INGOT))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .group("metal_detector")
                .save(output);

        // Carbon Recipes

        this.shaped(RecipeCategory.COMBAT, MFItems.CARBON_BOW)
                .pattern("CCC")
                .pattern("CBC")
                .pattern("CRC")
                .define('C', MFItems.CARBON_FIBER.get())
                .define('B', Items.BOW)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(MFItems.CARBON_FIBER.get()), has(MFItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        this.shaped(RecipeCategory.COMBAT, MFItems.CARBON_TRIDENT)
                .pattern("CCC")
                .pattern("CTC")
                .pattern("CRC")
                .define('C', MFItems.CARBON_FIBER.get())
                .define('T', Items.TRIDENT)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(MFItems.CARBON_FIBER.get()), has(MFItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        this.shaped(RecipeCategory.COMBAT, MFItems.CARBON_CROSSBOW)
                .pattern("CCC")
                .pattern("CBC")
                .pattern("CRC")
                .define('C', MFItems.CARBON_FIBER.get())
                .define('B', Items.CROSSBOW)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(MFItems.CARBON_FIBER.get()), has(MFItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        this.shaped(RecipeCategory.COMBAT, MFItems.CARBON_ELYTRA)
                .pattern("CCC")
                .pattern("CEC")
                .pattern("CRC")
                .define('C', MFItems.CARBON_FIBER.get())
                .define('E', Items.ELYTRA)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(MFItems.CARBON_FIBER.get()), has(MFItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        this.shaped(RecipeCategory.COMBAT, MFItems.CARBON_WOLF_ARMOR)
                .pattern("CCC")
                .pattern("CWC")
                .pattern("CRC")
                .define('C', MFItems.CARBON_FIBER.get())
                .define('W', Items.WOLF_ARMOR)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(MFItems.CARBON_FIBER.get()), has(MFItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        // Compressor block recipe

        this.shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.COMPRESSOR_BLOCK)
                .pattern("AMA")
                .pattern("MNM")
                .pattern("ANA")
                .define('A', MFItems.ALUMINIUM_INGOT)
                .define('M', MFItems.MAGNESIUM_INGOT)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group("compressor_block")
                .save(output);

        // Boats and chest boats

        this.woodenBoat(MFItems.BLOODWOOD_BOAT, MFBlocks.PLANKS.bloodwood());
        this.woodenBoat(MFItems.TAINTED_BOAT, MFBlocks.PLANKS.tainted());
        this.woodenBoat(MFItems.PALM_BOAT, MFBlocks.PLANKS.palm());

        this.chestBoat(MFItems.BLOODWOOD_CHEST_BOAT, MFItems.BLOODWOOD_BOAT);
        this.chestBoat(MFItems.TAINTED_CHEST_BOAT, MFItems.TAINTED_BOAT);
        this.chestBoat(MFItems.PALM_CHEST_BOAT, MFItems.PALM_BOAT);

        /*
         * Azurite and fluorite do not use their blocks for all of their recipes,
         * but a block family would assume that they do. Because of this, the
         * recipes are generated separately, not using generateForBlockFamilies()
         */

        // Stairs and slabs

        GemstoneCollection.zipApply(MFBlocks.GEMSTONE_STAIRS, MFBlocks.GEMSTONE_BLOCK, this::stairs);

        this.slab(RecipeCategory.BUILDING_BLOCKS, MFBlocks.GEMSTONE_SLAB.azurite().get(), MFBlocks.GEMSTONE_BLOCK.azurite().get());
        this.slab(RecipeCategory.BUILDING_BLOCKS, MFBlocks.GEMSTONE_SLAB.fluorite().get(), MFBlocks.GEMSTONE_BLOCK.fluorite().get());

        // Buttons and pressure plates

        GemstoneCollection.zipApply(MFBlocks.GEMSTONE_PRESSURE_PLATE, MFItems.GEM, this::pressurePlate);

        this.buttonBuilder(MFBlocks.GEMSTONE_BUTTON.azurite().get(), Ingredient.of(MFItems.GEM.azurite()))
                .group("azurite")
                .unlockedBy(getHasName(MFItems.GEM.azurite().get()), has(MFItems.GEM.azurite().get()))
                .save(output);

        this.buttonBuilder(MFBlocks.GEMSTONE_BUTTON.fluorite().get(), Ingredient.of(MFItems.GEM.fluorite()))
                .group("fluorite")
                .unlockedBy(getHasName(MFItems.GEM.fluorite().get()), has(MFItems.GEM.fluorite().get()))
                .save(output);

        // Doors and trapdoors

        this.doorBuilder(MFBlocks.GEMSTONE_DOOR.azurite().get(), Ingredient.of(MFItems.GEM.azurite()))
                .group("azurite")
                .unlockedBy(getHasName(MFItems.GEM.azurite().get()), has(MFItems.GEM.azurite().get()))
                .save(output);
        this.trapdoorBuilder(MFBlocks.GEMSTONE_TRAPDOOR.azurite().get(), Ingredient.of(MFItems.GEM.azurite()))
                .group("azurite")
                .unlockedBy(getHasName(MFItems.GEM.azurite().get()), has(MFItems.GEM.azurite().get()))
                .save(output);

        this.doorBuilder(MFBlocks.GEMSTONE_DOOR.fluorite().get(), Ingredient.of(MFItems.GEM.fluorite()))
                .group("fluorite")
                .unlockedBy(getHasName(MFItems.GEM.fluorite().get()), has(MFItems.GEM.fluorite().get()))
                .save(output);
        this.trapdoorBuilder(MFBlocks.GEMSTONE_TRAPDOOR.fluorite().get(), Ingredient.of(MFItems.GEM.fluorite()))
                .group("fluorite")
                .unlockedBy(getHasName(MFItems.GEM.fluorite().get()), has(MFItems.GEM.fluorite().get()))
                .save(output);

        GemstoneCollection.GEMS.forEach(
                gem -> this.gemstoneSignSet(
                        MFItems.GEMSTONE_SIGN.pick(gem),
                        MFItems.GEMSTONE_HANGING_SIGN.pick(gem),
                        MFItems.RAW_GEM.pick(gem)
                )
        );

        // Fences and Fence Gates

        GemstoneCollection.GEMS.forEach(
                gem -> this.gemstoneFenceSet(
                        MFBlocks.GEMSTONE_FENCE.pick(gem),
                        MFBlocks.GEMSTONE_FENCE_GATE.pick(gem),
                        MFBlocks.GEMSTONE_BLOCK.pick(gem),
                        MFItems.GEM.pick(gem)
                )
        );

        // Walls

        GemstoneCollection.zipApply(MFBlocks.GEMSTONE_WALL, MFBlocks.GEMSTONE_BLOCK, this::wall);

        // Bismuth smithing recipes

        this.bismuthSmithing(MFItems.CARBON_BOW.get(), RecipeCategory.COMBAT, MFItems.BISMUTH_BOW.get());
        this.bismuthSmithing(MFItems.CARBON_TRIDENT.get(), RecipeCategory.COMBAT, MFItems.BISMUTH_TRIDENT.get());
        this.bismuthSmithing(Items.NETHERITE_NAUTILUS_ARMOR, RecipeCategory.COMBAT, MFItems.BISMUTH_NAUTILUS_ARMOR.get());
        this.bismuthSmithing(Items.NETHERITE_HORSE_ARMOR, RecipeCategory.COMBAT, MFItems.BISMUTH_HORSE_ARMOR.get());
        this.bismuthSmithing(Items.NETHERITE_SPEAR, RecipeCategory.COMBAT, MFItems.BISMUTH_SPEAR.get());
        this.bismuthSmithing(Items.NETHERITE_AXE, RecipeCategory.COMBAT, MFItems.BISMUTH_AXE.get());
        this.bismuthSmithing(Items.NETHERITE_SHOVEL, RecipeCategory.COMBAT, MFItems.BISMUTH_SHOVEL.get());
        this.bismuthSmithing(Items.NETHERITE_HOE, RecipeCategory.COMBAT, MFItems.BISMUTH_HOE.get());
        this.bismuthSmithing(Items.NETHERITE_SWORD, RecipeCategory.COMBAT, MFItems.BISMUTH_SWORD.get());
        this.bismuthSmithing(Items.NETHERITE_PICKAXE, RecipeCategory.COMBAT, MFItems.BISMUTH_PICKAXE.get());
        this.bismuthSmithing(Items.NETHERITE_HELMET, RecipeCategory.COMBAT, MFItems.BISMUTH_HELMET.get());
        this.bismuthSmithing(Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, MFItems.BISMUTH_CHESTPLATE.get());
        this.bismuthSmithing(Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, MFItems.BISMUTH_LEGGINGS.get());
        this.bismuthSmithing(Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, MFItems.BISMUTH_BOOTS.get());

        //—————————————————————————————SMELTABLE LISTS———————————————————————————————

        List<ItemLike> ALUMINIUM_SMELTABLES = List.of(MFItems.RAW_ALUMINIUM, MFBlocks.ALUMINIUM_ORE, MFBlocks.DEEPSLATE_ALUMINIUM_ORE);
        List<ItemLike> MAGNESIUM_SMELTABLES = List.of(MFItems.RAW_MAGNESIUM, MFBlocks.MAGNESIUM_ORE, MFBlocks.DEEPSLATE_MAGNESIUM_ORE);
        List<ItemLike> AZURITE_SMELTABLES = List.of(MFItems.RAW_GEM.azurite(), MFBlocks.ORE.azurite(), MFBlocks.DEEPSLATE_ORE.azurite(), MFBlocks.NETHER_ORE.azurite(), MFBlocks.END_ORE.azurite());
        List<ItemLike> FLUORITE_SMELTABLES = List.of(MFItems.RAW_GEM.fluorite(), MFBlocks.ORE.fluorite(), MFBlocks.DEEPSLATE_ORE.fluorite(), MFBlocks.NETHER_ORE.fluorite(), MFBlocks.END_ORE.fluorite());
        List<ItemLike> BISMUTH_SMELTABLES = List.of(MFItems.RAW_BISMUTH, MFBlocks.BISMUTH_ORE);

        //——————————————————————SMELTING RECIPE DATA GENERATION——————————————————————

        // Aluminium
        this.oreSmelting(ALUMINIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.ALUMINIUM_INGOT.get(), 0.25f, 200, "aluminium");
        this.oreBlasting(ALUMINIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.ALUMINIUM_INGOT.get(), 0.25f, 100, "aluminium");

        // Magnesium
        this.oreSmelting(MAGNESIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.MAGNESIUM_INGOT.get(), 0.25f, 200, "magnesium");
        this.oreBlasting(MAGNESIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.MAGNESIUM_INGOT.get(), 0.25f, 100, "magnesium");

        // Azurite
        this.oreSmelting(AZURITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.GEM.azurite().get(), 0.25f, 200, "azurite");
        this.oreBlasting(AZURITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.GEM.azurite().get(), 0.25f, 100, "azurite");

        // Fluorite
        this.oreSmelting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.GEM.fluorite().get(), 0.25f, 200, "fluorite");
        this.oreBlasting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.GEM.fluorite().get(), 0.25f, 100, "fluorite");

        // Bismuth
        this.oreSmelting(BISMUTH_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.BISMUTH_SCRAP.get(), 0.25f, 200, "bismuth");
        this.oreBlasting(BISMUTH_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.BISMUTH_SCRAP.get(), 0.25f, 100, "bismuth");

        //—————————————————————————————VERTICAL SLABS TABLE———————————————————————————

        VanillaWoodCollection.SETS.forEach(set -> this.verticalSlabCrafting(MFBlocks.VANILLA_WOOD_VERTICAL_SLAB.pick(set), set.planks().get()));


        this.verticalSlabCrafting(MFBlocks.STONE_VERTICAL_SLAB, Blocks.STONE);
        this.verticalSlabCrafting(MFBlocks.COBBLESTONE_VERTICAL_SLAB, Blocks.COBBLESTONE);
        this.verticalSlabCrafting(MFBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB, Blocks.MOSSY_COBBLESTONE);
        this.verticalSlabCrafting(MFBlocks.SMOOTH_STONE_VERTICAL_SLAB, Blocks.SMOOTH_STONE);
        this.verticalSlabCrafting(MFBlocks.STONE_BRICK_VERTICAL_SLAB, Blocks.STONE_BRICKS);
        this.verticalSlabCrafting(MFBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB, Blocks.MOSSY_STONE_BRICKS);
        this.verticalSlabCrafting(MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB, Blocks.COBBLED_DEEPSLATE);
        this.verticalSlabCrafting(MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);
        this.verticalSlabCrafting(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.DEEPSLATE_BRICKS);
        this.verticalSlabCrafting(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.DEEPSLATE_TILES);

        this.verticalSlabCrafting(MFBlocks.GRANITE_VERTICAL_SLAB, Blocks.GRANITE);
        this.verticalSlabCrafting(MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB, Blocks.POLISHED_GRANITE);
        this.verticalSlabCrafting(MFBlocks.DIORITE_VERTICAL_SLAB, Blocks.DIORITE);
        this.verticalSlabCrafting(MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB, Blocks.POLISHED_DIORITE);
        this.verticalSlabCrafting(MFBlocks.ANDESITE_VERTICAL_SLAB, Blocks.ANDESITE);
        this.verticalSlabCrafting(MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB, Blocks.POLISHED_ANDESITE);
        this.verticalSlabCrafting(MFBlocks.TUFF_VERTICAL_SLAB, Blocks.TUFF);
        this.verticalSlabCrafting(MFBlocks.POLISHED_TUFF_VERTICAL_SLAB, Blocks.POLISHED_TUFF);
        this.verticalSlabCrafting(MFBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.TUFF_BRICKS);

        this.verticalSlabCrafting(MFBlocks.SANDSTONE_VERTICAL_SLAB, Blocks.SANDSTONE);
        this.verticalSlabCrafting(MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_SANDSTONE);
        this.verticalSlabCrafting(MFBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_SANDSTONE);
        this.verticalSlabCrafting(MFBlocks.RED_SANDSTONE_VERTICAL_SLAB, Blocks.RED_SANDSTONE);
        this.verticalSlabCrafting(MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_RED_SANDSTONE);
        this.verticalSlabCrafting(MFBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_RED_SANDSTONE);

        this.verticalSlabCrafting(MFBlocks.SULFUR_VERTICAL_SLAB, Blocks.SULFUR);
        this.verticalSlabCrafting(MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB, Blocks.POLISHED_SULFUR);
        this.verticalSlabCrafting(MFBlocks.SULFUR_BRICK_VERTICAL_SLAB, Blocks.SULFUR_BRICKS);
        this.verticalSlabCrafting(MFBlocks.CINNABAR_VERTICAL_SLAB, Blocks.CINNABAR);
        this.verticalSlabCrafting(MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB, Blocks.POLISHED_CINNABAR);
        this.verticalSlabCrafting(MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB, Blocks.CINNABAR_BRICKS);

        this.verticalSlabCrafting(MFBlocks.NETHER_BRICK_VERTICAL_SLAB, Blocks.NETHER_BRICKS);
        this.verticalSlabCrafting(MFBlocks.RED_NETHER_BRICK_VERTICAL_SLAB, Blocks.RED_NETHER_BRICKS);
        this.verticalSlabCrafting(MFBlocks.BLACKSTONE_VERTICAL_SLAB, Blocks.BLACKSTONE);
        this.verticalSlabCrafting(MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE);
        this.verticalSlabCrafting(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_BRICKS);
        this.verticalSlabCrafting(MFBlocks.END_STONE_BRICK_VERTICAL_SLAB, Blocks.END_STONE_BRICKS);
        this.verticalSlabCrafting(MFBlocks.PURPUR_VERTICAL_SLAB, Blocks.PURPUR_BLOCK);
        this.verticalSlabCrafting(MFBlocks.QUARTZ_VERTICAL_SLAB, Blocks.QUARTZ_BLOCK);
        this.verticalSlabCrafting(MFBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB, Blocks.SMOOTH_QUARTZ);

        this.verticalSlabCrafting(MFBlocks.PRISMARINE_VERTICAL_SLAB, Blocks.PRISMARINE);
        this.verticalSlabCrafting(MFBlocks.PRISMARINE_BRICK_VERTICAL_SLAB, Blocks.PRISMARINE_BRICKS);
        this.verticalSlabCrafting(MFBlocks.DARK_PRISMARINE_VERTICAL_SLAB, Blocks.DARK_PRISMARINE);
        this.verticalSlabCrafting(MFBlocks.BRICK_VERTICAL_SLAB, Blocks.BRICKS);
        this.verticalSlabCrafting(MFBlocks.MUD_BRICK_VERTICAL_SLAB, Blocks.MUD_BRICKS);
        this.verticalSlabCrafting(MFBlocks.RESIN_BRICK_VERTICAL_SLAB, Blocks.RESIN_BRICKS);

        // Generates the recipe for all states that cut copper vertical slabs can come in, for stonecutting from a regular copper block or a cut copper block, AND the crafting table recipe

        WeatheringCopperCollection.zipApply(MFBlocks.CUT_COPPER_VERTICAL_SLAB, Blocks.COPPER_BLOCK, this::verticalSlabStonecuttingFromCopper);
        WeatheringCopperCollection.zipApply(MFBlocks.CUT_COPPER_VERTICAL_SLAB, Blocks.CUT_COPPER, this::verticalSlabStonecutting);
        WeatheringCopperCollection.zipApply(MFBlocks.CUT_COPPER_VERTICAL_SLAB, Blocks.CUT_COPPER, this::verticalSlabCrafting);

        WoodTypeCollection.zipApply(MFBlocks.WOODEN_VERTICAL_SLAB, MFBlocks.PLANKS, this::verticalSlabCrafting);

        // And this line generates the recipe for all different colours of wool vertical slabs!
        ColorCollection.zipApply(MFBlocks.WOOL_VERTICAL_SLAB, Blocks.WOOL, this::verticalSlabCrafting);
        ColorCollection.zipApply(MFBlocks.CONCRETE_VERTICAL_SLAB, Blocks.CONCRETE, this::verticalSlabCrafting);

        //——————————————————————————VERTICAL SLABS STONECUTTING————————————————————————

        /* All recipes mimic an identical recipe for a horizontal slab */
        this.verticalSlabStonecutting(MFBlocks.STONE_VERTICAL_SLAB, Blocks.STONE);
        this.verticalSlabStonecutting(MFBlocks.COBBLESTONE_VERTICAL_SLAB, Blocks.STONE);
        this.verticalSlabStonecutting(MFBlocks.STONE_BRICK_VERTICAL_SLAB, Blocks.STONE);
        this.verticalSlabStonecutting(MFBlocks.COBBLESTONE_VERTICAL_SLAB, Blocks.COBBLESTONE);
        this.verticalSlabStonecutting(MFBlocks.STONE_BRICK_VERTICAL_SLAB, Blocks.STONE_BRICKS);
        this.verticalSlabStonecutting(MFBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB, Blocks.MOSSY_COBBLESTONE);
        this.verticalSlabStonecutting(MFBlocks.SMOOTH_STONE_VERTICAL_SLAB, Blocks.SMOOTH_STONE);
        this.verticalSlabStonecutting(MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB, Blocks.DEEPSLATE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB, Blocks.DEEPSLATE);
        this.verticalSlabStonecutting(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.DEEPSLATE);
        this.verticalSlabStonecutting(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.DEEPSLATE);
        this.verticalSlabStonecutting(MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB, Blocks.COBBLED_DEEPSLATE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);
        this.verticalSlabStonecutting(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.DEEPSLATE_BRICKS);
        this.verticalSlabStonecutting(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.DEEPSLATE_TILES);
        this.verticalSlabStonecutting(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);
        this.verticalSlabStonecutting(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);

        this.verticalSlabStonecutting(MFBlocks.GRANITE_VERTICAL_SLAB, Blocks.GRANITE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB, Blocks.GRANITE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB, Blocks.POLISHED_GRANITE);
        this.verticalSlabStonecutting(MFBlocks.DIORITE_VERTICAL_SLAB, Blocks.DIORITE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB, Blocks.DIORITE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB, Blocks.POLISHED_DIORITE);
        this.verticalSlabStonecutting(MFBlocks.ANDESITE_VERTICAL_SLAB, Blocks.ANDESITE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB, Blocks.ANDESITE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB, Blocks.POLISHED_ANDESITE);
        this.verticalSlabStonecutting(MFBlocks.TUFF_VERTICAL_SLAB, Blocks.TUFF);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_TUFF_VERTICAL_SLAB, Blocks.TUFF);
        this.verticalSlabStonecutting(MFBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.TUFF);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_TUFF_VERTICAL_SLAB, Blocks.POLISHED_TUFF);
        this.verticalSlabStonecutting(MFBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.TUFF_BRICKS);
        this.verticalSlabStonecutting(MFBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.POLISHED_TUFF);

        this.verticalSlabStonecutting(MFBlocks.SANDSTONE_VERTICAL_SLAB, Blocks.SANDSTONE);
        this.verticalSlabStonecutting(MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB, Blocks.SANDSTONE);
        this.verticalSlabStonecutting(MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_SANDSTONE);
        this.verticalSlabStonecutting(MFBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_SANDSTONE);
        this.verticalSlabStonecutting(MFBlocks.RED_SANDSTONE_VERTICAL_SLAB, Blocks.RED_SANDSTONE);
        this.verticalSlabStonecutting(MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB, Blocks.RED_SANDSTONE);
        this.verticalSlabStonecutting(MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_RED_SANDSTONE);
        this.verticalSlabStonecutting(MFBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_RED_SANDSTONE);

        this.verticalSlabStonecutting(MFBlocks.SULFUR_VERTICAL_SLAB, Blocks.SULFUR);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB, Blocks.SULFUR);
        this.verticalSlabStonecutting(MFBlocks.SULFUR_BRICK_VERTICAL_SLAB, Blocks.SULFUR);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB, Blocks.POLISHED_SULFUR);
        this.verticalSlabStonecutting(MFBlocks.SULFUR_BRICK_VERTICAL_SLAB, Blocks.SULFUR_BRICKS);
        this.verticalSlabStonecutting(MFBlocks.CINNABAR_VERTICAL_SLAB, Blocks.CINNABAR);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB, Blocks.CINNABAR);
        this.verticalSlabStonecutting(MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB, Blocks.CINNABAR);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB, Blocks.POLISHED_CINNABAR);
        this.verticalSlabStonecutting(MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB, Blocks.CINNABAR_BRICKS);

        this.verticalSlabStonecutting(MFBlocks.NETHER_BRICK_VERTICAL_SLAB, Blocks.NETHER_BRICKS);
        this.verticalSlabStonecutting(MFBlocks.RED_NETHER_BRICK_VERTICAL_SLAB, Blocks.RED_NETHER_BRICKS);
        this.verticalSlabStonecutting(MFBlocks.BLACKSTONE_VERTICAL_SLAB, Blocks.BLACKSTONE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB, Blocks.BLACKSTONE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.BLACKSTONE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE);
        this.verticalSlabStonecutting(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_BRICKS);
        this.verticalSlabStonecutting(MFBlocks.END_STONE_BRICK_VERTICAL_SLAB, Blocks.END_STONE);
        this.verticalSlabStonecutting(MFBlocks.END_STONE_BRICK_VERTICAL_SLAB, Blocks.END_STONE_BRICKS);
        this.verticalSlabStonecutting(MFBlocks.PURPUR_VERTICAL_SLAB, Blocks.PURPUR_BLOCK);
        this.verticalSlabStonecutting(MFBlocks.QUARTZ_VERTICAL_SLAB, Blocks.QUARTZ_BLOCK);
        this.verticalSlabStonecutting(MFBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB, Blocks.SMOOTH_QUARTZ);

        this.verticalSlabStonecutting(MFBlocks.PRISMARINE_VERTICAL_SLAB, Blocks.PRISMARINE);
        this.verticalSlabStonecutting(MFBlocks.PRISMARINE_BRICK_VERTICAL_SLAB, Blocks.PRISMARINE_BRICKS);
        this.verticalSlabStonecutting(MFBlocks.DARK_PRISMARINE_VERTICAL_SLAB, Blocks.DARK_PRISMARINE);
        this.verticalSlabStonecutting(MFBlocks.BRICK_VERTICAL_SLAB, Blocks.BRICKS);
        this.verticalSlabStonecutting(MFBlocks.MUD_BRICK_VERTICAL_SLAB, Blocks.MUD_BRICKS);
        this.verticalSlabStonecutting(MFBlocks.RESIN_BRICK_VERTICAL_SLAB, Blocks.RESIN_BRICKS);
    }


    @NullMarked
    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> smeltables,
                                                                RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result,
                                                                float experience, int cookingTime, String group, String fromDesc) {
        for(ItemLike itemlike : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), craftingCategory, cookingCategory, result, experience, cookingTime, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(this.output, MoreFeatures.MODID + ":" + getItemName(result) + fromDesc + "_" + getItemName(itemlike));
        }
    }

    @SuppressWarnings("SameParameterValue")
    protected void bismuthSmithing(Item base, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(MFItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(base),
                this.tag(MFItemTags.BISMUTH_TOOL_MATERIALS),
                category, result).unlocks("has_bismuth_ingot", this.has(MFItemTags.BISMUTH_TOOL_MATERIALS)).save(output, MoreFeatures.MODID + ":" + getItemName(result) + "_smithing");
    }

    protected void verticalSlabCrafting(ItemLike verticalSlab, ItemLike fullBlock) {
        shaped(RecipeCategory.BUILDING_BLOCKS, verticalSlab, 6)
                .pattern("B")
                .pattern("B")
                .pattern("B")
                .define('B', fullBlock)
                .unlockedBy(getHasName(fullBlock), has(fullBlock))
                .save(output);
    }

    protected void verticalSlabStonecutting(ItemLike verticalSlab, ItemLike fullBlock) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(fullBlock), RecipeCategory.BUILDING_BLOCKS, verticalSlab, 2)
                .unlockedBy(getHasName(fullBlock), has(fullBlock))
                .save(output, MoreFeatures.MODID + ":" + getConversionRecipeName(verticalSlab, fullBlock) + "_stonecutting");
    }

    protected void verticalSlabStonecuttingFromCopper(ItemLike verticalSlab, ItemLike fullBlock) {
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(fullBlock), RecipeCategory.BUILDING_BLOCKS, verticalSlab, 8)
                .unlockedBy(getHasName(fullBlock), has(fullBlock))
                .save(output, MoreFeatures.MODID + ":" + getConversionRecipeName(verticalSlab, fullBlock) + "_stonecutting");
    }

    protected void gemstoneFenceSet(ItemLike fence, ItemLike gate, ItemLike base, ItemLike actingStick){
        String hasName = getHasName(actingStick);

        this.specialFenceBuilder(fence, Ingredient.of(base), Ingredient.of(actingStick)).group("gemstone").unlockedBy(hasName, has(actingStick)).save(output);
        this.specialFenceGateBuilder(gate, Ingredient.of(base), Ingredient.of(actingStick)).group("gemstone").unlockedBy(hasName, has(actingStick)).save(output);
    }

    protected void gemstoneSignSet(ItemLike sign, ItemLike hanging, ItemLike base){
        String hasName = getHasName(base);

        this.signBuilder(sign, Ingredient.of(base)).group("gemstone").unlockedBy(hasName, has(base)).save(output);
        this.hangingSignBuilder(hanging, Ingredient.of(base)).group("gemstone").unlockedBy(hasName, has(base)).save(output);
    }

    protected RecipeBuilder specialFenceBuilder(ItemLike result, Ingredient base, Ingredient actingStick) {
        return this.shaped(RecipeCategory.DECORATIONS, result, 3).define('W', base).define('#', actingStick).pattern("W#W").pattern("W#W");
    }

    protected RecipeBuilder specialFenceGateBuilder(ItemLike result, Ingredient block, Ingredient actingStick) {
        return this.shaped(RecipeCategory.REDSTONE, result).define('#', actingStick).define('W', block).pattern("#W#").pattern("#W#");
    }

    protected void planksFromLogs(ItemLike result, BlockItemTagId logTag) {
        this.planksFromLogs(result, logTag.item(), 4);
    }

    protected void simpleShaped(ItemLike result, ItemLike ingredient) {
        this.shaped(RecipeCategory.BUILDING_BLOCKS, result)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .group("gemstone")
                .save(output);
    }

    protected void simpleShapeless(ItemLike result, ItemLike ingredient) {
        this.shapeless(RecipeCategory.BUILDING_BLOCKS, result, 9)
                .requires(ingredient)
                .unlockedBy(getHasName(ingredient), has(ingredient))
                .group("gemstone")
                .save(output);
    }

    protected void wall(ItemLike result, ItemLike base) {
        this.wall(RecipeCategory.DECORATIONS, result, base);
    }

    protected void stairs(ItemLike result, ItemLike base) {
        this.stairBuilder(result, Ingredient.of(base))
                .unlockedBy(getHasName(result), has(result))
                .group("gemstone").save(output);
    }
}

