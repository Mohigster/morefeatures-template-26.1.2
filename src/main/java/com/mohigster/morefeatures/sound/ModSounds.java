package com.mohigster.morefeatures.sound;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MoreFeatures.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> AQUAMARINE = registerJukeboxSong("aquamarine");


    private static DeferredHolder<SoundEvent, SoundEvent> registerJukeboxSong(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

}
