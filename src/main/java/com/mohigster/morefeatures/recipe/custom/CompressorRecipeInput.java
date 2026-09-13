package com.mohigster.morefeatures.recipe.custom;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;
import org.jspecify.annotations.NullMarked;

public record CompressorRecipeInput(ItemStack input) implements RecipeInput {
    @NullMarked
    @Override
    public ItemStack getItem(int index) {
        return this.input;
    }

    @Override
    public int size() {
        return 1;
    }
}
