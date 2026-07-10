package com.mohigster.morefeatures.item.custom.wand;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.item.custom.wand.type.TargetingWandItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class IceWandItem extends TargetingWandItem {
    private static final double RADIUS = 10.0D;

    private static final int COOLDOWN = 40;
    private static final int BASE_DURABILITY_COST = 1;
    private static final int SCALING_FACTOR = 2;
    private static final int MAX_TARGETS = 20;
    private static final int MANA_COST = 5;

    private static final float PROXIMITY_LIMIT = 2.0F;
    private static final float VOLUME = 1.0F;
    private static final float PITCH = 0.5F;

    public IceWandItem(Properties properties) {
        super(properties, RADIUS, COOLDOWN, BASE_DURABILITY_COST, SCALING_FACTOR, MANA_COST,
                MAX_TARGETS, PROXIMITY_LIMIT, SoundEvents.GLASS_BREAK, VOLUME, PITCH);
    }

    @Override
    protected void castTargetedSpell(LivingEntity target, Player caster, Level level) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        BlockPos spawnPos = BlockPos.containing(target.getX(), target.getEyeY() + 4.0, target.getZ());

        FallingBlockEntity iceChunk = FallingBlockEntity.fall(
                serverLevel, spawnPos, MFBlocks.CONJURED_ICE.get().defaultBlockState()
        );

        iceChunk.setHurtsEntities(5.0F, 30);
    }
}
