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
import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
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
        addOresAndIngots(event);

        addWoodenVerticalSlabs(event);
        addStoneAndDeepslateVerticalSlabs(event);
        addStoneVariantVerticalSlabs(event);
        addSandstoneVerticalSlabs(event);
        addSulfurAndCinnabarVerticalSlabs(event);
        addNetherAndEndVerticalSlabs(event);
        addMiscVerticalSlabs(event);
        addWoolAndCopperVerticalSlabs(event);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @SubscribeEvent
    public void onServerAboutToStart(ServerAboutToStartEvent event){
        if (rulesAdded) return;

        // Surface rule registration moved here due to 26.2 now requiring a HolderGetter for the isBiome check
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
        // version when a new patch releases and see if that patch fixes the issue. Until then, this temporary solution will have to do

        SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.OVERWORLD, SurfaceRuleManager.RuleStage.BEFORE_BEDROCK, 10, ModSurfaceRules.fixOverworldRules(biomeGetter));

        SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.NETHER, SurfaceRuleManager.RuleStage.BEFORE_BEDROCK, 10, ModSurfaceRules.fixNetherRules(biomeGetter));
    }

    private void addWoodenVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.insertBefore(Items.OAK_SLAB.getDefaultInstance(), ModBlocks.OAK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SPRUCE_SLAB.getDefaultInstance(), ModBlocks.SPRUCE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.BIRCH_SLAB.getDefaultInstance(), ModBlocks.BIRCH_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.JUNGLE_SLAB.getDefaultInstance(), ModBlocks.JUNGLE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.ACACIA_SLAB.getDefaultInstance(), ModBlocks.ACACIA_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.DARK_OAK_SLAB.getDefaultInstance(), ModBlocks.DARK_OAK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.CRIMSON_SLAB.getDefaultInstance(), ModBlocks.CRIMSON_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.WARPED_SLAB.getDefaultInstance(), ModBlocks.WARPED_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.MANGROVE_SLAB.getDefaultInstance(), ModBlocks.MANGROVE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.CHERRY_SLAB.getDefaultInstance(), ModBlocks.CHERRY_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.BAMBOO_SLAB.getDefaultInstance(), ModBlocks.BAMBOO_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.BAMBOO_MOSAIC_SLAB.getDefaultInstance(), ModBlocks.BAMBOO_MOSAIC_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.PALE_OAK_SLAB.getDefaultInstance(), ModBlocks.PALE_OAK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addStoneAndDeepslateVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.STONE_SLAB.getDefaultInstance(), ModBlocks.STONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.COBBLESTONE_SLAB.getDefaultInstance(), ModBlocks.COBBLESTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.MOSSY_COBBLESTONE_SLAB.getDefaultInstance(), ModBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SMOOTH_STONE_SLAB.getDefaultInstance(), ModBlocks.SMOOTH_STONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.STONE_BRICK_SLAB.getDefaultInstance(), ModBlocks.STONE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.MOSSY_STONE_BRICK_SLAB.getDefaultInstance(), ModBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.COBBLED_DEEPSLATE_SLAB.getDefaultInstance(), ModBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_DEEPSLATE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.DEEPSLATE_BRICK_SLAB.getDefaultInstance(), ModBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.DEEPSLATE_TILE_SLAB.getDefaultInstance(), ModBlocks.DEEPSLATE_TILE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addStoneVariantVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.GRANITE_SLAB.getDefaultInstance(), ModBlocks.GRANITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_GRANITE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_GRANITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.DIORITE_SLAB.getDefaultInstance(), ModBlocks.DIORITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_DIORITE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_DIORITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.ANDESITE_SLAB.getDefaultInstance(), ModBlocks.ANDESITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_ANDESITE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_ANDESITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.TUFF_SLAB.getDefaultInstance(), ModBlocks.TUFF_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_TUFF_SLAB.getDefaultInstance(), ModBlocks.POLISHED_TUFF_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.TUFF_BRICK_SLAB.getDefaultInstance(), ModBlocks.TUFF_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addSandstoneVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.SANDSTONE_SLAB.getDefaultInstance(), ModBlocks.SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            /*
             * No, CUT_STANDSTONE_SLAB is not my typo, it's Mojang's.
             * That's just what they called it in the Items class.
             */
            event.insertBefore(Items.CUT_STANDSTONE_SLAB.getDefaultInstance(), ModBlocks.CUT_SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SMOOTH_SANDSTONE_SLAB.getDefaultInstance(), ModBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.RED_SANDSTONE_SLAB.getDefaultInstance(), ModBlocks.RED_SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), ModBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SMOOTH_RED_SANDSTONE_SLAB.getDefaultInstance(), ModBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addSulfurAndCinnabarVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.CINNABAR_SLAB.getDefaultInstance(), ModBlocks.CINNABAR_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_CINNABAR_SLAB.getDefaultInstance(), ModBlocks.POLISHED_CINNABAR_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.CINNABAR_BRICK_SLAB.getDefaultInstance(), ModBlocks.CINNABAR_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SULFUR_SLAB.getDefaultInstance(), ModBlocks.SULFUR_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_SULFUR_SLAB.getDefaultInstance(), ModBlocks.POLISHED_SULFUR_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SULFUR_BRICK_SLAB.getDefaultInstance(), ModBlocks.SULFUR_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addNetherAndEndVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.NETHER_BRICK_SLAB.getDefaultInstance(), ModBlocks.NETHER_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.RED_NETHER_BRICK_SLAB.getDefaultInstance(), ModBlocks.RED_NETHER_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.BLACKSTONE_SLAB.getDefaultInstance(), ModBlocks.BLACKSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_BLACKSTONE_SLAB.getDefaultInstance(), ModBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_BLACKSTONE_BRICK_SLAB.getDefaultInstance(), ModBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.END_STONE_BRICK_SLAB.getDefaultInstance(), ModBlocks.END_STONE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.PURPUR_SLAB.getDefaultInstance(), ModBlocks.PURPUR_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.QUARTZ_SLAB.getDefaultInstance(), ModBlocks.QUARTZ_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SMOOTH_QUARTZ_SLAB.getDefaultInstance(), ModBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addMiscVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.PRISMARINE_SLAB.getDefaultInstance(), ModBlocks.PRISMARINE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.PRISMARINE_BRICK_SLAB.getDefaultInstance(), ModBlocks.PRISMARINE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.DARK_PRISMARINE_SLAB.getDefaultInstance(), ModBlocks.DARK_PRISMARINE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.BRICK_SLAB.getDefaultInstance(), ModBlocks.BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.MUD_BRICK_SLAB.getDefaultInstance(), ModBlocks.MUD_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.RESIN_BRICK_SLAB.getDefaultInstance(), ModBlocks.RESIN_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addWoolAndCopperVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS){
            ColorCollection.VALUES.forEach(colour -> event.insertBefore(Items.CARPET.white().getDefaultInstance(), ModBlocks.WOOL_VERTICAL_SLAB.pick(colour).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            WeatheringCopper.WeatherState.forEach(state -> event.insertBefore(Items.CUT_COPPER_SLAB.weathering().unaffected().getDefaultInstance(), ModBlocks.CUT_COPPER_VERTICAL_SLAB.weathering().pick(state).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
            WeatheringCopper.WeatherState.forEach(state -> event.insertBefore(Items.CUT_COPPER_SLAB.waxed().unaffected().getDefaultInstance(), ModBlocks.CUT_COPPER_VERTICAL_SLAB.waxed().pick(state).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
        }
    }

    private void addOresAndIngots(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.insertAfter(Items.RAW_GOLD.getDefaultInstance(), ModItems.RAW_ALUMINIUM.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.GOLD_INGOT.getDefaultInstance(), ModItems.ALUMINIUM_INGOT.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.RAW_ALUMINIUM.toStack(), ModItems.RAW_MAGNESIUM.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.ALUMINIUM_INGOT.toStack(), ModItems.MAGNESIUM_INGOT.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.RAW_MAGNESIUM.toStack(), ModItems.RAW_BISMUTH.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.NETHERITE_INGOT.getDefaultInstance(), ModItems.BISMUTH_SCRAP.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.BISMUTH_SCRAP.toStack(), ModItems.BISMUTH.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.RAW_BISMUTH.toStack(), ModItems.RAW_AZURITE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(ModItems.BISMUTH.toStack(), ModItems.AZURITE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
