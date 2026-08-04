package com.mohigster.morefeatures.block;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.collection.WoodSetType;
import com.mohigster.morefeatures.block.collection.WoodTypeCollection;
import com.mohigster.morefeatures.block.custom.*;
import com.mohigster.morefeatures.block.custom.modified.*;
import com.mohigster.morefeatures.block.custom.nylium.MFNyliumBlock;
import com.mohigster.morefeatures.block.custom.nylium.NulliumBlock;
import com.mohigster.morefeatures.block.custom.pillar.PillarBlock;
import com.mohigster.morefeatures.block.custom.flammable.*;
import com.mohigster.morefeatures.block.custom.magicblock.MagicBlock;
import com.mohigster.morefeatures.block.custom.pillar.WeatheringCopperPillarBlock;
import com.mohigster.morefeatures.block.custom.temporaldilator.TemporalDilatorBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.WeatheringCopperVerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.blocktype.MFBlockSetType;
import com.mohigster.morefeatures.block.custom.blocktype.MFWoodType;
import com.mohigster.morefeatures.references.MFBlockIds;
import com.mohigster.morefeatures.references.MFBlockItemIds;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.particles.MFParticleTypes;
import com.mohigster.morefeatures.references.MFIdentifier;
import com.mohigster.morefeatures.sound.MFSoundTypes;
import com.mohigster.morefeatures.tag.MFBlockTags;
import com.mohigster.morefeatures.worldgen.MFConfiguredFeatures;
import com.mohigster.morefeatures.worldgen.tree.MFTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.function.*;

import static net.minecraft.world.level.block.WeatheringCopperCollection.zipMap;

