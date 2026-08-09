package com.mohigster.morefeatures.data.world.feature.custom.config.nethervine;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record NetherVinesConfiguration(
        BlockState vinesState,
        BlockState vinesPlantState,
        HolderSet<Block> validSupportBlocks,
        int spreadWidth,
        int spreadHeight,
        int maxHeight
) implements FeatureConfiguration {
    public static final Codec<NetherVinesConfiguration> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    BlockState.CODEC.fieldOf("vines")
                            .forGetter(NetherVinesConfiguration::vinesState),
                    BlockState.CODEC.fieldOf("vines_plant")
                            .forGetter(NetherVinesConfiguration::vinesPlantState),
                    RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("valid_support_blocks")
                            .forGetter(NetherVinesConfiguration::validSupportBlocks),
                    ExtraCodecs.POSITIVE_INT.fieldOf("spread_width")
                            .forGetter(NetherVinesConfiguration::spreadWidth),
                    ExtraCodecs.POSITIVE_INT.fieldOf("spread_height")
                            .forGetter(NetherVinesConfiguration::spreadHeight),
                    ExtraCodecs.POSITIVE_INT.fieldOf("max_height")
                            .forGetter(NetherVinesConfiguration::maxHeight)
            ).apply(
                    inst,
                    NetherVinesConfiguration::new
            )
    );
}
