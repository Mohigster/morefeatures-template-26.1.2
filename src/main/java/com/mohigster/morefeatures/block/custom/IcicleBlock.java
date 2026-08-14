package com.mohigster.morefeatures.block.custom;

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
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
public class IcicleBlock extends SpeleothemBlock {
    private static final int MAX_GROWING_LENGTH = 5;

    // Sulfur Spikes and pointed dripstone have this value hardcoded.
    // However, in the levelEvent method in the LevelEventHandler class, they have hardcoded event IDs.
    // 1052 returns the sulfur spike sound, 1045 the pointed dripstone sound.

    // 2001 allows you to set a custom sound, hence its usage here.
    private static final int STALACTITE_SOUND_LEVEL_EVENT_ID = 2001;
    private static final int MELTING_LIGHT_LEVEL = 11;

    private static final double STALACTITE_DRIP_START_PIXEL = SHAPE_TIP_DOWN.min(Direction.Axis.Y);
    private static final double DRIP_PIXEL_SIZE = 0.0625D;
    private static final double STALAGMITE_DRIP_XZ_OFFSET_RANGE = 0.3D;

    private static final float FALL_DAMAGE_MODIFIER = 2.5F;
    private static final float PARTICLE_SPAWN_PERCENT_CHANCE = 7.5F; // 7.5% chance

    private final List<BlockState> blocksToGrowOn;

    public static final MapCodec<IcicleBlock> CODEC = RecordCodecBuilder.mapCodec((i) -> i.group(
            BlockState.CODEC.listOf().fieldOf("blocks_to_grow_on").forGetter((b) -> b.blocksToGrowOn),
            propertiesCodec()
    ).apply(i, IcicleBlock::new));

    public IcicleBlock(List<BlockState> blocksToGrowOn, Properties properties) {
        super(Blocks.PACKED_ICE.defaultBlockState(), properties); // Passing Blocks.PACKED_ICE.defaultBlockState() into the super is just to keep the compiler happy because the base class wants a singular block state, not a list. This block state is unused by this class.
                                                                  // I have checked SpeleothemBlock. Within that class, the blockToGrowIn Block State passed in here is ONLY used in the canGrow method. Since we override that to use the list anyway, this will have no side effects.

        if(blocksToGrowOn.isEmpty()) this.blocksToGrowOn = this.defaultValidGrowthBlocks();
        else this.blocksToGrowOn = blocksToGrowOn;
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
            level.levelEvent(this.getStalactiteLandingSound(), pos, Block.getId(entity.getBlockState())); // Will make the ice breaking sound and use the icicle textures for particles
        }
    }

    @Override
    protected int getMaxGrowthLength() {
        return MAX_GROWING_LENGTH;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (canMelt(level, state, pos)){
            this.melt(level, pos);
        } else {
            super.randomTick(state, level, pos, random);
        }
    }

    protected int minimumLightLevelToTriggerMelting(){
        return MELTING_LIGHT_LEVEL;
    }

    private boolean canMelt(Level level, BlockState state, BlockPos pos){
        return isTipOrMerge(state) && isBrightEnoughToMelt(level, pos);
    }


    // SpeleothemBlock does hav an isTip boolean with a parameter to determine to include merge tips, but it has private access
    // Since I never check only for the tip, and always check for both tip and merge, this method is fine
    protected boolean isTipOrMerge(BlockState state){
        SpeleothemThickness thickness = state.getValue(THICKNESS);

        return thickness == SpeleothemThickness.TIP || thickness == SpeleothemThickness.TIP_MERGE;
    }

    protected boolean isBrightEnoughToMelt(Level level, BlockPos pos){
        return level.getBrightness(LightLayer.BLOCK, pos) >= minimumLightLevelToTriggerMelting();
    }

    // Icicles will only drip water if they are in danger of melting!
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (canMelt(level, state, pos) && random.nextFloat() < (PARTICLE_SPAWN_PERCENT_CHANCE / 100)){
            spawnDripParticle(level, pos, state, random);
        } else {
            super.animateTick(state, level, pos, random);
        }
    }

    @Override // Make the block deal more fall damage, like dripstone, but I've made it slightly worse (dripstone's damage modifier is 2.0F, this is 2.5F)
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        if (state.getValue(TIP_DIRECTION) == Direction.UP && state.getValue(THICKNESS) == SpeleothemThickness.TIP) {
            entity.causeFallDamage(fallDistance + 2.5, FALL_DAMAGE_MODIFIER, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, state, pos, entity, fallDistance);
        }
    }

    @Override
    protected boolean canGrow(LevelReader level, BlockPos pos){
        Block blockToCheck = level.getBlockState(pos.above()).getBlock();
        return this.blocksToGrowOn.stream().anyMatch(state -> state.is(blockToCheck));
    }

    protected void spawnDripParticle(Level level, BlockPos stalactiteTipPos, BlockState stalactiteTipState, RandomSource random){
        Vec3 offset = stalactiteTipState.getOffset(stalactiteTipPos);
        double x = stalactiteTipPos.getX() + 0.5D + offset.x;
        double y = stalactiteTipPos.getY() + STALACTITE_DRIP_START_PIXEL - DRIP_PIXEL_SIZE;
        double z = stalactiteTipPos.getZ() + 0.5D + offset.z;

        if (stalactiteTipState.getValue(TIP_DIRECTION) == Direction.UP) {
            x += (random.nextDouble() - 0.5D) * STALAGMITE_DRIP_XZ_OFFSET_RANGE;
            z += (random.nextDouble() - 0.5D) * STALAGMITE_DRIP_XZ_OFFSET_RANGE;
        }

        level.addParticle(ParticleTypes.DRIPPING_DRIPSTONE_WATER, x, y, z, 0.0D, 0.0D, 0.0D);
    }

    protected void melt(Level level, BlockPos pos) {
        level.playSound(null, pos, SoundEvents.POINTED_DRIPSTONE_DRIP_WATER, SoundSource.BLOCKS, 0.5F, 1.5F);

        level.addParticle(ParticleTypes.SPLASH,
                pos.getX() + 0.5,
                pos.getY() + 0.1,
                pos.getZ() + 0.5,
                5.0,
                0.3,
                0.1
        );

        level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
    }

    @Override
    public boolean isPathfindable(BlockState state, PathComputationType type){
        return false;
    }

    protected List<BlockState> defaultValidGrowthBlocks(){
        return List.of(
                Blocks.PACKED_ICE.defaultBlockState(),
                Blocks.BLUE_ICE.defaultBlockState()
        );
    }
}
