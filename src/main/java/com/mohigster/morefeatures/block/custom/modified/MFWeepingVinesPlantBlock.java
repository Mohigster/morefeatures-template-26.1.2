package com.mohigster.morefeatures.block.custom.modified;

import com.mohigster.morefeatures.block.MFBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.WeepingVinesPlantBlock;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class MFWeepingVinesPlantBlock extends WeepingVinesPlantBlock {
    private final Supplier<Block> headBlock;

    public MFWeepingVinesPlantBlock(Supplier<Block> headBlock, Properties properties) {
        super(properties);
        this.headBlock = headBlock;
    }

    public MFWeepingVinesPlantBlock(Properties properties) {
        this(MFBlocks.SCORCHED_VINES, properties);
    }

    @Override
    protected @NonNull GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) this.headBlock.get();
    }
}
