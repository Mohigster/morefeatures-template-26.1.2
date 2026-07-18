package com.mohigster.morefeatures.worldgen.biome;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.worldgen.noise.MFNoises;
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
    private static final RuleSource AIR = makeStateRule(Blocks.AIR);
    private static final RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final RuleSource WHITE_TERRACOTTA = makeStateRule(Blocks.DYED_TERRACOTTA.white());
    private static final RuleSource ORANGE_TERRACOTTA = makeStateRule(Blocks.DYED_TERRACOTTA.orange());
    private static final RuleSource TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
    private static final RuleSource RED_SAND = makeStateRule(Blocks.RED_SAND);
    private static final RuleSource RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
    private static final RuleSource STONE = makeStateRule(Blocks.STONE);
    private static final RuleSource DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final RuleSource PODZOL = makeStateRule(Blocks.PODZOL);
    private static final RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final RuleSource MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final RuleSource CALCITE = makeStateRule(Blocks.CALCITE);
    private static final RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final RuleSource SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final RuleSource PACKED_ICE = makeStateRule(Blocks.PACKED_ICE);
    private static final RuleSource BLUE_ICE = makeStateRule(Blocks.BLUE_ICE);
    private static final RuleSource SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final RuleSource MUD = makeStateRule(Blocks.MUD);
    private static final RuleSource POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
    private static final RuleSource ICE = makeStateRule(Blocks.ICE);
    private static final RuleSource WATER = makeStateRule(Blocks.WATER);
    private static final RuleSource LAVA = makeStateRule(Blocks.LAVA);
    private static final RuleSource NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final RuleSource SOUL_SAND = makeStateRule(Blocks.SOUL_SAND);
    private static final RuleSource SOUL_SOIL = makeStateRule(Blocks.SOUL_SOIL);
    private static final RuleSource BASALT = makeStateRule(Blocks.BASALT);
    private static final RuleSource BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final RuleSource WARPED_WART_BLOCK = makeStateRule(Blocks.WARPED_WART_BLOCK);
    private static final RuleSource WARPED_NYLIUM = makeStateRule(Blocks.WARPED_NYLIUM);
    private static final RuleSource NETHER_WART_BLOCK = makeStateRule(Blocks.NETHER_WART_BLOCK);
    private static final RuleSource CRIMSON_NYLIUM = makeStateRule(Blocks.CRIMSON_NYLIUM);
    private static final RuleSource CINNABAR = makeStateRule(Blocks.CINNABAR);
    private static final RuleSource SULFUR = makeStateRule(Blocks.SULFUR);

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



    // Everything beneath here are fixers. Something abut TerraBlender's update to 26.2 broke stuff, this is all to fix it.
    // The end is fine because its only vanilla rule is to put End Stone everywhere. But the overworld and nether both get fixers.


    public static RuleSource fixOverworldRules(HolderGetter<Biome> biomes){
        return overworldRulesToApply(biomes, true, false, true);
    }

    private static RuleSource overworldRulesToApply(HolderGetter<Biome> biomes, boolean doPreliminarySurfaceCheck, boolean bedrockRoof, boolean bedrockFloor) {
        ConditionSource woodedBadlandsTop = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(97), 2);
        ConditionSource badlandsTop = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0);
        ConditionSource badlandsHeightCondition = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1);
        ConditionSource badlandsMid = SurfaceRules.yStartCheck(VerticalAnchor.absolute(74), 1);
        ConditionSource mangroveSwampPuddleLevel = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), 0);
        ConditionSource swampPuddleLevel = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        ConditionSource aboveOverworldSeaLevel = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        ConditionSource notUnderwater = SurfaceRules.waterBlockCheck(-1, 0);
        ConditionSource aboveWater = SurfaceRules.waterBlockCheck(0, 0);
        ConditionSource notUnderDeepWater = SurfaceRules.waterStartCheck(-6, -1);
        ConditionSource hole = SurfaceRules.hole();
        ConditionSource frozenOcean = SurfaceRules.isBiome(biomes, Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN);
        ConditionSource steep = SurfaceRules.steep();
        RuleSource grassOrDirtIfUnderwater = SurfaceRules.sequence(SurfaceRules.ifTrue(aboveWater, GRASS_BLOCK), DIRT);
        RuleSource sandOrSandstoneIfCeiling = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE), SAND);
        RuleSource gravelOrStoneIfCeiling = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), GRAVEL);
        ConditionSource biomesWithSandAndSandstone = SurfaceRules.isBiome(biomes, Biomes.WARM_OCEAN, Biomes.BEACH, Biomes.SNOWY_BEACH);
        ConditionSource biomesWithSandAndVeryDeepSandstone = SurfaceRules.isBiome(biomes, Biomes.DESERT);
        RuleSource sulfurCaveBands = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, -0.4F, -0.1F), CINNABAR),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.0, 0.4F), SULFUR),
                SurfaceRules.ifTrue(SurfaceRules.noiseCondition3d(Noises.SULFUR_CAVE_GRADIENT, 0.4F), CINNABAR)
        );
        RuleSource commonSurfaceAndUnderRules = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.STONY_PEAKS),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.CALCITE, -0.0125, 0.0125), CALCITE), STONE)
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.STONY_SHORE),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.GRAVEL, -0.05, 0.05), gravelOrStoneIfCeiling), STONE)
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.WINDSWEPT_HILLS), SurfaceRules.ifTrue(surfaceNoiseAbove(1.0), STONE)),
                SurfaceRules.ifTrue(biomesWithSandAndSandstone, sandOrSandstoneIfCeiling),
                SurfaceRules.ifTrue(biomesWithSandAndVeryDeepSandstone, sandOrSandstoneIfCeiling),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.DRIPSTONE_CAVES), STONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.SULFUR_CAVES), SurfaceRules.sequence(sulfurCaveBands, STONE))
        );
        RuleSource powderSnowUnderRule = SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition2d(Noises.POWDER_SNOW, 0.45, 0.58), SurfaceRules.ifTrue(aboveWater, POWDER_SNOW)
        );
        RuleSource powderSnowSurfaceRule = SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition2d(Noises.POWDER_SNOW, 0.35, 0.6), SurfaceRules.ifTrue(aboveWater, POWDER_SNOW)
        );
        RuleSource biomeUnderSurfaceRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.FROZEN_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(steep, PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.PACKED_ICE, -0.5, 0.2), PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.ICE, -0.0625, 0.025), ICE),
                                SurfaceRules.ifTrue(aboveWater, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.SNOWY_SLOPES),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(steep, STONE), powderSnowUnderRule, SurfaceRules.ifTrue(aboveWater, SNOW_BLOCK))
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.JAGGED_PEAKS), STONE),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.GROVE), SurfaceRules.sequence(powderSnowUnderRule, DIRT)),
                commonSurfaceAndUnderRules,
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.WINDSWEPT_SAVANNA), SurfaceRules.ifTrue(surfaceNoiseAbove(1.75), STONE)),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.WINDSWEPT_GRAVELLY_HILLS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(2.0), gravelOrStoneIfCeiling),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.0), STONE),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0), DIRT),
                                gravelOrStoneIfCeiling
                        )
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.MANGROVE_SWAMP), MUD),
                DIRT
        );
        RuleSource biomeSurfaceRule = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.FROZEN_PEAKS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(steep, PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.PACKED_ICE, 0.0, 0.2), PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.ICE, 0.0, 0.025), ICE),
                                SurfaceRules.ifTrue(aboveWater, SNOW_BLOCK)
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.SNOWY_SLOPES),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(steep, STONE), powderSnowSurfaceRule, SurfaceRules.ifTrue(aboveWater, SNOW_BLOCK))
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.JAGGED_PEAKS),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(steep, STONE), SurfaceRules.ifTrue(aboveWater, SNOW_BLOCK))
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.GROVE), SurfaceRules.sequence(powderSnowSurfaceRule, SurfaceRules.ifTrue(aboveWater, SNOW_BLOCK))
                ),
                commonSurfaceAndUnderRules,
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.WINDSWEPT_SAVANNA),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.75), STONE), SurfaceRules.ifTrue(surfaceNoiseAbove(-0.5), COARSE_DIRT))
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.WINDSWEPT_GRAVELLY_HILLS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseAbove(2.0), gravelOrStoneIfCeiling),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(1.0), STONE),
                                SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0), grassOrDirtIfUnderwater),
                                gravelOrStoneIfCeiling
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseAbove(1.75), COARSE_DIRT), SurfaceRules.ifTrue(surfaceNoiseAbove(-0.95), PODZOL))
                ),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.ICE_SPIKES), SurfaceRules.ifTrue(aboveWater, SNOW_BLOCK)),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.MANGROVE_SWAMP), MUD),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.MUSHROOM_FIELDS), MYCELIUM),
                grassOrDirtIfUnderwater
        );
        ConditionSource clayBand1 = SurfaceRules.noiseCondition2d(Noises.SURFACE, -0.909, -0.5454);
        ConditionSource clayBand2 = SurfaceRules.noiseCondition2d(Noises.SURFACE, -0.1818, 0.1818);
        ConditionSource clayBand3 = SurfaceRules.noiseCondition2d(Noises.SURFACE, 0.5454, 0.909);
        RuleSource mainRuleCloseToSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(biomes, Biomes.WOODED_BADLANDS),
                                        SurfaceRules.ifTrue(
                                                woodedBadlandsTop,
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(clayBand1, COARSE_DIRT),
                                                        SurfaceRules.ifTrue(clayBand2, COARSE_DIRT),
                                                        SurfaceRules.ifTrue(clayBand3, COARSE_DIRT),
                                                        grassOrDirtIfUnderwater
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(biomes, Biomes.SWAMP),
                                        SurfaceRules.ifTrue(
                                                swampPuddleLevel,
                                                SurfaceRules.ifTrue(SurfaceRules.not(aboveOverworldSeaLevel), SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, 0.0), WATER))
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(biomes, Biomes.MANGROVE_SWAMP),
                                        SurfaceRules.ifTrue(
                                                mangroveSwampPuddleLevel,
                                                SurfaceRules.ifTrue(SurfaceRules.not(aboveOverworldSeaLevel), SurfaceRules.ifTrue(SurfaceRules.noiseCondition2d(Noises.SWAMP, 0.0), WATER))
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(badlandsTop, ORANGE_TERRACOTTA),
                                                SurfaceRules.ifTrue(
                                                        badlandsMid,
                                                        SurfaceRules.sequence(
                                                                SurfaceRules.ifTrue(clayBand1, TERRACOTTA),
                                                                SurfaceRules.ifTrue(clayBand2, TERRACOTTA),
                                                                SurfaceRules.ifTrue(clayBand3, TERRACOTTA),
                                                                SurfaceRules.bandlands()
                                                        )
                                                ),
                                                SurfaceRules.ifTrue(notUnderwater, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RED_SANDSTONE), RED_SAND)),
                                                SurfaceRules.ifTrue(SurfaceRules.not(hole), ORANGE_TERRACOTTA),
                                                SurfaceRules.ifTrue(notUnderDeepWater, WHITE_TERRACOTTA),
                                                gravelOrStoneIfCeiling
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        badlandsHeightCondition,
                                        SurfaceRules.sequence(
                                                SurfaceRules.ifTrue(aboveOverworldSeaLevel, SurfaceRules.ifTrue(SurfaceRules.not(badlandsMid), ORANGE_TERRACOTTA)),
                                                SurfaceRules.bandlands()
                                        )
                                ),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.ifTrue(notUnderDeepWater, WHITE_TERRACOTTA))
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(
                                notUnderwater,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                frozenOcean,
                                                SurfaceRules.ifTrue(
                                                        hole, SurfaceRules.sequence(SurfaceRules.ifTrue(aboveWater, AIR), SurfaceRules.ifTrue(SurfaceRules.temperature(), ICE), WATER)
                                                )
                                        ),
                                        biomeSurfaceRule
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        notUnderDeepWater,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(frozenOcean, SurfaceRules.ifTrue(hole, WATER))),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, biomeUnderSurfaceRule),
                                SurfaceRules.ifTrue(biomesWithSandAndSandstone, SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, SANDSTONE)),
                                SurfaceRules.ifTrue(biomesWithSandAndVeryDeepSandstone, SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, SANDSTONE))
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS), STONE),
                                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN), sandOrSandstoneIfCeiling),
                                gravelOrStoneIfCeiling
                        )
                )
        );
        Builder<RuleSource> builder = ImmutableList.builder();
        if (bedrockRoof) {
            builder.add(
                    SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK)
            );
        }

        if (bedrockFloor) {
            builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK));
        }

        RuleSource ruleAbovePreliminarySurface = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), mainRuleCloseToSurface);
        builder.add(doPreliminarySurfaceCheck ? ruleAbovePreliminarySurface : mainRuleCloseToSurface);
        builder.add(SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, Biomes.SULFUR_CAVES), sulfurCaveBands));
        builder.add(SurfaceRules.ifTrue(SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8)), DEEPSLATE));
        return SurfaceRules.sequence((RuleSource[])builder.build().toArray(RuleSource[]::new));
    }

    public static SurfaceRules.RuleSource fixNetherRules(HolderGetter<Biome> biomes) {
        ConditionSource aboveNetherLavaLevel = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(31), 0);
        ConditionSource aboveNetherLavaSurface = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(32), 0);
        ConditionSource netherBandAroundLavaLevelBottom = SurfaceRules.yStartCheck(VerticalAnchor.absolute(30), 0);
        ConditionSource netherBandAroundLavaLevelTop = SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(35), 0));
        ConditionSource closeToCeiling = SurfaceRules.yBlockCheck(VerticalAnchor.belowTop(5), 0);
        ConditionSource hole = SurfaceRules.hole();
        ConditionSource soulSandLayer = SurfaceRules.noiseCondition2d(Noises.SOUL_SAND_LAYER, -0.012);
        ConditionSource gravelLayer = SurfaceRules.noiseCondition2d(Noises.GRAVEL_LAYER, -0.012);
        ConditionSource patch = SurfaceRules.noiseCondition2d(Noises.PATCH, -0.012);
        ConditionSource netherrack = SurfaceRules.noiseCondition2d(Noises.NETHERRACK, 0.54);
        ConditionSource netherWart = SurfaceRules.noiseCondition2d(Noises.NETHER_WART, 1.17);
        ConditionSource netherStateSelector = SurfaceRules.noiseCondition2d(Noises.NETHER_STATE_SELECTOR, 0.0);
        RuleSource gravelPatch = SurfaceRules.ifTrue(
                patch, SurfaceRules.ifTrue(netherBandAroundLavaLevelBottom, SurfaceRules.ifTrue(netherBandAroundLavaLevelTop, GRAVEL))
        );
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),
                SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),
                SurfaceRules.ifTrue(closeToCeiling, NETHERRACK),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.BASALT_DELTAS),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, BASALT),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(gravelPatch, SurfaceRules.ifTrue(netherStateSelector, BASALT), BLACKSTONE))
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.SOUL_SAND_VALLEY),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, SurfaceRules.sequence(SurfaceRules.ifTrue(netherStateSelector, SOUL_SAND), SOUL_SOIL)),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.sequence(gravelPatch, SurfaceRules.ifTrue(netherStateSelector, SOUL_SAND), SOUL_SOIL))
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.not(aboveNetherLavaSurface), SurfaceRules.ifTrue(hole, LAVA)),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(biomes, Biomes.WARPED_FOREST),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(netherrack),
                                                SurfaceRules.ifTrue(aboveNetherLavaLevel, SurfaceRules.sequence(SurfaceRules.ifTrue(netherWart, WARPED_WART_BLOCK), WARPED_NYLIUM))
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.isBiome(biomes, Biomes.CRIMSON_FOREST),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.not(netherrack),
                                                SurfaceRules.ifTrue(aboveNetherLavaLevel, SurfaceRules.sequence(SurfaceRules.ifTrue(netherWart, NETHER_WART_BLOCK), CRIMSON_NYLIUM))
                                        )
                                )
                        )
                ),
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(biomes, Biomes.NETHER_WASTES),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(
                                        SurfaceRules.UNDER_FLOOR,
                                        SurfaceRules.ifTrue(
                                                soulSandLayer,
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.not(hole),
                                                                SurfaceRules.ifTrue(netherBandAroundLavaLevelBottom, SurfaceRules.ifTrue(netherBandAroundLavaLevelTop, SOUL_SAND))
                                                        ),
                                                        NETHERRACK
                                                )
                                        )
                                ),
                                SurfaceRules.ifTrue(
                                        SurfaceRules.ON_FLOOR,
                                        SurfaceRules.ifTrue(
                                                aboveNetherLavaLevel,
                                                SurfaceRules.ifTrue(
                                                        netherBandAroundLavaLevelTop,
                                                        SurfaceRules.ifTrue(
                                                                gravelLayer,
                                                                SurfaceRules.sequence(SurfaceRules.ifTrue(aboveNetherLavaSurface, GRAVEL), SurfaceRules.ifTrue(SurfaceRules.not(hole), GRAVEL))
                                                        )
                                                )
                                        )
                                )
                        )
                ),
                NETHERRACK
        );
    }

    private static ConditionSource surfaceNoiseAbove(final double threshold) {
        return SurfaceRules.noiseCondition2d(Noises.SURFACE, threshold / 8.25, Double.MAX_VALUE);
    }

    private static RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
