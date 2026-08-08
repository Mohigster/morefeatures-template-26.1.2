package com.mohigster.morefeatures.block.collection.wood;

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

public record WoodTypeCollection<T>(
        T bloodwood,
        T tainted,
        T palm,
        T charred,
        T decrepit,
        T pallid
) {
    public static final WoodTypeCollection<WoodSet> SETS = new WoodTypeCollection<>(
            WoodSet.BLOODWOOD,
            WoodSet.TAINTED,
            WoodSet.PALM,
            WoodSet.CHARRED,
            WoodSet.DECREPIT,
            WoodSet.PALLID
    );

    public static <T> WoodTypeCollection<T> create(final T value) {
        return new WoodTypeCollection<>(value, value, value, value, value, value);
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
                formattedPrefix + set.getName() + set.getFormattedSuffix(id)
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
        ImmutableList.Builder<T> builder = ImmutableList.builderWithExpectedSize(6);
        this.forEach(builder::add);
        return builder.build();
    }

    public void forEach(final Consumer<T> consumer) {
        consumer.accept(this.bloodwood);
        consumer.accept(this.tainted);
        consumer.accept(this.palm);
        consumer.accept(this.charred);
        consumer.accept(this.decrepit);
        consumer.accept(this.pallid);
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
        return new WoodTypeCollection<>(
                mapper.apply(this.bloodwood),
                mapper.apply(this.tainted),
                mapper.apply(this.palm),
                mapper.apply(this.charred),
                mapper.apply(this.decrepit),
                mapper.apply(this.pallid)
        );
    }

    public static <T, U> void zipApply(final WoodTypeCollection<T> first, final WoodTypeCollection<U> second, final BiConsumer<T, U> consumer) {
        consumer.accept(first.bloodwood(), second.bloodwood());
        consumer.accept(first.tainted(), second.tainted());
        consumer.accept(first.palm(), second.palm());
        consumer.accept(first.charred(), second.charred());
        consumer.accept(first.decrepit(), second.decrepit());
        consumer.accept(first.pallid(), second.pallid());
    }

    public static <T, U, R> WoodTypeCollection<R> zipMap(final WoodTypeCollection<T> first, final WoodTypeCollection<U> second, final BiFunction<T, U, R> operation) {
        return new WoodTypeCollection<>(
                operation.apply(first.bloodwood(), second.bloodwood()),
                operation.apply(first.tainted(), second.tainted()),
                operation.apply(first.palm(), second.palm()),
                operation.apply(first.charred(), second.charred()),
                operation.apply(first.decrepit(), second.decrepit()),
                operation.apply(first.pallid(), second.pallid())
        );
    }
}
