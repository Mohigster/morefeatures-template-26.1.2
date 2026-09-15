package com.mohigster.morefeatures.data.world.feature.custom.config.nethervine;

import com.mohigster.morefeatures.util.MFExtraCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record FloorVinesConfiguration(
        BlockState vinesState,
        BlockState vinesPlantState,
        HolderSet<Block> validSupportBlocks,
        int spreadWidth,
        int spreadHeight,
        int maxHeight
) implements FeatureConfiguration {
    public static final Codec<FloorVinesConfiguration> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    BlockState.CODEC.fieldOf("vines")
                            .forGetter(FloorVinesConfiguration::vinesState),
                    BlockState.CODEC.fieldOf("vines_plant")
                            .forGetter(FloorVinesConfiguration::vinesPlantState),
                    MFExtraCodecs.BLOCK_SET.fieldOf("valid_support_blocks")
                            .forGetter(FloorVinesConfiguration::validSupportBlocks),
                    ExtraCodecs.POSITIVE_INT.fieldOf("spread_width")
                            .forGetter(FloorVinesConfiguration::spreadWidth),
                    ExtraCodecs.POSITIVE_INT.fieldOf("spread_height")
                            .forGetter(FloorVinesConfiguration::spreadHeight),
                    ExtraCodecs.POSITIVE_INT.fieldOf("max_height")
                            .forGetter(FloorVinesConfiguration::maxHeight)
            ).apply(
                    inst,
                    FloorVinesConfiguration::new
            )
    );
}
