package com.mohigster.morefeatures.portal.frame;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public sealed interface PortalFrame permits PortalFrame.Simple, PortalFrame.StateSensitive, PortalFrame.Empty {
    Codec<PortalFrame> CODEC = Codec.BOOL.dispatch("state_sensitive",
            PortalFrame::stateSensitive, PortalFrame::codec);

    boolean stateSensitive();

    static PortalFrame empty() {
        return Empty.INSTANCE;
    }

    default boolean isEmpty() {
        return this == empty();
    }

    boolean isAir();

    boolean is(Block block);

    @SuppressWarnings("unchecked")
    private static <A extends PortalFrame> MapCodec<A> codec(boolean stateSensitive) {
        if (stateSensitive) {
            return (MapCodec<A>) StateSensitive.CODEC;
        } else {
            return (MapCodec<A>) Simple.CODEC;
        }
    }

    final class Simple implements PortalFrame {
        private final Block block;

        private Simple(Block block) {
            this.block = block;
        }

        public Block block() {
            return this.block;
        }

        public static final MapCodec<Simple> CODEC = RecordCodecBuilder.mapCodec(
                inst -> inst.group(
                        BuiltInRegistries.BLOCK.byNameCodec().fieldOf("frame")
                                .forGetter(Simple::block)
                ).apply(
                        inst,
                        Simple::new
                )
        );

        @Override
        public boolean stateSensitive() {
            return false;
        }

        @Override
        public boolean isAir() {
            return this.block().defaultBlockState().isAir();
        }

        @Override
        public boolean is(Block block) {
            return this.block().equals(block);
        }
    }

    final class StateSensitive implements PortalFrame {
        private final BlockState state;

        private StateSensitive(BlockState state) {
            this.state = state;
        }

        public BlockState state() {
            return this.state;
        }

        public static final MapCodec<StateSensitive> CODEC = RecordCodecBuilder.mapCodec(
                inst -> inst.group(
                        BlockState.CODEC.fieldOf("frame_state")
                                .forGetter(StateSensitive::state)
                ).apply(
                        inst,
                        StateSensitive::new
                )
        );

        @Override
        public boolean stateSensitive() {
            return true;
        }

        @Override
        public boolean isAir() {
            return this.state().isAir();
        }

        @Override
        public boolean is(Block block) {
            return this.state().is(block);
        }
    }

    final class Empty implements PortalFrame {
        private static int numberOfEmptyInstances = 0;
        // This is the only permitted instance
        private static final Empty INSTANCE = new Empty();

        // Private constructor to hide default constructor
        private Empty() {
            numberOfEmptyInstances++;
            // Ensures that a second empty instance cannot be created
            if (numberOfEmptyInstances > 1) {
                throw new IllegalStateException();
            }
        }

        @Override
        public boolean stateSensitive() {
            return false;
        }

        @Override
        public boolean isAir() {
            return true;
        }

        @Override
        public boolean is(Block block) {
            return false;
        }
    }
}