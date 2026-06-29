package com.mohigster.morefeatures.attachment;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.attachment.mana.ManaAttachment;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MoreFeatures.MODID);

    public static final Supplier<AttachmentType<ManaAttachment>> MANA =
            ATTACHMENT_TYPES.register("mana", () ->
                    AttachmentType.builder(ManaAttachment::new)
                            .serialize(ManaAttachment.CODEC.fieldOf("mana")) // see step 2
                            .build()
            );

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
