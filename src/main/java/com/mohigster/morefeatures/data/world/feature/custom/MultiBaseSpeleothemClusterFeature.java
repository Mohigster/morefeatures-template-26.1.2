package com.mohigster.morefeatures.data.world.feature.custom;

import com.mohigster.morefeatures.data.world.feature.custom.config.MultiBaseSpeleothemClusterConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SpeleothemThickness;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SpeleothemUtils;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Consumer;

import static net.minecraft.world.level.levelgen.feature.SpeleothemUtils.isBase;

public class MultiBaseSpeleothemClusterFeature extends Feature<MultiBaseSpeleothemClusterConfiguration> {
    public MultiBaseSpeleothemClusterFeature(Codec<MultiBaseSpeleothemClusterConfiguration> codec) {
        super(codec);
    }

    // -----------------------------------------------------------------------
    // Entry point
    // -----------------------------------------------------------------------

    @Override
    public boolean place(FeaturePlaceContext<MultiBaseSpeleothemClusterConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        MultiBaseSpeleothemClusterConfiguration config = context.config();
        RandomSource random = context.random();

        if (isEmptyOrWater(level, origin)) {
            return false;
        }

        int height = config.height().sample(random);
        float wetness = config.wetness().sample(random);
        float density = config.density().sample(random);
        int xRadius = config.radius().sample(random);
        int zRadius = config.radius().sample(random);

        for (int dx = -xRadius; dx <= xRadius; dx++) {
            for (int dz = -zRadius; dz <= zRadius; dz++) {
                double chance = this.getChanceOfStalagmiteOrStalactite(xRadius, zRadius, dx, dz, config);
                BlockPos pos = origin.offset(dx, 0, dz);
                // Each column picks its own random base block from the list
                BlockState baseBlock = config.randomBaseBlock(random);
                this.placeColumn(level, random, pos, dx, dz, wetness, chance, height, density, config, baseBlock);
            }
        }

        return true;
    }

    // -----------------------------------------------------------------------
    // Column placement  (mirrors vanilla logic; baseBlock is now a parameter)
    // -----------------------------------------------------------------------

