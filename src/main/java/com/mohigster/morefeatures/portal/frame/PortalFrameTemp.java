package com.mohigster.morefeatures.portal.frame;


import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public record PortalFrameTemp(boolean stateSensitive, BlockState blockState) {

    public static final Codec<PortalFrameTemp> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    Codec.BOOL.optionalFieldOf("state_sensitive", false)
                            .forGetter(PortalFrameTemp::stateSensitive),
                    Codec.either(
                            BlockState.CODEC.fieldOf("block_state").codec(),
                            BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").codec()
                    ).fieldOf("frame").forGetter(config -> {
                        // Returns either the full BlockState or just the Block
                        if (config.stateSensitive()) {
                            return Either.left(config.blockState());
                        } else {
                            return Either.right(config.blockState().getBlock());
                        }
                    })
            ).apply(inst, (stateSensitive, either) -> {
                BlockState state = either.map(
                        fullState -> fullState,              // Left: BlockState provided directly
                        Block::defaultBlockState   // Right: Block provided, convert to default state
                );
                return new PortalFrameTemp(stateSensitive, state);
            })
    );
}
