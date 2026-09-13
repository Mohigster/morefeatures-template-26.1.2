package com.mohigster.morefeatures.core;

import com.mohigster.morefeatures.block.custom.portal.key.PortalKeyType;
import com.mohigster.morefeatures.block.custom.portal.key.PortalKeyTypes;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.RegistryBuilder;

import javax.sound.sampled.Port;

public class MFRegistries {
    public static final Registry<PortalKeyType<?>> PORTAL_KEY_TYPES =
            new RegistryBuilder<>(MFRegistryKeys.PORTAL_KEY_TYPES)
                    .sync(true)
                    .create();
}
