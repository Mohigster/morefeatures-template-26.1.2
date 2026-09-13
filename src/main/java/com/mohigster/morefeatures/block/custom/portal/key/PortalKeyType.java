package com.mohigster.morefeatures.block.custom.portal.key;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.core.MFRegistries;
import com.mojang.serialization.MapCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface PortalKeyType<P extends PortalKey> {
    DeferredRegister<PortalKeyType<?>> PORTAL_KEYS =
            DeferredRegister.create(MFRegistries.PORTAL_KEY_TYPES, MoreFeatures.MODID);

    Supplier<PortalKeyType<BlockKey>> BLOCK = registerKeyType("block", BlockKey.CODEC);
    Supplier<PortalKeyType<ItemKey>> ITEM = registerKeyType("item", ItemKey.CODEC);

    MapCodec<P> codec();

    private static <P extends PortalKey> Supplier<PortalKeyType<P>> registerKeyType(String id, MapCodec<P> codec) {
        return PORTAL_KEYS.register(id, () -> create(id, codec));
    }

    static <P extends PortalKey> PortalKeyType<P> create(String id, MapCodec<P> codec) {
        return new PortalKeyType<>() {
            @Override
            public String toString() {
                return "PortalKeyType[" + id.toUpperCase() + "]";
            }

            @Override
            public int hashCode() {
                return this.codec().hashCode() + id.length();
            }

            @Override
            public MapCodec<P> codec() {
                return codec;
            }
        };
    }

    default @NonNull Identifier getId() {
        return Objects.requireNonNull(
                this.getNullableId(),
                "Attempted to get Identifier for unregistered PortalKeyType: " + this
        );
    }

    default @Nullable Identifier getNullableId() {
        return MFRegistries.PORTAL_KEY_TYPES.getKey(this);
    }

    // Better for use when in risky areas, and it is uncertain if the key has been registered yet
    default Optional<ResourceKey<PortalKeyType<?>>> getOptionalKey() {
        return MFRegistries.PORTAL_KEY_TYPES.getResourceKey(this);
    }

    // Better for when it is certain that the key is registered
    default ResourceKey<PortalKeyType<?>> getKey() {
        return this.getOptionalKey().orElseThrow(() ->
                new IllegalStateException("Unregistered PortalKeyType: " + this));
    }

    static void register(IEventBus eventBus) {
        PORTAL_KEYS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Portal Key Types Registered -> Performed by: {}", MoreFeatures.MODID);
    }
}
