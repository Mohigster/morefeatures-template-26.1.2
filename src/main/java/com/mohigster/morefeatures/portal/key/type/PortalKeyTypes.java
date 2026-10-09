package com.mohigster.morefeatures.portal.key.type;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.core.RestrictedTo;
import com.mohigster.morefeatures.core.deferred.DeferredKeyType;
import com.mohigster.morefeatures.core.deferred.MFDeferredRegister;
import com.mohigster.morefeatures.data.resources.references.MFPortalKeyTypeIds;
import com.mohigster.morefeatures.portal.key.BlockKey;
import com.mohigster.morefeatures.portal.key.ItemKey;
import com.mohigster.morefeatures.portal.key.PortalKey;
import com.mojang.serialization.MapCodec;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;

public final class PortalKeyTypes {
    private static final MFDeferredRegister.Keys PORTAL_KEYS =
            MFDeferredRegister.createKeys(MoreFeatures.MODID);

    public static final DeferredKeyType<PortalKeyType<BlockKey>> BLOCK =
            registerKey(MFPortalKeyTypeIds.BLOCK, BlockKey.CODEC);

    public static final DeferredKeyType<PortalKeyType<ItemKey>> ITEM =
            registerKey(MFPortalKeyTypeIds.ITEM, ItemKey.CODEC);

    private static <P extends PortalKey> DeferredKeyType<PortalKeyType<P>> registerKey(
            ResourceKey<PortalKeyType<?>> id,
            MapCodec<P> codec
    ) {
        String name = id.identifier().getPath();
        return PORTAL_KEYS.registerKey(name, PortalKeyType.of(name, codec));
    }

    @RestrictedTo(MoreFeatures.class)
    public static void register(IEventBus eventBus) {
        PORTAL_KEYS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Portal Key Types Registered -> Performed by: {}", MoreFeatures.MODID);
    }
}
