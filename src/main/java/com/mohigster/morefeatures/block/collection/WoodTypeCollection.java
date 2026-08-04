package com.mohigster.morefeatures.block.collection;

import com.google.common.collect.ImmutableList;
import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.tag.MFItemTags;
import net.minecraft.tags.TagKey;
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
    public static final WoodTypeCollection<WoodSetType> TYPES = new WoodTypeCollection<>(
            WoodSetType.BLOODWOOD,
            WoodSetType.TAINTED,
            WoodSetType.PALM,
            WoodSetType.CHARRED,
            WoodSetType.DECREPIT,
            WoodSetType.PALLID
    );

    public static final WoodTypeCollection<String> NAMES = TYPES.map(WoodSetType::getName);

    public static <T> WoodTypeCollection<T> create(final T value) {
        return new WoodTypeCollection<>(value, value, value, value, value, value);
    }

    public static WoodTypeCollection<String> prefixWithType(final WoodTypeCollection<String> ids) {
        return prefixPrefixed("", ids);
    }

    public static WoodTypeCollection<String> prefixPrefixed(String prefix, final WoodTypeCollection<String> ids) {
        var toReturn = zipMap(NAMES, ids, (type, id) -> (needsPrefix(prefix) ? (prefix + "_") : "") + type + getFullSuffix(type, id));
        MoreFeatures.LOGGER.debug("Registered wood ids: {}",  toReturn);
        return toReturn;
    }

    public static <Id, B extends Block> WoodTypeCollection<DeferredBlock<B>> registerBlocks(
            final WoodTypeCollection<Id> ids,
            final BiFunction<Id, Function<BlockBehaviour.Properties, B>, DeferredBlock<B>> register,
            final BiFunction<WoodSetType, BlockBehaviour.Properties, B> blockFactory,
            final BiFunction<WoodSetType, BlockBehaviour.Properties, BlockBehaviour.Properties> propertiesCustomizer
    ) {
        return zipMap(
                TYPES, ids, (wood, id) -> register.apply(
                        id, props -> blockFactory.apply(
                                wood, propertiesCustomizer.apply(
                                        wood,
                                        props
                                )))
        );
    }

    public static <Id, I extends Item> WoodTypeCollection<DeferredItem<I>> registerItems(
            final WoodTypeCollection<Id> ids,
            final BiFunction<Id, Function<Item.Properties, I>, DeferredItem<I>> register,
            final BiFunction<WoodSetType, Item.Properties, I> itemFactory,
            final BiFunction<WoodSetType, Item.Properties, Item.Properties> propertiesCustomizer
    ) {
        return zipMap(
                TYPES, ids, (wood, id) -> register.apply(
                        id, props -> itemFactory.apply(
                                wood, propertiesCustomizer.apply(
                                        wood,
                                        props
                                )))
        );
    }

    public List<T> asList() {
        ImmutableList.Builder<T> builder = ImmutableList.builderWithExpectedSize(16);
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

    public T pick(final WoodSetType woodSet) {
        return switch (woodSet) {
            case BLOODWOOD -> this.bloodwood;
            case TAINTED -> this.tainted;
            case PALM -> this.palm;
            case CHARRED -> this.charred;
            case DECREPIT -> this.decrepit;
            case PALLID -> this.pallid;
        };
    }

    public TagKey<Item> pickTag(final WoodSetType woodSet) {
        return switch (woodSet) {
            case BLOODWOOD -> MFItemTags.BLOODWOOD_LOGS;
            case TAINTED -> MFItemTags.TAINTED_LOGS;
            case PALM -> MFItemTags.PALM_LOGS;
            case CHARRED -> MFItemTags.CHARRED_STEMS;
            case DECREPIT -> MFItemTags.DECREPIT_LOGS;
            case PALLID -> MFItemTags.PALLID_LOGS;
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

    @SuppressWarnings("unused")
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

    private static boolean needsPrefix(String prefix) {
        return !prefix.isEmpty();
    }

    private static String getFullSuffix(String type, String id){
        return (needsNoSuffix(type, id)) ? "" : "_" + (needsNetherSuffix(type, id) ? getNetherSuffix(id) : id);
    }

    private static boolean needsNoSuffix(String type, String id){
        return (type.equals("bloodwood") && id.equals("wood"));
    }

    private static boolean needsNetherSuffix(String type, String id){
        return (type.equals("charred") && ((id.equals("wood") || id.equals("log")) || id.equals("sapling")));
    }

    private static String getNetherSuffix(String id){
        return id.equals("wood") ? "hyphae" : id.equals("sapling") ? "fungus" : "stem";
    }
}
