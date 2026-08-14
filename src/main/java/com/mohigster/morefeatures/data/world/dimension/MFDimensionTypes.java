package com.mohigster.morefeatures.data.world.dimension;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import com.mohigster.morefeatures.data.resources.references.dimension.MFDimensionTypeIds;
import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.timeline.Timeline;
import org.jspecify.annotations.NonNull;

import java.util.Optional;

public class MFDimensionTypes {

    public static void bootstrap(BootstrapContext<DimensionType> context) {
        var timelines = context.lookup(Registries.TIMELINE);
        var clocks = context.lookup(Registries.WORLD_CLOCK);
        var blocks = context.lookup(Registries.BLOCK);

        register(context, MFDimensionTypeIds.EVILDIM, false, true,
                false, 0.5D, 0, 256,
                256, getInfiniburn(blocks, MFBlockTags.INFINIBURN_EVIL), 1.0F,
                new DimensionType.MonsterSettings(ConstantInt.of(0), 0),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.builder()
                        .set(EnvironmentAttributes.FOG_COLOR, ARGB.color(255, 225, 20, 10))
                        .set(EnvironmentAttributes.SKY_COLOR, ARGB.color(255, 225, 20, 10))
                        .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, -4212331)
                        .set(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(155, 200, 31, 25)),
                timelines.getOrThrow(TimelineTags.IN_OVERWORLD),
                clocks.getOrThrow(WorldClocks.OVERWORLD));

        register(context, MFDimensionTypeIds.BOREALIS, true, false,
                true, 4D, 0, 256,
                256, getInfiniburn(blocks, MFBlockTags.INFINIBURN_BOREALIS),
                1.0F, new DimensionType.MonsterSettings(ConstantInt.of(0), 0),
                DimensionType.Skybox.NONE,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.builder()
                        .set(EnvironmentAttributes.FOG_COLOR, ARGB.color(166, 166, 255))
                        .set(EnvironmentAttributes.SKY_COLOR, ARGB.color( 166, 166, 255))
                        .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, ARGB.color(155, 155, 155, 245))
                        .set(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(255, 255, 255)),
                timelines.getOrThrow(TimelineTags.UNIVERSAL),
                null);
    }

    @SuppressWarnings("SameParameterValue")
    private static void register(
            BootstrapContext<DimensionType> context, ResourceKey<DimensionType> dimType,
            boolean fixedTime, boolean skylight, boolean ceiling, double coordScale,
            int minY, int height, int logHeight, HolderSet<Block> infiniburn,
            float ambientLight, DimensionType.MonsterSettings settings,
            DimensionType.Skybox skybox, CardinalLighting.Type lightingType,
            EnvironmentAttributeMap.Builder attributeBuilder,
            HolderSet<Timeline> timeline, Holder<WorldClock> defaultClock
    ) {
        context.register(dimType, new DimensionType(
                fixedTime, skylight, ceiling, false,
                coordScale, minY, height, logHeight, infiniburn,
                ambientLight, settings, skybox, lightingType,
                attributeBuilder.build(), timeline,
                Optional.ofNullable(defaultClock)
        ));
    }

    private static HolderSet.Named<Block> getInfiniburn(
            HolderGetter<Block> blockGetter,
            @NonNull TagKey<Block> tag
    ) {
        return blockGetter.getOrThrow(tag);
    }
}
