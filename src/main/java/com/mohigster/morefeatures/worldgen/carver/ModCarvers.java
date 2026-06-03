package com.mohigster.morefeatures.worldgen.carver;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantFloat;
import net.minecraft.util.valueproviders.TrapezoidFloat;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.*;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class ModCarvers {
    public static final ResourceKey<ConfiguredWorldCarver<?>> ICE_CAVE = createKey("ice_cave");
    public static final ResourceKey<ConfiguredWorldCarver<?>> ICE_CAVE_EXTRA_UNDERGROUND = createKey("ice_cave_extra_underground");
    public static final ResourceKey<ConfiguredWorldCarver<?>> ICE_CANYON = createKey("ice_canyon");

    public ModCarvers(){
    }

    private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        context.register(ICE_CAVE, WorldCarver.CAVE.configured(new CaveCarverConfiguration(0.15F, UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(180)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.absolute(-64), CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()), blocks.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES), UniformFloat.of(0.7F, 1.4F), UniformFloat.of(0.8F, 1.3F), UniformFloat.of(-1.0F, -0.4F))));
        context.register(ICE_CAVE_EXTRA_UNDERGROUND, WorldCarver.CAVE.configured(new CaveCarverConfiguration(0.07F, UniformHeight.of(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(47)), UniformFloat.of(0.1F, 0.9F), VerticalAnchor.absolute(-64), CarverDebugSettings.of(false, Blocks.OAK_BUTTON.defaultBlockState()), blocks.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES), UniformFloat.of(0.7F, 1.4F), UniformFloat.of(0.8F, 1.3F), UniformFloat.of(-1.0F, -0.4F))));
        context.register(ICE_CANYON, WorldCarver.CANYON.configured(new CanyonCarverConfiguration(0.01F, UniformHeight.of(VerticalAnchor.absolute(0), VerticalAnchor.absolute(67)), ConstantFloat.of(3.0F), VerticalAnchor.absolute(-64), CarverDebugSettings.of(false, Blocks.WARPED_BUTTON.defaultBlockState()), blocks.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES), UniformFloat.of(-0.125F, 0.125F), new CanyonCarverConfiguration.CanyonShapeConfiguration(UniformFloat.of(0.75F, 1.0F), TrapezoidFloat.of(0.0F, 6.0F, 2.0F), 3, UniformFloat.of(0.75F, 1.0F), 1.0F, 0.0F))));
    }
}
