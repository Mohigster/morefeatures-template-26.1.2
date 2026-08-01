package com.mohigster.morefeatures.block.custom.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.List;

public record BonemealMorphData(List<Block> variants) {
    public static final Codec<BonemealMorphData> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            BuiltInRegistries.BLOCK.byNameCodec().listOf().fieldOf("variants").forGetter(BonemealMorphData::variants)
    ).apply(inst, BonemealMorphData::new));
}
