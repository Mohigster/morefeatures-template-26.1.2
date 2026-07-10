package com.mohigster.morefeatures.events;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.custom.VoidAnchorBlock;
import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import com.mohigster.morefeatures.block.entity.custom.CompressorBlockEntity;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.worldgen.biome.MFBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.storage.LevelData;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerRespawnPositionEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EventBusSubscriber(modid = MoreFeatures.MODID)
public class MFEvents {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {

        Player player = event.getEntity();

        if (!player.isFallFlying()) return;

        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!chest.is(MFItems.CARBON_ELYTRA.get())) return;

        List<FireworkRocketEntity> rockets =
                player.level().getEntitiesOfClass(
                        FireworkRocketEntity.class,
                        player.getBoundingBox().inflate(3)
                );

        for (FireworkRocketEntity rocket : rockets) {

            if (rocket.getOwner() == player) {
                double maxSpeed = 8.5;
                Vec3 movement = player.getDeltaMovement();

                if (movement.length() <= maxSpeed) {
                    player.push(
                            movement.x * 0.185,
                            movement.y * 0.185,
                            movement.z * 0.185
                    );
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTickIceCaves(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) return;

        if (!player.level().getBiome(player.blockPosition()).is(MFBiomes.ICE_CAVES)) {
            return;
        }

        if(!player.gameMode().isSurvival()) return;

        if (!player.isInWater()) {
            return;
        }

        int currentFrozen = player.getTicksFrozen();
        int required = player.getTicksRequiredToFreeze();

        player.setTicksFrozen(Math.min(currentFrozen + 3, required + 20));
    }

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {

        if (!(event.getEntity() instanceof AbstractArrow arrow)) {
            return;
        }

        if (!(arrow.getOwner() instanceof Player player)) {
            MoreFeatures.LOGGER.info("Shooter was not a player");
            return;
        }

        Vec3 movement = arrow.getDeltaMovement();

        double velBonus = movement.length();

        double baseDamage = 2.0 * velBonus;

        ItemStack weapon = player.getUseItem();


        if (weapon.is(MFItems.CARBON_BOW.get())) { // Carbon Bow damage boost
            MoreFeatures.LOGGER.debug("Carbon damage multiplier applied");
            arrow.setBaseDamage(baseDamage * 0.75);
        } else if (weapon.is(MFItems.BISMUTH_BOW.get())) { // Bismuth Bow damage boost
            MoreFeatures.LOGGER.debug("Bismuth damage multiplier applied");
            arrow.setBaseDamage(baseDamage * 1.05);
        }
    }

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {
        if(event.getEntity() instanceof Sheep sheep && event.getSource().getDirectEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() == Items.END_ROD) {
                player.sendSystemMessage(Component.literal(player.getName().getString() + " just hit this sheep with an End Rod? YOU SICK FUCK!"));
                player.getMainHandItem().shrink(1);
                sheep.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 6));
            }
        }
    }

    @SubscribeEvent
    public static void onBonemeal(BonemealEvent event) {

        final int SCAN_RADIUS = 1;

        RandomSource random = RandomSource.create();
        // Only act on End Stone
        BlockState state = event.getState();
        if (!state.is(Blocks.END_STONE)) {
            return;
        }

        // Only run server-side
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }

        BlockPos center = event.getPos();

        boolean foundPallid  = false;
        boolean foundDecrepit = false;

        // Scan the 3x3x3 cube centred on the bone-mealed End Stone
        for (int dx = -SCAN_RADIUS; dx <= SCAN_RADIUS; dx++) {
            for (int dy = -SCAN_RADIUS; dy <= SCAN_RADIUS; dy++) {
                for (int dz = -SCAN_RADIUS; dz <= SCAN_RADIUS; dz++) {
                    BlockState neighbour = serverLevel.getBlockState(center.offset(dx, dy, dz));

                    if (neighbour.is(MFBlocks.PALLID_NULLIUM.get())) {
                        foundPallid = true;
                    }
                    if (neighbour.is(MFBlocks.DECREPIT_NULLIUM.get())) {
                        foundDecrepit = true;
                    }

                    // Early-exit once both variants are found
                    if (foundPallid && foundDecrepit) break;
                }
                if (foundPallid && foundDecrepit) break;
            }
            if (foundPallid && foundDecrepit) break;
        }

        // Nothing nearby — don't consume bone meal, let default behaviour run
        if (!foundPallid && !foundDecrepit) {
            return;
        }

        // Build the candidate list exactly as vanilla does:
        // if both variants are present the game picks one at random.
        List<Block> candidates = new ArrayList<>();
        if (foundPallid)   candidates.add(MFBlocks.PALLID_NULLIUM.get());
        if (foundDecrepit) candidates.add(MFBlocks.DECREPIT_NULLIUM.get());

        Block chosen = candidates.get(random.nextInt(candidates.size()));

        // Replace only the exact bone-mealed End Stone block
        serverLevel.setBlock(center, chosen.defaultBlockState(), Block.UPDATE_ALL);

        // Tell NeoForge we handled the event — this consumes the bone meal
        // and prevents other handlers (including vanilla) from also firing.
        event.setSuccessful(true);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void onRespawnPosition(PlayerRespawnPositionEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ServerPlayer.RespawnConfig respawnConfig = player.getRespawnConfig();

        if (respawnConfig == null) return;

        // Resolve the dimension the anchor was set in.
        ServerLevel anchorLevel = player.level().getServer().getLevel(respawnConfig.respawnData().dimension());
        if (anchorLevel == null) return;

        // Only handle our block — leave vanilla Respawn Anchor behaviour alone.
        BlockPos anchorPos = respawnConfig.respawnData().pos();
        BlockState state = anchorLevel.getBlockState(anchorPos);
        if (!(state.getBlock() instanceof VoidAnchorBlock)) return;

        // Confirm the anchor is still in the End and still charged.
        if (!VoidAnchorBlock.canSetSpawn(anchorLevel)) return;
        if (state.getValue(VoidAnchorBlock.CHARGE) == 0) return;

        // Find a safe stand-up position around the anchor.
        Optional<Vec3> spawnPos = VoidAnchorBlock.findStandUpPosition(
                player.getType(), anchorLevel, anchorPos
        );

        if (spawnPos.isEmpty()) {
            // Anchor is obstructed — consume a charge and fall back to world spawn.
            // This mirrors vanilla behaviour when the Respawn Anchor is blocked.
            consumeCharge(anchorLevel, anchorPos, state);
            return;
        }

        // Consume one charge and redirect the respawn.
        consumeCharge(anchorLevel, anchorPos, state);
        if(anchorLevel.dimension().equals(Level.END)) {
            event.setTeleportTransition(new TeleportTransition(
                    anchorLevel,
                    spawnPos.get(),
                    Vec3.ZERO,      // no velocity on respawn
                    0.0F,           // yaw — you could derive this from the anchor facing if desired
                    0.0F,           // pitch
                    TeleportTransition.DO_NOTHING
            ));
        }
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event){
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // If the player already has a valid respawn config, nothing to do.
        if (player.getRespawnConfig() != null) return;

        ServerLevel currentLevel = (ServerLevel) player.level();

        if (!VoidAnchorBlock.canSetSpawn(currentLevel)) return;

        // Find the nearest charged Void Anchor within a reasonable search radius.
        // We stored nothing extra, so we find the closest one to the player's
        // current position as a best-effort restore.
        BlockPos playerPos = player.blockPosition();
        int searchRadius = 8; // should always be within 1 block of the anchor
        for (BlockPos candidate : BlockPos.betweenClosed(
                playerPos.offset(-searchRadius, -searchRadius, -searchRadius),
                playerPos.offset(searchRadius, searchRadius, searchRadius)
        )) {
            BlockState state = currentLevel.getBlockState(candidate);
            if (!(state.getBlock() instanceof VoidAnchorBlock)) continue;
            if (state.getValue(VoidAnchorBlock.CHARGE) == 0) continue;

            // Found it — restore the respawn config silently.
            player.setRespawnPosition(
                    new ServerPlayer.RespawnConfig(LevelData.RespawnData.of(currentLevel.dimension(), candidate.immutable(), 0.0F, 0.0F), false),
                    false
            );
            currentLevel.playSound(null, candidate, SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), SoundSource.BLOCKS);
            return;
        }
    }

    private static void consumeCharge(ServerLevel level, BlockPos pos, BlockState state) { // This part works perfectly
        int current = state.getValue(VoidAnchorBlock.CHARGE);
        if (current > 0) {
            level.setBlock(pos, state.setValue(VoidAnchorBlock.CHARGE, current - 1), 3);
        }
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Item.BLOCK, MFBlockEntities.COMPRESSOR_BE.get(), CompressorBlockEntity::getItemHandler);

        event.registerBlockEntity(Capabilities.Energy.BLOCK, MFBlockEntities.COMPRESSOR_BE.get(), CompressorBlockEntity::getEnergyStorage);

        event.registerBlockEntity(Capabilities.Fluid.BLOCK, MFBlockEntities.COMPRESSOR_BE.get(), CompressorBlockEntity::getFluidTank);
    }
}
