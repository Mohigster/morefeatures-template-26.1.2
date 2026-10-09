package com.mohigster.morefeatures.portal.frame;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.portal.PortalDestinationManager;
import com.mohigster.morefeatures.portal.key.BlockKey;
import com.mohigster.morefeatures.portal.key.PortalKey;
import com.mohigster.morefeatures.util.PortalUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import net.neoforged.neoforge.event.EventHooks;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.function.Predicate;

public class CustomPortalShape extends PortalShape {
    public static final int MIN_WIDTH = 2;
    public static final int MAX_WIDTH = 21;
    public static final int MIN_HEIGHT = 3;
    public static final int MAX_HEIGHT = 21;

    private static final BlockBehaviour.StatePredicate FRAME = PortalUtil::isFrame;

    /** Use {@code findEmptyShape()} instead! */
    private CustomPortalShape(
            Direction.Axis axis,
            int portalBlockCount,
            Direction rightDir,
            BlockPos bottomLeft,
            int width,
            int height
    ) {
        super(axis, portalBlockCount,
                rightDir, bottomLeft,
                width, height
        );
    }

    public static Optional<PortalShape> findEmptyShape(LevelAccessor level, BlockPos pos, Direction.Axis preferredAxis) {
        Optional<PortalShape> shape = findCustomShape(level, pos, CustomPortalShape::isValidAndEmpty, preferredAxis);
        // return the shape AND fire the NeoForge PortalSpawnEvent
        return EventHooks.onTrySpawnPortal(level, pos, shape);
    }

    public static Optional<PortalShape> findCustomShape(LevelAccessor level, BlockPos pos, Predicate<PortalShape> isValid, Direction.Axis preferredAxis) {
        Optional<PortalShape> firstAxis = Optional.of(findAnyCustomShape(level, pos, preferredAxis)).filter(isValid);
        if (firstAxis.isPresent()) {
            return firstAxis;
        } else {
            Direction.Axis otherAxis = preferredAxis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
            return Optional.of(findAnyCustomShape(level, pos, otherAxis)).filter(isValid);
        }
    }

    public static PortalShape findAnyCustomShape(BlockGetter level, BlockPos pos, Direction.Axis axis) {
        Direction rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
        BlockPos bottomLeft = calculateBottomLeft(level, rightDir, pos);
        if (bottomLeft == null) {
            MoreFeatures.LOGGER.debug("Bottom left was null");
            return new CustomPortalShape(axis, 0, rightDir, pos, 0, 0);
        } else {
            int width = calculateWidth(level, bottomLeft, rightDir);
            if (width == 0) {
                MoreFeatures.LOGGER.debug("Width was 0");
                return new CustomPortalShape(axis, 0, rightDir, bottomLeft, 0, 0);
            } else {
                MutableInt portalBlockCountOutput = new MutableInt();
                int height = calculateHeight(level, bottomLeft, rightDir, width, portalBlockCountOutput);

                if (height == 0) {
                    MoreFeatures.LOGGER.debug("Height was 0");
                }
                return new CustomPortalShape(axis, portalBlockCountOutput.intValue(), rightDir, bottomLeft, width, height);
            }
        }
    }

    private static @Nullable BlockPos calculateBottomLeft(BlockGetter level, Direction rightDir, BlockPos pos) {
        int minY = Math.max(level.getMinY(), pos.getY() - PortalShape.MAX_WIDTH);

        while (pos.getY() > minY && isEmpty(level.getBlockState(pos.below()))) {
            pos = pos.below();
        }

        Direction leftDir = rightDir.getOpposite();
        int edge = getDistanceUntilEdgeAboveFrame(level, pos, leftDir) - 1;
        if (edge < 0) {
            MoreFeatures.LOGGER.debug("Edge value {} was negative", edge);
            return null;
        }
        return pos.relative(leftDir, edge);
    }

    private static int calculateWidth(BlockGetter level, BlockPos bottomLeft, Direction rightDir) {
        int width = getDistanceUntilEdgeAboveFrame(level, bottomLeft, rightDir);
        return width >= MIN_WIDTH && width <= MAX_WIDTH ? width : 0;
    }

