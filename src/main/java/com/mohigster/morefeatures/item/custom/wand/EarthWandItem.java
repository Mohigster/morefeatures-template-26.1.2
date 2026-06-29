package com.mohigster.morefeatures.item.custom.wand;

import com.mohigster.morefeatures.item.custom.wand.type.GenericAOEWandItem;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.level.Level;

public class EarthWandItem extends GenericAOEWandItem {
    private static final double RADIUS = 12.0D;
    private static final int COOLDOWN = 50;
    private static final int DURABILITY_COST = 2;
    private static final int RINGS = 5;
    private static final int FLOOR_SCAN_DISTANCE = 12;
    private static final float VOLUME = 1.0F;
    private static final float PITCH = 0.5F;

    public EarthWandItem(Properties properties) {
        super(properties, RADIUS, COOLDOWN, RINGS, DURABILITY_COST,
                SoundEvents.DEEPSLATE_BREAK, VOLUME, PITCH);
    }


    @Override
    protected int floorScanDistance() {
        return FLOOR_SCAN_DISTANCE;
    }

    @Override
    protected void castAOESpell(Player caster, Level level, BlockPos pos) {
        double distance = Math.sqrt(pos.distToCenterSqr(caster.getX(), pos.getY(), caster.getZ()));

        int dynamicWarmup = (int) (distance * 1.5); // Calculate a so the ring spreads outwards and doesn't all spawn at once

        EvokerFangs fangs = new EvokerFangs(
                    level,
                    pos.getX() + 0.5,
                    pos.getY(),
                    pos.getZ() + 0.5,
                    caster.getYRot(),
                    dynamicWarmup,
                    caster
        );

        level.addFreshEntity(fangs);
    }
}
