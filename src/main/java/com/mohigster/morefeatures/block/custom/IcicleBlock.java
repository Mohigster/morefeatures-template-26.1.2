package com.mohigster.morefeatures.block.custom;

import com.mohigster.morefeatures.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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

import java.util.List;

public class IcicleBlock extends SpeleothemBlock {
    private static final int MAX_GROWING_LENGTH = 4;

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
    public MapCodec<IcicleBlock> codec() {
        return CODEC;
    }

    @Override
    protected int getStalactiteLandingSound() {
        return STALACTITE_SOUND_LEVEL_EVENT_ID;
    }

    @Override
    public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity entity) {
        if (!entity.isSilent()) {
            int glassStateId = Block.getId(ModBlocks.ICICLE.get().defaultBlockState());
            level.levelEvent(this.getStalactiteLandingSound(), pos, glassStateId); // Will make the ice breaking sound and use the icicle textures for particles
        }
    }

    @Override
    protected int getMaxGrowthLength() {
        return MAX_GROWING_LENGTH;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (world.getBrightness(LightLayer.BLOCK, pos) > 11) {
            this.melt(world, pos);
        } else {
            // Fallback to the regular speleothem growth logic if it doesn't melt
            super.randomTick(state, world, pos, random);
        }
    }


    @Override // Make the block deal more fall damage, like dripstone, but I've made it slightly worse (dripstone's damage modifier is 2.0F)
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        if (state.getValue(TIP_DIRECTION) == Direction.UP && state.getValue(THICKNESS) == SpeleothemThickness.TIP) {
            entity.causeFallDamage(fallDistance + 2.5, FALL_DAMAGE_MODIFIER, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, state, pos, entity, fallDistance);
        }
    }

    @Override
    protected boolean canGrow(LevelReader level, BlockPos pos){
        Block blockToCheck = level.getBlockState(pos).getBlock();
        return this.blocksToGrowOn.stream().anyMatch(state -> state.is(blockToCheck));
    }

    protected void melt(Level world, BlockPos pos) {
        world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
    }
}
