package com.mohigster.morefeatures.item.custom;

import com.mohigster.morefeatures.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos positionClicked = context.getClickedPos();
        Player player = context.getPlayer();

        if(!level.isClientSide()){
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++){
                BlockState blockState = level.getBlockState(positionClicked.below(i));

                if(isValuableBlock(blockState)){
                    outputValuableCoordinates(positionClicked.below(i), player, blockState.getBlock());
                    foundBlock = true;

                    // Damage the item
                    context.getItemInHand().hurtAndBreak(1, player, context.getHand());
                    // Play sound
                    level.playSound(null, positionClicked,
                            SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.5f, 1f);
                    // Spawn particles
                    spawnFoundParticles(level, positionClicked, blockState);

                    break;
                }
            }

            if(!foundBlock){
                outputNoValuablesFound(player);
                level.playSound(null, positionClicked,
                        SoundEvents.ANVIL_LAND, SoundSource.BLOCKS, 1.5f, 1f);
            }
        }

        return InteractionResult.SUCCESS;
    }

    private void spawnFoundParticles(Level level, BlockPos positionClicked, BlockState blockState) {
        for(int i = 0; i < 20; i++){
            ServerLevel serverLevel = (ServerLevel) level;

            serverLevel.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState),
            positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d, 1,
                    Math.cos(i * 18) * 0.15d, 0.15d, Math.sin(i * 18) * 0.15d, 0.1);
        }
    }
    private boolean isValuableBlock(BlockState blockState) {
        return(
                blockState.is(Blocks.IRON_ORE) ||
                blockState.is(Blocks.DEEPSLATE_IRON_ORE) ||
                blockState.is(ModBlocks.BISMUTH_ORE) ||
                blockState.is(Blocks.GOLD_ORE) ||
                blockState.is(Blocks.DEEPSLATE_GOLD_ORE) ||
                blockState.is(Blocks.COPPER_ORE) ||
                blockState.is(Blocks.DEEPSLATE_COPPER_ORE) ||
                blockState.is(ModBlocks.ALUMINIUM_ORE) ||
                blockState.is(ModBlocks.DEEPSLATE_ALUMINIUM_ORE) ||
                blockState.is(ModBlocks.MAGNESIUM_ORE) ||
                blockState.is(ModBlocks.DEEPSLATE_MAGNESIUM_ORE) ||
                blockState.is(Blocks.ANCIENT_DEBRIS) ||
                blockState.is(Blocks.NETHER_GOLD_ORE)
        );

    }

    private void outputNoValuablesFound(Player player) {
        player.sendSystemMessage(Component.translatable("item.morefeatures.metal_detector.fail"));
    }

    private void outputValuableCoordinates(BlockPos position, Player player, Block block) {
        player.sendSystemMessage(Component.keybind("Valuables Found: ")
                .append(block.getName())
                .append(Component.literal(
                        " at ("
                        + position.getX()
                        + ", "
                        + position.getY()
                        + ", "
                        + position.getZ()
                        + ")"
                ))
        );
    }
}
