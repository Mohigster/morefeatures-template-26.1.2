package com.mohigster.morefeatures.block.custom.portal.datadriven;

import com.mohigster.morefeatures.block.MFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.function.Predicate;

public class CustomPortalShape extends PortalShape {
    public static final int MIN_WIDTH = 2;
    public static final int MAX_WIDTH = 21;
    public static final int MIN_HEIGHT = 3;
    public static final int MAX_HEIGHT = 21;

    private static final BlockBehaviour.StatePredicate FRAME =
            (state, _, _) -> state
                    .is(PortalDestinations.getFrameState(state).getBlock());

    private CustomPortalShape(Direction.Axis axis, int portalBlockCount, Direction rightDir, BlockPos bottomLeft, int width, int height) {
        super(axis, portalBlockCount, rightDir, bottomLeft, width, height);
    }

    public static Optional<CustomPortalShape> findEmptyCustomShape(LevelAccessor level, BlockPos pos, Direction.Axis preferredAxis) {
        return findCustomShape(level, pos, (shape) -> shape.isValid() && shape.numPortalBlocks == 0, preferredAxis);
    }

    public static Optional<CustomPortalShape> findCustomShape(LevelAccessor level, BlockPos pos, Predicate<CustomPortalShape> isValid, Direction.Axis preferredAxis) {
        Optional<CustomPortalShape> firstAxis = Optional.of(findAnyCustomShape(level, pos, preferredAxis)).filter(isValid);
        if (firstAxis.isPresent()) {
            return firstAxis;
        } else {
            Direction.Axis otherAxis = preferredAxis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X;
            return Optional.of(findAnyCustomShape(level, pos, otherAxis)).filter(isValid);
        }
    }

    public static CustomPortalShape findAnyCustomShape(BlockGetter level, BlockPos pos, Direction.Axis axis) {
        Direction rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
        BlockPos bottomLeft = calculateBottomLeft(level, rightDir, pos);
        if (bottomLeft == null) {
            return new CustomPortalShape(axis, 0, rightDir, pos, 0, 0);
        } else {
            int width = calculateWidth(level, bottomLeft, rightDir);
            if (width == 0) {
                return new CustomPortalShape(axis, 0, rightDir, bottomLeft, 0, 0);
            } else {
                MutableInt portalBlockCountOutput = new MutableInt();
                int height = calculateHeight(level, bottomLeft, rightDir, width, portalBlockCountOutput);
                return new CustomPortalShape(axis, portalBlockCountOutput.intValue(), rightDir, bottomLeft, width, height);
            }
        }
    }

    private static @Nullable BlockPos calculateBottomLeft(BlockGetter level, Direction rightDir, BlockPos pos) {
        // scanPos is redundant but prevents the IDE from complaining about an empty for loop
        BlockPos scanPos = pos;

        for(int minY = Math.max(level.getMinY(), pos.getY() - MAX_HEIGHT); pos.getY() > minY && isEmpty(level.getBlockState(pos.below())); scanPos = pos.below()) {
            pos = scanPos;
        }

        Direction leftDir = rightDir.getOpposite();
        int edge = getDistanceUntilEdgeAboveFrame(level, pos, leftDir) - 1;
        return edge < 0 ? null : pos.relative(leftDir, edge);
    }

    private static int calculateWidth(BlockGetter level, BlockPos bottomLeft, Direction rightDir) {
        int width = getDistanceUntilEdgeAboveFrame(level, bottomLeft, rightDir);
        return width >= MIN_WIDTH && width <= MAX_WIDTH ? width : 0;
    }

    private static int getDistanceUntilEdgeAboveFrame(BlockGetter level, BlockPos pos, Direction direction) {
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();

        for(int width = 0; width <= MAX_WIDTH; ++width) {
            blockPos.set(pos).move(direction, width);
            BlockState blockState = level.getBlockState(blockPos);
            if (!isEmpty(blockState)) {
                if (FRAME.test(blockState, level, blockPos)) {
                    return width;
                }
                break;
            }

            BlockState belowState = level.getBlockState(blockPos.move(Direction.DOWN));
            if (!FRAME.test(belowState, level, blockPos)) {
                break;
            }
        }

        return 0;
    }

    private static int calculateHeight(BlockGetter level, BlockPos bottomLeft, Direction rightDir, int width, MutableInt portalBlockCount) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int height = getDistanceUntilTop(level, bottomLeft, rightDir, pos, width, portalBlockCount);
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT && hasTopFrame(level, bottomLeft, rightDir, pos, width, height) ? height : 0;
    }

    private static boolean hasTopFrame(BlockGetter level, BlockPos bottomLeft, Direction rightDir, BlockPos.MutableBlockPos pos, int width, int height) {
        for(int i = 0; i < width; ++i) {
            BlockPos.MutableBlockPos framePos = pos.set(bottomLeft).move(Direction.UP, height).move(rightDir, i);
            if (!FRAME.test(level.getBlockState(framePos), level, framePos)) {
                return false;
            }
        }

        return true;
    }

    private static int getDistanceUntilTop(BlockGetter level, BlockPos bottomLeft, Direction rightDir, BlockPos.MutableBlockPos pos, int width, MutableInt portalBlockCount) {
        for(int height = 0; height < MAX_HEIGHT; ++height) {
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
                    portalBlockCount.increment();
                }
            }
        }

        return MAX_HEIGHT;
    }

    private static boolean isEmpty(BlockState state) {
        return state.isAir() || state.is(BlockTags.FIRE) || state.is(MFBlocks.PORTAL.get());
    }

    @Override
    public void createPortalBlocks(LevelAccessor level) {
        BlockState portalState = MFBlocks.PORTAL.get().defaultBlockState().setValue(NetherPortalBlock.AXIS, this.axis);
        BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1)
                .relative(this.rightDir, this.width - 1)).forEach((pos) ->
                level.setBlock(pos, portalState, 18));
    }
}
