package com.mohigster.morefeatures.data.generators;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.sound.MFSoundEvents;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

import static com.mohigster.morefeatures.data.resources.MFIdentifier.withMfNamespace;

public class MFSoundsProvider extends SoundDefinitionsProvider {
    public MFSoundsProvider(PackOutput output) {
        super(output, MoreFeatures.MODID);
    }

    @Override
    public void registerSounds() {

        // Music disc

        this.add(MFSoundEvents.AQUAMARINE.get(), definition().subtitle("music.morefeatures.aquamarine")
                .with(sound(withMfNamespace("music/disc/aquamarine")).stream()));

        this.add(MFSoundEvents.SNOW_QUEEN.get(), definition().subtitle("music.morefeatures.snow_queen")
                .with(sound(withMfNamespace("music/disc/the_snow_queen")).stream()));

        this.add(MFSoundEvents.MUSIC_BIOME_ICE_CAVES.get(), definition().subtitle("music.morefeatures.ice_caves")
                .with(
                        sound(withMfNamespace("music/background/frozen_star")).stream(),
                        sound(withMfNamespace("music/background/frost_waltz")).stream()
                )
        );

        // Magic block sounds

        this.add(MFSoundEvents.MAGIC_BLOCK_BREAK.get(), definition().subtitle("sounds.morefeatures.magic_block_break")
                .with(sound(withMfNamespace("magic_block_break"))));
        this.add(MFSoundEvents.MAGIC_BLOCK_STEP.get(), definition().subtitle("sounds.morefeatures.magic_block_step")
                .with(sound(withMfNamespace("magic_block_step"))));
        this.add(MFSoundEvents.MAGIC_BLOCK_PLACE.get(), definition().subtitle("sounds.morefeatures.magic_block_place")
                .with(sound(withMfNamespace("magic_block_place"))));
        this.add(MFSoundEvents.MAGIC_BLOCK_HIT.get(), definition().subtitle("sounds.morefeatures.magic_block_hit")
                .with(sound(withMfNamespace("magic_block_hit"))));
        this.add(MFSoundEvents.MAGIC_BLOCK_FALL.get(), definition().subtitle("sounds.morefeatures.magic_block_fall")
                .with(sound(withMfNamespace("magic_block_fall"))));

        // These sounds events use the same OGG files as the magic block, but with different volume and pitch.
        // The volume and pitch have been defined in the MFSoundTypes class, rather than this datagen class.

        this.add(MFSoundEvents.EVIL_PORTAL_BREAK.get(), definition().subtitle("sounds.morefeatures.evil_block_break")
                .with(sound(withMfNamespace("magic_block_break"))));
        this.add(MFSoundEvents.EVIL_PORTAL_STEP.get(), definition().subtitle("sounds.morefeatures.evil_block_step")
                .with(sound(withMfNamespace("magic_block_step"))));
        this.add(MFSoundEvents.EVIL_PORTAL_PLACE.get(), definition().subtitle("sounds.morefeatures.evil_block_place")
                .with(sound(withMfNamespace("magic_block_place"))));
        this.add(MFSoundEvents.EVIL_PORTAL_HIT.get(), definition().subtitle("sounds.morefeatures.evil_block_hit")
                .with(sound(withMfNamespace("magic_block_hit"))));
        this.add(MFSoundEvents.EVIL_PORTAL_FALL.get(), definition().subtitle("sounds.morefeatures.evil_block_fall")
                .with(sound(withMfNamespace("magic_block_fall"))));
    }
}
