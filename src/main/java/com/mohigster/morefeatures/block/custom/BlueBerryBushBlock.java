package com.mohigster.morefeatures.block.custom;

import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.data.references.MFLootTableIds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class BlueBerryBushBlock extends SweetBerryBushBlock {
    public BlueBerryBushBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        return new ItemStack(MFItems.BLUE_BERRY.get());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int age = state.getValue(AGE);

        if (age > 1){
            if (level instanceof ServerLevel server) {
                pickBerry(state, server, pos, player);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    protected void pickBerry(BlockState state, ServerLevel server, BlockPos pos, Player player){
        float pitch = 0.8F + server.getRandom().nextFloat() * 0.4F;

        Block.dropFromBlockInteractLootTable(server, MFLootTableIds.HARVEST_BLUE_BERRY_BUSH, state, server.getBlockEntity(pos), null, player, (serverLevel, itemStack) -> Block.popResource(serverLevel, pos, itemStack));
        this.playSound(server, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 1.0F, pitch);
        BlockState newState = state.setValue(AGE, 1);
        server.setBlock(pos, newState, 2);
        server.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
    }

    @SuppressWarnings("SameParameterValue")
    private void playSound(ServerLevel server, BlockPos pos, SoundEvent sound, float volume, float pitch){
        server.playSound(null, pos, sound, SoundSource.BLOCKS, volume, pitch);
    }
}
