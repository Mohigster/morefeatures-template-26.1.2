package com.mohigster.morefeatures.events;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.custom.VoidAnchorBlock;
import com.mohigster.morefeatures.block.custom.data.MFDataMaps;
import com.mohigster.morefeatures.block.custom.data.codec.BonemealMorph;
import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import com.mohigster.morefeatures.block.entity.custom.CompressorBlockEntity;
import com.mohigster.morefeatures.events.data.BowDamageBonuses;
import com.mohigster.morefeatures.events.data.ElytraSpeedBoosts;
import com.mohigster.morefeatures.data.world.biome.MFBiomes;
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
import java.util.Objects;
import java.util.Optional;

@EventBusSubscriber(modid = MoreFeatures.MODID)
public class MFEvents {

    private static boolean sharedAllElytraEntries;
    private static boolean sharedAllBowEntries;

    // Both elytra speed and bow damage are data-driven

    @SubscribeEvent
    public static void onPlayerTickElytra(PlayerTickEvent.Post event) {

        Player player = event.getEntity();

        if (!player.isFallFlying()) return;

        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!ElytraSpeedBoosts.INSTANCE.getElytraEntries().contains(chest.getItem())) return;

        // This boolean prevents the logger from being spammed for every tick that the player is flying
        if(!sharedAllElytraEntries) {
            MoreFeatures.LOGGER.debug("All Elytra Entries: {}", ElytraSpeedBoosts.INSTANCE.getElytraEntries());
            sharedAllElytraEntries = true;
        }

        boolean hasActiveRocket = !player.level().getEntitiesOfClass(
                FireworkRocketEntity.class,
                player.getBoundingBox().inflate(3),
                rocket -> rocket.getOwner() == player
        ).isEmpty();

        if (hasActiveRocket) {
            double speedBoost = ElytraSpeedBoosts.INSTANCE.getSpeed(chest);
            double maxSpeed = ElytraSpeedBoosts.INSTANCE.getMaxSpeed(chest);
            Vec3 currentVelocity = player.getDeltaMovement();

            if (currentVelocity.length() < maxSpeed) {
                player.push(
                        currentVelocity.x * speedBoost,
                        currentVelocity.y * speedBoost,
                        currentVelocity.z * speedBoost
                );
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTickIceCaves(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) return;

        if (!player.level().getBiome(player.blockPosition()).is(MFBiomes.ICE_CAVES)) return;

        if (!Objects.requireNonNull(player.gameMode()).isSurvival()) return;

        if (!player.isInWater() && !player.isInPowderSnow) return;

        int currentFrozen = player.getTicksFrozen();
        int required = player.getTicksRequiredToFreeze();

        int requiredModifier = player.isInWater() ? 20 : 5;

        player.setTicksFrozen(Math.min(currentFrozen + 3, required + requiredModifier));
    }

    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof AbstractArrow arrow)) return;
        if (!(arrow.getOwner() instanceof Player player)) return;

        ItemStack weapon = player.getUseItem();

        if (!BowDamageBonuses.INSTANCE.isBow(weapon)) return;

        if (!sharedAllBowEntries){
            MoreFeatures.LOGGER.debug("All Bow Entries: {}", BowDamageBonuses.INSTANCE.getBowEntries());
            sharedAllBowEntries = true;
        }

        double baseDamage = 2.0F + arrow.getRandom().triangle(arrow.level().getDifficulty().getId() * 0.11, 0.57425);
        double totalDamage = getBowDamage(baseDamage, weapon);

        arrow.setBaseDamage(totalDamage == 0 ? baseDamage : totalDamage);
    }

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {
        if(event.getEntity() instanceof Sheep sheep && event.getSource().getDirectEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() == Items.END_ROD) {
                player.sendSystemMessage(Component.literal(player.getName().getString() + " just hit this sheep with an End Rod? YOU SICK FUCK!"));
                player.getMainHandItem().shrink(1);
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 150, 3));
                sheep.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 6));
            }
        }
    }

    // This event takes data from the BonemealMorphs data map and uses it to transform a block to another when bonemealed
    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void onBonemeal(BonemealEvent event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState targetState = event.getState();

        BonemealMorph data = targetState.getBlock().builtInRegistryHolder().getData(MFDataMaps.BONEMEAL_MORPHS);

        if (data == null || data.variants().isEmpty()) {
            return;
        }

        List<Block> availableVariants = new ArrayList<>();

        for (BlockPos testPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            BlockState nearbyState = level.getBlockState(testPos);

            for (Block variantBlock : data.variants()) {
                if (nearbyState.is(variantBlock) && !availableVariants.contains(variantBlock)) {
                    availableVariants.add(variantBlock);
                }
            }
        }

        if (!availableVariants.isEmpty()) {
            if (!level.isClientSide()) {
                RandomSource random = level.getRandom();
                Block chosenVariant = availableVariants.get(random.nextInt(availableVariants.size()));

                level.setBlock(pos, chosenVariant.defaultBlockState(), Block.UPDATE_ALL);
            }

            level.levelEvent(1505, pos, 20);

            event.setSuccessful(true);
            event.setCanceled(true);
        }
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
        if (VoidAnchorBlock.cannotSetSpawn(anchorLevel)) return;
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

        ServerLevel currentLevel = player.level();

        if (VoidAnchorBlock.cannotSetSpawn(currentLevel)) return;

        BlockPos playerPos = player.blockPosition();
        int searchRadius = 8; // should always be within 1 block of the anchor
        for (BlockPos candidate : BlockPos.betweenClosed(
                playerPos.offset(-searchRadius, -searchRadius, -searchRadius),
                playerPos.offset(searchRadius, searchRadius, searchRadius)
        )) {
            BlockState state = currentLevel.getBlockState(candidate);
            if (!(state.getBlock() instanceof VoidAnchorBlock)) continue;
            if (state.getValue(VoidAnchorBlock.CHARGE) == 0) continue;

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

    private static double getBowDamage(double baseDamage, ItemStack weapon){
        double damage = baseDamage * BowDamageBonuses.INSTANCE.getDamage(weapon);

        if (damage != 0){
            return damage;
        }

        throw new IllegalStateException("Bow damage multiplier cannot be 0!");
    }
}
