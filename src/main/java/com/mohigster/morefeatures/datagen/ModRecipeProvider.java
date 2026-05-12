package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

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



        //—————————————————————————————SMELTABLE LISTS———————————————————————————————

        List<ItemLike> ALUMINIUM_SMELTABLES = List.of(ModItems.RAW_ALUMINIUM, ModBlocks.ALUMINIUM_ORE, ModBlocks.DEEPSLATE_ALUMINIUM_ORE);
        List<ItemLike> MAGNESIUM_SMELTABLES = List.of(ModItems.RAW_MAGNESIUM, ModBlocks.MAGNESIUM_ORE, ModBlocks.DEEPSLATE_MAGNESIUM_ORE);


        //——————————————————————SMELTING RECIPE DATA GENERATION——————————————————————

        // Aluminium
        oreSmelting(ALUMINIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.ALUMINIUM_INGOT.get(), 0.25f, 200, "aluminium");
        oreBlasting(ALUMINIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.ALUMINIUM_INGOT.get(), 0.25f, 100, "aluminium");

        // Magnesium
        oreSmelting(MAGNESIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.MAGNESIUM_INGOT.get(), 0.25f, 200, "magnesium");
        oreBlasting(MAGNESIUM_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.MAGNESIUM_INGOT.get(), 0.25f, 100, "magnesium");
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
}
