package com.mohigster.morefeatures.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NullMarked;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class ColumnBlock extends Block {
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");

    // Footprint of the post/shaft in the middle of the block (in 1/16ths).
    // 4-12 gives an 8px-wide post centered in the block; tweak to taste.
    private static final double POST_MIN = 4.0;
    private static final double POST_MAX = 12.0;

    // Cache the 4 possible shapes (up x down) instead of rebuilding every call.
    private static final Map<Integer, VoxelShape> SHAPE_CACHE = new HashMap<>();

    public ColumnBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(UP, false)
                .setValue(DOWN, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN);
    }

    private boolean connectsTo(BlockGetter level, BlockPos pos, Direction direction) {
        BlockState neighbor = level.getBlockState(pos.relative(direction));
        return neighbor.is(this);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockGetter level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        return this.defaultBlockState()
                .setValue(DOWN, connectsTo(level, pos, Direction.DOWN))
                .setValue(UP, connectsTo(level, pos, Direction.UP));
    }

    @NullMarked
    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (directionToNeighbour == Direction.UP) {
            return state.setValue(UP, neighbourState.is(this));
        } else if (directionToNeighbour == Direction.DOWN) {
            return state.setValue(DOWN, neighbourState.is(this));
        }
        return state;
    }

    @NullMarked
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean up = state.getValue(UP);
        boolean down = state.getValue(DOWN);
        int key = (up ? 1 : 0) | (down ? 2 : 0);
        return SHAPE_CACHE.computeIfAbsent(key, _ -> buildShape(up, down));
    }

    private static VoxelShape buildShape(boolean up, boolean down) {
        // Shaft stretches all the way to an edge if connected there, otherwise
        // stops 1px short to leave room for that end's carpet cap.
        double shaftMinY = down ? 0.0 : 1.0;
        double shaftMaxY = up ? 16.0 : 15.0;

        VoxelShape shape = Shapes.box(
                POST_MIN / 16.0, shaftMinY / 16.0, POST_MIN / 16.0,
                POST_MAX / 16.0, shaftMaxY / 16.0, POST_MAX / 16.0);

        if (!down) {
            VoxelShape base = Shapes.box(0.0, 0.0, 0.0, 1.0, 1.0 / 16.0, 1.0);
            shape = Shapes.or(shape, base);
        }
        if (!up) {
            VoxelShape top = Shapes.box(0.0, 15.0 / 16.0, 0.0, 1.0, 1.0, 1.0);
            shape = Shapes.or(shape, top);
        }
        return shape;
    }

    @NullMarked
    @Override
    public boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }
}
