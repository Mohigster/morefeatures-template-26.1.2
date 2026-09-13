package com.mohigster.morefeatures.data.generators.models;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;
import org.jspecify.annotations.NonNull;

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
    public void registerModels(@NonNull BlockModelGenerators blockModels, @NonNull ItemModelGenerators itemModels) {
        MFBlockModelGenerators mfBlockModels = MFBlockModelGenerators.create(blockModels);
        MFItemModelGenerators mfItemModels = MFItemModelGenerators.create(itemModels);
        this.registerModels(mfBlockModels, mfItemModels, blockModels, itemModels);
    }
}
