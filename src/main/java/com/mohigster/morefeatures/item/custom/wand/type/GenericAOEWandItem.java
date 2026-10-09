package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;

public abstract non-sealed class GenericAOEWandItem extends AbstractWandItem implements AreaOfEffect {
    private final int ringCount;
    private final int floorScanDistance;

    public GenericAOEWandItem(
            Properties properties,
            double radius,
            int cooldownTicks,
            int ringCount,
            int baseDurabilityCost,
            int manaCost,
            int floorScanDistance,
            SoundEvent castSound,
            float soundVolume,
            float soundPitch
    ) {
        super(properties, radius, cooldownTicks, baseDurabilityCost,
                0, manaCost, castSound, soundVolume, soundPitch);

        this.ringCount = ringCount;
        this.floorScanDistance = floorScanDistance;
    }

    @Override
    public @NonNull InteractionResult use(Level level, Player player, @NonNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide() && hasEnoughMana(player)){
            List<BlockPos> affectedPositions = this.calculateRingPositions(level, player);

            for (BlockPos pos : affectedPositions){
                this.castAOESpell(player, level, pos);
            }

            this.consumeMana(player);

            this.applyCastEffects(player, itemStack, baseDurabilityCost, hand, level);

            return InteractionResult.SUCCESS_SERVER;
        }

        return InteractionResult.PASS;
    }

    @Override
    public int ringCount() {
        return this.ringCount;
    }

    @Override
    public double radius() {
        return this.radius;
    }

    @Override
    public int floorScanDistance() {
        return this.floorScanDistance;
    }
}
