package com.mohigster.morefeatures.data.generators.custom;

import com.mohigster.morefeatures.data.generators.custom.providers.BowDamageBonusProvider;
import com.mohigster.morefeatures.item.MFItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class MFBowDamageBonusProvider extends BowDamageBonusProvider {
    public MFBowDamageBonusProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void generate() {
        this.add(MFItems.CARBON_BOW.get(), 1.15D);
        this.add(MFItems.BISMUTH_BOW.get(), 1.45D);
    }
}
