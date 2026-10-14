package com.mohigster.morefeatures.portal.key.type;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.core.MFRegistries;
import com.mohigster.morefeatures.core.MFRegistryKeys;
import com.mohigster.morefeatures.portal.key.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.TypedInstance;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class PortalKeyType<P extends PortalKey> implements TypedInstance<PortalKeyType<?>> {
    private final MapCodec<P> codec;
    private final String name;

    private final Holder.Reference<PortalKeyType<?>> builtInRegistryHolder = MFRegistries.PORTAL_KEY_TYPE.createIntrusiveHolder(this);

    private @Nullable ResourceKey<PortalKeyType<?>> key;
    private @Nullable Identifier id;

    private static final Identifier EMPTY = Identifier.parse("empty");

    /** Use {@code of()} instead! This is protected ONLY so that subclasses can extend this class */
    protected PortalKeyType(MapCodec<P> codec, String name) {
        this.codec = Objects.requireNonNull(codec, "Codec cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
    }

    public MapCodec<P> codec() {
        return this.codec;
    }

    public String name() {
        return this.name;
    }

    /**
     * The method to create a PortalKeyType
     *
     * @param id the String used to differentiate key types.
     * @param codec The MapCodec used to reference the key in JSON
     * @return a new PortalKeyType
     * @param <P> A class or record that implements PortalKey. For more information, see {@link BlockKey} or {@link ItemKey}
     */
    public static <P extends PortalKey> PortalKeyType<P> of(
            String id,
            MapCodec<P> codec
    ) {
        PortalKeyType<P> key = new PortalKeyType<>(
                codec,
                id
        );

        if (key.unbound()) {
            key.logEmpty();
        }

        return key;
    }

    @Override
    public String toString() {
        return "PortalKeyType[" + this.name().toUpperCase() + "]";
    }

    @Override
    public int hashCode() {
        return this.codec().hashCode()
                + this.name().length();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PortalKeyType<?> type)) return false;
        return type.codec().equals(this.codec()) && type.name().equals(this.name());
    }

    public Identifier getId() {
        bindKeys();
        return Objects.requireNonNull(this.id);
    }

    public Holder.Reference<PortalKeyType<?>> builtInRegistryHolder() {
        return this.builtInRegistryHolder;
    }

    private void bindKeys() {
        if (!this.unbound()) return;

        this.key = MFRegistries.PORTAL_KEY_TYPE.getResourceKey(this).orElseGet(() ->
                ResourceKey.create(MFRegistryKeys.PORTAL_KEY_TYPE.get(), EMPTY));

        this.id = this.key.identifier();
    }

    // Better for when it is certain that the key is registered
    public ResourceKey<PortalKeyType<?>> getKey() {
        bindKeys();
        return Objects.requireNonNull(this.key, "key cannot be null");
    }

    private boolean unbound() {
        return this.id == null || this.id.equals(EMPTY);
    }

    private void logEmpty() {
        MoreFeatures.LOGGER.warn("{} has an empty id", this);
    }

    @Override
    public Holder.Reference<PortalKeyType<?>> typeHolder() {
        return this.builtInRegistryHolder();
    }
}
