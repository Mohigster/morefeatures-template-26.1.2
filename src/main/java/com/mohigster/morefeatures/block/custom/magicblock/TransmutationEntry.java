package com.mohigster.morefeatures.block.custom.magicblock;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public record TransmutationEntry(TagKey<Item> inputTag, Item output, int extraItems, boolean copyComponents) {
    public static final Codec<TransmutationEntry> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    TagKey.codec(Registries.ITEM).fieldOf("input_tag")
                            .forGetter(TransmutationEntry::inputTag),
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("output_item")
                            .forGetter(TransmutationEntry::output),
                    Codec.INT.optionalFieldOf("extra_items", 0)
                            .forGetter(TransmutationEntry::extraItems),
                    Codec.BOOL.optionalFieldOf("copy_components", false)
                            .forGetter(TransmutationEntry::copyComponents)
            ).apply(
                    instance,
                    TransmutationEntry::new
            )
    );
}
