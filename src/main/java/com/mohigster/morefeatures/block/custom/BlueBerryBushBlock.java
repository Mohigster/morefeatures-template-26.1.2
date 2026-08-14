package com.mohigster.morefeatures.block.custom;

import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.data.resources.references.MFLootTableIds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
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
import net.neoforged.neoforge.common.CommonHooks;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class BlueBerryBushBlock extends SweetBerryBushBlock {
    private static final float BASE_PITCH = 0.8F;
    private static final float AGE_UP_PITCH_MULTIPLIER = 0.1F;
    private static final float HARVEST_PITCH_MULTIPLIER = 0.4F;

    private static final float AGE_UP_VOLUME = 0.3F;
    private static final float HARVEST_VOLUME = 1.0F;

    private static final int BERRYLESS_GROWN_AGE = 1;
    private static final int MIN_LIGHT_LEVEL_FOR_GROWTH = 9;
    private static final int GROWTH_CHANCE = 5;

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

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int age = state.getValue(AGE);
        if (age < MAX_AGE && level.getRawBrightness(pos.above(), 0) >= MIN_LIGHT_LEVEL_FOR_GROWTH
                && CommonHooks.canCropGrow(level, pos, state, random.nextInt(GROWTH_CHANCE) == 0)) {
            this.ageUpBerry(state, level, pos, age);
        }
    }

    protected void ageUpBerry(BlockState state, ServerLevel server, BlockPos pos, int age){
        float pitch = BASE_PITCH + randFloat(server, AGE_UP_PITCH_MULTIPLIER);

        BlockState newState = state.setValue(AGE, age + 1);
        server.setBlock(pos, newState, 2);
        this.playSound(server, pos, SoundEvents.GROWING_PLANT_CROP, AGE_UP_VOLUME, pitch);
        CommonHooks.fireCropGrowPost(server, pos, state);
        server.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));
    }

    protected void pickBerry(BlockState state, ServerLevel server, BlockPos pos, Player player){
        float pitch = BASE_PITCH + randFloat(server, HARVEST_PITCH_MULTIPLIER);

        Block.dropFromBlockInteractLootTable(server,
                MFLootTableIds.HARVEST_BLUE_BERRY_BUSH, state, server.getBlockEntity(pos),
                null, player, (level, itemStack) -> Block.popResource(
                        level, pos, itemStack));

        this.playSound(server, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, HARVEST_VOLUME, pitch);
        BlockState newState = state.setValue(AGE, BERRYLESS_GROWN_AGE);
        server.setBlock(pos, newState, 2);
        server.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
    }

    protected void playSound(ServerLevel server, BlockPos pos, SoundEvent sound, float volume, float pitch){
        server.playSound(null, pos, sound, SoundSource.BLOCKS, volume, pitch);
    }

    private static float randFloat(ServerLevel server, float multiplier) {
        return randFloat(server) * multiplier;
    }

    private static float randFloat(ServerLevel server) {
        return server.getRandom().nextFloat();
    }
}
