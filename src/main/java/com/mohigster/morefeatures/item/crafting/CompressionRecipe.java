package com.mohigster.morefeatures.item.crafting;

import com.mohigster.morefeatures.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import static net.neoforged.neoforge.client.color.item.FluidContentsTint.MAP_CODEC;

public class CompressionRecipe extends AbstractCookingRecipe {

    public static final RecipeSerializer<CompressionRecipe> SERIALIZER = new RecipeSerializer(MAP_CODEC, STREAM_CODEC);

    public CompressionRecipe(
            final CommonInfo commonInfo,
            final CookingBookInfo bookInfo,
            final Ingredient ingredient,
            final ItemStackTemplate result,
            final float experience,
            final int cookingTime
    ) {
        super(commonInfo, bookInfo, ingredient, result, experience, cookingTime);
    }


    @Override
    public RecipeSerializer<? extends AbstractCookingRecipe> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<? extends AbstractCookingRecipe> getType() {
        return ModRecipeType.COMPRESSION;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }

    @Override
    protected Item furnaceIcon() {
        return ModBlocks.COMPRESSOR_BLOCK.get().asItem();
    }
}
