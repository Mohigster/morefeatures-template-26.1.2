package com.mohigster.morefeatures.enchantment.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NullMarked;

public record ThunderEnchantmentEffect(int level) implements EnchantmentEntityEffect {
    public static final MapCodec<ThunderEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(
            inst-> inst.group(
                    Codec.INT.fieldOf("level")
                            .forGetter(ThunderEnchantmentEffect::level)
            ).apply(
                    inst,
                    ThunderEnchantmentEffect::new
            )
    );

    @NullMarked
    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        // One lightning bolt spawned per enchantment level. E.g. level 1 = 1 lightning spawned, level 2 = 2 lightning spawned etc. up to a maximum of 5
        // Too many lightning bolts would be laggy and two is the maximum level in survival, so capping at five is only noticable with commands anyway
        for (int i = 0; i < Math.min(enchantmentLevel, 5); i++) {
            EntityTypes.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), EntitySpawnReason.TRIGGERED);
        }
    }

    @NullMarked
    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
