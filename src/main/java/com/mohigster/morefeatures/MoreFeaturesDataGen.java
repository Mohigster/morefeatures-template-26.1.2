package com.mohigster.morefeatures;

import com.mohigster.morefeatures.datagen.*;
import com.mohigster.morefeatures.datagen.custom.MFMagicBlockTransmutationProvider;
import com.mohigster.morefeatures.datagen.custom.MFMetalDetectorCostProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;

@EventBusSubscriber(modid = MoreFeatures.MODID)
public class MoreFeaturesDataGen {

    // Running datagen in neoforge is an event. Therefore we must annotate the method as a SubscribeEvent method

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();

        //————————————————————————————Adding providers————————————————————————————

        generator.addProvider(true, new MFModelProvider(packOutput));
        generator.addProvider(true, new MFBlockTagsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFItemTagsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(
                        new LootTableProvider.SubProviderEntry(MFBlockLootTableProvider::new, LootContextParamSets.BLOCK),
                        new LootTableProvider.SubProviderEntry(MFEntityLootTableProvider::new, LootContextParamSets.ENTITY)), lookupProvider));
        generator.addProvider(true, new MFRecipeProvider.Runner(packOutput, lookupProvider));
        generator.addProvider(true, new MFDatapackProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFSoundsProvider(packOutput));
        generator.addProvider(true, new MFAdvancementProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFEnchantmentTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFBiomeTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFEntityTagsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFEquipmentAssetProvider(packOutput));
        generator.addProvider(true, new MFMagicBlockTransmutationProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFMetalDetectorCostProvider(packOutput, lookupProvider));
    }
}
