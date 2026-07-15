How to add custom Magic Block transmutations
=======

For mods:

1. Create a custom datagen class that extends MagicBlockTransmutationProvider
2. in the generate method, for each transmutation, call add(inputTag, outputItem)
3. In your GatherDataEvent.Client event, call generator.addProvider(true, new YourDatagenClass(packOutput, LookupProvider))
4. Run datagen. The transmutation JSON files will be automatically generated!

Example class:

```java
public class MyMagicBlockTransmutationProvider extends MagicBlockTransmutationProvider {
    public MyMagicBlockTransmutationProvider(PackOutput output,
                                             CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, "my_mod_id");
    }

    @Override
    protected void generate() {
        // Turns any item in the "my_mod:magic_to_emerald" tag into emeralds
        add(MyItemTags.MAGIC_TO_EMERALD, Items.EMERALD);

        // Turns any item in the "my_mod:magic_to_diamond" tag into diamonds
        add(MyItemTags.MAGIC_TO_DIAMOND, Items.DIAMOND);
    }
}
```

This class will output the following JSON files:


emerald_from_magic_block.json
diamond_from_magic_block.json

Though any name will suffice.

For datapacks:

1. Create the following directory: data/namespace/magic_block_transmutations
2. In that directory, create a JSON file with the below format:

```json
{
    "input_tag": "namespace:item_tag",
    "output_item": "namespace:item"
}
```

Important notes:

"namespace:item_tag" MUST be an existing item tag. This can be a custom tag, or a vanilla tag

"namespace:item" MUST be an existing item. Item registration is not related to magic block transmutations

"copy_components" is OPTIONAL. It can be set to true, false, or left out of the JSON entirely (defaults to false if omitted). When true, any components on the input item (e.g. potion effects, custom data, enchantments) are copied onto the output item. Useful for transmutations where the output item should retain some property of the input, such as turning a custom potion into a lingering variant of itself

Example JSONs:

magic_block_turns_to_carbon.json:
```json
{
  "input_tag": "morefeatures:magic_block_turns_to_carbon",
  "output_item": "morefeatures:carbon_fiber"
}
```

magic_block_turns_to_lingering_potion.json:
```json
{
  "input_tag": "morefeatures:magic_block_turns_to_lingering_pot",
  "output_item": "minecraft:lingering_potion",
  "copy_components": true
}
```

magic_block_turns_to_emerald.json: // Not actually in the mod
```json
{
  "input_tag": "minecraft:wool",
  "output_item": "minecraft:emerald"
}
```