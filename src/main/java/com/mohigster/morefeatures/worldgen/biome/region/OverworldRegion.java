package com.mohigster.morefeatures.worldgen.biome.region;

import com.mohigster.morefeatures.worldgen.biome.MFBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class OverworldRegion extends Region {

    // Custom biome parameters

    // Bloodwood and tainted forests should NEVER border each other. Their values (specifically their weirdness values) are set with this in mind

    private static final float BLOODWOOD_TEMP_MIN = 0.20F;
    private static final float BLOODWOOD_TEMP_MAX = 0.25F;
    private static final float BLOODWOOD_EROSION_MIN = 0.45F;
    private static final float BLOODWOOD_EROSION_MAX = 0.48F;
    private static final float BLOODWOOD_WEIRD_MIN = -0.6F;
    private static final float BLOODWOOD_WEIRD_MAX = -0.5F;

    private static final float TAINTED_TEMP_MIN = -0.25F;
    private static final float TAINTED_TEMP_MAX = -0.20F;
    private static final float TAINTED_EROSION_MIN = 0.65F;
    private static final float TAINTED_EROSION_MAX = 0.68F;
    private static final float TAINTED_WEIRD_MIN = 0.55F;
    private static final float TAINTED_WEIRD_MAX = 0.6F;

    private static final float ICE_CAVE_TEMP_MIN = -1.0F;
    private static final float ICE_CAVE_TEMP_MAX = -0.85F;
    private static final float ICE_CAVE_EROSION_MIN = -1.0F;
    private static final float ICE_CAVE_EROSION_MAX = -0.15F;
    private static final float ICE_CAVE_DEPTH_MIN = 0.75F;
    private static final float ICE_CAVE_DEPTH_MAX = 1.0F;
    private static final float ICE_CAVE_WEIRD_MIN_1 = -0.8F;
    private static final float ICE_CAVE_WEIRD_MIN_2 = 0.7F;
    private static final float ICE_CAVE_WEIRD_MAX_1 = -0.7F;
    private static final float ICE_CAVE_WEIRD_MAX_2 = 0.8F;

    public OverworldRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        new ParameterUtils.ParameterPointListBuilder()
                .temperature(Climate.Parameter.span(BLOODWOOD_TEMP_MIN, BLOODWOOD_TEMP_MAX))
                .humidity(ParameterUtils.Humidity.span(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY))
                .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                .erosion(Climate.Parameter.span(BLOODWOOD_EROSION_MIN, BLOODWOOD_EROSION_MAX))
                .depth(ParameterUtils.Depth.SURFACE)
                .weirdness(Climate.Parameter.span(BLOODWOOD_WEIRD_MIN, BLOODWOOD_WEIRD_MAX))
                .build().forEach(point -> builder.add(point, MFBiomes.BLOODWOOD_FOREST));

        new ParameterUtils.ParameterPointListBuilder()
                .temperature(Climate.Parameter.span(TAINTED_TEMP_MIN, TAINTED_TEMP_MAX))
                .humidity(ParameterUtils.Humidity.span(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY))
                .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                .erosion(Climate.Parameter.span(TAINTED_EROSION_MIN, TAINTED_EROSION_MAX))
                .depth(ParameterUtils.Depth.SURFACE)
                .weirdness(Climate.Parameter.span(TAINTED_WEIRD_MIN, TAINTED_WEIRD_MAX))
                .build().forEach(point -> builder.add(point, MFBiomes.TAINTED_FOREST));

        new ParameterUtils.ParameterPointListBuilder()
                .temperature(Climate.Parameter.span(ICE_CAVE_TEMP_MIN, ICE_CAVE_TEMP_MAX))
                .humidity(ParameterUtils.Humidity.NEUTRAL)
                .continentalness(ParameterUtils.Continentalness.span(
                        ParameterUtils.Continentalness.INLAND,
                        ParameterUtils.Continentalness.FAR_INLAND))
                .erosion(Climate.Parameter.span(ICE_CAVE_EROSION_MIN, ICE_CAVE_EROSION_MAX))
                .depth(Climate.Parameter.span(ICE_CAVE_DEPTH_MIN, ICE_CAVE_DEPTH_MAX))
                .weirdness(Climate.Parameter.span(ICE_CAVE_WEIRD_MIN_1, ICE_CAVE_WEIRD_MAX_1), Climate.Parameter.span(ICE_CAVE_WEIRD_MIN_2, ICE_CAVE_WEIRD_MAX_2))
                .build().forEach(point -> builder.add(point, MFBiomes.ICE_CAVES));

        // Add our points to the mapper
        builder.build().forEach(mapper);
    }
}
