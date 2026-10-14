package com.mohigster.morefeatures.core.deferred;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.core.MFRegistryKeys;
import com.mohigster.morefeatures.portal.key.type.PortalKeyType;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public sealed abstract class MFDeferredRegister<R> implements Supplier<DeferredRegister<R>> permits MFDeferredRegister.Keys {
    private final DeferredRegister<R> internalRegister;

    private MFDeferredRegister(final Supplier<ResourceKey<Registry<R>>> key, final String namespace) {
        this.internalRegister = DeferredRegister.create(key.get(), namespace);
    }

    @Override
    public DeferredRegister<R> get() {
        return this.internalRegister;
    }

    public static Keys createKeys(final String namespace) {
        return new Keys(namespace);
    }

    public static final class Keys extends MFDeferredRegister<PortalKeyType<?>> {
        private Keys(final String namespace) {
            super(MFRegistryKeys.PORTAL_KEY_TYPE, namespace);
        }

        public <T extends PortalKeyType<?>> DeferredKeyType<T> register(final String name, final Supplier<? extends T> sup) {
            return this.register(name, _ -> sup.get());
        }

        public <T extends PortalKeyType<?>> DeferredKeyType<T> register(final String name, final Function<Identifier, ? extends T> func) {
            DeferredKeyType<T> key = DeferredKeyType.createType(this.get().register(name, func).getKey());
            MoreFeatures.LOGGER.info("Creating DeferredKeyType with ResourceKey: {}", key.getKey());
            return key;
        }

        public <T extends PortalKeyType<?>> DeferredKeyType<T> registerKey(final String name, final T type) {
            return this.register(name, () -> type);
        }

        public void register(final IEventBus bus) {
            this.get().register(bus);
        }
    }
}