    private void placeColumn(
            WorldGenLevel level,
            RandomSource random,
            BlockPos pos,
            int dx,
            int dz,
            float chanceOfWater,
            double chanceOfStalagmiteOrStalactite,
            int clusterHeight,
            float density,
            MultiBaseSpeleothemClusterConfiguration config,
            BlockState baseBlock
    ) {
        Optional<Column> baseColumn = Column.scan(
                level, pos, config.floorToCeilingSearchRange(),
                SpeleothemUtils::isEmptyOrWater, SpeleothemUtils::isNeitherEmptyNorWater
        );
        if (baseColumn.isEmpty()) return;

        OptionalInt ceiling = baseColumn.get().getCeiling();
        OptionalInt baseFloor = baseColumn.get().getFloor();
        if (ceiling.isEmpty() && baseFloor.isEmpty()) return;

        // -- optional water pool --
        boolean wantPool = random.nextFloat() < chanceOfWater;
        Column column;
        if (wantPool && baseFloor.isPresent() && canPlacePool(level, pos.atY(baseFloor.getAsInt()), config, baseBlock)) {
            int baseFloorY = baseFloor.getAsInt();
            column = baseColumn.get().withFloor(OptionalInt.of(baseFloorY - 1));
            level.setBlock(pos.atY(baseFloorY), Blocks.WATER.defaultBlockState(), 2);
        } else {
            column = baseColumn.get();
        }

        OptionalInt floor = column.getFloor();

        // -- stalactite (hanging from ceiling) --
        boolean wantStalactite = random.nextDouble() < chanceOfStalagmiteOrStalactite;
        int stalactiteHeight;
        if (ceiling.isPresent() && wantStalactite && isNotLava(level, pos.atY(ceiling.getAsInt()))) {
            int ceilingThickness = config.speleothemBlockLayerThickness().sample(random);
            replaceBlocksWithBaseBlock(level, pos.atY(ceiling.getAsInt()), ceilingThickness, Direction.UP, config, baseBlock);
            int maxH = floor.isPresent()
                    ? Math.min(clusterHeight, ceiling.getAsInt() - floor.getAsInt())
                    : clusterHeight;
            stalactiteHeight = this.getSpeleothemHeight(random, dx, dz, density, maxH, config);
        } else {
            stalactiteHeight = 0;
        }

        // -- stalagmite (rising from floor) --
        boolean wantStalagmite = random.nextDouble() < chanceOfStalagmiteOrStalactite;
        int stalagmiteHeight;
        if (floor.isPresent() && wantStalagmite && isNotLava(level, pos.atY(floor.getAsInt()))) {
            int floorThickness = config.speleothemBlockLayerThickness().sample(random);
            this.replaceBlocksWithBaseBlock(level, pos.atY(floor.getAsInt()), floorThickness, Direction.DOWN, config, baseBlock);
            if (ceiling.isPresent()) {
                stalagmiteHeight = Math.max(0,
                        stalactiteHeight + Mth.randomBetweenInclusive(
                                random,
                                -config.maxStalagmiteStalactiteHeightDiff(),
                                config.maxStalagmiteStalactiteHeightDiff()
                        ));
            } else {
                stalagmiteHeight = this.getSpeleothemHeight(random, dx, dz, density, clusterHeight, config);
            }
        } else {
            stalagmiteHeight = 0;
        }

        // -- resolve overlap when stalactite + stalagmite would intersect --
        int actualStalactiteHeight;
        int actualStalagmiteHeight;
        if (ceiling.isPresent() && floor.isPresent()
                && ceiling.getAsInt() - stalactiteHeight <= floor.getAsInt() + stalagmiteHeight) {
            int floorY = floor.getAsInt();
            int ceilingY = ceiling.getAsInt();
            int lowestStalactiteBottom = Math.max(ceilingY - stalactiteHeight, floorY + 1);
            int highestStalagmiteTop = Math.min(floorY + stalagmiteHeight, ceilingY - 1);
            int actualStalactiteBottom = Mth.randomBetweenInclusive(random, lowestStalactiteBottom, highestStalagmiteTop + 1);
            int actualStalagmiteTop = actualStalactiteBottom - 1;
            actualStalactiteHeight = ceilingY - actualStalactiteBottom;
            actualStalagmiteHeight = actualStalagmiteTop - floorY;
        } else {
            actualStalactiteHeight = stalactiteHeight;
            actualStalagmiteHeight = stalagmiteHeight;
        }

        // -- merge tips when they exactly fill the gap --
        boolean mergeTips = random.nextBoolean()
                && actualStalactiteHeight > 0
                && actualStalagmiteHeight > 0
                && column.getHeight().isPresent()
                && actualStalactiteHeight + actualStalagmiteHeight == column.getHeight().getAsInt();

        // -- place the actual speleothem blocks --
        if (ceiling.isPresent()) {
            growSpeleothem(
                    level,
                    pos.atY(ceiling.getAsInt() - 1),
                    Direction.DOWN,
                    actualStalactiteHeight,
                    mergeTips,
                    baseBlock.getBlock(),
                    config.pointedBlock().getBlock(),
                    config.replaceableBlocks()
            );
        }
        if (floor.isPresent()) {
            growSpeleothem(
                    level,
                    pos.atY(floor.getAsInt() + 1),
                    Direction.UP,
                    actualStalagmiteHeight,
                    mergeTips,
                    baseBlock.getBlock(),
                    config.pointedBlock().getBlock(),
                    config.replaceableBlocks()
            );
        }
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private boolean isNotLava(LevelReader level, BlockPos pos) {
        return !level.getBlockState(pos).is(Blocks.LAVA);
    }

    private int getSpeleothemHeight(
            RandomSource random, int dx, int dz, float density, int maxHeight,
            MultiBaseSpeleothemClusterConfiguration config
    ) {
        if (random.nextFloat() > density) return 0;
        int distanceFromCenter = Math.abs(dx) + Math.abs(dz);
        float heightMean = (float) Mth.clampedMap(
                distanceFromCenter,
                0.0, config.maxDistanceFromCenterAffectingHeightBias(),
                maxHeight / 2.0, 0.0
        );
        return (int) ClampedNormalFloat.sample(random, heightMean, config.heightDeviation(), 0.0F, maxHeight);
    }

    /**
     * Checks whether a water pool can be placed at this position.
     * Updated to accept a {@code baseBlock} parameter so the check works
     * regardless of which material was chosen for this column.
     */
    private boolean canPlacePool(
            WorldGenLevel level, BlockPos pos,
            MultiBaseSpeleothemClusterConfiguration config,
            BlockState baseBlock
    ) {
        BlockState state = level.getBlockState(pos);
        if (state.is(Blocks.WATER)
                || state.is(baseBlock.getBlock())
                || state.is(config.pointedBlock().getBlock())) {
            return false;
        }
        if (level.getBlockState(pos.above()).getFluidState().is(FluidTags.WATER)) {
            return false;
        }
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (!this.canBeAdjacentToWater(level, pos.relative(direction))) return false;
        }
        return this.canBeAdjacentToWater(level, pos.below());
    }

