package com.mohigster.morefeatures.block.custom.verticalslab;

import com.mohigster.morefeatures.block.collection.WoodSetType;
import com.mohigster.morefeatures.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NullMarked;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class VerticalSlabBlock extends Block implements SimpleWaterloggedBlock {

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<VerticalSlabType> TYPE = EnumProperty.create("type", VerticalSlabType.class);
    public static final EnumProperty<StairsShape> SHAPE = BlockStateProperties.STAIRS_SHAPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    // Base "straight" shapes for each of the four single-slab orientations.
    private static final VoxelShape NORTH_SHAPE = Block.box(0, 0, 0, 16, 16, 8);
    private static final VoxelShape SOUTH_SHAPE = Block.box(0, 0, 8, 16, 16, 16);
    private static final VoxelShape WEST_SHAPE = Block.box(0, 0, 0, 8, 16, 16);
    private static final VoxelShape EAST_SHAPE = Block.box(8, 0, 0, 16, 16, 16);

    // Cache of every (type, shape) -> VoxelShape combination.
    private static final Map<BlockState, VoxelShape> SHAPE_CACHE = new HashMap<>();

    private final boolean isFlammable;

    public VerticalSlabBlock(boolean isFlammable, Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TYPE, VerticalSlabType.NORTH)
                .setValue(FACING, Direction.NORTH)
                .setValue(SHAPE, StairsShape.STRAIGHT)
                .setValue(WATERLOGGED, false));

        this.isFlammable = isFlammable;
    }

    public VerticalSlabBlock(WoodSetType woodType, Properties properties){
        this(woodType.isFlammable(), properties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        BlockState existing = context.getLevel().getBlockState(pos);

        if (existing.is(this) && existing.getValue(TYPE) != VerticalSlabType.DOUBLE) {
            Direction originalFacing = existing.getValue(FACING);
            return existing
                    .setValue(TYPE, VerticalSlabType.DOUBLE)
                    .setValue(FACING, originalFacing)
                    .setValue(WATERLOGGED, false);
        }

        Direction facing = getFacingFromClick(context);
        BlockState state = this.defaultBlockState()
                .setValue(TYPE, VerticalSlabType.fromDirection(facing))
                .setValue(FACING, facing)
                .setValue(WATERLOGGED, context.getLevel().getFluidState(pos).getType() == Fluids.WATER);

        // Apply smart orientation snapping
        state = updateOrientation(state, context.getLevel(), pos);
        return state;
    }

    private static Direction getFacingFromClick(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();
        BlockPos pos = context.getClickedPos();
        BlockState clickedState = context.getLevel().getBlockState(pos);

        // Special handling when clicking on an existing vertical slab
        if (clickedState.getBlock() instanceof VerticalSlabBlock
                && clickedState.getValue(TYPE) != VerticalSlabType.DOUBLE) {

            Direction existingSlabDirection = clickedState.getValue(TYPE).toDirection();

            // If the player is holding shift, always respect normal click direction (manual override)
            if (context.getPlayer() != null && context.getPlayer().isShiftKeyDown()) {
                return getDefaultFacingFromClick(context);
            }

            // Determine if we clicked on the "side" (perpendicular) or "front/back" (parallel)
            boolean isSideClick = clickedFace.getAxis() != existingSlabDirection.getAxis();

            if (isSideClick) {
                // Clicking on the side → extend the line by copying direction
                return existingSlabDirection;
            } else {
                // Clicking on front or back → do NOT copy (allows placing double slabs easily)
                return getDefaultFacingFromClick(context);
            }
        }

        // Normal placement (no existing slab or other cases)
        return getDefaultFacingFromClick(context);
    }

    private static Direction getDefaultFacingFromClick(BlockPlaceContext context) {
        Direction clickedFace = context.getClickedFace();

        if (clickedFace.getAxis().isHorizontal()) {
            return clickedFace.getOpposite();
        }

        // Top/bottom click - determine based on hit position
        double hitX = context.getClickLocation().x - context.getClickedPos().getX();
        double hitZ = context.getClickLocation().z - context.getClickedPos().getZ();
        double dx = hitX - 0.5;
        double dz = hitZ - 0.5;

        if (Math.abs(dx) > Math.abs(dz)) {
            return dx > 0 ? Direction.EAST : Direction.WEST;
        } else {
            return dz > 0 ? Direction.SOUTH : Direction.NORTH;
        }
    }

    private static BlockState updateOrientation(BlockState state, BlockGetter level, BlockPos pos) {
        VerticalSlabType type = state.getValue(TYPE);
        if (type == VerticalSlabType.DOUBLE) return state;

        Direction bestDirection = findBestParallelDirection(state, level, pos);
        if (bestDirection != null) {
            state = state.setValue(TYPE, VerticalSlabType.fromDirection(bestDirection))
                    .setValue(FACING, bestDirection);
        }

        return state.setValue(SHAPE, getStairsShape(state, level, pos));
    }

    private static Direction findBestParallelDirection(BlockState state, BlockGetter level, BlockPos pos) {
        VerticalSlabType currentType = state.getValue(TYPE);
        Direction currentFacing = currentType.toDirection();

        // Check perpendicular axis first (most common case for snapping)
        Direction perp = currentFacing.getAxis() == Direction.Axis.X ? Direction.NORTH : Direction.EAST;
        Direction match = checkParallelSandwich(level, pos, perp);
        if (match != null && match.getAxis() != currentFacing.getAxis()) {
            return match;
        }

        // Also check current axis (for completeness)
        match = checkParallelSandwich(level, pos, currentFacing);
        if (match != null && match.getAxis() != currentFacing.getAxis()) {  // only cross-axis
            return match;
        }

        return null;
    }

    private static Direction checkParallelSandwich(BlockGetter level, BlockPos pos, Direction checkDir) { // Checks if it is sandwiched by two parallel vertical slab blocks. Opposite directions (i.e. North and South) do not count as parallel.
        BlockState n1 = level.getBlockState(pos.relative(checkDir));
        BlockState n2 = level.getBlockState(pos.relative(checkDir.getOpposite()));

        Direction d1 = getParallelFacing(n1);
        Direction d2 = getParallelFacing(n2);

        if (d2 != null && d1 == d2) {
            return d1;
        }
        return null;
    }

    private static Direction getParallelFacing(BlockState neighbor) {
        if (canConnectToVerticalSlab(neighbor)) return neighbor.getValue(TYPE).toDirection();
        return null;
    }

    private static StairsShape getStairsShape(BlockState state, BlockGetter level, BlockPos pos) {
        VerticalSlabType type = state.getValue(TYPE);
        if (type == VerticalSlabType.DOUBLE) return StairsShape.STRAIGHT;

        Direction facing = type.toDirection();
        Direction frontPerp = getPerpendicularNeighborFacing(level.getBlockState(pos.relative(facing)), facing);
        Direction backPerp  = getPerpendicularNeighborFacing(level.getBlockState(pos.relative(facing.getOpposite())), facing);

        if (frontPerp != null && canTakeShape(state, level, pos, frontPerp.getOpposite())) {
            return frontPerp == rotateClockwise(facing) ? StairsShape.OUTER_LEFT : StairsShape.OUTER_RIGHT;
        }

        if (backPerp != null && canTakeShape(state, level, pos, backPerp)) {
            return backPerp == rotateClockwise(facing) ? StairsShape.INNER_LEFT : StairsShape.INNER_RIGHT;
        }

        return StairsShape.STRAIGHT;
    }

    private static Direction getPerpendicularNeighborFacing(BlockState neighbor, Direction facing) {
        if (!canConnectToVerticalSlab(neighbor)) return null;
        Direction neighborFacing = neighbor.getValue(TYPE).toDirection();
        if (neighborFacing.getAxis() == facing.getAxis()) return null;
        return neighborFacing;
    }

    private static boolean canTakeShape(BlockState state, BlockGetter level, BlockPos pos, Direction towards) {
        BlockState adjacent = level.getBlockState(pos.relative(towards));
        return !canConnectToVerticalSlab(adjacent) || adjacent.getValue(TYPE).toDirection().getAxis() == state.getValue(TYPE).toDirection().getAxis();
    }

    // A block can connect to other blocks if:

    // The block is a non-double vertical slab
    // OR the block is in the vertical slab connectable tag
    // Vertical slabs being in the tag does nothing.
    // This is intentional to prevent double connections caused by a vertical slab in the tag
    private static boolean canConnectToVerticalSlab(BlockState state) {
        return (state.getBlock() instanceof VerticalSlabBlock
                && state.getValue(TYPE) != VerticalSlabType.DOUBLE)
                || (state.is(MFBlockTags.VERTICAL_SLAB_CONNECTABLE)
                && !(state.getBlock() instanceof VerticalSlabBlock)
        );
    }

    private static Direction rotateClockwise(Direction facing) {
        return switch (facing) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
            default -> facing;
        };
    }

    @NullMarked
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_CACHE.computeIfAbsent(state, VerticalSlabBlock::computeShape);
    }

    private static VoxelShape computeShape(BlockState state) {
        VerticalSlabType type = state.getValue(TYPE);
        if (type == VerticalSlabType.DOUBLE) {
            return Shapes.block();
        }

        StairsShape shape = state.getValue(SHAPE);
        if (shape == StairsShape.STRAIGHT) {
            return baseShape(type.toDirection());
        }

        Direction facing = type.toDirection();
        Direction corner = (shape == StairsShape.OUTER_LEFT || shape == StairsShape.INNER_LEFT)
                ? rotateClockwise(facing)
                : rotateClockwise(facing).getOpposite();

        VoxelShape straight = baseShape(facing);
        VoxelShape adjoining = baseShape(corner);

        return switch (shape) {
            case INNER_LEFT, INNER_RIGHT -> Shapes.join(straight, adjoining, BooleanOp.OR);  // 3/4 L-shape
            case OUTER_LEFT, OUTER_RIGHT -> Shapes.join(straight, adjoining, BooleanOp.AND); // 1/4 corner
            default -> straight;
        };
    }

    private static VoxelShape baseShape(Direction facing) {
        return switch (facing) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            case EAST -> EAST_SHAPE;
            default -> throw new IllegalArgumentException("Vertical slabs only support horizontal facings");
        };
    }

    // ---------------------------------------------------------------
    // Waterlogging
    // ---------------------------------------------------------------

    @NullMarked
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @NullMarked
    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        return SimpleWaterloggedBlock.super.placeLiquid(level, pos, state, fluidState);
    }

    @NullMarked
    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (directionToNeighbour.getAxis().isHorizontal()) {
            BlockState updated = updateOrientation(state, level, pos);
            return updated.setValue(SHAPE, getStairsShape(updated, level, pos));
        }

        return super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    // ---------------------------------------------------------------
    // Rotation / mirror support (for structure blocks, /setblock, etc.)
    // ---------------------------------------------------------------

    @NullMarked
    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        VerticalSlabType type = state.getValue(TYPE);
        if (type == VerticalSlabType.DOUBLE) {
            return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
        }
        return state.setValue(TYPE, VerticalSlabType.fromDirection(rotation.rotate(type.toDirection())))
                .setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @NullMarked
    @Override
    public BlockState mirror(BlockState state, Mirror mirroring) {
        VerticalSlabType type = state.getValue(TYPE);
        if (type == VerticalSlabType.DOUBLE) {
            return state.setValue(FACING, mirroring.mirror(state.getValue(FACING)));
        }
        return state.setValue(TYPE, VerticalSlabType.fromDirection(mirroring.mirror(type.toDirection())))
                .setValue(FACING, mirroring.mirror(state.getValue(FACING)));
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return state.getValue(TYPE) != VerticalSlabType.DOUBLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, FACING, SHAPE, WATERLOGGED);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        ItemStack held = context.getItemInHand();
        VerticalSlabType type = state.getValue(TYPE);

        if (type == VerticalSlabType.DOUBLE || !held.is(this.asItem())) {
            return false;
        }

        if (state.getValue(SHAPE) != StairsShape.STRAIGHT) {
            if (context.getPlayer() == null || !context.getPlayer().isShiftKeyDown()) {
                return false;
            }
        }

        if (context.replacingClickedOnBlock()) {
            // Only allow combining when the player clicks the open face —
            // i.e. the side opposite to where the slab is sitting.
            return context.getClickedFace() == type.toDirection().getOpposite();
        }

        return true;
    }


    /* --- FLAMMABILITY OVERRIDES --- */

    /* written to allow a true or false flammability setting at block registration, making flammability overrides easy */

    @NullMarked
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.isFlammable;
    }

    @NullMarked
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.isFlammable ? 20 : 0;
    }

    @NullMarked
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.isFlammable ? 5 : 0;
    }
}
