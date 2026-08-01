package com.mohigster.morefeatures.events.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public record BowDamageEntry(HolderSet<Item> bows, double damageBonus) {
    public static final Codec<HolderSet<Item>> ITEM_HOLDER_SET_CODEC =
            RegistryCodecs.homogeneousList(Registries.ITEM);

    public static final Codec<BowDamageEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ITEM_HOLDER_SET_CODEC.fieldOf("values").forGetter(BowDamageEntry::bows),
            Codec.DOUBLE.fieldOf("damage_bonus").forGetter(BowDamageEntry::damageBonus)
    ).apply(instance, BowDamageEntry::new));
}
