package com.mohigster.morefeatures.data.generators.custom;

import com.mohigster.morefeatures.data.generators.custom.providers.MetalDetectorCostProvider;
import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class MFMetalDetectorCostProvider extends MetalDetectorCostProvider {
    public MFMetalDetectorCostProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void generate() {
        this.add(MFBlockTags.METAL_DETECTOR_LOW_COST, 2);
        this.add(MFBlockTags.METAL_DETECTOR_MEDIUM_COST, 3);
        this.add(MFBlockTags.METAL_DETECTOR_HIGH_COST, 4);
        this.add(MFBlockTags.METAL_DETECTOR_BISMUTH_COST, 5);
    }
}
