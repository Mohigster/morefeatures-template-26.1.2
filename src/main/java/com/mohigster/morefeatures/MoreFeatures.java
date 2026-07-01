package com.mohigster.morefeatures;

import com.mohigster.morefeatures.attachment.ModAttachments;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.block.custom.woodtype.ModWoodType;
import com.mohigster.morefeatures.block.entity.ModBlockEntities;
import com.mohigster.morefeatures.creativemodetab.ModCreativeModeTabs;
import com.mohigster.morefeatures.datacomponent.ModDataComponentTypes;
import com.mohigster.morefeatures.enchantment.ModEnchantmentEffects;
import com.mohigster.morefeatures.entity.entity_types.ModEntityTypes;
import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.menu.ModMenuTypes;
import com.mohigster.morefeatures.particles.ModParticleTypes;
import com.mohigster.morefeatures.recipe.ModRecipes;
import com.mohigster.morefeatures.sound.ModSounds;
import com.mohigster.morefeatures.worldgen.biome.ModBiomes;
import com.mohigster.morefeatures.worldgen.biome.ModSurfaceRules;
import com.mohigster.morefeatures.worldgen.feature.ModFeatures;
import com.mohigster.morefeatures.worldgen.tree.decorator.ModTreeDecorators;
import com.mohigster.morefeatures.worldgen.tree.foliage_placer.ModFoliagePlacerType;
import com.mohigster.morefeatures.worldgen.tree.trunk_placer.ModTrunkPlacerType;
import net.minecraft.core.*;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import terrablender.api.SurfaceRuleManager;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MoreFeatures.MODID)
public class MoreFeatures {

    private static boolean rulesAdded = false;
    // Define mod id in a common place for everything to reference
    public static final String MODID = "morefeatures";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public MoreFeatures(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading

        modEventBus.addListener(this::commonSetup);

        ModCreativeModeTabs.register(modEventBus); // All register methods are declared in the class

        ModWoodType.init();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModAttachments.register(modEventBus);

        ModEntityTypes.register(modEventBus);
        ModSounds.register(modEventBus);

        ModEnchantmentEffects.register(modEventBus);

        ModMenuTypes.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        ModTrunkPlacerType.register(modEventBus);
        ModFoliagePlacerType.register(modEventBus);
        ModFeatures.register(modEventBus);

        ModParticleTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        ModTreeDecorators.register(modEventBus);

        ModDataComponentTypes.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (MoreFeatures) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);


    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

            this.registerPottedPlants();

            ModBiomes.registerBiomes(); // Register biomes so the SurfaceRules has something to find.

            this.registerDispenserBehaviour();
        });
    }

    // Add the items to a creative mode tab.
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RAW_ALUMINIUM);
            event.accept(ModItems.ALUMINIUM_INGOT);
            event.accept(ModItems.RAW_MAGNESIUM);
            event.accept(ModItems.MAGNESIUM_INGOT);
            event.accept(ModItems.RAW_BISMUTH);
            event.accept(ModItems.BISMUTH_SCRAP);
            event.accept(ModItems.BISMUTH);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @SubscribeEvent
    public void onServerAboutToStart(ServerAboutToStartEvent event){
        if (rulesAdded) return;

        HolderGetter<Biome> biomeGetter = event.getServer().registryAccess().lookupOrThrow(Registries.BIOME);

        this.registerSurfaceRules(biomeGetter);

        rulesAdded = true;
    }

    // Register functions called in enqueueWork and onServerAboutToStart

    private void registerPottedPlants(){
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.ROSE.getId(), ModBlocks.POTTED_ROSE);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.BLUE_ROSE.getId(), ModBlocks.POTTED_BLUE_ROSE);

        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.TAINTED_SAPLING.getId(), ModBlocks.POTTED_TAINTED_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.BLOODWOOD_SAPLING.getId(), ModBlocks.POTTED_BLOODWOOD_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PALM_SAPLING.getId(), ModBlocks.POTTED_PALM_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.DECREPIT_SAPLING.getId(), ModBlocks.POTTED_DECREPIT_SAPLING);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PALLID_SAPLING.getId(), ModBlocks.POTTED_PALLID_SAPLING);

        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.DECREPIT_ROOTS.getId(), ModBlocks.POTTED_DECREPIT_ROOTS);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PALLID_ROOTS.getId(), ModBlocks.POTTED_PALLID_ROOTS);
    }

    private void registerDispenserBehaviour(){
        DispenserBlock.registerBehavior(
                ModItems.PALM_BOAT.get(),
                new BoatDispenseItemBehavior(ModEntityTypes.PALM_BOAT.get())
        );
        DispenserBlock.registerBehavior(
                ModItems.PALM_CHEST_BOAT.get(),
                new BoatDispenseItemBehavior(ModEntityTypes.PALM_CHEST_BOAT.get())
        );
    }

    private void registerSurfaceRules(HolderGetter<Biome> biomeGetter){
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, ModSurfaceRules.makeIceCaveRules(biomeGetter));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.END, MODID, ModSurfaceRules.makeEndSurfaceRules(biomeGetter));

        // Fixes several broken vanilla surface rules.

        // I'm not entirely sure what broke, but a bunch of surface rules just stopped working. This caused issues.

        // For example, the desert was entirely grass. The dry shrubs? Growing on grass.
        // Ice spikes also didn't generate in the Ice Spikes biome because all of the snow was grass.
        // My own custom surface rules had nothing to do with it, as confirmed when I commented this whole event
        // including the custom rules registration, and the biomes were still broken.

        // Perhaps a TerraBlender issue, rather than an issue with my mod. If so, I'll update my TerraBlender
        // version when a new patch releases and see if that patch fixes the issue.

        SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.OVERWORLD, SurfaceRuleManager.RuleStage.BEFORE_BEDROCK, 10, ModSurfaceRules.fixOverworldRules(biomeGetter));

        SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.NETHER, SurfaceRuleManager.RuleStage.BEFORE_BEDROCK, 10, ModSurfaceRules.fixNetherRules(biomeGetter));
    }
}
