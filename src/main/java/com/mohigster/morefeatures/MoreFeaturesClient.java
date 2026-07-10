package com.mohigster.morefeatures;

import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import com.mohigster.morefeatures.entity.custom.IceologerEntity;
import com.mohigster.morefeatures.entity.entity_types.MFEntityTypes;
import com.mohigster.morefeatures.entity.model.IceologerModel;
import com.mohigster.morefeatures.entity.model.BismuthTridentModel;
import com.mohigster.morefeatures.particles.MFFallingLeavesParticle;
import com.mohigster.morefeatures.particles.MFParticleTypes;
import com.mohigster.morefeatures.references.MFIdentifier;
import com.mohigster.morefeatures.renderer.trident.BismuthTridentRenderer;
import com.mohigster.morefeatures.entity.model.CarbonTridentModel;
import com.mohigster.morefeatures.renderer.trident.CarbonTridentRenderer;
import com.mohigster.morefeatures.menu.MFMenuTypes;
import com.mohigster.morefeatures.menu.custom.CompressorScreen;
import com.mohigster.morefeatures.model.MFModelLayer;
import com.mohigster.morefeatures.renderer.iceologer.IceologerRenderer;
import com.mohigster.morefeatures.renderer.special.BismuthTridentSpecialRenderer;
import com.mohigster.morefeatures.renderer.special.CarbonShieldSpecialRenderer;
import com.mohigster.morefeatures.renderer.special.CarbonTridentSpecialRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.ShelfRenderer;
import net.minecraft.client.renderer.blockentity.StandingSignRenderer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = MoreFeatures.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = MoreFeatures.MODID, value = Dist.CLIENT)
public class MoreFeaturesClient {
    public MoreFeaturesClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        MoreFeatures.LOGGER.info("HELLO FROM CLIENT SETUP");
        MoreFeatures.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(
                MFParticleTypes.BLOODWOOD_LEAVES.get(),
                MFFallingLeavesParticle.EvilLeafProvider::new
        );
        event.registerSpriteSet(
                MFParticleTypes.TAINTED_LEAVES.get(),
                MFFallingLeavesParticle.EvilLeafProvider::new
        );
        event.registerSpriteSet(
                MFParticleTypes.PALM_LEAVES.get(),
                MFFallingLeavesParticle.PalmProvider::new
        );
        event.registerSpriteSet(
                MFParticleTypes.DECREPIT_LEAVES.get(),
                MFFallingLeavesParticle.EndLeafProvider::new
        );
        event.registerSpriteSet(
                MFParticleTypes.PALLID_LEAVES.get(),
                MFFallingLeavesParticle.EndLeafProvider::new
        );
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MFModelLayer.CARBON_TRIDENT, CarbonTridentModel::createLayer);
        event.registerLayerDefinition(MFModelLayer.BISMUTH_TRIDENT, BismuthTridentModel::createLayer);
        event.registerLayerDefinition(MFModelLayer.PALM_BOAT, BoatModel::createBoatModel);
        event.registerLayerDefinition(MFModelLayer.PALM_CHEST_BOAT, BoatModel::createChestBoatModel);
        event.registerLayerDefinition(MFModelLayer.ICEOLOGER, IceologerModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(MFEntityTypes.CARBON_TRIDENT.get(), CarbonTridentRenderer::new);
        event.registerEntityRenderer(MFEntityTypes.BISMUTH_TRIDENT.get(), BismuthTridentRenderer::new);
        event.registerEntityRenderer(MFEntityTypes.ICEOLOGER.get(), IceologerRenderer::new);
        event.registerEntityRenderer(
                MFEntityTypes.PALM_BOAT.get(),
                context -> new BoatRenderer(
                        context,
                        MFModelLayer.PALM_BOAT
                )
        );
        event.registerEntityRenderer(
                MFEntityTypes.PALM_CHEST_BOAT.get(),
                context -> new BoatRenderer(
                        context,
                        MFModelLayer.PALM_CHEST_BOAT
                )
        );
        event.registerBlockEntityRenderer(
                MFBlockEntities.MOD_SIGN_BE.get(),
                StandingSignRenderer::new
        );
        event.registerBlockEntityRenderer(
                MFBlockEntities.MOD_HANGING_SIGN_BE.get(),
                HangingSignRenderer::new
        );
        event.registerBlockEntityRenderer(
                MFBlockEntities.MOD_SHELF_BE.get(),
                ShelfRenderer::new
        );
    }

    @SubscribeEvent
    public static Identifier onTextureStitch(TextureAtlasStitchedEvent event) {
        if (event.getAtlas().location().equals(AtlasIds.GUI)) {
            return MFIdentifier.withMfNamespace("entity/sign/palm");
        }
        return null;
    }

    @SubscribeEvent
    public static void onRegisterSpecialRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(
                MFIdentifier.withMfNamespace("carbon_trident"),
                CarbonTridentSpecialRenderer.Unbaked.MAP_CODEC
        );
        event.register(
                MFIdentifier.withMfNamespace("bismuth_trident"),
                BismuthTridentSpecialRenderer.Unbaked.MAP_CODEC
        );
        event.register(
                MFIdentifier.withMfNamespace("carbon_shield"),
                CarbonShieldSpecialRenderer.Unbaked.MAP_CODEC
        );
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(MFMenuTypes.COMPRESSOR_MENU.get(), CompressorScreen::new);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(MFEntityTypes.ICEOLOGER.get(), IceologerEntity.createAttributes().build());
    }
}
