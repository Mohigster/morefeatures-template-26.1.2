package com.mohigster.morefeatures.datagen.tag;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.enchantment.MFEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MFEnchantmentTagProvider extends EnchantmentTagsProvider {
    public MFEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        this.tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .addOptional(MFEnchantments.THUNDERING)
                .addOptional(MFEnchantments.THUNDERBOLT);

    }
}
