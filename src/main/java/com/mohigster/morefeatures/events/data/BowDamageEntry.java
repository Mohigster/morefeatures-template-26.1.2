package com.mohigster.morefeatures.events.data;

import com.mohigster.morefeatures.util.MFExtraCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public record BowDamageEntry(HolderSet<Item> bows, double damageBonus) {
    public static final Codec<BowDamageEntry> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    RegistryCodecs.homogeneousList(Registries.ITEM).fieldOf("values")
                            .forGetter(BowDamageEntry::bows),
                    MFExtraCodecs.POSITIVE_DOUBLE.fieldOf("damage_bonus")
                            .forGetter(BowDamageEntry::damageBonus)
            ).apply(
                    inst,
                    BowDamageEntry::new
            )
    );
}
