package com.mohigster.morefeatures.block.custom.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.List;

public record BonemealMorph(List<Block> variants) {
    public static final Codec<BonemealMorph> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    BuiltInRegistries.BLOCK.byNameCodec().listOf().fieldOf("variants")
                            .forGetter(BonemealMorph::variants)
            ).apply(
                    inst,
                    BonemealMorph::new
            )
    );
}
