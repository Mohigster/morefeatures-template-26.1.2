package com.mohigster.morefeatures.block.custom.portal.key;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class ItemKey extends PortalKey {
    public static final MapCodec<ItemKey> CODEC = RecordCodecBuilder.mapCodec(
            inst -> inst.group(
                    BuiltInRegistries.ITEM.byNameCodec().fieldOf("item")
                            .forGetter(ItemKey::item)
            ).apply(
                    inst,
                    ItemKey::new
            )
    );

    private final Item item;

    private ItemKey(Item item) {
        this.item = item;
    }

    private Item item() {
        return this.item;
    }

    @Override
    public String keyName() {
        return "Item";
    }

    @Override
    public PortalKeyType<?> getType() {
        return PortalKeyTypes.ITEM;
    }
}
