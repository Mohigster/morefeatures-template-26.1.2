package com.mohigster.morefeatures.block.collection.gemstone;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public record GemstoneCollection<T>(
        T azurite,
        T fluorite
) {
    public static final GemstoneCollection<GemstoneType> GEMS = new GemstoneCollection<>(
            GemstoneType.AZURITE,
            GemstoneType.FLUORITE
    );

    public static <T> GemstoneCollection<T> create(final T value) {
        return new GemstoneCollection<>(value, value);
    }

    public static final GemstoneCollection<String> NAMES = GEMS.map(GemstoneType::getName);

    public static GemstoneCollection<String> prefixWithGem(final GemstoneCollection<String> ids) {
        return zipMap(NAMES, ids, GemstoneCollection::concatenate);
    }

    private static String concatenate(String gem, String id){
        return gem + id;
    }

    public static <Id, B extends Block> GemstoneCollection<DeferredBlock<B>> registerBlocks(
            final GemstoneCollection<Id> ids,
            final BiFunction<Id, Function<BlockBehaviour.Properties, B>, DeferredBlock<B>> register,
            final BiFunction<GemstoneType, BlockBehaviour.Properties, B> blockFactory,
            final BiFunction<GemstoneType, BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesCustomizer
    ) {
        return zipMap(GEMS, ids, (gem, id) ->
                register.apply(id, props -> blockFactory.apply(
                        gem, propertiesCustomizer.apply(gem, props)))
        );
    }

    public static <Id, I extends Item> GemstoneCollection<DeferredItem<I>> registerItems(
            final GemstoneCollection<Id> ids,
            final BiFunction<Id, Function<Item.Properties, I>, DeferredItem<I>> register,
            final BiFunction<GemstoneType, Item.Properties, I> itemFactory,
            final BiFunction<GemstoneType, Item.Properties, Item.Properties> propertiesCustomizer
    ) {
        return zipMap(GEMS, ids, (gem, id) ->
                register.apply(id, props -> itemFactory.apply(
                        gem, propertiesCustomizer.apply(gem, props)))
        );
    }

    public List<T> asList() {
        ImmutableList.Builder<T> builder = ImmutableList.builderWithExpectedSize(2);
        this.forEach(builder::add);
        return builder.build();
    }

    public void forEach(final Consumer<T> consumer) {
        consumer.accept(this.azurite);
        consumer.accept(this.fluorite);
    }

    public <U> GemstoneCollection<U> map(final Function<T, U> mapper) {
        return new GemstoneCollection<>(
                mapper.apply(this.azurite),
                mapper.apply(this.fluorite)
        );
    }

    public static <T, U> void zipApply(final GemstoneCollection<T> first, final GemstoneCollection<U> second, final BiConsumer<T, U> consumer) {
        consumer.accept(first.azurite(), second.azurite());
        consumer.accept(first.fluorite(), second.fluorite());
    }

    public static <T, U, R> GemstoneCollection<R> zipMap(final GemstoneCollection<T> first, final GemstoneCollection<U> second, final BiFunction<T, U, R> operation) {
        return new GemstoneCollection<>(
                operation.apply(first.azurite(), second.azurite()),
                operation.apply(first.fluorite(), second.fluorite())
        );
    }
}
