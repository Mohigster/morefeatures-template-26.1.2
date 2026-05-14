package com.mohigster.morefeatures.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class BrineEntity extends Monster {

    protected BrineEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }
}
