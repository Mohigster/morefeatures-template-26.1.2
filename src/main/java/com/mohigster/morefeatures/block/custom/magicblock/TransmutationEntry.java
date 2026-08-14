package com.mohigster.morefeatures.block.custom.magicblock;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.Item;

public record TransmutationEntry(
        HolderSet<Item> inputValue,
        Item output,
        int extraItems,
        boolean copyComponents
) {
    public static final Codec<TransmutationEntry> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    RegistryCodecs.homogeneousList(Registries.ITEM).fieldOf("input_values")
                            .forGetter(TransmutationEntry::inputValue),
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("output_item")
                            .forGetter(TransmutationEntry::output),
                    ExtraCodecs.NON_NEGATIVE_INT.optionalFieldOf("extra_items", 0)
                            .forGetter(TransmutationEntry::extraItems),
                    Codec.BOOL.optionalFieldOf("copy_components", false)
                            .forGetter(TransmutationEntry::copyComponents)
            ).apply(
                    instance,
                    TransmutationEntry::new
            )
    );
}
