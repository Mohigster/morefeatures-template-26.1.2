package com.mohigster.morefeatures.data.component;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.util.MFExtraCodecs;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MFDataComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MoreFeatures.MODID);

    public static final Supplier<DataComponentType<Integer>> COMPRESSOR_FUEL_VALUE =
            DATA_COMPONENT_TYPES.register("compressor_fuel_value", () -> DataComponentType.<Integer>builder()
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
                    .build());

    public static final Supplier<DataComponentType<Double>> BOW_DAMAGE_BONUS =
            DATA_COMPONENT_TYPES.register("damage_bonus", () -> DataComponentType.<Double>builder()
                    .persistent(MFExtraCodecs.POSITIVE_DOUBLE)
                    .networkSynchronized(ByteBufCodecs.DOUBLE)
                    .build());

    public static final Supplier<DataComponentType<Double>> ELYTRA_SPEED_BOOST =
            DATA_COMPONENT_TYPES.register("speed_boost", () -> DataComponentType.<Double>builder()
                    .persistent(MFExtraCodecs.POSITIVE_DOUBLE)
                    .networkSynchronized(ByteBufCodecs.DOUBLE)
                    .build());

    public static void register(IEventBus modEventBus) {
        DATA_COMPONENT_TYPES.register(modEventBus);
        MoreFeatures.LOGGER.info("Mod Data Components registered -> Performed by: " + MoreFeatures.MODID);
    }
}
