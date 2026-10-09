package com.mohigster.morefeatures.block.custom.data.codec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Objects;

public record BonemealMorph(
        List<Block> variants,
        String bonemealType // This field represents the Type enum within BonemealableBlock.
) {
    // This method is an adapted version of getParticlePos in BonemealableBlock.
    public BlockPos particlePos(BlockPos pos) {
        return switch (this.bonemealType()) {
            case "neighbour_spreader", "neighbor_spreader" -> pos.above();
            case "grower" -> pos;
            default -> throw new IllegalStateException("Unexpected value: " + this.bonemealType());
        };
    }
    
    public String bonemealType() {
        if (this.bonemealTypeIsValid()) {
            return this.bonemealType;
        }
        throw new IllegalStateException("Invalid bonemeal type");
    }
    
    private boolean bonemealTypeIsValid() {
        Objects.requireNonNull(this.bonemealType);

        return Objects.equals(this.bonemealType, "neighbour_spreader")
                || Objects.equals(this.bonemealType, "neighbor_spreader")
                || Objects.equals(this.bonemealType, "grower");
    }

    public static final Codec<BonemealMorph> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    BuiltInRegistries.BLOCK.byNameCodec().listOf().fieldOf("variants")
                            .forGetter(BonemealMorph::variants),
                    Codec.STRING.fieldOf("bonemeal_type")
                            .forGetter(BonemealMorph::bonemealType)
            ).apply(
                    inst,
                    BonemealMorph::new
            )
    );
}
