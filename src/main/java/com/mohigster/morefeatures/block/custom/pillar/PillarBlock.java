package com.mohigster.morefeatures.block.custom.pillar;

import com.mohigster.morefeatures.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NullMarked;

public class PillarBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<PillarShape> SHAPE = EnumProperty.create("shape", PillarShape.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape CENTER_PILLAR_SHAPE = Block.box(2, 0, 2, 14, 16, 14);

    private static final VoxelShape BASE_PLATE_SHAPE = Block.box(0, 0, 0, 16, 2, 16);
    private static final VoxelShape TOP_PLATE_SHAPE = Block.box(0, 14, 0, 16, 16, 16);

    private static final VoxelShape SHAPE_MIDDLE = CENTER_PILLAR_SHAPE;
    private static final VoxelShape SHAPE_BOTTOM = Shapes.or(BASE_PLATE_SHAPE, CENTER_PILLAR_SHAPE);
    private static final VoxelShape SHAPE_TOP = Shapes.or(TOP_PLATE_SHAPE, CENTER_PILLAR_SHAPE);
    private static final VoxelShape SHAPE_FULL = Shapes.or(BASE_PLATE_SHAPE, TOP_PLATE_SHAPE, CENTER_PILLAR_SHAPE);

    public PillarBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SHAPE, PillarShape.FULL)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SHAPE, WATERLOGGED);
    }

    @NullMarked
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @NullMarked
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(SHAPE)) {
            case BOTTOM -> SHAPE_BOTTOM;
            case TOP -> SHAPE_TOP;
            case MIDDLE -> SHAPE_MIDDLE;
            case FULL -> SHAPE_FULL;
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        FluidState fluidState = level.getFluidState(pos);

        return calculateShape(level, pos, fluidState);
    }

    @NullMarked
    @Override
    public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos currentPos, Direction directionToNeighbour, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (state.getValue(WATERLOGGED)) {
            ticks.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        if (directionToNeighbour == Direction.UP || directionToNeighbour == Direction.DOWN) {
            return calculateShape(level, currentPos, level.getFluidState(currentPos));
        }
        return super.updateShape(state, level, ticks, currentPos, directionToNeighbour, neighborPos, neighborState, random);
    }

    // Helper method to determine shape based on vertical neighbors
    private BlockState calculateShape(LevelReader level, BlockPos pos, FluidState fluidState) {
        boolean connectAbove = canConnectTo(level.getBlockState(pos.above()));
        boolean connectBelow = canConnectTo(level.getBlockState(pos.below()));

        if (connectAbove && connectBelow) {
            return this.defaultBlockState().setValue(SHAPE, PillarShape.MIDDLE).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        } else if (connectAbove) {
            return this.defaultBlockState().setValue(SHAPE, PillarShape.BOTTOM).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        } else if (connectBelow) {
            return this.defaultBlockState().setValue(SHAPE, PillarShape.TOP).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        } else {
            return this.defaultBlockState().setValue(SHAPE, PillarShape.FULL).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        }
    }

    // Pillar blocks don't need to be added to the tag, only non pillar blocks that pillars should connect to will need to be in the tag
    private boolean canConnectTo(BlockState state) {
        return state.getBlock() instanceof PillarBlock || state.is(MFBlockTags.PILLAR_CONNECTABLE);
    }
}