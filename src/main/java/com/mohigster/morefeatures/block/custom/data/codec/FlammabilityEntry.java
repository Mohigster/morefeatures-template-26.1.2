package com.mohigster.morefeatures.block.custom.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;

public record FlammabilityEntry(
        int flammability,
        int fireSpreadSpeed
) {
    public static final Codec<FlammabilityEntry> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    ExtraCodecs.POSITIVE_INT.fieldOf("flammability")
                            .forGetter(FlammabilityEntry::flammability),
                    ExtraCodecs.POSITIVE_INT.fieldOf("fire_spread_speed")
                            .forGetter(FlammabilityEntry::fireSpreadSpeed)
            ).apply(
                    inst,
                    FlammabilityEntry::new
            )
    );
}
