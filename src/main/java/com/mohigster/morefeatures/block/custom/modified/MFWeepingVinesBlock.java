package com.mohigster.morefeatures.block.custom.modified;

import com.mohigster.morefeatures.block.MFBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeepingVinesBlock;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class MFWeepingVinesBlock extends WeepingVinesBlock {
    private final Supplier<Block> bodyBlock;

    public MFWeepingVinesBlock(Supplier<Block> bodyBlock, Properties properties) {
        super(properties);
        this.bodyBlock = bodyBlock;
    }

    public MFWeepingVinesBlock(Properties properties) {
        this(MFBlocks.SCORCHED_VINES_PLANT, properties);
    }

    @Override
    protected @NonNull Block getBodyBlock() {
        return this.bodyBlock.get();
    }
}