    private static int getDistanceUntilEdgeAboveFrame(BlockGetter level, BlockPos pos, Direction direction) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int width = 0; width <= MAX_WIDTH; width++) {
            MoreFeatures.LOGGER.debug("Width: {}", width);
            mutablePos.set(pos).move(direction, width);
            BlockState state = level.getBlockState(mutablePos);
            if (!isEmpty(state)) {
                if (FRAME.test(state, level, mutablePos)) {
                    MoreFeatures.LOGGER.debug("Found block at ({}, {})", mutablePos, state);
                    return width;
                }
                break;
            }

            BlockState belowState = level.getBlockState(mutablePos.move(Direction.DOWN));
            if (!FRAME.test(belowState, level, mutablePos)) {
                MoreFeatures.LOGGER.debug("State below key was not a Frame");
                break;
            }
        }

        MoreFeatures.LOGGER.debug("Failed to find distance");

        return 0;
    }

    private static int calculateHeight(BlockGetter level, BlockPos bottomLeft, Direction rightDir, int width, MutableInt portalBlockCount) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int height = getDistanceUntilTop(level, bottomLeft, rightDir, pos, width, portalBlockCount);
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT &&
                hasTopFrame(level, bottomLeft, rightDir, pos, width, height) ? height : 0;
    }

    private static boolean hasTopFrame(BlockGetter level, BlockPos bottomLeft, Direction rightDir, BlockPos.MutableBlockPos pos, int width, int height) {
        for(int i = 0; i < width; ++i) {
            BlockPos.MutableBlockPos framePos = pos.set(bottomLeft)
                    .move(Direction.UP, height).move(rightDir, i);
            if (!FRAME.test(level.getBlockState(framePos), level, framePos)) {
                return false;
            }
        }

        return true;
    }

    private static int getDistanceUntilTop(BlockGetter level, BlockPos bottomLeft, Direction rightDir, BlockPos.MutableBlockPos pos, int width, MutableInt blockCount) {
        for(int height = 0; height < MAX_HEIGHT; height++) {
            pos.set(bottomLeft).move(Direction.UP, height).move(rightDir, -1);
            if (!FRAME.test(level.getBlockState(pos), level, pos)) {
                return height;
            }

            pos.set(bottomLeft).move(Direction.UP, height).move(rightDir, width);
            if (!FRAME.test(level.getBlockState(pos), level, pos)) {
                return height;
            }

            for(int i = 0; i < width; ++i) {
                pos.set(bottomLeft).move(Direction.UP, height).move(rightDir, i);
                BlockState state = level.getBlockState(pos);
                if (!isEmpty(state)) {
                    return height;
                }

                if (state.is(MFBlocks.PORTAL)) {
                    blockCount.increment();
                }
            }
        }

        return MAX_HEIGHT;
    }
    
    private static boolean isValidAndEmpty(PortalShape shape) {
        return shape.isValid() && shape.numPortalBlocks == 0;
    }

    private static boolean isEmpty(BlockState state) {
        Optional<PortalKey> optionalKey = PortalDestinationManager.getPortalKey(state);

        if (optionalKey.isPresent()) {
            if (optionalKey.get() instanceof BlockKey(Block block)) {
                boolean stateIsKey = state.is(block);
                MoreFeatures.LOGGER.debug("State is key?: {}", stateIsKey);
                return stateIsKey;
            }
        }

        return state.isAir() || state.is(BlockTags.FIRE) || state.is(MFBlocks.PORTAL.get());
    }

    @Override
    public void createPortalBlocks(LevelAccessor level) {
        BlockState portalState = MFBlocks.PORTAL.get()
                .defaultBlockState().setValue(NetherPortalBlock.AXIS, this.axis);
        BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(
                Direction.UP, this.height - 1
        ).relative(this.rightDir, this.width - 1)).forEach((pos) ->
                level.setBlock(pos, portalState, Block.UPDATE_KNOWN_SHAPE));
    }
}
