package com.mohigster.morefeatures.block;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.custom.*;
import com.mohigster.morefeatures.block.custom.flammable.*;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.WeatheringCopperVerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.blocktype.MFBlockSetType;
import com.mohigster.morefeatures.block.custom.blocktype.MFWoodType;
import com.mohigster.morefeatures.references.MFBlockIds;
import com.mohigster.morefeatures.references.MFBlockItemIds;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.particles.MFParticleTypes;
import com.mohigster.morefeatures.references.MFIdentifier;
import com.mohigster.morefeatures.sound.MFSounds;
import com.mohigster.morefeatures.tag.MFBlockTags;
import com.mohigster.morefeatures.worldgen.tree.MFTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
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
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

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
            _ -> Properties.ofFullCopy(RAW_MAGNESIUM_BLOCK.get())
    );

    public static final DeferredBlock<Block> DEEPSLATE_MAGNESIUM_ORE = registerBlock(MFBlockItemIds.DEEPSLATE_MAGNESIUM_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties),
            _ -> Properties.ofFullCopy(RAW_MAGNESIUM_BLOCK.get()).sound(SoundType.DEEPSLATE)
    );

    //———————————————————————————————————————Bismuth Blocks——————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock(MFBlockItemIds.BISMUTH_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(35f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock(MFBlockItemIds.BISMUTH_BLOCK,
            properties -> new Block(properties
                    .strength(60f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_BISMUTH_BLOCK = registerBlock(MFBlockItemIds.RAW_BISMUTH_BLOCK,
            properties -> new Block(properties
                    .strength(50f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    //———————————————————————————————————————Azurite Blocks——————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> AZURITE_ORE = registerBlock(MFBlockItemIds.AZURITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_AZURITE_ORE = registerBlock(MFBlockItemIds.DEEPSLATE_AZURITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));

    public static final DeferredBlock<Block> NETHER_AZURITE_ORE = registerBlock(MFBlockItemIds.NETHER_AZURITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHER_ORE)
            ));

    public static final DeferredBlock<Block> END_AZURITE_ORE = registerBlock(MFBlockItemIds.END_AZURITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> AZURITE_BLOCK = registerBlock(MFBlockItemIds.AZURITE_BLOCK,
            properties -> new Block(properties
                    .strength(4f, 16f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
                    .instrument(NoteBlockInstrument.CHIME)
                    .isRedstoneConductor(MFBlocks::always)
                    .mapColor(MapColor.COLOR_BLUE)
            ));

    public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = registerBlock(MFBlockItemIds.RAW_AZURITE_BLOCK,
            Block::new,
            _ -> Properties.ofFullCopy(AZURITE_BLOCK.get())
    );

    public static final DeferredBlock<Block> AZURITE_STAIRS = registerBlock(MFBlockItemIds.AZURITE_STAIRS,
            props -> new StairBlock(MFBlocks.AZURITE_BLOCK.get().defaultBlockState(), props),
            _ -> Properties.ofFullCopy(AZURITE_BLOCK.get())
    );

    public static final DeferredBlock<Block> AZURITE_SLAB = registerBlock(MFBlockItemIds.AZURITE_SLAB,
            SlabBlock::new,
            _ -> Properties.ofFullCopy(AZURITE_BLOCK.get())
    );

    public static final DeferredBlock<Block> AZURITE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.AZURITE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            _ -> Properties.ofFullCopy(AZURITE_BLOCK.get())
    );

    public static final DeferredBlock<Block> AZURITE_PRESSURE_PLATE = registerBlock(MFBlockItemIds.AZURITE_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(MFBlockSetType.AZURITE, properties
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> AZURITE_BUTTON = registerBlock(MFBlockItemIds.AZURITE_BUTTON,
            properties -> new ButtonBlock(MFBlockSetType.AZURITE, 20, properties
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(MFBlocks::never) // Buttons do not conduct redstone, even though they can activate it
            ));

    public static final DeferredBlock<Block> AZURITE_FENCE = registerBlock(MFBlockItemIds.AZURITE_FENCE,
            properties -> new FenceBlock(properties
                    .strength(2F, 8F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
                    .isRedstoneConductor(MFBlocks::never)
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
                    .isRedstoneConductor(MFBlocks::never) // Same with doors / trapdoors
            ));

    public static final DeferredBlock<Block> AZURITE_TRAPDOOR = registerBlock(MFBlockItemIds.AZURITE_TRAPDOOR,
            properties -> new TrapDoorBlock(MFBlockSetType.AZURITE, properties),
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

    public static final DeferredBlock<Block> AZURITE_SHELF = registerBlock(MFBlockItemIds.AZURITE_SHELF,
            props -> new MFShelfBlock(false, props),
            _ -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF)
                    .mapColor(MapColor.COLOR_BLUE)
                    .sound(SoundType.MEDIUM_AMETHYST_BUD)
    );

    //———————————————————————————————————————Fluorite Blocks—————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> FLUORITE_ORE = registerBlock(MFBlockItemIds.FLUORITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 16f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .isRedstoneConductor(MFBlocks::always)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_FLUORITE_ORE = registerBlock(MFBlockItemIds.DEEPSLATE_FLUORITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(6f, 24f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .isRedstoneConductor(MFBlocks::always)
            ));

    public static final DeferredBlock<Block> NETHER_FLUORITE_ORE = registerBlock(MFBlockItemIds.NETHER_FLUORITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4F, 16F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHER_ORE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .isRedstoneConductor(MFBlocks::always)
            ));

    public static final DeferredBlock<Block> END_FLUORITE_ORE = registerBlock(MFBlockItemIds.END_FLUORITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4F, 16F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .isRedstoneConductor(MFBlocks::always)
            ));

    public static final DeferredBlock<Block> FLUORITE_BLOCK = registerBlock(MFBlockItemIds.FLUORITE_BLOCK,
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_FLUORITE_BLOCK = registerBlock(MFBlockItemIds.RAW_FLUORITE_BLOCK,
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> FLUORITE_STAIRS = registerBlock(MFBlockItemIds.FLUORITE_STAIRS,
            properties -> new StairBlock(MFBlocks.FLUORITE_BLOCK.get().defaultBlockState(), properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
                    .isRedstoneConductor(MFBlocks::never)
            ));

    public static final DeferredBlock<Block> FLUORITE_SLAB = registerBlock(MFBlockItemIds.FLUORITE_SLAB,
            properties -> new SlabBlock(properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> FLUORITE_DOOR = registerBlock(MFBlockItemIds.FLUORITE_DOOR,
            properties -> new DoorBlock(MFBlockSetType.FLUORITE, properties
                    .strength(2F, 6F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(MFBlocks::never)
            ));

    public static final DeferredBlock<Block> FLUORITE_TRAPDOOR = registerBlock(MFBlockItemIds.FLUORITE_TRAPDOOR,
            properties -> new TrapDoorBlock(MFBlockSetType.FLUORITE, properties
                    .strength(2F, 6F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(MFBlocks::never)
            ));

    public static final DeferredBlock<Block> FLUORITE_SIGN = registerBlockWithoutItem(MFBlockIds.FLUORITE_SIGN,
            properties -> new MFStandingSignBlock(MFWoodType.FLUORITE, properties),
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

    //———————————————————————————————————————Bloodwood Blocks————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> BLOODWOOD_LOG = registerBlock(MFBlockItemIds.BLOODWOOD_LOG,
            properties -> new MFFlammableRotatedPillarBlock(properties
                    .mapColor(MapColor.COLOR_RED)
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASS)
            ));

    public static final DeferredBlock<Block> BLOODWOOD = registerBlock(MFBlockItemIds.BLOODWOOD,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(BLOODWOOD_LOG.get()).mapColor(MapColor.COLOR_GRAY)
    );

    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_LOG = registerBlock(MFBlockItemIds.STRIPPED_BLOODWOOD_LOG,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(BLOODWOOD_LOG.get())
    );

    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD = registerBlock(MFBlockItemIds.STRIPPED_BLOODWOOD,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(BLOODWOOD_LOG.get())
    );

    public static final DeferredBlock<Block> BLOODWOOD_PLANKS = registerBlock(MFBlockItemIds.BLOODWOOD_PLANKS,
            MFFlammableBlock::new,
            _ -> Properties.ofFullCopy(BLOODWOOD_LOG.get())
    );

    public static final DeferredBlock<Block> BLOODWOOD_LEAVES = registerBlock(MFBlockItemIds.BLOODWOOD_LEAVES,
            properties -> new MFLeavesBlock(0.03F, MFParticleTypes.BLOODWOOD_LEAVES.get(), properties
                    .mapColor(MapColor.PLANT)
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .isValidSpawn(MFBlocks::never)
                    .isRedstoneConductor(MFBlocks::never)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> BLOODWOOD_SAPLING = registerBlock(MFBlockItemIds.BLOODWOOD_SAPLING,
            properties -> new SaplingBlock(MFTreeGrowers.BLOODWOOD, properties
                    .mapColor(MapColor.PLANT)
                    .randomTicks()
                    .instabreak()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS)
            ));

    public static final DeferredBlock<Block> POTTED_BLOODWOOD_SAPLING = registerBlockWithoutItem(MFBlockIds.POTTED_BLOODWOOD_SAPLING,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, BLOODWOOD_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_STAIRS = registerBlock(MFBlockItemIds.BLOODWOOD_STAIRS,
            properties -> new MFFlammableStairBlock(MFBlocks.BLOODWOOD_PLANKS.get().defaultBlockState(), properties),
            _ -> Properties.ofFullCopy(BLOODWOOD_PLANKS.get())
    );

    public static final DeferredBlock<Block> BLOODWOOD_SLAB = registerBlock(MFBlockItemIds.BLOODWOOD_SLAB,
            MFFlammableSlabBlock::new,
            _ -> Properties.ofFullCopy(BLOODWOOD_PLANKS.get())
    );

    public static final DeferredBlock<VerticalSlabBlock> BLOODWOOD_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.BLOODWOOD_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(BLOODWOOD_PLANKS.get())
    );

    public static final DeferredBlock<Block> BLOODWOOD_FENCE = registerBlock(MFBlockItemIds.BLOODWOOD_FENCE,
            MFFlammableFenceBlock::new,
            _ -> Properties.ofFullCopy(BLOODWOOD_PLANKS.get())
                    .isValidSpawn(MFBlocks::never)
                    .isRedstoneConductor(MFBlocks::never)
                    .isSuffocating(MFBlocks::never)
    );

    public static final DeferredBlock<Block> BLOODWOOD_FENCE_GATE = registerBlock(MFBlockItemIds.BLOODWOOD_FENCE_GATE,
            props -> new MFFlammableFenceGateBlock(MFWoodType.BLOODWOOD, props),
            _ -> Properties.ofFullCopy(BLOODWOOD_FENCE.get())
    );

    public static final DeferredBlock<Block> BLOODWOOD_PRESSURE_PLATE = registerBlock(MFBlockItemIds.BLOODWOOD_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(MFBlockSetType.BLOODWOOD, properties
                    .sound(SoundType.WOOD)
                    .strength(2F, 2F)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_BUTTON = registerBlock(MFBlockItemIds.BLOODWOOD_BUTTON,
            properties -> new ButtonBlock(MFBlockSetType.BLOODWOOD, 20, properties
                    .sound(SoundType.WOOD)
                    .strength(2F, 2F)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_SIGN = registerBlockWithoutItem(MFBlockIds.BLOODWOOD_SIGN,
            props -> new MFStandingSignBlock(MFWoodType.BLOODWOOD, props),
            _ -> Properties.ofFullCopy(BLOODWOOD_PLANKS.get()).mapColor(MapColor.NONE)
    );

    public static final DeferredBlock<Block> BLOODWOOD_WALL_SIGN = registerBlockWithoutItem(MFBlockIds.BLOODWOOD_WALL_SIGN,
            props -> new MFWallSignBlock(MFWoodType.BLOODWOOD, props),
            _ -> Properties.ofFullCopy(BLOODWOOD_SIGN.get())
    );

    // Ceiling sign
    public static final DeferredBlock<Block> BLOODWOOD_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.BLOODWOOD_HANGING_SIGN,
            props -> new MFCeilingHangingSignBlock(MFWoodType.BLOODWOOD, props),
            _ -> Properties.ofFullCopy(BLOODWOOD_SIGN.get())
    );

    // Wall sign
    public static final DeferredBlock<Block> BLOODWOOD_WALL_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.BLOODWOOD_WALL_HANGING_SIGN,
            props -> new MFWallHangingSignBlock(MFWoodType.BLOODWOOD, props),
            _ -> Properties.ofFullCopy(BLOODWOOD_SIGN.get())
    );

    public static final DeferredBlock<Block> BLOODWOOD_SHELF = registerBlock(MFBlockItemIds.BLOODWOOD_SHELF,
            props -> new MFShelfBlock(true, props),
            _ -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.COLOR_RED)
    );

    //———————————————————————————————————————Tainted Wood Blocks—————————————————————————————————————————————————————————————————————


    public static final DeferredBlock<Block> TAINTED_LOG = registerBlock(MFBlockItemIds.TAINTED_LOG,
            properties -> new MFFlammableRotatedPillarBlock(properties
                    .mapColor(MapColor.COLOR_PURPLE)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(2F, 4F)
                    .sound(SoundType.WOOD)
                    .isRedstoneConductor(MFBlocks::always)
            ));

    public static final DeferredBlock<Block> TAINTED_WOOD = registerBlock(MFBlockItemIds.TAINTED_WOOD,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(TAINTED_LOG.get()).mapColor(MapColor.COLOR_GRAY)
    );

    public static final DeferredBlock<Block> STRIPPED_TAINTED_LOG = registerBlock(MFBlockItemIds.STRIPPED_TAINTED_LOG,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(TAINTED_LOG.get())
    );


    public static final DeferredBlock<Block> STRIPPED_TAINTED_WOOD = registerBlock(MFBlockItemIds.STRIPPED_TAINTED_WOOD,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(TAINTED_LOG.get())
    );

    public static final DeferredBlock<Block> TAINTED_PLANKS = registerBlock(MFBlockItemIds.TAINTED_PLANKS,
            MFFlammableBlock::new,
            _ -> Properties.ofFullCopy(TAINTED_LOG.get())
    );

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

    public static final DeferredBlock<Block> POTTED_TAINTED_SAPLING = registerBlockWithoutItem(MFBlockIds.POTTED_TAINTED_SAPLING,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, TAINTED_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> TAINTED_STAIRS = registerBlock(MFBlockItemIds.TAINTED_STAIRS,
            props -> new MFFlammableStairBlock(MFBlocks.TAINTED_PLANKS.get().defaultBlockState(), props),
            _ -> Properties.ofFullCopy(TAINTED_LOG.get())
    );

    public static final DeferredBlock<Block> TAINTED_SLAB = registerBlock(MFBlockItemIds.TAINTED_SLAB,
            MFFlammableSlabBlock::new,
            _ -> Properties.ofFullCopy(TAINTED_LOG.get())
    );

    public static final DeferredBlock<Block> TAINTED_VERTICAL_SLAB = registerBlock(MFBlockItemIds.TAINTED_VERTICAL_SLAB,
            properties -> new VerticalSlabBlock(true, properties),
            _ -> Properties.ofFullCopy(TAINTED_PLANKS.get())
    );

    public static final DeferredBlock<Block> TAINTED_FENCE = registerBlock(MFBlockItemIds.TAINTED_FENCE,
            properties -> new MFFlammableFenceBlock(properties
                    .strength(2F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> TAINTED_FENCE_GATE = registerBlock(MFBlockItemIds.TAINTED_FENCE_GATE,
            properties -> new MFFlammableFenceGateBlock(MFWoodType.TAINTED, properties
                    .strength(2F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> TAINTED_PRESSURE_PLATE = registerBlock(MFBlockItemIds.TAINTED_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(MFBlockSetType.TAINTED, properties
                    .sound(SoundType.WOOD)
                    .strength(2F, 2F)
            ));

    public static final DeferredBlock<Block> TAINTED_BUTTON = registerBlock(MFBlockItemIds.TAINTED_BUTTON,
            properties -> new ButtonBlock(MFBlockSetType.TAINTED, 20, properties
                    .sound(SoundType.WOOD)
                    .strength(2F, 2F)
            ));

    public static final DeferredBlock<Block> TAINTED_SIGN = registerBlockWithoutItem(MFBlockIds.TAINTED_SIGN,
            properties -> new MFStandingSignBlock(MFWoodType.TAINTED, properties
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> TAINTED_WALL_SIGN = registerBlockWithoutItem(MFBlockIds.TAINTED_WALL_SIGN,
            properties -> new MFWallSignBlock(MFWoodType.TAINTED, properties
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
            ));

    // Ceiling sign
    public static final DeferredBlock<Block> TAINTED_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.TAINTED_HANGING_SIGN,
            properties -> new MFCeilingHangingSignBlock(MFWoodType.TAINTED, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
            ));

    // Wall sign
    public static final DeferredBlock<Block> TAINTED_WALL_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.TAINTED_WALL_HANGING_SIGN,
            properties -> new MFWallHangingSignBlock(MFWoodType.TAINTED, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> TAINTED_SHELF = registerBlock(MFBlockItemIds.TAINTED_SHELF,
            props -> new MFShelfBlock(true, props),
            _ -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.COLOR_PURPLE)
    );

    //———————————————————————————————————————Palm Wood Blocks————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> PALM_LOG = registerBlock(MFBlockItemIds.PALM_LOG,
            properties -> new MFFlammableRotatedPillarBlock(properties
                    .mapColor(MapColor.COLOR_YELLOW)
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .isRedstoneConductor(MFBlocks::always)
                    .instrument(NoteBlockInstrument.BASEDRUM)
            ));

    public static final DeferredBlock<Block> PALM_WOOD = registerBlock(MFBlockItemIds.PALM_WOOD,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(PALM_LOG.get()).mapColor(MapColor.TERRACOTTA_YELLOW)
    );

    public static final DeferredBlock<Block> STRIPPED_PALM_LOG = registerBlock(MFBlockItemIds.STRIPPED_PALM_LOG,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(PALM_LOG.get())
    );

    public static final DeferredBlock<Block> STRIPPED_PALM_WOOD = registerBlock(MFBlockItemIds.STRIPPED_PALM_WOOD,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(PALM_LOG.get())
    );

    public static final DeferredBlock<Block> PALM_PLANKS = registerBlock(MFBlockItemIds.PALM_PLANKS,
            MFFlammableBlock::new,
            _ -> Properties.ofFullCopy(PALM_LOG.get())
    );

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
                    Blocks.SAND // The block that the sapling can be planted on. You can also pass in a block tag here.
            ));

    public static final DeferredBlock<Block> POTTED_PALM_SAPLING = registerBlockWithoutItem(MFBlockIds.POTTED_PALM_SAPLING,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, PALM_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALM_STAIRS = registerBlock(MFBlockItemIds.PALM_STAIRS,
            properties -> new MFFlammableStairBlock(MFBlocks.PALM_PLANKS.get().defaultBlockState(), properties
                    .strength(3f)
                    .ignitedByLava()
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_SLAB = registerBlock(MFBlockItemIds.PALM_SLAB,
            MFFlammableSlabBlock::new,
            _ -> Properties.ofFullCopy(PALM_PLANKS.get())
    );

    public static final DeferredBlock<Block> PALM_VERTICAL_SLAB = registerBlock(MFBlockItemIds.PALM_VERTICAL_SLAB,
            properties -> new VerticalSlabBlock(true, properties),
            _ -> Properties.ofFullCopy(PALM_PLANKS.get())
    );

    public static final DeferredBlock<Block> PALM_FENCE = registerBlock(MFBlockItemIds.PALM_FENCE,
            MFFlammableFenceBlock::new,
            _ -> Properties.ofFullCopy(PALM_PLANKS.get())
    );

    public static final DeferredBlock<Block> PALM_FENCE_GATE = registerBlock(MFBlockItemIds.PALM_FENCE_GATE,
            props -> new MFFlammableFenceGateBlock(MFWoodType.PALM, props),
            _ -> Properties.ofFullCopy(PALM_PLANKS.get())
    );

    // Standing sign
    public static final DeferredBlock<Block> PALM_SIGN = registerBlockWithoutItem(MFBlockIds.PALM_SIGN,
            properties -> new MFStandingSignBlock(MFWoodType.PALM, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
            ));

    // Wall sign
    public static final DeferredBlock<Block> PALM_WALL_SIGN = registerBlockWithoutItem(MFBlockIds.PALM_WALL_SIGN,
            properties -> new MFWallSignBlock(MFWoodType.PALM, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
            ));

    // Ceiling hanging sign
    public static final DeferredBlock<Block> PALM_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.PALM_HANGING_SIGN,
            properties -> new MFCeilingHangingSignBlock(MFWoodType.PALM, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
            ));

    // Wall hanging sign
    public static final DeferredBlock<Block> PALM_WALL_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.PALM_WALL_HANGING_SIGN,
            properties -> new MFWallHangingSignBlock(MFWoodType.PALM, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_TRAPDOOR = registerBlock(MFBlockItemIds.PALM_TRAPDOOR,
            properties -> new TrapDoorBlock(MFBlockSetType.PALM, properties
                    .strength(2f, 2f)
                    .noOcclusion()
            ));

    public static final DeferredBlock<Block> PALM_DOOR = registerBlock(MFBlockItemIds.PALM_DOOR,
            properties -> new DoorBlock(MFBlockSetType.PALM, properties
                    .strength(2f, 2f)
                    .noOcclusion()
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(MFBlocks::never)
            ));

    public static final DeferredBlock<Block> PALM_PRESSURE_PLATE = registerBlock(MFBlockItemIds.PALM_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(MFBlockSetType.PALM, properties
                    .strength(2f)
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(MFBlocks::never)
            ));

    public static final DeferredBlock<Block> PALM_BUTTON = registerBlock(MFBlockItemIds.PALM_BUTTON,
            properties -> new ButtonBlock(MFBlockSetType.PALM, 20, properties
                    .strength(2f)
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .isRedstoneConductor(MFBlocks::never)
            ));

    public static final DeferredBlock<Block> PALM_SHELF = registerBlock(MFBlockItemIds.PALM_SHELF,
            props -> new MFShelfBlock(true, props),
            _ -> Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.COLOR_YELLOW)
    );

    //———————————————————————————————————————Decrepit Wood Blocks————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> DECREPIT_LOG = registerBlock(MFBlockItemIds.DECREPIT_LOG,
            properties -> new MFFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .mapColor(MapColor.TERRACOTTA_BLUE)
            ));

    public static final DeferredBlock<Block> DECREPIT_WOOD = registerBlock(MFBlockItemIds.DECREPIT_WOOD,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(DECREPIT_LOG.get()).mapColor(MapColor.COLOR_BLACK)
    );

    public static final DeferredBlock<Block> STRIPPED_DECREPIT_LOG = registerBlock(MFBlockItemIds.STRIPPED_DECREPIT_LOG,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(DECREPIT_LOG.get())
    );

    public static final DeferredBlock<Block> STRIPPED_DECREPIT_WOOD = registerBlock(MFBlockItemIds.STRIPPED_DECREPIT_WOOD,
            MFFlammableRotatedPillarBlock::new,
            _ -> Properties.ofFullCopy(DECREPIT_LOG.get())
    );

    public static final DeferredBlock<Block> DECREPIT_PLANKS = registerBlock(MFBlockItemIds.DECREPIT_PLANKS,
            MFFlammableBlock::new,
            _ -> Properties.ofFullCopy(DECREPIT_LOG.get()).sound(SoundType.NETHER_WOOD)
    );

    public static final DeferredBlock<Block> DECREPIT_LEAVES = registerBlock(MFBlockItemIds.DECREPIT_LEAVES,
            properties -> new MFLeavesBlock(0.02F, MFParticleTypes.DECREPIT_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
                    .mapColor(Blocks.PALE_OAK_LEAVES.defaultMapColor())
            ));

    public static final DeferredBlock<Block> DECREPIT_NULLIUM = registerBlock(MFBlockItemIds.DECREPIT_NULLIUM,
            properties -> new NulliumBlock(properties
                    .sound(SoundType.NYLIUM)
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .mapColor(MapColor.TERRACOTTA_BLUE)
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

    public static final DeferredBlock<Block> POTTED_DECREPIT_SAPLING = registerBlockWithoutItem(MFBlockIds.POTTED_DECREPIT_SAPLING, // BlockIds are just ResourceKeys, but it makes referencing them more consistent
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, DECREPIT_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> DECREPIT_STAIRS = registerBlock(MFBlockItemIds.DECREPIT_STAIRS,
            properties -> new MFFlammableStairBlock(MFBlocks.DECREPIT_PLANKS.get().defaultBlockState(), properties
                    .strength(2f, 2f)
                    .ignitedByLava()
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_SLAB = registerBlock(MFBlockItemIds.DECREPIT_SLAB,
            properties -> new MFFlammableSlabBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> DECREPIT_VERTICAL_SLAB = registerBlock(MFBlockItemIds.DECREPIT_VERTICAL_SLAB,
            properties -> new VerticalSlabBlock(true, properties),
            _ -> Properties.ofFullCopy(DECREPIT_PLANKS.get())
    );

    public static final DeferredBlock<Block> DECREPIT_FENCE = registerBlock(MFBlockItemIds.DECREPIT_FENCE,
            properties -> new MFFlammableFenceBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_FENCE_GATE = registerBlock(MFBlockItemIds.DECREPIT_FENCE_GATE,
            properties -> new MFFlammableFenceGateBlock(MFWoodType.DECREPIT, properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_PRESSURE_PLATE = registerBlock(MFBlockItemIds.DECREPIT_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(MFBlockSetType.DECREPIT, properties
                    .strength(2f)
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> DECREPIT_BUTTON = registerBlock(MFBlockItemIds.DECREPIT_BUTTON,
            properties -> new ButtonBlock(MFBlockSetType.DECREPIT, 20, properties
                    .strength(2f)
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
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

    public static final DeferredBlock<Block> DECREPIT_SIGN = registerBlockWithoutItem(MFBlockIds.DECREPIT_SIGN,
            properties -> new MFStandingSignBlock(MFWoodType.DECREPIT, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_WALL_SIGN = registerBlockWithoutItem(MFBlockIds.DECREPIT_WALL_SIGN,
            properties -> new MFWallSignBlock(MFWoodType.DECREPIT, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    // Ceiling sign
    public static final DeferredBlock<Block> DECREPIT_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.DECREPIT_HANGING_SIGN,
            properties -> new MFCeilingHangingSignBlock(MFWoodType.DECREPIT, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    // Wall sign
    public static final DeferredBlock<Block> DECREPIT_WALL_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.DECREPIT_WALL_HANGING_SIGN,
            properties -> new MFWallHangingSignBlock(MFWoodType.DECREPIT, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_SHELF = registerBlock(MFBlockItemIds.DECREPIT_SHELF,
            props -> new MFShelfBlock(true, props),
            _ -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.TERRACOTTA_BLUE)
    );

    //———————————————————————————————————————Pallid Wood Blocks——————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> PALLID_LOG = registerBlock(MFBlockItemIds.PALLID_LOG,
            properties -> new MFFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .mapColor(MapColor.TERRACOTTA_GREEN)
            ));

    public static final DeferredBlock<Block> PALLID_WOOD = registerBlock(MFBlockItemIds.PALLID_WOOD,
            MFFlammableRotatedPillarBlock::new,
            _ -> BlockBehaviour.Properties.ofFullCopy(PALLID_LOG.get()).mapColor(MapColor.COLOR_GRAY)
    );

    public static final DeferredBlock<Block> STRIPPED_PALLID_LOG = registerBlock(MFBlockItemIds.STRIPPED_PALLID_LOG,
            MFFlammableRotatedPillarBlock::new,
            _ -> BlockBehaviour.Properties.ofFullCopy(PALLID_LOG.get())
    );

    public static final DeferredBlock<Block> STRIPPED_PALLID_WOOD = registerBlock(MFBlockItemIds.STRIPPED_PALLID_WOOD,
            MFFlammableRotatedPillarBlock::new,
            _ -> BlockBehaviour.Properties.ofFullCopy(PALLID_LOG.get())
    );

    public static final DeferredBlock<Block> PALLID_PLANKS = registerBlock(MFBlockItemIds.PALLID_PLANKS,
            MFFlammableBlock::new,
            _ -> BlockBehaviour.Properties.ofFullCopy(PALLID_LOG.get()).sound(SoundType.NETHER_WOOD)
    );

    public static final DeferredBlock<Block> PALLID_LEAVES = registerBlock(MFBlockItemIds.PALLID_LEAVES,
            properties -> new MFLeavesBlock(0.02F, MFParticleTypes.PALLID_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
                    .mapColor(Blocks.PALE_OAK_LEAVES.defaultMapColor())
            ));

    public static final DeferredBlock<Block> PALLID_NULLIUM = registerBlock(MFBlockItemIds.PALLID_NULLIUM,
            properties -> new NulliumBlock(properties
                    .sound(SoundType.NYLIUM)
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .mapColor(MapColor.TERRACOTTA_GREEN)
            )
    );

    public static final DeferredBlock<Block> PALLID_SAPLING = registerBlock(MFBlockItemIds.PALLID_SAPLING,
            properties -> new PlantedOffGrassSaplingBlock(MFTreeGrowers.PALLID, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion()
                    .noCollision()
                    .mapColor(MapColor.PLANT),
                    MFBlockTags.NULLIUM
            ));

    public static final DeferredBlock<Block> POTTED_PALLID_SAPLING = registerBlockWithoutItem(MFBlockIds.POTTED_PALLID_SAPLING,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, PALLID_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_STAIRS = registerBlock(MFBlockItemIds.PALLID_STAIRS,
            properties -> new MFFlammableStairBlock(MFBlocks.DECREPIT_PLANKS.get().defaultBlockState(), properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALLID_SLAB = registerBlock(MFBlockItemIds.PALLID_SLAB,
            properties -> new MFFlammableSlabBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALLID_VERTICAL_SLAB = registerBlock(MFBlockItemIds.PALLID_VERTICAL_SLAB,
            properties -> new VerticalSlabBlock(true, properties),
            _ -> Properties.ofFullCopy(PALLID_PLANKS.get())
    );

    public static final DeferredBlock<Block> PALLID_FENCE = registerBlock(MFBlockItemIds.PALLID_FENCE,
            MFFlammableFenceBlock::new,
            _ -> Properties.ofFullCopy(PALLID_PLANKS.get())
    );

    public static final DeferredBlock<Block> PALLID_FENCE_GATE = registerBlock(MFBlockItemIds.PALLID_FENCE_GATE,
            properties -> new MFFlammableFenceGateBlock(MFWoodType.PALLID, properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_PRESSURE_PLATE = registerBlock(MFBlockItemIds.PALLID_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(MFBlockSetType.PALLID, properties
                    .strength(2F)
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_BUTTON = registerBlock(MFBlockItemIds.PALLID_BUTTON,
            properties -> new ButtonBlock(MFBlockSetType.PALLID, 20, properties
                    .strength(2F)
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_ROOTS = registerBlock(MFBlockItemIds.PALLID_ROOTS,
            properties -> new NetherRootsBlock(MFBlockTags.SUPPORTS_END_ROOTS, properties
                    .sound(SoundType.ROOTS)
                    .noOcclusion()
                    .noCollision()
                    .instabreak()
                    .replaceable()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
            ));

    public static final DeferredBlock<Block> POTTED_PALLID_ROOTS = registerBlockWithoutItem(MFBlockIds.POTTED_PALLID_ROOTS,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, PALLID_ROOTS, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_SIGN = registerBlockWithoutItem(MFBlockIds.PALLID_SIGN,
            properties -> new MFStandingSignBlock(MFWoodType.PALLID, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_WALL_SIGN = registerBlockWithoutItem(MFBlockIds.PALLID_WALL_SIGN,
            properties -> new MFWallSignBlock(MFWoodType.PALLID, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.PALLID_HANGING_SIGN,
            properties -> new MFCeilingHangingSignBlock(MFWoodType.PALLID, properties
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_WALL_HANGING_SIGN = registerBlockWithoutItem(MFBlockIds.PALLID_WALL_HANGING_SIGN,
            properties -> new MFWallHangingSignBlock(MFWoodType.PALLID, properties
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_SHELF = registerBlock(MFBlockItemIds.PALLID_SHELF,
            props -> new MFShelfBlock(true, props),
            _ -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.TERRACOTTA_GREEN)
    );

    /*
     * Vertical slabs for vanilla block types.
     * Because there are so many, I will be subdividing this section into more sections
     */

    /* --- WOOD SLABS --- */

    public static final DeferredBlock<VerticalSlabBlock> OAK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.OAK_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.OAK_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> SPRUCE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.SPRUCE_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> BIRCH_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.BIRCH_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BIRCH_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> JUNGLE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.JUNGLE_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> ACACIA_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.ACACIA_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.ACACIA_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> DARK_OAK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.DARK_OAK_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> CRIMSON_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.CRIMSON_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> WARPED_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.WARPED_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.WARPED_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> MANGROVE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.MANGROVE_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> CHERRY_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.CHERRY_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CHERRY_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> BAMBOO_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.BAMBOO_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> BAMBOO_MOSAIC_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.BAMBOO_MOSAIC_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BAMBOO_MOSAIC)
    );

    public static final DeferredBlock<VerticalSlabBlock> PALE_OAK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.PALE_OAK_VERTICAL_SLAB,
            true,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS)
    );

    /* --- STONE & DEEPSLATE SLABS --- */

    public static final DeferredBlock<VerticalSlabBlock> STONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.STONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.STONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> COBBLESTONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.COBBLESTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.COBBLESTONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> MOSSY_COBBLESTONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.MOSSY_COBBLESTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> SMOOTH_STONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.SMOOTH_STONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_STONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> STONE_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.STONE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.STONE_BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> MOSSY_STONE_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.MOSSY_STONE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> COBBLED_DEEPSLATE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.COBBLED_DEEPSLATE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE)
    );

    public static final DeferredBlock<VerticalSlabBlock> POLISHED_DEEPSLATE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.POLISHED_DEEPSLATE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE)
    );

    public static final DeferredBlock<VerticalSlabBlock> DEEPSLATE_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.DEEPSLATE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> DEEPSLATE_TILE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.DEEPSLATE_TILE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DEEPSLATE_TILES)
    );

    /* --- OTHER STONE TYPE SLABS --- */

    public static final DeferredBlock<VerticalSlabBlock> GRANITE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.GRANITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.GRANITE)
    );

    public static final DeferredBlock<VerticalSlabBlock> POLISHED_GRANITE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.POLISHED_GRANITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_GRANITE)
    );

    public static final DeferredBlock<VerticalSlabBlock> DIORITE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.DIORITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DIORITE)
    );

    public static final DeferredBlock<VerticalSlabBlock> POLISHED_DIORITE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.POLISHED_DIORITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_DIORITE)
    );

    public static final DeferredBlock<VerticalSlabBlock> ANDESITE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.ANDESITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.ANDESITE)
    );

    public static final DeferredBlock<VerticalSlabBlock> POLISHED_ANDESITE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.POLISHED_ANDESITE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_ANDESITE)
    );

    public static final DeferredBlock<VerticalSlabBlock> TUFF_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.TUFF_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.TUFF)
    );

    public static final DeferredBlock<VerticalSlabBlock> POLISHED_TUFF_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.POLISHED_TUFF_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_TUFF)
    );

    public static final DeferredBlock<VerticalSlabBlock> TUFF_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.TUFF_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.TUFF_BRICKS)
    );

    /* --- SANDSTONE SLABS --- */

    public static final DeferredBlock<VerticalSlabBlock> SANDSTONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SANDSTONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> SMOOTH_SANDSTONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.SMOOTH_SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> CUT_SANDSTONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.CUT_SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CUT_SANDSTONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> RED_SANDSTONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.RED_SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.RED_SANDSTONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> SMOOTH_RED_SANDSTONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_RED_SANDSTONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> CUT_RED_SANDSTONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.CUT_RED_SANDSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CUT_RED_SANDSTONE)
    );

    /* --- SULFUR & CINNABAR SLABS --- */

    public static final DeferredBlock<VerticalSlabBlock> SULFUR_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.SULFUR_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SULFUR)
    );

    public static final DeferredBlock<VerticalSlabBlock> POLISHED_SULFUR_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.POLISHED_SULFUR_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_SULFUR)
    );

    public static final DeferredBlock<VerticalSlabBlock> SULFUR_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.SULFUR_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SULFUR_BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> CINNABAR_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.CINNABAR_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CINNABAR)
    );

    public static final DeferredBlock<VerticalSlabBlock> POLISHED_CINNABAR_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.POLISHED_CINNABAR_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_CINNABAR)
    );

    public static final DeferredBlock<VerticalSlabBlock> CINNABAR_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.CINNABAR_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CINNABAR_BRICKS)
    );

    /* --- NETHER & END SLABS --- */

    public static final DeferredBlock<VerticalSlabBlock> NETHER_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.NETHER_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.NETHER_BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> RED_NETHER_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.RED_NETHER_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> BLACKSTONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.BLACKSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BLACKSTONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> POLISHED_BLACKSTONE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.POLISHED_BLACKSTONE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE)
    );

    public static final DeferredBlock<VerticalSlabBlock> POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> END_STONE_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.END_STONE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.END_STONE_BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> PURPUR_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.PURPUR_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PURPUR_BLOCK)
    );

    public static final DeferredBlock<VerticalSlabBlock> QUARTZ_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.QUARTZ_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)
    );

    public static final DeferredBlock<VerticalSlabBlock> SMOOTH_QUARTZ_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.SMOOTH_QUARTZ_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ)
    );

    /* --- MISCELLANEOUS SLABS --- */

    public static final DeferredBlock<VerticalSlabBlock> PRISMARINE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.PRISMARINE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PRISMARINE)
    );

    public static final DeferredBlock<VerticalSlabBlock> PRISMARINE_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.PRISMARINE_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> DARK_PRISMARINE_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.DARK_PRISMARINE_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DARK_PRISMARINE)
    );

    public static final DeferredBlock<VerticalSlabBlock> BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> MUD_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.MUD_BRICK_VERTICAL_SLAB,
            false,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MUD_BRICKS)
    );

    public static final DeferredBlock<VerticalSlabBlock> RESIN_BRICK_VERTICAL_SLAB = registerVerticalSlab(MFBlockItemIds.RESIN_BRICK_VERTICAL_SLAB,
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
                    (_, props) -> new VerticalSlabBlock(true, props),
                    colour -> Properties.ofFullCopy(Blocks.WOOL.pick(colour))
            );

    // Flowers

    public static final DeferredBlock<Block> ROSE = registerBlock(MFBlockItemIds.ROSE,
            properties -> new FlowerBlock(
                    MobEffects.SLOW_FALLING, 10, properties
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .noCollision()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS)
            ));

    public static final DeferredBlock<Block> POTTED_ROSE = registerBlockWithoutItem(MFBlockIds.POTTED_ROSE,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, ROSE, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> BLUE_ROSE = registerBlock(MFBlockItemIds.BLUE_ROSE,
            properties -> new FlowerBlock(MobEffects.SPEED, 10, properties),
            _ -> Properties.ofFullCopy(ROSE.get())
    );

    public static final DeferredBlock<Block> POTTED_BLUE_ROSE = registerBlockWithoutItem(MFBlockIds.POTTED_BLUE_ROSE,
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, BLUE_ROSE, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    // Void anchor block
    public static final DeferredBlock<Block> VOID_ANCHOR = registerBlock(MFBlockItemIds.VOID_ANCHOR,
            properties -> new VoidAnchorBlock(properties
                    .strength(50f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .lightLevel(state -> VoidAnchorBlock.getScaledChargeLevel(state, 15))
            ));

    // Magic block!
    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlockWithTooltip(MFBlockItemIds.MAGIC_BLOCK,
            properties -> new MagicBlock(properties
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(MFSounds.MAGIC_BLOCK_SOUNDS)
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
                    .sound(MFSounds.EVIL_PORTAL_SOUNDS)
            ), Component.translatable("tooltip.morefeatures.evil_portal"));

    // Conjured Ice
    public static final DeferredBlock<Block> CONJURED_ICE = registerBlockWithoutItem(MFBlockIds.CONJURED_ICE,
            props -> new ConjuredIceBlock(props
                    .mapColor(MapColor.ICE)
                    .friction(0.98F)
                    .strength(0.5F)
                    .sound(SoundType.GLASS)
                    .noOcclusion()
                    .isValidSpawn((_, _, _, entityType) -> entityType == EntityTypes.POLAR_BEAR)
                    .isRedstoneConductor(MFBlocks::never)
            ));

    // Icicle
    public static final DeferredBlock<Block> ICICLE = registerBlock(MFBlockItemIds.ICICLE,
            properties -> new IcicleBlock(
                    List.of(
                            Blocks.PACKED_ICE.defaultBlockState(),
                            Blocks.BLUE_ICE.defaultBlockState()
                    ), properties
                    .sound(SoundType.GLASS)
                    .randomTicks()
                    .strength(1.5F, 3.0F)
                    .dynamicShape()
                    .forceSolidOn()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
                    .noOcclusion()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .friction(0.98F)
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
            BiFunction<DyeColor, Properties, ? extends Block> factory,
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
                    DeferredBlock<Block> block = BLOCKS.register(name, () -> factory.apply(color, props));

                    // Register the BlockItem, just like with copper
                    registerBlockItem(id, block);

                    return block;
                }
        );
    }

    private static DeferredBlock<VerticalSlabBlock> registerVerticalSlab(
            BlockItemId id,
            boolean isFlammable,
            BiFunction<Boolean, BlockBehaviour.Properties, VerticalSlabBlock> blockFactory,
            UnaryOperator<BlockBehaviour.Properties> propertyModifier
    ) {
        if (MFIdentifier.isNotMfNamespace(id.block().identifier())){
            throw new IllegalStateException("Could not register the " + id.block().identifier().getPath() + " block. ID must be within the More Features namespace!");
        }

        DeferredBlock<VerticalSlabBlock> block = BLOCKS.registerBlock(id.block().identifier().getPath(),
                baseProps -> {
                    BlockBehaviour.Properties props = propertyModifier.apply(baseProps).setId(id.block());
                    // Pass both the boolean and the modified properties into the constructor
                    return blockFactory.apply(isFlammable, props);
                }
        );

        registerBlockItem(id, block);
        return block;
    }

    // These exact booleans exist in the vanilla Blocks class, but have private access

    private static boolean always(BlockState state, BlockGetter blockGetter, BlockPos blockPos) {
        return true;
    }

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    private static Boolean never(final BlockState state, final BlockGetter blockGetter, final BlockPos blockPos, final EntityType<?> entityType) {
        return false;
    }

    private static Boolean always(final BlockState state, final BlockGetter blockGetter, final BlockPos blockPos, final EntityType<?> entityType) {
        return true;
    }

    // Register method called in the mod event bus

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Blocks registered -> Performed by: " + MoreFeatures.MODID);
    }
}
