package com.mohigster.morefeatures.data.sound;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.registries.DeferredHolder;

public class MFJukeboxSongs {

    public static final ResourceKey<JukeboxSong> AQUAMARINE_KEY = createKey("aquamarine");
    public static final ResourceKey<JukeboxSong> SNOW_QUEEN_KEY = createKey("snow_queen");

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, AQUAMARINE_KEY, MFSoundEvents.AQUAMARINE, 192, 13);
        register(context, SNOW_QUEEN_KEY, MFSoundEvents.SNOW_QUEEN, 220, 15);
    }

    private static ResourceKey<JukeboxSong> createKey(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, MFIdentifier.withMfNamespace(name));
    }

    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> registryKey,
                                 final DeferredHolder<SoundEvent, SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(registryKey, new JukeboxSong(soundEvent.getDelegate(), Component.translatable(
                Util.makeDescriptionId("jukebox_song", registryKey.identifier())),
                lengthInSeconds, comparatorOutput));
    }
}
