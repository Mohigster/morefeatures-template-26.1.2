package com.mohigster.morefeatures.portal.key;

import com.mohigster.morefeatures.portal.key.type.PortalKeyType;
import com.mohigster.morefeatures.portal.key.type.PortalKeyTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

public record BlockKey(Block block) implements PortalKey {
    public static final MapCodec<BlockKey> CODEC = RecordCodecBuilder.mapCodec(
            inst -> inst.group(
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block")
                            .forGetter(BlockKey::block)
            ).apply(
                    inst,
                    BlockKey::new
            )
    );

    @Override
    public @NonNull String toString() {
        Identifier id = BuiltInRegistries.BLOCK.getKey(this.block());
        return "BlockKey[" + Objects.requireNonNull(id) + "]";
    }

    @Override
    public PortalKeyType<BlockKey> type() {
        return PortalKeyTypes.BLOCK.get();
    }
}
