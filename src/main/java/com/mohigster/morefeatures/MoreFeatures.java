package com.mohigster.morefeatures;

import com.mohigster.morefeatures.attachment.MFAttachments;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.block.custom.blocktype.MFWoodType;
import com.mohigster.morefeatures.block.custom.data.MFDataMaps;
import com.mohigster.morefeatures.block.custom.magicblock.MagicBlockTransmutations;
import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import com.mohigster.morefeatures.creativetab.MFCreativeModeTabs;
import com.mohigster.morefeatures.data.component.MFDataComponentTypes;
import com.mohigster.morefeatures.data.world.biome.SurfaceRuleFix;
import com.mohigster.morefeatures.enchantment.MFEnchantmentEffects;
import com.mohigster.morefeatures.entity.MFEntityTypes;
import com.mohigster.morefeatures.events.data.BowDamageBonuses;
import com.mohigster.morefeatures.events.data.ElytraSpeedBoosts;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.item.custom.metaldetector.MetalDetectorCosts;
import com.mohigster.morefeatures.menu.MFMenuTypes;
import com.mohigster.morefeatures.particles.MFParticleTypes;
import com.mohigster.morefeatures.recipe.MFRecipes;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import com.mohigster.morefeatures.data.sound.MFSoundEvents;
import com.mohigster.morefeatures.data.world.biome.MFBiomes;
import com.mohigster.morefeatures.data.world.biome.MFSurfaceRules;
import com.mohigster.morefeatures.data.world.feature.custom.MFFeatures;
import com.mohigster.morefeatures.data.world.tree.decorator.MFTreeDecorators;
import com.mohigster.morefeatures.data.world.tree.placer.foliage.MFFoliagePlacerType;
import com.mohigster.morefeatures.data.world.tree.placer.trunk.MFTrunkPlacerType;
import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
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
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
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
        // Register the commonSetup method for mod loading

        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.addListener(MagicBlockTransmutations.INSTANCE::ignoreResultsNotInTag);
        modEventBus.addListener(MFDataMaps::registerDataMaps);

        MFCreativeModeTabs.register(modEventBus); // All register methods are declared in the class

        MFWoodType.init();

        MFItems.register(modEventBus);
        MFBlocks.register(modEventBus);

        MFAttachments.register(modEventBus);

        MFEntityTypes.register(modEventBus);

        MFSoundEvents.register(modEventBus);

        MFEnchantmentEffects.register(modEventBus);

        MFMenuTypes.register(modEventBus);
        MFBlockEntities.register(modEventBus);

        MFTrunkPlacerType.register(modEventBus);
        MFFoliagePlacerType.register(modEventBus);
        MFFeatures.register(modEventBus);

        MFParticleTypes.register(modEventBus);

        MFRecipes.register(modEventBus);

        MFTreeDecorators.register(modEventBus);

        MFDataComponentTypes.register(modEventBus);

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

            MFBiomes.registerBiomes();

            this.registerDispenserBehaviour();
        });
    }

    @SubscribeEvent
    public void onReloadListener(AddServerReloadListenersEvent event){
        event.addListener(
                MFIdentifier.withMfNamespace("magic_block_transmutations"),
                MagicBlockTransmutations.INSTANCE
        );
        event.addListener(
                MFIdentifier.withMfNamespace("metal_detector_costs"),
                MetalDetectorCosts.INSTANCE
        );
        event.addListener(
                MFIdentifier.withMfNamespace("elytra_speed_boosts"),
                ElytraSpeedBoosts.INSTANCE
        );
        event.addListener(
                MFIdentifier.withMfNamespace("bow_damage_bonuses"),
                BowDamageBonuses.INSTANCE
        );
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
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(MFBlocks.ROSE.getId(), MFBlocks.POTTED_ROSE);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(MFBlocks.BLUE_ROSE.getId(), MFBlocks.POTTED_BLUE_ROSE);

        WoodTypeCollection.SETS.forEach(set ->
                ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(
                        // Saplings are registered separately since they are not all registered by the same class. Instead,
                        // the getSaplingOrFungus() method will return the sapling associated with the relevant WoodSetType
                        set.getSaplingOrFungus().getId(),
                        // Potted saplings ARE by contrast registered as a WoodTypeCollection because
                        // they are all registered by the same class. That class being FlowerPotBlock
                        MFBlocks.POTTED_SAPLING.pick(set)
                )
        );

        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(MFBlocks.CHARRED_ROOTS.getId(), MFBlocks.POTTED_CHARRED_ROOTS);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(MFBlocks.DECREPIT_ROOTS.getId(), MFBlocks.POTTED_DECREPIT_ROOTS);
        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(MFBlocks.PALLID_ROOTS.getId(), MFBlocks.POTTED_PALLID_ROOTS);
    }

    private void registerDispenserBehaviour(){
        DispenserBlock.registerBehavior(
                MFItems.BLOODWOOD_BOAT.get(),
                new BoatDispenseItemBehavior(MFEntityTypes.BLOODWOOD_BOAT.get())
        );
        DispenserBlock.registerBehavior(
                MFItems.BLOODWOOD_CHEST_BOAT.get(),
                new BoatDispenseItemBehavior(MFEntityTypes.BLOODWOOD_CHEST_BOAT.get())
        );

        DispenserBlock.registerBehavior(
                MFItems.TAINTED_BOAT.get(),
                new BoatDispenseItemBehavior(MFEntityTypes.TAINTED_BOAT.get())
        );
        DispenserBlock.registerBehavior(
                MFItems.TAINTED_CHEST_BOAT.get(),
                new BoatDispenseItemBehavior(MFEntityTypes.TAINTED_CHEST_BOAT.get())
        );

        DispenserBlock.registerBehavior(
                MFItems.PALM_BOAT.get(),
                new BoatDispenseItemBehavior(MFEntityTypes.PALM_BOAT.get())
        );
        DispenserBlock.registerBehavior(
                MFItems.PALM_CHEST_BOAT.get(),
                new BoatDispenseItemBehavior(MFEntityTypes.PALM_CHEST_BOAT.get())
        );
    }

    private void registerSurfaceRules(HolderGetter<Biome> biomeGetter){
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, MFSurfaceRules.makeIceCaveRules(biomeGetter));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.NETHER, MODID, MFSurfaceRules.makeCharredForestRules(biomeGetter));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.END, MODID, MFSurfaceRules.makeEndSurfaceRules(biomeGetter));

        SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.OVERWORLD, SurfaceRuleManager.RuleStage.BEFORE_BEDROCK, 15, SurfaceRuleFix.fixOverworldRules(biomeGetter));

        SurfaceRuleManager.addToDefaultSurfaceRulesAtStage(SurfaceRuleManager.RuleCategory.NETHER, SurfaceRuleManager.RuleStage.BEFORE_BEDROCK, 15, SurfaceRuleFix.fixNetherRules(biomeGetter));
    }

    // I have broken down the creative tab event into many separate methods for readability

    // Add the items to a creative mode tab.
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        this.addArmorAndTools(event);
        this.addOresAndIngots(event);
        this.addAllVerticalSlabs(event);
        this.addCustomWoodSets(event);
    }

    private void addAllVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        this.addWoodenVerticalSlabs(event);
        this.addStoneAndDeepslateVerticalSlabs(event);
        this.addStoneVariantVerticalSlabs(event);
        this.addSandstoneVerticalSlabs(event);
        this.addSulfurAndCinnabarVerticalSlabs(event);
        this.addNetherAndEndVerticalSlabs(event);
        this.addMiscVerticalSlabs(event);
        this.addWoolAndCopperVerticalSlabs(event);
    }


    private void addWoodenVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.insertBefore(Items.OAK_SLAB.getDefaultInstance(), MFBlocks.OAK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SPRUCE_SLAB.getDefaultInstance(), MFBlocks.SPRUCE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.BIRCH_SLAB.getDefaultInstance(), MFBlocks.BIRCH_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.JUNGLE_SLAB.getDefaultInstance(), MFBlocks.JUNGLE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.ACACIA_SLAB.getDefaultInstance(), MFBlocks.ACACIA_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.DARK_OAK_SLAB.getDefaultInstance(), MFBlocks.DARK_OAK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.CRIMSON_SLAB.getDefaultInstance(), MFBlocks.CRIMSON_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.WARPED_SLAB.getDefaultInstance(), MFBlocks.WARPED_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.MANGROVE_SLAB.getDefaultInstance(), MFBlocks.MANGROVE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.CHERRY_SLAB.getDefaultInstance(), MFBlocks.CHERRY_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.BAMBOO_SLAB.getDefaultInstance(), MFBlocks.BAMBOO_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.BAMBOO_MOSAIC_SLAB.getDefaultInstance(), MFBlocks.BAMBOO_MOSAIC_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.PALE_OAK_SLAB.getDefaultInstance(), MFBlocks.PALE_OAK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addStoneAndDeepslateVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.STONE_SLAB.getDefaultInstance(), MFBlocks.STONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.COBBLESTONE_SLAB.getDefaultInstance(), MFBlocks.COBBLESTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.MOSSY_COBBLESTONE_SLAB.getDefaultInstance(), MFBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SMOOTH_STONE_SLAB.getDefaultInstance(), MFBlocks.SMOOTH_STONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.STONE_BRICK_SLAB.getDefaultInstance(), MFBlocks.STONE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.MOSSY_STONE_BRICK_SLAB.getDefaultInstance(), MFBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.COBBLED_DEEPSLATE_SLAB.getDefaultInstance(), MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_DEEPSLATE_SLAB.getDefaultInstance(), MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.DEEPSLATE_BRICK_SLAB.getDefaultInstance(), MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.DEEPSLATE_TILE_SLAB.getDefaultInstance(), MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addStoneVariantVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.GRANITE_SLAB.getDefaultInstance(), MFBlocks.GRANITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_GRANITE_SLAB.getDefaultInstance(), MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.DIORITE_SLAB.getDefaultInstance(), MFBlocks.DIORITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_DIORITE_SLAB.getDefaultInstance(), MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.ANDESITE_SLAB.getDefaultInstance(), MFBlocks.ANDESITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_ANDESITE_SLAB.getDefaultInstance(), MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.TUFF_SLAB.getDefaultInstance(), MFBlocks.TUFF_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_TUFF_SLAB.getDefaultInstance(), MFBlocks.POLISHED_TUFF_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.TUFF_BRICK_SLAB.getDefaultInstance(), MFBlocks.TUFF_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addSandstoneVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.SANDSTONE_SLAB.getDefaultInstance(), MFBlocks.SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            /*
             * No, CUT_STANDSTONE_SLAB is not my typo, it's Mojang's.
             * That's just what they called it in the Items class.
             */
            event.insertBefore(Items.CUT_STANDSTONE_SLAB.getDefaultInstance(), MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SMOOTH_SANDSTONE_SLAB.getDefaultInstance(), MFBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.RED_SANDSTONE_SLAB.getDefaultInstance(), MFBlocks.RED_SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.CUT_RED_SANDSTONE_SLAB.getDefaultInstance(), MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SMOOTH_RED_SANDSTONE_SLAB.getDefaultInstance(), MFBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addSulfurAndCinnabarVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.CINNABAR_SLAB.getDefaultInstance(), MFBlocks.CINNABAR_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_CINNABAR_SLAB.getDefaultInstance(), MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.CINNABAR_BRICK_SLAB.getDefaultInstance(), MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SULFUR_SLAB.getDefaultInstance(), MFBlocks.SULFUR_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_SULFUR_SLAB.getDefaultInstance(), MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SULFUR_BRICK_SLAB.getDefaultInstance(), MFBlocks.SULFUR_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addNetherAndEndVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.NETHER_BRICK_SLAB.getDefaultInstance(), MFBlocks.NETHER_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.RED_NETHER_BRICK_SLAB.getDefaultInstance(), MFBlocks.RED_NETHER_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.BLACKSTONE_SLAB.getDefaultInstance(), MFBlocks.BLACKSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_BLACKSTONE_SLAB.getDefaultInstance(), MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.POLISHED_BLACKSTONE_BRICK_SLAB.getDefaultInstance(), MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.END_STONE_BRICK_SLAB.getDefaultInstance(), MFBlocks.END_STONE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.PURPUR_SLAB.getDefaultInstance(), MFBlocks.PURPUR_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.QUARTZ_SLAB.getDefaultInstance(), MFBlocks.QUARTZ_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.SMOOTH_QUARTZ_SLAB.getDefaultInstance(), MFBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addMiscVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.insertBefore(Items.PRISMARINE_SLAB.getDefaultInstance(), MFBlocks.PRISMARINE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.PRISMARINE_BRICK_SLAB.getDefaultInstance(), MFBlocks.PRISMARINE_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.DARK_PRISMARINE_SLAB.getDefaultInstance(), MFBlocks.DARK_PRISMARINE_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.BRICK_SLAB.getDefaultInstance(), MFBlocks.BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.MUD_BRICK_SLAB.getDefaultInstance(), MFBlocks.MUD_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertBefore(Items.RESIN_BRICK_SLAB.getDefaultInstance(), MFBlocks.RESIN_BRICK_VERTICAL_SLAB.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addWoolAndCopperVerticalSlabs(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS){
            ColorCollection.VALUES.forEach(colour -> event.insertBefore(Items.CARPET.white().getDefaultInstance(), MFBlocks.WOOL_VERTICAL_SLAB.pick(colour).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            WeatheringCopper.WeatherState.forEach(state -> event.insertBefore(Items.CUT_COPPER_SLAB.weathering().unaffected().getDefaultInstance(), MFBlocks.CUT_COPPER_VERTICAL_SLAB.weathering().pick(state).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
            WeatheringCopper.WeatherState.forEach(state -> event.insertBefore(Items.CUT_COPPER_SLAB.waxed().unaffected().getDefaultInstance(), MFBlocks.CUT_COPPER_VERTICAL_SLAB.waxed().pick(state).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS));
        }
    }

    private void addArmorAndTools(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.COMBAT){
            ItemStack bismuthHelmet = MFItems.BISMUTH_HELMET.toStack();
            ItemStack bismuthChest = MFItems.BISMUTH_CHESTPLATE.toStack();
            ItemStack bismuthLegs = MFItems.BISMUTH_LEGGINGS.toStack();
            ItemStack bismuthBoots = MFItems.BISMUTH_BOOTS.toStack();

            event.insertAfter(Items.NETHERITE_BOOTS.getDefaultInstance(), bismuthHelmet, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(bismuthHelmet, bismuthChest, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(bismuthChest, bismuthLegs, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(bismuthLegs, bismuthBoots, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(Items.NETHERITE_SWORD.getDefaultInstance(), MFItems.BISMUTH_SWORD.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.NETHERITE_SPEAR.getDefaultInstance(), MFItems.BISMUTH_SPEAR.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.NETHERITE_AXE.getDefaultInstance(), MFItems.BISMUTH_AXE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.insertAfter(Items.NETHERITE_PICKAXE.getDefaultInstance(), MFItems.BISMUTH_PICKAXE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addOresAndIngots(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.insertAfter(Items.RAW_GOLD.getDefaultInstance(), MFItems.RAW_ALUMINIUM.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.GOLD_INGOT.getDefaultInstance(), MFItems.ALUMINIUM_INGOT.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(MFItems.RAW_ALUMINIUM.toStack(), MFItems.RAW_MAGNESIUM.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(MFItems.ALUMINIUM_INGOT.toStack(), MFItems.MAGNESIUM_INGOT.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(MFItems.RAW_MAGNESIUM.toStack(), MFItems.RAW_BISMUTH.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(Items.NETHERITE_INGOT.getDefaultInstance(), MFItems.BISMUTH_SCRAP.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(MFItems.BISMUTH_SCRAP.toStack(), MFItems.BISMUTH.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(MFItems.RAW_BISMUTH.toStack(), MFItems.RAW_AZURITE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(MFItems.BISMUTH.toStack(), MFItems.AZURITE.toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    private void addCustomWoodSets(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            WoodTypeCollection.SETS.forEach(
                    set -> {
                        event.insertBefore(Items.STONE.getDefaultInstance(), MFBlocks.LOG.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.LOG.pick(set).toStack(), MFBlocks.WOOD.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.WOOD.pick(set).toStack(), MFBlocks.STRIPPED_LOG.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.STRIPPED_LOG.pick(set).toStack(), MFBlocks.STRIPPED_WOOD.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.STRIPPED_WOOD.pick(set).toStack(), MFBlocks.PLANKS.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.PLANKS.pick(set).toStack(), MFBlocks.WOODEN_STAIRS.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.WOODEN_STAIRS.pick(set).toStack(), MFBlocks.WOODEN_VERTICAL_SLAB.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.WOODEN_VERTICAL_SLAB.pick(set).toStack(), MFBlocks.WOODEN_SLAB.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.WOODEN_SLAB.pick(set).toStack(), MFBlocks.WOODEN_FENCE.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.WOODEN_FENCE.pick(set).toStack(), MFBlocks.WOODEN_FENCE_GATE.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.WOODEN_FENCE_GATE.pick(set).toStack(), MFBlocks.WOODEN_PRESSURE_PLATE.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFBlocks.WOODEN_PRESSURE_PLATE.pick(set).toStack(), MFBlocks.WOODEN_BUTTON.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    }
            );
        }
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            WoodTypeCollection.SETS.forEach(
                    set -> {
                        event.insertBefore(Items.CHEST.getDefaultInstance(), MFItems.WOODEN_SIGN.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertAfter(MFItems.WOODEN_SIGN.pick(set).toStack(), MFItems.WOODEN_HANGING_SIGN.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                        event.insertBefore(Items.LECTERN.getDefaultInstance(), MFBlocks.WOODEN_SHELF.pick(set).toStack(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
                    }
            );
        }
    }
}
