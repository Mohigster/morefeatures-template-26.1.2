package com.mohigster.morefeatures.item.crafting;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface ModRecipeType extends RecipeType {
    RecipeType<CompressionRecipe> COMPRESSION = register("compression");

    static <T extends Recipe<?>> RecipeType<T> register(final String name) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name), new RecipeType<T>() {
            public String toString() {
                return name;
            }
        });
    }
}
