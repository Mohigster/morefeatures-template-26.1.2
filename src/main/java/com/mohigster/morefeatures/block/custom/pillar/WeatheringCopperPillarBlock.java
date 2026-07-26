package com.mohigster.morefeatures.block.custom.pillar;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

public class WeatheringCopperPillarBlock extends PillarBlock implements WeatheringCopper {
    public static final MapCodec<WeatheringCopperPillarBlock> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                    WeatherState.CODEC.fieldOf("weathering_state").forGetter(WeatheringCopperPillarBlock::getAge),
                    propertiesCodec()
            ).apply(i, WeatheringCopperPillarBlock::new)
    );

    private final WeatherState weatherState;

    public WeatheringCopperPillarBlock(WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    @NullMarked
    @Override
    public MapCodec<WeatheringCopperPillarBlock> codec() {
        return CODEC;
    }

    @NullMarked
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.changeOverTime(state, level, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(@NonNull BlockState state) {
        return this.weatherState != WeatherState.OXIDIZED;
    }

    @Override
    public @NonNull WeatherState getAge() {
        return this.weatherState;
    }
}
