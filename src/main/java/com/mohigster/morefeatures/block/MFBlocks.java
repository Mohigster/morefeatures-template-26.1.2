package com.mohigster.morefeatures.block;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.collection.gemstone.GemstoneCollection;
import com.mohigster.morefeatures.block.collection.vanilla.VanillaWoodCollection;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.block.custom.*;
import com.mohigster.morefeatures.block.custom.modified.*;
import com.mohigster.morefeatures.block.custom.modified.nethervines.scorched.CeilingVinesBlock;
import com.mohigster.morefeatures.block.custom.modified.nethervines.scorched.CeilingVinesPlantBlock;
import com.mohigster.morefeatures.block.custom.modified.nethervines.smoldered.FloorVinesBlock;
import com.mohigster.morefeatures.block.custom.modified.nethervines.smoldered.FloorVinesPlantBlock;
import com.mohigster.morefeatures.block.custom.modified.sign.MFCeilingHangingSignBlock;
import com.mohigster.morefeatures.block.custom.modified.sign.MFStandingSignBlock;
import com.mohigster.morefeatures.block.custom.modified.sign.MFWallHangingSignBlock;
import com.mohigster.morefeatures.block.custom.modified.sign.MFWallSignBlock;
import com.mohigster.morefeatures.block.custom.nylium.MFNyliumBlock;
import com.mohigster.morefeatures.block.custom.nylium.NulliumBlock;
import com.mohigster.morefeatures.block.custom.pillar.PillarBlock;
import com.mohigster.morefeatures.block.custom.magicblock.MagicBlock;
import com.mohigster.morefeatures.block.custom.pillar.WeatheringCopperPillarBlock;
import com.mohigster.morefeatures.block.custom.portal.EvilPortalBlock;
import com.mohigster.morefeatures.block.custom.portal.datadriven.PortalBlock;
import com.mohigster.morefeatures.block.custom.temporaldilator.TemporalDilatorBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.WeatheringCopperVerticalSlabBlock;
import com.mohigster.morefeatures.data.resources.references.MFBlockIds;
import com.mohigster.morefeatures.data.resources.references.MFBlockItemIds;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.particles.MFParticleTypes;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import com.mohigster.morefeatures.data.sound.MFSoundTypes;
import com.mohigster.morefeatures.data.tag.MFBlockTags;
import com.mohigster.morefeatures.data.world.feature.MFConfiguredFeatures;
import com.mohigster.morefeatures.data.world.tree.MFTreeGrowers;
import com.mohigster.morefeatures.util.PropertyUtil;
import com.mohigster.morefeatures.util.VanillaCollectionUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.function.*;

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
                    .isValidSpawn(PropertyUtil.Blocks::always)
                    .isRedstoneConductor(PropertyUtil.Blocks::always)
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
                    .isRedstoneConductor(PropertyUtil.Blocks::always)
                    .isValidSpawn(PropertyUtil.Blocks::always)
                    .isViewBlocking(PropertyUtil.Blocks::always)
                    .isSuffocating(PropertyUtil.Blocks::always)
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

    //———————————————————————————————————————Gemstone Blocks—————————————————————————————————————————————————————————————————————————

    public static final GemstoneCollection<DeferredBlock<Block>> ORE = GemstoneCollection.registerBlocks(
            MFBlockItemIds.ORE,
            MFBlocks::registerBlock,
            (_, props) -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            PropertyUtil.Blocks::stoneGemOreProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> DEEPSLATE_ORE = GemstoneCollection.registerBlocks(
            MFBlockItemIds.DEEPSLATE_ORE,
            MFBlocks::registerBlock,
            (_, props) -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            PropertyUtil.Blocks::deepslateGemOreProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> NETHER_ORE = GemstoneCollection.registerBlocks(
            MFBlockItemIds.NETHER_ORE,
            MFBlocks::registerBlock,
            (_, props) -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            PropertyUtil.Blocks::netherGemOreProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> END_ORE = GemstoneCollection.registerBlocks(
            MFBlockItemIds.END_ORE,
            MFBlocks::registerBlock,
            (_, props) -> new DropExperienceBlock(UniformInt.of(2, 4), props),
            PropertyUtil.Blocks::endGemOreProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_BLOCK = GemstoneCollection.registerBlocks(
            MFBlockItemIds.BLOCK,
            MFBlocks::registerBlock,
            (_, props) -> new Block(props),
            PropertyUtil.Blocks::gemProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> RAW_GEM_BLOCK = GemstoneCollection.registerBlocks(
            MFBlockItemIds.RAW_BLOCK,
            MFBlocks::registerBlock,
            (_, props) -> new Block(props),
            PropertyUtil.Blocks::gemProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_STAIRS = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_STAIRS,
            MFBlocks::registerBlock,
            (gem, props) -> new StairBlock(GEMSTONE_BLOCK.pick(gem).get().defaultBlockState(), props),
            PropertyUtil.Blocks::gemProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_SLAB = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_SLAB,
            MFBlocks::registerBlock,
            (_, props) -> new SlabBlock(props),
            PropertyUtil.Blocks::gemProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_VERTICAL_SLAB = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_VERTICAL_SLAB,
            MFBlocks::registerBlock,
            (_, props) -> new VerticalSlabBlock(props),
            PropertyUtil.Blocks::gemProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_FENCE = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_FENCE,
            MFBlocks::registerBlock,
            (_, props) -> new MFFenceBlock(props),
            PropertyUtil.Blocks::gemFenceProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_FENCE_GATE = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_FENCE_GATE,
            MFBlocks::registerBlock,
            (gem, props) -> new FenceGateBlock(gem.woodType(), props),
            PropertyUtil.Blocks::gemFenceProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_PRESSURE_PLATE = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_PRESSURE_PLATE,
            MFBlocks::registerBlock,
            (gem, props) -> new PressurePlateBlock(gem.blockSetType(), props),
            PropertyUtil.Blocks::gemFenceProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_BUTTON = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_BUTTON,
            MFBlocks::registerBlock,
            (gem, props) -> new ButtonBlock(gem.blockSetType(), 20, props),
            PropertyUtil.Blocks::gemFenceProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_WALL = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_WALL,
            MFBlocks::registerBlock,
            (_, props) -> new WallBlock(props),
            PropertyUtil.Blocks::gemFenceProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_DOOR = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_DOOR,
            MFBlocks::registerBlock,
            (gem, props) -> new DoorBlock(gem.blockSetType(), props),
            PropertyUtil.Blocks::gemFenceProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_TRAPDOOR = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_TRAPDOOR,
            MFBlocks::registerBlock,
            (gem, props) -> new TrapDoorBlock(gem.blockSetType(), props),
            PropertyUtil.Blocks::gemFenceProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_SHELF = GemstoneCollection.registerBlocks(
            MFBlockItemIds.GEMSTONE_SHELF,
            MFBlocks::registerBlock,
            (_, props) -> new MFShelfBlock(props),
            (gem, props) -> PropertyUtil.Blocks.gemProps(gem, props).sound(SoundType.MEDIUM_AMETHYST_BUD)
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_SIGN = GemstoneCollection.registerBlocks(
            MFBlockIds.GEMSTONE_SIGN,
            MFBlocks::registerBlockWithoutItem,
            MFStandingSignBlock::new,
            PropertyUtil.Blocks::gemSignProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_WALL_SIGN = GemstoneCollection.registerBlocks(
            MFBlockIds.GEMSTONE_WALL_SIGN,
            MFBlocks::registerBlockWithoutItem,
            MFWallSignBlock::new,
            PropertyUtil.Blocks::gemSignProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_HANGING_SIGN = GemstoneCollection.registerBlocks(
            MFBlockIds.GEMSTONE_HANGING_SIGN,
            MFBlocks::registerBlockWithoutItem,
            MFCeilingHangingSignBlock::new,
            PropertyUtil.Blocks::gemSignProps
    );

    public static final GemstoneCollection<DeferredBlock<Block>> GEMSTONE_WALL_HANGING_SIGN = GemstoneCollection.registerBlocks(
            MFBlockIds.GEMSTONE_WALL_HANGING_SIGN,
            MFBlocks::registerBlockWithoutItem,
            MFWallHangingSignBlock::new,
            PropertyUtil.Blocks::gemSignProps
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

    public static final DeferredBlock<Block> BLOODWOOD_LEAVES = registerBlock(MFBlockItemIds.BLOODWOOD_LEAVES,
            props -> new UntintedParticleLeavesBlock(0.03F, MFParticleTypes.BLOODWOOD_LEAVES.get(), props),
            _ -> Properties.ofFullCopy(Blocks.OAK_LEAVES)
    );

    public static final DeferredBlock<Block> BLOODWOOD_SAPLING = registerBlock(MFBlockItemIds.BLOODWOOD_SAPLING,
            props -> new SaplingBlock(MFTreeGrowers.BLOODWOOD, props),
            _ -> Properties.ofFullCopy(Blocks.OAK_SAPLING)
    );

    //———————————————————————————————————————Tainted Wood Blocks—————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> TAINTED_LEAVES = registerBlock(MFBlockItemIds.TAINTED_LEAVES,
            properties -> new UntintedParticleLeavesBlock(0.03F, MFParticleTypes.TAINTED_LEAVES.get(), properties
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
            properties -> new UntintedParticleLeavesBlock(0.01F, MFParticleTypes.PALM_LEAVES.get(), properties
                    .strength(0.2F, 0.2F)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
            ));

    public static final DeferredBlock<Block> PALM_SAPLING = registerBlock(MFBlockItemIds.PALM_SAPLING,
            properties -> new PlantedOffGrassSaplingBlock(MFTreeGrowers.PALM,
                    () -> Blocks.SAND, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion()
                    .noCollision()
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
            props -> new MFNyliumBlock(MFConfiguredFeatures.CHARRED_VEGETATION_BONEMEAL_KEY, props),
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
                    MFBlockTags.SUPPORTS_CHARRED_FUNGUS,
                    props
            ),
            _ -> Properties.ofFullCopy(Blocks.WARPED_FUNGUS).mapColor(MapColor.COLOR_GRAY)
    );

    public static final DeferredBlock<Block> SCORCHED_VINES = registerBlock(MFBlockItemIds.SCORCHED_VINES,
            CeilingVinesBlock::new,
            _ -> Properties.ofFullCopy(Blocks.WEEPING_VINES)
    );

    public static final DeferredBlock<Block> SCORCHED_VINES_PLANT = registerBlockWithoutItem(MFBlockIds.SCORCHED_VINES_PLANT,
            CeilingVinesPlantBlock::new,
            _ -> Properties.ofFullCopy(Blocks.WEEPING_VINES_PLANT)
    );

    public static final DeferredBlock<Block> SMOLDERED_VINES = registerBlock(MFBlockItemIds.SMOLDERED_VINES,
            FloorVinesBlock::new,
            _ -> Properties.ofFullCopy(Blocks.TWISTING_VINES)
    );

    public static final DeferredBlock<Block> SMOLDERED_VINES_PLANT = registerBlockWithoutItem(MFBlockIds.SMOLDERED_VINES_PLANT,
            FloorVinesPlantBlock::new,
            _ -> Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT)
    );

    //———————————————————————————————————————Decrepit Wood Blocks————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> DECREPIT_LEAVES = registerBlock(MFBlockItemIds.DECREPIT_LEAVES,
            properties -> new UntintedParticleLeavesBlock(0.02F, MFParticleTypes.DECREPIT_LEAVES.get(), properties
                    .strength(0.2f, 0.2f)
                    .sound(SoundType.GRASS)
                    .noOcclusion()
                    .ignitedByLava()
                    .mapColor(Blocks.PALE_OAK_LEAVES.defaultMapColor())
            ));

    public static final DeferredBlock<Block> DECREPIT_SAPLING = registerBlock(MFBlockItemIds.DECREPIT_SAPLING,
            properties -> new PlantedOffGrassSaplingBlock
                    (MFTreeGrowers.DECREPIT, MFBlockTags.NULLIUM, properties
                    .sound(SoundType.GRASS)
                    .instabreak()
                    .noOcclusion()
                    .noCollision()
                    .mapColor(MapColor.PLANT)
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
            properties -> new NulliumBlock(MFConfiguredFeatures.DECREPIT_VEGETATION_BONEMEAL_KEY,
                    properties.sound(SoundType.NYLIUM)
                    .strength(4f, 4f)
                    .requiresCorrectToolForDrops()
                    .randomTicks()
                    .mapColor(MapColor.TERRACOTTA_BLUE)
            ));

    //———————————————————————————————————————Pallid Wood Blocks——————————————————————————————————————————————————————————————————————

    public static final DeferredBlock<Block> PALLID_LEAVES = registerBlock(MFBlockItemIds.PALLID_LEAVES,
            props -> new UntintedParticleLeavesBlock(0.02F, MFParticleTypes.PALLID_LEAVES.get(), props),
            _ -> Properties.ofFullCopy(DECREPIT_LEAVES.get())
    );

    public static final DeferredBlock<Block> PALLID_SAPLING = registerBlock(MFBlockItemIds.PALLID_SAPLING,
            props -> new PlantedOffGrassSaplingBlock(MFTreeGrowers.PALLID, MFBlockTags.NULLIUM, props),
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
            props -> new NulliumBlock(MFConfiguredFeatures.PALLID_VEGETATION_BONEMEAL_KEY, props),
            _ -> Properties.ofFullCopy(DECREPIT_NULLIUM.get()).mapColor(MapColor.TERRACOTTA_GREEN)
    );

    //———————————————————————————————————————Wooden Blocks———————————————————————————————————————————————————————————————————————————

    public static final WoodTypeCollection<DeferredBlock<Block>> LOG = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.LOG,
            MFBlocks::registerBlock,
            (_, props) -> new RotatedPillarBlock(props),
            PropertyUtil.Blocks::logProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOOD = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOOD,
            MFBlocks::registerBlock,
            (_, props) -> new RotatedPillarBlock(props),
            PropertyUtil.Blocks::logProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> STRIPPED_LOG = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.STRIPPED_LOG,
            MFBlocks::registerBlock,
            (_, props) -> new RotatedPillarBlock(props),
            PropertyUtil.Blocks::logProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> STRIPPED_WOOD = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.STRIPPED_WOOD,
            MFBlocks::registerBlock,
            (_, props) -> new RotatedPillarBlock(props),
            PropertyUtil.Blocks::logProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> PLANKS = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.PLANKS,
            MFBlocks::registerBlock,
            (_, props) -> new Block(props),
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_STAIRS = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_STAIRS,
            MFBlocks::registerBlock,
            (wood, props) -> new StairBlock(PLANKS.pick(wood).get().defaultBlockState(), props),
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_SLAB = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_SLAB,
            MFBlocks::registerBlock,
            (_, props) -> new SlabBlock(props),
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_VERTICAL_SLAB = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_VERTICAL_SLAB,
            MFBlocks::registerBlock,
            (_, props) -> new VerticalSlabBlock(props),
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_FENCE = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_FENCE,
            MFBlocks::registerBlock,
            (_, props) -> new FenceBlock(props),
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_FENCE_GATE = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_FENCE_GATE,
            MFBlocks::registerBlock,
            (set, props) -> new FenceGateBlock(set.woodType(), props),
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_PRESSURE_PLATE = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_PRESSURE_PLATE,
            MFBlocks::registerBlock,
            (set, props) -> new PressurePlateBlock(set.blockSetType(), props),
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_BUTTON = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_BUTTON,
            MFBlocks::registerBlock,
            (set, props) -> new ButtonBlock(set.blockSetType(), 20, props),
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_DOOR = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_DOOR,
            MFBlocks::registerBlock,
            (set, props) -> new DoorBlock(set.blockSetType(), props),
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_TRAPDOOR = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_TRAPDOOR,
            MFBlocks::registerBlock,
            (set, props) -> new TrapDoorBlock(set.blockSetType(), props),
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_SHELF = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_SHELF,
            MFBlocks::registerBlock,
            (_, props) -> new MFShelfBlock(props),
            PropertyUtil.Blocks::woodShelfProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_SIGN = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_SIGN,
            MFBlocks::registerSign,
            MFStandingSignBlock::new,
            PropertyUtil.Blocks::woodSignProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_WALL_SIGN = WoodTypeCollection.registerBlocks(
            MFBlockIds.WOODEN_WALL_SIGN,
            MFBlocks::registerBlockWithoutItem,
            MFWallSignBlock::new,
            PropertyUtil.Blocks::woodSignProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_HANGING_SIGN = WoodTypeCollection.registerBlocks(
            MFBlockItemIds.WOODEN_HANGING_SIGN,
            MFBlocks::registerSign,
            MFCeilingHangingSignBlock::new,
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> WOODEN_WALL_HANGING_SIGN = WoodTypeCollection.registerBlocks(
            MFBlockIds.WOODEN_WALL_HANGING_SIGN,
            MFBlocks::registerBlockWithoutItem,
            MFWallHangingSignBlock::new,
            PropertyUtil.Blocks::woodProps
    );

    public static final WoodTypeCollection<DeferredBlock<Block>> POTTED_SAPLING = WoodTypeCollection.registerBlocks(
            MFBlockIds.POTTED_SAPLING,
            MFBlocks::registerBlockWithoutItem,
            (wood, props) -> new FlowerPotBlock(() -> (FlowerPotBlock)
                    Blocks.FLOWER_POT, wood.saplingOrFungus(), props),
            (_, props) -> props.noOcclusion().instabreak().pushReaction(PushReaction.DESTROY)
    );

    /*
     * Vertical slabs for vanilla block types.
     * Because there are so many, I will be subdividing this section into more sections
     */

    /* --- STONE & DEEPSLATE SLABS --- */

    public static final DeferredBlock<Block> STONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.STONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.STONE)
    );

    public static final DeferredBlock<Block> COBBLESTONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.COBBLESTONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.COBBLESTONE)
    );

    public static final DeferredBlock<Block> MOSSY_COBBLESTONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.MOSSY_COBBLESTONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE)
    );

    public static final DeferredBlock<Block> SMOOTH_STONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.SMOOTH_STONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_STONE)
    );

    public static final DeferredBlock<Block> STONE_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.STONE_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.STONE_BRICKS)
    );

    public static final DeferredBlock<Block> MOSSY_STONE_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.MOSSY_STONE_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MOSSY_STONE_BRICKS)
    );

    public static final DeferredBlock<Block> COBBLED_DEEPSLATE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.COBBLED_DEEPSLATE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE)
    );

    public static final DeferredBlock<Block> POLISHED_DEEPSLATE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.POLISHED_DEEPSLATE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_DEEPSLATE)
    );

    public static final DeferredBlock<Block> DEEPSLATE_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.DEEPSLATE_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DEEPSLATE_BRICKS)
    );

    public static final DeferredBlock<Block> DEEPSLATE_TILE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.DEEPSLATE_TILE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DEEPSLATE_TILES)
    );

    /* --- OTHER STONE TYPE SLABS --- */

    public static final DeferredBlock<Block> GRANITE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.GRANITE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.GRANITE)
    );

    public static final DeferredBlock<Block> POLISHED_GRANITE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.POLISHED_GRANITE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_GRANITE)
    );

    public static final DeferredBlock<Block> DIORITE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.DIORITE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DIORITE)
    );

    public static final DeferredBlock<Block> POLISHED_DIORITE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.POLISHED_DIORITE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_DIORITE)
    );

    public static final DeferredBlock<Block> ANDESITE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.ANDESITE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.ANDESITE)
    );

    public static final DeferredBlock<Block> POLISHED_ANDESITE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.POLISHED_ANDESITE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_ANDESITE)
    );

    public static final DeferredBlock<Block> TUFF_VERTICAL_SLAB = registerBlock(MFBlockItemIds.TUFF_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.TUFF)
    );

    public static final DeferredBlock<Block> POLISHED_TUFF_VERTICAL_SLAB = registerBlock(MFBlockItemIds.POLISHED_TUFF_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_TUFF)
    );

    public static final DeferredBlock<Block> TUFF_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.TUFF_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.TUFF_BRICKS)
    );

    /* --- SANDSTONE SLABS --- */

    public static final DeferredBlock<Block> SANDSTONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.SANDSTONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SANDSTONE)
    );

    public static final DeferredBlock<Block> SMOOTH_SANDSTONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.SMOOTH_SANDSTONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_SANDSTONE)
    );

    public static final DeferredBlock<Block> CUT_SANDSTONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.CUT_SANDSTONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CUT_SANDSTONE)
    );

    public static final DeferredBlock<Block> RED_SANDSTONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.RED_SANDSTONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.RED_SANDSTONE)
    );

    public static final DeferredBlock<Block> SMOOTH_RED_SANDSTONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_RED_SANDSTONE)
    );

    public static final DeferredBlock<Block> CUT_RED_SANDSTONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.CUT_RED_SANDSTONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CUT_RED_SANDSTONE)
    );

    /* --- SULFUR & CINNABAR SLABS --- */

    public static final DeferredBlock<Block> SULFUR_VERTICAL_SLAB = registerBlock(MFBlockItemIds.SULFUR_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SULFUR)
    );

    public static final DeferredBlock<Block> POLISHED_SULFUR_VERTICAL_SLAB = registerBlock(MFBlockItemIds.POLISHED_SULFUR_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_SULFUR)
    );

    public static final DeferredBlock<Block> SULFUR_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.SULFUR_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SULFUR_BRICKS)
    );

    public static final DeferredBlock<Block> CINNABAR_VERTICAL_SLAB = registerBlock(MFBlockItemIds.CINNABAR_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CINNABAR)
    );

    public static final DeferredBlock<Block> POLISHED_CINNABAR_VERTICAL_SLAB = registerBlock(MFBlockItemIds.POLISHED_CINNABAR_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_CINNABAR)
    );

    public static final DeferredBlock<Block> CINNABAR_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.CINNABAR_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.CINNABAR_BRICKS)
    );

    /* --- NETHER & END SLABS --- */

    public static final DeferredBlock<Block> NETHER_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.NETHER_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.NETHER_BRICKS)
    );

    public static final DeferredBlock<Block> RED_NETHER_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.RED_NETHER_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.RED_NETHER_BRICKS)
    );

    public static final DeferredBlock<Block> BLACKSTONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.BLACKSTONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BLACKSTONE)
    );

    public static final DeferredBlock<Block> POLISHED_BLACKSTONE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.POLISHED_BLACKSTONE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE)
    );

    public static final DeferredBlock<Block> POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
    );

    public static final DeferredBlock<Block> END_STONE_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.END_STONE_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.END_STONE_BRICKS)
    );

    public static final DeferredBlock<Block> PURPUR_VERTICAL_SLAB = registerBlock(MFBlockItemIds.PURPUR_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PURPUR_BLOCK)
    );

    public static final DeferredBlock<Block> QUARTZ_VERTICAL_SLAB = registerBlock(MFBlockItemIds.QUARTZ_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)
    );

    public static final DeferredBlock<Block> SMOOTH_QUARTZ_VERTICAL_SLAB = registerBlock(MFBlockItemIds.SMOOTH_QUARTZ_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ)
    );

    /* --- MISCELLANEOUS SLABS --- */

    public static final DeferredBlock<Block> PRISMARINE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.PRISMARINE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PRISMARINE)
    );

    public static final DeferredBlock<Block> PRISMARINE_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.PRISMARINE_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.PRISMARINE_BRICKS)
    );

    public static final DeferredBlock<Block> DARK_PRISMARINE_VERTICAL_SLAB = registerBlock(MFBlockItemIds.DARK_PRISMARINE_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.DARK_PRISMARINE)
    );

    public static final DeferredBlock<Block> BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.BRICKS)
    );

    public static final DeferredBlock<Block> MUD_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.MUD_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.MUD_BRICKS)
    );

    public static final DeferredBlock<Block> RESIN_BRICK_VERTICAL_SLAB = registerBlock(MFBlockItemIds.RESIN_BRICK_VERTICAL_SLAB,
            VerticalSlabBlock::new,
            _ -> Properties.ofFullCopy(Blocks.RESIN_BRICKS)
    );

    /* --- BLOCK COLLECTION SLABS --- */

    public static final WeatheringCopperCollection<DeferredBlock<Block>> CUT_COPPER_VERTICAL_SLAB = // Because this is a WeatheringCopperCollection, all eight blocks (unaffected, exposed, weathered, and oxidised, and all of their waxed variants) are registered at the exact same time. No need to repeat myself for each one.
            VanillaCollectionUtil.registerDeferredCopper(
                    MFBlockItemIds.CUT_COPPER_VERTICAL_SLAB,
                    WeatheringCopperVerticalSlabBlock::new, // Regular
                    WeatheringCopperVerticalSlabBlock::new, // Waxed
                    state -> Properties.ofFullCopy(Blocks.CUT_COPPER.weathering().pick(state))
            );

    public static final ColorCollection<DeferredBlock<Block>> WOOL_VERTICAL_SLAB =
            VanillaCollectionUtil.registerDeferredColoured(
                    MFBlockItemIds.WOOL_VERTICAL_SLAB,
                    VerticalSlabBlock::new,
                    colour -> Properties.ofFullCopy(Blocks.WOOL.pick(colour))
            );

    public static final ColorCollection<DeferredBlock<Block>> CONCRETE_VERTICAL_SLAB =
            VanillaCollectionUtil.registerDeferredColoured(
                    MFBlockItemIds.CONCRETE_VERTICAL_SLAB,
                    VerticalSlabBlock::new,
                    colour -> Properties.ofFullCopy(Blocks.CONCRETE.pick(colour))
            );

    public static final VanillaWoodCollection<DeferredBlock<Block>> VANILLA_WOOD_VERTICAL_SLAB = VanillaWoodCollection.registerBlocks(
            MFBlockItemIds.VANILLA_WOOD_VERTICAL_SLAB,
            MFBlocks::registerBlock,
            (_, props) -> new VerticalSlabBlock(props),
            PropertyUtil.Blocks::vanillaWoodProps
    );


    // PILLARS

    public static final DeferredBlock<Block> TEST_PILLAR_BLOCK = registerBlock(MFBlockItemIds.TEST_COLUMN,
            PillarBlock::new,
            _ -> Properties.ofFullCopy(Blocks.OAK_PLANKS)
    );

    public static final ColorCollection<DeferredBlock<Block>> CONCRETE_PILLAR =
            VanillaCollectionUtil.registerDeferredColoured(
                    MFBlockItemIds.CONCRETE_PILLAR,
                    PillarBlock::new,
                    colour -> Properties.ofFullCopy(Blocks.CONCRETE.pick(colour))
            );

    public static final WeatheringCopperCollection<DeferredBlock<Block>> CUT_COPPER_PILLAR =
            VanillaCollectionUtil.registerDeferredCopper(
                    MFBlockItemIds.CUT_COPPER_PILLAR,
                    WeatheringCopperPillarBlock::new,
                    WeatheringCopperPillarBlock::new,
                    state -> Properties.ofFullCopy(Blocks.CUT_COPPER.weathering().pick(state))
            );

    public static final VanillaWoodCollection<DeferredBlock<Block>> VANILLA_WOOD_PILLAR = VanillaWoodCollection.registerBlocks(
            MFBlockItemIds.VANILLA_WOOD_PILLAR,
            MFBlocks::registerBlock,
            (_, props) -> new PillarBlock(props),
            PropertyUtil.Blocks::vanillaWoodProps
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

    // Portal Blocks
    public static final DeferredBlock<Block> EVIL_PORTAL = registerBlockWithTooltip(MFBlockItemIds.EVIL_PORTAL,
            properties -> new EvilPortalBlock(properties
                    .strength(2f)
                    .sound(MFSoundTypes.EVIL_PORTAL_SOUNDS)
                    .pushReaction(PushReaction.BLOCK)
                    .isViewBlocking(PropertyUtil.Blocks::always)
                    .isValidSpawn(PropertyUtil.Blocks::never)
                    .isSuffocating(PropertyUtil.Blocks::always)
                    .isRedstoneConductor(PropertyUtil.Blocks::never)
            ), Component.translatable("tooltip.morefeatures.evil_portal"));

    public static final DeferredBlock<Block> PORTAL = registerBlockWithoutItem(MFBlockIds.PORTAL_BLOCK,
            props -> new PortalBlock(props.noCollision().noOcclusion())
    );

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

    private static <T extends Block> DeferredBlock<T> registerSign(BlockItemId id, Function<BlockBehaviour.Properties, T> function) {
        return registerBlockWithoutItem(id.block(), function);
    }

    public static <T extends Block> void registerBlockItem(BlockItemId id, DeferredBlock<T> block) {
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
        Identifier blockId = id.block().identifier();

        // This register method is private, so ensuring all blocks in this class are registered in the More Features namespace only forces me to follow good practice without hindering other devs
        if (MFIdentifier.isNotMfNamespace(blockId)){
            throw new IllegalStateException("Could not register the " + blockId.getPath() + " block. ID must be within the More Features namespace!");
        }

        DeferredBlock<T> block = BLOCKS.registerBlock(blockId.getPath(),
                baseProps -> {
                    BlockBehaviour.Properties props = propertyModifier.apply(baseProps).setId(id.block());
                    return blockFactory.apply(props);
                }
        );

        registerBlockItem(id, block);
        return block;
    }

    // Register method called in the mod event bus

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Blocks registered -> Performed by: " + MoreFeatures.MODID);
    }
}