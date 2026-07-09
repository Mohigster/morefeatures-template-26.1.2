package com.mohigster.morefeatures.block.custom.verticalslab;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

public class WeatheringCopperVerticalSlabBlock extends VerticalSlabBlock implements WeatheringCopper {

    public static final MapCodec<WeatheringCopperVerticalSlabBlock> CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                    WeatherState.CODEC.fieldOf("weathering_state").forGetter(WeatheringCopperVerticalSlabBlock::getAge),
                    propertiesCodec()
            ).apply(i, WeatheringCopperVerticalSlabBlock::new)
    );

    private final WeatherState weatherState;

    public WeatheringCopperVerticalSlabBlock(WeatherState weatherState, Properties properties) {
        super(false, properties); // Copper slabs are not flammable, so we hard code this value to false
        this.weatherState = weatherState;
    }

    @Override
    public MapCodec<WeatheringCopperVerticalSlabBlock> codec() {
        return CODEC;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.changeOverTime(state, level, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return this.weatherState != WeatherState.OXIDIZED;
    }

    @Override
    public WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }
}