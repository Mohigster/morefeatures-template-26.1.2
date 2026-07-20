package com.mohigster.morefeatures.item.custom.wand;

import com.mohigster.morefeatures.item.custom.wand.type.TargetingBlockWandItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public class TimeWandItem extends TargetingBlockWandItem {

    private static final int COOLDOWN_TICKS = 25;
    private static final int DURABILITY_COST = 2;
    private static final int MANA_COST = 3;

    private static final float VOLUME = 1.2F;
    private static final float PITCH = 1.0F;

    public TimeWandItem(Properties properties) {
        super(properties, COOLDOWN_TICKS, DURABILITY_COST, MANA_COST,
                SoundEvents.AMETHYST_BLOCK_RESONATE, VOLUME, PITCH);
    }

    @Override
    protected void castBlockSpell(ServerLevel level, Player player, BlockPos pos) {
        BlockState state = level.getBlockState(pos);

        for (int i = 0; i < 30; i ++){
            state.randomTick(level, pos, level.getRandom());
        }
    }
}
