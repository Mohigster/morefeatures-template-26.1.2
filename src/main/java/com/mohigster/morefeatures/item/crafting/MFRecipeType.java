package com.mohigster.morefeatures.item.crafting;

import com.mohigster.morefeatures.recipe.custom.CompressionRecipe;
import com.mohigster.morefeatures.data.references.MFIdentifier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

@SuppressWarnings({"unused", "rawtypes"})
public interface MFRecipeType extends RecipeType {
    RecipeType<CompressionRecipe> COMPRESSION = register("compression");

    static <T extends Recipe<?>> RecipeType<T> register(final String name) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, MFIdentifier.withMfNamespace(name), new RecipeType<T>() {
            public String toString() {
                return name;
            }
        });
    }
}
