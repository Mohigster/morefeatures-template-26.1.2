package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public interface SelfInflicting {
    boolean canCastSpellOnSelf(Player caster); // Whether the target can have the spell cast on them is easily defined in the target predicate. However, the same is not said for when casting on yourself, hence this abstract method.

    void castSelfInflictingSpell(Player caster, Level level); // To make the self inflicting spell the same as the targeting spell, it is as simple as calling castTargetedSpell(caster, caster, level); in this method. Otherwise, write your own spell logic in here.
}
