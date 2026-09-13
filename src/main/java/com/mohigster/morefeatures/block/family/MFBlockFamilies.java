package com.mohigster.morefeatures.block.family;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.gemstone.GemstoneCollection;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import net.minecraft.data.BlockFamily;

import java.util.ArrayList;
import java.util.List;

public class MFBlockFamilies {
    private static GemstoneCollection<BlockFamily> gemstoneFamily;
    private static WoodTypeCollection<BlockFamily> woodFamily;
    private static final List<BlockFamily> FAMILIES = new ArrayList<>();

    public static GemstoneCollection<BlockFamily> getGemstoneFamily(){
        if (gemstoneFamily == null) {
            gemstoneFamily = GemstoneCollection.GEMS.map(
                    gem -> new BlockFamily.Builder(MFBlocks.GEMSTONE_BLOCK.pick(gem).get())
                            .stairs(MFBlocks.GEMSTONE_STAIRS.pick(gem).get())
                            .strippedLog(MFBlocks.RAW_GEM_BLOCK.pick(gem).get())
                            .slab(MFBlocks.GEMSTONE_SLAB.pick(gem).get())
                            .fence(MFBlocks.GEMSTONE_FENCE.pick(gem).get())
                            .fenceGate(MFBlocks.GEMSTONE_FENCE_GATE.pick(gem).get())
                            .button(MFBlocks.GEMSTONE_BUTTON.pick(gem).get())
                            .pressurePlate(MFBlocks.GEMSTONE_PRESSURE_PLATE.pick(gem).get())
                            .wall(MFBlocks.GEMSTONE_WALL.pick(gem).get())
                            .door(MFBlocks.GEMSTONE_DOOR.pick(gem).get())
                            .trapdoor(MFBlocks.GEMSTONE_TRAPDOOR.pick(gem).get())
                            .sign(MFBlocks.GEMSTONE_SIGN.pick(gem).get(), MFBlocks.GEMSTONE_WALL_SIGN.pick(gem).get())
                            .hangingSign(MFBlocks.GEMSTONE_HANGING_SIGN.pick(gem).get(), MFBlocks.GEMSTONE_WALL_HANGING_SIGN.pick(gem).get())
                            .recipeGroupPrefix("gemstone")
                            .recipeUnlockedBy("has_gemstone")
                            .generateStonecutterRecipe()
                            .getFamily()
            );
            gemstoneFamily.forEach(FAMILIES::add);
        }
        return gemstoneFamily;
    }

    public static WoodTypeCollection<BlockFamily> getWoodFamily() {
        if (woodFamily == null) {
            woodFamily = WoodTypeCollection.SETS.map(
                    set -> new BlockFamily.Builder(MFBlocks.PLANKS.pick(set).get())
                            .stairs(MFBlocks.WOODEN_STAIRS.pick(set).get())
                            .slab(MFBlocks.WOODEN_SLAB.pick(set).get())
                            .log(MFBlocks.LOG.pick(set).get())
                            .strippedLog(MFBlocks.STRIPPED_LOG.pick(set).get())
                            .fence(MFBlocks.WOODEN_FENCE.pick(set).get())
                            .fenceGate(MFBlocks.WOODEN_FENCE_GATE.pick(set).get())
                            .button(MFBlocks.WOODEN_BUTTON.pick(set).get())
                            .pressurePlate(MFBlocks.WOODEN_PRESSURE_PLATE.pick(set).get())
                            .door(MFBlocks.WOODEN_DOOR.pick(set).get())
                            .trapdoor(MFBlocks.WOODEN_TRAPDOOR.pick(set).get())
                            .sign(MFBlocks.WOODEN_SIGN.pick(set).get(), MFBlocks.WOODEN_WALL_SIGN.pick(set).get())
                            .hangingSign(MFBlocks.WOODEN_HANGING_SIGN.pick(set).get(), MFBlocks.WOODEN_WALL_HANGING_SIGN.pick(set).get())
                            .recipeGroupPrefix("wooden")
                            .recipeUnlockedBy("has_planks")
                            .getFamily()
            );
            woodFamily.forEach(FAMILIES::add);
        }
        return woodFamily;
    }

    private static void clearBlockFamilies() {
        FAMILIES.clear();
        woodFamily = null;
        gemstoneFamily = null;
    }

    // Azurite and fluorite has special recipes declared in the Recipes class.
    // This method is used to grab all block families so that the RecipeProvider
    // can generate recipes without manual input. Since Azurite and Fluorite
    // fences, signs, etc. are crafted using custom recipes, they are not called here.
    public static List<BlockFamily> getAllNonGemstoneFamilies() {
        clearBlockFamilies();
        getWoodFamily();

        if (FAMILIES.isEmpty()) {
            throw new IllegalStateException("No block families found! Ensure that the family is properly registered");
        }

        MoreFeatures.LOGGER.debug("Found {} block families!", FAMILIES.size());

        FAMILIES.forEach(family ->  MoreFeatures.LOGGER.debug("Found block family: {}", family.getBaseBlock().asItem().getDescriptionId()));

        return FAMILIES;
    }
}
