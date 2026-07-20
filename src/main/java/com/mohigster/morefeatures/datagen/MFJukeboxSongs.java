package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.references.MFIdentifier;
import com.mohigster.morefeatures.sound.MFSounds;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Util;
import net.minecraft.world.item.JukeboxSong;

public class MFJukeboxSongs {

    public static final ResourceKey<JukeboxSong> AQUAMARINE_KEY = createKey("aquamarine");
    public static final ResourceKey<JukeboxSong> SNOW_QUEEN_KEY = createKey("snow_queen");

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, AQUAMARINE_KEY, ((Holder.Reference<SoundEvent>) MFSounds.AQUAMARINE.getDelegate()), 192, 15);
        register(context, SNOW_QUEEN_KEY, ((Holder.Reference<SoundEvent>) MFSounds.SNOW_QUEEN.getDelegate()), 220, 15);
    }

    private static ResourceKey<JukeboxSong> createKey(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, MFIdentifier.withMfNamespace(name));
    }

    @SuppressWarnings("SameParameterValue")
    private static void register(BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> registryKey,
                                 final Holder.Reference<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput) {
        context.register(registryKey, new JukeboxSong(soundEvent,
                Component.translatable(Util.makeDescriptionId("jukebox_song", registryKey.identifier())), lengthInSeconds, comparatorOutput));
    }
}
