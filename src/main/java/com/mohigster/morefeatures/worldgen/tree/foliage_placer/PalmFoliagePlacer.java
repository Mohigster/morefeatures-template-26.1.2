package com.mohigster.morefeatures.worldgen.tree.foliage_placer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
            i -> foliagePlacerParts(i).apply(i, PalmFoliagePlacer::new)
    );

    // How many blocks each frond arm extends outward from the crown center.
    private static final int FROND_LENGTH = 4;

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @NullMarked
    @Override
    protected FoliagePlacerType<?> type() {
        return MFFoliagePlacerType.PALM_FOLIAGE_PLACER.get();
    }

    @NullMarked
    @Override
    public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration config) {
        // The crown is essentially flat with a slight droop; no additional height needed.
        return 0;
    }

    @NullMarked
    @Override
    protected void createFoliage(
            WorldGenLevel level,
            FoliageSetter foliageSetter,
            RandomSource random,
            TreeConfiguration config,
            int treeHeight,
            FoliageAttachment foliageAttachment,
            int foliageHeight,
            int leafRadius,
            int offset
    ) {
        boolean doubleTrunk = foliageAttachment.doubleTrunk();
        // Crown center sits just above the trunk tip.
        BlockPos crownCenter = foliageAttachment.pos().above(offset);

        // 1. Dense central tuft at the very top (two layers).
        placeLeavesRow(level, foliageSetter, random, config, crownCenter, 1, 0, doubleTrunk);
        placeLeavesRow(level, foliageSetter, random, config, crownCenter, 0, 1, doubleTrunk);

        // 2. Four frond arms — one per cardinal direction.
        for (Direction dir : new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST}) {
            placeFrondArm(level, foliageSetter, random, config, crownCenter, dir, doubleTrunk);
        }
    }

    private void placeFrondArm(
            WorldGenLevel level,
            FoliageSetter foliageSetter,
            RandomSource random,
            TreeConfiguration config,
            BlockPos crownCenter,
            Direction dir,
            boolean doubleTrunk
    ) {
        int dx = dir.getStepX();
        int dz = dir.getStepZ();

        for (int dist = 1; dist <= FROND_LENGTH; dist++) {
            // Droop: drop 1 block every 2 blocks of outward distance.
            int yDrop = -(dist / 2);

            BlockPos frondPos = crownCenter.offset(dx * dist, yDrop, dz * dist);

            // Cluster radius narrows toward the tip for a tapered frond look.
            int clusterRadius = (dist <= FROND_LENGTH - 2) ? 1 : 0;

            // If the Y-level changes, place a bridge block below the previous distance block.
            if (dist > 1 && yDrop < -((dist - 1) / 2)) {
                // Look back 1 block in the horizontal direction, but stay at the current (dropped) Y level.
                BlockPos bridgePos = frondPos.relative(dir.getOpposite());
                placeLeavesRow(level, foliageSetter, random, config, bridgePos, 0, 0, doubleTrunk);
            }

            placeLeavesRow(level, foliageSetter, random, config, frondPos, clusterRadius, 0, doubleTrunk);
        }
    }

    /**
     * Corner-skipping rule mirrors the acacia pattern:
     * skips outer corners to keep clusters rounded, and at y == 0 skips
     * positions that are diagonal AND not axis-aligned.
     */
    @Override
    protected boolean shouldSkipLocation(
            @NonNull RandomSource random,
            int dx, int y, int dz,
            int currentRadius,
            boolean doubleTrunk
    ) {
        if (y == 0) {
            // Skip true diagonal corners (both dx and dz at max) to round the cluster.
            return (dx > 1 || dz > 1) && dx != 0 && dz != 0;
        }
        // Skip outer diagonal corners at other layers.
        return dx == currentRadius && dz == currentRadius && currentRadius > 0;
    }
}
