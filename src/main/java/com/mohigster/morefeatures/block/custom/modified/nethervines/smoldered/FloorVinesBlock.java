package com.mohigster.morefeatures.block.custom.modified.nethervines.smoldered;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TwistingVinesBlock;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class FloorVinesBlock extends TwistingVinesBlock {
    private final Supplier<FloorVinesPlantBlock> bodyBlock;

    public FloorVinesBlock(Supplier<FloorVinesPlantBlock> bodyBlock, Properties properties) {
        super(properties);
        this.bodyBlock = bodyBlock;
    }

    public FloorVinesBlock(Properties properties) {
        this(MFBlocks.SMOLDERED_VINES_PLANT, properties);
    }

    @Override
    protected @NonNull Block getBodyBlock() {
        return this.bodyBlock.get();
    }
}
