package com.mohigster.morefeatures.block.family;

import com.mohigster.morefeatures.block.MFBlocks;
import net.minecraft.data.BlockFamily;

import java.util.ArrayList;
import java.util.List;

public class MFBlockFamilies {
    private static BlockFamily azuriteFamily;
    private static BlockFamily fluoriteFamily;
    private static BlockFamily bloodwoodFamily;
    private static BlockFamily taintedFamily;
    private static BlockFamily palmFamily;
    private static BlockFamily decrepitFamily;
    private static BlockFamily pallidFamily;
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
                    .getFamily();
        }
        return fluoriteFamily;
    }

    public static BlockFamily getBloodwoodFamily(){
        if (bloodwoodFamily == null) {
            bloodwoodFamily = new BlockFamily.Builder(MFBlocks.BLOODWOOD_PLANKS.get())
                    .stairs(MFBlocks.BLOODWOOD_STAIRS.get())
                    .slab(MFBlocks.BLOODWOOD_SLAB.get())
                    .log(MFBlocks.BLOODWOOD_LOG.get())
                    .strippedLog(MFBlocks.STRIPPED_BLOODWOOD_LOG.get())
                    .fence(MFBlocks.BLOODWOOD_FENCE.get())
                    .fenceGate(MFBlocks.BLOODWOOD_FENCE_GATE.get())
                    .pressurePlate(MFBlocks.BLOODWOOD_PRESSURE_PLATE.get())
                    .button(MFBlocks.BLOODWOOD_BUTTON.get())
                    .sign(MFBlocks.BLOODWOOD_SIGN.get(), MFBlocks.BLOODWOOD_WALL_SIGN.get())
                    .hangingSign(MFBlocks.BLOODWOOD_HANGING_SIGN.get(), MFBlocks.BLOODWOOD_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            FAMILIES.add(bloodwoodFamily);
        }
        return bloodwoodFamily;
    }

    public static BlockFamily getTaintedFamily(){
        if (taintedFamily == null) {
            taintedFamily = new BlockFamily.Builder(MFBlocks.TAINTED_PLANKS.get())
                    .stairs(MFBlocks.TAINTED_STAIRS.get())
                    .slab(MFBlocks.TAINTED_SLAB.get())
                    .log(MFBlocks.TAINTED_LOG.get())
                    .strippedLog(MFBlocks.STRIPPED_TAINTED_LOG.get())
                    .fence(MFBlocks.TAINTED_FENCE.get())
                    .fenceGate(MFBlocks.TAINTED_FENCE_GATE.get())
                    .pressurePlate(MFBlocks.TAINTED_PRESSURE_PLATE.get())
                    .button(MFBlocks.TAINTED_BUTTON.get())
                    .sign(MFBlocks.TAINTED_SIGN.get(), MFBlocks.TAINTED_WALL_SIGN.get())
                    .hangingSign(MFBlocks.TAINTED_HANGING_SIGN.get(), MFBlocks.TAINTED_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            FAMILIES.add(taintedFamily);
        }
        return taintedFamily;
    }

    public static BlockFamily getPalmFamily() {
        if (palmFamily == null) {
            palmFamily = new BlockFamily.Builder(MFBlocks.PALM_PLANKS.get())
                    .stairs(MFBlocks.PALM_STAIRS.get())
                    .slab(MFBlocks.PALM_SLAB.get())
                    .log(MFBlocks.PALM_LOG.get())
                    .strippedLog(MFBlocks.STRIPPED_PALM_LOG.get())
                    .pressurePlate(MFBlocks.PALM_PRESSURE_PLATE.get())
                    .button(MFBlocks.PALM_BUTTON.get())
                    .fence(MFBlocks.PALM_FENCE.get())
                    .fenceGate(MFBlocks.PALM_FENCE_GATE.get())
                    .trapdoor(MFBlocks.PALM_TRAPDOOR.get())
                    .door(MFBlocks.PALM_DOOR.get())
                    .sign(MFBlocks.PALM_SIGN.get(), MFBlocks.PALM_WALL_SIGN.get())
                    .hangingSign(MFBlocks.PALM_HANGING_SIGN.get(), MFBlocks.PALM_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            FAMILIES.add(palmFamily);
        }
        return palmFamily;
    }

    public static BlockFamily getDecrepitFamily() {
        if (decrepitFamily == null) {
            decrepitFamily = new BlockFamily.Builder(MFBlocks.DECREPIT_PLANKS.get())
                    .stairs(MFBlocks.DECREPIT_STAIRS.get())
                    .slab(MFBlocks.DECREPIT_SLAB.get())
                    .log(MFBlocks.DECREPIT_LOG.get())
                    .strippedLog(MFBlocks.STRIPPED_DECREPIT_LOG.get())
                    .pressurePlate(MFBlocks.DECREPIT_PRESSURE_PLATE.get())
                    .button(MFBlocks.DECREPIT_BUTTON.get())
                    .fence(MFBlocks.DECREPIT_FENCE.get())
                    .fenceGate(MFBlocks.DECREPIT_FENCE_GATE.get())
                    .sign(MFBlocks.DECREPIT_SIGN.get(), MFBlocks.DECREPIT_WALL_SIGN.get())
                    .hangingSign(MFBlocks.DECREPIT_HANGING_SIGN.get(), MFBlocks.DECREPIT_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            FAMILIES.add(decrepitFamily);
        }
        return decrepitFamily;
    }

    public static BlockFamily getPallidFamily() {
        if (pallidFamily == null) {
            pallidFamily = new BlockFamily.Builder(MFBlocks.PALLID_PLANKS.get())
                    .stairs(MFBlocks.PALLID_STAIRS.get())
                    .slab(MFBlocks.PALLID_SLAB.get())
                    .log(MFBlocks.PALLID_LOG.get())
                    .strippedLog(MFBlocks.STRIPPED_PALLID_LOG.get())
                    .pressurePlate(MFBlocks.PALLID_PRESSURE_PLATE.get())
                    .button(MFBlocks.PALLID_BUTTON.get())
                    .fence(MFBlocks.PALLID_FENCE.get())
                    .fenceGate(MFBlocks.PALLID_FENCE_GATE.get())
                    .sign(MFBlocks.PALLID_SIGN.get(), MFBlocks.PALLID_WALL_SIGN.get())
                    .hangingSign(MFBlocks.PALLID_HANGING_SIGN.get(), MFBlocks.PALLID_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            FAMILIES.add(pallidFamily);
        }
        return pallidFamily;
    }

    public static List<BlockFamily> getAllFamilies() {
        getBloodwoodFamily();
        getTaintedFamily();
        getPalmFamily();
        getDecrepitFamily();
        getPallidFamily();
        return FAMILIES;
    }
}
