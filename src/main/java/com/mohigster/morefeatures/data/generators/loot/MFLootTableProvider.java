package com.mohigster.morefeatures.data.generators.loot;

import com.mohigster.morefeatures.data.generators.loot.sub.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MFLootTableProvider {
    /**
     * @return the LootTableProvider with all the sub provider entries for the
     * MoreFeatures mod. Moved here to keep the main DataGen class clean.
     */
    public static LootTableProvider generateLootTables(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider){
        return new LootTableProvider(
                packOutput,
                Collections.emptySet(),
                List.of(
                        new SubProviderEntry(MFBlockLootTableProvider::new, LootContextParamSets.BLOCK),
                        new SubProviderEntry(MFBlockInteractLootTableProvider::new, LootContextParamSets.BLOCK_INTERACT),
                        new SubProviderEntry(MFEntityLootTableProvider::new, LootContextParamSets.ENTITY)
                ),
                lookupProvider
        );
    }
}
