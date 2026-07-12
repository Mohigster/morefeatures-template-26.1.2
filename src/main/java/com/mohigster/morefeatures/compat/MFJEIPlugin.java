package com.mohigster.morefeatures.compat;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.compat.custom.CompressionRecipeCategory;
import com.mohigster.morefeatures.menu.custom.CompressorScreen;
import com.mohigster.morefeatures.recipe.MFRecipes;
import com.mohigster.morefeatures.references.MFIdentifier;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RecipesReceivedEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import org.jspecify.annotations.NonNull;

import java.util.List;

@JeiPlugin
public class MFJEIPlugin implements IModPlugin {
    private static RecipeMap syncedRecipes = RecipeMap.EMPTY;

    @Override
    public @NonNull Identifier getPluginUid() {
        return MFIdentifier.withMfNamespace("jei_plugin");
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    private <I extends RecipeInput, T extends Recipe<I>> List<RecipeHolder<T>> getRecipes(RecipeMap recipeMap, RecipeType<T> type) {
        return (List) recipeMap.byType(type);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CompressionRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(MFJEIRecipeTypes.COMPRESSION, this.getRecipes(syncedRecipes, MFRecipes.COMPRESSOR_TYPE.get()));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CompressorScreen.class, 74, 30, 22, 20,
                MFJEIRecipeTypes.COMPRESSION);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addCraftingStation(MFJEIRecipeTypes.COMPRESSION, new ItemStack(MFBlocks.COMPRESSOR_BLOCK.asItem()));
    }


    @EventBusSubscriber(modid = MoreFeatures.MODID)
    public static class ServerRecipeSync {
        @SubscribeEvent
        public static void onDatapackSync(OnDatapackSyncEvent event) {
            event.sendRecipes(
                    MFRecipes.COMPRESSOR_TYPE.get()
            );
        }
    }

    @EventBusSubscriber(modid = MoreFeatures.MODID, value = Dist.CLIENT)
    public static class ClientRecipeSync {
        @SubscribeEvent
        public static void onRecipeReceived(RecipesReceivedEvent event) {
            syncedRecipes = event.getRecipeMap();
        }
    }
}
