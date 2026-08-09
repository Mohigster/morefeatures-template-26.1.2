package com.mohigster.morefeatures.block.custom.modified.nethervines.smoldered;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.TwistingVinesPlantBlock;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class SmolderedVinesPlantBlock extends TwistingVinesPlantBlock {
    private final Supplier<SmolderedVinesBlock> headBlock;
    public static final MapCodec<SmolderedVinesPlantBlock> CODEC = simpleCodec(SmolderedVinesPlantBlock::new);

    public SmolderedVinesPlantBlock(Supplier<SmolderedVinesBlock> headBlock, Properties properties) {
        super(properties);
        this.headBlock = headBlock;
    }

    public SmolderedVinesPlantBlock(Properties properties) {
        this(MFBlocks.SMOLDERED_VINES, properties);
    }

    @Override
    protected @NonNull GrowingPlantHeadBlock getHeadBlock() {
        return this.headBlock.get();
    }
}
