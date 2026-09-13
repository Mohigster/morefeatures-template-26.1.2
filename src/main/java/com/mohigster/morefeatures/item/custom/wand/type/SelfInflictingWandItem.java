package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;

public abstract class SelfInflictingWandItem extends AbstractWandItem implements SelfInflicting {
    public SelfInflictingWandItem(Properties properties, double radius, int cooldownTicks, int baseDurabilityCost, int durabilityScalingFactor, int manaCost, SoundEvent castSound, float soundVolume, float soundPitch) {
        super(properties, radius, cooldownTicks, baseDurabilityCost, durabilityScalingFactor, manaCost, castSound, soundVolume, soundPitch);
    }

    @Override
    public boolean canCastSpellOnSelf(Player caster) {
        return true;
    }
}
