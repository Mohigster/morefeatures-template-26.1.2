package com.mohigster.morefeatures.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MFLootTableProvider {
    public static LootTableProvider createLootTables(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider){
        return new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(
                        new LootTableProvider.SubProviderEntry(MFBlockLootTableProvider::new, LootContextParamSets.BLOCK),
                        new LootTableProvider.SubProviderEntry(MFBlockInteractLootTableProvider::new, LootContextParamSets.BLOCK_INTERACT),
                        new LootTableProvider.SubProviderEntry(MFEntityLootTableProvider::new, LootContextParamSets.ENTITY)
                ),
                lookupProvider
        );
    }
}
