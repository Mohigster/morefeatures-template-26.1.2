package com.mohigster.morefeatures.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class PlantedOffGrassSaplingBlock extends SaplingBlock {
    private final Block blockToSurviveOn;
    private final boolean constructedWithTag;
    private final TagKey<Block> blocksToSurviveOn;

    public PlantedOffGrassSaplingBlock(TreeGrower treeGrower, Properties properties, Block blockToSurviveOn) {
        super(treeGrower, properties);
        this.blockToSurviveOn = blockToSurviveOn;
        this.constructedWithTag = false;
        this.blocksToSurviveOn = BlockTags.GRASS_BLOCKS; // Passed in to keep the compiler happy
    }

    public PlantedOffGrassSaplingBlock(TreeGrower treeGrower, Properties properties, TagKey<Block> blocksToSurviveOn) {
        super(treeGrower, properties);

        this.blockToSurviveOn = Blocks.GRASS_BLOCK; // Same as above, for the compiler but unused
        this.constructedWithTag = true;
        this.blocksToSurviveOn = blocksToSurviveOn;
    }

    @NullMarked
    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return this.constructedWithTag ? state.is(this.blocksToSurviveOn) : state.is(this.blockToSurviveOn);
    }
}
