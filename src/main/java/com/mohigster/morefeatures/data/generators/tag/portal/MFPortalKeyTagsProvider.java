package com.mohigster.morefeatures.data.generators.tag.portal;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.resources.references.MFPortalKeyTypeIds;
import com.mohigster.morefeatures.data.tag.MFPortalKeyTypeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MFPortalKeyTagsProvider extends PortalKeyTypeTagsProvider{
    public MFPortalKeyTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        this.tag(MFPortalKeyTypeTags.TEST_TAG)
                .add(MFPortalKeyTypeIds.BLOCK);

        this.tag(MFPortalKeyTypeTags.STABLE_KEYS)
                .add(MFPortalKeyTypeIds.BLOCK)
                .add(MFPortalKeyTypeIds.ITEM);
    }
}
