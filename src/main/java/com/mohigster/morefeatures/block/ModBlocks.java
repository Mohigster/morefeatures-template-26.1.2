package com.mohigster.morefeatures.block;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.custom.*;
import com.mohigster.morefeatures.block.custom.woodtype.ModWoodType;
import com.mohigster.morefeatures.block.entity.ModBlockEntities;
import com.mohigster.morefeatures.block.id.ModBlockItemIds;
import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.particles.ModParticleTypes;
import com.mohigster.morefeatures.sound.ModSounds;
import com.mohigster.morefeatures.tag.ModBlockTags;
import com.mohigster.morefeatures.worldgen.tree.ModTreeGrowers;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(MoreFeatures.MODID);

    // Same convention as ModItems. Section block registration into types of blocks e.g. // Aluminium Blocks

    //———————————————————————————————————————Aluminium Blocks————————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> ALUMINIUM_BLOCK = registerBlock(ModBlockItemIds.ALUMINIUM_BLOCK,
            properties -> new Block(properties
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
    public static final DeferredBlock<Block> AZURITE_ORE = registerLegacyBlock("azurite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_AZURITE_ORE = registerLegacyBlock("deepslate_azurite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)
            ));

    public static final DeferredBlock<Block> NETHER_AZURITE_ORE = registerLegacyBlock("nether_azurite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHER_ORE)
            ));

    public static final DeferredBlock<Block> END_AZURITE_ORE = registerLegacyBlock("end_azurite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> AZURITE_BLOCK = registerLegacyBlock("azurite_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = registerLegacyBlock("raw_azurite_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> AZURITE_STAIRS = registerLegacyBlock("azurite_stairs",
            properties -> new StairBlock(ModBlocks.AZURITE_BLOCK.get().defaultBlockState(), properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> AZURITE_SLAB = registerLegacyBlock("azurite_slab",
            properties -> new SlabBlock(properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> AZURITE_PRESSURE_PLATE = registerLegacyBlock("azurite_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.IRON, properties
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> AZURITE_BUTTON = registerLegacyBlock("azurite_button",
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

    public static final DeferredBlock<Block> EVERFROST_BLUE_ICE_ORE = registerLegacyBlock("everfrost_blue_ice_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLASS)
                    .friction(0.98F)
            ));

    public static final DeferredBlock<Block> EVERFROST_PACKED_ICE_ORE = registerLegacyBlock("everfrost_packed_ice_ore",
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

    // Standing sign
    public static final DeferredBlock<Block> BLOODWOOD_HANGING_SIGN =
            registerBlockWithoutItem("bloodwood_hanging_sign", properties ->
                    new CeilingHangingSignBlock(
                            ModWoodType.BLOODWOOD,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "bloodwood_hanging_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.BLOODWOOD_HANGING_SIGN_BE.get().create(pos, state); // Prevents game crash (no, literally)
                        }
                    }
            );

    // Wall sign
    public static final DeferredBlock<Block> BLOODWOOD_WALL_HANGING_SIGN =
            registerBlockWithoutItem("bloodwood_wall_hanging_sign", properties ->
                    new WallHangingSignBlock(
                            ModWoodType.BLOODWOOD,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "bloodwood_wall_hanging_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.BLOODWOOD_HANGING_SIGN_BE.get().create(pos, state);
                        }
                    }
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

    // Standing sign
    public static final DeferredBlock<Block> TAINTED_HANGING_SIGN =
            registerBlockWithoutItem("tainted_hanging_sign", properties ->
                    new CeilingHangingSignBlock(
                            ModWoodType.TAINTED,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "tainted_hanging_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.TAINTED_HANGING_SIGN_BE.get().create(pos, state); // Prevents game crash (no, literally)
                        }
                    }
            );

    // Wall sign
    public static final DeferredBlock<Block> TAINTED_WALL_HANGING_SIGN =
            registerBlockWithoutItem("tainted_wall_hanging_sign", properties ->
                    new WallHangingSignBlock(
                            ModWoodType.TAINTED,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "tainted_wall_hanging_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.TAINTED_HANGING_SIGN_BE.get().create(pos, state);
                        }
                    }
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
            properties -> new ModLeavesBlock(0.05F, ModParticleTypes.PALM_LEAVES.get(), properties
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
                    () -> Blocks.SAND
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
            properties -> new FenceBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_FENCE_GATE = registerBlock(ModBlockItemIds.PALM_FENCE_GATE,
            properties -> new FenceGateBlock(ModWoodType.PALM,
                    properties
                            .strength(2f, 2f)
                            .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_SIGN =
            registerBlockWithoutItem("palm_sign", properties ->
                    new StandingSignBlock(
                            ModWoodType.PALM,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "palm_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.PALM_SIGN_BE.get().create(pos, state); // Prevents game crash (no, literally)
                        }
                    }
            );

    public static final DeferredBlock<Block> PALM_WALL_SIGN = // Does not need a BlockItemId because the item and block are registered separately anyway
            registerBlockWithoutItem("palm_wall_sign", properties ->
                    new WallSignBlock(
                            ModWoodType.PALM,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "palm_wall_sign"))) // Since we're using BlockBehviour.Properties.of() (Which is necessary for most block entities) the ID is not set automatically. Game will crash if the ID is not set here. Block that use properties -> new /*BlockType*/(properties) do not use .setId and function perfectly with no crash, as that method sets the ID automatically.
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.PALM_SIGN_BE.get().create(pos, state);
                        }
                    }
            );

    // Ceiling sign
    public static final DeferredBlock<Block> PALM_HANGING_SIGN =
            registerBlockWithoutItem("palm_hanging_sign", properties ->
                    new CeilingHangingSignBlock(
                            ModWoodType.PALM,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "palm_hanging_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.PALM_HANGING_SIGN_BE.get().create(pos, state); // Prevents game crash (no, literally)
                        }
                    }
            );

    // Wall sign
    public static final DeferredBlock<Block> PALM_WALL_HANGING_SIGN =
            registerBlockWithoutItem("palm_wall_hanging_sign", properties ->
                    new WallHangingSignBlock(
                            ModWoodType.PALM,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "palm_wall_hanging_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.PALM_HANGING_SIGN_BE.get().create(pos, state);
                        }
                    }
            );

    public static final DeferredBlock<Block> PALM_TRAPDOOR = registerLegacyBlock("palm_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.ACACIA, properties
                    .strength(2f, 2f)
                    .noOcclusion()
            ));

    public static final DeferredBlock<Block> PALM_DOOR = registerLegacyBlock("palm_door",
            properties -> new DoorBlock(BlockSetType.ACACIA, properties
                    .strength(2f, 2f)
                    .noOcclusion()
            ));

    public static final DeferredBlock<Block> PALM_PRESSURE_PLATE = registerLegacyBlock("palm_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.ACACIA, properties
                    .strength(2f)
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALM_BUTTON = registerLegacyBlock("palm_button",
            properties -> new ButtonBlock(BlockSetType.ACACIA, 20, properties
                    .strength(2f)
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALM_SHELF = registerLegacyBlock("palm_shelf",
            properties -> new ShelfBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SHELF).setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "palm_shelf")))){
                @NullMarked
                @Override
                public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                    return ModBlockEntities.PALM_SHELF_BE.get().create(pos, state);
                }
            });

    //———————————————————————————————————————Decrepit Wood Blocks————————————————————————————————————————————————————————————————————
    public static final DeferredBlock<Block> DECREPIT_LOG = registerBlock(ModBlockItemIds.DECREPIT_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
            ));

    public static final DeferredBlock<Block> DECREPIT_WOOD = registerBlock(ModBlockItemIds.DECREPIT_WOOD,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
            ));

    public static final DeferredBlock<Block> STRIPPED_DECREPIT_LOG = registerBlock(ModBlockItemIds.STRIPPED_DECREPIT_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
            ));

    public static final DeferredBlock<Block> STRIPPED_DECREPIT_WOOD = registerBlock(ModBlockItemIds.STRIPPED_DECREPIT_WOOD,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
            ));

    public static final DeferredBlock<Block> DECREPIT_PLANKS = registerBlock(ModBlockItemIds.DECREPIT_PLANKS,
            properties -> new ModFlammableBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_LEAVES = registerBlock(ModBlockItemIds.DECREPIT_LEAVES,
            properties -> new ModLeavesBlock(0.02F, ModParticleTypes.DECREPIT_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> DECREPIT_NULLIUM = registerBlock(ModBlockItemIds.DECREPIT_NULLIUM,
            properties -> new NulliumBlock(properties
                    .sound(SoundType.NYLIUM)
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
            ));

    public static final DeferredBlock<Block> DECREPIT_SAPLING = registerBlock(ModBlockItemIds.DECREPIT_SAPLING,
            properties -> new PlantedOffGrassSaplingBlock(ModTreeGrowers.DECREPIT, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion()
                    .noCollision(),
                    DECREPIT_NULLIUM // The block that the sapling can be planted on
            ));

    public static final DeferredBlock<Block> POTTED_DECREPIT_SAPLING = registerBlockWithoutItem("potted_decrepit_sapling", // Item is the Decrepit Sapling, so no deperate BlockItemId needed.
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

    public static final DeferredBlock<Block> DECREPIT_FENCE = registerBlock(ModBlockItemIds.DECREPIT_FENCE,
            properties -> new FenceBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_FENCE_GATE = registerBlock(ModBlockItemIds.DECREPIT_FENCE_GATE,
            properties -> new FenceGateBlock(ModWoodType.DECREPIT,
                    properties
                            .strength(2f, 2f)
                            .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> DECREPIT_PRESSURE_PLATE = registerLegacyBlock("decrepit_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.WARPED, properties
                    .strength(2f)
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> DECREPIT_BUTTON = registerLegacyBlock("decrepit_button",
            properties -> new ButtonBlock(BlockSetType.WARPED, 20, properties
                    .strength(2f)
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> DECREPIT_ROOTS = registerLegacyBlock("decrepit_roots",
            properties -> new NetherRootsBlock(ModBlockTags.NULLIUM_BLOCKS, properties
                    .sound(SoundType.ROOTS)
                    .noOcclusion()
                    .noCollision()
                    .instabreak()
                    .replaceable()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
            ));

    public static final DeferredBlock<Block> DECREPIT_SIGN =
            registerBlockWithoutItem("decrepit_sign", properties ->
                    new StandingSignBlock(
                            ModWoodType.DECREPIT,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.NETHER_WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "decrepit_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.DECREPIT_SIGN_BE.get().create(pos, state); // Prevents game crash (no, literally)
                        }
                    }
            );

    public static final DeferredBlock<Block> DECREPIT_WALL_SIGN =
            registerBlockWithoutItem("decrepit_wall_sign", properties ->
                    new WallSignBlock(
                            ModWoodType.DECREPIT,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.NETHER_WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "decrepit_wall_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.DECREPIT_SIGN_BE.get().create(pos, state);
                        }
                    }
            );

    // Ceiling sign
    public static final DeferredBlock<Block> DECREPIT_HANGING_SIGN =
            registerBlockWithoutItem("decrepit_hanging_sign", properties ->
                    new CeilingHangingSignBlock(
                            ModWoodType.DECREPIT,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.NETHER_WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "decrepit_hanging_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.DECREPIT_HANGING_SIGN_BE.get().create(pos, state); // Prevents game crash (no, literally)
                        }
                    }
            );

    // Wall sign
    public static final DeferredBlock<Block> DECREPIT_WALL_HANGING_SIGN =
            registerBlockWithoutItem("decrepit_wall_hanging_sign", properties ->
                    new WallHangingSignBlock(
                            ModWoodType.DECREPIT,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.NETHER_WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "decrepit_wall_hanging_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.DECREPIT_HANGING_SIGN_BE.get().create(pos, state);
                        }
                    }
            );

    public static final DeferredBlock<Block> POTTED_DECREPIT_ROOTS = registerBlockWithoutItem("potted_decrepit_roots",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, DECREPIT_ROOTS, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    //———————————————————————————————————————Pallid Wood Blocks——————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> PALLID_LOG = registerBlock(ModBlockItemIds.PALLID_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
            ));

    public static final DeferredBlock<Block> PALLID_WOOD = registerBlock(ModBlockItemIds.PALLID_WOOD,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
            ));

    public static final DeferredBlock<Block> STRIPPED_PALLID_LOG = registerBlock(ModBlockItemIds.STRIPPED_PALLID_LOG,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
            ));

    public static final DeferredBlock<Block> STRIPPED_PALLID_WOOD = registerBlock(ModBlockItemIds.STRIPPED_PALLID_WOOD,
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.STEM)
            ));

    public static final DeferredBlock<Block> PALLID_PLANKS = registerBlock(ModBlockItemIds.PALLID_PLANKS,
            properties -> new ModFlammableBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_LEAVES = registerBlock(ModBlockItemIds.PALLID_LEAVES,
            properties -> new ModLeavesBlock(0.02F, ModParticleTypes.PALLID_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALLID_NULLIUM = registerBlock(ModBlockItemIds.PALLID_NULLIUM,
            properties -> new NulliumBlock(properties
                    .sound(SoundType.NYLIUM)
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
            )
    );

    public static final DeferredBlock<Block> PALLID_SAPLING = registerBlock(ModBlockItemIds.PALLID_SAPLING,
            properties -> new PlantedOffGrassSaplingBlock(ModTreeGrowers.PALLID, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion()
                    .noCollision(),
                    () -> PALLID_NULLIUM.get()
            )
    );

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
                    .ignitedByLava()
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_SLAB = registerBlock(ModBlockItemIds.PALLID_SLAB,
            properties -> new ModFlammableSlabBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALLID_FENCE = registerBlock(ModBlockItemIds.PALLID_FENCE,
            properties -> new FenceBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_FENCE_GATE = registerBlock(ModBlockItemIds.PALLID_FENCE_GATE,
            properties -> new FenceGateBlock(WoodType.WARPED,
                    properties
                            .strength(2f, 2f)
                            .sound(SoundType.NETHER_WOOD)
            ));

    public static final DeferredBlock<Block> PALLID_PRESSURE_PLATE = registerLegacyBlock("pallid_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.WARPED, properties
                    .strength(2f)
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_BUTTON = registerLegacyBlock("pallid_button",
            properties -> new ButtonBlock(BlockSetType.WARPED, 20, properties
                    .strength(2f)
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> PALLID_ROOTS = registerLegacyBlock("pallid_roots",
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

    public static final DeferredBlock<Block> PALLID_SIGN =
            registerBlockWithoutItem("pallid_sign", properties ->
                    new StandingSignBlock(
                            ModWoodType.PALLID,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.NETHER_WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "pallid_sign")))
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.PALLID_SIGN_BE.get().create(pos, state); // Prevents game crash (no, literally)
                        }
                    }
            );

    public static final DeferredBlock<Block> PALLID_WALL_SIGN =
            registerBlockWithoutItem("pallid_wall_sign", properties ->
                    new WallSignBlock(
                            ModWoodType.PALLID,
                            BlockBehaviour.Properties.of()
                                    .noCollision()
                                    .strength(1.0f)
                                    .sound(SoundType.NETHER_WOOD)
                                    .setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "pallid_wall_sign"))) // Since we're using BlockBehviour.Properties.of() (Which is necessary for most block entities) the ID is not set automatically. Game will crash if the ID is not set here. Block that use properties -> new /*BlockType*/(properties) do not use .setId and function perfectly with no crash, as that method sets the ID automatically.
                    ){
                        @NullMarked
                        @Override
                        public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
                            return ModBlockEntities.PALLID_SIGN_BE.get().create(pos, state);
                        }
                    }
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
                    MobEffects.SLOW_FALLING, 10, properties
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
                    .lightLevel(statex -> VoidAnchorBlock.getScaledChargeLevel(statex, 15))
            ));

    // Magic block!
    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock(ModBlockItemIds.MAGIC_BLOCK,
            properties -> new MagicBlock(properties
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(ModSounds.MAGIC_BLOCK_SOUNDS)
            ), Component.translatable("tooltip.morefeatures.magic_block"));


    // Compressor block
    public static final DeferredBlock<Block> COMPRESSOR_BLOCK = registerBlock(ModBlockItemIds.COMPRESSOR_BLOCK,
            properties -> new CompressorBlock(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
            ));

    // Portal Block
    public static final DeferredBlock<Block> EVIL_PORTAL = registerBlock(ModBlockItemIds.EVIL_PORTAL,
            properties -> new EvilPortalBlock(properties
                    .strength(2f)
                    .sound(ModSounds.MAGIC_BLOCK_SOUNDS)
            ), Component.translatable("tooltip.morefeatures.evil_portal"));

    // Conjured Ice
    public static final DeferredBlock<Block> CONJURED_ICE = registerBlockWithoutItem("conjured_ice", // Blocks without items don't need a BlockItemId. Keep just the string
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

    private static <T extends Block> DeferredBlock<T> registerBlockWithoutItem(String name, Function<BlockBehaviour.Properties, T> function){ // Blocks without an item don't need a BlockItemId.
        return BLOCKS.registerBlock(name, function);
    }

    /*
    Deprecated methods. Use registerBlock(BlockItemId, Function) instead.
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

    private static <T extends Block> DeferredBlock<T> registerBlock(BlockItemId id, Function<BlockBehaviour.Properties, T> function, Component... components){
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(id.block().identifier().getPath(),
                props -> function.apply(props.setId(id.block())));
        registerBlockItem(id, toReturn, components);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(BlockItemId id, DeferredBlock<T> block, Component... components){
        ModItems.ITEMS.registerItem(
                id.item().identifier().getPath(), // Minecraft uses Identifier over ResourceLocation since 1.21.5. the location() method was replaced with identifier() at the same time.
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

    /*
    In 26.2, Mojang changed some things about TagProviders. In my ModItemTagsProvider class, I could no longer
    call something such as .add(ModBlocks.ALUMINIUM_BLOCK.asItem()). Why? Because .add() now needed a ResourceKey<Item>,
    instead of a plain Item. The only fix was to shift to BlockItemIds and call .add(ModBlockItemIds.ALUMINIUM_BLOCK.item()).
    That's what this new method is for. The above methods are deprecated and block registrations will be incrementally
    shifted to this new method. I will spend ~20 mins per day creating and attaching BlockItemIds this way.

    I have to do this incrementally because this class is ~1500 lines of code long. There are simply too many blocks
    that I have added in my mod to add all of the BlockItemIds and switch all the registrations all at once.
     */

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
                id.item().identifier().getPath(), // Minecraft uses Identifier over ResourceLocation since 1.21.5. the location() method was replaced with identifier() at the same time.
                properties -> new BlockItem(block.get(), properties.setId(id.item()).useBlockDescriptionPrefix())
        );
    }

    // Register method called in the mod event bus

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Blocks registered -> Performed by: " + MoreFeatures.MODID);
    }
}
