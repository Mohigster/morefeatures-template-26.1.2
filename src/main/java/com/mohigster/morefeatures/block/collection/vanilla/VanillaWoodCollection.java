package com.mohigster.morefeatures.block.collection.vanilla;

import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public record VanillaWoodCollection<T>(
        T oak,
        T spruce,
        T birch,
        T jungle,
        T acacia,
        T darkOak,
        T crimson,
        T warped,
        T mangrove,
        T cherry,
        T bamboo,
        T mosaic,
        T paleOak
) {
    public static final VanillaWoodCollection<VanillaWoodSet> SETS = new VanillaWoodCollection<>(
            VanillaWoodSet.OAK,
            VanillaWoodSet.SPRUCE,
            VanillaWoodSet.BIRCH,
            VanillaWoodSet.JUNGLE,
            VanillaWoodSet.ACACIA,
            VanillaWoodSet.DARK_OAK,
            VanillaWoodSet.CRIMSON,
            VanillaWoodSet.WARPED,
            VanillaWoodSet.MANGROVE,
            VanillaWoodSet.CHERRY,
            VanillaWoodSet.BAMBOO,
            VanillaWoodSet.MOSAIC,
            VanillaWoodSet.PALE_OAK
    );

    private static final VanillaWoodCollection<String> NAMES = SETS.map(VanillaWoodSet::getSerializedName);

    public static VanillaWoodCollection<String> prefixWithSet(final VanillaWoodCollection<String> ids) {
        return zipMap(NAMES, ids, (set, id) -> set + "_" + id);
    }

    public static <Id, B extends Block> VanillaWoodCollection<DeferredBlock<B>> registerBlocks(
            VanillaWoodCollection<Id> ids,
            final BiFunction<Id, Function<BlockBehaviour.Properties, B>, DeferredBlock<B>> register,
            final BiFunction<VanillaWoodSet, BlockBehaviour.Properties, B> blockFactory,
            final BiFunction<VanillaWoodSet, BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesCustomizer
    ) {
        return zipMap(SETS, ids, (set, id) ->
                register.apply(id, props -> blockFactory.apply(
                        set, propertiesCustomizer.apply(set, props)))
        );
    }

    public static <T> VanillaWoodCollection<T> createForAll(final T value) {
        return new VanillaWoodCollection<>(value,
                value, value, value, value, value, value,
                value, value, value, value, value, value
        );
    }

    public void forEach(final Consumer<T> consumer) {
        consumer.accept(this.oak);
        consumer.accept(this.spruce);
        consumer.accept(this.birch);
        consumer.accept(this.jungle);
        consumer.accept(this.acacia);
        consumer.accept(this.darkOak);
        consumer.accept(this.crimson);
        consumer.accept(this.warped);
        consumer.accept(this.mangrove);
        consumer.accept(this.cherry);
        consumer.accept(this.bamboo);
        consumer.accept(this.mosaic);
        consumer.accept(this.paleOak);
    }

    public <U> VanillaWoodCollection<U> map(final Function<T, U> mapper) {
        return new VanillaWoodCollection<>(
                mapper.apply(this.oak),
                mapper.apply(this.spruce),
                mapper.apply(this.birch),
                mapper.apply(this.jungle),
                mapper.apply(this.acacia),
                mapper.apply(this.darkOak),
                mapper.apply(this.crimson),
                mapper.apply(this.warped),
                mapper.apply(this.mangrove),
                mapper.apply(this.cherry),
                mapper.apply(this.bamboo),
                mapper.apply(this.mosaic),
                mapper.apply(this.paleOak)
        );
    }

    public static <T, U, R> VanillaWoodCollection<R> zipMap(final VanillaWoodCollection<T> first, final VanillaWoodCollection<U> second, final BiFunction<T, U, R> operation) {
        return new VanillaWoodCollection<>(
                operation.apply(first.oak(), second.oak()),
                operation.apply(first.spruce(), second.spruce()),
                operation.apply(first.birch(), second.birch()),
                operation.apply(first.jungle(), second.jungle()),
                operation.apply(first.acacia(), second.acacia()),
                operation.apply(first.darkOak(), second.darkOak()),
                operation.apply(first.crimson(), second.crimson()),
                operation.apply(first.warped(), second.warped()),
                operation.apply(first.mangrove(), second.mangrove()),
                operation.apply(first.cherry(), second.cherry()),
                operation.apply(first.bamboo(), second.bamboo()),
                operation.apply(first.mosaic(), second.mosaic()),
                operation.apply(first.paleOak(), second.paleOak())
        );
    }

    public List<T> asList() {
        ImmutableList.Builder<T> builder = ImmutableList.builderWithExpectedSize(13);
        this.forEach(builder::add);
        return builder.build();
    }

    public T pick(final VanillaWoodSet set) {
        return switch (set) {
            case OAK -> this.oak;
            case SPRUCE -> this.spruce;
            case BIRCH -> this.birch;
            case JUNGLE -> this.jungle;
            case ACACIA -> this.acacia;
            case DARK_OAK -> this.darkOak;
            case CRIMSON -> this.crimson;
            case WARPED -> this.warped;
            case MANGROVE -> this.mangrove;
            case CHERRY -> this.cherry;
            case BAMBOO -> this.bamboo;
            case MOSAIC -> this.mosaic;
            case PALE_OAK -> this.paleOak;
        };
    }
}
