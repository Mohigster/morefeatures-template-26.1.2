package com.mohigster.morefeatures.block.entity;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.block.entity.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MoreFeatures.MODID);

    public static final Supplier<BlockEntityType<CompressorBlockEntity>> COMPRESSOR_BE =
            BLOCK_ENTITIES.register("compressor_be", () -> new BlockEntityType<>(
                    CompressorBlockEntity::new, ModBlocks.COMPRESSOR_BLOCK.get()));

    public static final Supplier<BlockEntityType<ModSignBlockEntity>> MOD_SIGN_BE =
            BLOCK_ENTITIES.register("mod_sign_be", () -> new BlockEntityType<>(
                    ModSignBlockEntity::new,
                    ModBlocks.BLOODWOOD_SIGN.get(),
                    ModBlocks.BLOODWOOD_WALL_SIGN.get(),
                    ModBlocks.TAINTED_SIGN.get(),
                    ModBlocks.TAINTED_WALL_SIGN.get(),
                    ModBlocks.PALM_SIGN.get(),
                    ModBlocks.PALM_WALL_SIGN.get(),
                    ModBlocks.DECREPIT_SIGN.get(),
                    ModBlocks.DECREPIT_WALL_SIGN.get(),
                    ModBlocks.PALLID_SIGN.get(),
                    ModBlocks.PALLID_WALL_SIGN.get()
            ));

    public static final Supplier<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN_BE =
            BLOCK_ENTITIES.register("mod_hanging_sign_be", () -> new BlockEntityType<>(
                    ModHangingSignBlockEntity::new,
                    ModBlocks.BLOODWOOD_HANGING_SIGN.get(),
                    ModBlocks.BLOODWOOD_WALL_HANGING_SIGN.get(),
                    ModBlocks.TAINTED_HANGING_SIGN.get(),
                    ModBlocks.TAINTED_WALL_HANGING_SIGN.get(),
                    ModBlocks.PALM_HANGING_SIGN.get(),
                    ModBlocks.PALM_WALL_HANGING_SIGN.get(),
                    ModBlocks.DECREPIT_HANGING_SIGN.get(),
                    ModBlocks.DECREPIT_WALL_HANGING_SIGN.get(),
                    ModBlocks.PALLID_HANGING_SIGN.get(),
                    ModBlocks.PALLID_WALL_HANGING_SIGN.get()
            ));

    public static final Supplier<BlockEntityType<ModShelfBlockEntity>> MOD_SHELF_BE =
            BLOCK_ENTITIES.register("mod_shelf_be", () -> new BlockEntityType<>(
                    ModShelfBlockEntity::new, ModBlocks.PALM_SHELF.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
