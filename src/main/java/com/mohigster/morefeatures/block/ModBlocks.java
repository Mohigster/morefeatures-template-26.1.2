package com.mohigster.morefeatures.block;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.custom.*;
import com.mohigster.morefeatures.block.custom.flammable.*;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.WeatheringCopperVerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.woodtype.ModBlockSetType;
import com.mohigster.morefeatures.block.custom.woodtype.ModWoodType;
import com.mohigster.morefeatures.block.entity.ModBlockEntities;
import com.mohigster.morefeatures.block.references.ModBlockItemIds;
import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.particles.ModParticleTypes;
import com.mohigster.morefeatures.sound.ModSounds;
import com.mohigster.morefeatures.tag.ModBlockTags;
import com.mohigster.morefeatures.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
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

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MoreFeatures.MODID);

    // Same convention as ModItems. Section block registration into types of blocks e.g. // Aluminium Blocks

    //———————————————————————————————————————Aluminium Blocks————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> ALUMINIUM_BLOCK = registerBlock(ModBlockItemIds.ALUMINIUM_BLOCK,
            properties -> new Block(properties
                    .mapColor(MapColor.COLOR_LIGHT_GRAY)
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
            ));

    public static final DeferredBlock<Block> RAW_ALUMINIUM_BLOCK = registerBlock(ModBlockItemIds.RAW_ALUMINIUM_BLOCK,
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> ALUMINIUM_ORE = registerBlock(ModBlockItemIds.ALUMINIUM_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_ALUMINIUM_ORE = registerBlock(ModBlockItemIds.DEEPSLATE_ALUMINIUM_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));

    //———————————————————————————————————————Magnesium Blocks————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> MAGNESIUM_BLOCK = registerBlock(ModBlockItemIds.MAGNESIUM_BLOCK,
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
            ));

    public static final DeferredBlock<Block> RAW_MAGNESIUM_BLOCK = registerBlock(ModBlockItemIds.RAW_MAGNESIUM_BLOCK,
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> MAGNESIUM_ORE = registerBlock(ModBlockItemIds.MAGNESIUM_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_MAGNESIUM_ORE = registerBlock(ModBlockItemIds.DEEPSLATE_MAGNESIUM_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));


    //———————————————————————————————————————Bismuth Blocks——————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> BISMUTH_ORE = registerBlock(ModBlockItemIds.BISMUTH_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(35f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock(ModBlockItemIds.BISMUTH_BLOCK,
            properties -> new Block(properties
                    .strength(60f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_BISMUTH_BLOCK = registerBlock(ModBlockItemIds.RAW_BISMUTH_BLOCK,
            properties -> new Block(properties
                    .strength(50f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    //———————————————————————————————————————Azurite Blocks——————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> AZURITE_ORE = registerBlock(ModBlockItemIds.AZURITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_AZURITE_ORE = registerBlock(ModBlockItemIds.DEEPSLATE_AZURITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));

    public static final DeferredBlock<Block> NETHER_AZURITE_ORE = registerBlock(ModBlockItemIds.NETHER_AZURITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHER_ORE)
            ));

    public static final DeferredBlock<Block> END_AZURITE_ORE = registerBlock(ModBlockItemIds.END_AZURITE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> AZURITE_BLOCK = registerBlock(ModBlockItemIds.AZURITE_BLOCK,
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = registerBlock(ModBlockItemIds.RAW_AZURITE_BLOCK,
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> AZURITE_STAIRS = registerBlock(ModBlockItemIds.AZURITE_STAIRS,
            properties -> new StairBlock(ModBlocks.AZURITE_BLOCK.get().defaultBlockState(), properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> AZURITE_SLAB = registerBlock(ModBlockItemIds.AZURITE_SLAB,
            properties -> new SlabBlock(properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> AZURITE_PRESSURE_PLATE = registerBlock(ModBlockItemIds.AZURITE_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(BlockSetType.IRON, properties
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> AZURITE_BUTTON = registerBlock(ModBlockItemIds.AZURITE_BUTTON,
            properties -> new ButtonBlock(BlockSetType.IRON, 20, properties
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));


    //———————————————————————————————————————Fluorite Blocks—————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> FLUORITE_ORE = registerLegacyBlock("fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_FLUORITE_ORE = registerLegacyBlock("deepslate_fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));

    public static final DeferredBlock<Block> NETHER_FLUORITE_ORE = registerLegacyBlock("nether_fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHER_ORE)
            ));

    public static final DeferredBlock<Block> END_FLUORITE_ORE = registerLegacyBlock("end_fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> FLUORITE_BLOCK = registerLegacyBlock("fluorite_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_FLUORITE_BLOCK = registerLegacyBlock("raw_fluorite_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> FLUORITE_STAIRS = registerLegacyBlock("fluorite_stairs",
            properties -> new StairBlock(ModBlocks.FLUORITE_BLOCK.get().defaultBlockState(), properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> FLUORITE_SLAB = registerLegacyBlock("fluorite_slab",
            properties -> new SlabBlock(properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    //———————————————————————————————————————Everfrost Blocks————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> EVERFROST_BLUE_ICE_ORE = registerBlock(ModBlockItemIds.EVERFROST_BLUE_ICE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLASS)
                    .friction(0.98F)
            ));

    public static final DeferredBlock<Block> EVERFROST_PACKED_ICE_ORE = registerBlock(ModBlockItemIds.EVERFROST_PACKED_ICE_ORE,
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLASS)
                    .friction(0.98F)
            ));

    //———————————————————————————————————————Bloodwood Blocks————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> BLOODWOOD_LOG = registerBlock(ModBlockItemIds.BLOODWOOD_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> BLOODWOOD = registerBlock(ModBlockItemIds.BLOODWOOD,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_LOG = registerBlock(ModBlockItemIds.STRIPPED_BLOODWOOD_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD = registerBlock(ModBlockItemIds.STRIPPED_BLOODWOOD,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_PLANKS = registerBlock(ModBlockItemIds.BLOODWOOD_PLANKS,
            properties -> new ModFlammableBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_LEAVES = registerBlock(ModBlockItemIds.BLOODWOOD_LEAVES,
            properties -> new ModLeavesBlock(0.03F, ModParticleTypes.BLOODWOOD_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> BLOODWOOD_SAPLING = registerBlock(ModBlockItemIds.BLOODWOOD_SAPLING,
            properties -> new SaplingBlock(ModTreeGrowers.BLOODWOOD, properties
                    .randomTicks()
                    .instabreak()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS)
            ));

    public static final DeferredBlock<Block> POTTED_BLOODWOOD_SAPLING = registerBlockWithoutItem("potted_bloodwood_sapling",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, BLOODWOOD_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_STAIRS = registerBlock(ModBlockItemIds.BLOODWOOD_STAIRS,
            properties -> new ModFlammableStairBlock(ModBlocks.BLOODWOOD_PLANKS.get().defaultBlockState(), properties
                    .strength(3f)
                    .ignitedByLava()
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_SLAB = registerBlock(ModBlockItemIds.BLOODWOOD_SLAB,
            properties -> new ModFlammableSlabBlock(properties
                    .strength(1f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> BLOODWOOD_FENCE = registerBlock(ModBlockItemIds.BLOODWOOD_FENCE,
            properties -> new ModFlammableFenceBlock(properties
                    .strength(2F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> BLOODWOOD_FENCE_GATE = registerBlock(ModBlockItemIds.BLOODWOOD_FENCE_GATE,
            properties -> new ModFlammableFenceGateBlock(ModWoodType.BLOODWOOD, properties
                    .strength(2F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> BLOODWOOD_PRESSURE_PLATE = registerBlock(ModBlockItemIds.BLOODWOOD_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(ModBlockSetType.BLOODWOOD, properties
                    .sound(SoundType.WOOD)
                    .strength(2F, 2F)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_BUTTON = registerBlock(ModBlockItemIds.BLOODWOOD_BUTTON,
            properties -> new ButtonBlock(ModBlockSetType.BLOODWOOD, 20, properties
                    .sound(SoundType.WOOD)
                    .strength(2F, 2F)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_SIGN = registerBlockWithoutItem("bloodwood_sign",
            properties -> new ModStandingSignBlock(ModWoodType.BLOODWOOD, properties
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_WALL_SIGN = registerBlockWithoutItem("bloodwood_wall_sign",
            properties -> new ModWallSignBlock(ModWoodType.BLOODWOOD, properties
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
            ));

    // Ceiling sign
    public static final DeferredBlock<Block> BLOODWOOD_HANGING_SIGN = registerBlockWithoutItem("bloodwood_hanging_sign",
            properties -> new ModCeilingHangingSignBlock(ModWoodType.BLOODWOOD, properties
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
            ));

    // Wall sign
    public static final DeferredBlock<Block> BLOODWOOD_WALL_HANGING_SIGN = registerBlockWithoutItem("bloodwood_wall_hanging_sign",
            properties -> new ModWallHangingSignBlock(ModWoodType.BLOODWOOD, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_SHELF = registerBlock(ModBlockItemIds.BLOODWOOD_SHELF,
            props -> new ModShelfBlock(true, props),
            props -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.COLOR_RED)
    );

    //———————————————————————————————————————Tainted Wood Blocks—————————————————————————————————————————————————————————————————————


    public static final DeferredBlock<Block> TAINTED_LOG = registerBlock(ModBlockItemIds.TAINTED_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> TAINTED_WOOD = registerBlock(ModBlockItemIds.TAINTED_WOOD,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> STRIPPED_TAINTED_LOG = registerBlock(ModBlockItemIds.STRIPPED_TAINTED_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> STRIPPED_TAINTED_WOOD = registerBlock(ModBlockItemIds.STRIPPED_TAINTED_WOOD,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> TAINTED_PLANKS = registerBlock(ModBlockItemIds.TAINTED_PLANKS,
            properties -> new ModFlammableBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> TAINTED_LEAVES = registerBlock(ModBlockItemIds.TAINTED_LEAVES,
            properties -> new ModLeavesBlock(0.03F, ModParticleTypes.TAINTED_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> TAINTED_SAPLING = registerBlock(ModBlockItemIds.TAINTED_SAPLING,
            properties -> new SaplingBlock(ModTreeGrowers.TAINTED, properties
                    .randomTicks()
                    .instabreak()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS))
    );

    public static final DeferredBlock<Block> POTTED_TAINTED_SAPLING = registerBlockWithoutItem("potted_tainted_sapling",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, TAINTED_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> TAINTED_STAIRS = registerBlock(ModBlockItemIds.TAINTED_STAIRS,
            properties -> new ModFlammableStairBlock(ModBlocks.TAINTED_PLANKS.get().defaultBlockState(), properties
                    .strength(3f)
                    .ignitedByLava()
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> TAINTED_SLAB = registerBlock(ModBlockItemIds.TAINTED_SLAB,
            properties -> new ModFlammableSlabBlock(properties
                    .strength(1f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> TAINTED_FENCE = registerBlock(ModBlockItemIds.TAINTED_FENCE,
            properties -> new ModFlammableFenceBlock(properties
                    .strength(2F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> TAINTED_FENCE_GATE = registerBlock(ModBlockItemIds.TAINTED_FENCE_GATE,
            properties -> new ModFlammableFenceGateBlock(ModWoodType.TAINTED, properties
                    .strength(2F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> TAINTED_PRESSURE_PLATE = registerBlock(ModBlockItemIds.TAINTED_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(ModBlockSetType.TAINTED, properties
                    .sound(SoundType.WOOD)
                    .strength(2F, 2F)
            ));

    public static final DeferredBlock<Block> TAINTED_BUTTON = registerBlock(ModBlockItemIds.TAINTED_BUTTON,
            properties -> new ButtonBlock(ModBlockSetType.TAINTED, 20, properties
                    .sound(SoundType.WOOD)
                    .strength(2F, 2F)
            ));

    public static final DeferredBlock<Block> TAINTED_SIGN = registerBlockWithoutItem("tainted_sign",
            properties -> new ModStandingSignBlock(
                    ModWoodType.TAINTED,
                    BlockBehaviour.Properties.of()
                            .noCollision()
                            .strength(1.0F)
                            .sound(SoundType.WOOD)
                            .setId(ResourceKey.create(Registries.BLOCK,
                                    Identifier.fromNamespaceAndPath(MoreFeatures.MODID,
                                            "tainted_sign")))
            ));

    public static final DeferredBlock<Block> TAINTED_WALL_SIGN = registerBlockWithoutItem("tainted_wall_sign",
            properties -> new ModWallSignBlock(ModWoodType.TAINTED, properties
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
            ));

    // Ceiling sign
    public static final DeferredBlock<Block> TAINTED_HANGING_SIGN = registerBlockWithoutItem("tainted_hanging_sign",
            properties -> new ModCeilingHangingSignBlock(ModWoodType.TAINTED, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
            ));

    // Wall sign
    public static final DeferredBlock<Block> TAINTED_WALL_HANGING_SIGN = registerBlockWithoutItem("tainted_wall_hanging_sign",
            properties -> new ModWallHangingSignBlock(ModWoodType.TAINTED, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> TAINTED_SHELF = registerBlock(ModBlockItemIds.TAINTED_SHELF,
            props -> new ModShelfBlock(true, props),
            props -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.COLOR_PURPLE)
    );

    //———————————————————————————————————————Palm Wood Blocks————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> PALM_LOG = registerBlock(ModBlockItemIds.PALM_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_WOOD = registerBlock(ModBlockItemIds.PALM_WOOD,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> STRIPPED_PALM_LOG = registerBlock(ModBlockItemIds.STRIPPED_PALM_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> STRIPPED_PALM_WOOD = registerBlock(ModBlockItemIds.STRIPPED_PALM_WOOD,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_PLANKS = registerBlock(ModBlockItemIds.PALM_PLANKS,
            properties -> new ModFlammableBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_LEAVES = registerBlock(ModBlockItemIds.PALM_LEAVES,
            properties -> new ModLeavesBlock(0.01F, ModParticleTypes.PALM_LEAVES.get(), properties
                    .strength(0.2F, 0.2F)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALM_SAPLING = registerBlock(ModBlockItemIds.PALM_SAPLING,
            properties -> new PlantedOffGrassSaplingBlock(ModTreeGrowers.PALM, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion()
                    .noCollision(),
                    Blocks.SAND // The block that the sapling can be planted on
            ));

    public static final DeferredBlock<Block> POTTED_PALM_SAPLING = registerBlockWithoutItem("potted_palm_sapling",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, PALM_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALM_STAIRS = registerBlock(ModBlockItemIds.PALM_STAIRS,
            properties -> new ModFlammableStairBlock(ModBlocks.PALM_PLANKS.get().defaultBlockState(), properties
                    .strength(3f)
                    .ignitedByLava()
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_SLAB = registerBlock(ModBlockItemIds.PALM_SLAB,
            properties -> new ModFlammableSlabBlock(properties
                    .strength(1f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALM_FENCE = registerBlock(ModBlockItemIds.PALM_FENCE,
            properties -> new ModFlammableFenceBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_FENCE_GATE = registerBlock(ModBlockItemIds.PALM_FENCE_GATE,
            properties -> new ModFlammableFenceGateBlock(ModWoodType.PALM, properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_SIGN = registerBlockWithoutItem("palm_sign",
            properties -> new ModStandingSignBlock(
                    ModWoodType.PALM,
                    BlockBehaviour.Properties.of()
                            .noCollision()
                            .strength(1.0f)
                            .sound(SoundType.WOOD)
                            .setId(ResourceKey.create(Registries.BLOCK,
                                    Identifier.fromNamespaceAndPath(MoreFeatures.MODID,
                                            "palm_sign")))
            ));

    public static final DeferredBlock<Block> PALM_WALL_SIGN = registerBlockWithoutItem("palm_wall_sign",
            properties -> new ModWallSignBlock(
                    ModWoodType.PALM,
                    BlockBehaviour.Properties.of()
                            .noCollision()
                            .strength(1.0f)
                            .sound(SoundType.WOOD)
                            .setId(ResourceKey.create(Registries.BLOCK,
                                    Identifier.fromNamespaceAndPath(MoreFeatures.MODID,
                                            "palm_wall_sign")))
            ));

    // Ceiling sign
    public static final DeferredBlock<Block> PALM_HANGING_SIGN = registerBlockWithoutItem("palm_hanging_sign",
            properties -> new ModCeilingHangingSignBlock(
                    ModWoodType.PALM,
                    BlockBehaviour.Properties.of()
                            .noCollision()
                            .strength(1.0f)
                            .sound(SoundType.WOOD)
                            .setId(ResourceKey.create(Registries.BLOCK,
                                    Identifier.fromNamespaceAndPath(MoreFeatures.MODID,
                                            "palm_hanging_sign")))
            ));

    // Wall sign
    public static final DeferredBlock<Block> PALM_WALL_HANGING_SIGN = registerBlockWithoutItem("palm_wall_hanging_sign",
            properties -> new ModWallHangingSignBlock(
                    ModWoodType.PALM,
                    BlockBehaviour.Properties.of()
                            .noCollision()
                            .strength(1.0f)
                            .sound(SoundType.WOOD)
                            .setId(ResourceKey.create(Registries.BLOCK,
                                    Identifier.fromNamespaceAndPath(MoreFeatures.MODID,
                                            "palm_wall_hanging_sign")))
            ));

    public static final DeferredBlock<Block> PALM_TRAPDOOR = registerBlock(ModBlockItemIds.PALM_TRAPDOOR,
            properties -> new TrapDoorBlock(BlockSetType.ACACIA, properties
                    .strength(2f, 2f)
                    .noOcclusion()
            ));

    public static final DeferredBlock<Block> PALM_DOOR = registerBlock(ModBlockItemIds.PALM_DOOR,
            properties -> new DoorBlock(BlockSetType.ACACIA, properties
                    .strength(2f, 2f)
                    .noOcclusion()
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALM_PRESSURE_PLATE = registerBlock(ModBlockItemIds.PALM_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(ModBlockSetType.PALM, properties
                    .strength(2f)
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALM_BUTTON = registerBlock(ModBlockItemIds.PALM_BUTTON,
            properties -> new ButtonBlock(ModBlockSetType.PALM, 20, properties
                    .strength(2f)
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALM_SHELF = registerBlock(ModBlockItemIds.PALM_SHELF,
            props -> new ModShelfBlock(true, props),
            props -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.COLOR_YELLOW)
    );

    //———————————————————————————————————————Decrepit Wood Blocks————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> DECREPIT_LOG = registerBlock(ModBlockItemIds.DECREPIT_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .mapColor(MapColor.TERRACOTTA_BLUE)
            ));

    public static final DeferredBlock<Block> DECREPIT_WOOD = registerBlock(ModBlockItemIds.DECREPIT_WOOD,
            ModFlammableRotatedPillarBlock::new,
            props -> Properties.ofFullCopy(DECREPIT_LOG.get()).mapColor(MapColor.COLOR_BLACK)
    );

    public static final DeferredBlock<Block> STRIPPED_DECREPIT_LOG = registerBlock(ModBlockItemIds.STRIPPED_DECREPIT_LOG,
            ModFlammableRotatedPillarBlock::new,
            props -> Properties.ofFullCopy(DECREPIT_LOG.get())
    );

    public static final DeferredBlock<Block> STRIPPED_DECREPIT_WOOD = registerBlock(ModBlockItemIds.STRIPPED_DECREPIT_WOOD,
            ModFlammableRotatedPillarBlock::new,
            props -> Properties.ofFullCopy(DECREPIT_LOG.get())
    );

    public static final DeferredBlock<Block> DECREPIT_PLANKS = registerBlock(ModBlockItemIds.DECREPIT_PLANKS,
            properties -> new ModFlammableBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
                    .ignitedByLava()
                    .mapColor(MapColor.TERRACOTTA_BLUE)
            ));

    public static final DeferredBlock<Block> DECREPIT_LEAVES = registerBlock(ModBlockItemIds.DECREPIT_LEAVES,
            properties -> new ModLeavesBlock(0.02F, ModParticleTypes.DECREPIT_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
                    .mapColor(Blocks.PALE_OAK_LEAVES.defaultMapColor())
            ));

    public static final DeferredBlock<Block> DECREPIT_NULLIUM = registerBlock(ModBlockItemIds.DECREPIT_NULLIUM,
            properties -> new NulliumBlock(properties
                    .sound(SoundType.NYLIUM)
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .mapColor(MapColor.TERRACOTTA_BLUE)
            ));

    public static final DeferredBlock<Block> DECREPIT_SAPLING = registerBlock(ModBlockItemIds.DECREPIT_SAPLING,
            properties -> new PlantedOffGrassSaplingBlock(ModTreeGrowers.DECREPIT, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion()
                    .noCollision()
                    .mapColor(MapColor.PLANT),
                    DECREPIT_NULLIUM.get()
            ));

    public static final DeferredBlock<Block> POTTED_DECREPIT_SAPLING = registerBlockWithoutItem("potted_decrepit_sapling", // Item is the Decrepit Sapling, so no separate BlockItemId needed.
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, DECREPIT_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> DECREPIT_STAIRS = registerBlock(ModBlockItemIds.DECREPIT_STAIRS,
            properties -> new ModFlammableStairBlock(ModBlocks.DECREPIT_PLANKS.get().defaultBlockState(), properties
                    .strength(2f, 2f)
                    .ignitedByLava()
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_SLAB = registerBlock(ModBlockItemIds.DECREPIT_SLAB,
            properties -> new ModFlammableSlabBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> DECREPIT_VERTICAL_SLAB = registerBlock(ModBlockItemIds.DECREPIT_VERTICAL_SLAB,
            properties -> new VerticalSlabBlock(true, properties),
            props -> Properties.ofFullCopy(DECREPIT_PLANKS.get())
    );

    public static final DeferredBlock<Block> DECREPIT_FENCE = registerBlock(ModBlockItemIds.DECREPIT_FENCE,
            properties -> new ModFlammableFenceBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_FENCE_GATE = registerBlock(ModBlockItemIds.DECREPIT_FENCE_GATE,
            properties -> new ModFlammableFenceGateBlock(ModWoodType.DECREPIT, properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_PRESSURE_PLATE = registerBlock(ModBlockItemIds.DECREPIT_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(ModBlockSetType.DECREPIT, properties
                    .strength(2f)
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> DECREPIT_BUTTON = registerBlock(ModBlockItemIds.DECREPIT_BUTTON,
            properties -> new ButtonBlock(ModBlockSetType.DECREPIT, 20, properties
                    .strength(2f)
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> DECREPIT_ROOTS = registerBlock(ModBlockItemIds.DECREPIT_ROOTS,
            properties -> new NetherRootsBlock(ModBlockTags.NULLIUM_BLOCKS, properties
                    .sound(SoundType.ROOTS)
                    .noOcclusion()
                    .noCollision()
                    .instabreak()
                    .replaceable()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
            ));

    public static final DeferredBlock<Block> POTTED_DECREPIT_ROOTS = registerBlockWithoutItem("potted_decrepit_roots",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, DECREPIT_ROOTS, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> DECREPIT_SIGN = registerBlockWithoutItem("decrepit_sign",
            properties -> new ModStandingSignBlock(ModWoodType.DECREPIT, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_WALL_SIGN = registerBlockWithoutItem("decrepit_wall_sign",
            properties -> new ModWallSignBlock(ModWoodType.DECREPIT, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    // Ceiling sign
    public static final DeferredBlock<Block> DECREPIT_HANGING_SIGN = registerBlockWithoutItem("decrepit_hanging_sign",
            properties -> new ModCeilingHangingSignBlock(ModWoodType.DECREPIT, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    // Wall sign
    public static final DeferredBlock<Block> DECREPIT_WALL_HANGING_SIGN = registerBlockWithoutItem("decrepit_wall_hanging_sign",
            properties -> new ModWallHangingSignBlock(ModWoodType.DECREPIT, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_SHELF = registerBlock(ModBlockItemIds.DECREPIT_SHELF,
            props -> new ModShelfBlock(true, props),
            props -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.TERRACOTTA_BLUE)
    );

    //———————————————————————————————————————Pallid Wood Blocks——————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> PALLID_LOG = registerBlock(ModBlockItemIds.PALLID_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
                    .ignitedByLava()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .mapColor(MapColor.TERRACOTTA_GREEN)
            ));

    public static final DeferredBlock<Block> PALLID_WOOD = registerBlock(ModBlockItemIds.PALLID_WOOD,
            ModFlammableRotatedPillarBlock::new,
            props -> BlockBehaviour.Properties.ofFullCopy(PALLID_LOG.get()).mapColor(MapColor.COLOR_GRAY)
    );

    public static final DeferredBlock<Block> STRIPPED_PALLID_LOG = registerBlock(ModBlockItemIds.STRIPPED_PALLID_LOG,
            ModFlammableRotatedPillarBlock::new,
            props -> BlockBehaviour.Properties.ofFullCopy(PALLID_LOG.get())
    );

    public static final DeferredBlock<Block> STRIPPED_PALLID_WOOD = registerBlock(ModBlockItemIds.STRIPPED_PALLID_WOOD,
            ModFlammableRotatedPillarBlock::new,
            props -> BlockBehaviour.Properties.ofFullCopy(PALLID_LOG.get())
    );

    public static final DeferredBlock<Block> PALLID_PLANKS = registerBlock(ModBlockItemIds.PALLID_PLANKS,
            properties -> new ModFlammableBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
                    .ignitedByLava()
                    .mapColor(MapColor.TERRACOTTA_GREEN)
            ));

    public static final DeferredBlock<Block> PALLID_LEAVES = registerBlock(ModBlockItemIds.PALLID_LEAVES,
            properties -> new ModLeavesBlock(0.02F, ModParticleTypes.PALLID_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
                    .mapColor(Blocks.PALE_OAK_LEAVES.defaultMapColor())
            ));

    public static final DeferredBlock<Block> PALLID_NULLIUM = registerBlock(ModBlockItemIds.PALLID_NULLIUM,
            properties -> new NulliumBlock(properties
                    .sound(SoundType.NYLIUM)
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .mapColor(MapColor.TERRACOTTA_GREEN)
            )
    );

    public static final DeferredBlock<Block> PALLID_SAPLING = registerBlock(ModBlockItemIds.PALLID_SAPLING,
            properties -> new PlantedOffGrassSaplingBlock(ModTreeGrowers.PALLID, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion()
                    .noCollision()
                    .mapColor(MapColor.PLANT),
                    PALLID_NULLIUM.get()
            ));

    public static final DeferredBlock<Block> POTTED_PALLID_SAPLING = registerBlockWithoutItem("potted_pallid_sapling",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, PALLID_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_STAIRS = registerBlock(ModBlockItemIds.PALLID_STAIRS,
            properties -> new ModFlammableStairBlock(ModBlocks.DECREPIT_PLANKS.get().defaultBlockState(), properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALLID_SLAB = registerBlock(ModBlockItemIds.PALLID_SLAB,
            properties -> new ModFlammableSlabBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALLID_VERTICAL_SLAB = registerBlock(ModBlockItemIds.PALLID_VERTICAL_SLAB,
            properties -> new VerticalSlabBlock(true, properties),
            props -> Properties.ofFullCopy(PALLID_PLANKS.get())
    );

    public static final DeferredBlock<Block> PALLID_FENCE = registerBlock(ModBlockItemIds.PALLID_FENCE,
            properties -> new ModFlammableFenceBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_FENCE_GATE = registerBlock(ModBlockItemIds.PALLID_FENCE_GATE,
            properties -> new ModFlammableFenceGateBlock(ModWoodType.PALLID, properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_PRESSURE_PLATE = registerBlock(ModBlockItemIds.PALLID_PRESSURE_PLATE,
            properties -> new PressurePlateBlock(ModBlockSetType.PALLID, properties
                    .strength(2F)
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_BUTTON = registerBlock(ModBlockItemIds.PALLID_BUTTON,
            properties -> new ButtonBlock(ModBlockSetType.PALLID, 20, properties
                    .strength(2F)
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_ROOTS = registerBlock(ModBlockItemIds.PALLID_ROOTS,
            properties -> new NetherRootsBlock(ModBlockTags.NULLIUM_BLOCKS, properties
                    .sound(SoundType.ROOTS)
                    .noOcclusion()
                    .noCollision()
                    .instabreak()
                    .replaceable()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
            ));

    public static final DeferredBlock<Block> POTTED_PALLID_ROOTS = registerBlockWithoutItem("potted_pallid_roots",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, PALLID_ROOTS, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_SIGN = registerBlockWithoutItem("pallid_sign",
            properties -> new ModStandingSignBlock(ModWoodType.PALLID, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_WALL_SIGN = registerBlockWithoutItem("pallid_wall_sign",
            properties -> new ModWallSignBlock(ModWoodType.PALLID, properties
                    .noCollision()
                    .strength(1.0f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_HANGING_SIGN = registerBlockWithoutItem("pallid_hanging_sign",
            properties -> new ModCeilingHangingSignBlock(ModWoodType.PALLID, properties
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_WALL_HANGING_SIGN = registerBlockWithoutItem("pallid_wall_hanging_sign",
            properties -> new ModWallHangingSignBlock(ModWoodType.PALLID, properties
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_SHELF = registerBlock(ModBlockItemIds.PALLID_SHELF,
            props -> new ModShelfBlock(true, props),
            props -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).mapColor(MapColor.TERRACOTTA_GREEN)
    );

    /*
     * Vertical slabs for vanilla block types.
     *
     * Because there are so many, I will be subdividing this section into more sections
     */

    /* --- WOOD SLABS --- */

    public static final DeferredBlock<Block> OAK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.OAK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.OAK_PLANKS)
    );


    public static final DeferredBlock<Block> SPRUCE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.SPRUCE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)
    );


    public static final DeferredBlock<Block> BIRCH_VERTICAL_SLAB = registerBlock(ModBlockItemIds.BIRCH_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.BIRCH_PLANKS)
    );


    public static final DeferredBlock<Block> JUNGLE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.JUNGLE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.JUNGLE_PLANKS)
    );


    public static final DeferredBlock<Block> ACACIA_VERTICAL_SLAB = registerBlock(ModBlockItemIds.ACACIA_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.ACACIA_PLANKS)
    );


    public static final DeferredBlock<Block> DARK_OAK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.DARK_OAK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS)
    );


    public static final DeferredBlock<Block> CRIMSON_VERTICAL_SLAB = registerBlock(ModBlockItemIds.CRIMSON_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.CRIMSON_PLANKS)
    );


    public static final DeferredBlock<Block> WARPED_VERTICAL_SLAB = registerBlock(ModBlockItemIds.WARPED_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.WARPED_PLANKS)
    );


    public static final DeferredBlock<Block> MANGROVE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.MANGROVE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)
    );


    public static final DeferredBlock<Block> CHERRY_VERTICAL_SLAB = registerBlock(ModBlockItemIds.CHERRY_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.CHERRY_PLANKS)
    );


    public static final DeferredBlock<Block> BAMBOO_VERTICAL_SLAB = registerBlock(ModBlockItemIds.BAMBOO_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.BAMBOO_PLANKS)
    );

    public static final DeferredBlock<Block> BAMBOO_MOSAIC_VERTICAL_SLAB = registerBlock(ModBlockItemIds.BAMBOO_MOSAIC_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.BAMBOO_MOSAIC)
    );


    public static final DeferredBlock<Block> PALE_OAK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.PALE_OAK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS)
    );


    /* --- STONE & DEEPSLATE SLABS --- */

    public static final DeferredBlock<Block> STONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.STONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.STONE)
    );


    public static final DeferredBlock<Block> COBBLESTONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.COBBLESTONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.COBBLESTONE)
    );


    public static final DeferredBlock<Block> MOSSY_COBBLESTONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.MOSSY_COBBLESTONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)
    );


    public static final DeferredBlock<Block> SMOOTH_STONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.SMOOTH_STONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.SMOOTH_STONE)
    );


    public static final DeferredBlock<Block> STONE_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.STONE_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.STONE_BRICKS)
    );


    public static final DeferredBlock<Block> MOSSY_STONE_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.MOSSY_STONE_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)
    );


    public static final DeferredBlock<Block> COBBLED_DEEPSLATE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.COBBLED_DEEPSLATE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE)
    );


    public static final DeferredBlock<Block> POLISHED_DEEPSLATE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.POLISHED_DEEPSLATE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE)
    );

    public static final DeferredBlock<Block> DEEPSLATE_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.DEEPSLATE_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)
    );

    public static final DeferredBlock<Block> DEEPSLATE_TILE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.DEEPSLATE_TILE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.DEEPSLATE_TILES)
    );

    /* --- OTHER STONE TYPE SLABS --- */

    public static final DeferredBlock<Block> GRANITE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.GRANITE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.GRANITE)
    );

    public static final DeferredBlock<Block> POLISHED_GRANITE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.POLISHED_GRANITE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.POLISHED_GRANITE)
    );

    public static final DeferredBlock<Block> DIORITE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.DIORITE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.DIORITE)
    );

    public static final DeferredBlock<Block> POLISHED_DIORITE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.POLISHED_DIORITE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.POLISHED_DIORITE)
    );

    public static final DeferredBlock<Block> ANDESITE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.ANDESITE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.ANDESITE)
    );

    public static final DeferredBlock<Block> POLISHED_ANDESITE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.POLISHED_ANDESITE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.POLISHED_ANDESITE)
    );

    public static final DeferredBlock<Block> TUFF_VERTICAL_SLAB = registerBlock(ModBlockItemIds.TUFF_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.TUFF)
    );

    public static final DeferredBlock<Block> POLISHED_TUFF_VERTICAL_SLAB = registerBlock(ModBlockItemIds.POLISHED_TUFF_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.POLISHED_TUFF)
    );

    public static final DeferredBlock<Block> TUFF_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.TUFF_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.TUFF_BRICKS)
    );

    /* --- SANDSTONE SLABS --- */

    public static final DeferredBlock<Block> SANDSTONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.SANDSTONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.SANDSTONE)
    );

    public static final DeferredBlock<Block> SMOOTH_SANDSTONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.SMOOTH_SANDSTONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE)
    );

    public static final DeferredBlock<Block> CUT_SANDSTONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.CUT_SANDSTONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.CUT_SANDSTONE)
    );

    public static final DeferredBlock<Block> RED_SANDSTONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.RED_SANDSTONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.RED_SANDSTONE)
    );

    public static final DeferredBlock<Block> SMOOTH_RED_SANDSTONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.SMOOTH_RED_SANDSTONE)
    );

    public static final DeferredBlock<Block> CUT_RED_SANDSTONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.CUT_RED_SANDSTONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.CUT_RED_SANDSTONE)
    );

    /* --- SULFUR & CINNABAR SLABS --- */

    public static final DeferredBlock<Block> SULFUR_VERTICAL_SLAB = registerBlock(ModBlockItemIds.SULFUR_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.SULFUR)
    );

    public static final DeferredBlock<Block> POLISHED_SULFUR_VERTICAL_SLAB = registerBlock(ModBlockItemIds.POLISHED_SULFUR_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.POLISHED_SULFUR)
    );

    public static final DeferredBlock<Block> SULFUR_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.SULFUR_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.SULFUR_BRICKS)
    );

    public static final DeferredBlock<Block> CINNABAR_VERTICAL_SLAB = registerBlock(ModBlockItemIds.CINNABAR_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.CINNABAR)
    );

    public static final DeferredBlock<Block> POLISHED_CINNABAR_VERTICAL_SLAB = registerBlock(ModBlockItemIds.POLISHED_CINNABAR_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.POLISHED_CINNABAR)
    );

    public static final DeferredBlock<Block> CINNABAR_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.CINNABAR_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.CINNABAR_BRICKS)
    );

    /* --- NETHER & END SLABS --- */

    public static final DeferredBlock<Block> NETHER_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.NETHER_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.NETHER_BRICKS)
    );

    public static final DeferredBlock<Block> RED_NETHER_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.RED_NETHER_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)
    );

    public static final DeferredBlock<Block> BLACKSTONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.BLACKSTONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.BLACKSTONE)
    );

    public static final DeferredBlock<Block> POLISHED_BLACKSTONE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.POLISHED_BLACKSTONE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE)
    );

    public static final DeferredBlock<Block> POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
    );

    public static final DeferredBlock<Block> END_STONE_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.END_STONE_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.END_STONE_BRICKS)
    );

    public static final DeferredBlock<Block> PURPUR_VERTICAL_SLAB = registerBlock(ModBlockItemIds.PURPUR_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.PURPUR_BLOCK)
    );

    public static final DeferredBlock<Block> QUARTZ_VERTICAL_SLAB = registerBlock(ModBlockItemIds.QUARTZ_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)
    );

    public static final DeferredBlock<Block> SMOOTH_QUARTZ_VERTICAL_SLAB = registerBlock(ModBlockItemIds.SMOOTH_QUARTZ_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ)
    );

    /* --- MISCELLANEOUS SLABS --- */

    public static final DeferredBlock<Block> PRISMARINE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.PRISMARINE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.PRISMARINE)
    );

    public static final DeferredBlock<Block> PRISMARINE_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.PRISMARINE_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)
    );

    public static final DeferredBlock<Block> DARK_PRISMARINE_VERTICAL_SLAB = registerBlock(ModBlockItemIds.DARK_PRISMARINE_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.DARK_PRISMARINE)
    );

    public static final DeferredBlock<Block> BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.BRICKS)
    );

    public static final DeferredBlock<Block> MUD_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.MUD_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.MUD_BRICKS)
    );

    public static final DeferredBlock<Block> RESIN_BRICK_VERTICAL_SLAB = registerBlock(ModBlockItemIds.RESIN_BRICK_VERTICAL_SLAB,
            props -> new VerticalSlabBlock(false, props),
            props -> Properties.ofFullCopy(Blocks.RESIN_BRICKS)
    );

    /* --- BLOCK COLLECTION SLABS --- */

    public static final WeatheringCopperCollection<DeferredBlock<Block>> CUT_COPPER_VERTICAL_SLAB = // Because this is a WeatheringCopperCollection, all eight blocks (unaffected, exposed, weathered, and oxidised, and all of their waxed variants) are registered at the exact same time. No need to repeat myself for each one.
            registerCopperBlockSet(
                    ModBlockItemIds.CUT_COPPER_VERTICAL_SLAB,
                    WeatheringCopperVerticalSlabBlock::new, // Create the regular variant
                    WeatheringCopperVerticalSlabBlock::new, // Create the waxed variant
                    state -> Properties.ofFullCopy(Blocks.CUT_COPPER.weathering().pick(state))
            );

    public static final ColorCollection<DeferredBlock<Block>> WOOL_VERTICAL_SLAB =
            registerColouredBlockSet(
                    ModBlockItemIds.WOOL_VERTICAL_SLAB,
                    (colour, props) -> new VerticalSlabBlock(true, props),
                    colour -> Properties.ofFullCopy(Blocks.WOOL.pick(colour))
            );

    // Flowers

    public static final DeferredBlock<Block> ROSE = registerBlock(ModBlockItemIds.ROSE,
            properties -> new FlowerBlock(
                    MobEffects.SLOW_FALLING, 10, properties
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .noCollision()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS)
            ));

    public static final DeferredBlock<Block> POTTED_ROSE = registerBlockWithoutItem("potted_rose",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, ROSE, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> BLUE_ROSE = registerBlock(ModBlockItemIds.BLUE_ROSE,
            properties -> new FlowerBlock(
                    MobEffects.SPEED, 10, properties
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .noCollision()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS)
            ));

    public static final DeferredBlock<Block> POTTED_BLUE_ROSE = registerBlockWithoutItem("potted_blue_rose",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, BLUE_ROSE, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    // Void anchor block
    public static final DeferredBlock<Block> VOID_ANCHOR = registerBlock(ModBlockItemIds.VOID_ANCHOR,
            properties -> new VoidAnchorBlock(properties
                    .strength(50f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
                    .lightLevel(state -> VoidAnchorBlock.getScaledChargeLevel(state, 15))
            ));

    // Magic block!
    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlockWithTooltip(ModBlockItemIds.MAGIC_BLOCK,
            properties -> new MagicBlock(properties
                    .mapColor(MapColor.COLOR_MAGENTA)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(ModSounds.MAGIC_BLOCK_SOUNDS)
            ), Component.translatable("tooltip.morefeatures.magic_block"));


    // Compressor block
    public static final DeferredBlock<Block> COMPRESSOR_BLOCK = registerBlock(ModBlockItemIds.COMPRESSOR_BLOCK,
            properties -> new CompressorBlock(properties
                    .mapColor(MapColor.STONE)
                    .strength(4F, 16f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
            ));

    // Portal Block
    public static final DeferredBlock<Block> EVIL_PORTAL = registerBlockWithTooltip(ModBlockItemIds.EVIL_PORTAL,
            properties -> new EvilPortalBlock(properties
                    .strength(2f)
                    .sound(ModSounds.EVIL_PORTAL_SOUNDS)
            ), Component.translatable("tooltip.morefeatures.evil_portal"));

    // Conjured Ice
    public static final DeferredBlock<Block> CONJURED_ICE = registerBlockWithoutItem("conjured_ice",
            properties -> new ConjuredIceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FROSTED_ICE)
                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "conjured_ice")))));

    // Icicle
    public static final DeferredBlock<Block> ICICLE = registerBlock(ModBlockItemIds.ICICLE,
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

    private static <T extends Block> DeferredBlock<T> registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, T> function){ // Blocks without an item don't need a BlockItemId. As such, this method accepts just a string
        return BLOCKS.registerBlock(name, function);
    }

    /**
    @deprecated methods. Use registerBlock(BlockItemId, Function) instead.

    I promise, fluorite is being migrated to the new method soon.
     */
    @Deprecated
    private static <T extends Block> DeferredBlock<T> registerLegacyBlock(String name, Function<BlockBehaviour.Properties, T> function){
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerLegacyBlockItem(name, toReturn);
        return toReturn;
    }

    @Deprecated
    private static <T extends Block> void registerLegacyBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.registerItem(name, (properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix())));
    }

    private static <T extends Block> DeferredBlock<T> registerBlockWithTooltip(BlockItemId id, Function<BlockBehaviour.Properties, T> function, Component... components){
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(id.block().identifier().getPath(),
                props -> function.apply(props.setId(id.block())));
        registerBlockItem(id, toReturn, components);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(BlockItemId id, DeferredBlock<T> block, Component... components){
        ModItems.ITEMS.registerItem(
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
        DeferredBlock<T> block = BLOCKS.registerBlock(id.block().identifier().getPath(),
                props -> function.apply(props.setId(id.block())));

        registerBlockItem(id, block);

        return block;
    }

    private static <T extends Block> void registerBlockItem(BlockItemId id, DeferredBlock<T> block) {
        ModItems.ITEMS.registerItem(
                id.item().identifier().getPath(),
                properties -> new BlockItem(block.get(), properties.setId(id.item()).useBlockDescriptionPrefix())
        );
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(
            BlockItemId id,
            Function<BlockBehaviour.Properties, T> blockFactory,
            UnaryOperator<BlockBehaviour.Properties> propertyModifier
    ) {
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
                    Properties props = propertiesSupplier.apply(color).setId(id.block());
                    DeferredBlock<Block> block = BLOCKS.register(name, () -> factory.apply(color, props));

                    // Register the BlockItem, just like with copper
                    registerBlockItem(id, block);

                    return block;
                }
        );
    }

    // Register method called in the mod event bus

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Blocks registered -> Performed by: " + MoreFeatures.MODID);
    }
}
