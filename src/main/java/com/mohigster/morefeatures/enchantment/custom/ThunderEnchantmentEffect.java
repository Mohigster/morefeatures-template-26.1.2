package com.mohigster.morefeatures.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record ThunderEnchantmentEffect(int level) implements EnchantmentEntityEffect {
    public static final MapCodec<ThunderEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Codec.INT.fieldOf("level").forGetter(ThunderEnchantmentEffect::level)
            ).apply(instance, ThunderEnchantmentEffect::new));

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if(enchantmentLevel == 1) {
            EntityTypes.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), EntitySpawnReason.TRIGGERED);
        }

        if(enchantmentLevel == 2) {
            EntityTypes.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), EntitySpawnReason.TRIGGERED);
            EntityTypes.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), EntitySpawnReason.TRIGGERED);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
