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
        this.addFromTag(Items.DIRT, MyItemTags.MAGIC_TO_DIRT);

        // Turns any item in the "my_mod:magic_to_stone" tag into stone
        this.add(Items.STONE, MyItemTags.MAGIC_TO_STONE);
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
    "input_values": [
      "namespace:input_item",
      "#namespace:item_tag"
    ],
    "output_item": "namespace:output_item",
    "copy_components": false,
    "extra_items": 0
}
```

Important notes:

`"namespace:item_tag"` MUST be an existing item tag. This can be a custom tag, or a vanilla tag.

`"namespace:input_item"` AND `"namespace:output_item` MUST be valid items. Only the output is technically mandatory

`"input_values` accepts a list of however many Item Tags or Items you want. There must be at least one value within this, but otherwise it is completely up to you.

`"copy_components"` is OPTIONAL. It can be set to true, false, or left out of the JSON entirely (defaults to false if omitted). When true, any components on the input item (e.g. potion effects, custom data, enchantments) are copied onto the output item. Useful for transmutations where the output item should retain some property of the input, such as turning a custom potion into a lingering variant of itself

`"extra_items"` is also OPTIONAL. It can be set to any non-negative integer (0 or greater) or left out (default to 0 if omitted). The transmutation result is a 1:value + 1 ratio. For example, if set to 0, each input item will turn into one output item. Or if set to one, each input becomes two of the output.

Example JSONs. These may not necessarily actually be in the mod:

`carbon_fiber_from_magic_block.json`:
```json
{
  "input_value": "#morefeatures:magic_block_transmutations/carbon",
  "output_item": "morefeatures:carbon_fiber"
}
```

`lingering_potion_from_magic_block.json`:
```json
{
  "input_value": "#morefeatures:magic_block_transmutations/lingering_pot",
  "output_item": "minecraft:lingering_potion",
  "copy_components": true
}
```

`grass_block_from_magic_block.json`:
```json
{
  "input_value": [
    "minecraft:sand",
    "minecraft:red_sand",
    "#my_mod:nylium"
  ],
  "output_item": "minecraft:grass_block",
  "extra_items": 2
}
```

Keep in mind that while vanilla tags do work, it is better to make your own specifically for use as an input tag. That way, you have complete control over what items are valid.

For example, if you remove an item from a vanilla tag in order to use that tag as an input without that item, you may inadvertently affect vanilla behaviour. Using a custom tag avoids this possibility.


And finally, don't forget to add your custom transmutation result to the **results** item tag!

This tag functions as a failsafe to ensure that your result is the final item in the chain. Even if your item is a part of
a tag that is accepted as a transmutation input tag, it will not mutate if it is in this tag!

Since it is a More Features tag, you must add it in this EXACT JSON file in this EXACT directory:

`data/morefeatures/tags/item/magic_block_transmutations/results.json`

To prevent developers from forgetting to add the result to the item tag, the Magic Block will reject the transmutation if the result item is not in the tag!

Alternatively, mods can add their items to the tag using datagen. 

An example ItemTagsProvider class:

```java
public class MyItemTagsProvider extends ItemTagsProvider {

    public MyItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MyMod.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(MFItemTags.MAGIC_BLOCK_TRANSMUTATION_RESULTS)
                .add(ItemIds.DIRT)
                .add(ItemIds.STONE);
    }
}
```

Tag names are generally simple.

In this mod, all magic block transmutation tags are names simply after the output item, and located within the tags/item/magic_block_transmutations folder

e.g. `tags/item/magic_block_transmutations/bedrock` is the tag to turn an item into bedrock using the magic block.

I recommend you follow a similar convention.