package com.mohigster.morefeatures.block.custom.modified.nethervines.smoldered;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TwistingVinesBlock;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public class SmolderedVinesBlock extends TwistingVinesBlock {
    private final Supplier<SmolderedVinesPlantBlock> bodyBlock;
    public static final MapCodec<SmolderedVinesBlock> CODEC = simpleCodec(SmolderedVinesBlock::new);

    public SmolderedVinesBlock(Supplier<SmolderedVinesPlantBlock> bodyBlock, Properties properties) {
        super(properties);
        this.bodyBlock = bodyBlock;
    }

    public SmolderedVinesBlock(Properties properties) {
        this(MFBlocks.SMOLDERED_VINES_PLANT, properties);
    }

    @Override
    protected @NonNull Block getBodyBlock() {
        return this.bodyBlock.get();
    }
}