    private boolean canBeAdjacentToWater(LevelAccessor level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        return state.is(BlockTags.BASE_STONE_OVERWORLD) || state.getFluidState().is(FluidTags.WATER);
    }

    /**
     * Replaces solid blocks above/below the speleothem attachment point with
     * the chosen {@code baseBlock} to give it a natural backing layer.
     */
    private void replaceBlocksWithBaseBlock(
            WorldGenLevel level, BlockPos firstPos, int maxCount,
            Direction direction,
            MultiBaseSpeleothemClusterConfiguration config,
            BlockState baseBlock
    ) {
        BlockPos.MutableBlockPos pos = firstPos.mutable();
        for (int i = 0; i < maxCount; i++) {
            if (placeBaseBlockIfPossible(
                    level, pos, baseBlock.getBlock(), config.replaceableBlocks())) {
                return;
            }
            pos.move(direction);
        }
    }

    private double getChanceOfStalagmiteOrStalactite(
            int xRadius, int zRadius, int dx, int dz,
            MultiBaseSpeleothemClusterConfiguration config
    ) {
        int xDistanceFromEdge = xRadius - Math.abs(dx);
        int zDistanceFromEdge = zRadius - Math.abs(dz);
        int distanceFromEdge = Math.min(xDistanceFromEdge, zDistanceFromEdge);
        return Mth.clampedMap(
                (float) distanceFromEdge,
                0.0F, (float) config.maxDistanceFromEdgeAffectingChanceOfSpeleothem(),
                config.chanceOfSpeleothemAtMaxDistanceFromCenter(), 1.0F
        );
    }

    protected static void growSpeleothem(LevelAccessor level, BlockPos startPos, Direction tipDirection, int height, boolean mergedTip, Block baseBlock, Block pointedBlock, HolderSet<Block> replaceableBlocks) {
        if (isBase(level.getBlockState(startPos.relative(tipDirection.getOpposite())), baseBlock, replaceableBlocks)) {
            BlockPos.MutableBlockPos pos = startPos.mutable();
            buildBaseToTipColumn(tipDirection, height, mergedTip, (state) -> {
                if (state.is(pointedBlock)) {
                    state = state.setValue(PointedDripstoneBlock.WATERLOGGED, level.isWaterAt(pos));
                }

                level.setBlock(pos, state, 2);
                pos.move(tipDirection);
            }, pointedBlock);
        }

    }

    protected static void buildBaseToTipColumn(Direction direction, int totalLength, boolean mergedTip, Consumer<BlockState> consumer, Block pointedBlock) {
        if (totalLength >= 3) {
            consumer.accept(createPointedBlock(direction, SpeleothemThickness.BASE, pointedBlock));

            for (int i = 0; i < totalLength - 3; ++i) {
                consumer.accept(createPointedBlock(direction, SpeleothemThickness.MIDDLE, pointedBlock));
            }
        }

        if (totalLength >= 2) {
            consumer.accept(createPointedBlock(direction, SpeleothemThickness.FRUSTUM, pointedBlock));
        }

        if (totalLength >= 1) {
            consumer.accept(createPointedBlock(direction, mergedTip ? SpeleothemThickness.TIP_MERGE : SpeleothemThickness.TIP, pointedBlock));
        }
    }

    private static BlockState createPointedBlock(Direction direction, SpeleothemThickness thickness, Block pointedBlock) {
        return (pointedBlock.defaultBlockState().setValue(PointedDripstoneBlock.TIP_DIRECTION, direction)).setValue(PointedDripstoneBlock.THICKNESS, thickness);
    }

    protected static boolean isEmptyOrWater(LevelAccessor level, BlockPos pos) {
        return level.isStateAtPosition(pos, SpeleothemUtils::isEmptyOrWater);
    }

    protected static boolean placeBaseBlockIfPossible(LevelAccessor level, BlockPos pos, Block baseBlock, HolderSet<Block> replaceableBlocks) {
        BlockState state = level.getBlockState(pos);
        if (state.is(replaceableBlocks)) {
            level.setBlock(pos, baseBlock.defaultBlockState(), 2);
            return true;
        } else {
            return false;
        }
    }
}
