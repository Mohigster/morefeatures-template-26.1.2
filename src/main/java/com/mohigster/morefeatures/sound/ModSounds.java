package com.mohigster.morefeatures.sound;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MoreFeatures.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> AQUAMARINE = registerMultiSoundEvent("aquamarine");

    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_BIOME_ICE_CAVES = registerMultiSoundEvent("music_biome_ice_caves");

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

    public static final DeferredSoundType MAGIC_BLOCK_SOUNDS = new DeferredSoundType(1F, 1F,
            MAGIC_BLOCK_BREAK, MAGIC_BLOCK_STEP, MAGIC_BLOCK_PLACE, MAGIC_BLOCK_HIT, MAGIC_BLOCK_FALL);

    public static final DeferredSoundType EVIL_PORTAL_SOUNDS = new DeferredSoundType(0.9F, 0.6F,
            EVIL_PORTAL_BREAK, EVIL_PORTAL_STEP, EVIL_PORTAL_PLACE, EVIL_PORTAL_HIT, EVIL_PORTAL_FALL);

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerMultiSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
