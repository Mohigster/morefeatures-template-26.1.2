package com.mohigster.morefeatures.block.custom.flammable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MFLeavesBlock extends UntintedParticleLeavesBlock {
    public MFLeavesBlock(float leafParticleChance, ParticleOptions leafParticle, Properties properties) {
        super(leafParticleChance, leafParticle, properties);
    }

    @Override
    public void spawnFallingLeavesParticle(Level level, BlockPos pos, RandomSource random){
        double x = (double)pos.getX() + random.nextDouble();
        double y = (double)pos.getY() - 0.05;
        double z = (double)pos.getZ() + random.nextDouble();
        level.addParticle(leafParticle, x, y, z, 0.0D, 0.0D, 0.0D);
    }

    @NullMarked
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return true;
    }

    @NullMarked
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return 60;
    }

    @NullMarked
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return 30;
    }
}