public class MFBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MoreFeatures.MODID);

    // Same convention as ModItems. Section block registration into types of blocks e.g. // Aluminium Blocks

    //———————————————————————————————————————Aluminium Blocks————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> ALUMINIUM_BLOCK = registerBlock(MFBlockItemIds.ALUMINIUM_BLOCK,
            properties -> new Block(properties
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .strength(4F, 16F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .isValidSpawn(MFBlocks::always)
                    .isRedstoneConductor(MFBlocks::always)
            ));

    public static final DeferredBlock<Block> RAW_ALUMINIUM_BLOCK = registerBlock(MFBlockItemIds.RAW_ALUMINIUM_BLOCK,
            Block::new,
            _ -> Properties.ofFullCopy(ALUMINIUM_BLOCK.get()).sound(SoundType.STONE)
    );

    public static final DeferredBlock<Block> ALUMINIUM_ORE = registerBlock(MFBlockItemIds.ALUMINIUM_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties),
            _ -> Properties.ofFullCopy(RAW_ALUMINIUM_BLOCK.get())
    );

    public static final DeferredBlock<Block> DEEPSLATE_ALUMINIUM_ORE = registerBlock(MFBlockItemIds.DEEPSLATE_ALUMINIUM_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties),
            _ -> Properties.ofFullCopy(ALUMINIUM_BLOCK.get()).sound(SoundType.DEEPSLATE)
    );

    //———————————————————————————————————————Magnesium Blocks————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> MAGNESIUM_BLOCK = registerBlock(MFBlockItemIds.MAGNESIUM_BLOCK,
            Block::new,
            _ -> Properties.ofFullCopy(ALUMINIUM_BLOCK.get()).mapColor(MapColor.COLOR_GRAY)
    );

    public static final DeferredBlock<Block> RAW_MAGNESIUM_BLOCK = registerBlock(MFBlockItemIds.RAW_MAGNESIUM_BLOCK,
            Block::new,
            _ -> Properties.ofFullCopy(RAW_ALUMINIUM_BLOCK.get()).mapColor(MapColor.COLOR_GRAY)
    );

    public static final DeferredBlock<Block> MAGNESIUM_ORE = registerBlock(MFBlockItemIds.MAGNESIUM_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties),
            _ -> Properties.ofFullCopy(RAW_MAGNESIUM_BLOCK.get()).mapColor(MapColor.STONE)
    );

    public static final DeferredBlock<Block> DEEPSLATE_MAGNESIUM_ORE = registerBlock(MFBlockItemIds.DEEPSLATE_MAGNESIUM_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties),
            _ -> Properties.ofFullCopy(MAGNESIUM_ORE.get()).sound(SoundType.DEEPSLATE).mapColor(MapColor.DEEPSLATE)
    );

    //———————————————————————————————————————Bismuth Blocks——————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock(MFBlockItemIds.BISMUTH_BLOCK,
            properties -> new Block(properties
                    .strength(60F, 1200F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .instrument(NoteBlockInstrument.HARP)
                    .isRedstoneConductor(MFBlocks::always)
                    .isValidSpawn(MFBlocks::always)
                    .isViewBlocking(MFBlocks::always)
                    .isSuffocating(MFBlocks::always)
            ));

    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock(MFBlockItemIds.BISMUTH_ORE,
            props -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            _ -> Properties.ofFullCopy(BISMUTH_BLOCK.get())
                    .sound(SoundType.STONE)
                    .mapColor(Blocks.END_STONE.defaultMapColor())
    );

    public static final DeferredBlock<Block> RAW_BISMUTH_BLOCK = registerBlock(MFBlockItemIds.RAW_BISMUTH_BLOCK,
            Block::new,
            _ -> Properties.ofFullCopy(BISMUTH_BLOCK.get())
    );

    //———————————————————————————————————————Azurite Blocks——————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> AZURITE_ORE = registerBlock(MFBlockItemIds.AZURITE_ORE,
            props -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            _ -> Properties.ofFullCopy(BISMUTH_ORE.get()).mapColor(MapColor.STONE));

    public static final DeferredBlock<Block> DEEPSLATE_AZURITE_ORE = registerBlock(MFBlockItemIds.DEEPSLATE_AZURITE_ORE,
            props -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            _ -> Properties.ofFullCopy(AZURITE_ORE.get())
                    .sound(SoundType.DEEPSLATE)
                    .mapColor(MapColor.DEEPSLATE)
    );

    public static final DeferredBlock<Block> NETHER_AZURITE_ORE = registerBlock(MFBlockItemIds.NETHER_AZURITE_ORE,
            props -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            _ -> Properties.ofFullCopy(AZURITE_ORE.get())
                    .sound(SoundType.NETHER_ORE)
                    .mapColor(MapColor.NETHER)
    );

    public static final DeferredBlock<Block> END_AZURITE_ORE = registerBlock(MFBlockItemIds.END_AZURITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties),
            _ -> Properties.ofFullCopy(AZURITE_ORE.get()).mapColor(MapColor.SAND)
    );

    public static final DeferredBlock<Block> AZURITE_BLOCK = registerBlock(MFBlockItemIds.AZURITE_BLOCK,
            properties -> new Block(properties
                    .strength(4f, 16f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.CHIME)
                    .isRedstoneConductor(MFBlocks::always)
                    .isValidSpawn(MFBlocks::always)
                    .isSuffocating(MFBlocks::always)
                    .isViewBlocking(MFBlocks::always)
                    .mapColor(MapColor.COLOR_BLUE)
            ));

    public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = registerBlock(MFBlockItemIds.RAW_AZURITE_BLOCK,
            Block::new,
            _ -> Properties.ofFullCopy(AZURITE_BLOCK.get())
    );

    public static final DeferredBlock<StairBlock> AZURITE_STAIRS = registerStair(MFBlockItemIds.AZURITE_STAIRS, AZURITE_BLOCK);

    public static final DeferredBlock<SlabBlock> AZURITE_SLAB = registerSlab(MFBlockItemIds.AZURITE_SLAB, AZURITE_BLOCK);

    public static final DeferredBlock<Block> AZURITE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.AZURITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(AZURITE_BLOCK.get())
    );

    public static final DeferredBlock<Block> AZURITE_PRESSURE_PLATE = registerBlock(MFBlockItemIds.AZURITE_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(MFBlockSetType.AZURITE, properties),
            _ -> Properties.ofFullCopy(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)
    );

    public static final DeferredBlock<Block> AZURITE_BUTTON = registerBlock(MFBlockItemIds.AZURITE_BUTTON,
            properties -> new ButtonBlock(MFBlockSetType.AZURITE, 20, properties),
            _ -> Properties.ofFullCopy(Blocks.STONE_BUTTON));

    public static final DeferredBlock<Block> AZURITE_WALL = registerBlock(MFBlockItemIds.AZURITE_WALL,
            WallBlock::new,
            _ -> Properties.ofFullCopy(AZURITE_BLOCK.get()).forceSolidOn()
    );

    public static final DeferredBlock<Block> AZURITE_FENCE = registerBlock(MFBlockItemIds.AZURITE_FENCE,
            properties -> new MFFenceBlock(properties
                    .strength(2F, 8F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
                    .isRedstoneConductor(MFBlocks::never)
                    .isViewBlocking(MFBlocks::never)
                    .isSuffocating(MFBlocks::never)
                    .isValidSpawn(MFBlocks::never)
                    .noOcclusion()
                    .forceSolidOn()
            ));

    public static final DeferredBlock<Block> AZURITE_FENCE_GATE = registerBlock(MFBlockItemIds.AZURITE_FENCE_GATE,
            props -> new FenceGateBlock(MFWoodType.AZURITE, props),
            _ -> Properties.ofFullCopy(AZURITE_FENCE.get())
    );

    public static final DeferredBlock<Block> AZURITE_DOOR = registerBlock(MFBlockItemIds.AZURITE_DOOR,
            properties -> new DoorBlock(MFBlockSetType.AZURITE, properties
                    .strength(2F, 6F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(MFBlocks::never)
            ));

    public static final DeferredBlock<Block> AZURITE_TRAPDOOR = registerBlock(MFBlockItemIds.AZURITE_TRAPDOOR,
            props -> new TrapDoorBlock(MFBlockSetType.AZURITE, props),
            _ -> Properties.ofFullCopy(AZURITE_DOOR.get())
    );

    public static final DeferredBlock<Block> AZURITE_SIGN = registerBlockWithoutItem(MFBlockIds.AZURITE_SIGN,
            properties -> new MFStandingSignBlock(MFWoodType.AZURITE, properties
                    .noCollision()
                    .strength(2F, 6F)
                    .sound(SoundType.AMETHYST)
                    .requiresCorrectToolForDrops()
                    .isRedstoneConductor(MFBlocks::never)
            ));

    public static final DeferredBlock<Block> AZURITE_WALL_SIGN = registerBlockWithoutItem(MFBlockIds.AZURITE_WALL_SIGN,
            properties -> new MFWallSignBlock(MFWoodType.AZURITE, properties),
            _ -> Properties.ofFullCopy(AZURITE_SIGN.get())
    );

    // Ceiling sign
    public static final DeferredBlock<Block> AZURITE_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.AZURITE_HANGING_SIGN,
            properties -> new MFCeilingHangingSignBlock(MFWoodType.AZURITE, properties),
            _ -> Properties.ofFullCopy(AZURITE_SIGN.get())
    );

    // Wall sign
    public static final DeferredBlock<Block> AZURITE_WALL_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.AZURITE_WALL_HANGING_SIGN,
            properties -> new MFWallHangingSignBlock(MFWoodType.AZURITE, properties),
            _ -> Properties.ofFullCopy(AZURITE_SIGN.get())
    );

    public static final DeferredBlock<Block> AZURITE_SHELF = registerVerticalSlabOrShelf(MFBlockItemIds.AZURITE_SHELF,
            false,
            MFShelfBlock::new,
            _ -> BlockBehaviour.Properties.ofFullCopy(RAW_AZURITE_BLOCK.get())
                    .sound(SoundType.MEDIUM_AMETHYST_BUD)
                    .isValidSpawn(MFBlocks::never)
                    .isRedstoneConductor(MFBlocks::never)
                    .isSuffocating(MFBlocks::never)
    );

    //———————————————————————————————————————Fluorite Blocks—————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> FLUORITE_ORE = registerBlock(MFBlockItemIds.FLUORITE_ORE,
            props -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            _ -> Properties.ofFullCopy(AZURITE_ORE.get())
    );

    public static final DeferredBlock<Block> DEEPSLATE_FLUORITE_ORE = registerBlock(MFBlockItemIds.DEEPSLATE_FLUORITE_ORE,
            props -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            _ -> Properties.ofFullCopy(DEEPSLATE_AZURITE_ORE.get())
    );

    public static final DeferredBlock<Block> NETHER_FLUORITE_ORE = registerBlock(MFBlockItemIds.NETHER_FLUORITE_ORE,
            props -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            _ -> Properties.ofFullCopy(NETHER_AZURITE_ORE.get())
    );

    public static final DeferredBlock<Block> END_FLUORITE_ORE = registerBlock(MFBlockItemIds.END_FLUORITE_ORE,
            props -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            _ -> Properties.ofFullCopy(END_AZURITE_ORE.get()));

    public static final DeferredBlock<Block> FLUORITE_BLOCK = registerBlock(MFBlockItemIds.FLUORITE_BLOCK,
            Block::new,
            _ -> Properties.ofFullCopy(AZURITE_BLOCK.get()).mapColor(MapColor.COLOR_GREEN)
    );

    public static final DeferredBlock<Block> RAW_FLUORITE_BLOCK = registerBlock(MFBlockItemIds.RAW_FLUORITE_BLOCK,
            Block::new,
            _ -> Properties.ofFullCopy(FLUORITE_BLOCK.get())
    );

    public static final DeferredBlock<StairBlock> FLUORITE_STAIRS = registerStair(MFBlockItemIds.FLUORITE_STAIRS, FLUORITE_BLOCK);

    public static final DeferredBlock<SlabBlock> FLUORITE_SLAB = registerSlab(MFBlockItemIds.FLUORITE_SLAB, FLUORITE_BLOCK);

    public static final DeferredBlock<Block> FLUORITE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.FLUORITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(FLUORITE_BLOCK.get())
    );

    public static final DeferredBlock<Block> FLUORITE_WALL = registerBlock(MFBlockItemIds.FLUORITE_WALL,
            WallBlock::new,
            _ -> Properties.ofFullCopy(FLUORITE_BLOCK.get()).forceSolidOn()
    );

    public static final DeferredBlock<Block> FLUORITE_PRESSURE_PLATE = registerBlock(MFBlockItemIds.FLUORITE_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(MFBlockSetType.FLUORITE, properties),
            _ -> Properties.ofFullCopy(AZURITE_PRESSURE_PLATE.get())
    );

    public static final DeferredBlock<Block> FLUORITE_BUTTON = registerBlock(MFBlockItemIds.FLUORITE_BUTTON,
            properties -> new ButtonBlock(MFBlockSetType.FLUORITE, 20, properties),
            _ -> Properties.ofFullCopy(AZURITE_BUTTON.get())
    );

    public static final DeferredBlock<Block> FLUORITE_FENCE = registerBlock(MFBlockItemIds.FLUORITE_FENCE,
            MFFenceBlock::new,
            _ -> Properties.ofFullCopy(AZURITE_FENCE.get())
    );

    public static final DeferredBlock<Block> FLUORITE_FENCE_GATE = registerBlock(MFBlockItemIds.FLUORITE_FENCE_GATE,
            props -> new FenceGateBlock(MFWoodType.FLUORITE, props),
            _ -> Properties.ofFullCopy(AZURITE_FENCE_GATE.get())
    );

    public static final DeferredBlock<Block> FLUORITE_DOOR = registerBlock(MFBlockItemIds.FLUORITE_DOOR,
            properties -> new DoorBlock(MFBlockSetType.FLUORITE, properties),
            _ -> Properties.ofFullCopy(AZURITE_DOOR.get()));

    public static final DeferredBlock<Block> FLUORITE_TRAPDOOR = registerBlock(MFBlockItemIds.FLUORITE_TRAPDOOR,
            props -> new TrapDoorBlock(MFBlockSetType.FLUORITE, props),
            _ -> Properties.ofFullCopy(AZURITE_TRAPDOOR.get())
    );

    public static final DeferredBlock<Block> FLUORITE_SIGN = registerBlockWithoutItem(MFBlockIds.FLUORITE_SIGN,
            props -> new MFStandingSignBlock(MFWoodType.FLUORITE, props),
            _ -> Properties.ofFullCopy(AZURITE_SIGN.get())
    );

    public static final DeferredBlock<Block> FLUORITE_WALL_SIGN = registerBlockWithoutItem(MFBlockIds.FLUORITE_WALL_SIGN,
            properties -> new MFWallSignBlock(MFWoodType.FLUORITE, properties),
            _ -> Properties.ofFullCopy(AZURITE_SIGN.get())
    );

    public static final DeferredBlock<Block> FLUORITE_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.FLUORITE_HANGING_SIGN,
            properties -> new MFCeilingHangingSignBlock(MFWoodType.FLUORITE, properties),
            _ -> Properties.ofFullCopy(AZURITE_SIGN.get())
    );

    // Wall sign
    public static final DeferredBlock<Block> FLUORITE_WALL_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.FLUORITE_WALL_HANGING_SIGN,
            properties -> new MFWallHangingSignBlock(MFWoodType.FLUORITE, properties),
            _ -> Properties.ofFullCopy(AZURITE_SIGN.get())
    );

    public static final DeferredBlock<Block> FLUORITE_SHELF = registerVerticalSlabOrShelf(MFBlockItemIds.FLUORITE_SHELF,
            false,
            MFShelfBlock::new,
            _ -> Properties.ofFullCopy(AZURITE_SHELF.get())
    );

    //———————————————————————————————————————Everfrost Blocks————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> EVERFROST_BLUE_ICE_ORE = registerBlock(MFBlockItemIds.EVERFROST_BLUE_ICE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLASS)
                    .friction(0.98F)
            ));

    public static final DeferredBlock<Block> EVERFROST_PACKED_ICE_ORE = registerBlock(MFBlockItemIds.EVERFROST_PACKED_ICE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLASS)
                    .friction(0.98F)
            ));

    //———————————————————————————————————————Wooden Blocks———————————————————————————————————————————————————————————————————————————

    public static final WoodTypeCollection<DeferredBlock<Block>> LOG = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.LOG,
            MFBlocks::registerBlock,
            MFFlammableRotatedPillarBlock::new,
            MFBlocks::logProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOOD = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOOD,
            MFBlocks::registerBlock,
            MFFlammableRotatedPillarBlock::new,
            MFBlocks::logProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> STRIPPED_LOG = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.STRIPPED_LOG,
            MFBlocks::registerBlock,
            MFFlammableRotatedPillarBlock::new,
            MFBlocks::logProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> STRIPPED_WOOD = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.STRIPPED_WOOD,
            MFBlocks::registerBlock,
            MFFlammableRotatedPillarBlock::new,
            MFBlocks::logProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> PLANKS = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.PLANKS,
            MFBlocks::registerBlock,
            MFFlammableBlock::new,
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_STAIRS = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_STAIRS,
            MFBlocks::registerBlock,
            MFFlammableStairBlock::new,
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_SLAB = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_SLAB,
            MFBlocks::registerBlock,
            MFFlammableSlabBlock::new,
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_VERTICAL_SLAB = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_VERTICAL_SLAB,
            MFBlocks::registerBlock,
            VerticalSlabBlock::new,
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_FENCE = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_FENCE,
            MFBlocks::registerBlock,
            MFFlammableFenceBlock::new,
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_FENCE_GATE = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_FENCE_GATE,
            MFBlocks::registerBlock,
            MFFlammableFenceGateBlock::new,
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_PRESSURE_PLATE = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_PRESSURE_PLATE,
            MFBlocks::registerBlock,
            (wood, props) -> new PressurePlateBlock(wood.getBlockSetType(), props),
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_BUTTON = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_BUTTON,
            MFBlocks::registerBlock,
            (wood, props) -> new ButtonBlock(wood.getBlockSetType(), 20, props),
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_DOOR = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_DOOR,
            MFBlocks::registerBlock,
            (wood, props) -> new DoorBlock(wood.getBlockSetType(), props),
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_TRAPDOOR = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_TRAPDOOR,
            MFBlocks::registerBlock,
            (wood, props) -> new TrapDoorBlock(wood.getBlockSetType(), props),
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_SHELF = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_SHELF,
            MFBlocks::registerBlock,
            MFShelfBlock::new,
            MFBlocks::shelfProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_SIGN = WoodTypeCollection.registerBlocks(
            MFBlockIds.SIGN,
            MFBlocks::registerBlockWithoutItem,
            MFStandingSignBlock::new,
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_WALL_SIGN = WoodTypeCollection.registerBlocks(
            MFBlockIds.WALL_SIGN,
            MFBlocks::registerBlockWithoutItem,
            MFWallSignBlock::new,
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_HANGING_SIGN = WoodTypeCollection.registerBlocks(
            MFBlockIds.HANGING_SIGN,
            MFBlocks::registerBlockWithoutItem,
            MFCeilingHangingSignBlock::new,
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_WALL_HANGING_SIGN = WoodTypeCollection.registerBlocks(
            MFBlockIds.WALL_HANGING_SIGN,
            MFBlocks::registerBlockWithoutItem,
            MFWallHangingSignBlock::new,
            MFBlocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> POTTED_SAPLING = WoodTypeCollection.registerBlocks(
            MFBlockIds.POTTED_SAPLING,
            MFBlocks::registerBlockWithoutItem,
            (wood, props) -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, wood.getSaplingOrFungus(), props),
            (_, props) -> props.noOcclusion().instabreak().pushReaction(PushReaction.DESTROY)
    );

    public static final DeferredBlock<Block> BLOODWOOD_LEAVES = registerBlock(MFBlockItemIds.BLOODWOOD_LEAVES,
            props -> new MFLeavesBlock(0.03F, MFParticleTypes.BLOODWOOD_LEAVES.get(), props),
            _ -> Properties.ofFullCopy(Blocks.OAK_LEAVES)
    );

    public static final DeferredBlock<Block> BLOODWOOD_SAPLING = registerBlock(MFBlockItemIds.BLOODWOOD_SAPLING,
            props -> new SaplingBlock(MFTreeGrowers.BLOODWOOD, props),
            _ -> Properties.ofFullCopy(Blocks.OAK_SAPLING)
    );

    //———————————————————————————————————————Tainted Wood Blocks—————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> TAINTED_LEAVES = registerBlock(MFBlockItemIds.TAINTED_LEAVES,
            properties -> new MFLeavesBlock(0.03F, MFParticleTypes.TAINTED_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> TAINTED_SAPLING = registerBlock(MFBlockItemIds.TAINTED_SAPLING,
            properties -> new SaplingBlock(MFTreeGrowers.TAINTED, properties
                    .randomTicks()
                    .instabreak()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS))
    );

    //———————————————————————————————————————Palm Wood Blocks————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> PALM_LEAVES = registerBlock(MFBlockItemIds.PALM_LEAVES,
            properties -> new MFLeavesBlock(0.01F, MFParticleTypes.PALM_LEAVES.get(), properties
                    .strength(0.2F, 0.2F)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALM_SAPLING = registerBlock(MFBlockItemIds.PALM_SAPLING,
            properties -> new PlantedOffGrassSaplingBlock(MFTreeGrowers.PALM, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion()
                    .noCollision(),
                    () -> Blocks.SAND // The block that the sapling can be planted on. You can also pass in a block tag here.
            ));

    //———————————————————————————————————————Charred Wood Blocks—————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> CHARRED_ROOTS = registerBlock(MFBlockItemIds.CHARRED_ROOTS,
            props -> new NetherRootsBlock(MFBlockTags.SUPPORTS_CHARRED_ROOTS, props),
            _ -> Properties.ofFullCopy(Blocks.WARPED_ROOTS)
    );

    public static final DeferredBlock<Block> POTTED_CHARRED_ROOTS = registerBlockWithoutItem(MFBlockIds.POTTED_CHARRED_ROOTS,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, CHARRED_ROOTS, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> CHARRED_NYLIUM = registerBlock(MFBlockItemIds.CHARRED_NYLIUM,
            props -> new MFNyliumBlock(props, CHARRED_ROOTS),
            _ -> Properties.ofFullCopy(Blocks.WARPED_NYLIUM).mapColor(MapColor.COLOR_BLACK)
    );

    public static final DeferredBlock<Block> CHARRED_WART_BLOCK = registerBlock(MFBlockItemIds.CHARRED_WART_BLOCK,
            Block::new,
            _ -> Properties.ofFullCopy(Blocks.WARPED_WART_BLOCK).mapColor(MapColor.COLOR_BLACK)
    );

    public static final DeferredBlock<Block> CHARRED_FUNGUS = registerBlock(MFBlockItemIds.CHARRED_FUNGUS,
            props -> new NetherFungusBlock(
                    MFConfiguredFeatures.PLANTED_CHARRED_KEY,
                    CHARRED_NYLIUM.get(),
                    MFBlockTags.SUPPORTS_CHARRED_ROOTS,
                    props
            ),
            _ -> Properties.ofFullCopy(Blocks.WARPED_FUNGUS).mapColor(MapColor.COLOR_GRAY)
    );

    //———————————————————————————————————————Decrepit Wood Blocks————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> DECREPIT_LEAVES = registerBlock(MFBlockItemIds.DECREPIT_LEAVES,
            properties -> new MFLeavesBlock(0.02F, MFParticleTypes.DECREPIT_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
                    .mapColor(Blocks.PALE_OAK_LEAVES.defaultMapColor())
            ));

    public static final DeferredBlock<Block> DECREPIT_SAPLING = registerBlock(MFBlockItemIds.DECREPIT_SAPLING,
            properties -> new PlantedOffGrassSaplingBlock(MFTreeGrowers.DECREPIT, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion()
                    .noCollision()
                    .mapColor(MapColor.PLANT),
                    MFBlockTags.NULLIUM
            ));

    public static final DeferredBlock<Block> DECREPIT_ROOTS = registerBlock(MFBlockItemIds.DECREPIT_ROOTS,
            properties -> new NetherRootsBlock(MFBlockTags.SUPPORTS_END_ROOTS, properties
                    .sound(SoundType.ROOTS)
                    .noOcclusion()
                    .noCollision()
                    .instabreak()
                    .replaceable()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
            ));

    public static final DeferredBlock<Block> POTTED_DECREPIT_ROOTS = registerBlockWithoutItem(MFBlockIds.POTTED_DECREPIT_ROOTS,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, DECREPIT_ROOTS, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> DECREPIT_NULLIUM = registerBlock(MFBlockItemIds.DECREPIT_NULLIUM,
            properties -> new NulliumBlock(properties
                    .sound(SoundType.NYLIUM)
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .mapColor(MapColor.TERRACOTTA_BLUE),
                    DECREPIT_ROOTS
            ));

    //———————————————————————————————————————Pallid Wood Blocks——————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> PALLID_LEAVES = registerBlock(MFBlockItemIds.PALLID_LEAVES,
            props -> new MFLeavesBlock(0.02F, MFParticleTypes.PALLID_LEAVES.get(), props),
            _ -> Properties.ofFullCopy(DECREPIT_LEAVES.get())
    );

    public static final DeferredBlock<Block> PALLID_SAPLING = registerBlock(MFBlockItemIds.PALLID_SAPLING,
            props -> new PlantedOffGrassSaplingBlock(MFTreeGrowers.PALLID, props, MFBlockTags.NULLIUM),
            _ -> Properties.ofFullCopy(DECREPIT_SAPLING.get())
    );

    public static final DeferredBlock<Block> PALLID_ROOTS = registerBlock(MFBlockItemIds.PALLID_ROOTS,
            properties -> new NetherRootsBlock(MFBlockTags.SUPPORTS_END_ROOTS, properties),
            _ -> Properties.ofFullCopy(DECREPIT_ROOTS.get())
    );

    public static final DeferredBlock<Block> POTTED_PALLID_ROOTS = registerBlockWithoutItem(MFBlockIds.POTTED_PALLID_ROOTS,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, PALLID_ROOTS, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_NULLIUM = registerBlock(MFBlockItemIds.PALLID_NULLIUM,
            props -> new NulliumBlock(props, PALLID_ROOTS),
            _ -> Properties.ofFullCopy(DECREPIT_NULLIUM.get()).mapColor(MapColor.TERRACOTTA_GREEN)
    );

    /*
     * Vertical slabs for vanilla block types.
     * Because there are so many, I will be subdividing this section into more sections
     */

    /* --- WOOD SLABS --- */

    public static final DeferredBlock<Block> OAK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.OAK_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.OAK_PLANKS)
    );

    public static final DeferredBlock<Block> SPRUCE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.SPRUCE_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)
    );

    public static final DeferredBlock<Block> BIRCH_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.BIRCH_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BIRCH_PLANKS)
    );

    public static final DeferredBlock<Block> JUNGLE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.JUNGLE_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)
    );

    public static final DeferredBlock<Block> ACACIA_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.ACACIA_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.ACACIA_PLANKS)
    );

    public static final DeferredBlock<Block> DARK_OAK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.DARK_OAK_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)
    );

    public static final DeferredBlock<Block> CRIMSON_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.CRIMSON_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)
    );

    public static final DeferredBlock<Block> WARPED_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.WARPED_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.WARPED_PLANKS)
    );

    public static final DeferredBlock<Block> MANGROVE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.MANGROVE_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)
    );

    public static final DeferredBlock<Block> CHERRY_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.CHERRY_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CHERRY_PLANKS)
    );

    public static final DeferredBlock<Block> BAMBOO_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.BAMBOO_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)
    );

    public static final DeferredBlock<Block> BAMBOO_MOSAIC_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.BAMBOO_MOSAIC_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BAMBOO_MOSAIC)
    );

    public static final DeferredBlock<Block> PALE_OAK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.PALE_OAK_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS)
    );

    /* --- STONE & DEEPSLATE SLABS --- */

    public static final DeferredBlock<Block> STONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.STONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.STONE)
    );

    public static final DeferredBlock<Block> COBBLESTONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.COBBLESTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.COBBLESTONE)
    );

    public static final DeferredBlock<Block> MOSSY_COBBLESTONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.MOSSY_COBBLESTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)
    );

    public static final DeferredBlock<Block> SMOOTH_STONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.SMOOTH_STONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_STONE)
    );

    public static final DeferredBlock<Block> STONE_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.STONE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.STONE_BRICKS)
    );

    public static final DeferredBlock<Block> MOSSY_STONE_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.MOSSY_STONE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)
    );

    public static final DeferredBlock<Block> COBBLED_DEEPSLATE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.COBBLED_DEEPSLATE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE)
    );

    public static final DeferredBlock<Block> POLISHED_DEEPSLATE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.POLISHED_DEEPSLATE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE)
    );

    public static final DeferredBlock<Block> DEEPSLATE_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.DEEPSLATE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)
    );

    public static final DeferredBlock<Block> DEEPSLATE_TILE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.DEEPSLATE_TILE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DEEPSLATE_TILES)
    );

    /* --- OTHER STONE TYPE SLABS --- */

    public static final DeferredBlock<Block> GRANITE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.GRANITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.GRANITE)
    );

    public static final DeferredBlock<Block> POLISHED_GRANITE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.POLISHED_GRANITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_GRANITE)
    );

    public static final DeferredBlock<Block> DIORITE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.DIORITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DIORITE)
    );

    public static final DeferredBlock<Block> POLISHED_DIORITE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.POLISHED_DIORITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_DIORITE)
    );

    public static final DeferredBlock<Block> ANDESITE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.ANDESITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.ANDESITE)
    );

    public static final DeferredBlock<Block> POLISHED_ANDESITE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.POLISHED_ANDESITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_ANDESITE)
    );

    public static final DeferredBlock<Block> TUFF_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.TUFF_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.TUFF)
    );

    public static final DeferredBlock<Block> POLISHED_TUFF_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.POLISHED_TUFF_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_TUFF)
    );

    public static final DeferredBlock<Block> TUFF_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.TUFF_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.TUFF_BRICKS)
    );

    /* --- SANDSTONE SLABS --- */

    public static final DeferredBlock<Block> SANDSTONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SANDSTONE)
    );

    public static final DeferredBlock<Block> SMOOTH_SANDSTONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.SMOOTH_SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE)
    );

    public static final DeferredBlock<Block> CUT_SANDSTONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.CUT_SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CUT_SANDSTONE)
    );

    public static final DeferredBlock<Block> RED_SANDSTONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.RED_SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.RED_SANDSTONE)
    );

    public static final DeferredBlock<Block> SMOOTH_RED_SANDSTONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_RED_SANDSTONE)
    );

    public static final DeferredBlock<Block> CUT_RED_SANDSTONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.CUT_RED_SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CUT_RED_SANDSTONE)
    );

    /* --- SULFUR & CINNABAR SLABS --- */

    public static final DeferredBlock<Block> SULFUR_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.SULFUR_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SULFUR)
    );

    public static final DeferredBlock<Block> POLISHED_SULFUR_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.POLISHED_SULFUR_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_SULFUR)
    );

    public static final DeferredBlock<Block> SULFUR_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.SULFUR_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SULFUR_BRICKS)
    );

    public static final DeferredBlock<Block> CINNABAR_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.CINNABAR_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CINNABAR)
    );

    public static final DeferredBlock<Block> POLISHED_CINNABAR_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.POLISHED_CINNABAR_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_CINNABAR)
    );

    public static final DeferredBlock<Block> CINNABAR_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.CINNABAR_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CINNABAR_BRICKS)
    );

    /* --- NETHER & END SLABS --- */

    public static final DeferredBlock<Block> NETHER_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.NETHER_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.NETHER_BRICKS)
    );

    public static final DeferredBlock<Block> RED_NETHER_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.RED_NETHER_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)
    );

    public static final DeferredBlock<Block> BLACKSTONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.BLACKSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BLACKSTONE)
    );

    public static final DeferredBlock<Block> POLISHED_BLACKSTONE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.POLISHED_BLACKSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE)
    );

    public static final DeferredBlock<Block> POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
    );

    public static final DeferredBlock<Block> END_STONE_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.END_STONE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.END_STONE_BRICKS)
    );

    public static final DeferredBlock<Block> PURPUR_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.PURPUR_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PURPUR_BLOCK)
    );

    public static final DeferredBlock<Block> QUARTZ_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.QUARTZ_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)
    );

    public static final DeferredBlock<Block> SMOOTH_QUARTZ_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.SMOOTH_QUARTZ_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ)
    );

    /* --- MISCELLANEOUS SLABS --- */

    public static final DeferredBlock<Block> PRISMARINE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.PRISMARINE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PRISMARINE)
    );

    public static final DeferredBlock<Block> PRISMARINE_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.PRISMARINE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)
    );

    public static final DeferredBlock<Block> DARK_PRISMARINE_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.DARK_PRISMARINE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DARK_PRISMARINE)
    );

    public static final DeferredBlock<Block> BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BRICKS)
    );

    public static final DeferredBlock<Block> MUD_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.MUD_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MUD_BRICKS)
    );

    public static final DeferredBlock<Block> RESIN_BRICK_VERTICAL_SLAB = registerVerticalSlabOrShelf(MFBlockItemIds.RESIN_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.RESIN_BRICKS)
    );

    /* --- BLOCK COLLECTION SLABS --- */

    public static final WeatheringCopperCollection<DeferredBlock<Block>> CUT_COPPER_VERTICAL_SLAB = // Because this is a WeatheringCopperCollection, all eight blocks (unaffected, exposed, weathered, and oxidised, and all of their waxed variants) are registered at the exact same time. No need to repeat myself for each one.
            registerCopperBlockSet(
                    MFBlockItemIds.CUT_COPPER_VERTICAL_SLAB,
                    WeatheringCopperVerticalSlabBlock::new, // Regular
                    WeatheringCopperVerticalSlabBlock::new, // Waxed
                    state -> Properties.ofFullCopy(Blocks.CUT_COPPER.weathering().pick(state))
            );

    public static final ColorCollection<DeferredBlock<Block>> WOOL_VERTICAL_SLAB =
            registerColouredBlockSet(
                    MFBlockItemIds.WOOL_VERTICAL_SLAB,
                    props -> new VerticalSlabBlock(true, props),
                    colour -> Properties.ofFullCopy(Blocks.WOOL.pick(colour))
            );

    public static final ColorCollection<DeferredBlock<Block>> CONCRETE_VERTICAL_SLAB =
            registerColouredBlockSet(
                    MFBlockItemIds.CONCRETE_VERTICAL_SLAB,
                    props -> new VerticalSlabBlock(false, props),
                    colour -> Properties.ofFullCopy(Blocks.CONCRETE.pick(colour))
            );


    // PILLARS

    public static final DeferredBlock<Block> TEST_PILLAR_BLOCK = registerBlock(MFBlockItemIds.TEST_COLUMN,
            PillarBlock::new,
            _ -> Properties.ofFullCopy(Blocks.OAK_PLANKS)
    );

    public static final ColorCollection<DeferredBlock<Block>> CONCRETE_PILLAR =
            registerColouredBlockSet(
                    MFBlockItemIds.CONCRETE_PILLAR,
                    PillarBlock::new,
                    colour -> Properties.ofFullCopy(Blocks.CONCRETE.pick(colour))
            );

    public static final WeatheringCopperCollection<DeferredBlock<Block>> CUT_COPPER_PILLAR =
            registerCopperBlockSet(
                    MFBlockItemIds.CUT_COPPER_PILLAR,
                    WeatheringCopperPillarBlock::new,
                    WeatheringCopperPillarBlock::new,
                    state -> Properties.ofFullCopy(Blocks.CUT_COPPER.weathering().pick(state))
            );

    // Concrete slabs and stairs

    public static final ColorCollection<DeferredBlock<Block>> CONCRETE_SLAB =
            registerColouredBlockSet(
                    MFBlockItemIds.CONCRETE_SLAB,
                    SlabBlock::new,
                    colour -> Properties.ofFullCopy(Blocks.CONCRETE.pick(colour))
            );

    public static final ColorCollection<DeferredBlock<Block>> CONCRETE_STAIRS =
            registerColouredBlockSet(
                    MFBlockItemIds.CONCRETE_STAIRS,
                    props -> new StairBlock(Blocks.CONCRETE.white().defaultBlockState(), props),
                    colour -> Properties.ofFullCopy(Blocks.CONCRETE.pick(colour))
            );

    // Flowers

    public static final DeferredBlock<Block> ROSE = registerBlock(MFBlockItemIds.ROSE,
            properties -> new FlowerBlock(MobEffects.SLOW_FALLING, 10, properties),
            _ -> Properties.ofFullCopy(Blocks.POPPY)
    );

    public static final DeferredBlock<Block> POTTED_ROSE = registerBlockWithoutItem(MFBlockIds.POTTED_ROSE,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ROSE, properties),
            _ -> Properties.ofFullCopy(Blocks.POTTED_POPPY)
    );

    public static final DeferredBlock<Block> BLUE_ROSE = registerBlock(MFBlockItemIds.BLUE_ROSE,
            properties -> new FlowerBlock(MobEffects.SPEED, 10, properties),
            _ -> Properties.ofFullCopy(ROSE.get())
    );

    public static final DeferredBlock<Block> POTTED_BLUE_ROSE = registerBlockWithoutItem(MFBlockIds.POTTED_BLUE_ROSE,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_ROSE, properties),
            _ -> Properties.ofFullCopy(POTTED_ROSE.get())
    );

    // Void anchor block
    public static final DeferredBlock<Block> VOID_ANCHOR = registerBlock(MFBlockItemIds.VOID_ANCHOR,
            properties -> new VoidAnchorBlock(properties
                    .strength(50F, 1200F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .lightLevel(state -> VoidAnchorBlock.getScaledChargeLevel(state, 15))
            ));

    // Magic block!
    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlockWithTooltip(MFBlockItemIds.MAGIC_BLOCK,
            properties -> new MagicBlock(properties
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .strength(8F, 500F)
                    .requiresCorrectToolForDrops()
                    .sound(MFSoundTypes.MAGIC_BLOCK_SOUNDS)
            ), Component.translatable("tooltip.morefeatures.magic_block"));

    // Compressor block
    public static final DeferredBlock<Block> COMPRESSOR_BLOCK = registerBlock(MFBlockItemIds.COMPRESSOR_BLOCK,
            properties -> new CompressorBlock(properties
                    .mapColor(MapColor.STONE)
                    .strength(4F, 16f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
            ));

    // Portal Block
    public static final DeferredBlock<Block> EVIL_PORTAL = registerBlockWithTooltip(MFBlockItemIds.EVIL_PORTAL,
            properties -> new EvilPortalBlock(properties
                    .strength(2f)
                    .sound(MFSoundTypes.EVIL_PORTAL_SOUNDS)
                    .pushReaction(PushReaction.BLOCK)
                    .isViewBlocking(MFBlocks::always)
                    .isValidSpawn(MFBlocks::never)
                    .isSuffocating(MFBlocks::always)
                    .isRedstoneConductor(MFBlocks::never)
            ), Component.translatable("tooltip.morefeatures.evil_portal"));

    // Conjured Ice
    public static final DeferredBlock<Block> CONJURED_ICE = registerBlockWithoutItem(MFBlockIds.CONJURED_ICE,
            ConjuredIceBlock::new,
            _ -> Properties.ofFullCopy(Blocks.FROSTED_ICE)
    );

    public static final DeferredBlock<Block> TEMPORAL_DILATOR = registerBlock(MFBlockItemIds.TEMPORAL_DILATOR,
            props -> new TemporalDilatorBlock(props
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .forceSolidOn()
                    .strength(9.5F, -1F)
                    .lightLevel(TemporalDilatorBlock::getLightLevel)
            ));

    // Icicle
    public static final DeferredBlock<Block> ICICLE = registerBlock(MFBlockItemIds.ICICLE,
            props -> new IcicleBlock(List.of(Blocks.PACKED_ICE.defaultBlockState(), Blocks.BLUE_ICE.defaultBlockState()), props),
            _ -> Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE)
                    .sound(SoundType.GLASS)
                    .instrument(NoteBlockInstrument.CHIME)
                    .friction(0.98F)
    );

    // Bush blocks
    public static final DeferredBlock<Block> BLUE_BERRY_BUSH = registerBlockWithoutItem(MFBlockIds.BLUE_BERRY_BUSH,
            properties -> new BlueBerryBushBlock(properties
                    .sound(SoundType.SWEET_BERRY_BUSH)
                    .randomTicks()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    // Register functions

    private static <T extends Block> DeferredBlock<T> registerBlockWithoutItem(ResourceKey<Block> id, Function<BlockBehaviour.Properties, T> function){
        if (MFIdentifier.isNotMfNamespace(id.identifier())){
            throw new IllegalStateException("Could not register the " + id.identifier().getPath() + " block. ID must be within the More Features namespace!");
        }
        return BLOCKS.registerBlock(id.identifier().getPath(), props -> function.apply(props.setId(id)));
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithoutItem(ResourceKey<Block> id, Function<BlockBehaviour.Properties, T> function, UnaryOperator<BlockBehaviour.Properties> propertyModifier){
        if (MFIdentifier.isNotMfNamespace(id.identifier())){
            throw new IllegalStateException("Could not register the " + id.identifier().getPath() + " block. ID must be within the More Features namespace!");
        }
        return BLOCKS.registerBlock(id.identifier().getPath(), baseProps -> {
            BlockBehaviour.Properties props = propertyModifier.apply(baseProps).setId(id);
            return function.apply(props);
        });
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithTooltip(BlockItemId id, Function<BlockBehaviour.Properties, T> function, Component... components){
        if (MFIdentifier.isNotMfNamespace(id.block().identifier())){
            throw new IllegalStateException("Could not register the " + id.block().identifier().getPath() + " block. ID must be within the More Features namespace!");
        }

        DeferredBlock<T> toReturn = BLOCKS.registerBlock(id.block().identifier().getPath(),
                props -> function.apply(props.setId(id.block())));
        registerBlockItem(id, toReturn, components);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(BlockItemId id, DeferredBlock<T> block, Component... components){
        MFItems.ITEMS.registerItem(
                id.item().identifier().getPath(),
                properties -> new BlockItem(block.get(), properties.setId(id.item()).useBlockDescriptionPrefix()){
            @SuppressWarnings("deprecation")
            @NullMarked
            @Override
            public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                for(var component : components) {
                    builder.accept(component);
                }
                super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
            }
        });
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(
            BlockItemId id,
            Function<BlockBehaviour.Properties, T> function
    ) {
        if (MFIdentifier.isNotMfNamespace(id.block().identifier())){
            throw new IllegalStateException("Could not register the " + id.block().identifier().getPath() + " block. ID must be within the More Features namespace!");
        }

        DeferredBlock<T> block = BLOCKS.registerBlock(id.block().identifier().getPath(),
                props -> function.apply(props.setId(id.block())));

        registerBlockItem(id, block);

        return block;
    }

    private static <T extends Block> void registerBlockItem(BlockItemId id, DeferredBlock<T> block) {
        MFItems.ITEMS.registerItem(
                id.item().identifier().getPath(),
                properties -> new BlockItem(block.get(), properties.setId(id.item()).useBlockDescriptionPrefix())
        );
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(
            BlockItemId id,
            Function<BlockBehaviour.Properties, T> blockFactory,
            UnaryOperator<BlockBehaviour.Properties> propertyModifier
    ) {

        // This register method is private, so ensuring all blocks in this class are registered in the More Features namespace only forces me to follow good practice without hindering other devs
        if (MFIdentifier.isNotMfNamespace(id.block().identifier())){
            throw new IllegalStateException("Could not register the " + id.block().identifier().getPath() + " block. ID must be within the More Features namespace!");
        }
        DeferredBlock<T> block = BLOCKS.registerBlock(id.block().identifier().getPath(),
                baseProps -> {
                    BlockBehaviour.Properties props = propertyModifier.apply(baseProps).setId(id.block());
                    return blockFactory.apply(props);
                }
        );

        registerBlockItem(id, block);
        return block;
    }

    private static WeatheringCopperCollection<DeferredBlock<Block>> registerCopperBlockSet(
            WeatheringCopperCollection<BlockItemId> ids,
            BiFunction<WeatheringCopper.WeatherState, Properties, ? extends Block> weatheringFactory,
            BiFunction<WeatheringCopper.WeatherState, Properties, ? extends Block> waxedFactory,
            Function<WeatheringCopper.WeatherState, Properties> propertiesSupplier) {

        return ids.apply(
                weatheringIds -> zipMap(
                        WeatheringCopperCollection.STATES,
                        weatheringIds,
                        (state, id) -> {
                            String name = id.block().identifier().getPath();
                            Properties props = propertiesSupplier.apply(state).setId(id.block());

                            DeferredBlock<Block> toReturn = BLOCKS.register(name, () -> weatheringFactory.apply(state, props));

                            registerBlockItem(id, toReturn);

                            return toReturn;
                        }
                ),
                waxedIds -> zipMap(
                        WeatheringCopperCollection.STATES,
                        waxedIds,
                        (state, id) -> {
                            String name = id.block().identifier().getPath();
                            if (MFIdentifier.isNotMfNamespace(id.block().identifier())){
                                throw new IllegalStateException("Failed to register " + name + " within the " + ids.weathering().unaffected().block().identifier().getPath() + " copper block set. ID must be within the More Features namespace!");
                            }
                            Properties props = propertiesSupplier.apply(state).setId(id.block());

                            DeferredBlock<Block> toReturn = BLOCKS.register(name, () -> waxedFactory.apply(state, props));

                            registerBlockItem(id, toReturn);

                            return toReturn;
                        }
                )
        );
    }

    private static ColorCollection<DeferredBlock<Block>> registerColouredBlockSet(
            ColorCollection<BlockItemId> ids,
            Function<Properties, ? extends Block> factory,
            Function<DyeColor, Properties> propertiesSupplier){

        return ColorCollection.zipMap(
                ColorCollection.VALUES,
                ids,
                (color, id) -> {
                    String name = id.block().identifier().getPath();
                    if (MFIdentifier.isNotMfNamespace(id.block().identifier())){
                        throw new IllegalStateException("Failed to register " + name + " within a coloured block set. ID must be within the More Features namespace!");
                    }
                    Properties props = propertiesSupplier.apply(color).setId(id.block());
                    DeferredBlock<Block> block = BLOCKS.register(name, () -> factory.apply(props));

                    // Register the BlockItem, just like with copper
                    registerBlockItem(id, block);

                    return block;
                }
        );
    }

    private static <T extends Block> DeferredBlock<T> registerVerticalSlabOrShelf(
            BlockItemId id,
            boolean isFlammable,
            BiFunction<Boolean, BlockBehaviour.Properties, T> blockFactory,
            UnaryOperator<BlockBehaviour.Properties> propertyModifier
    ) {
        if (MFIdentifier.isNotMfNamespace(id.block().identifier())){
            throw new IllegalStateException("Could not register the " + id.block().identifier().getPath() + " block. ID must be within the More Features namespace!");
        }

        DeferredBlock<T> block = BLOCKS.registerBlock(id.block().identifier().getPath(),
                baseProps -> {
                    BlockBehaviour.Properties props = propertyModifier.apply(baseProps).setId(id.block());
                    // Pass both the boolean and the modified properties into the constructor
                    return blockFactory.apply(isFlammable, props);
                }
        );

        registerBlockItem(id, block);
        return block;
    }

    private static DeferredBlock<StairBlock> registerStair(BlockItemId stairId, Supplier<Block> fullBlock){
        return registerBlock(stairId, props -> new StairBlock(fullBlock.get().defaultBlockState(), props),
                _ -> Properties.ofFullCopy(fullBlock.get()));
    }

    private static DeferredBlock<SlabBlock> registerSlab(BlockItemId slabId, Supplier<Block> fullBlock){
        return registerBlock(slabId, SlabBlock::new,
                _ -> Properties.ofFullCopy(fullBlock.get()));
    }

    // These exact booleans exist in the vanilla Blocks class, but have private access

    // Used by the isRedstoneConductor, isViewBlocking, and isSuffocating properties

    private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    // Used by the isValidSpawn property

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
        return true;
    }

    private static Properties woodProps(WoodSetType wood, Properties props){
        return baseWoodProps(wood, props, false);
    }

    private static Properties logProps(WoodSetType wood, Properties props){
        return baseWoodProps(wood, props, true);
    }

    private static Properties shelfProps(WoodSetType wood, Properties props){
        return baseWoodProps(wood, props, false).sound(SoundType.SHELF);
    }

    private static Properties baseWoodProps(WoodSetType wood, Properties props, boolean log){
        Properties finalProps = props.mapColor(wood.getMapColor()).sound(log ? wood.getLogSoundType() : wood.getMainSoundType())
                .strength(2.0F, 8.0F).isValidSpawn(MFBlocks::never);

        if(wood.isFlammable()) finalProps.ignitedByLava();

        return finalProps;
    }

    // Register method called in the mod event bus

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Blocks registered -> Performed by: " + MoreFeatures.MODID);
    }
}