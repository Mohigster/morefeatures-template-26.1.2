package com.mohigster.morefeatures.worldgen.biome;

import com.mohigster.morefeatures.block.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

import java.util.Optional;
import java.util.function.Supplier;

public class ModSurfaceRules {

    // Surface Overworld biomes
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.STONE);

    // Underground Overworld biomes
    private static final SurfaceRules.RuleSource PACKED_ICE = makeStateRule(Blocks.PACKED_ICE);
    private static final SurfaceRules.RuleSource BLUE_ICE = makeStateRule(Blocks.BLUE_ICE);

    // End biomes
    private static final SurfaceRules.RuleSource OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final SurfaceRules.RuleSource END_STONE = makeStateRule(Blocks.END_STONE);
    private static final SurfaceRules.RuleSource PALLID_NULLIUM = makeStateRule(ModBlocks.PALLID_NULLIUM.get());
    private static final SurfaceRules.RuleSource DECREPIT_NULLIUM = makeStateRule(ModBlocks.DECREPIT_NULLIUM.get());

    public static SurfaceRules.RuleSource makeEndSurfaceRules(HolderGetter<Biome> biomes) {
        // Specific biome rules (highest priority)
        return SurfaceRules.sequence(
                // Specific biome rules (highest priority)
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, ModBiomes.END_ROT),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, OBSIDIAN),
                                OBSIDIAN
                        )),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, ModBiomes.DECREPIT_FOREST),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, DECREPIT_NULLIUM)),

                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, ModBiomes.PALLID_FOREST),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, PALLID_NULLIUM))
        );
    }

    public static SurfaceRules.RuleSource makeBloodwoodForestRules(HolderGetter<Biome> biomes) {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, ModBiomes.BLOODWOOD_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT), STONE)),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK)
        );
    }

    public static SurfaceRules.RuleSource makeTaintedForestRules(HolderGetter<Biome> biomes) {
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(biomes, ModBiomes.TAINTED_FOREST),
                        SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT), STONE)),
                // Default to Dirt
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR,GRASS_BLOCK)
        );
    }

    public static SurfaceRules.RuleSource makeIceCaveRules(HolderGetter<Biome> biomes) {
        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(biomes, ModBiomes.ICE_CAVE),
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
