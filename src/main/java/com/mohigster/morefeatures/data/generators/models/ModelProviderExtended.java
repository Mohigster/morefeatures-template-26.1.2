package com.mohigster.morefeatures.data.generators.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import org.jspecify.annotations.NonNull;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class ModelProviderExtended extends ModelProvider {
    public ModelProviderExtended(PackOutput output, String modId) {
        super(output, modId);
    }

    protected abstract void registerModels(
            @NonNull MFBlockModelGenerators mfBlockModels,
            @NonNull MFItemModelGenerators mfItemModels,
            @NonNull BlockModelGenerators blockModels,
            @NonNull ItemModelGenerators itemModels
    );

    @Override
    @OverridingMethodsMustInvokeSuper
    public void registerModels(@NonNull BlockModelGenerators blockModels, @NonNull ItemModelGenerators itemModels) {
        MFBlockModelGenerators mfBlockModels = MFBlockModelGenerators.of(blockModels);
        MFItemModelGenerators mfItemModels = MFItemModelGenerators.of(itemModels);
        this.registerModels(mfBlockModels, mfItemModels, blockModels, itemModels);
    }
}
