package com.mohigster.morefeatures.core;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public final class RegistryKey<R> implements Supplier<ResourceKey<Registry<R>>> {
    private final ResourceKey<Registry<R>> registry;

    private RegistryKey(ResourceKey<Registry<R>> registry) {
        this.registry = registry;
    }

    @Override
    public ResourceKey<Registry<R>> get() {
        return this.registry;
    }

    public static <R> RegistryKey<R> create(Identifier id) {
        return new RegistryKey<>(ResourceKey.createRegistryKey(id));
    }

    public Identifier identifier() {
        return this.get().identifier();
    }

    @Override
    public String toString() {
        return "RegistryKey[" + this.identifier() + "]";
    }
}
