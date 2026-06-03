package com.mohigster.morefeatures.worldgen.tree;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;



public class ModTreeGrowers {

    public static final TreeGrower BLOODWOOD = new TreeGrower(
            MoreFeatures.MODID + "bloodwood",
            0.1f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.SMALL_BLOODWOOD_KEY),
            Optional.of(ModConfiguredFeatures.BLOODWOOD_KEY),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower TAINTED = new TreeGrower(
            MoreFeatures.MODID + "tainted",
            0.1f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.SMALL_TAINTED_KEY),
            Optional.of(ModConfiguredFeatures.TAINTED_KEY),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower PALM = new TreeGrower(
            MoreFeatures.MODID + "palm",
            Optional.empty(), Optional.of(ModConfiguredFeatures.PALM_TREE_KEY), Optional.empty()
    );
}
