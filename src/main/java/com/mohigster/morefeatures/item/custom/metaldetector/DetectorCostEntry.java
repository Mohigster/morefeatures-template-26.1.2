package com.mohigster.morefeatures.item.custom.metaldetector;

import com.mohigster.morefeatures.util.MFExtraCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Block;

public record DetectorCostEntry(HolderSet<Block> inputValues, int durabilityCost) {
    public static final Codec<DetectorCostEntry> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    MFExtraCodecs.BLOCK_SET.fieldOf("values")
                            .forGetter(DetectorCostEntry::inputValues),
                    ExtraCodecs.POSITIVE_INT.fieldOf("durability_cost") // Cannot be zero
                            .forGetter(DetectorCostEntry::durabilityCost)
            ).apply(
                    inst,
                    DetectorCostEntry::new
            )
    );
}
