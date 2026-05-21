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
    }
}
