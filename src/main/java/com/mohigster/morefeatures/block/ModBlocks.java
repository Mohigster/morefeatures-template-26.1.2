package com.mohigster.morefeatures.block;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MoreFeatures.MODID);

    // Same convention as ModItems. Section block registration into types of blocks e.g. // Aluminium Blocks

    // Aluminium Blocks
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
                    .sound(SoundType.IRON)
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

    // Magnesium Blocks
    public static final DeferredBlock<Block> MAGNESIUM_BLOCK = registerBlock("magnesium_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
    ));


    // Bismuth Blocks
    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock("bismuth_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
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
