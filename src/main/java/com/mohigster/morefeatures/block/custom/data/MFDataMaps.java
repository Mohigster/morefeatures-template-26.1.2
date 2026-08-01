package com.mohigster.morefeatures.block.custom.data;

import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.AdvancedDataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

import java.util.ArrayList;
import java.util.List;

public class MFDataMaps {

    public static final AdvancedDataMapType<Block, BonemealMorphData, ?> BONEMEAL_MORPHS =
            AdvancedDataMapType.builder(
                    MFIdentifier.withMfNamespace("bonemeal_morphs"),
                    Registries.BLOCK,
                    BonemealMorphData.CODEC
            ).merger((_, _, first, _, second) -> {
                // If two data packs both add variants to the same block,
                // combine the lists instead of one clobbering the other.
                List<Block> merged = new ArrayList<>(first.variants());
                for (Block b : second.variants()) if (!merged.contains(b)) merged.add(b);
                return new BonemealMorphData(merged);
            }).build();

    public static void registerDataMaps(RegisterDataMapTypesEvent event) {
        event.register(BONEMEAL_MORPHS);
    }
}
