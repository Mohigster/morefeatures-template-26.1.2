package com.mohigster.morefeatures.block.entity;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.entity.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MFBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MoreFeatures.MODID);

    public static final Supplier<BlockEntityType<CompressorBlockEntity>> COMPRESSOR_BE =
            BLOCK_ENTITIES.register("compressor_be", () -> new BlockEntityType<>(
                    CompressorBlockEntity::new, MFBlocks.COMPRESSOR_BLOCK.get()));

    public static final Supplier<BlockEntityType<MFSignBlockEntity>> MF_SIGN_BE =
            BLOCK_ENTITIES.register("mod_sign_be", () -> new BlockEntityType<>(
                    MFSignBlockEntity::new,
                    MFBlocks.AZURITE_SIGN.get(),
                    MFBlocks.AZURITE_WALL_SIGN.get(),
                    MFBlocks.BLOODWOOD_SIGN.get(),
                    MFBlocks.BLOODWOOD_WALL_SIGN.get(),
                    MFBlocks.TAINTED_SIGN.get(),
                    MFBlocks.TAINTED_WALL_SIGN.get(),
                    MFBlocks.PALM_SIGN.get(),
                    MFBlocks.PALM_WALL_SIGN.get(),
                    MFBlocks.DECREPIT_SIGN.get(),
                    MFBlocks.DECREPIT_WALL_SIGN.get(),
                    MFBlocks.PALLID_SIGN.get(),
                    MFBlocks.PALLID_WALL_SIGN.get()
            ));

    public static final Supplier<BlockEntityType<MFHangingSignBlockEntity>> MF_HANGING_SIGN_BE =
            BLOCK_ENTITIES.register("mod_hanging_sign_be", () -> new BlockEntityType<>(
                    MFHangingSignBlockEntity::new,
                    MFBlocks.AZURITE_HANGING_SIGN.get(),
                    MFBlocks.AZURITE_WALL_HANGING_SIGN.get(),
                    MFBlocks.BLOODWOOD_HANGING_SIGN.get(),
                    MFBlocks.BLOODWOOD_WALL_HANGING_SIGN.get(),
                    MFBlocks.TAINTED_HANGING_SIGN.get(),
                    MFBlocks.TAINTED_WALL_HANGING_SIGN.get(),
                    MFBlocks.PALM_HANGING_SIGN.get(),
                    MFBlocks.PALM_WALL_HANGING_SIGN.get(),
                    MFBlocks.DECREPIT_HANGING_SIGN.get(),
                    MFBlocks.DECREPIT_WALL_HANGING_SIGN.get(),
                    MFBlocks.PALLID_HANGING_SIGN.get(),
                    MFBlocks.PALLID_WALL_HANGING_SIGN.get()
            ));

    public static final Supplier<BlockEntityType<MFShelfBlockEntity>> MF_SHELF_BE =
            BLOCK_ENTITIES.register("mod_shelf_be", () -> new BlockEntityType<>(
                    MFShelfBlockEntity::new,
                    MFBlocks.BLOODWOOD_SHELF.get(),
                    MFBlocks.TAINTED_SHELF.get(),
                    MFBlocks.PALM_SHELF.get(),
                    MFBlocks.DECREPIT_SHELF.get(),
                    MFBlocks.PALLID_SHELF.get()
            ));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
