package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.sound.MFSoundEvents;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

import static com.mohigster.morefeatures.references.MFIdentifier.withMfNamespace;

public class MFSoundsProvider extends SoundDefinitionsProvider {
    public MFSoundsProvider(PackOutput output) {
        super(output, MoreFeatures.MODID);
    }

    @Override
    public void registerSounds() {

        // Music disc

        add(MFSoundEvents.AQUAMARINE.get(), definition().subtitle("music.morefeatures.aquamarine")
                .with(sound(withMfNamespace("music/disc/aquamarine")).stream()));

        add(MFSoundEvents.SNOW_QUEEN.get(), definition().subtitle("music.morefeatures.snow_queen")
                .with(sound(withMfNamespace("music/disc/the_snow_queen")).stream()));

        add(MFSoundEvents.MUSIC_BIOME_ICE_CAVES.get(), definition().subtitle("music.morefeatures.ice_caves")
                .with(
                        sound(withMfNamespace("music/background/frozen_star")).stream(),
                        sound(withMfNamespace("music/background/frost_waltz")).stream()
                )
        );

        // Magic block sounds

        add(MFSoundEvents.MAGIC_BLOCK_BREAK.get(), definition().subtitle("sounds.morefeatures.magic_block_break")
                .with(sound(withMfNamespace("magic_block_break"))));
        add(MFSoundEvents.MAGIC_BLOCK_STEP.get(), definition().subtitle("sounds.morefeatures.magic_block_step")
                .with(sound(withMfNamespace("magic_block_step"))));
        add(MFSoundEvents.MAGIC_BLOCK_PLACE.get(), definition().subtitle("sounds.morefeatures.magic_block_place")
                .with(sound(withMfNamespace("magic_block_place"))));
        add(MFSoundEvents.MAGIC_BLOCK_HIT.get(), definition().subtitle("sounds.morefeatures.magic_block_hit")
                .with(sound(withMfNamespace("magic_block_hit"))));
        add(MFSoundEvents.MAGIC_BLOCK_FALL.get(), definition().subtitle("sounds.morefeatures.magic_block_fall")
                .with(sound(withMfNamespace("magic_block_fall"))));

        // These sounds events use the same OGG files as the magic block, but with different volume and pitch.
        // The volume and pitch have been defined in the MFSounds class, rather than this datagen class.

        add(MFSoundEvents.EVIL_PORTAL_BREAK.get(), definition().subtitle("sounds.morefeatures.evil_block_break")
                .with(sound(withMfNamespace("magic_block_break"))));
        add(MFSoundEvents.EVIL_PORTAL_STEP.get(), definition().subtitle("sounds.morefeatures.evil_block_step")
                .with(sound(withMfNamespace("magic_block_step"))));
        add(MFSoundEvents.EVIL_PORTAL_PLACE.get(), definition().subtitle("sounds.morefeatures.evil_block_place")
                .with(sound(withMfNamespace("magic_block_place"))));
        add(MFSoundEvents.EVIL_PORTAL_HIT.get(), definition().subtitle("sounds.morefeatures.evil_block_hit")
                .with(sound(withMfNamespace("magic_block_hit"))));
        add(MFSoundEvents.EVIL_PORTAL_FALL.get(), definition().subtitle("sounds.morefeatures.evil_block_fall")
                .with(sound(withMfNamespace("magic_block_fall"))));
    }
}
