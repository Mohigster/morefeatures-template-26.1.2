package com.mohigster.morefeatures.block;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.custom.*;
import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.sound.ModSounds;
import com.mohigster.morefeatures.worldgen.tree.ModTreeGrowers;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;
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
                    .strength(6f, 6f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> BISMUTH_BLOCK = registerBlock("bismuth_block",
            properties -> new Block(properties
                    .strength(8f, 8f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_BISMUTH_BLOCK = registerBlock("raw_bismuth_block",
            properties -> new Block(properties
                    .strength(8f, 8f)
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
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = registerBlock("raw_azurite_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> AZURITE_STAIRS = registerBlock("azurite_stairs",
            properties -> new StairBlock(ModBlocks.AZURITE_BLOCK.get().defaultBlockState(), properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> AZURITE_SLAB = registerBlock("azurite_slab",
            properties -> new SlabBlock(properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> AZURITE_PRESSURE_PLATE = registerBlock("azurite_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.IRON, properties
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .forceSolidOn()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> AZURITE_BUTTON = registerBlock("azurite_button",
            properties -> new ButtonBlock(BlockSetType.IRON, 20, properties
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
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
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> RAW_FLUORITE_BLOCK = registerBlock("raw_fluorite_block",
            properties -> new Block(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    public static final DeferredBlock<Block> FLUORITE_STAIRS = registerBlock("fluorite_stairs",
            properties -> new StairBlock(ModBlocks.FLUORITE_BLOCK.get().defaultBlockState(), properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> FLUORITE_SLAB = registerBlock("fluorite_slab",
            properties -> new SlabBlock(properties
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.AMETHYST)
            ));

    //———————————————————————————————————————Everfrost Blocks————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> EVERFROST_BLUE_ICE_ORE = registerBlock("everfrost_blue_ice_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLASS)
            ));

    public static final DeferredBlock<Block> EVERFROST_PACKED_ICE_ORE = registerBlock("everfrost_packed_ice_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties
                    .strength(4f, 2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLASS)
            ));



    //———————————————————————————————————————Bloodwood Blocks————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> BLOODWOOD_LOG = registerBlock("bloodwood_log",
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> BLOODWOOD = registerBlock("bloodwood",
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_LOG = registerBlock("stripped_bloodwood_log",
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD = registerBlock("stripped_bloodwood",
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_PLANKS = registerBlock("bloodwood_planks",
            properties -> new Block(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD))
            {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return 5;
                }
            });

    public static final DeferredBlock<Block> BLOODWOOD_LEAVES = registerBlock("bloodwood_leaves",
            properties -> new TintedParticleLeavesBlock(4, properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
            )



            {
                @Override
                public MapCodec<? extends TintedParticleLeavesBlock> codec() {
                    return null;
                }

                @Override
                protected void spawnFallingLeavesParticle(Level level, BlockPos blockPos, RandomSource randomSource) {

                }

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return 30;
                }
            }
    );
    public static final DeferredBlock<Block> BLOODWOOD_SAPLING = registerBlock("bloodwood_sapling",
            properties -> new SaplingBlock(ModTreeGrowers.BLOODWOOD, properties
                    .randomTicks()
                    .instabreak()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS))
    );

    public static final DeferredBlock<Block> POTTED_BLOODWOOD_SAPLING = BLOCKS.registerBlock("potted_bloodwood_sapling",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, BLOODWOOD_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> BLOODWOOD_STAIRS = registerBlock("bloodwood_stairs",
            properties -> new StairBlock(ModBlocks.FLUORITE_BLOCK.get().defaultBlockState(), properties
                    .strength(3f)
                    .ignitedByLava()
                    .sound(SoundType.WOOD))
            {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            }
    );

    public static final DeferredBlock<Block> BLOODWOOD_SLAB = registerBlock("bloodwood_slab",
            properties -> new SlabBlock(properties
                    .strength(1f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            )
            {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            }
    );

    //———————————————————————————————————————Tainted Wood Blocks—————————————————————————————————————————————————————————————————————


    public static final DeferredBlock<Block> TAINTED_LOG = registerBlock("tainted_log",
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> TAINTED_WOOD = registerBlock("tainted_wood",
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> STRIPPED_TAINTED_LOG = registerBlock("stripped_tainted_log",
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> STRIPPED_TAINTED_WOOD = registerBlock("stripped_tainted_wood",
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> TAINTED_PLANKS = registerBlock("tainted_planks",
            properties -> new Block(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD))
            {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return 5;
                }
            });

    public static final DeferredBlock<Block> TAINTED_LEAVES = registerBlock("tainted_leaves",
            properties -> new TintedParticleLeavesBlock(4, properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
            )
            {
                @Override
                public MapCodec<? extends TintedParticleLeavesBlock> codec() {
                    return null;
                }

                @Override
                protected void spawnFallingLeavesParticle(Level level, BlockPos blockPos, RandomSource randomSource) {

                }

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
                    return 30;
                }
            }
    );

    public static final DeferredBlock<Block> TAINTED_SAPLING = registerBlock("tainted_sapling",
            properties -> new SaplingBlock(ModTreeGrowers.TAINTED, properties
                    .randomTicks()
                    .instabreak()
                    .noCollision()
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS))
    );

    public static final DeferredBlock<Block> POTTED_TAINTED_SAPLING = BLOCKS.registerBlock("potted_tainted_sapling",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, TAINTED_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> TAINTED_STAIRS = registerBlock("tainted_stairs",
            properties -> new StairBlock(ModBlocks.FLUORITE_BLOCK.get().defaultBlockState(), properties
                    .strength(3f)
                    .ignitedByLava()
                    .sound(SoundType.WOOD))
            {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            }
    );

    public static final DeferredBlock<Block> TAINTED_SLAB = registerBlock("tainted_slab",
            properties -> new SlabBlock(properties
                    .strength(1f)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            )
            {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            }
    );

    //———————————————————————————————————————Palm Blocks—————————————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> PALM_LOG = registerBlock("palm_log",
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_WOOD = registerBlock("palm_wood",
            properties -> new ModFlammableRotatedPillarBlock(properties
                    .strength(2f, 2f)
                    .sound(SoundType.WOOD)
            ));

    public static final DeferredBlock<Block> PALM_SAPLING = registerBlock("palm_sapling",
            properties -> new PlantedOffGrassSaplingBlock(ModTreeGrowers.PALM, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion(),
                    () -> Blocks.SAND));

    public static final DeferredBlock<Block> POTTED_PALM_SAPLING = BLOCKS.registerBlock("potted_palm_sapling",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, PALM_SAPLING, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));
    // Liquid

//    public static final DeferredBlock<Block> OIL_BLOCK = BLOCKS.register("oil_block",
//            () -> new LiquidBlock(ModFluids.OIL_SOURCE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));

    // Flowers

    public static final DeferredBlock<Block> ROSE = registerBlock("rose",
            properties -> new FlowerBlock(
                    MobEffects.SLOW_FALLING, 10, properties
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .noCollision()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS)
            ));

    public static final DeferredBlock<Block> POTTED_ROSE = BLOCKS.registerBlock("potted_rose",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, ROSE, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    public static final DeferredBlock<Block> BLUE_ROSE = registerBlock("blue_rose",
            properties -> new FlowerBlock(
                    MobEffects.SLOW_FALLING, 10, properties
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .noCollision()
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)
                    .sound(SoundType.GRASS)
            ));

    public static final DeferredBlock<Block> POTTED_BLUE_ROSE = BLOCKS.registerBlock("potted_blue_rose",
            properties -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, BLUE_ROSE, properties
                    .noOcclusion()
                    .instabreak()
                    .pushReaction(PushReaction.DESTROY)
            ));

    // Magic block!
    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(properties
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(ModSounds.MAGIC_BLOCK_SOUNDS)
            ), Component.translatable("tooltip.morefeatures.magic_block"));


    // TEST CRAFTING STATION
    public static final DeferredBlock<Block> COMPRESSOR_BLOCK = registerBlock("compressor_block",
            properties -> new CompressorBlock(properties
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.IRON)
            ));

    // Portal Block
    public static final DeferredBlock<Block> EVIL_PORTAL = registerBlock("evil_portal",
            properties -> new EvilPortalBlock(properties
                    .strength(2f)
                    .sound(ModSounds.MAGIC_BLOCK_SOUNDS)));






    // Register functions
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function){
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        ModItems.ITEMS.registerItem(name, (properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix())));
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> function, Component... components){
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, function);
        registerBlockItem(name, toReturn, components);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block, Component... components){
        ModItems.ITEMS.registerItem(name, (properties -> new BlockItem(block.get(), properties.useBlockDescriptionPrefix()){
            @Override
            public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                for(var component : components) {
                    builder.accept(component);
                }
                super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
            }
        }));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Blocks registered -> Performed by: " + MoreFeatures.MODID);
    }
}
