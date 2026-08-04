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

    public static final Supplier<BlockEntityType<TemporalDilatorBlockEntity>> TEMPORAL_DILATOR_BE =
            BLOCK_ENTITIES.register("temporal_dilator_be", () -> new BlockEntityType<>(
                    TemporalDilatorBlockEntity::new, MFBlocks.TEMPORAL_DILATOR.get()));

    public static final Supplier<BlockEntityType<MFSignBlockEntity>> MF_SIGN_BE =
            BLOCK_ENTITIES.register("mf_sign_be", () -> new BlockEntityType<>(
                    MFSignBlockEntity::new,
                    MFBlocks.AZURITE_SIGN.get(),
                    MFBlocks.AZURITE_WALL_SIGN.get(),
                    MFBlocks.FLUORITE_SIGN.get(),
                    MFBlocks.FLUORITE_WALL_SIGN.get(),
                    MFBlocks.WOODEN_SIGN.bloodwood().get(),
                    MFBlocks.WOODEN_WALL_SIGN.bloodwood().get(),
                    MFBlocks.WOODEN_SIGN.tainted().get(),
                    MFBlocks.WOODEN_WALL_SIGN.tainted().get(),
                    MFBlocks.WOODEN_SIGN.palm().get(),
                    MFBlocks.WOODEN_WALL_SIGN.palm().get(),
                    MFBlocks.WOODEN_SIGN.charred().get(),
                    MFBlocks.WOODEN_WALL_SIGN.charred().get(),
                    MFBlocks.WOODEN_SIGN.decrepit().get(),
                    MFBlocks.WOODEN_WALL_SIGN.decrepit().get(),
                    MFBlocks.WOODEN_SIGN.pallid().get(),
                    MFBlocks.WOODEN_WALL_SIGN.pallid().get()
            ));

    public static final Supplier<BlockEntityType<MFHangingSignBlockEntity>> MF_HANGING_SIGN_BE =
            BLOCK_ENTITIES.register("mf_hanging_sign_be", () -> new BlockEntityType<>(
                    MFHangingSignBlockEntity::new,
                    MFBlocks.AZURITE_HANGING_SIGN.get(),
                    MFBlocks.AZURITE_WALL_HANGING_SIGN.get(),
                    MFBlocks.FLUORITE_HANGING_SIGN.get(),
                    MFBlocks.FLUORITE_WALL_HANGING_SIGN.get(),
                    MFBlocks.WOODEN_HANGING_SIGN.bloodwood().get(),
                    MFBlocks.WOODEN_WALL_HANGING_SIGN.bloodwood().get(),
                    MFBlocks.WOODEN_HANGING_SIGN.tainted().get(),
                    MFBlocks.WOODEN_WALL_HANGING_SIGN.tainted().get(),
                    MFBlocks.WOODEN_HANGING_SIGN.palm().get(),
                    MFBlocks.WOODEN_WALL_HANGING_SIGN.palm().get(),
                    MFBlocks.WOODEN_HANGING_SIGN.charred().get(),
                    MFBlocks.WOODEN_WALL_HANGING_SIGN.charred().get(),
                    MFBlocks.WOODEN_HANGING_SIGN.decrepit().get(),
                    MFBlocks.WOODEN_WALL_HANGING_SIGN.decrepit().get(),
                    MFBlocks.WOODEN_HANGING_SIGN.pallid().get(),
                    MFBlocks.WOODEN_WALL_HANGING_SIGN.pallid().get()
            ));

    public static final Supplier<BlockEntityType<MFShelfBlockEntity>> MF_SHELF_BE =
            BLOCK_ENTITIES.register("mf_shelf_be", () -> new BlockEntityType<>(
                    MFShelfBlockEntity::new,
                    MFBlocks.AZURITE_SHELF.get(),
                    MFBlocks.FLUORITE_SHELF.get(),
                    MFBlocks.WOODEN_SHELF.bloodwood().get(),
                    MFBlocks.WOODEN_SHELF.tainted().get(),
                    MFBlocks.WOODEN_SHELF.palm().get(),
                    MFBlocks.WOODEN_SHELF.charred().get(),
                    MFBlocks.WOODEN_SHELF.decrepit().get(),
                    MFBlocks.WOODEN_SHELF.pallid().get()
            ));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Block Entities registered -> Performed by: " + MoreFeatures.MODID);
    }
}
