package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.block.family.ModBlockFamilies;
import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.tag.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "MoreFeatures Recipes";
        }
    }

    protected void generateForModBlockFamilies(FeatureFlagSet flagSet) {
        ModBlockFamilies.getAllFamilies()
                .forEach(family -> this.generateRecipes(family, flagSet));
    }

    @Override
    protected void buildRecipes(){

        generateForModBlockFamilies(
                FeatureFlagSet.of(FeatureFlags.VANILLA)
        );

        // Aluminium recipes

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALUMINIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ALUMINIUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.ALUMINIUM_INGOT.get()), has(ModItems.ALUMINIUM_INGOT))
                .group("aluminium")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.RAW_ALUMINIUM.get(), 9)
                .requires(ModBlocks.RAW_ALUMINIUM_BLOCK)
                .unlockedBy(getHasName(ModBlocks.RAW_ALUMINIUM_BLOCK.get()), has(ModBlocks.RAW_ALUMINIUM_BLOCK))
                .group("aluminium")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_ALUMINIUM_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_ALUMINIUM.get())
                .unlockedBy(getHasName(ModItems.RAW_ALUMINIUM.get()), has(ModItems.RAW_ALUMINIUM))
                .group("aluminium")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.ALUMINIUM_INGOT.get(), 9)
                .requires(ModBlocks.ALUMINIUM_BLOCK)
                .unlockedBy(getHasName(ModBlocks.ALUMINIUM_BLOCK.get()), has(ModBlocks.ALUMINIUM_BLOCK))
                .group("aluminium")
                .save(output);

        // Magnesium recipes

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_MAGNESIUM_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_MAGNESIUM.get())
                .unlockedBy(getHasName(ModItems.RAW_MAGNESIUM.get()), has(ModItems.RAW_MAGNESIUM))
                .group("magnesium")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MAGNESIUM_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.MAGNESIUM_INGOT.get())
                .unlockedBy(getHasName(ModItems.MAGNESIUM_INGOT.get()), has(ModItems.MAGNESIUM_INGOT))
                .group("magnesium")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.RAW_MAGNESIUM.get(), 9)
                .requires(ModBlocks.RAW_MAGNESIUM_BLOCK)
                .unlockedBy(getHasName(ModBlocks.RAW_MAGNESIUM_BLOCK.get()), has(ModBlocks.RAW_MAGNESIUM_BLOCK))
                .group("magnesium")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.MAGNESIUM_INGOT.get(), 9)
                .requires(ModBlocks.MAGNESIUM_BLOCK)
                .unlockedBy(getHasName(ModBlocks.MAGNESIUM_BLOCK.get()), has(ModBlocks.MAGNESIUM_BLOCK))
                .group("magnesium")
                .save(output);

        // Azurite recipes

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_AZURITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_AZURITE.get())
                .unlockedBy(getHasName(ModItems.RAW_AZURITE.get()), has(ModItems.RAW_AZURITE))
                .group("azurite")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AZURITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.AZURITE.get())
                .unlockedBy(getHasName(ModItems.AZURITE.get()), has(ModItems.AZURITE))
                .group("azurite")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.RAW_AZURITE.get(), 9)
                .requires(ModBlocks.RAW_AZURITE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.RAW_AZURITE_BLOCK.get()), has(ModBlocks.RAW_AZURITE_BLOCK))
                .group("azurite")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.AZURITE.get(), 9)
                .requires(ModBlocks.AZURITE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.AZURITE_BLOCK.get()), has(ModBlocks.AZURITE_BLOCK))
                .group("azurite")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.AZURITE.get(), 18)
                .requires(ModBlocks.AZURITE_BLOCK)
                .requires(Items.BLAZE_ROD)
                .requires(Items.BREEZE_ROD)
                .requires(ModItems.BRINE_ROD)
                .unlockedBy(getHasName(ModBlocks.AZURITE_BLOCK.get()), has(ModBlocks.AZURITE_BLOCK))
                .group("azurite")
                .save(output, "morefeatures:azurite_from_blaze_rod_and_breeze_rod_and_brine_rod");

        // Fluorite recipes

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_FLUORITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_FLUORITE.get())
                .unlockedBy(getHasName(ModItems.RAW_FLUORITE.get()), has(ModItems.RAW_FLUORITE))
                .group("fluorite")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.FLUORITE.get())
                .unlockedBy(getHasName(ModItems.FLUORITE.get()), has(ModItems.FLUORITE))
                .group("fluorite")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.RAW_FLUORITE.get(), 9)
                .requires(ModBlocks.RAW_FLUORITE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.RAW_FLUORITE_BLOCK.get()), has(ModBlocks.RAW_FLUORITE_BLOCK))
                .group("fluorite")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.FLUORITE.get(), 9)
                .requires(ModBlocks.FLUORITE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.FLUORITE_BLOCK.get()), has(ModBlocks.FLUORITE_BLOCK))
                .group("fluorite")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.FLUORITE.get(), 18)
                .requires(ModBlocks.FLUORITE_BLOCK)
                .requires(Items.BLAZE_ROD)
                .requires(Items.BREEZE_ROD)
                .requires(ModItems.BRINE_ROD)
                .unlockedBy(getHasName(ModBlocks.FLUORITE_BLOCK.get()), has(ModBlocks.FLUORITE_BLOCK))
                .group("fluorite")
                .save(output, "morefeatures:fluorite_from_blaze_rod_and_breeze_rod_and_brine_rod");

        // Bloodwood recipes
        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODWOOD_PLANKS.get(), 4)
                .requires(ModItemTags.BLOODWOOD_LOGS)
                .unlockedBy(getHasName(ModBlocks.BLOODWOOD_LOG.get()), has(ModBlocks.BLOODWOOD_LOG.get()))
                .group("bloodwood_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODWOOD.get(), 3)
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBlocks.BLOODWOOD_LOG.get())
                .unlockedBy(getHasName(ModBlocks.BLOODWOOD_LOG.get()), has(ModBlocks.BLOODWOOD_LOG))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_BLOODWOOD.get(), 3)
                .pattern("BB")
                .pattern("BB")
                .define('B', ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()), has(ModBlocks.STRIPPED_BLOODWOOD_LOG))
                .save(output);

        // Tainted wood recipes
        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TAINTED_PLANKS.get(), 4)
                .requires(ModItemTags.TAINTED_LOGS)
                .unlockedBy(getHasName(ModBlocks.TAINTED_LOG.get()), has(ModBlocks.TAINTED_LOG.get()))
                .group("tainted_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.TAINTED_WOOD.get(), 3)
                .pattern("TT")
                .pattern("TT")
                .define('T', ModBlocks.TAINTED_LOG.get())
                .unlockedBy(getHasName(ModBlocks.TAINTED_LOG.get()), has(ModBlocks.TAINTED_LOG))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_TAINTED_WOOD.get(), 3)
                .pattern("TT")
                .pattern("TT")
                .define('T', ModBlocks.STRIPPED_TAINTED_LOG.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_TAINTED_LOG.get()), has(ModBlocks.STRIPPED_TAINTED_LOG))
                .save(output);

        // Palm recipes

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PLANKS.get(), 4)
                .requires(ModItemTags.PALM_LOGS)
                .unlockedBy(getHasName(ModBlocks.PALM_LOG.get()), has(ModBlocks.PALM_LOG.get()))
                .group("palm_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', ModBlocks.PALM_LOG.get())
                .unlockedBy(getHasName(ModBlocks.PALM_LOG.get()), has(ModBlocks.PALM_LOG))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_PALM_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', ModBlocks.STRIPPED_PALM_LOG.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_LOG.get()), has(ModBlocks.STRIPPED_PALM_LOG))
                .save(output);

        // Decrepit recipes

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DECREPIT_PLANKS.get(), 4)
                .requires(ModItemTags.DECREPIT_LOGS)
                .unlockedBy(getHasName(ModBlocks.DECREPIT_LOG.get()), has(ModBlocks.DECREPIT_LOG.get()))
                .group("palm_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DECREPIT_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', ModBlocks.DECREPIT_LOG.get())
                .unlockedBy(getHasName(ModBlocks.DECREPIT_LOG.get()), has(ModBlocks.DECREPIT_LOG))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_DECREPIT_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', ModBlocks.STRIPPED_DECREPIT_LOG.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_DECREPIT_LOG.get()), has(ModBlocks.STRIPPED_DECREPIT_LOG))
                .save(output);

        // Pallid recipes

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALLID_PLANKS.get(), 4)
                .requires(ModItemTags.PALLID_LOGS)
                .unlockedBy(getHasName(ModBlocks.PALLID_LOG.get()), has(ModBlocks.PALLID_LOG.get()))
                .group("palm_planks")
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALLID_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', ModBlocks.PALLID_LOG.get())
                .unlockedBy(getHasName(ModBlocks.PALLID_LOG.get()), has(ModBlocks.PALLID_LOG))
                .save(output);

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_PALLID_WOOD.get(), 3)
                .pattern("PP")
                .pattern("PP")
                .define('P', ModBlocks.STRIPPED_PALLID_LOG.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALLID_LOG.get()), has(ModBlocks.STRIPPED_PALLID_LOG))
                .save(output);

        // Bismuth recipes

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BISMUTH_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.BISMUTH.get())
                .unlockedBy(getHasName(ModItems.BISMUTH.get()), has(ModItems.BISMUTH))
                .group("bismuth")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get(), 9)
                .requires(ModBlocks.BISMUTH_BLOCK)
                .unlockedBy(getHasName(ModBlocks.BISMUTH_BLOCK.get()), has(ModBlocks.BISMUTH_BLOCK))
                .group("bismuth")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.RAW_BISMUTH.get(), 9)
                .requires(ModBlocks.RAW_BISMUTH_BLOCK)
                .unlockedBy(getHasName(ModBlocks.RAW_BISMUTH_BLOCK.get()), has(ModBlocks.RAW_BISMUTH_BLOCK))
                .group("bismuth")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get(), 18)
                .requires(ModBlocks.BISMUTH_BLOCK)
                .requires(Items.BLAZE_ROD)
                .requires(Items.BREEZE_ROD)
                .requires(ModItems.BRINE_ROD)
                .unlockedBy(getHasName(ModBlocks.BISMUTH_BLOCK.get()), has(ModBlocks.BISMUTH_BLOCK))
                .group("bismuth")
                .save(output, "morefeatures:bismuth_from_blaze_rod_and_breeze_rod_and_brine_rod");

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_BISMUTH_BLOCK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_BISMUTH.get())
                .unlockedBy(getHasName(ModItems.RAW_BISMUTH.get()), has(ModItems.RAW_BISMUTH))
                .group("bismuth")
                .save(output);

        shaped(RecipeCategory.MISC, ModItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE, 2)
                .pattern("NBN")
                .pattern("NEN")
                .pattern("NNN")
                .define('B', ModItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE.get())
                .define('N', Items.NETHERITE_SCRAP)
                .define('E', Blocks.END_STONE.asItem())
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                .group("bismuth")
                .save(output);

        shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get())
                .requires(ModItems.BISMUTH_SCRAP, 4)
                .requires(Items.DIAMOND, 4)
                .unlockedBy(getHasName(ModItems.BISMUTH_SCRAP.get()), has(ModItems.BISMUTH_SCRAP))
                .group("bismuth")
                .save(output, "morefeatures:bismuth_from_bismuth_scraps_and_diamonds");

        // Magic block recipe

        shaped(RecipeCategory.MISC, ModBlocks.MAGIC_BLOCK)
                .pattern("DBD")
                .pattern("BAB")
                .pattern("NFN")
                .define('B', ModItems.BISMUTH.get())
                .define('N', Items.NETHERITE_SCRAP)
                .define('A', ModItems.AZURITE.get())
                .define('F', ModItems.FLUORITE.get())
                .define('D', Items.DIAMOND)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                .group("magic")
                .save(output);

        // Metal detector recipe

        shaped(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR)
                .pattern("  S")
                .pattern("MS ")
                .pattern("BMI")
                .define('B', ModItems.BISMUTH.get())
                .define('S', Items.STICK)
                .define('M', ModItems.MAGNESIUM_INGOT.get())
                .define('I', Items.IRON_INGOT)
                .unlockedBy(getHasName(ModItems.MAGNESIUM_INGOT.get()), has(ModItems.MAGNESIUM_INGOT))
                .group("metal_detector")
                .save(output);

        // Carbon Recipes

        shaped(RecipeCategory.COMBAT, ModItems.CARBON_BOW)
                .pattern("CCC")
                .pattern("CBC")
                .pattern("CRC")
                .define('C', ModItems.CARBON_FIBER.get())
                .define('B', Items.BOW)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(ModItems.CARBON_FIBER.get()), has(ModItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        shaped(RecipeCategory.COMBAT, ModItems.CARBON_TRIDENT)
                .pattern("CCC")
                .pattern("CTC")
                .pattern("CRC")
                .define('C', ModItems.CARBON_FIBER.get())
                .define('T', Items.TRIDENT)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(ModItems.CARBON_FIBER.get()), has(ModItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        shaped(RecipeCategory.COMBAT, ModItems.CARBON_CROSSBOW)
                .pattern("CCC")
                .pattern("CBC")
                .pattern("CRC")
                .define('C', ModItems.CARBON_FIBER.get())
                .define('B', Items.CROSSBOW)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(ModItems.CARBON_FIBER.get()), has(ModItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        shaped(RecipeCategory.COMBAT, ModItems.CARBON_ELYTRA)
                .pattern("CCC")
                .pattern("CEC")
                .pattern("CRC")
                .define('C', ModItems.CARBON_FIBER.get())
                .define('E', Items.ELYTRA)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(ModItems.CARBON_FIBER.get()), has(ModItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        shaped(RecipeCategory.COMBAT, ModItems.CARBON_WOLF_ARMOR)
                .pattern("CCC")
                .pattern("CWC")
                .pattern("CRC")
                .define('C', ModItems.CARBON_FIBER.get())
                .define('W', Items.WOLF_ARMOR)
                .define('R', Items.RESIN_CLUMP)
                .unlockedBy(getHasName(ModItems.CARBON_FIBER.get()), has(ModItems.CARBON_FIBER))
                .group("carbon_bow")
                .save(output);

        // Compressor block recipe

        shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COMPRESSOR_BLOCK)
                .pattern("AMA")
                .pattern("MNM")
                .pattern("ANA")
                .define('A', ModItems.ALUMINIUM_INGOT)
                .define('M', ModItems.MAGNESIUM_INGOT)
                .define('N', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .group("compressor_block")
                .save(output);


        // Stairs and slabs
        stairBuilder(ModBlocks.AZURITE_STAIRS.get(), Ingredient.of(ModBlocks.AZURITE_BLOCK))
                .unlockedBy(getHasName(ModBlocks.AZURITE_BLOCK.get()), has(ModBlocks.AZURITE_BLOCK))
                .group("azurite").save(output);
        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.AZURITE_SLAB.get(), ModBlocks.AZURITE_BLOCK.get());

        stairBuilder(ModBlocks.FLUORITE_STAIRS.get(), Ingredient.of(ModBlocks.FLUORITE_BLOCK))
                .unlockedBy(getHasName(ModBlocks.FLUORITE_BLOCK.get()), has(ModBlocks.FLUORITE_BLOCK))
                .group("fluorite").save(output);
        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_SLAB.get(), ModBlocks.FLUORITE_BLOCK.get());

        // Buttons and pressure plates

        buttonBuilder(ModBlocks.AZURITE_BUTTON.get(), Ingredient.of(ModItems.AZURITE))
                .group("zircon")
                .unlockedBy(getHasName(ModItems.AZURITE.get()), has(ModItems.AZURITE.get()))
                .save(output);
        pressurePlate(ModBlocks.AZURITE_PRESSURE_PLATE.get(), ModItems.AZURITE.get());


        // Bismuth smithing recipes

        // carbonBismuthSmithing upgrades carbon items to bismuth
        // netheriteBismuthSmithing applies to netherite items

        // This is not technically necessary and is simply for clarity on my part

        bismuthSmithing(ModItems.CARBON_BOW.get(), RecipeCategory.COMBAT, ModItems.BISMUTH_BOW.get());
        bismuthSmithing(ModItems.CARBON_TRIDENT.get(), RecipeCategory.COMBAT, ModItems.BISMUTH_TRIDENT.get());
        bismuthSmithing(Items.NETHERITE_NAUTILUS_ARMOR, RecipeCategory.COMBAT, ModItems.BISMUTH_NAUTILUS_ARMOR.get());
        bismuthSmithing(Items.NETHERITE_HORSE_ARMOR, RecipeCategory.COMBAT, ModItems.BISMUTH_HORSE_ARMOR.get());
        bismuthSmithing(Items.NETHERITE_SPEAR, RecipeCategory.COMBAT, ModItems.BISMUTH_SPEAR.get());
        bismuthSmithing(Items.NETHERITE_AXE, RecipeCategory.COMBAT, ModItems.BISMUTH_AXE.get());
        bismuthSmithing(Items.NETHERITE_SHOVEL, RecipeCategory.COMBAT, ModItems.BISMUTH_SHOVEL.get());
        bismuthSmithing(Items.NETHERITE_HOE, RecipeCategory.COMBAT, ModItems.BISMUTH_HOE.get());
        bismuthSmithing(Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(0).asItem());
        bismuthSmithing(Items.NETHERITE_PICKAXE, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(1).asItem());
        bismuthSmithing(Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(2).asItem());
        bismuthSmithing(Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(3).asItem());
        bismuthSmithing(Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(4).asItem());
        bismuthSmithing(Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(5).asItem());


        //—————————————————————————————SMELTABLE LISTS———————————————————————————————

        List<ItemLike> ALUMINIUM_SMELTABLES = List.of(ModItems.RAW_ALUMINIUM, ModBlocks.ALUMINIUM_ORE, ModBlocks.DEEPSLATE_ALUMINIUM_ORE);
        List<ItemLike> MAGNESIUM_SMELTABLES = List.of(ModItems.RAW_MAGNESIUM, ModBlocks.MAGNESIUM_ORE, ModBlocks.DEEPSLATE_MAGNESIUM_ORE);
        List<ItemLike> AZURITE_SMELTABLES = List.of(ModItems.RAW_AZURITE, ModBlocks.AZURITE_ORE, ModBlocks.DEEPSLATE_AZURITE_ORE, ModBlocks.END_AZURITE_ORE, ModBlocks.NETHER_AZURITE_ORE);
        List<ItemLike> FLUORITE_SMELTABLES = List.of(ModItems.RAW_FLUORITE, ModBlocks.FLUORITE_ORE, ModBlocks.DEEPSLATE_FLUORITE_ORE, ModBlocks.NETHER_FLUORITE_ORE, ModBlocks.END_FLUORITE_ORE);
        List<ItemLike> BISMUTH_SMELTABLES = List.of(ModItems.RAW_BISMUTH, ModBlocks.BISMUTH_ORE);



        //——————————————————————SMELTING RECIPE DATA GENERATION——————————————————————

        // Aluminium
        oreSmelting(ALUMINIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.ALUMINIUM_INGOT.get(), 0.25f, 200, "aluminium");
        oreBlasting(ALUMINIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.ALUMINIUM_INGOT.get(), 0.25f, 100, "aluminium");

        // Magnesium
        oreSmelting(MAGNESIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.MAGNESIUM_INGOT.get(), 0.25f, 200, "magnesium");
        oreBlasting(MAGNESIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.MAGNESIUM_INGOT.get(), 0.25f, 100, "magnesium");

        // Azurite
        oreSmelting(AZURITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.AZURITE.get(), 0.25f, 200, "azurite");
        oreBlasting(AZURITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.AZURITE.get(), 0.25f, 100, "azurite");

        // Fluorite
        oreSmelting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.FLUORITE.get(), 0.25f, 200, "fluorite");
        oreBlasting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.FLUORITE.get(), 0.25f, 100, "fluorite");

        // Bismuth
        oreSmelting(BISMUTH_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.BISMUTH_SCRAP.get(), 0.25f, 200, "bismuth");
        oreBlasting(BISMUTH_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.BISMUTH_SCRAP.get(), 0.25f, 100, "bismuth");

        //—————————————————————————————VERTICAL SLABS TABLE———————————————————————————
        verticalSlabCrafting(ModBlocks.OAK_VERTICAL_SLAB, Blocks.OAK_PLANKS);
        verticalSlabCrafting(ModBlocks.SPRUCE_VERTICAL_SLAB, Blocks.SPRUCE_PLANKS);
        verticalSlabCrafting(ModBlocks.BIRCH_VERTICAL_SLAB, Blocks.BIRCH_PLANKS);
        verticalSlabCrafting(ModBlocks.JUNGLE_VERTICAL_SLAB, Blocks.JUNGLE_PLANKS);
        verticalSlabCrafting(ModBlocks.ACACIA_VERTICAL_SLAB, Blocks.ACACIA_PLANKS);
        verticalSlabCrafting(ModBlocks.DARK_OAK_VERTICAL_SLAB, Blocks.DARK_OAK_PLANKS);
        verticalSlabCrafting(ModBlocks.CRIMSON_VERTICAL_SLAB, Blocks.CRIMSON_PLANKS);
        verticalSlabCrafting(ModBlocks.WARPED_VERTICAL_SLAB, Blocks.WARPED_PLANKS);
        verticalSlabCrafting(ModBlocks.MANGROVE_VERTICAL_SLAB, Blocks.MANGROVE_PLANKS);
        verticalSlabCrafting(ModBlocks.CHERRY_VERTICAL_SLAB, Blocks.CHERRY_PLANKS);
        verticalSlabCrafting(ModBlocks.BAMBOO_VERTICAL_SLAB, Blocks.BAMBOO_PLANKS);
        verticalSlabCrafting(ModBlocks.BAMBOO_MOSAIC_VERTICAL_SLAB, Blocks.BAMBOO_MOSAIC);
        verticalSlabCrafting(ModBlocks.PALE_OAK_VERTICAL_SLAB, Blocks.PALE_OAK_PLANKS);

        verticalSlabCrafting(ModBlocks.STONE_VERTICAL_SLAB, Blocks.STONE);
        verticalSlabCrafting(ModBlocks.COBBLESTONE_VERTICAL_SLAB, Blocks.COBBLESTONE);
        verticalSlabCrafting(ModBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB, Blocks.MOSSY_COBBLESTONE);
        verticalSlabCrafting(ModBlocks.SMOOTH_STONE_VERTICAL_SLAB, Blocks.SMOOTH_STONE);
        verticalSlabCrafting(ModBlocks.STONE_BRICK_VERTICAL_SLAB, Blocks.STONE_BRICKS);
        verticalSlabCrafting(ModBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB, Blocks.MOSSY_STONE_BRICKS);
        verticalSlabCrafting(ModBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB, Blocks.COBBLED_DEEPSLATE);
        verticalSlabCrafting(ModBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);
        verticalSlabCrafting(ModBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.DEEPSLATE_BRICKS);
        verticalSlabCrafting(ModBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.DEEPSLATE_TILES);

        verticalSlabCrafting(ModBlocks.GRANITE_VERTICAL_SLAB, Blocks.GRANITE);
        verticalSlabCrafting(ModBlocks.POLISHED_GRANITE_VERTICAL_SLAB, Blocks.POLISHED_GRANITE);
        verticalSlabCrafting(ModBlocks.DIORITE_VERTICAL_SLAB, Blocks.DIORITE);
        verticalSlabCrafting(ModBlocks.POLISHED_DIORITE_VERTICAL_SLAB, Blocks.POLISHED_DIORITE);
        verticalSlabCrafting(ModBlocks.ANDESITE_VERTICAL_SLAB, Blocks.ANDESITE);
        verticalSlabCrafting(ModBlocks.POLISHED_ANDESITE_VERTICAL_SLAB, Blocks.POLISHED_ANDESITE);
        verticalSlabCrafting(ModBlocks.TUFF_VERTICAL_SLAB, Blocks.TUFF);
        verticalSlabCrafting(ModBlocks.POLISHED_TUFF_VERTICAL_SLAB, Blocks.POLISHED_TUFF);
        verticalSlabCrafting(ModBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.TUFF_BRICKS);

        verticalSlabCrafting(ModBlocks.SANDSTONE_VERTICAL_SLAB, Blocks.SANDSTONE);
        verticalSlabCrafting(ModBlocks.CUT_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_SANDSTONE);
        verticalSlabCrafting(ModBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_SANDSTONE);
        verticalSlabCrafting(ModBlocks.RED_SANDSTONE_VERTICAL_SLAB, Blocks.RED_SANDSTONE);
        verticalSlabCrafting(ModBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_RED_SANDSTONE);
        verticalSlabCrafting(ModBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_RED_SANDSTONE);

        verticalSlabCrafting(ModBlocks.SULFUR_VERTICAL_SLAB, Blocks.SULFUR);
        verticalSlabCrafting(ModBlocks.POLISHED_SULFUR_VERTICAL_SLAB, Blocks.POLISHED_SULFUR);
        verticalSlabCrafting(ModBlocks.SULFUR_BRICK_VERTICAL_SLAB, Blocks.SULFUR_BRICKS);
        verticalSlabCrafting(ModBlocks.CINNABAR_VERTICAL_SLAB, Blocks.CINNABAR);
        verticalSlabCrafting(ModBlocks.POLISHED_CINNABAR_VERTICAL_SLAB, Blocks.POLISHED_CINNABAR);
        verticalSlabCrafting(ModBlocks.CINNABAR_BRICK_VERTICAL_SLAB, Blocks.CINNABAR_BRICKS);

        verticalSlabCrafting(ModBlocks.NETHER_BRICK_VERTICAL_SLAB, Blocks.NETHER_BRICKS);
        verticalSlabCrafting(ModBlocks.RED_NETHER_BRICK_VERTICAL_SLAB, Blocks.RED_NETHER_BRICKS);
        verticalSlabCrafting(ModBlocks.BLACKSTONE_VERTICAL_SLAB, Blocks.BLACKSTONE);
        verticalSlabCrafting(ModBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE);
        verticalSlabCrafting(ModBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_BRICKS);
        verticalSlabCrafting(ModBlocks.END_STONE_BRICK_VERTICAL_SLAB, Blocks.END_STONE_BRICKS);
        verticalSlabCrafting(ModBlocks.PURPUR_VERTICAL_SLAB, Blocks.PURPUR_BLOCK);
        verticalSlabCrafting(ModBlocks.QUARTZ_VERTICAL_SLAB, Blocks.QUARTZ_BLOCK);
        verticalSlabCrafting(ModBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB, Blocks.SMOOTH_QUARTZ);

        verticalSlabCrafting(ModBlocks.PRISMARINE_VERTICAL_SLAB, Blocks.PRISMARINE);
        verticalSlabCrafting(ModBlocks.PRISMARINE_BRICK_VERTICAL_SLAB, Blocks.PRISMARINE_BRICKS);
        verticalSlabCrafting(ModBlocks.DARK_PRISMARINE_VERTICAL_SLAB, Blocks.DARK_PRISMARINE);
        verticalSlabCrafting(ModBlocks.BRICK_VERTICAL_SLAB, Blocks.BRICKS);
        verticalSlabCrafting(ModBlocks.MUD_BRICK_VERTICAL_SLAB, Blocks.MUD_BRICKS);
        verticalSlabCrafting(ModBlocks.RESIN_BRICK_VERTICAL_SLAB, Blocks.RESIN_BRICKS);

        // Generates the recipe for all states that cut copper vertical slabs can come in, for stonecutting from a regular copper block or a cut copper block, AND the crafting table recipe

        // Thank goodness for the zipApply method. Made this so easy to do.

        WeatheringCopperCollection.zipApply(ModBlocks.CUT_COPPER_VERTICAL_SLAB, Blocks.COPPER_BLOCK, (cutSlab, material) -> this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, cutSlab, material, 8));
        WeatheringCopperCollection.zipApply(ModBlocks.CUT_COPPER_VERTICAL_SLAB, Blocks.CUT_COPPER, (cutSlab, material) -> this.stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, cutSlab, material, 2));
        WeatheringCopperCollection.zipApply(ModBlocks.CUT_COPPER_VERTICAL_SLAB, Blocks.CUT_COPPER, this::verticalSlabCrafting);

        ColorCollection.VALUES.forEach(colour ->
                verticalSlabCrafting(ModBlocks.WOOL_VERTICAL_SLAB.pick(colour).get(), Blocks.WOOL.pick(colour)));

        //——————————————————————————VERTICAL SLABS STONECUTTING————————————————————————

        /* All recipes mimic an identical recipe for a horizontal slab */
        verticalSlabStonecutting(ModBlocks.STONE_VERTICAL_SLAB, Blocks.STONE);
        verticalSlabStonecutting(ModBlocks.COBBLESTONE_VERTICAL_SLAB, Blocks.STONE);
        verticalSlabStonecutting(ModBlocks.STONE_BRICK_VERTICAL_SLAB, Blocks.STONE);
        verticalSlabStonecutting(ModBlocks.COBBLESTONE_VERTICAL_SLAB, Blocks.COBBLESTONE);
        verticalSlabStonecutting(ModBlocks.STONE_BRICK_VERTICAL_SLAB, Blocks.STONE_BRICKS);
        verticalSlabStonecutting(ModBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB, Blocks.MOSSY_COBBLESTONE);
        verticalSlabStonecutting(ModBlocks.SMOOTH_STONE_VERTICAL_SLAB, Blocks.SMOOTH_STONE);
        verticalSlabStonecutting(ModBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB, Blocks.DEEPSLATE);
        verticalSlabStonecutting(ModBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB, Blocks.DEEPSLATE);
        verticalSlabStonecutting(ModBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.DEEPSLATE);
        verticalSlabStonecutting(ModBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.DEEPSLATE);
        verticalSlabStonecutting(ModBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB, Blocks.COBBLED_DEEPSLATE);
        verticalSlabStonecutting(ModBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);
        verticalSlabStonecutting(ModBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.DEEPSLATE_BRICKS);
        verticalSlabStonecutting(ModBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.DEEPSLATE_TILES);
        verticalSlabStonecutting(ModBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);
        verticalSlabStonecutting(ModBlocks.DEEPSLATE_TILE_VERTICAL_SLAB, Blocks.POLISHED_DEEPSLATE);

        verticalSlabStonecutting(ModBlocks.GRANITE_VERTICAL_SLAB, Blocks.GRANITE);
        verticalSlabStonecutting(ModBlocks.POLISHED_GRANITE_VERTICAL_SLAB, Blocks.GRANITE);
        verticalSlabStonecutting(ModBlocks.POLISHED_GRANITE_VERTICAL_SLAB, Blocks.POLISHED_GRANITE);
        verticalSlabStonecutting(ModBlocks.DIORITE_VERTICAL_SLAB, Blocks.DIORITE);
        verticalSlabStonecutting(ModBlocks.POLISHED_DIORITE_VERTICAL_SLAB, Blocks.DIORITE);
        verticalSlabStonecutting(ModBlocks.POLISHED_DIORITE_VERTICAL_SLAB, Blocks.POLISHED_DIORITE);
        verticalSlabStonecutting(ModBlocks.ANDESITE_VERTICAL_SLAB, Blocks.ANDESITE);
        verticalSlabStonecutting(ModBlocks.POLISHED_ANDESITE_VERTICAL_SLAB, Blocks.ANDESITE);
        verticalSlabStonecutting(ModBlocks.POLISHED_ANDESITE_VERTICAL_SLAB, Blocks.POLISHED_ANDESITE);
        verticalSlabStonecutting(ModBlocks.TUFF_VERTICAL_SLAB, Blocks.TUFF);
        verticalSlabStonecutting(ModBlocks.POLISHED_TUFF_VERTICAL_SLAB, Blocks.TUFF);
        verticalSlabStonecutting(ModBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.TUFF);
        verticalSlabStonecutting(ModBlocks.POLISHED_TUFF_VERTICAL_SLAB, Blocks.POLISHED_TUFF);
        verticalSlabStonecutting(ModBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.TUFF_BRICKS);
        verticalSlabStonecutting(ModBlocks.TUFF_BRICK_VERTICAL_SLAB, Blocks.POLISHED_TUFF);

        verticalSlabStonecutting(ModBlocks.SANDSTONE_VERTICAL_SLAB, Blocks.SANDSTONE);
        verticalSlabStonecutting(ModBlocks.CUT_SANDSTONE_VERTICAL_SLAB, Blocks.SANDSTONE);
        verticalSlabStonecutting(ModBlocks.CUT_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_SANDSTONE);
        verticalSlabStonecutting(ModBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_SANDSTONE);
        verticalSlabStonecutting(ModBlocks.RED_SANDSTONE_VERTICAL_SLAB, Blocks.RED_SANDSTONE);
        verticalSlabStonecutting(ModBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB, Blocks.RED_SANDSTONE);
        verticalSlabStonecutting(ModBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB, Blocks.CUT_RED_SANDSTONE);
        verticalSlabStonecutting(ModBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB, Blocks.SMOOTH_RED_SANDSTONE);

        verticalSlabStonecutting(ModBlocks.SULFUR_VERTICAL_SLAB, Blocks.SULFUR);
        verticalSlabStonecutting(ModBlocks.POLISHED_SULFUR_VERTICAL_SLAB, Blocks.SULFUR);
        verticalSlabStonecutting(ModBlocks.SULFUR_BRICK_VERTICAL_SLAB, Blocks.SULFUR);
        verticalSlabStonecutting(ModBlocks.POLISHED_SULFUR_VERTICAL_SLAB, Blocks.POLISHED_SULFUR);
        verticalSlabStonecutting(ModBlocks.SULFUR_BRICK_VERTICAL_SLAB, Blocks.SULFUR_BRICKS);
        verticalSlabStonecutting(ModBlocks.CINNABAR_VERTICAL_SLAB, Blocks.CINNABAR);
        verticalSlabStonecutting(ModBlocks.POLISHED_CINNABAR_VERTICAL_SLAB, Blocks.CINNABAR);
        verticalSlabStonecutting(ModBlocks.CINNABAR_BRICK_VERTICAL_SLAB, Blocks.CINNABAR);
        verticalSlabStonecutting(ModBlocks.POLISHED_CINNABAR_VERTICAL_SLAB, Blocks.POLISHED_CINNABAR);
        verticalSlabStonecutting(ModBlocks.CINNABAR_BRICK_VERTICAL_SLAB, Blocks.CINNABAR_BRICKS);

        verticalSlabStonecutting(ModBlocks.NETHER_BRICK_VERTICAL_SLAB, Blocks.NETHER_BRICKS);
        verticalSlabStonecutting(ModBlocks.RED_NETHER_BRICK_VERTICAL_SLAB, Blocks.RED_NETHER_BRICKS);
        verticalSlabStonecutting(ModBlocks.BLACKSTONE_VERTICAL_SLAB, Blocks.BLACKSTONE);
        verticalSlabStonecutting(ModBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB, Blocks.BLACKSTONE);
        verticalSlabStonecutting(ModBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.BLACKSTONE);
        verticalSlabStonecutting(ModBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE);
        verticalSlabStonecutting(ModBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE);
        verticalSlabStonecutting(ModBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB, Blocks.POLISHED_BLACKSTONE_BRICKS);
        verticalSlabStonecutting(ModBlocks.END_STONE_BRICK_VERTICAL_SLAB, Blocks.END_STONE);
        verticalSlabStonecutting(ModBlocks.END_STONE_BRICK_VERTICAL_SLAB, Blocks.END_STONE_BRICKS);
        verticalSlabStonecutting(ModBlocks.PURPUR_VERTICAL_SLAB, Blocks.PURPUR_BLOCK);
        verticalSlabStonecutting(ModBlocks.QUARTZ_VERTICAL_SLAB, Blocks.QUARTZ_BLOCK);
        verticalSlabStonecutting(ModBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB, Blocks.SMOOTH_QUARTZ);

        verticalSlabStonecutting(ModBlocks.PRISMARINE_VERTICAL_SLAB, Blocks.PRISMARINE);
        verticalSlabStonecutting(ModBlocks.PRISMARINE_BRICK_VERTICAL_SLAB, Blocks.PRISMARINE_BRICKS);
        verticalSlabStonecutting(ModBlocks.DARK_PRISMARINE_VERTICAL_SLAB, Blocks.DARK_PRISMARINE);
        verticalSlabStonecutting(ModBlocks.BRICK_VERTICAL_SLAB, Blocks.BRICKS);
        verticalSlabStonecutting(ModBlocks.MUD_BRICK_VERTICAL_SLAB, Blocks.MUD_BRICKS);
        verticalSlabStonecutting(ModBlocks.RESIN_BRICK_VERTICAL_SLAB, Blocks.RESIN_BRICKS);
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
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(base), this.tag(ModItemTags.BISMUTH_TOOL_MATERIALS), category, result).unlocks("has_bismuth_ingot", this.has(ModItemTags.BISMUTH_TOOL_MATERIALS)).save(this.output, getItemName(result) + "_smithing");
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
                .save(output, getConversionRecipeName(verticalSlab, fullBlock) + "_stonecutting");
    }
}

