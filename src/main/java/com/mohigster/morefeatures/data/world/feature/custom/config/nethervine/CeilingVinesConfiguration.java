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

public record CeilingVinesConfiguration(
        BlockState vinesState,
        BlockState vinesPlantState,
        BlockState roofBlockState,
        HolderSet<Block> validSupportBlocks,
        int spreadWidth,
        int spreadHeight,
        int maxHeight
) implements FeatureConfiguration {
    public static final Codec<CeilingVinesConfiguration> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    BlockState.CODEC.fieldOf("vines")
                            .forGetter(CeilingVinesConfiguration::vinesState),
                    BlockState.CODEC.fieldOf("vines_plant")
                            .forGetter(CeilingVinesConfiguration::vinesPlantState),
                    BlockState.CODEC.fieldOf("roof_block")
                            .forGetter(CeilingVinesConfiguration::roofBlockState),
                    MFExtraCodecs.BLOCK_SET.fieldOf("valid_support_blocks")
                            .forGetter(CeilingVinesConfiguration::validSupportBlocks),
                    ExtraCodecs.POSITIVE_INT.fieldOf("spread_width")
                            .forGetter(CeilingVinesConfiguration::spreadWidth),
                    ExtraCodecs.POSITIVE_INT.fieldOf("spread_height")
                            .forGetter(CeilingVinesConfiguration::spreadHeight),
                    ExtraCodecs.POSITIVE_INT.fieldOf("max_height")
                            .forGetter(CeilingVinesConfiguration::maxHeight)
            ).apply(
                    inst,
                    CeilingVinesConfiguration::new
            )
    );
}
