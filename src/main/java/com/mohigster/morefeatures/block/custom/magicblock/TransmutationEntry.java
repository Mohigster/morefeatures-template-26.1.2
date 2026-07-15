package com.mohigster.morefeatures.block.custom.magicblock;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public record TransmutationEntry(TagKey<Item> inputTag, Item output) {
    public static final Codec<TransmutationEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            TagKey.codec(Registries.ITEM).fieldOf("input_tag").forGetter(TransmutationEntry::inputTag),
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("output_item").forGetter(TransmutationEntry::output)
    ).apply(instance, TransmutationEntry::new));
}
