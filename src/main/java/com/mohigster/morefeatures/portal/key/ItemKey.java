package com.mohigster.morefeatures.portal.key;

import com.mohigster.morefeatures.portal.key.type.PortalKeyType;
import com.mohigster.morefeatures.portal.key.type.PortalKeyTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record ItemKey(Item item) implements PortalKey {
    public static final MapCodec<ItemKey> CODEC = RecordCodecBuilder.mapCodec(
            inst -> inst.group(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item")
                            .forGetter(ItemKey::item)
            ).apply(
                    inst,
                    ItemKey::new
            )
    );

    @Override
    public @NotNull String toString() {
        Identifier id = BuiltInRegistries.ITEM.getKey(this.item());
        return "ItemKey[" + Objects.requireNonNull(id) + "]";
    }

    @Override
    public PortalKeyType<ItemKey> type() {
        return PortalKeyTypes.ITEM.get();
    }
}
