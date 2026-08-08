package com.mohigster.morefeatures.block.family;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.gemstone.GemstoneCollection;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import net.minecraft.data.BlockFamily;

import java.util.ArrayList;
import java.util.List;

public class MFBlockFamilies {
    private static GemstoneCollection<BlockFamily> gemstoneFamily;
    private static BlockFamily azuriteFamily;
    private static BlockFamily fluoriteFamily;
    private static WoodTypeCollection<BlockFamily> woodFamily;
    private static final List<BlockFamily> FAMILIES = new ArrayList<>();

    public static BlockFamily getAzuriteFamily(){
        if(azuriteFamily == null) {
            azuriteFamily = new BlockFamily.Builder(MFBlocks.AZURITE_BLOCK.get())
                    .stairs(MFBlocks.AZURITE_STAIRS.get())
                    .strippedLog(MFBlocks.RAW_AZURITE_BLOCK.get())
                    .slab(MFBlocks.AZURITE_SLAB.get())
                    .fence(MFBlocks.AZURITE_FENCE.get())
                    .fenceGate(MFBlocks.AZURITE_FENCE_GATE.get())
                    .button(MFBlocks.AZURITE_BUTTON.get())
                    .pressurePlate(MFBlocks.AZURITE_PRESSURE_PLATE.get())
                    .wall(MFBlocks.AZURITE_WALL.get())
                    .door(MFBlocks.AZURITE_DOOR.get())
                    .trapdoor(MFBlocks.AZURITE_TRAPDOOR.get())
                    .sign(MFBlocks.AZURITE_SIGN.get(), MFBlocks.AZURITE_WALL_SIGN.get())
                    .hangingSign(MFBlocks.AZURITE_HANGING_SIGN.get(), MFBlocks.AZURITE_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("azurite")
                    .recipeUnlockedBy("has_azurite")
                    .getFamily();
        }
        return azuriteFamily;
    }

//    public static GemstoneCollection<BlockFamily> getGemstoneFamily() {
//        if(gemstoneFamily == null) {
//            gemstoneFamily = GemstoneCollection.GEMS.map(
//                    gem -> new BlockFamily.Builder()
//            );
//        }
//    }

    public static BlockFamily getFluoriteFamily(){
        if(fluoriteFamily == null) {
            fluoriteFamily = new BlockFamily.Builder(MFBlocks.FLUORITE_BLOCK.get())
                    .stairs(MFBlocks.FLUORITE_STAIRS.get())
                    .strippedLog(MFBlocks.RAW_FLUORITE_BLOCK.get())
                    .slab(MFBlocks.FLUORITE_SLAB.get())
                    .fence(MFBlocks.FLUORITE_FENCE.get())
                    .fenceGate(MFBlocks.FLUORITE_FENCE_GATE.get())
                    .button(MFBlocks.FLUORITE_BUTTON.get())
                    .pressurePlate(MFBlocks.FLUORITE_PRESSURE_PLATE.get())
                    .wall(MFBlocks.FLUORITE_WALL.get())
                    .door(MFBlocks.FLUORITE_DOOR.get())
                    .trapdoor(MFBlocks.FLUORITE_TRAPDOOR.get())
                    .sign(MFBlocks.FLUORITE_SIGN.get(), MFBlocks.FLUORITE_WALL_SIGN.get())
                    .hangingSign(MFBlocks.FLUORITE_HANGING_SIGN.get(), MFBlocks.FLUORITE_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("fluorite")
                    .recipeUnlockedBy("has_fluorite")
                    .generateStonecutterRecipe()
                    .getFamily();
        }
        return fluoriteFamily;
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

    // Azurite and fluorite has special recipes declared in the Recipes class.
    // This method is used to grab all block families so that the RecipeProvider
    // can generate recipes without manual input. Since Azurite and Fluorite
    // fences, signs, etc. are crafted using custom recipes, they are not called here.
    public static List<BlockFamily> getAllNonGemstoneFamilies() {
        getWoodFamily().asList();
        return FAMILIES;
    }
}
