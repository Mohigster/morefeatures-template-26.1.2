package com.mohigster.morefeatures.block.custom.portal.key;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

public class BlockKey extends PortalKey {
    public static final MapCodec<BlockKey> CODEC = RecordCodecBuilder.mapCodec(
            inst -> inst.group(
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block")
                            .forGetter(BlockKey::block)
            ).apply(
                    inst,
                    BlockKey::new
            )
    );

    private final Block block;

    private BlockKey(Block block) {
        this.block = block;
    }

    public Block block() {
        return this.block;
    }

    @Override
    public PortalKeyType<?> getType() {
        return PortalKeyTypes.BLOCK;
    }
}
