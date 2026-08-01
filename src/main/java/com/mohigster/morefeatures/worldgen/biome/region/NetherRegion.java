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

public class NetherRegion extends Region {
    public NetherRegion(Identifier name, int weight) {
        super(name, RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        new ParameterUtils.ParameterPointListBuilder()
                .temperature(Climate.Parameter.span(0.5F, 0.9F))
                .humidity(ParameterUtils.Humidity.span(ParameterUtils.Humidity.ARID, ParameterUtils.Humidity.DRY))
                .continentalness(ParameterUtils.Continentalness.FAR_INLAND)
                .erosion(Climate.Parameter.span(0.4F, 0.6F))
                .depth(ParameterUtils.Depth.SURFACE)
                .weirdness(Climate.Parameter.span(0.4F, 0.6F))
                .build().forEach(point -> builder.add(point, MFBiomes.CHARRED_FOREST));
    }
}
