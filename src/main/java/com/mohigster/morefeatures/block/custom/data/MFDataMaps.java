package com.mohigster.morefeatures.block.custom.data;

import com.mohigster.morefeatures.block.custom.data.codec.BonemealMorph;
import com.mohigster.morefeatures.block.custom.data.codec.FlammabilityEntry;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.AdvancedDataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

import java.util.ArrayList;
import java.util.List;

public class MFDataMaps {
    public static final AdvancedDataMapType<Block, BonemealMorph, ?> BONEMEAL_MORPHS =
            AdvancedDataMapType.builder(
                    MFIdentifier.withMfNamespace("bonemeal_morphs"),
                    Registries.BLOCK,
                    BonemealMorph.CODEC
            ).merger((_, _, first, _, second) -> {
                // If two data packs both add variants to the same block,
                // combine the lists instead of one overriding the other.
                List<Block> merged = new ArrayList<>(first.variants());
                for (Block b : second.variants()) if (!merged.contains(b)) merged.add(b);

                // Bonemeal type is simply overridden by the first, since
                // it is not a list and therefore cannot be merged
                return new BonemealMorph(merged, first.bonemealType());
            }).build();

    public static final AdvancedDataMapType<Block, FlammabilityEntry, ?> FLAMMABLES =
            AdvancedDataMapType.builder(
                    MFIdentifier.withMfNamespace("flammables"),
                    Registries.BLOCK,
                    FlammabilityEntry.CODEC
            ).build();

    public static void registerDataMaps(RegisterDataMapTypesEvent event) {
        event.register(BONEMEAL_MORPHS);
        event.register(FLAMMABLES);
    }
}
