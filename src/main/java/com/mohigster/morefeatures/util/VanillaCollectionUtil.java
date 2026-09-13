package com.mohigster.morefeatures.util;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.references.BlockItemId;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperCollection;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.mohigster.morefeatures.block.MFBlocks.BLOCKS;
import static com.mohigster.morefeatures.block.MFBlocks.registerBlockItem;
import static net.minecraft.world.level.block.WeatheringCopperCollection.zipMap;

public class VanillaCollectionUtil {
    public static WeatheringCopperCollection<DeferredBlock<Block>> registerDeferredCopper(
            WeatheringCopperCollection<BlockItemId> ids,
            BiFunction<WeatheringCopper.WeatherState, BlockBehaviour.Properties, ? extends Block> weatheringFactory,
            BiFunction<WeatheringCopper.WeatherState, BlockBehaviour.Properties, ? extends Block> waxedFactory,
            Function<WeatheringCopper.WeatherState, BlockBehaviour.Properties> propertiesSupplier) {

        return ids.apply(
                weatheringIds -> zipMap(
                        WeatheringCopperCollection.STATES,
                        weatheringIds,
                        (state, id) -> {
                            String name = id.block().identifier().getPath();
                            BlockBehaviour.Properties props = propertiesSupplier.apply(state).setId(id.block());

                            DeferredBlock<Block> toReturn = BLOCKS.register(name, () -> weatheringFactory.apply(state, props));

                            registerBlockItem(id, toReturn);

                            return toReturn;
                        }
                ),
                waxedIds -> zipMap(
                        WeatheringCopperCollection.STATES,
                        waxedIds,
                        (state, id) -> {
                            String name = id.block().identifier().getPath();
                            if (MFIdentifier.isNotMfNamespace(id.block().identifier())){
                                throw new IllegalStateException("Failed to register " + name + " within the " + ids.weathering().unaffected().block().identifier().getPath() + " copper block set. ID must be within the More Features namespace!");
                            }
                            BlockBehaviour.Properties props = propertiesSupplier.apply(state).setId(id.block());

                            DeferredBlock<Block> toReturn = BLOCKS.register(name, () -> waxedFactory.apply(state, props));

                            registerBlockItem(id, toReturn);

                            return toReturn;
                        }
                )
        );
    }

    public static ColorCollection<DeferredBlock<Block>> registerDeferredColoured(
            ColorCollection<BlockItemId> ids,
            Function<BlockBehaviour.Properties, ? extends Block> factory,
            Function<DyeColor, BlockBehaviour.Properties> propertiesSupplier){

        return ColorCollection.zipMap(
                ColorCollection.VALUES,
                ids,
                (color, id) -> {
                    String name = id.block().identifier().getPath();
                    if (MFIdentifier.isNotMfNamespace(id.block().identifier())){
                        throw new IllegalStateException("Failed to register " + name + " within a coloured block set. ID must be within the More Features namespace!");
                    }
                    BlockBehaviour.Properties props = propertiesSupplier.apply(color).setId(id.block());
                    DeferredBlock<Block> block = BLOCKS.register(name, () -> factory.apply(props));

                    registerBlockItem(id, block);

                    return block;
                }
        );
    }
}
