package com.mohigster.morefeatures.worldgen.tree.decorator;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MFTreeDecorators {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, MoreFeatures.MODID);

    public static final DeferredHolder<TreeDecoratorType<?>, TreeDecoratorType<TrunkLightDecorator>> TRUNK_LIGHT =
            TREE_DECORATORS.register("trunk_light",
                    () -> new TreeDecoratorType<>(TrunkLightDecorator.CODEC));

    public static void register(IEventBus modEventBus) {
        TREE_DECORATORS.register(modEventBus);
        MoreFeatures.LOGGER.info("Mod Tree Decorators registered -> Performed by: " + MoreFeatures.MODID);
    }
}
