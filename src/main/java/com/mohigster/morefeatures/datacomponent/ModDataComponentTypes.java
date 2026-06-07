package com.mohigster.morefeatures.datacomponent;

import com.mohigster.morefeatures.MoreFeatures;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModDataComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, MoreFeatures.MODID);

    // This registers a data component that holds an Integer (the energy value)
    public static final Supplier<DataComponentType<Integer>> COMPRESSOR_FUEL_VALUE =
            DATA_COMPONENT_TYPES.register("compressor_fuel_value", () -> DataComponentType.<Integer>builder()
                    .persistent(Codec.INT) // Tells Minecraft how to save it to NBT / JSON
                    .networkSynchronized(ByteBufCodecs.VAR_INT) // Syncs it smoothly to the client
                    .build());
}
