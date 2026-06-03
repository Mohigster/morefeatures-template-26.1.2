package com.mohigster.morefeatures.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PointedIceBlock extends PointedDripstoneBlock {
    public PointedIceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        boolean isStalactite = state.getValue(TIP_DIRECTION) == Direction.DOWN;

        if (isStalactite && random.nextFloat() < 0.1F) {
            BlockPos blockPos = pos.below();
            BlockState blockState = level.getBlockState(blockPos);
            if (!blockState.isCollisionShapeFullBlock(level, blockPos)) {
                // Spawn a snowflake or blue water droplet instead of a standard water droplet
                double x = pos.getX() + random.nextDouble();
                double y = pos.getY() - 0.05D;
                double z = pos.getZ() + random.nextDouble();
                level.addParticle(ParticleTypes.SNOWFLAKE, x, y, z, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
