package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.sound.MFSounds;
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

        add(MFSounds.AQUAMARINE.get(), definition().subtitle("sounds.morefeatures.aquamarine")
                .with(sound(withMfNamespace("aquamarine")).stream()));

        add(MFSounds.MUSIC_BIOME_ICE_CAVES.get(), definition().subtitle("music.morefeatures.ice_caves")
                .with(sound(withMfNamespace(""))));

        // Magic block sounds

        add(MFSounds.MAGIC_BLOCK_BREAK.get(), definition().subtitle("sounds.morefeatures.magic_block_break")
                .with(sound(withMfNamespace("magic_block_break"))));
        add(MFSounds.MAGIC_BLOCK_STEP.get(), definition().subtitle("sounds.morefeatures.magic_block_step")
                .with(sound(withMfNamespace("magic_block_step"))));
        add(MFSounds.MAGIC_BLOCK_PLACE.get(), definition().subtitle("sounds.morefeatures.magic_block_place")
                .with(sound(withMfNamespace("magic_block_place"))));
        add(MFSounds.MAGIC_BLOCK_HIT.get(), definition().subtitle("sounds.morefeatures.magic_block_hit")
                .with(sound(withMfNamespace("magic_block_hit"))));
        add(MFSounds.MAGIC_BLOCK_FALL.get(), definition().subtitle("sounds.morefeatures.magic_block_fall")
                .with(sound(withMfNamespace("magic_block_fall"))));

        add(MFSounds.EVIL_PORTAL_BREAK.get(), definition().subtitle("sounds.morefeatures.evil_block_break")
                .with(sound(withMfNamespace("magic_block_break"))));
        add(MFSounds.EVIL_PORTAL_STEP.get(), definition().subtitle("sounds.morefeatures.evil_block_step")
                .with(sound(withMfNamespace("magic_block_step"))));
        add(MFSounds.EVIL_PORTAL_PLACE.get(), definition().subtitle("sounds.morefeatures.evil_block_place")
                .with(sound(withMfNamespace("magic_block_place"))));
        add(MFSounds.EVIL_PORTAL_HIT.get(), definition().subtitle("sounds.morefeatures.evil_block_hit")
                .with(sound(withMfNamespace("magic_block_hit"))));
        add(MFSounds.EVIL_PORTAL_FALL.get(), definition().subtitle("sounds.morefeatures.evil_block_fall")
                .with(sound(withMfNamespace("magic_block_fall"))));
    }
}
