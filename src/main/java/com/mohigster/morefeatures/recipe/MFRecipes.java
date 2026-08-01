package com.mohigster.morefeatures.recipe;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.recipe.custom.CompressionRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NonNull;

public class MFRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, MoreFeatures.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, MoreFeatures.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CompressionRecipe>> COMPRESSOR_SERIALIZER =
            SERIALIZERS.register("compressing", () -> new RecipeSerializer<>(CompressionRecipe.CODEC, CompressionRecipe.STREAM_CODEC));

    public static final DeferredHolder<RecipeType<?>, RecipeType<CompressionRecipe>> COMPRESSOR_TYPE =
            registerRecipeType("compressing");

    private static <T extends Record & Recipe<?>> DeferredHolder<RecipeType<?>, @NonNull RecipeType<@NonNull T>> registerRecipeType(String name){
        return TYPES.register(name, () -> new RecipeType<>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Recipes registered -> Performed by: " + MoreFeatures.MODID);
    }
}
