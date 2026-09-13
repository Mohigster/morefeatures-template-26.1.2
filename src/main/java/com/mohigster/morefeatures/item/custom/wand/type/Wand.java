package com.mohigster.morefeatures.item.custom.wand.type;

import com.mohigster.morefeatures.attachment.MFAttachments;
import net.minecraft.world.entity.player.Player;

public interface Wand {
    int manaCost();

    default boolean hasEnoughMana(Player caster){
        return this.getMana(caster) >= this.manaCost();
    }

    default void consumeMana(Player caster) {
        caster.setData(MFAttachments.MANA, this.calculateManaConsumption(caster));
    }

    default int calculateManaConsumption(Player caster) {
        return this.getMana(caster) - this.manaCost();
    }

    default int getMana(Player caster) {
        return caster.getData(MFAttachments.MANA);
    }
}
