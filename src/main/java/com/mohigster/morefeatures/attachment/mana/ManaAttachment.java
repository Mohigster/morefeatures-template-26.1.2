package com.mohigster.morefeatures.attachment.mana;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class ManaAttachment {
    private static final int MAX_MANA = 100;

    private int mana = MAX_MANA;

    public static final Codec<ManaAttachment> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("mana").forGetter(ManaAttachment::getMana)
            ).apply(instance, mana -> {
                ManaAttachment a = new ManaAttachment();
                a.setMana(mana);
                return a;
            })
    );

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return MAX_MANA;
    }

    public void setMana(int mana) {
        this.mana = Math.max(0, Math.min(MAX_MANA, mana));
    }

    public boolean consume(int amount) {
        if (mana < amount) return false;
        mana -= amount;
        return true;
    }

    public void recharge(int amount) {
        setMana(mana + amount);
    }
}
