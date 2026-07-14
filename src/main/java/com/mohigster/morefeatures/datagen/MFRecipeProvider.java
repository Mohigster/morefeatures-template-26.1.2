package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.family.MFBlockFamilies;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.tag.MFItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
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

    protected void generateForBlockFamilies(FeatureFlagSet flagSet) {
        MFBlockFamilies.getAllFamilies()
                .forEach(family -> this.generateRecipes(family, flagSet));
    }

    @Override
    protected void buildRecipes(){

        generateForBlockFamilies(
                FeatureFlagSet.of(FeatureFlags.VANILLA)
        );

        // Aluminium recipes

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.ALUMINIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.ALUMINIUM_INGOT.get())
                .unlockedBy(getHasName(MFItems.ALUMINIUM_INGOT.get()), has(MFItems.ALUMINIUM_INGOT))
                .group("aluminium")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.RAW_ALUMINIUM.get(), 9)
                .requires(MFBlocks.RAW_ALUMINIUM_BLOCK)
                .unlockedBy(getHasName(MFBlocks.RAW_ALUMINIUM_BLOCK.get()), has(MFBlocks.RAW_ALUMINIUM_BLOCK))
                .group("aluminium")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.RAW_ALUMINIUM_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.RAW_ALUMINIUM.get())
                .unlockedBy(getHasName(MFItems.RAW_ALUMINIUM.get()), has(MFItems.RAW_ALUMINIUM))
                .group("aluminium")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.ALUMINIUM_INGOT.get(), 9)
                .requires(MFBlocks.ALUMINIUM_BLOCK)
                .unlockedBy(getHasName(MFBlocks.ALUMINIUM_BLOCK.get()), has(MFBlocks.ALUMINIUM_BLOCK))
                .group("aluminium")
                .save(output);

        // Magnesium recipes

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.RAW_MAGNESIUM_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.RAW_MAGNESIUM.get())
                .unlockedBy(getHasName(MFItems.RAW_MAGNESIUM.get()), has(MFItems.RAW_MAGNESIUM))
                .group("magnesium")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.MAGNESIUM_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.MAGNESIUM_INGOT.get())
                .unlockedBy(getHasName(MFItems.MAGNESIUM_INGOT.get()), has(MFItems.MAGNESIUM_INGOT))
                .group("magnesium")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.RAW_MAGNESIUM.get(), 9)
                .requires(MFBlocks.RAW_MAGNESIUM_BLOCK)
                .unlockedBy(getHasName(MFBlocks.RAW_MAGNESIUM_BLOCK.get()), has(MFBlocks.RAW_MAGNESIUM_BLOCK))
                .group("magnesium")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.MAGNESIUM_INGOT.get(), 9)
                .requires(MFBlocks.MAGNESIUM_BLOCK)
                .unlockedBy(getHasName(MFBlocks.MAGNESIUM_BLOCK.get()), has(MFBlocks.MAGNESIUM_BLOCK))
                .group("magnesium")
                .save(output);

        // Azurite recipes

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.RAW_AZURITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.RAW_AZURITE.get())
                .unlockedBy(getHasName(MFItems.RAW_AZURITE.get()), has(MFItems.RAW_AZURITE))
                .group("azurite")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.AZURITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.AZURITE.get())
                .unlockedBy(getHasName(MFItems.AZURITE.get()), has(MFItems.AZURITE))
                .group("azurite")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.RAW_AZURITE.get(), 9)
                .requires(MFBlocks.RAW_AZURITE_BLOCK)
                .unlockedBy(getHasName(MFBlocks.RAW_AZURITE_BLOCK.get()), has(MFBlocks.RAW_AZURITE_BLOCK))
                .group("azurite")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.AZURITE.get(), 9)
                .requires(MFBlocks.AZURITE_BLOCK)
                .unlockedBy(getHasName(MFBlocks.AZURITE_BLOCK.get()), has(MFBlocks.AZURITE_BLOCK))
                .group("azurite")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.AZURITE.get(), 18)
                .requires(MFBlocks.AZURITE_BLOCK)
                .requires(Items.BLAZE_ROD)
                .requires(Items.BREEZE_ROD)
                .requires(MFItems.BRINE_ROD)
                .unlockedBy(getHasName(MFBlocks.AZURITE_BLOCK.get()), has(MFBlocks.AZURITE_BLOCK))
                .group("azurite")
                .save(output, "morefeatures:azurite_from_blaze_rod_and_breeze_rod_and_brine_rod");

        verticalSlabCrafting(MFBlocks.AZURITE_VERTICAL_SLAB, MFBlocks.AZURITE_BLOCK);
        verticalSlabStonecutting(MFBlocks.AZURITE_VERTICAL_SLAB, MFBlocks.AZURITE_BLOCK);

        // Fluorite recipes

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.RAW_FLUORITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.RAW_FLUORITE.get())
                .unlockedBy(getHasName(MFItems.RAW_FLUORITE.get()), has(MFItems.RAW_FLUORITE))
                .group("fluorite")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.FLUORITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.FLUORITE.get())
                .unlockedBy(getHasName(MFItems.FLUORITE.get()), has(MFItems.FLUORITE))
                .group("fluorite")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.RAW_FLUORITE.get(), 9)
                .requires(MFBlocks.RAW_FLUORITE_BLOCK)
                .unlockedBy(getHasName(MFBlocks.RAW_FLUORITE_BLOCK.get()), has(MFBlocks.RAW_FLUORITE_BLOCK))
                .group("fluorite")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.FLUORITE.get(), 9)
                .requires(MFBlocks.FLUORITE_BLOCK)
                .unlockedBy(getHasName(MFBlocks.FLUORITE_BLOCK.get()), has(MFBlocks.FLUORITE_BLOCK))
                .group("fluorite")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.FLUORITE.get(), 18)
                .requires(MFBlocks.FLUORITE_BLOCK)
                .requires(Items.BLAZE_ROD)
                .requires(Items.BREEZE_ROD)
                .requires(MFItems.BRINE_ROD)
                .unlockedBy(getHasName(MFBlocks.FLUORITE_BLOCK.get()), has(MFBlocks.FLUORITE_BLOCK))
                .group("fluorite")
                .save(output, "morefeatures:fluorite_from_blaze_rod_and_breeze_rod_and_brine_rod");

        // Bloodwood recipes
        shapeless(RecipeCategory.BUILDING_BLOCKS, MFBlocks.BLOODWOOD_PLANKS.get(), 4)
                .requires(MFItemTags.BLOODWOOD_LOGS)
                .unlockedBy(getHasName(MFBlocks.BLOODWOOD_LOG.get()), has(MFBlocks.BLOODWOOD_LOG.get()))
                .group("bloodwood_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.BLOODWOOD.get(), 3)
                .pattern("BB")
                .pattern("BB")
                .define('B', MFBlocks.BLOODWOOD_LOG.get())
                .unlockedBy(getHasName(MFBlocks.BLOODWOOD_LOG.get()), has(MFBlocks.BLOODWOOD_LOG))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.STRIPPED_BLOODWOOD.get(), 3)
                .pattern("BB")
                .pattern("BB")
                .define('B', MFBlocks.STRIPPED_BLOODWOOD_LOG.get())
                .unlockedBy(getHasName(MFBlocks.STRIPPED_BLOODWOOD_LOG.get()), has(MFBlocks.STRIPPED_BLOODWOOD_LOG))
                .save(output);

        verticalSlabCrafting(MFBlocks.BLOODWOOD_VERTICAL_SLAB, MFBlocks.BLOODWOOD_PLANKS.get());

        // Tainted wood recipes
        shapeless(RecipeCategory.BUILDING_BLOCKS, MFBlocks.TAINTED_PLANKS.get(), 4)
                .requires(MFItemTags.TAINTED_LOGS)
                .unlockedBy(getHasName(MFBlocks.TAINTED_LOG.get()), has(MFBlocks.TAINTED_LOG.get()))
                .group("tainted_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.TAINTED_WOOD.get(), 3)
                .pattern("TT")
                .pattern("TT")
                .define('T', MFBlocks.TAINTED_LOG.get())
                .unlockedBy(getHasName(MFBlocks.TAINTED_LOG.get()), has(MFBlocks.TAINTED_LOG))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.STRIPPED_TAINTED_WOOD.get(), 3)
                .pattern("TT")
                .pattern("TT")
                .define('T', MFBlocks.STRIPPED_TAINTED_LOG.get())
                .unlockedBy(getHasName(MFBlocks.STRIPPED_TAINTED_LOG.get()), has(MFBlocks.STRIPPED_TAINTED_LOG))
                .save(output);

        // Palm recipes

        shapeless(RecipeCategory.BUILDING_BLOCKS, MFBlocks.PALM_PLANKS.get(), 4)
                .requires(MFItemTags.PALM_LOGS)
                .unlockedBy(getHasName(MFBlocks.PALM_LOG.get()), has(MFBlocks.PALM_LOG.get()))
                .group("palm_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.PALM_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', MFBlocks.PALM_LOG.get())
                .unlockedBy(getHasName(MFBlocks.PALM_LOG.get()), has(MFBlocks.PALM_LOG))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.STRIPPED_PALM_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', MFBlocks.STRIPPED_PALM_LOG.get())
                .unlockedBy(getHasName(MFBlocks.STRIPPED_PALM_LOG.get()), has(MFBlocks.STRIPPED_PALM_LOG))
                .save(output);

        // Decrepit recipes

        shapeless(RecipeCategory.BUILDING_BLOCKS, MFBlocks.DECREPIT_PLANKS.get(), 4)
                .requires(MFItemTags.DECREPIT_LOGS)
                .unlockedBy(getHasName(MFBlocks.DECREPIT_LOG.get()), has(MFBlocks.DECREPIT_LOG.get()))
                .group("palm_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.DECREPIT_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', MFBlocks.DECREPIT_LOG.get())
                .unlockedBy(getHasName(MFBlocks.DECREPIT_LOG.get()), has(MFBlocks.DECREPIT_LOG))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.STRIPPED_DECREPIT_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', MFBlocks.STRIPPED_DECREPIT_LOG.get())
                .unlockedBy(getHasName(MFBlocks.STRIPPED_DECREPIT_LOG.get()), has(MFBlocks.STRIPPED_DECREPIT_LOG))
                .save(output);

        // Pallid recipes

        shapeless(RecipeCategory.BUILDING_BLOCKS, MFBlocks.PALLID_PLANKS.get(), 4)
                .requires(MFItemTags.PALLID_LOGS)
                .unlockedBy(getHasName(MFBlocks.PALLID_LOG.get()), has(MFBlocks.PALLID_LOG.get()))
                .group("palm_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.PALLID_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', MFBlocks.PALLID_LOG.get())
                .unlockedBy(getHasName(MFBlocks.PALLID_LOG.get()), has(MFBlocks.PALLID_LOG))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.STRIPPED_PALLID_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', MFBlocks.STRIPPED_PALLID_LOG.get())
                .unlockedBy(getHasName(MFBlocks.STRIPPED_PALLID_LOG.get()), has(MFBlocks.STRIPPED_PALLID_LOG))
                .save(output);

        // Bismuth recipes

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.BISMUTH_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.BISMUTH.get())
                .unlockedBy(getHasName(MFItems.BISMUTH.get()), has(MFItems.BISMUTH))
                .group("bismuth")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.BISMUTH.get(), 9)
                .requires(MFBlocks.BISMUTH_BLOCK)
                .unlockedBy(getHasName(MFBlocks.BISMUTH_BLOCK.get()), has(MFBlocks.BISMUTH_BLOCK))
                .group("bismuth")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.RAW_BISMUTH.get(), 9)
                .requires(MFBlocks.RAW_BISMUTH_BLOCK)
                .unlockedBy(getHasName(MFBlocks.RAW_BISMUTH_BLOCK.get()), has(MFBlocks.RAW_BISMUTH_BLOCK))
                .group("bismuth")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.BISMUTH.get(), 18)
                .requires(MFBlocks.BISMUTH_BLOCK)
                .requires(Items.BLAZE_ROD)
                .requires(Items.BREEZE_ROD)
                .requires(MFItems.BRINE_ROD)
                .unlockedBy(getHasName(MFBlocks.BISMUTH_BLOCK.get()), has(MFBlocks.BISMUTH_BLOCK))
                .group("bismuth")
                .save(output, "morefeatures:bismuth_from_blaze_rod_and_breeze_rod_and_brine_rod");

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.RAW_BISMUTH_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', MFItems.RAW_BISMUTH.get())
                .unlockedBy(getHasName(MFItems.RAW_BISMUTH.get()), has(MFItems.RAW_BISMUTH))
                .group("bismuth")
                .save(output);

        shaped(RecipeCategory.MISC, MFItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("NBN")
                .pattern("NEN")
                .pattern("NNN")
                .define('B', MFItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE.get())
                .define('N', Items.NETHERITE_SCRAP)
                .define('E', Blocks.END_STONE.asItem())
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                .group("bismuth")
                .save(output);

        shapeless(RecipeCategory.MISC, MFItems.BISMUTH.get())
                .requires(MFItems.BISMUTH_SCRAP, 4)
                .requires(Items.DIAMOND, 4)
                .unlockedBy(getHasName(MFItems.BISMUTH_SCRAP.get()), has(MFItems.BISMUTH_SCRAP))
                .group("bismuth")
                .save(output, "morefeatures:bismuth_from_bismuth_scraps_and_diamonds");

        // Magic block recipe

        shaped(RecipeCategory.MISC, MFBlocks.MAGIC_BLOCK)
                .pattern("DBD")
                .pattern("BAB")
                .pattern("NFN")
                .define('B', MFItems.BISMUTH.get())
                .define('N', Items.NETHERITE_SCRAP)
                .define('A', MFItems.AZURITE.get())
                .define('F', MFItems.FLUORITE.get())
                .define('D', Items.DIAMOND)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                .group("magic")
                .save(output);

        // Metal detector recipe

        shaped(RecipeCategory.TOOLS, MFItems.METAL_DETECTOR)
                .pattern("  S")
                .pattern("MS ")
                .pattern("BMI")
                .define('B', MFItems.BISMUTH.get())
                .define('S', Items.STICK)
                .define('M', MFItems.MAGNESIUM_INGOT.get())
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(MFItems.MAGNESIUM_INGOT.get()), has(MFItems.MAGNESIUM_INGOT))
                .group("metal_detector")
                .save(output);

        // Carbon Recipes

        shaped(RecipeCategory.COMBAT, MFItems.CARBON_BOW)
                .pattern("CCC")
                .pattern("CBC")
                .pattern("CRC")
                .define('C', MFItems.CARBON_FIBER.get())
                .define('B', Items.BOW)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(MFItems.CARBON_FIBER.get()), has(MFItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        shaped(RecipeCategory.COMBAT, MFItems.CARBON_TRIDENT)
                .pattern("CCC")
                .pattern("CTC")
                .pattern("CRC")
                .define('C', MFItems.CARBON_FIBER.get())
                .define('T', Items.TRIDENT)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(MFItems.CARBON_FIBER.get()), has(MFItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        shaped(RecipeCategory.COMBAT, MFItems.CARBON_CROSSBOW)
                .pattern("CCC")
                .pattern("CBC")
                .pattern("CRC")
                .define('C', MFItems.CARBON_FIBER.get())
                .define('B', Items.CROSSBOW)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(MFItems.CARBON_FIBER.get()), has(MFItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        shaped(RecipeCategory.COMBAT, MFItems.CARBON_ELYTRA)
                .pattern("CCC")
                .pattern("CEC")
                .pattern("CRC")
                .define('C', MFItems.CARBON_FIBER.get())
                .define('E', Items.ELYTRA)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(MFItems.CARBON_FIBER.get()), has(MFItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        shaped(RecipeCategory.COMBAT, MFItems.CARBON_WOLF_ARMOR)
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

        shaped(RecipeCategory.BUILDING_BLOCKS, MFBlocks.COMPRESSOR_BLOCK)
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

        woodenBoat(MFItems.BLOODWOOD_BOAT, MFBlocks.BLOODWOOD_PLANKS);
        woodenBoat(MFItems.TAINTED_BOAT, MFBlocks.TAINTED_PLANKS);
        woodenBoat(MFItems.PALM_BOAT, MFBlocks.PALM_PLANKS);

        chestBoat(MFItems.BLOODWOOD_CHEST_BOAT, MFItems.BLOODWOOD_BOAT);
        chestBoat(MFItems.TAINTED_CHEST_BOAT, MFItems.TAINTED_BOAT);
        chestBoat(MFItems.PALM_CHEST_BOAT, MFItems.PALM_BOAT);

        /*
         * Azurite and fluorite do not use their blocks for all of their recipes,
         * but a block family would assume that they do. Because of this, the
         * recipes are generated separately, not using generateForBlockFamilies()
         */

        // Stairs and slabs

        stairBuilder(MFBlocks.AZURITE_STAIRS.get(), Ingredient.of(MFBlocks.AZURITE_BLOCK))
                .unlockedBy(getHasName(MFBlocks.AZURITE_BLOCK.get()), has(MFBlocks.AZURITE_BLOCK))
                .group("azurite").save(output);
        slab(RecipeCategory.BUILDING_BLOCKS, MFBlocks.AZURITE_SLAB.get(), MFBlocks.AZURITE_BLOCK.get());

        stairBuilder(MFBlocks.FLUORITE_STAIRS.get(), Ingredient.of(MFBlocks.FLUORITE_BLOCK))
                .unlockedBy(getHasName(MFBlocks.FLUORITE_BLOCK.get()), has(MFBlocks.FLUORITE_BLOCK))
                .group("fluorite").save(output);
        slab(RecipeCategory.BUILDING_BLOCKS, MFBlocks.FLUORITE_SLAB.get(), MFBlocks.FLUORITE_BLOCK.get());

        // Buttons and pressure plates

        buttonBuilder(MFBlocks.AZURITE_BUTTON.get(), Ingredient.of(MFItems.AZURITE))
                .group("azurite")
                .unlockedBy(getHasName(MFItems.AZURITE.get()), has(MFItems.AZURITE.get()))
                .save(output);
        pressurePlate(MFBlocks.AZURITE_PRESSURE_PLATE.get(), MFItems.AZURITE.get());

        // Doors and trapdoors

        doorBuilder(MFBlocks.AZURITE_DOOR.get(), Ingredient.of(MFItems.AZURITE))
                .group("azurite")
                .unlockedBy(getHasName(MFItems.AZURITE.get()), has(MFItems.AZURITE.get()))
                .save(output);
        trapdoorBuilder(MFBlocks.AZURITE_TRAPDOOR.get(), Ingredient.of(MFItems.AZURITE))
                .group("azurite")
                .unlockedBy(getHasName(MFItems.AZURITE.get()), has(MFItems.AZURITE.get()))
                .save(output);

        doorBuilder(MFBlocks.FLUORITE_DOOR.get(), Ingredient.of(MFItems.FLUORITE))
                .group("fluorite")
                .unlockedBy(getHasName(MFItems.FLUORITE.get()), has(MFItems.FLUORITE.get()))
                .save(output);
        trapdoorBuilder(MFBlocks.FLUORITE_TRAPDOOR.get(), Ingredient.of(MFItems.FLUORITE))
                .group("fluorite")
                .unlockedBy(getHasName(MFItems.FLUORITE.get()), has(MFItems.FLUORITE.get()))
                .save(output);

        // Signs and Hanging signs

        signBuilder(MFItems.AZURITE_SIGN.get(), Ingredient.of(MFItems.AZURITE.get()))
                .group("azurite")
                .unlockedBy(getHasName(MFItems.AZURITE.get()), has(MFItems.AZURITE.get()))
                .save(output);
        hangingSignBuilder(MFItems.AZURITE_HANGING_SIGN.get(), Ingredient.of(MFItems.RAW_AZURITE.get()))
                .group("azurite")
                .unlockedBy(getHasName(MFItems.RAW_AZURITE.get()), has(MFItems.RAW_AZURITE.get()))
                .save(output);

        signBuilder(MFItems.FLUORITE_SIGN.get(), Ingredient.of(MFItems.FLUORITE.get()))
                .group("fluorite")
                .unlockedBy(getHasName(MFItems.FLUORITE.get()), has(MFItems.FLUORITE.get()))
                .save(output);
        hangingSignBuilder(MFItems.FLUORITE_HANGING_SIGN.get(), Ingredient.of(MFItems.RAW_FLUORITE.get()))
                .group("fluorite")
                .unlockedBy(getHasName(MFItems.RAW_FLUORITE.get()), has(MFItems.RAW_FLUORITE.get()))
                .save(output);

        // Fences and Fence Gates

        specialFenceBuilder(MFBlocks.AZURITE_FENCE.get(), Ingredient.of(MFBlocks.AZURITE_BLOCK), Ingredient.of(MFItems.AZURITE))
                .group("azurite")
                .unlockedBy(getHasName(MFItems.AZURITE.get()), has(MFItems.AZURITE.get()))
                .save(output);

        specialFenceGateBuilder(MFBlocks.AZURITE_FENCE_GATE.get(), Ingredient.of(MFBlocks.AZURITE_BLOCK), Ingredient.of(MFItems.AZURITE))
                .group("azurite")
                .unlockedBy(getHasName(MFItems.AZURITE.get()), has(MFItems.AZURITE.get()))
                .save(output);

        // Bismuth smithing recipes

        bismuthSmithing(MFItems.CARBON_BOW.get(), RecipeCategory.COMBAT, MFItems.BISMUTH_BOW.get());
        bismuthSmithing(MFItems.CARBON_TRIDENT.get(), RecipeCategory.COMBAT, MFItems.BISMUTH_TRIDENT.get());
        bismuthSmithing(Items.NETHERITE_NAUTILUS_ARMOR, RecipeCategory.COMBAT, MFItems.BISMUTH_NAUTILUS_ARMOR.get());
        bismuthSmithing(Items.NETHERITE_HORSE_ARMOR, RecipeCategory.COMBAT, MFItems.BISMUTH_HORSE_ARMOR.get());
        bismuthSmithing(Items.NETHERITE_SPEAR, RecipeCategory.COMBAT, MFItems.BISMUTH_SPEAR.get());
        bismuthSmithing(Items.NETHERITE_AXE, RecipeCategory.COMBAT, MFItems.BISMUTH_AXE.get());
        bismuthSmithing(Items.NETHERITE_SHOVEL, RecipeCategory.COMBAT, MFItems.BISMUTH_SHOVEL.get());
        bismuthSmithing(Items.NETHERITE_HOE, RecipeCategory.COMBAT, MFItems.BISMUTH_HOE.get());
        bismuthSmithing(Items.NETHERITE_SWORD, RecipeCategory.COMBAT, MFItems.BISMUTH_EQUIPMENT.get(0).asItem());
        bismuthSmithing(Items.NETHERITE_PICKAXE, RecipeCategory.COMBAT, MFItems.BISMUTH_EQUIPMENT.get(1).asItem());
        bismuthSmithing(Items.NETHERITE_HELMET, RecipeCategory.COMBAT, MFItems.BISMUTH_HELMET.get());
        bismuthSmithing(Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, MFItems.BISMUTH_CHESTPLATE.get());
        bismuthSmithing(Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, MFItems.BISMUTH_LEGGINGS.get());
        bismuthSmithing(Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, MFItems.BISMUTH_BOOTS.get());

        //—————————————————————————————SMELTABLE LISTS———————————————————————————————

        List<ItemLike> ALUMINIUM_SMELTABLES = List.of(MFItems.RAW_ALUMINIUM, MFBlocks.ALUMINIUM_ORE, MFBlocks.DEEPSLATE_ALUMINIUM_ORE);
        List<ItemLike> MAGNESIUM_SMELTABLES = List.of(MFItems.RAW_MAGNESIUM, MFBlocks.MAGNESIUM_ORE, MFBlocks.DEEPSLATE_MAGNESIUM_ORE);
        List<ItemLike> AZURITE_SMELTABLES = List.of(MFItems.RAW_AZURITE, MFBlocks.AZURITE_ORE, MFBlocks.DEEPSLATE_AZURITE_ORE, MFBlocks.END_AZURITE_ORE, MFBlocks.NETHER_AZURITE_ORE);
        List<ItemLike> FLUORITE_SMELTABLES = List.of(MFItems.RAW_FLUORITE, MFBlocks.FLUORITE_ORE, MFBlocks.DEEPSLATE_FLUORITE_ORE, MFBlocks.NETHER_FLUORITE_ORE, MFBlocks.END_FLUORITE_ORE);
        List<ItemLike> BISMUTH_SMELTABLES = List.of(MFItems.RAW_BISMUTH, MFBlocks.BISMUTH_ORE);



        //——————————————————————SMELTING RECIPE DATA GENERATION——————————————————————

        // Aluminium
        oreSmelting(ALUMINIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.ALUMINIUM_INGOT.get(), 0.25f, 200, "aluminium");
        oreBlasting(ALUMINIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.ALUMINIUM_INGOT.get(), 0.25f, 100, "aluminium");

        // Magnesium
        oreSmelting(MAGNESIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.MAGNESIUM_INGOT.get(), 0.25f, 200, "magnesium");
        oreBlasting(MAGNESIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.MAGNESIUM_INGOT.get(), 0.25f, 100, "magnesium");

        // Azurite
        oreSmelting(AZURITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.AZURITE.get(), 0.25f, 200, "azurite");
        oreBlasting(AZURITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.AZURITE.get(), 0.25f, 100, "azurite");

        // Fluorite
        oreSmelting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.FLUORITE.get(), 0.25f, 200, "fluorite");
        oreBlasting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.FLUORITE.get(), 0.25f, 100, "fluorite");

        // Bismuth
        oreSmelting(BISMUTH_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.BISMUTH_SCRAP.get(), 0.25f, 200, "bismuth");
        oreBlasting(BISMUTH_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, MFItems.BISMUTH_SCRAP.get(), 0.25f, 100, "bismuth");

        //—————————————————————————————VERTICAL SLABS TABLE———————————————————————————
        verticalSlabCrafting(MFBlocks.OAK_VERTICAL_SLAB, Blocks.OAK_PLANKS);
        verticalSlabCrafting(MFBlocks.SPRUCE_VERTICAL_SLAB, Blocks.SPRUCE_PLANKS);
        verticalSlabCrafting(MFBlocks.BIRCH_VERTICAL_SLAB, Blocks.BIRCH_PLANKS);
        verticalSlabCrafting(MFBlocks.JUNGLE_VERTICAL_SLAB, Blocks.JUNGLE_PLANKS);
        verticalSlabCrafting(MFBlocks.ACACIA_VERTICAL_SLAB, Blocks.ACACIA_PLANKS);
        verticalSlabCrafting(MFBlocks.DARK_OAK_VERTICAL_SLAB, Blocks.DARK_OAK_PLANKS);
        verticalSlabCrafting(MFBlocks.CRIMSON_VERTICAL_SLAB, Blocks.CRIMSON_PLANKS);
        verticalSlabCrafting(MFBlocks.WARPED_VERTICAL_SLAB, Blocks.WARPED_PLANKS);
        verticalSlabCrafting(MFBlocks.MANGROVE_VERTICAL_SLAB, Blocks.MANGROVE_PLANKS);
        verticalSlabCrafting(MFBlocks.CHERRY_VERTICAL_SLAB, Blocks.CHERRY_PLANKS);
        verticalSlabCrafting(MFBlocks.BAMBOO_VERTICAL_SLAB, Blocks.BAMBOO_PLANKS);
        verticalSlabCrafting(MFBlocks.BAMBOO_MOSAIC_VERTICAL_SLAB, Blocks.BAMBOO_MOSAIC);
        verticalSlabCrafting(MFBlocks.PALE_OAK_VERTICAL_SLAB, Blocks.PALE_OAK_PLANKS);

        verticalSlabCrafting(MFBlocks.STONE_VERTICAL_SLAB, Blocks.STONE);
        verticalSlabCrafting(MFBlocks.COBBLESTONE_VERTICAL_SLAB, Blocks.COBBLESTONE);
        verticalSlabCrafting(MFBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB, Blocks.MOSSY_COBBLESTONE);
        verticalSlabCrafting(MFBlocks.SMOOTH_STONE_VERTICAL_SLAB, Blocks.SMOOTH_STONE);
        verticalSlabCrafting(MFBlocks.STONE_BRICK_VERTICAL_SLAB, Blocks.STONE_BRICKS);
        verticalSlabCrafting(MFBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB, Blocks.MOSSY_STONE_BRICKS);
        verticalSlabCrafting(MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB, Blocks.COBBLED_DEEPSLATE);
        verticalSlabCrafting(MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);
        verticalSlabCrafting(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.DEEPSLATE_BRICKS);
        verticalSlabCrafting(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.DEEPSLATE_TILES);

        verticalSlabCrafting(MFBlocks.GRANITE_VERTICAL_SLAB, Blocks.GRANITE);
        verticalSlabCrafting(MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB, Blocks.POLISHED_GRANITE);
        verticalSlabCrafting(MFBlocks.DIORITE_VERTICAL_SLAB, Blocks.DIORITE);
        verticalSlabCrafting(MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB, Blocks.POLISHED_DIORITE);
        verticalSlabCrafting(MFBlocks.ANDESITE_VERTICAL_SLAB, Blocks.ANDESITE);
        verticalSlabCrafting(MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB, Blocks.POLISHED_ANDESITE);
        verticalSlabCrafting(MFBlocks.TUFF_VERTICAL_SLAB, Blocks.TUFF);
        verticalSlabCrafting(MFBlocks.POLISHED_TUFF_VERTICAL_SLAB, Blocks.POLISHED_TUFF);
        verticalSlabCrafting(MFBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.TUFF_BRICKS);

        verticalSlabCrafting(MFBlocks.SANDSTONE_VERTICAL_SLAB, Blocks.SANDSTONE);
        verticalSlabCrafting(MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_SANDSTONE);
        verticalSlabCrafting(MFBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_SANDSTONE);
        verticalSlabCrafting(MFBlocks.RED_SANDSTONE_VERTICAL_SLAB, Blocks.RED_SANDSTONE);
        verticalSlabCrafting(MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_RED_SANDSTONE);
        verticalSlabCrafting(MFBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_RED_SANDSTONE);

        verticalSlabCrafting(MFBlocks.SULFUR_VERTICAL_SLAB, Blocks.SULFUR);
        verticalSlabCrafting(MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB, Blocks.POLISHED_SULFUR);
        verticalSlabCrafting(MFBlocks.SULFUR_BRICK_VERTICAL_SLAB, Blocks.SULFUR_BRICKS);
        verticalSlabCrafting(MFBlocks.CINNABAR_VERTICAL_SLAB, Blocks.CINNABAR);
        verticalSlabCrafting(MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB, Blocks.POLISHED_CINNABAR);
        verticalSlabCrafting(MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB, Blocks.CINNABAR_BRICKS);

        verticalSlabCrafting(MFBlocks.NETHER_BRICK_VERTICAL_SLAB, Blocks.NETHER_BRICKS);
        verticalSlabCrafting(MFBlocks.RED_NETHER_BRICK_VERTICAL_SLAB, Blocks.RED_NETHER_BRICKS);
        verticalSlabCrafting(MFBlocks.BLACKSTONE_VERTICAL_SLAB, Blocks.BLACKSTONE);
        verticalSlabCrafting(MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE);
        verticalSlabCrafting(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_BRICKS);
        verticalSlabCrafting(MFBlocks.END_STONE_BRICK_VERTICAL_SLAB, Blocks.END_STONE_BRICKS);
        verticalSlabCrafting(MFBlocks.PURPUR_VERTICAL_SLAB, Blocks.PURPUR_BLOCK);
        verticalSlabCrafting(MFBlocks.QUARTZ_VERTICAL_SLAB, Blocks.QUARTZ_BLOCK);
        verticalSlabCrafting(MFBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB, Blocks.SMOOTH_QUARTZ);

        verticalSlabCrafting(MFBlocks.PRISMARINE_VERTICAL_SLAB, Blocks.PRISMARINE);
        verticalSlabCrafting(MFBlocks.PRISMARINE_BRICK_VERTICAL_SLAB, Blocks.PRISMARINE_BRICKS);
        verticalSlabCrafting(MFBlocks.DARK_PRISMARINE_VERTICAL_SLAB, Blocks.DARK_PRISMARINE);
        verticalSlabCrafting(MFBlocks.BRICK_VERTICAL_SLAB, Blocks.BRICKS);
        verticalSlabCrafting(MFBlocks.MUD_BRICK_VERTICAL_SLAB, Blocks.MUD_BRICKS);
        verticalSlabCrafting(MFBlocks.RESIN_BRICK_VERTICAL_SLAB, Blocks.RESIN_BRICKS);

        // Generates the recipe for all states that cut copper vertical slabs can come in, for stonecutting from a regular copper block or a cut copper block, AND the crafting table recipe

        WeatheringCopperCollection.zipApply(MFBlocks.CUT_COPPER_VERTICAL_SLAB, Blocks.COPPER_BLOCK, this::verticalSlabStonecuttingFromCopper);
        WeatheringCopperCollection.zipApply(MFBlocks.CUT_COPPER_VERTICAL_SLAB, Blocks.CUT_COPPER, this::verticalSlabStonecutting);
        WeatheringCopperCollection.zipApply(MFBlocks.CUT_COPPER_VERTICAL_SLAB, Blocks.CUT_COPPER, this::verticalSlabCrafting);

        // And this line generates the recipe for all different colours of wool vertical slabs!

        ColorCollection.VALUES.forEach(colour ->
                verticalSlabCrafting(MFBlocks.WOOL_VERTICAL_SLAB.pick(colour).get(), Blocks.WOOL.pick(colour)));

        //——————————————————————————VERTICAL SLABS STONECUTTING————————————————————————

        /* All recipes mimic an identical recipe for a horizontal slab */
        verticalSlabStonecutting(MFBlocks.STONE_VERTICAL_SLAB, Blocks.STONE);
        verticalSlabStonecutting(MFBlocks.COBBLESTONE_VERTICAL_SLAB, Blocks.STONE);
        verticalSlabStonecutting(MFBlocks.STONE_BRICK_VERTICAL_SLAB, Blocks.STONE);
        verticalSlabStonecutting(MFBlocks.COBBLESTONE_VERTICAL_SLAB, Blocks.COBBLESTONE);
        verticalSlabStonecutting(MFBlocks.STONE_BRICK_VERTICAL_SLAB, Blocks.STONE_BRICKS);
        verticalSlabStonecutting(MFBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB, Blocks.MOSSY_COBBLESTONE);
        verticalSlabStonecutting(MFBlocks.SMOOTH_STONE_VERTICAL_SLAB, Blocks.SMOOTH_STONE);
        verticalSlabStonecutting(MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB, Blocks.DEEPSLATE);
        verticalSlabStonecutting(MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB, Blocks.DEEPSLATE);
        verticalSlabStonecutting(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.DEEPSLATE);
        verticalSlabStonecutting(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.DEEPSLATE);
        verticalSlabStonecutting(MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB, Blocks.COBBLED_DEEPSLATE);
        verticalSlabStonecutting(MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);
        verticalSlabStonecutting(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.DEEPSLATE_BRICKS);
        verticalSlabStonecutting(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.DEEPSLATE_TILES);
        verticalSlabStonecutting(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);
        verticalSlabStonecutting(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);

        verticalSlabStonecutting(MFBlocks.GRANITE_VERTICAL_SLAB, Blocks.GRANITE);
        verticalSlabStonecutting(MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB, Blocks.GRANITE);
        verticalSlabStonecutting(MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB, Blocks.POLISHED_GRANITE);
        verticalSlabStonecutting(MFBlocks.DIORITE_VERTICAL_SLAB, Blocks.DIORITE);
        verticalSlabStonecutting(MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB, Blocks.DIORITE);
        verticalSlabStonecutting(MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB, Blocks.POLISHED_DIORITE);
        verticalSlabStonecutting(MFBlocks.ANDESITE_VERTICAL_SLAB, Blocks.ANDESITE);
        verticalSlabStonecutting(MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB, Blocks.ANDESITE);
        verticalSlabStonecutting(MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB, Blocks.POLISHED_ANDESITE);
        verticalSlabStonecutting(MFBlocks.TUFF_VERTICAL_SLAB, Blocks.TUFF);
        verticalSlabStonecutting(MFBlocks.POLISHED_TUFF_VERTICAL_SLAB, Blocks.TUFF);
        verticalSlabStonecutting(MFBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.TUFF);
        verticalSlabStonecutting(MFBlocks.POLISHED_TUFF_VERTICAL_SLAB, Blocks.POLISHED_TUFF);
        verticalSlabStonecutting(MFBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.TUFF_BRICKS);
        verticalSlabStonecutting(MFBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.POLISHED_TUFF);

        verticalSlabStonecutting(MFBlocks.SANDSTONE_VERTICAL_SLAB, Blocks.SANDSTONE);
        verticalSlabStonecutting(MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB, Blocks.SANDSTONE);
        verticalSlabStonecutting(MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_SANDSTONE);
        verticalSlabStonecutting(MFBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_SANDSTONE);
        verticalSlabStonecutting(MFBlocks.RED_SANDSTONE_VERTICAL_SLAB, Blocks.RED_SANDSTONE);
        verticalSlabStonecutting(MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB, Blocks.RED_SANDSTONE);
        verticalSlabStonecutting(MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_RED_SANDSTONE);
        verticalSlabStonecutting(MFBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_RED_SANDSTONE);

        verticalSlabStonecutting(MFBlocks.SULFUR_VERTICAL_SLAB, Blocks.SULFUR);
        verticalSlabStonecutting(MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB, Blocks.SULFUR);
        verticalSlabStonecutting(MFBlocks.SULFUR_BRICK_VERTICAL_SLAB, Blocks.SULFUR);
        verticalSlabStonecutting(MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB, Blocks.POLISHED_SULFUR);
        verticalSlabStonecutting(MFBlocks.SULFUR_BRICK_VERTICAL_SLAB, Blocks.SULFUR_BRICKS);
        verticalSlabStonecutting(MFBlocks.CINNABAR_VERTICAL_SLAB, Blocks.CINNABAR);
        verticalSlabStonecutting(MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB, Blocks.CINNABAR);
        verticalSlabStonecutting(MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB, Blocks.CINNABAR);
        verticalSlabStonecutting(MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB, Blocks.POLISHED_CINNABAR);
        verticalSlabStonecutting(MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB, Blocks.CINNABAR_BRICKS);

        verticalSlabStonecutting(MFBlocks.NETHER_BRICK_VERTICAL_SLAB, Blocks.NETHER_BRICKS);
        verticalSlabStonecutting(MFBlocks.RED_NETHER_BRICK_VERTICAL_SLAB, Blocks.RED_NETHER_BRICKS);
        verticalSlabStonecutting(MFBlocks.BLACKSTONE_VERTICAL_SLAB, Blocks.BLACKSTONE);
        verticalSlabStonecutting(MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB, Blocks.BLACKSTONE);
        verticalSlabStonecutting(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.BLACKSTONE);
        verticalSlabStonecutting(MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE);
        verticalSlabStonecutting(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE);
        verticalSlabStonecutting(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_BRICKS);
        verticalSlabStonecutting(MFBlocks.END_STONE_BRICK_VERTICAL_SLAB, Blocks.END_STONE);
        verticalSlabStonecutting(MFBlocks.END_STONE_BRICK_VERTICAL_SLAB, Blocks.END_STONE_BRICKS);
        verticalSlabStonecutting(MFBlocks.PURPUR_VERTICAL_SLAB, Blocks.PURPUR_BLOCK);
        verticalSlabStonecutting(MFBlocks.QUARTZ_VERTICAL_SLAB, Blocks.QUARTZ_BLOCK);
        verticalSlabStonecutting(MFBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB, Blocks.SMOOTH_QUARTZ);

        verticalSlabStonecutting(MFBlocks.PRISMARINE_VERTICAL_SLAB, Blocks.PRISMARINE);
        verticalSlabStonecutting(MFBlocks.PRISMARINE_BRICK_VERTICAL_SLAB, Blocks.PRISMARINE_BRICKS);
        verticalSlabStonecutting(MFBlocks.DARK_PRISMARINE_VERTICAL_SLAB, Blocks.DARK_PRISMARINE);
        verticalSlabStonecutting(MFBlocks.BRICK_VERTICAL_SLAB, Blocks.BRICKS);
        verticalSlabStonecutting(MFBlocks.MUD_BRICK_VERTICAL_SLAB, Blocks.MUD_BRICKS);
        verticalSlabStonecutting(MFBlocks.RESIN_BRICK_VERTICAL_SLAB, Blocks.RESIN_BRICKS);
    }


    @Override
    protected <T extends AbstractCookingRecipe> void oreCooking(AbstractCookingRecipe.Factory<T> factory, List<ItemLike> smeltables,
                                                                RecipeCategory craftingCategory, CookingBookCategory cookingCategory, ItemLike result,
                                                                float experience, int cookingTime, String group, String fromDesc) {
        for(ItemLike itemlike : smeltables) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), craftingCategory, cookingCategory, result, experience, cookingTime, factory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(output, MoreFeatures.MODID + ":" + getItemName(result) + fromDesc + "_" + getItemName(itemlike));
        }
    }


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

    protected RecipeBuilder specialFenceBuilder(ItemLike result, Ingredient base, Ingredient actingStick) {
        return this.shaped(RecipeCategory.DECORATIONS, result, 3).define('W', base).define('#', actingStick).pattern("W#W").pattern("W#W");
    }

    protected RecipeBuilder specialFenceGateBuilder(ItemLike result, Ingredient planks, Ingredient actingStick) {
        return this.shaped(RecipeCategory.REDSTONE, result).define('#', actingStick).define('W', planks).pattern("#W#").pattern("#W#");
    }
}

