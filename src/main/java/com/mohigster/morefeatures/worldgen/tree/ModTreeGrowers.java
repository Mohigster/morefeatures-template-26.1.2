package com.mohigster.morefeatures.worldgen.tree;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;



public class ModTreeGrowers {
    public static final TreeGrower BLOODWOOD = new TreeGrower(MoreFeatures.MODID + "bloodwood",
            Optional.of(ModConfiguredFeatures.BLOODWOOD_KEY), Optional.of(ModConfiguredFeatures.SMALL_BLOODWOOD_KEY), Optional.empty());
}
