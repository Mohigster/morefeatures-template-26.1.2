package com.mohigster.morefeatures.item.custom.metaldetector;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
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
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @NullMarked
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos positionClicked = context.getClickedPos();
        Player player = context.getPlayer();

        if(!level.isClientSide()){
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY() + 64; i++){
                BlockState blockState = level.getBlockState(positionClicked.below(i));

                if(isValidTarget(blockState)){
                    assert player != null;
                    outputValuableCoordinates(positionClicked.below(i), player, blockState.getBlock());
                    foundBlock = true;

                    // Calculate damage cost based on data-driven costs (defaults to 1 if a cost is not defined)
                    int damageCost = getDamageCost(blockState);

                    MoreFeatures.LOGGER.debug("damageCost: {}", damageCost);

                    context.getItemInHand().hurtAndBreak(damageCost, player, context.getHand());

                    level.playSound(null, positionClicked,
                            SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.5f, 1f);

                    spawnFoundParticles(level, positionClicked, blockState);

                    break;
                }
            }

            if(!foundBlock){
                assert player != null;
                outputNoValuablesFound(player);
                level.playSound(null, positionClicked,
                        SoundEvents.EGG_THROW, SoundSource.BLOCKS, 1.5f, 1f);
            }
        }

        return InteractionResult.SUCCESS;
    }

    private void spawnFoundParticles(Level level, BlockPos positionClicked, BlockState blockState) {
        for(int i = 0; i < 20; i++){
            ServerLevel serverLevel = (ServerLevel) level;

            serverLevel.sendParticles(this.getParticleType(blockState),
            positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d, 1,
                    Math.cos(i * 18) * 0.15d, 0.15d, Math.sin(i * 18) * 0.15d, 0.1);
        }
    }

    @SuppressWarnings("unchecked")
    protected <T extends ParticleOptions> T getParticleType(BlockState state) {
        return (T) new BlockParticleOption(ParticleTypes.BLOCK, state);
    }

    private static int getDamageCost(BlockState state){
        int totalCost = MetalDetectorCosts.getCost(state);

        if (totalCost == 0){
            throw new IllegalStateException("Metal detector durability cost must not be zero! If the value is not zero within the JSON file, report the issue on GitHub.");
        }

        return totalCost;
    }

    protected boolean isValidTarget(BlockState state) {
        return state.is(MFBlockTags.METAL_DETECTOR_FINDABLE);
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
