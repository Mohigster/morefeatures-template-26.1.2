package com.mohigster.morefeatures.core;

import com.google.common.collect.Lists;
import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.portal.frame.PortalFrameType;
import com.mohigster.morefeatures.portal.key.type.PortalKeyType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.List;

@SuppressWarnings("SameParameterValue")
@EventBusSubscriber(modid = MoreFeatures.MODID)
public final class MFRegistries {
    private static final List<Registry<?>> REGISTRIES = Lists.newArrayList();

    public static final Registry<PortalKeyType<?>> PORTAL_KEY_TYPE = build(MFRegistryKeys.PORTAL_KEY_TYPE);
    public static final Registry<PortalFrameType<?>> PORTAL_FRAME = build(MFRegistryKeys.PORTAL_FRAME);

    /**
     * This method creates a new {@link Registry} and automatically adds it to a list to be registered.
     * <p>
     * The created {@code Registry} will always be synced to the client
     *
     * @param key the {@link ResourceKey} for the {@code Registry}
     * @return a new {@code Registry}, synced to the client
     * @param <T> The class the {@code Registry} is for
     */
    public static <T> Registry<T> build(ResourceKey<Registry<T>> key) {
        return build(key, true);
    }
    /**
     * This method creates a new {@link Registry} and automatically adds it to a list to be registered.
     * <p>
     * May be synced to the client if the parameters demand it
     *
     * @param key the {@link ResourceKey} for the {@code Registry}
     * @param sync whether to sync the {@code Registry} to the client
     * @return a new {@code Registry}
     * @param <T> The class the {@code Registry} is for
     */
    @SuppressWarnings("deprecation")
    public static <T> Registry<T> build(ResourceKey<Registry<T>> key, boolean sync) {
        final Registry<T> registry = new RegistryBuilder<>(key)
                .sync(sync)
                .withIntrusiveHolders()
                .create();
        synchronized (REGISTRIES) {
            REGISTRIES.add(registry);
        }
        return registry;
    }

    @SubscribeEvent
    public static void addNewRegistries(NewRegistryEvent event) {
        synchronized (REGISTRIES) {
            for (Registry<?> registry : REGISTRIES) {
                event.register(registry);
                MoreFeatures.LOGGER.info("Registered custom registry: {}", registry.key().identifier());
            }
        }
    }
}