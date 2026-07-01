package com.mohigster.morefeatures.item.custom.wand;

import com.mohigster.morefeatures.item.custom.wand.type.GenericAOEWandItem;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LightningWandItem extends GenericAOEWandItem {
    private static final double RADIUS = 35.0D;
    private static final int COOLDOWN = 100;
    private static final int RINGS = 3;
    private static final int DURABILITY_COST = 5;
    private static final int FLOOR_SCAN_DISTANCE = 128;
    private static final float DAMAGE = 10.0F;
    private static final float VOLUME = 1.0F;
    private static final float PITCH = 1.0F;

    public LightningWandItem(Properties properties) {
        super(properties, RADIUS, COOLDOWN, RINGS, DURABILITY_COST, SoundEvents.LIGHTNING_BOLT_THUNDER, VOLUME, PITCH);
    }

    @Override
    protected int blocksToScanForFloor() {
        return FLOOR_SCAN_DISTANCE;
    }

    @Override
    protected void castAOESpell(Player caster, Level level, BlockPos pos) {
        LightningBolt bolt = EntityTypes.LIGHTNING_BOLT.create(level, EntitySpawnReason.MOB_SUMMONED);
        if (bolt != null) {
            bolt.setPos(Vec3.atBottomCenterOf(pos));
            bolt.setDamage(DAMAGE);
            level.addFreshEntity(bolt);
        }
    }
}
