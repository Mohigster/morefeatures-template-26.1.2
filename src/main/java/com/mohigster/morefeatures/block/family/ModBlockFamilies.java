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
    private static BlockFamily palmFamily;
    private static BlockFamily decrepitFamily;
    private static BlockFamily pallidFamily;
    private static final List<BlockFamily> FAMILIES = new ArrayList<>();

    public static BlockFamily getPalmFamily() {
        if (palmFamily == null) {
            palmFamily = new BlockFamily.Builder(ModBlocks.PALM_PLANKS.get())
                    .stairs(ModBlocks.PALM_STAIRS.get())
                    .slab(ModBlocks.PALM_SLAB.get())
                    .pressurePlate(ModBlocks.PALM_PRESSURE_PLATE.get())
                    .button(ModBlocks.PALM_BUTTON.get())
                    .fence(ModBlocks.PALM_FENCE.get())
                    .fenceGate(ModBlocks.PALM_FENCE_GATE.get())
                    .trapdoor(ModBlocks.PALM_TRAPDOOR.get())
                    .door(ModBlocks.PALM_DOOR.get())
                    .sign(ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get())
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
                    .pressurePlate(ModBlocks.DECREPIT_PRESSURE_PLATE.get())
                    .button(ModBlocks.DECREPIT_BUTTON.get())
                    .fence(ModBlocks.DECREPIT_FENCE.get())
                    .fenceGate(ModBlocks.DECREPIT_FENCE_GATE.get())
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
                    .recipeGroupPrefix("wooden")
                    .recipeUnlockedBy("has_planks")
                    .getFamily();
            FAMILIES.add(pallidFamily);
        }
        return pallidFamily;
    }

    public static List<BlockFamily> getAllFamilies() {
        getPalmFamily();
        getDecrepitFamily();
        getPallidFamily();
        return FAMILIES;
    }
}
