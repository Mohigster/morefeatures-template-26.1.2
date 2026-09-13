package com.mohigster.morefeatures.data.resources.references;

import com.mohigster.morefeatures.block.collection.gemstone.GemstoneCollection;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class MFBlockIds {
    // Potted Nether / End roots
    public static final ResourceKey<Block> POTTED_CHARRED_ROOTS = createId("potted_charred_roots");
    public static final ResourceKey<Block> POTTED_DECREPIT_ROOTS = createId("potted_decrepit_roots");
    public static final ResourceKey<Block> POTTED_PALLID_ROOTS = createId("potted_pallid_roots");

    // Roses
    public static final ResourceKey<Block> POTTED_ROSE = createId("potted_rose");
    public static final ResourceKey<Block> POTTED_BLUE_ROSE = createId("potted_blue_rose");

    // Nether vinesState
    public static final ResourceKey<Block> SCORCHED_VINES_PLANT = createId("scorched_vines_plant");
    public static final ResourceKey<Block> SMOLDERED_VINES_PLANT = createId("smoldered_vines_plant");

    // Conjured ice
    public static final ResourceKey<Block> CONJURED_ICE = createId("conjured_ice");

    public static final ResourceKey<Block> PORTAL_BLOCK = createId("portal");

    public static final ResourceKey<Block> BLUE_BERRY_BUSH = createId("blue_berry_bush");

    public static final WoodTypeCollection<ResourceKey<Block>> WOODEN_SIGN = createSimpleWoodId("sign");
    public static final WoodTypeCollection<ResourceKey<Block>> WOODEN_WALL_SIGN = createSimpleWoodId("wall_sign");
    public static final WoodTypeCollection<ResourceKey<Block>> WOODEN_HANGING_SIGN = createSimpleWoodId("hanging_sign");
    public static final WoodTypeCollection<ResourceKey<Block>> WOODEN_WALL_HANGING_SIGN = createSimpleWoodId("wall_hanging_sign");

    public static final WoodTypeCollection<ResourceKey<Block>> POTTED_SAPLING = createPottedSaplingId();

    public static final GemstoneCollection<ResourceKey<Block>> GEMSTONE_SIGN = createSimpleGemId("sign");
    public static final GemstoneCollection<ResourceKey<Block>> GEMSTONE_WALL_SIGN = createSimpleGemId("wall_sign");
    public static final GemstoneCollection<ResourceKey<Block>> GEMSTONE_HANGING_SIGN = createSimpleGemId("hanging_sign");
    public static final GemstoneCollection<ResourceKey<Block>> GEMSTONE_WALL_HANGING_SIGN = createSimpleGemId("wall_hanging_sign");


    private static ResourceKey<Block> createId(String name) {
        return ResourceKey.create(Registries.BLOCK, MFIdentifier.withMfNamespace(name));
    }

    private static WoodTypeCollection<ResourceKey<Block>> createSimpleWoodId(String name) {
        return WoodTypeCollection.prefixWithSet(WoodTypeCollection.createForAll(name)).map(MFBlockIds::createId);
    }

    private static WoodTypeCollection<ResourceKey<Block>> createPottedSaplingId() {
        return WoodTypeCollection.prefixWithSet("potted", WoodTypeCollection.createForAll("sapling")).map(MFBlockIds::createId);
    }

    private static GemstoneCollection<ResourceKey<Block>> createSimpleGemId(String name) {
        return GemstoneCollection.prefixWithGem(GemstoneCollection.createForAll(name)).map(MFBlockIds::createId);
    }
}
