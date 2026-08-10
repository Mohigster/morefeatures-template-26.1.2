package com.mohigster.morefeatures.block.custom.modified.nethervines.scorched;

import com.mohigster.morefeatures.block.MFBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeepingVinesBlock;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class CeilingVinesBlock extends WeepingVinesBlock {
    private final Supplier<Block> bodyBlock;

    public CeilingVinesBlock(Supplier<Block> bodyBlock, Properties properties) {
        super(properties);
        this.bodyBlock = bodyBlock;
    }

    public CeilingVinesBlock(Properties properties) {
        this(MFBlocks.SCORCHED_VINES_PLANT, properties);
    }

    @Override
    protected @NonNull Block getBodyBlock() {
        return this.bodyBlock.get();
    }
}
