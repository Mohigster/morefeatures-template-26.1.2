package com.mohigster.morefeatures;

import com.mohigster.morefeatures.data.generators.*;
import com.mohigster.morefeatures.data.generators.custom.MFBowDamageBonusProvider;
import com.mohigster.morefeatures.data.generators.custom.MFElytraSpeedBoostProvider;
import com.mohigster.morefeatures.data.generators.custom.MFMagicBlockTransmutationProvider;
import com.mohigster.morefeatures.data.generators.custom.MFMetalDetectorCostProvider;
import com.mohigster.morefeatures.data.generators.loot.MFLootTableProvider;
import com.mohigster.morefeatures.data.generators.models.MFModelProvider;
import com.mohigster.morefeatures.data.generators.MFSoundsProvider;
import com.mohigster.morefeatures.data.generators.tag.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = MoreFeatures.MODID)
public class MoreFeaturesDataGen {

    // Running datagen in neoforge is an event. Therefore we must annotate the method as a SubscribeEvent method

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        //————————————————————————————Adding providers————————————————————————————

        // Main vanilla providers
        generator.addProvider(true, new MFModelProvider(packOutput));
        generator.addProvider(true, new MFRecipeProvider.Runner(packOutput, lookupProvider));
        generator.addProvider(true, new MFDatapackProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFSoundsProvider(packOutput));
        generator.addProvider(true, new MFAdvancementProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFEquipmentAssetProvider(packOutput));

        // NeoForge providers
        generator.addProvider(true, new MFDataMapsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFParticleDescriptionProvider(packOutput)); // Oddly enough, vanilla doesn't have this.

        // Tag providers
        generator.addProvider(true, new MFBlockTagsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFItemTagsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFEnchantmentTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFBiomeTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFEntityTagsProvider(packOutput, lookupProvider));

        // Custom providers for data-driven features added by this mod
        generator.addProvider(true, new MFMagicBlockTransmutationProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFMetalDetectorCostProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFElytraSpeedBoostProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFBowDamageBonusProvider(packOutput, lookupProvider));

        // Loot tables work a bit differently. generateLootTables() returns the LootTableProvider with all of the SubProviderEntries
        generator.addProvider(true, MFLootTableProvider.generateLootTables(packOutput, lookupProvider));
    }
}
