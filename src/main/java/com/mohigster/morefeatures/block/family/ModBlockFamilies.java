package com.mohigster.morefeatures.block.family;

import com.google.common.collect.Maps;
import com.mohigster.morefeatures.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies{
    private static BlockFamily palmFamily;

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
        }
        return palmFamily;
    }
}
