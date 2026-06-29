package com.mohigster.morefeatures.block.custom;

import com.mohigster.morefeatures.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SpeleothemBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class IcicleBlock extends SpeleothemBlock {
    private static int MAX_GROWING_LENGTH = 4;

    // Sulfur Spikes and pointed dripstone have this value hardcoded.
    // However, in the levelEvent method in the LevelEventHandler class, they have hardcoded event IDs.
    // 1052 returns the sulfur spike sound, 1045 the pointed dripstone sound

    // 2001 allows you to set a custom sound, hence its usage here.
    private static int STALACTITE_SOUND_LEVEL_EVENT_ID = 2001;

    private final List<BlockState> blocksToGrowOn;


    public static final MapCodec<IcicleBlock> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(
            BlockState.CODEC.listOf().fieldOf("blocks_to_grow_on").forGetter((b) -> b.blocksToGrowOn),
            propertiesCodec()
    ).apply(i, IcicleBlock::new));



    public IcicleBlock(List<BlockState> blocksToGrowOn, Properties properties) {
        super(Blocks.PACKED_ICE.defaultBlockState(), properties); // Passing Blocks.PACKED_ICE.defaultBlockState() into the super allows me to leave it undefined when registering an icicle block, and instead register a list of valid blocks to grow on.
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

    @Override
    protected boolean canGrow(LevelReader level, BlockPos pos){
        Block blockToCheck = level.getBlockState(pos).getBlock();
        return this.blocksToGrowOn.stream().anyMatch(state -> state.is(blockToCheck));
    }

    protected void melt(Level world, BlockPos pos) {
        world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
    }
}
