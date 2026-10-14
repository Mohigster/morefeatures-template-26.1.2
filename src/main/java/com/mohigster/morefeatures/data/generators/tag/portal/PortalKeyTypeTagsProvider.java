package com.mohigster.morefeatures.data.generators.tag.portal;

import com.mohigster.morefeatures.core.MFRegistryKeys;
import com.mohigster.morefeatures.portal.key.type.PortalKeyType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;

import java.util.concurrent.CompletableFuture;

public abstract class PortalKeyTypeTagsProvider extends TagsProvider<PortalKeyType<?>> {
    protected PortalKeyTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId) {
        super(output, MFRegistryKeys.PORTAL_KEY_TYPE.get(), lookupProvider, modId);
    }
}
