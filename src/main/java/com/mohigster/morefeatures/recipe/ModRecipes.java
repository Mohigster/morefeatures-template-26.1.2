package com.mohigster.morefeatures.recipe;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.recipe.custom.CompressionRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, MoreFeatures.MODID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, MoreFeatures.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CompressionRecipe>> COMPRESSOR_SERIALIZER =
            SERIALIZERS.register("compressing", () -> new RecipeSerializer<>(CompressionRecipe.CODEC, CompressionRecipe.STREAM_CODEC));
    public static final DeferredHolder<RecipeType<?>, RecipeType<CompressionRecipe>> COMPRESSOR_TYPE =
            TYPES.register("compressing", () -> new RecipeType<CompressionRecipe>() {
                @Override
                public String toString() {
                    return "compressing";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
