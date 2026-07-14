package com.mohigster.morefeatures.block.custom;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpeleothemBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SpeleothemThickness;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.util.List;

public class IcicleBlock extends SpeleothemBlock {
    private static final int MAX_GROWING_LENGTH = 5;

    // Sulfur Spikes and pointed dripstone have this value hardcoded.
    // However, in the levelEvent method in the LevelEventHandler class, they have hardcoded event IDs.
    // 1052 returns the sulfur spike sound, 1045 the pointed dripstone sound.

    // 2001 allows you to set a custom sound, hence its usage here.
    private static final int STALACTITE_SOUND_LEVEL_EVENT_ID = 2001;

    private static final float FALL_DAMAGE_MODIFIER = 2.5F;

    private final List<BlockState> blocksToGrowOn;

    public static final MapCodec<IcicleBlock> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(
            BlockState.CODEC.listOf().fieldOf("blocks_to_grow_on").forGetter((b) -> b.blocksToGrowOn),
            propertiesCodec()
    ).apply(i, IcicleBlock::new));

    public IcicleBlock(List<BlockState> blocksToGrowOn, Properties properties) {
        super(Blocks.PACKED_ICE.defaultBlockState(), properties); // Passing Blocks.PACKED_ICE.defaultBlockState() into the super is just to keep the compiler happy because the base class wants a singular block state, not a list. This block state is unused by this class. Feel free to swap it out to whatever block you want, as a joke or whatever.
        this.blocksToGrowOn = blocksToGrowOn;
    }

    @Override
    public @NonNull MapCodec<IcicleBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getStalactiteLandingSound() {
        return STALACTITE_SOUND_LEVEL_EVENT_ID;
    }

    @NullMarked
    @Override
    public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity entity) {
        if (!entity.isSilent()) {
            int blockStateId = getBlockStateId(MFBlocks.ICICLE.get());
            level.levelEvent(this.getStalactiteLandingSound(), pos, blockStateId); // Will make the ice breaking sound and use the icicle textures for particles
        }
    }

    protected int getBlockStateId(Block block){
        return Block.getId(block.defaultBlockState());
    }

    @Override
    protected int getMaxGrowthLength() {
        return MAX_GROWING_LENGTH;
    }

    @NullMarked
    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (canMelt(state) && world.getBrightness(LightLayer.BLOCK, pos) > 11) {
            this.melt(world, pos);
        } else {
            super.randomTick(state, world, pos, random);
        }
    }

    private boolean canMelt(BlockState state){
        SpeleothemThickness thickness = state.getValue(THICKNESS);

        return thickness == SpeleothemThickness.TIP || thickness == SpeleothemThickness.TIP_MERGE;
    }

    // Icicles that can melt will drip water. Icicles that can NOT melt do not drip water
    @NullMarked
    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        // If it's a tip and warm enough to melt, make it drip water!
        if (canMelt(state) && world.getBrightness(LightLayer.BLOCK, pos) > 11) {
            // Only drip occasionally so it doesn't create a waterfall of particles
            if (random.nextFloat() < 0.15F) {
                double x = pos.getX() + random.nextDouble();
                // Spawn particle just slightly below the bottom of the block
                double y = pos.getY() - 0.05;
                double z = pos.getZ() + random.nextDouble();

                world.addParticle(ParticleTypes.DRIPPING_WATER, x, y, z, 0.0, 0.0, 0.0);
            }
        }
    }

    @NullMarked
    @Override // Make the block deal more fall damage, like dripstone, but I've made it slightly worse (dripstone's damage modifier is 2.0F)
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        if (state.getValue(TIP_DIRECTION) == Direction.UP && state.getValue(THICKNESS) == SpeleothemThickness.TIP) {
            entity.causeFallDamage(fallDistance + 2.5, FALL_DAMAGE_MODIFIER, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, state, pos, entity, fallDistance);
        }
    }

    @NullMarked
    @Override
    protected boolean canGrow(LevelReader level, BlockPos pos){
        Block blockToCheck = level.getBlockState(pos).getBlock();
        return this.blocksToGrowOn.stream().anyMatch(state -> state.is(blockToCheck));
    }

    protected void melt(Level world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.POINTED_DRIPSTONE_DRIP_WATER, SoundSource.BLOCKS, 0.5F, 1.5F);

        world.addParticle(ParticleTypes.SPLASH,
                pos.getX() + 0.5,
                pos.getY() + 0.1,
                pos.getZ() + 0.5,
                5.0,
                0.3,
                0.1
        );

        world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
    }
}
