package com.mohigster.morefeatures.block;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MoreFeatures.MODID);

    // Same convention as ModItems. Section block registration into types of blocks e.g. // Aluminium Blocks

    //———————————————————————————————————————Aluminium Blocks————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> ALUMINIUM_BLOCK = registerBlock("aluminium_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
    ));

    public static final DeferredBlock<Block> RAW_ALUMINIUM_BLOCK = registerBlock("raw_aluminium_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
    ));

    public static final DeferredBlock<Block> ALUMINIUM_ORE = registerBlock("aluminium_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
    ));

    public static final DeferredBlock<Block> DEEPSLATE_ALUMINIUM_ORE = registerBlock("deepslate_aluminium_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
    ));

    //———————————————————————————————————————Magnesium Blocks————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> MAGNESIUM_BLOCK = registerBlock("magnesium_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
    ));

    public static final DeferredBlock<Block> RAW_MAGNESIUM_BLOCK = registerBlock("raw_magnesium_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
    ));

    public static final DeferredBlock<Block> MAGNESIUM_ORE = registerBlock("magnesium_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
    ));

    public static final DeferredBlock<Block> DEEPSLATE_MAGNESIUM_ORE = registerBlock("deepslate_magnesium_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
    ));


    //———————————————————————————————————————Bismuth Blocks——————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock("bismuth_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));
    public static final DeferredBlock<Block> RAW_BISMUTH_BLOCK = registerBlock("raw_bismuth_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    //———————————————————————————————————————Azurite Blocks——————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> AZURITE_ORE = registerBlock("azurite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_AZURITE_ORE = registerBlock("deepslate_azurite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));

    public static final DeferredBlock<Block> NETHER_AZURITE_ORE = registerBlock("nether_azurite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHER_ORE)
            ));

    public static final DeferredBlock<Block> END_AZURITE_ORE = registerBlock("end_azurite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> AZURITE_BLOCK = registerBlock("azurite_block",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = registerBlock("raw_azurite_block",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    //———————————————————————————————————————Fluorite Blocks—————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> FLUORITE_ORE = registerBlock("fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_FLUORITE_ORE = registerBlock("deepslate_fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));

    public static final DeferredBlock<Block> NETHER_FLUORITE_ORE = registerBlock("nether_fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHER_ORE)
            ));

    public static final DeferredBlock<Block> END_FLUORITE_ORE = registerBlock("end_fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> FLUORITE_BLOCK = registerBlock("fluorite_block",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_FLUORITE_BLOCK = registerBlock("raw_fluorite_block",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));






    // Register functions
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function){
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.registerItem(name, (properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix())));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Blocks registered -> Performed by: " + MoreFeatures.MODID);
    }
}
