package com.mohigster.morefeatures.block.family;

import com.google.common.collect.Maps;
import com.mohigster.morefeatures.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies{
    private static BlockFamily bloodwoodFamily;
    private static BlockFamily taintedFamily;
    private static BlockFamily palmFamily;
    private static BlockFamily decrepitFamily;
    private static BlockFamily pallidFamily;
    private static final List<BlockFamily> FAMILIES = new ArrayList<>();

    public static BlockFamily getBloodwoodFamily(){
        if (bloodwoodFamily == null) {
            bloodwoodFamily = new BlockFamily.Builder(ModBlocks.BLOODWOOD_PLANKS.get())
                    .stairs(ModBlocks.BLOODWOOD_STAIRS.get())
                    .slab(ModBlocks.BLOODWOOD_SLAB.get())
                    .strippedLog(ModBlocks.STRIPPED_BLOODWOOD_LOG.get())
                    .hangingSign(ModBlocks.BLOODWOOD_HANGING_SIGN.get(), ModBlocks.BLOODWOOD_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            FAMILIES.add(bloodwoodFamily);
        }
        return bloodwoodFamily;
    }

    public static BlockFamily getTaintedFamily(){
        if (taintedFamily == null) {
            taintedFamily = new BlockFamily.Builder(ModBlocks.TAINTED_PLANKS.get())
                    .stairs(ModBlocks.TAINTED_STAIRS.get())
                    .slab(ModBlocks.TAINTED_SLAB.get())
                    .strippedLog(ModBlocks.STRIPPED_TAINTED_LOG.get())
                    .hangingSign(ModBlocks.TAINTED_HANGING_SIGN.get(), ModBlocks.TAINTED_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            FAMILIES.add(taintedFamily);
        }
        return taintedFamily;
    }

    public static BlockFamily getPalmFamily() {
        if (palmFamily == null) {
            palmFamily = new BlockFamily.Builder(ModBlocks.PALM_PLANKS.get())
                    .stairs(ModBlocks.PALM_STAIRS.get())
                    .slab(ModBlocks.PALM_SLAB.get())
                    .strippedLog(ModBlocks.STRIPPED_PALM_LOG.get())
                    .pressurePlate(ModBlocks.PALM_PRESSURE_PLATE.get())
                    .button(ModBlocks.PALM_BUTTON.get())
                    .fence(ModBlocks.PALM_FENCE.get())
                    .fenceGate(ModBlocks.PALM_FENCE_GATE.get())
                    .trapdoor(ModBlocks.PALM_TRAPDOOR.get())
                    .door(ModBlocks.PALM_DOOR.get())
                    .sign(ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get())
                    .hangingSign(ModBlocks.PALM_HANGING_SIGN.get(), ModBlocks.PALM_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            FAMILIES.add(palmFamily);
        }
        return palmFamily;
    }

    public static BlockFamily getDecrepitFamily() {
        if (decrepitFamily == null) {
            decrepitFamily = new BlockFamily.Builder(ModBlocks.DECREPIT_PLANKS.get())
                    .stairs(ModBlocks.DECREPIT_STAIRS.get())
                    .slab(ModBlocks.DECREPIT_SLAB.get())
                    .strippedLog(ModBlocks.STRIPPED_DECREPIT_LOG.get())
                    .pressurePlate(ModBlocks.DECREPIT_PRESSURE_PLATE.get())
                    .button(ModBlocks.DECREPIT_BUTTON.get())
                    .fence(ModBlocks.DECREPIT_FENCE.get())
                    .fenceGate(ModBlocks.DECREPIT_FENCE_GATE.get())
                    .sign(ModBlocks.DECREPIT_SIGN.get(), ModBlocks.DECREPIT_WALL_SIGN.get())
                    .hangingSign(ModBlocks.DECREPIT_HANGING_SIGN.get(), ModBlocks.DECREPIT_WALL_HANGING_SIGN.get())
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            FAMILIES.add(decrepitFamily);
        }
        return decrepitFamily;
    }

    public static BlockFamily getPallidFamily() {
        if (pallidFamily == null) {
            pallidFamily = new BlockFamily.Builder(ModBlocks.PALLID_PLANKS.get())
                    .stairs(ModBlocks.PALLID_STAIRS.get())
                    .slab(ModBlocks.PALLID_SLAB.get())
                    .pressurePlate(ModBlocks.PALLID_PRESSURE_PLATE.get())
                    .button(ModBlocks.PALLID_BUTTON.get())
                    .fence(ModBlocks.PALLID_FENCE.get())
                    .fenceGate(ModBlocks.PALLID_FENCE_GATE.get())
                    .sign(ModBlocks.PALLID_SIGN.get(), ModBlocks.PALLID_WALL_SIGN.get())
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
