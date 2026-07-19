package com.mohigster.morefeatures.enchantment;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.enchantment.custom.ThunderEnchantmentEffect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MFEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, MoreFeatures.MODID);

    @SuppressWarnings("unused")
    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> THUNDER =
            ENTITY_ENCHANTMENT_EFFECTS.register("thunder", () -> ThunderEnchantmentEffect.CODEC);




    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Enchantment Effects registered -> Performed by: " + MoreFeatures.MODID);
    }
}
