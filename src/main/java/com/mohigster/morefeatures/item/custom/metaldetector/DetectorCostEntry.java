package com.mohigster.morefeatures.item.custom.metaldetector;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Block;

public record DetectorCostEntry(HolderSet<Block> inputValues, int durabilityCost) {
    public static final Codec<DetectorCostEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("values")
                    .forGetter(DetectorCostEntry::inputValues),
            ExtraCodecs.POSITIVE_INT.fieldOf("durability_cost") // Cannot be zero
                    .forGetter(DetectorCostEntry::durabilityCost)
    ).apply(instance, DetectorCostEntry::new));
}
