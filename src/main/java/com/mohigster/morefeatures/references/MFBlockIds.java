package com.mohigster.morefeatures.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

public class MFBlockIds {

    // Azurite
    public static final ResourceKey<Block> AZURITE_SIGN = createId("azurite_sign");
    public static final ResourceKey<Block> AZURITE_WALL_SIGN = createId("azurite_wall_sign");
    public static final ResourceKey<Block> AZURITE_HANGING_SIGN = createId("azurite_hanging_sign");
    public static final ResourceKey<Block> AZURITE_WALL_HANGING_SIGN = createId("azurite_wall_hanging_sign");

    // Bloodwood
    public static final ResourceKey<Block> BLOODWOOD_SIGN = createId("bloodwood_sign");
    public static final ResourceKey<Block> BLOODWOOD_WALL_SIGN = createId("bloodwood_wall_sign");
    public static final ResourceKey<Block> BLOODWOOD_HANGING_SIGN = createId("bloodwood_hanging_sign");
    public static final ResourceKey<Block> BLOODWOOD_WALL_HANGING_SIGN = createId("bloodwood_wall_hanging_sign");
    public static final ResourceKey<Block> POTTED_BLOODWOOD_SAPLING = createId("potted_bloodwood_sapling");

    // Tainted
    public static final ResourceKey<Block> TAINTED_SIGN = createId("tainted_sign");
    public static final ResourceKey<Block> TAINTED_WALL_SIGN = createId("tainted_wall_sign");
    public static final ResourceKey<Block> TAINTED_HANGING_SIGN = createId("tainted_hanging_sign");
    public static final ResourceKey<Block> TAINTED_WALL_HANGING_SIGN = createId("tainted_wall_hanging_sign");
    public static final ResourceKey<Block> POTTED_TAINTED_SAPLING = createId("potted_tainted_sapling");

    // Palm
    public static final ResourceKey<Block> PALM_SIGN = createId("palm_sign");
    public static final ResourceKey<Block> PALM_WALL_SIGN = createId("palm_wall_sign");
    public static final ResourceKey<Block> PALM_HANGING_SIGN = createId("palm_hanging_sign");
    public static final ResourceKey<Block> PALM_WALL_HANGING_SIGN = createId("palm_wall_hanging_sign");
    public static final ResourceKey<Block> POTTED_PALM_SAPLING = createId("potted_palm_sapling");

    // Decrepit
    public static final ResourceKey<Block> DECREPIT_SIGN = createId("decrepit_sign");
    public static final ResourceKey<Block> DECREPIT_WALL_SIGN = createId("decrepit_wall_sign");
    public static final ResourceKey<Block> DECREPIT_HANGING_SIGN = createId("decrepit_hanging_sign");
    public static final ResourceKey<Block> DECREPIT_WALL_HANGING_SIGN = createId("decrepit_wall_hanging_sign");
    public static final ResourceKey<Block> POTTED_DECREPIT_ROOTS = createId("potted_decrepit_roots");
    public static final ResourceKey<Block> POTTED_DECREPIT_SAPLING = createId("potted_decrepit_sapling");

    // Pallid
    public static final ResourceKey<Block> PALLID_SIGN = createId("pallid_sign");
    public static final ResourceKey<Block> PALLID_WALL_SIGN = createId("pallid_wall_sign");
    public static final ResourceKey<Block> PALLID_HANGING_SIGN = createId("pallid_hanging_sign");
    public static final ResourceKey<Block> PALLID_WALL_HANGING_SIGN = createId("pallid_wall_hanging_sign");
    public static final ResourceKey<Block> POTTED_PALLID_ROOTS = createId("potted_pallid_roots");
    public static final ResourceKey<Block> POTTED_PALLID_SAPLING = createId("potted_pallid_sapling");

    // Roses
    public static final ResourceKey<Block> POTTED_ROSE = createId("potted_rose");
    public static final ResourceKey<Block> POTTED_BLUE_ROSE = createId("potted_blue_rose");

    // Conjured ice
    public static final ResourceKey<Block> CONJURED_ICE = createId("conjured_ice");

    private static ResourceKey<Block> createId(String name) {
        return ResourceKey.create(Registries.BLOCK, MFIdentifier.withMfNamespace(name));
    }
}
