package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public abstract class TargetingBlockWandItem extends AbstractWandItem{

    public TargetingBlockWandItem(Properties properties, int cooldownTicks,
                                  int baseDurabilityCost, int manaCost,
                                  SoundEvent castSound, float soundVolume, float soundPitch) {
        super(properties, 0.0D, cooldownTicks, baseDurabilityCost, 0, manaCost, castSound, soundVolume, soundPitch);
    }

    @NullMarked
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        ItemStack stack = context.getItemInHand();
        InteractionHand hand = context.getHand();

        if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.PASS;

        assert player != null;
        if (hasEnoughMana(player)) {
            castBlockSpell(serverLevel, player, pos);

            consumeMana(player);

            applyCastEffects(player, stack, baseDurabilityCost, hand, level);

            return InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.PASS;
    }

    protected abstract void castBlockSpell(ServerLevel level, Player player, BlockPos pos);
}
