package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.entity.entity_types.MFEntityTypes;
import com.mohigster.morefeatures.tag.MFEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class MFEntityTagsProvider extends EntityTypeTagsProvider {
    public MFEntityTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries){
        tag(EntityTypeTags.BOAT)
                .add(MFEntityTypes.PALM_BOAT.getKey())
                .add(MFEntityTypes.PALM_CHEST_BOAT.getKey());

        tag(MFEntityTypeTags.WAND_IMMUNE_MOUNTS)
                .addTag(EntityTypeTags.CAN_EQUIP_SADDLE);
    }
}
