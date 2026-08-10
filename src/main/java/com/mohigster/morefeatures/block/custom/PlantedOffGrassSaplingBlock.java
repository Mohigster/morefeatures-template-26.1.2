package com.mohigster.morefeatures.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

import java.util.function.Supplier;

public class PlantedOffGrassSaplingBlock extends SaplingBlock {
    private final Supplier<Block> blockToSurviveOn;
    private final boolean constructedWithTag;
    private final TagKey<Block> blocksToSurviveOn;

    private PlantedOffGrassSaplingBlock(TreeGrower treeGrower, Properties properties, Supplier<Block> blockToSurviveOn, TagKey<Block> blocksToSurviveOn, boolean constructedWithTag) {
        super(treeGrower, properties);
        this.blockToSurviveOn = blockToSurviveOn;
        this.constructedWithTag = constructedWithTag;
        this.blocksToSurviveOn =  blocksToSurviveOn;
    }

    public PlantedOffGrassSaplingBlock(TreeGrower treeGrower, Supplier<Block> blockToSurviveOn, Properties properties) {
        this(treeGrower, properties, blockToSurviveOn, BlockTags.GRASS_BLOCKS, false);
    }

    public PlantedOffGrassSaplingBlock(TreeGrower treeGrower, TagKey<Block> blocksToSurviveOn, Properties properties) {
        this(treeGrower, properties, () -> Blocks.GRASS_BLOCK, blocksToSurviveOn, true);
    }

    @NullMarked
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return this.constructedWithTag ? state.is(this.blocksToSurviveOn) : state.is(this.blockToSurviveOn.get());
    }
}
