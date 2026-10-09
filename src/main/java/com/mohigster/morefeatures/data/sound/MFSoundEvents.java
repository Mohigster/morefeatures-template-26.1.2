package com.mohigster.morefeatures.data.sound;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.core.RestrictedTo;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MFSoundEvents {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MoreFeatures.MODID);

    // For music, we need the getDelegate method, so we use a DeferredHolder
    public static final DeferredHolder<SoundEvent, SoundEvent> AQUAMARINE = registerSoundEvent("aquamarine");
    public static final DeferredHolder<SoundEvent, SoundEvent> SNOW_QUEEN = registerSoundEvent("snow_queen");

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_BIOME_ICE_CAVES = registerSoundEvent("music_biome_ice_caves");

    // A plain old supplier is quicker to type for when we don't need that method
    public static final Supplier<SoundEvent> MAGIC_BLOCK_BREAK = registerSoundEvent("magic_block_break");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_STEP = registerSoundEvent("magic_block_step");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_PLACE = registerSoundEvent("magic_block_place");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_HIT = registerSoundEvent("magic_block_hit");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_FALL = registerSoundEvent("magic_block_fall");

    public static final Supplier<SoundEvent> EVIL_PORTAL_BREAK = registerSoundEvent("evil_block_break");
    public static final Supplier<SoundEvent> EVIL_PORTAL_STEP = registerSoundEvent("evil_block_step");
    public static final Supplier<SoundEvent> EVIL_PORTAL_PLACE = registerSoundEvent("evil_block_place");
    public static final Supplier<SoundEvent> EVIL_PORTAL_HIT = registerSoundEvent("evil_block_hit");
    public static final Supplier<SoundEvent> EVIL_PORTAL_FALL = registerSoundEvent("evil_block_fall");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        Identifier id = MFIdentifier.withMfNamespace(name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    @RestrictedTo(MoreFeatures.class)
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Sounds registered -> Performed by: " + MoreFeatures.MODID);
    }
}
