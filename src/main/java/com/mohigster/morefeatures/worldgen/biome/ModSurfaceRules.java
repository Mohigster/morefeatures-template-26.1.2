package com.mohigster.morefeatures.worldgen.biome;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);
    public static SurfaceRules.RuleSource makeBloodwoodForestRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BLOODWOOD_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT), STONE)),
                // Default to Dirt
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DIRT)
        );
    }

    public static SurfaceRules.RuleSource makeTaintedForestRules() {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.TAINTED_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT), STONE)),
                // Default to Dirt
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DIRT)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
