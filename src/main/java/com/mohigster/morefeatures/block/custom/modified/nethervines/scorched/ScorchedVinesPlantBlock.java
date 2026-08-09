package com.mohigster.morefeatures.block.custom.modified.nethervines.scorched;

import com.mohigster.morefeatures.block.MFBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.WeepingVinesPlantBlock;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class ScorchedVinesPlantBlock extends WeepingVinesPlantBlock {
    private final Supplier<Block> headBlock;

    public ScorchedVinesPlantBlock(Supplier<Block> headBlock, Properties properties) {
        super(properties);
        this.headBlock = headBlock;
    }

    public ScorchedVinesPlantBlock(Properties properties) {
        this(MFBlocks.SCORCHED_VINES, properties);
    }

    @Override
    protected @NonNull GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) this.headBlock.get();
    }
}
