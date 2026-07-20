package com.mohigster.morefeatures.compat;

import com.mohigster.morefeatures.recipe.custom.CompressionRecipe;
import com.mohigster.morefeatures.references.MFIdentifier;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

public class MFJEIRecipeTypes {
    public static final IRecipeType<RecipeHolder<CompressionRecipe>> COMPRESSION =
            create("compressing", CompressionRecipe.class);


    // From Occultism: https://github.com/klikli-dev/occultism/blob/version/26.1.2/src/main/java/com/klikli_dev/occultism/integration/jei/impl/JeiRecipeTypes.java
    // Under MIT-License
    @SuppressWarnings("unused")
    public static <R extends Recipe<?>> IRecipeType<RecipeHolder<R>> create(String name, Class<? extends R> recipeClass) {
        Identifier uid = MFIdentifier.withMfNamespace(name);
        @SuppressWarnings({"unchecked", "RedundantCast"})
        Class<? extends RecipeHolder<R>> holderClass = (Class<? extends RecipeHolder<R>>) (Object) RecipeHolder.class;
        return IRecipeType.create(uid, holderClass);
    }
}
