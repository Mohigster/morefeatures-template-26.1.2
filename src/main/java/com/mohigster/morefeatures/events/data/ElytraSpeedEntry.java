package com.mohigster.morefeatures.events.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public record ElytraSpeedEntry(HolderSet<Item> elytra, double percentSpeedBoost, double maximumSpeed) {
    public static final Codec<HolderSet<Item>> ITEM_HOLDER_SET_CODEC =
            RegistryCodecs.homogeneousList(Registries.ITEM);

    public static final Codec<ElytraSpeedEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ITEM_HOLDER_SET_CODEC.fieldOf("values").forGetter(ElytraSpeedEntry::elytra),
            Codec.DOUBLE.fieldOf("percent_speed_boost").forGetter(ElytraSpeedEntry::percentSpeedBoost),
            Codec.DOUBLE.optionalFieldOf("maximum_speed", 0D).forGetter(ElytraSpeedEntry::maximumSpeed)
    ).apply(instance, ElytraSpeedEntry::new));
}
