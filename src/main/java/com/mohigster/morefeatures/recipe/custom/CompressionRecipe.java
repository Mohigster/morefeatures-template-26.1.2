package com.mohigster.morefeatures.recipe.custom;

import com.mohigster.morefeatures.recipe.MFRecipes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NullMarked;

public record CompressionRecipe(Ingredient inputItem, ItemStackTemplate output) implements Recipe<CompressorRecipeInput> {
    public static final MapCodec<CompressionRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(CompressionRecipe::inputItem),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(CompressionRecipe::output)
            ).apply(instance, CompressionRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, CompressionRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC,
                    CompressionRecipe::inputItem,

                    ItemStackTemplate.STREAM_CODEC,
                    CompressionRecipe::output,

                    CompressionRecipe::new
            );

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.inputItem);
        return list;
    }

    @NullMarked
    @Override
    public boolean matches(CompressorRecipeInput input, Level level) {
        if(level.isClientSide()) {
            return false;
        }

        return this.inputItem.test(input.getItem(0));
    }

    @NullMarked
    @Override
    public ItemStack assemble(CompressorRecipeInput input) {
        return this.output.create().copy();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @NullMarked
    @Override
    public String group() {
        return "Compressing";
    }

    @NullMarked
    @Override
    public RecipeSerializer<? extends Recipe<CompressorRecipeInput>> getSerializer() {
        return MFRecipes.COMPRESSOR_SERIALIZER.get();
    }

    @NullMarked
    @Override
    public RecipeType<? extends Recipe<CompressorRecipeInput>> getType() {
        return MFRecipes.COMPRESSOR_TYPE.get();
    }

    @NullMarked
    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @NullMarked
    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
}
