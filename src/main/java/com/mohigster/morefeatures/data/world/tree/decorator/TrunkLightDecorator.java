package com.mohigster.morefeatures.data.world.tree.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jspecify.annotations.NullMarked;

import java.util.List;

public class TrunkLightDecorator extends TreeDecorator {
    public static final MapCodec<TrunkLightDecorator> CODEC =
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    Codec.FLOAT.fieldOf("probability").forGetter(d -> d.probability),
                    BlockStateProvider.CODEC.fieldOf("block").forGetter(d -> d.blockProvider)
            ).apply(instance, TrunkLightDecorator::new));

    private final float probability;
    private final BlockStateProvider blockProvider;

    public TrunkLightDecorator(float probability, BlockStateProvider blockProvider) {
        this.probability = probability;
        this.blockProvider = blockProvider;
    }

    @NullMarked
    @Override
    protected TreeDecoratorType<?> type() {
        return MFTreeDecorators.TRUNK_LIGHT.get();
    }

    @Override
    public void place(Context context) {
        RandomSource random = context.random();
        List<BlockPos> logs = context.logs();
        WorldGenLevel level = context.level();

        for (BlockPos pos : logs) {
            if (random.nextFloat() < this.probability) {
                BlockState state = blockProvider.getState(level, random, pos);
                // Only place if the block at this position is already a log
                // (trunk placer will have placed logs; we're replacing one)
                context.setBlock(pos, state);
            }
        }
    }
}
