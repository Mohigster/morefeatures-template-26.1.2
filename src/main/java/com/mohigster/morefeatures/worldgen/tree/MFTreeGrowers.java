package com.mohigster.morefeatures.worldgen.tree;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.worldgen.MFConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;



public class MFTreeGrowers {

    public static final TreeGrower BLOODWOOD = new TreeGrower(
            MoreFeatures.MODID + "bloodwood",
            0.1f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(MFConfiguredFeatures.SMALL_BLOODWOOD_KEY),
            Optional.of(MFConfiguredFeatures.BLOODWOOD_KEY),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower TAINTED = new TreeGrower(
            MoreFeatures.MODID + "tainted",
            0.1f,
            Optional.empty(),
            Optional.empty(),
            Optional.of(MFConfiguredFeatures.SMALL_TAINTED_KEY),
            Optional.of(MFConfiguredFeatures.TAINTED_KEY),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower PALM = new TreeGrower(
            MoreFeatures.MODID + "palm",
            Optional.empty(), Optional.of(MFConfiguredFeatures.PALM_TREE_KEY), Optional.empty()
    );

    public static final TreeGrower DECREPIT = new TreeGrower(
            MoreFeatures.MODID + "decrepit",
            Optional.of(MFConfiguredFeatures.DECREPIT_KEY), Optional.empty(), Optional.empty()
    );
    public static final TreeGrower PALLID = new TreeGrower(
            MoreFeatures.MODID + "decrepit",
            Optional.of(MFConfiguredFeatures.PALLID_KEY), Optional.empty(), Optional.empty()
    );
}
