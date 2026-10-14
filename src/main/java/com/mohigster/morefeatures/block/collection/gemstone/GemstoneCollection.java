package com.mohigster.morefeatures.block.collection.gemstone;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public record GemstoneCollection<T>(
        T azurite,
        T fluorite,
        ObjectList<Holder.Reference<T>> byId
) implements Iterable<T> {
    public static <T> GemstoneCollection<T> of(T azurite, T fluorite) {
        return new GemstoneCollection<>(azurite, fluorite, new ObjectArrayList<>(256));
    }

    public static final GemstoneCollection<GemstoneType> GEMS = of(
            GemstoneType.AZURITE,
            GemstoneType.FLUORITE
    );

    public static <T> GemstoneCollection<T> ofAll(final T value) {
        return of(value, value);
    }

    public static GemstoneCollection<String> prefixWithGem(final GemstoneCollection<String> ids) {
        return prefixWithGem("", ids);
    }

    public static GemstoneCollection<String> prefixWithGem(String prefix, final GemstoneCollection<String> ids) {
        return prefixWithGem(prefix, ids, !prefix.isEmpty());
    }

    public static GemstoneCollection<String> prefixWithGem(String prefix, final GemstoneCollection<String> ids, boolean appendPrefixWithUnderscore) {
        String formattedPrefix = appendPrefixWithUnderscore ? prefix + "_" : prefix;

        return zipMap(GEMS, ids, (gem, id) -> gem.getFormattedId(formattedPrefix, id));
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

    @SuppressWarnings("unused")
    public List<T> asList() {
        ImmutableList.Builder<T> builder = ImmutableList.builderWithExpectedSize(2);
        this.forEach(builder::add);
        return builder.build();
    }

    public <U> GemstoneCollection<U> map(final Function<T, U> mapper) {
        return of(
                mapper.apply(this.azurite),
                mapper.apply(this.fluorite)
        );
    }

    public static <T, U> void zipApply(final GemstoneCollection<T> first, final GemstoneCollection<U> second, final BiConsumer<T, U> consumer) {
        GEMS.forEach(gem -> consumer.accept(first.pick(gem), second.pick(gem)));
    }

    public static <T, U, R> GemstoneCollection<R> zipMap(final GemstoneCollection<T> first, final GemstoneCollection<U> second, final BiFunction<T, U, R> operation) {
        return of(
                operation.apply(first.azurite(), second.azurite()),
                operation.apply(first.fluorite(), second.fluorite())
        );
    }

    public T pick(final GemstoneType gem) {
        return switch (gem) {
            case AZURITE -> this.azurite;
            case FLUORITE -> this.fluorite;
        };
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return Iterators.transform(this.byId().iterator(), holder -> Objects.requireNonNull(holder).value());
    }
}
