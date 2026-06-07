package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.sound.ModSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ModSoundsProvider extends SoundDefinitionsProvider {
    public ModSoundsProvider(PackOutput output) {
        super(output, MoreFeatures.MODID);
    }

    @Override
    public void registerSounds() {
        add(ModSounds.AQUAMARINE.get(), definition().subtitle("sounds.morefeatures.aquamarine")
                .with(sound(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "aquamarine")).stream()));

        add(ModSounds.MAGIC_BLOCK_BREAK.get(), definition().subtitle("sounds.morefeatures.magic_block_break")
                .with(sound(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "magic_block_break"))));
        add(ModSounds.MAGIC_BLOCK_STEP.get(), definition().subtitle("sounds.morefeatures.magic_block_step")
                .with(sound(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "magic_block_step"))));
        add(ModSounds.MAGIC_BLOCK_PLACE.get(), definition().subtitle("sounds.morefeatures.magic_block_place")
                .with(sound(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "magic_block_place"))));
        add(ModSounds.MAGIC_BLOCK_HIT.get(), definition().subtitle("sounds.morefeatures.magic_block_hit")
                .with(sound(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "magic_block_hit"))));
        add(ModSounds.MAGIC_BLOCK_FALL.get(), definition().subtitle("sounds.morefeatures.magic_block_fall")
                .with(sound(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "magic_block_fall"))));
    }
}
