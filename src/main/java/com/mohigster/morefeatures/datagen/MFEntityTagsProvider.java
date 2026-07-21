package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.entity.MFEntityTypes;
import com.mohigster.morefeatures.tag.MFEntityTypeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityTypeIds;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MFEntityTagsProvider extends EntityTypeTagsProvider {
    public MFEntityTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries){
        tag(EntityTypeTags.BOAT)
                .add(MFEntityTypes.BLOODWOOD_BOAT.getKey())
                .add(MFEntityTypes.BLOODWOOD_CHEST_BOAT.getKey())
                .add(MFEntityTypes.TAINTED_BOAT.getKey())
                .add(MFEntityTypes.TAINTED_CHEST_BOAT.getKey())
                .add(MFEntityTypes.PALM_BOAT.getKey())
                .add(MFEntityTypes.PALM_CHEST_BOAT.getKey());

        tag(MFEntityTypeTags.WAND_IMMUNE_MOUNTS)
                .addTag(EntityTypeTags.CAN_EQUIP_SADDLE);

        tag(MFEntityTypeTags.ICEOLOGER_FRIENDS)
                .add(EntityTypeIds.RAVAGER)
                .add(EntityTypeIds.EVOKER)
                .add(EntityTypeIds.PILLAGER)
                .add(EntityTypeIds.VINDICATOR);
    }
}
