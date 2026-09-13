package com.mohigster.morefeatures.block.custom.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;

import java.util.List;

public record BonemealMorph(
        List<Block> variants,
        String bonemealType // This field represents the Type enum within BonemealableBlock.
) {
    // This method is an adapted version of getParticlePos in BonemealableBlock.
    public BlockPos particlePos(BlockPos pos) {
        return switch (this.bonemealType) {
            case "neighbour_spreader" -> pos.above();
            case "grower" -> pos;
            default -> throw new MatchException(null, null);
        };
    }

    public static final Codec<BonemealMorph> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    BuiltInRegistries.BLOCK.byNameCodec().listOf().fieldOf("variants")
                            .forGetter(BonemealMorph::variants),
                    StringRepresentable.EnumCodec.STRING.fieldOf("bonemeal_type")
                            .forGetter(BonemealMorph::bonemealType)
            ).apply(
                    inst,
                    BonemealMorph::new
            )
    );
}
