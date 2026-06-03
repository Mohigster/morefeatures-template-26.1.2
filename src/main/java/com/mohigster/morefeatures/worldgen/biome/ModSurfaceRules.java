package com.mohigster.morefeatures.worldgen.biome;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);

    private static final SurfaceRules.RuleSource PACKED_ICE = makeStateRule(Blocks.PACKED_ICE);
    private static final SurfaceRules.RuleSource BLUE_ICE = makeStateRule(Blocks.BLUE_ICE);

    private static final SurfaceRules.RuleSource OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final SurfaceRules.RuleSource END_STONE = makeStateRule(Blocks.END_STONE);

    public static SurfaceRules.RuleSource makeEndRotRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.END_ROT), OBSIDIAN),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, OBSIDIAN)
        );
    }

    public static SurfaceRules.RuleSource makeEndGrowthRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.END_GROWTH), END_STONE),
                // Default to end stone
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, END_STONE)
        );
    }

    public static SurfaceRules.RuleSource makeBloodwoodForestRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BLOODWOOD_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT), STONE)),
                // Default to Dirt
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK)
        );
    }

    public static SurfaceRules.RuleSource makeTaintedForestRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.TAINTED_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT), STONE)),
                // Default to Dirt
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,GRASS_BLOCK)
        );
    }

    public static SurfaceRules.RuleSource makeIceCaveRules() {
        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.ICE_CAVE),
                SurfaceRules.ifTrue(
                        SurfaceRules.yBlockCheck(VerticalAnchor.absolute(-58), 0),
                        SurfaceRules.sequence(

                                // Ceiling / Roof of the cave gets Blue Ice
                                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, BLUE_ICE),
                                // Floor and walls get Packed Ice
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, PACKED_ICE),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, PACKED_ICE),
                                // Default filler block inside this biome if it's not a direct floor/ceiling
                                PACKED_ICE
                        )
                )
        );
    }


    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
