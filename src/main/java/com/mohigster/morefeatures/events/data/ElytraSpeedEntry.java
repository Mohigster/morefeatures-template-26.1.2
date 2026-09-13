package com.mohigster.morefeatures.events.data;

import com.mohigster.morefeatures.util.MFExtraCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public record ElytraSpeedEntry(HolderSet<Item> elytra, double percentSpeedBoost, double maximumSpeed) {
    public static final Codec<ElytraSpeedEntry> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    MFExtraCodecs.ITEM_SET.fieldOf("values")
                            .forGetter(ElytraSpeedEntry::elytra),
                    MFExtraCodecs.POSITIVE_DOUBLE.fieldOf("percent_speed_boost")
                            .forGetter(ElytraSpeedEntry::percentSpeedBoost),
                    // A value of zero defaults to a formula based on the percentSpeedBoost, hence why we allow zero
                    MFExtraCodecs.NON_NEGATIVE_DOUBLE.optionalFieldOf("maximum_speed", 0.0D)
                            .forGetter(ElytraSpeedEntry::maximumSpeed)
            ).apply(
                    inst,
                    ElytraSpeedEntry::new
            )
    );
}
