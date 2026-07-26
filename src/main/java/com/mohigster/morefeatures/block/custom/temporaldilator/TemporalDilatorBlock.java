package com.mohigster.morefeatures.block.custom.temporaldilator;

import com.mohigster.morefeatures.block.entity.custom.TemporalDilatorBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

public class TemporalDilatorBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    public static final MapCodec<TemporalDilatorBlock> CODEC = simpleCodec(TemporalDilatorBlock::new);
    // Using a custom Direction enum because only UP and DOWN are valid directions.
    // Meanwhile, normal Direction allows for NORTH, SOUTH, EAST, and WEST, as well
    public static EnumProperty<TemporalDilatorDirection> DIRECTION = EnumProperty.create("dilator_direction", TemporalDilatorDirection.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty SLOW_MODE = BooleanProperty.create("slow_mode");
    public static final BooleanProperty POWERED_BY_REDSTONE = BooleanProperty.create("powered_by_redstone");

    // Upright Shape Definition
    private static final VoxelShape SHAPE_UP = Shapes.or(
            Block.box(7.0, 0.0, 7.0, 9.0, 1.0, 9.0),    // Base
            Block.box(7.5, 1.0, 7.5, 8.5, 6.0, 8.5),    // Shaft
            Block.box(6.0, 6.0, 6.0, 10.0, 12.0, 10.0), // Core
            Block.box(5.0, 7.0, 6.0, 11.0, 11.0, 10.0), // Outer Ring X
            Block.box(6.0, 7.0, 5.0, 10.0, 11.0, 11.0)  // Outer Ring Z
    );

    private static final VoxelShape SHAPE_DOWN = Shapes.or(
            Block.box(7.0, 15.0, 7.0, 9.0, 16.0, 9.0),   // Base (Ceiling)
            Block.box(7.5, 10.0, 7.5, 8.5, 15.0, 8.5),   // Shaft
            Block.box(6.0, 4.0, 6.0, 10.0, 10.0, 10.0),  // Core
            Block.box(5.0, 5.0, 6.0, 11.0, 9.0, 10.0),   // Outer Ring X
            Block.box(6.0, 5.0, 5.0, 10.0, 9.0, 11.0)    // Outer Ring Z
    );

    public TemporalDilatorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(DIRECTION, TemporalDilatorDirection.UP)
                .setValue(WATERLOGGED, false)
                .setValue(SLOW_MODE, false)
                .setValue(POWERED_BY_REDSTONE, false)
        );
    }

    @NullMarked
    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        TemporalDilatorBlockEntity.tick(level, pos, state);
    }

    @Override
    protected @NonNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @NullMarked
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            BlockState newState = state.cycle(SLOW_MODE);
            level.setBlock(pos, newState, Block.UPDATE_ALL);
        }
        return InteractionResult.SUCCESS;
    }

    @NullMarked
    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, orientation, movedByPiston);

        if (!level.isClientSide()) {
            boolean isPowered = level.hasNeighborSignal(pos);

            if (state.getValue(POWERED_BY_REDSTONE) != isPowered) {
                level.setBlock(pos, state.setValue(POWERED_BY_REDSTONE, isPowered), Block.UPDATE_ALL);
            }
        }
    }

    public static int getLightLevel(BlockState state){
        return !state.getValue(POWERED_BY_REDSTONE) ? getSlowOrFastLightLevel(state) : 15;
    }

    private static int getSlowOrFastLightLevel(BlockState state){
        if (state.getValue(WATERLOGGED)) return state.getValue(SLOW_MODE) ? 4 : 6;
        else return state.getValue(SLOW_MODE) ? 5 : 8;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DIRECTION, WATERLOGGED, SLOW_MODE, POWERED_BY_REDSTONE);
    }

    @NullMarked
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(DIRECTION)) {
            case UP -> SHAPE_UP;
            case DOWN -> SHAPE_DOWN;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelReader level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState fluidState = level.getFluidState(pos);

        // Prefer hanging when the player clicked the underside of a block,
        // or when there's nothing to stand on but there is something to hang from.
        boolean hanging = context.getClickedFace() == Direction.DOWN
                || (!canSupportOnFloor(level, pos) && canSupportOnCeiling(level, pos));

        BlockState state = this.defaultBlockState()
                .setValue(DIRECTION, hanging ? TemporalDilatorDirection.DOWN : TemporalDilatorDirection.UP)
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);

        return this.canSurvive(state, level, pos) ? state : null;
    }

    @NullMarked
    @Override
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction direction, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        Direction supportDirection = state.getValue(DIRECTION) == TemporalDilatorDirection.UP
                ? Direction.DOWN
                : Direction.UP;

        return direction == supportDirection && !this.canSurvive(state, level, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, level, ticks, pos, direction, neighbourPos, neighbourState, random);
    }

    @Override
    public boolean canSurvive(BlockState state, @NonNull LevelReader level, @NonNull BlockPos pos) {
        return state.getValue(DIRECTION) == TemporalDilatorDirection.UP
                ? canSupportOnFloor(level, pos)
                : canSupportOnCeiling(level, pos);
    }

    @NullMarked
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    private static boolean canSupportOnFloor(LevelReader level, BlockPos pos) {
        BlockPos below = pos.below();
        return level.getBlockState(below).isFaceSturdy(level, below, Direction.UP);
    }

    private static boolean canSupportOnCeiling(LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        return level.getBlockState(above).isFaceSturdy(level, above, Direction.DOWN);
    }

    @NullMarked
    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TemporalDilatorBlockEntity(blockPos, blockState);
    }
}
