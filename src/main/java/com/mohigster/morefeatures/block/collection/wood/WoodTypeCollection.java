package com.mohigster.morefeatures.block.collection.wood;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.mohigster.morefeatures.MoreFeatures;
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
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public record WoodTypeCollection<T>(
        T bloodwood,
        T tainted,
        T palm,
        T charred,
        T decrepit,
        T pallid,
        ObjectList<Holder.Reference<T>> byId
) implements Iterable<T> {

    public static <T> WoodTypeCollection<T> of(
            final T bloodwood,
            final T tainted,
            final T palm,
            final T charred,
            final T decrepit,
            final T pallid
    ) {
        WoodTypeCollection<T> collection = new WoodTypeCollection<>(
                bloodwood,
                tainted,
                palm,
                charred,
                decrepit,
                pallid,
                new ObjectArrayList<>(256)
        );

        for (T type : collection) {
            MoreFeatures.LOGGER.debug("WoodType within collection: {}", type);
        }

        return collection;
    }

    public static final WoodTypeCollection<WoodSet> SETS = of(
            WoodSet.BLOODWOOD,
            WoodSet.TAINTED,
            WoodSet.PALM,
            WoodSet.CHARRED,
            WoodSet.DECREPIT,
            WoodSet.PALLID
    );

    public static <T> WoodTypeCollection<T> ofAll(final T value) {
        return of(value, value, value, value, value, value);
    }

    public static WoodTypeCollection<String> prefixWithSet(final WoodTypeCollection<String> ids) {
        return prefixWithSet("", ids);
    }

    public static WoodTypeCollection<String> prefixWithSet(String prefix, final WoodTypeCollection<String> ids) {
        return prefixWithSet(prefix, ids, !prefix.isEmpty());
    }

    public static WoodTypeCollection<String> prefixWithSet(String prefix, final WoodTypeCollection<String> ids, boolean appendPrefixWithUnderscore) {
        String formattedPrefix = appendPrefixWithUnderscore ? prefix + "_" : prefix;

        return zipMap(SETS, ids, (set, id) ->
                set.getFormattedId(formattedPrefix, id)
        );
    }

    public static <Id, B extends Block> WoodTypeCollection<DeferredBlock<B>> registerBlocks(
            final WoodTypeCollection<Id> ids,
            final BiFunction<Id, Function<BlockBehaviour.Properties, B>, DeferredBlock<B>> register,
            final BiFunction<WoodSet, BlockBehaviour.Properties, B> blockFactory,
            final BiFunction<WoodSet, BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesCustomizer
    ) {
        return zipMap(SETS, ids, (set, id) ->
                register.apply(id, props -> blockFactory.apply(
                        set, propertiesCustomizer.apply(set, props)))
        );
    }

    public static <Id, I extends Item> WoodTypeCollection<DeferredItem<I>> registerItems(
            final WoodTypeCollection<Id> ids,
            final BiFunction<Id, Function<Item.Properties, I>, DeferredItem<I>> register,
            final BiFunction<WoodSet, Item.Properties, I> itemFactory,
            final BiFunction<WoodSet, Item.Properties, Item.Properties> propertiesCustomizer
    ) {
        return zipMap(SETS, ids, (set, id) ->
                register.apply(id, props -> itemFactory.apply(
                        set, propertiesCustomizer.apply(set, props)))
        );
    }

    public List<T> asList() {
        final ImmutableList.Builder<T> builder = ImmutableList.builderWithExpectedSize(6);
        this.forEach(builder::add);
        return builder.build();
    }

    public T pick(final WoodSet set) {
        return switch (set) {
            case BLOODWOOD -> this.bloodwood;
            case TAINTED -> this.tainted;
            case PALM -> this.palm;
            case CHARRED -> this.charred;
            case DECREPIT -> this.decrepit;
            case PALLID -> this.pallid;
        };
    }

    public <U> WoodTypeCollection<U> map(final Function<T, U> mapper) {
        return of(
                mapper.apply(this.bloodwood),
                mapper.apply(this.tainted),
                mapper.apply(this.palm),
                mapper.apply(this.charred),
                mapper.apply(this.decrepit),
                mapper.apply(this.pallid)
        );
    }

    public static <T, U> void zipApply(final WoodTypeCollection<T> first, final WoodTypeCollection<U> second, final BiConsumer<T, U> consumer) {
        SETS.forEach(set -> consumer.accept(first.pick(set), second.pick(set)));
    }

    public static <T, U, R> WoodTypeCollection<R> zipMap(final WoodTypeCollection<T> first, final WoodTypeCollection<U> second, final BiFunction<T, U, R> operation) {
        return of(
                operation.apply(first.bloodwood(), second.bloodwood()),
                operation.apply(first.tainted(), second.tainted()),
                operation.apply(first.palm(), second.palm()),
                operation.apply(first.charred(), second.charred()),
                operation.apply(first.decrepit(), second.decrepit()),
                operation.apply(first.pallid(), second.pallid())
        );
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return Iterators.transform(this.byId().iterator(), tReference -> tReference != null ? tReference.value() : null);
    }
}
