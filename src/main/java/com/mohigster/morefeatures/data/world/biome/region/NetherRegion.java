package com.mohigster.morefeatures.data.world.biome.region;

import com.mohigster.morefeatures.data.references.MFIdentifier;
import com.mohigster.morefeatures.data.world.biome.MFBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class NetherRegion extends Region {
    public NetherRegion(int weight) {
        super(MFIdentifier.withMfNamespace("nether"), RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        new ParameterUtils.ParameterPointListBuilder()
                .temperature(Climate.Parameter.span(0.5F, 1.0F))
                .humidity(ParameterUtils.Humidity.FULL_RANGE)
                .depth(ParameterUtils.Depth.FULL_RANGE)
                .build().forEach(point -> builder.add(point, MFBiomes.CHARRED_FOREST));

        builder.build().forEach(mapper);
    }
}
