package com.mohigster.morefeatures.util;

import com.mohigster.morefeatures.block.collection.gemstone.GemstoneType;
import com.mohigster.morefeatures.block.collection.vanilla.VanillaWoodSet;
import com.mohigster.morefeatures.block.collection.wood.WoodSet;
import com.mohigster.morefeatures.data.component.MFDataComponentTypes;
import com.mohigster.morefeatures.item.custom.trident.MFTridentItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

import java.util.List;
import java.util.Optional;

public class PropertyUtil {
    public static class Blocks {
        // Used by the isRedstoneConductor, isViewBlocking, and isSuffocating properties

        public static boolean always(BlockState state, BlockGetter blockGetter, BlockPos blockPos) {
            return true;
        }

        public static boolean never(BlockState state, BlockGetter blockGetter, BlockPos blockPos) {
            return false;
        }

        // Used by the isValidSpawn property

        public static boolean never(BlockState state, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
            return false;
        }

        public static boolean always(BlockState state, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {
            return true;
        }

        public static BlockBehaviour.Properties gemSignProps(GemstoneType gem, BlockBehaviour.Properties props) {
            return baseGemOreProps(gem, props, false).sound(gem.woodType().soundType()).isRedstoneConductor(Blocks::never);
        }

        // Predetermined properties

        public static BlockBehaviour.Properties woodProps(WoodSet wood, BlockBehaviour.Properties props) {
            return baseWoodProps(wood, props, false);
        }

        public static BlockBehaviour.Properties logProps(WoodSet wood, BlockBehaviour.Properties props) {
            return baseWoodProps(wood, props, true);
        }

        public static BlockBehaviour.Properties woodShelfProps(WoodSet wood, BlockBehaviour.Properties props) {
            return baseWoodProps(wood, props, false).sound(SoundType.SHELF);
        }

        public static BlockBehaviour.Properties woodSignProps(WoodSet wood, BlockBehaviour.Properties props) {
            return baseWoodProps(wood, props, false).noCollision();
        }

        public static BlockBehaviour.Properties endGemOreProps(GemstoneType gem, BlockBehaviour.Properties props) {
            return baseGemOreProps(gem, props, true).mapColor(MapColor.SAND);
        }

        public static BlockBehaviour.Properties netherGemOreProps(GemstoneType gem, BlockBehaviour.Properties props) {
            return baseGemOreProps(gem, props, false).sound(SoundType.NETHER_ORE).mapColor(MapColor.NETHER);
        }

        public static BlockBehaviour.Properties deepslateGemOreProps(GemstoneType gem, BlockBehaviour.Properties props) {
            return baseGemOreProps(gem, props, false);
        }

        public static BlockBehaviour.Properties stoneGemOreProps(GemstoneType gem, BlockBehaviour.Properties props) {
            return baseGemOreProps(gem, props, true);
        }

        public static BlockBehaviour.Properties baseGemOreProps(GemstoneType gem, BlockBehaviour.Properties props, boolean stone) {
            return gemProps(gem, props).mapColor(stone ? MapColor.STONE : MapColor.DEEPSLATE).sound(stone ? SoundType.STONE : SoundType.DEEPSLATE);
        }

        public static BlockBehaviour.Properties gemProps(GemstoneType gem, BlockBehaviour.Properties props) {
            return props.mapColor(gem.mapColor()).sound(SoundType.AMETHYST).strength(10F, 400F)
                    .isRedstoneConductor(Blocks::always).isSuffocating(Blocks::always).isValidSpawn(Blocks::always)
                    .isViewBlocking(Blocks::always).instrument(NoteBlockInstrument.CHIME).requiresCorrectToolForDrops();
        }

        public static BlockBehaviour.Properties gemFenceProps(GemstoneType gem, BlockBehaviour.Properties props) {
            return gemProps(gem, props).isValidSpawn(Blocks::never).isSuffocating(Blocks::never);
        }

        public static BlockBehaviour.Properties baseWoodProps(WoodSet set, BlockBehaviour.Properties props, boolean log) {
            BlockBehaviour.Properties finalProps = props.mapColor(set.mapColor()).sound(log ? set.logSoundType() : set.mainSoundType())
                    .strength(2.0F, 8.0F).isValidSpawn(Blocks::never);

            if (set.isFlammable()) finalProps.ignitedByLava();

            return finalProps;
        }

        public static BlockBehaviour.Properties vanillaWoodProps(VanillaWoodSet set, BlockBehaviour.Properties props) {
            BlockBehaviour.Properties finalProps = props.mapColor(set.mapColor()).sound(set.soundType())
                    .strength(2.0F, 8.0F).isValidSpawn(Blocks::never);

            if (set.isFlammable()) finalProps.ignitedByLava();

            return finalProps;
        }
    }

    public static class Items {
        public static Item.Properties gemProps(GemstoneType gem, Item.Properties props) {
            return props.component(MFDataComponentTypes.COMPRESSOR_FUEL_VALUE.get(), 3200)
                    .trimMaterial(gem.trimMaterial());
        }

        public static Item.Properties shieldProps(
                int durability,
                float blockDelaySeconds,
                float disableCooldownScale,
                TagKey<Item> repairable,
                Item.Properties props
        ) {
            return props.fireResistant()
                    .enchantable(15)
                    .durability(durability)
                    .repairable(repairable)
                    .equippableUnswappable(EquipmentSlot.OFFHAND)
                    .delayedComponent(
                            DataComponents.BLOCKS_ATTACKS,
                            context -> new BlocksAttacks(
                                    blockDelaySeconds,
                                    disableCooldownScale,
                                    List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                    new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                    Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                                    Optional.of(SoundEvents.SHIELD_BLOCK),
                                    Optional.of(SoundEvents.SHIELD_BREAK)
                            )
                    )
                    .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK);
        }

        public static Item.Properties tridentProps(
                int durability,
                double damageAmount,
                double attackSpeedMod,
                int damagePerBlock,
                int damagePerAttack,
                TagKey<Item> repairable,
                Item.Properties props
        ) {
            return props.fireResistant()
                    .enchantable(15)
                    .durability(durability)
                    .repairable(repairable)
                    .attributes(MFTridentItem.createAttributes(damageAmount, attackSpeedMod))
                    .rarity(Rarity.RARE)
                    .component(DataComponents.TOOL, MFTridentItem.createToolProperties(damagePerBlock))
                    .component(DataComponents.WEAPON, new Weapon(damagePerAttack));

        }
    }
}
