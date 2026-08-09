package com.mohigster.morefeatures.data.world.feature.custom;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.tag.MFBlockTags;
import com.mohigster.morefeatures.data.world.feature.custom.config.OasisConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OasisFeature extends Feature<OasisConfiguration> {
    private int noSurfaceCount = 0;

    private static final int WATER_RADIUS = 4;
    private static final int MAIN_BASE_RADIUS = 7;
    private static final int TREE_ATTEMPTS = 7;
    private static final int TREE_RING_MIN = 5;
    private static final int TREE_RING_MAX = 7;

    private final List<BlockPos> usedPalmSpots = new ArrayList<>();

    public OasisFeature(Codec<OasisConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<OasisConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();
        OasisConfiguration config = context.config();

        usedPalmSpots.clear();

        BlockPos center = findSurface(level, origin);
        if (center == null) return false;

        double uniqueAngle = this.calculateDeformationAngle(center, random);

        if (noSurfaceCount > 0){
            MoreFeatures.LOGGER.debug("Oasis at {} had {} positions without suitable surface", center, noSurfaceCount);
        }

        this.generateOasisPool(config, level, center, uniqueAngle); // Generate the actual pool of water
        this.wallInWater(config, level, center);                    // Prevent holes in the pool wall from causing water to flow out
        this.placeTrees(context, level, random, center);            // Place on average 2-4 trees around the pool
                                                               // NOTE: can generate with only one or none in rough areas, and can theoretically generate 5 or more
        return true;
    }

    private double calculateDeformationAngle(BlockPos center, RandomSource source) {
        Random random = new Random(center.asLong() + source.nextLong());
        return random.nextDouble() * 2 * Math.PI;
    }

    @SuppressWarnings("deprecation")
    private BlockPos findSurface(WorldGenLevel level, BlockPos pos) {
        for (int dy = 0; dy >= -16; dy--) {
            BlockPos check = pos.offset(0, dy, 0);
            if (level.getBlockState(check.below()).isSolid()) return check;
        }
        return null;
    }

    private void generateOasisPool(OasisConfiguration config, WorldGenLevel level, BlockPos center, double uniqueAngle) {
        int fixedWaterY = center.below().getY();
        int maxR = MAIN_BASE_RADIUS + 3;

        BlockState water = Blocks.WATER.defaultBlockState();
        BlockState main = config.mainBaseBlock();
        BlockState exposed = config.exposedBaseBlock();
        BlockState air = Blocks.AIR.defaultBlockState();

        for (int dx = -maxR; dx <= maxR; dx++) {
            for (int dz = -maxR; dz <= maxR; dz++) {
                double distanceSq = dx * dx + dz * dz;
                double distance = Math.sqrt(distanceSq);

                // Calculate angle for the organic deformation
                double angle = Math.atan2(dz, dx);
                // Use the uniqueSeed modifier to vary the wave shapes per oasis location
                double deformation = Math.sin(angle * 3.0 + uniqueAngle) * 0.7 +
                        Math.cos(angle * 5.0 - uniqueAngle) * 0.4;

                double effectiveWaterRadius = WATER_RADIUS + deformation;
                double effectiveSandRadius = MAIN_BASE_RADIUS + (deformation * 1.3);

                BlockPos surfacePos = findSurface(level, center.offset(dx, 0, dz));

                if (surfacePos == null){
                    noSurfaceCount++;
                    continue;
                }

                int surfaceY = surfacePos.getY();

                // Water pool
                if (distance <= effectiveWaterRadius) {
                    double relativeDistance = distance / effectiveWaterRadius;
                    int depth = (relativeDistance < 0.35) ? 3 : (relativeDistance < 0.75) ? 2 : 1;

                    // Clear area above water
                    int finalClearY = surfaceY + 6;
                    for (int clearY = fixedWaterY + 1; clearY <= finalClearY; clearY++) {
                        BlockPos clearPos = new BlockPos(center.getX() + dx, clearY, center.getZ() + dz);
                        if (!level.getBlockState(clearPos).isAir()) {
                            level.setBlock(clearPos, air, 3);
                        }
                    }

                    BlockPos ceilingPos = new BlockPos(center.getX() + dx, finalClearY + 1, center.getZ() + dz);
                    BlockState ceilingState = level.getBlockState(ceilingPos);
                    if (ceilingState.is(config.mainBaseBlock().getBlock())) {
                        level.setBlock(ceilingPos, config.exposedBaseBlock(), 3);
                    }


                    // Fill water layers
                    for (int d = 0; d < depth; d++) {
                        BlockPos waterPos = new BlockPos(center.getX() + dx, fixedWaterY - d, center.getZ() + dz);
                        if (!level.getBlockState(waterPos).is(Blocks.WATER)) {
                            level.setBlock(waterPos, water, 3);
                        }
                    }


                    BlockPos floorPos = new BlockPos(center.getX() + dx, fixedWaterY - depth, center.getZ() + dz);
                    BlockPos below = floorPos.below();
                    BlockState belowState = level.getBlockState(below);

                    if (!level.getBlockState(floorPos).is(Blocks.SAND) && !belowState.isAir()) {
                        level.setBlock(floorPos, main, 3); // Place sand if the block beneath target is not air
                    } else if (belowState.isAir()) {
                        level.setBlock(floorPos, exposed, 3); // If the block beneath the target block IS air, place sandstone instead
                    }
                }
                // Sand ring
                else if (distance <= effectiveSandRadius) {

                    BlockPos target = surfacePos.below();
                    BlockState current = level.getBlockState(target);

                    boolean shouldReplace =
                            current.is(BlockTags.DIRT) ||
                                    current.is(BlockTags.SAND) ||
                                    current.is(Blocks.GRASS_BLOCK) ||
                                    current.is(Blocks.COARSE_DIRT) ||
                                    current.is(Blocks.STONE);

                    if (shouldReplace) {

                        level.setBlock(target, Blocks.SAND.defaultBlockState(), 3);

                        BlockPos below = target.below();

                        BlockState belowState = level.getBlockState(below);

                        if (belowState.isAir() || belowState.is(BlockTags.REPLACEABLE)) {
                            level.setBlock(below, Blocks.SANDSTONE.defaultBlockState(), 3);
                        }
                    }

                    // Clear any stray blocks above sand
                    int finalClearY = surfaceY + 6;
                    for (int clearY = fixedWaterY + 1; clearY <= finalClearY; clearY++) {
                        BlockPos clearPos = new BlockPos(center.getX() + dx, clearY, center.getZ() + dz);
                        if (!level.getBlockState(clearPos).isAir()) {
                            level.setBlock(clearPos, air, 3);
                        }
                    }

                    BlockPos ceilingPos = new BlockPos(center.getX() + dx, finalClearY + 1, center.getZ() + dz);
                    BlockState ceilingState = level.getBlockState(ceilingPos);
                    if (ceilingState.is(Blocks.SAND)) {
                        level.setBlock(ceilingPos, Blocks.SANDSTONE.defaultBlockState(), 3);
                    }
                }
            }
        }
    }

    private void wallInWater(OasisConfiguration config, WorldGenLevel level, BlockPos center) {
        int fixedWaterY = center.below().getY();
        // Expand the check radius slightly to make sure we catch the wavy edges
        int maxR = OasisFeature.WATER_RADIUS + 4;

        for (int dx = -maxR; dx <= maxR; dx++) {
            for (int dz = -maxR; dz <= maxR; dz++) {
                // Check all possible depth layers (0 down to -2 for a 3-deep pool)
                for (int dy = 0; dy >= -2; dy--) {
                    BlockPos checkPos = new BlockPos(center.getX() + dx, fixedWaterY + dy, center.getZ() + dz);

                    if (!level.getBlockState(checkPos).is(Blocks.WATER)) continue;

                    // Check all four horizontal neighbours of this confirmed water block
                    for (Direction dir : Direction.Plane.HORIZONTAL) {

                        BlockPos n = checkPos.relative(dir);
                        BlockState state = level.getBlockState(n);

                        boolean replaceable =
                                state.isAir() ||
                                        (state.is(BlockTags.REPLACEABLE)
                                                && !state.is(MFBlockTags.COMPRESSOR_FLUIDS));

                        if (replaceable) {
                            level.setBlock(n, config.mainBaseBlock(), 3);

                            BlockPos below = n.below();
                            if (level.getBlockState(below).isAir()) {
                                level.setBlock(below, config.exposedBaseBlock(), 3);
                            }
                        }
                    }
                }
            }
        }
    }

    private void placeTrees(FeaturePlaceContext<OasisConfiguration> context,
                            WorldGenLevel level,
                            RandomSource random,
                            BlockPos center) {

        var treeFeature = context.config().treeFeature().value();

        for (int i = 0; i < TREE_ATTEMPTS; i++) {

            double angle = random.nextDouble() * 2 * Math.PI;
            int dist = TREE_RING_MIN + random.nextInt(TREE_RING_MAX - TREE_RING_MIN + 1);

            int dx = (int) Math.round(Math.cos(angle) * dist);
            int dz = (int) Math.round(Math.sin(angle) * dist);

            BlockPos treeBase = findSurface(level, center.offset(dx, 0, dz));
            if (treeBase == null) continue;

            if (!level.getBlockState(treeBase.below()).is(BlockTags.SAND)) continue;

            boolean tooClose = false;
            for (BlockPos used : usedPalmSpots) {
                if (used.distSqr(treeBase) < 25) { // 5 block radius
                    tooClose = true;
                    break;
                }
            }

            if (tooClose) continue;

            usedPalmSpots.add(treeBase);
            treeFeature.place(level, context.chunkGenerator(), random, treeBase);
        }
    }
}