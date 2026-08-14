package com.mohigster.morefeatures.events.data;

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

    public double getSpeed(ItemStack stack){
        for (ElytraSpeedEntry entry : this.entries){
            if (entry.elytra().contains(stack.typeHolder())){
                return entry.percentSpeedBoost() / 100; // To make it more readable, the speed boost is divided by 100 before being returned. This allows them to input a percentage boost (e.g 18 for 18%)
            }
        }

        return 0.0D; // default fallback
    }

    public double getMaxSpeed(ItemStack stack){
        for (ElytraSpeedEntry entry : this.entries){
            if (entry.elytra().contains(stack.typeHolder())){
                if (entry.maximumSpeed() == 0.0D) {
                    double maxSpeed = (this.getSpeed(stack) - MAX_SPEED_REDUCTION_BEFORE_SCALE) * MAX_SPEED_SCALING_FACTOR;

                    // Use the simple, less precise rounding method. This is just an elytra max speed, not cybersecurity software. Ultra-precision is unnecessary
                    double roundedMaxSpeed = this.round(maxSpeed);

                    return Math.max(roundedMaxSpeed, MIN_MAX_SPEED);
                }
                return this.round(entry.maximumSpeed());
            }
        }

        return MIN_MAX_SPEED; // Fallback
    }

    public boolean elytraInEntries(ItemStack stack){
        return this.getElytraEntries().contains(stack.getItem());
    }

    public Set<Item> getElytraEntries(){
        return this.elytraEntries;
    }

    private double round(double valueToRound){
        return (double) Math.round(valueToRound / FACTOR_TO_ROUND_BY) * FACTOR_TO_ROUND_BY;
    }
}
