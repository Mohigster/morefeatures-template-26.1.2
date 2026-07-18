package com.mohigster.morefeatures.item.custom.metaldetector;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public record DetectorCostEntry(TagKey<Block> inputTag, int durabilityCost) {
    public static final Codec<DetectorCostEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            TagKey.codec(Registries.BLOCK).fieldOf("tag").forGetter(DetectorCostEntry::inputTag),
            Codec.INT.fieldOf("cost").forGetter(DetectorCostEntry::durabilityCost)
    ).apply(instance, DetectorCostEntry::new));
}
