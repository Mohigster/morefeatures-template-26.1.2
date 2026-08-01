package com.mohigster.morefeatures.datagen.custom;

import com.mohigster.morefeatures.datagen.custom.providers.ElytraSpeedBoostProvider;
import com.mohigster.morefeatures.item.MFItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class MFElytraSpeedBoostProvider extends ElytraSpeedBoostProvider {
    public MFElytraSpeedBoostProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void generate() {
        this.add(MFItems.CARBON_ELYTRA.get(), 18.5D);
        this.add(MFItems.BISMUTH_ELYTRA.get(), 26.75D);
    }
}
