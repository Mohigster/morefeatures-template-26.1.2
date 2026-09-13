package com.mohigster.morefeatures.events.data;

import com.mohigster.morefeatures.data.component.MFDataComponentTypes;
import com.mohigster.morefeatures.util.Directories;
import net.minecraft.core.Holder;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NullMarked;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("DataFlowIssue")
public class ElytraSpeedBoosts extends SimpleJsonResourceReloadListener<ElytraSpeedEntry> {
    private static final double MAX_SPEED_SCALING_FACTOR = 130.0D;
    private static final double MAX_SPEED_REDUCTION_BEFORE_SCALE = 0.1D;
    private static final double MIN_MAX_SPEED = 2.0D;
    private static final int FACTOR_TO_ROUND_BY = 100;

    public static final ElytraSpeedBoosts INSTANCE = new ElytraSpeedBoosts();

    private List<ElytraSpeedEntry> entries = List.of();

    private Set<Item> elytraEntries = Collections.emptySet();

    private ElytraSpeedBoosts() {
        super(ElytraSpeedEntry.CODEC, FileToIdConverter.json(Directories.ELYTRA_PATH));
    }

    @NullMarked
    @Override
    protected void apply(Map<Identifier, ElytraSpeedEntry> map, ResourceManager resourceManager, ProfilerFiller profiler) {
        this.entries = map.values().stream()
                .sorted(Comparator.comparingDouble(ElytraSpeedEntry::percentSpeedBoost))
                .toList();

        this.elytraEntries = this.entries.stream()
                .flatMap(entry -> entry.elytra().stream())
                .map(Holder::value)
                .collect(Collectors.toSet());
    }

    public static double getSpeed(ItemStack stack){
        for (ElytraSpeedEntry entry : INSTANCE.entries){
            if (entry.elytra().contains(stack.typeHolder())){
                return entry.percentSpeedBoost() / 100; // To make it more readable, the speed boost is divided by 100 before being returned. This allows them to input a percentage boost (e.g. 18 for 18%)
            }
        }

        if (stack.has(MFDataComponentTypes.ELYTRA_SPEED_BOOST)){
            return stack.get(MFDataComponentTypes.ELYTRA_SPEED_BOOST) / 100;
        }

        return 0.0D; // default fallback
    }

    public static double getMaxSpeed(ItemStack stack){
        for (ElytraSpeedEntry entry : INSTANCE.entries){
            if (entry.elytra().contains(stack.typeHolder())){
                if (entry.maximumSpeed() == 0.0D) {
                    return roundMaxSpeed(calculateMaxSpeed(stack));
                }
                return roundMaxSpeed(entry.maximumSpeed());
            }
        }

        if (stack.has(MFDataComponentTypes.ELYTRA_SPEED_BOOST)){
            return roundMaxSpeed(calculateMaxSpeed(stack));
        }

        return MIN_MAX_SPEED; // Fallback
    }

    public static boolean elytraInEntries(ItemStack stack){
        if (stack.has(MFDataComponentTypes.ELYTRA_SPEED_BOOST)) {
            if (!inRawEntries(stack)) INSTANCE.elytraEntries.add(stack.getItem());
        }

        return inRawEntries(stack);
    }

    public static Set<Item> getElytraEntries(){
        return INSTANCE.elytraEntries;
    }

    private static double round(double valueToRound){
        return (double) Math.round(valueToRound / FACTOR_TO_ROUND_BY) * FACTOR_TO_ROUND_BY;
    }

    private static double calculateMaxSpeed(ItemStack stack){
        return (getSpeed(stack) - MAX_SPEED_REDUCTION_BEFORE_SCALE) * MAX_SPEED_SCALING_FACTOR;
    }

    private static double roundMaxSpeed(double maxSpeed){
        return Math.max(round(maxSpeed), MIN_MAX_SPEED);
    }

    private static boolean inRawEntries(ItemStack stack){
        return getElytraEntries().contains(stack.getItem());
    }
}
