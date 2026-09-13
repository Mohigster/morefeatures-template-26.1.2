package com.mohigster.morefeatures.core;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.custom.portal.key.PortalKeyType;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;

@SuppressWarnings("SameParameterValue")
@EventBusSubscriber(modid = MoreFeatures.MODID)
public class MFRegistryKeys {
    public static final ResourceKey<Registry<PortalKeyType<?>>> PORTAL_KEY_TYPES = create("portal_key_types");

    private static <T> ResourceKey<Registry<T>> create(String registryName) {
        return ResourceKey.createRegistryKey(MFIdentifier.withMfNamespace(registryName));
    }

    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event) {
        event.register(MFRegistries.PORTAL_KEY_TYPES);
    }
}
