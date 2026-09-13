package com.mohigster.morefeatures.util;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class MFOrePlacementUtil {
    public static List<PlacementModifier> orePlacement(final PlacementModifier pCountPlacement, final PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(final int pCount, final PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    public static List<PlacementModifier> extraOrePlacement(final int pCount, final float pChance, final int pExtra, final PlacementModifier pHeightRange) {
        return orePlacement(PlacementUtils.countExtra(pCount, pChance, pExtra), pHeightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(final int pChance, final PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}
