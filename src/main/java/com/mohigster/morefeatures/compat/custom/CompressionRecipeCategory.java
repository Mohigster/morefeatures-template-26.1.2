package com.mohigster.morefeatures.compat.custom;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.compat.MFJEIRecipeTypes;
import com.mohigster.morefeatures.menu.custom.CompressorScreen;
import com.mohigster.morefeatures.recipe.custom.CompressionRecipe;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import com.mohigster.morefeatures.renderer.FluidTankRenderer;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import javax.annotation.Nullable;
import java.util.List;

public class CompressionRecipeCategory implements IRecipeCategory<RecipeHolder<CompressionRecipe>> {
    public static final Identifier TEXTURE = MFIdentifier.withMfNamespace("textures/gui/compressor/crystallizer_gui.png");
    private final IDrawable icon;
    private final IDrawable overlay;
    private final FluidTankRenderer fluidRenderer;

    public CompressionRecipeCategory(IGuiHelper helper) {
        this.overlay = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MFBlocks.COMPRESSOR_BLOCK));

        fluidRenderer = new FluidTankRenderer(16000, FluidTankRenderer.TooltipMode.SHOW_AMOUNT, 16, 50);
    }

    @NullMarked
    @Override
    public IRecipeType<RecipeHolder<CompressionRecipe>> getRecipeType() {
        return MFJEIRecipeTypes.COMPRESSION;
    }

    @NullMarked
    @Override
    public Component getTitle() {
        return Component.translatable("block.morefeatures.compressor");
    }

    @Override
    public int getWidth() {
        return 176;
    }

    @Override
    public int getHeight() {
        return 85;
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RecipeHolder<CompressionRecipe> recipe, @NonNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).add(recipe.value().inputItem());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).add(recipe.value().output());
    }

    @NullMarked
    @Override
    public void draw(RecipeHolder<CompressionRecipe> recipe, IRecipeSlotsView recipeSlotsView, GuiGraphicsExtractor guiGraphics, double mouseX, double mouseY) {
        this.overlay.draw(guiGraphics, 0, 0);
        guiGraphics.fillGradient(156, 50, 164, 56,0xffb51500, 0xff600b00);
        if(CompressorScreen.isMouseAboveArea((int)mouseX, (int)mouseY, 0, 0, 156, 11, 8, 48)) {
            guiGraphics.setComponentTooltipForNextFrame(Minecraft.getInstance().font, List.of(Component.literal(25 + "FE/T Needed. Total of 7500 FE.")),
                    (int)mouseX, (int)mouseY + 110);
        }
        fluidRenderer.render(guiGraphics, 8, 7, new FluidStack(Fluids.WATER, 1000));
        if(CompressorScreen.isMouseAboveArea((int)mouseX, (int)mouseY, 0, 0, 9, 7, fluidRenderer)) {
            guiGraphics.setComponentTooltipForNextFrame(Minecraft.getInstance().font, fluidRenderer.getTooltip(new FluidStack(Fluids.WATER, 1000)),
                    (int)mouseX + 78, (int)mouseY + 110);
        }
    }
}
