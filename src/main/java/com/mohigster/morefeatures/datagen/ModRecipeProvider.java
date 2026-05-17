package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.tag.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;

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
            return "TutorialMod Recipes";
        }
    }

    @Override
    protected void buildRecipes(){


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
                .requires(ModBlocks.BLOODWOOD_LOG.get())
                .unlockedBy(getHasName(ModBlocks.BLOODWOOD_LOG.get()), has(ModBlocks.BLOODWOOD_LOG.get()))
                .group("bloodwood_planks")
                .save(output, "morefeatures:bloodwood_planks_from_log");

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODWOOD_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                .unlockedBy(getHasName(ModBlocks.BLOODWOOD_LOG.get()), has(ModBlocks.BLOODWOOD_LOG.get()))
                .group("bloodwood_planks")
                .save(output, "morefeatures:bloodwood_planks_from_stripped_log");

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODWOOD_PLANKS.get(), 4)
                .requires(ModBlocks.BLOODWOOD.get())
                .unlockedBy(getHasName(ModBlocks.BLOODWOOD.get()), has(ModBlocks.BLOODWOOD.get()))
                .group("bloodwood_planks")
                .save(output, "morefeatures:bloodwood_planks_from_wood");

        shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODWOOD_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_BLOODWOOD.get())
                .unlockedBy(getHasName(ModBlocks.BLOODWOOD.get()), has(ModBlocks.BLOODWOOD))
                .group("bloodwood_planks")
                .save(output, "morefeatures:bloodwood_planks_from_stripped_wood");

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

        shaped(RecipeCategory.MISC, Items.STICK, 4)
                .pattern("P")
                .pattern("P")
                .define('P', ModBlocks.BLOODWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.BLOODWOOD_PLANKS.get()), has(ModBlocks.BLOODWOOD_PLANKS))
                .group("stick")
                .save(output, "morefeatures:sticks_from_bloodwood_planks");

        shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("PP")
                .pattern("PP")
                .define('P', ModBlocks.BLOODWOOD_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.BLOODWOOD_PLANKS.get()), has(ModBlocks.BLOODWOOD_PLANKS))
                .group("crafting_table")
                .save(output, "morefeatures:crafting_table_from_bloodwood_planks");

//        stairBuilder(ModBlocks.BLOODWOOD_STAIRS.get(), Ingredient.of(ModBlocks.BLOODWOOD_PLANKS))
//                .unlockedBy(getHasName(ModBlocks.BLOODWOOD_PLANKS), has(ModBlocks.BLOODWOOD_PLANKS))
//                .group("bloodwood_stairs")
//                .save(output);

//        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODWOOD_SLAB, ModBlocks.BLOODWOOD_PLANKS);



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
                .pattern("BEB")
                .pattern("NFN")
                .define('B', ModItems.BISMUTH.get())
                .define('N', Items.NETHERITE_SCRAP)
                .define('E', Blocks.END_STONE.asItem())
                .define('F', Blocks.NETHERRACK.asItem())
                .define('D', Items.DIAMOND)
                .unlockedBy(getHasName(Items.NETHERITE_SCRAP), has(Items.NETHERITE_SCRAP))
                .group("magic")
                .save(output);

        // Metal detector recipe

        shaped(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR)
                .pattern("  S")
                .pattern("MS ")
                .pattern("BMI")
                .pattern("  S")
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


        // Bismuth smithing recipes

        bismuthSmithing(Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(0).asItem());
        bismuthSmithing(Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(4).asItem());
        bismuthSmithing(Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(5).asItem());
        bismuthSmithing(Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(6).asItem());
        bismuthSmithing(Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.BISMUTH_EQUIPMENT.get(7).asItem());


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
}
