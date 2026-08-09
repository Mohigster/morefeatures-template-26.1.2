package com.mohigster.morefeatures.block.custom.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;

public record Flammable(
        int flammability,
        int fireSpreadSpeed
) {
    public static final Codec<Flammable> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    ExtraCodecs.POSITIVE_INT.fieldOf("flammability")
                            .forGetter(Flammable::flammability),
                    ExtraCodecs.POSITIVE_INT.fieldOf("fire_spread_speed")
                            .forGetter(Flammable::fireSpreadSpeed)
            ).apply(
                    inst,
                    Flammable::new
            )
    );
}
