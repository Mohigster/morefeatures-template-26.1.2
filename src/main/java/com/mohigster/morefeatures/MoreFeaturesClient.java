package com.mohigster.morefeatures;

import com.mohigster.morefeatures.entity.entity_types.ModEntityTypes;
import com.mohigster.morefeatures.entity.projectile.CarbonTridentRenderer;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

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
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Bind your entity type to its renderer!
        // Swap out "ThrownTridentRenderer::new" if you have a custom CarbonTridentRenderer class
        event.registerEntityRenderer(
                ModEntityTypes.CARBON_TRIDENT.get(),
                CarbonTridentRenderer::new
        );
    }
}
