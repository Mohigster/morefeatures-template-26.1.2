How to add custom Magic Block transmutations
=======

The Magic Block is a late-game block that can be highly valuable. When an item entity is thrown onto it, it can be transmuted into a different (often more powerful!) item.
In this file, you will learn how to add a custom transmutation to the magic block, either as a mod developer or datapack creator.

**Using datagen (Recommended for mod developers)**:

1. Create a custom datagen class that extends MagicBlockTransmutationProvider
2. in the generate method, for each transmutation, call add(inputTag, outputItem)
3. In your GatherDataEvent.Client event, call generator.addProvider(true, new YourDatagenClass(packOutput, LookupProvider))
4. Run datagen. The transmutation JSON files will be automatically generated!

Example class:

```java
public class MyMagicBlockTransmutationProvider extends MagicBlockTransmutationProvider {
    public MyMagicBlockTransmutationProvider(PackOutput output,
                                             CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, "my_mod_id"); // Like in vanilla datagen classes, the string passed here is the namespace that the JSON files will be generated in.
    }

    @Override
    protected void generate() {
        // Turns any item in the "my_mod:magic_to_dirt" tag into dirt
        add(MyItemTags.MAGIC_TO_DIRT, Items.DIRT);

        // Turns any item in the "my_mod:magic_to_stone" tag into stone
        add(MyItemTags.MAGIC_TO_STONE, Items.STONE);
    }
}
```

This class will output the following JSON files:


`dirt_from_magic_block.json`

`stone_from_magic_block.json`

Any name will work, but item_from_magic_block is the convention for this mod and the name that will be output by MagicBlockTransmutationProvider. It is encouraged, though not mandatory, that you also follow this convention.

**Manually creating JSONs (only recommended for datapacks)**:

1. In your datapack OR in your mod resources folder, create the following directory: `data/namespace/magic_block_transmutations`
2. In that directory, create a JSON file with the below format:

```json
{
    "input_tag": "namespace:item_tag",
    "output_item": "namespace:item",
    "copy_components": false
}
```

Important notes:

`"namespace:item_tag"` MUST be an existing item tag. This can be a custom tag, or a vanilla tag

`"namespace:item"` MUST be an existing item. Item registration is not related to magic block transmutations

`"copy_components"` is OPTIONAL. It can be set to true, false, or left out of the JSON entirely (defaults to false if omitted). When true, any components on the input item (e.g. potion effects, custom data, enchantments) are copied onto the output item. Useful for transmutations where the output item should retain some property of the input, such as turning a custom potion into a lingering variant of itself

Example JSONs. These may not necessarily actually be in the mod:

`carbon_fiber_from_magic_block.json`:
```json
{
  "input_tag": "morefeatures:magic_block_turns_to_carbon",
  "output_item": "morefeatures:carbon_fiber"
}
```

`lingering_potion_from_magic_block.json`:
```json
{
  "input_tag": "morefeatures:magic_block_turns_to_lingering_pot",
  "output_item": "minecraft:lingering_potion",
  "copy_components": true
}
```

`grass_block_from_magic_block.json`:
```json
{
  "input_tag": "minecraft:nylium",
  "output_item": "minecraft:grass_block"
}
```

And finally, don't forget to add your custom transmutation result to the **magic_block_transmutation_results** item tag!

This tag functions as a failsafe to ensure that your result is the final item in the chain. Even if your item is a part of
a tag that is accepted as a transmutation input tag, it will not mutate if it is in this tag!

Since it is a More Features tag, you must add it in this EXACT JSON file in this EXACT directory:

`data/morefeatures/tags/item/magic_block_transmutation_results.json`

Alternatively, mods can add their items to the tag using datagen. 

An example ItemTagsProvider class:

```java
public class MyItemTagsProvider extends ItemTagsProvider {

    public MyItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, "my_mod_id");
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(MFItemTags.MAGIC_BLOCK_TRANSMUTATION_RESULTS)
                .add(ItemIds.DIRT)
                .add(ItemIds.STONE);
    }
}
```