package com.mohigster.morefeatures.datagen.custom;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.tag.MFBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class MFMetalDetectorCostProvider extends MetalDetectorCostProvider{
    public MFMetalDetectorCostProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, MoreFeatures.MODID);
    }

    @Override
    protected void generate() {
        add(MFBlockTags.METAL_DETECTOR_LOW_COST, 2);
        add(MFBlockTags.METAL_DETECTOR_MEDIUM_COST, 3);
        add(MFBlockTags.METAL_DETECTOR_HIGH_COST, 4);
        add(MFBlockTags.METAL_DETECTOR_BISMUTH_COST, 5);
    }
}
