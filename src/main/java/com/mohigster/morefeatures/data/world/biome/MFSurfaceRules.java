package com.mohigster.morefeatures.data.world.biome;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.data.world.noise.MFNoises;
import net.minecraft.core.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.SurfaceRules.*;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class MFSurfaceRules {

    // Surface Overworld biomes
    private static final RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final RuleSource PACKED_ICE = makeStateRule(Blocks.PACKED_ICE);
    private static final RuleSource BLUE_ICE = makeStateRule(Blocks.BLUE_ICE);
    private static final RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final RuleSource LAVA = makeStateRule(Blocks.LAVA);

    // Nether biomes
    private static final RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final RuleSource CHARRED_WART_BLOCK = makeStateRule(MFBlocks.CHARRED_WART_BLOCK.get());
    private static final RuleSource CHARRED_NYLIUM = makeStateRule(MFBlocks.CHARRED_NYLIUM.get());

    // End biomes
    private static final RuleSource OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final RuleSource PALLID_NULLIUM = makeStateRule(MFBlocks.PALLID_NULLIUM.get());
    private static final RuleSource DECREPIT_NULLIUM = makeStateRule(MFBlocks.DECREPIT_NULLIUM.get());

    public static RuleSource makeEndSurfaceRules(HolderGetter<Biome> biomes) {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, MFBiomes.END_ROT),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, OBSIDIAN),
                                OBSIDIAN
                        )),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, MFBiomes.DECREPIT_FOREST),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DECREPIT_NULLIUM)),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, MFBiomes.PALLID_FOREST),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, PALLID_NULLIUM))
        );
    }

    public static RuleSource makeCharredForestRules(HolderGetter<Biome> biomes) {
        ConditionSource aboveNetherLavaLevel = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0);
        ConditionSource netherrack = SurfaceRules.noiseCondition2d(Noises.NETHERRACK, 0.54);
        ConditionSource netherWart = SurfaceRules.noiseCondition2d(Noises.NETHER_WART, 1.17);
        ConditionSource closeToCeiling = SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0);
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),
                SurfaceRules.ifTrue(closeToCeiling, NETHERRACK),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.not(aboveNetherLavaLevel),
                                        SurfaceRules.ifTrue(SurfaceRules.hole(), LAVA)
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(biomes, MFBiomes.CHARRED_FOREST),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(netherrack),
                                                SurfaceRules.ifTrue(aboveNetherLavaLevel, SurfaceRules.sequence(SurfaceRules.ifTrue(netherWart, CHARRED_WART_BLOCK), CHARRED_NYLIUM))
                                        )
                                )
                        )
                )
        );
    }

    public static RuleSource makeIceCaveRules(HolderGetter<Biome> biomes) {
        RuleSource iceCaveBands = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition3d(MFNoises.ICE_CAVE_GRADIENT, -0.4F, -0.1F), PACKED_ICE),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition3d(MFNoises.ICE_CAVE_GRADIENT, 0.0, 0.4F), SNOW_BLOCK),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition3d(MFNoises.ICE_CAVE_GRADIENT, 0.4F), PACKED_ICE)
        );

        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(biomes, MFBiomes.ICE_CAVES),
                SurfaceRules.ifTrue(
                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(-50), 0),
                        SurfaceRules.sequence(
                                // Ceiling / Roof of the cave gets Blue Ice
                                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, BLUE_ICE),
                                // Floor and walls get Packed Ice
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, PACKED_ICE),

                                iceCaveBands
                        )
                )
        );
    }

    private static RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
