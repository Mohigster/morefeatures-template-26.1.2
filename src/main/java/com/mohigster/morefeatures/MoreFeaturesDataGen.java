package com.mohigster.morefeatures;

import com.mohigster.morefeatures.datagen.*;
import com.mohigster.morefeatures.datagen.custom.MFBowDamageBonusProvider;
import com.mohigster.morefeatures.datagen.custom.MFElytraSpeedBoostProvider;
import com.mohigster.morefeatures.datagen.custom.MFMagicBlockTransmutationProvider;
import com.mohigster.morefeatures.datagen.custom.MFMetalDetectorCostProvider;
import com.mohigster.morefeatures.datagen.loot.MFLootTableProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

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
        generator.addProvider(true, new MFRecipeProvider.Runner(packOutput, lookupProvider));
        generator.addProvider(true, new MFDatapackProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFSoundsProvider(packOutput));
        generator.addProvider(true, new MFAdvancementProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFEnchantmentTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFBiomeTagProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFEntityTagsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFEquipmentAssetProvider(packOutput));
        generator.addProvider(true, new MFDataMapsProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFParticleDescriptionProvider(packOutput));

        generator.addProvider(true, MFLootTableProvider.createLootTables(packOutput, lookupProvider));

        // Custom datagen providers. These generate JSON files for data driven mod features and do not exist in vanilla
        generator.addProvider(true, new MFMagicBlockTransmutationProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFMetalDetectorCostProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFElytraSpeedBoostProvider(packOutput, lookupProvider));
        generator.addProvider(true, new MFBowDamageBonusProvider(packOutput, lookupProvider));
    }
}
