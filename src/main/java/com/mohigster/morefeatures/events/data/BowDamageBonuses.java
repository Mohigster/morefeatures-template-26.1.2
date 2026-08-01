package com.mohigster.morefeatures.events.data;

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

public class BowDamageBonuses extends SimpleJsonResourceReloadListener<BowDamageEntry> {
    public static final BowDamageBonuses INSTANCE = new BowDamageBonuses();

    private List<BowDamageEntry> entries = List.of();
    private Set<Item> bowEntries = Collections.emptySet();
    
    protected BowDamageBonuses() {
        super(BowDamageEntry.CODEC, FileToIdConverter.json("bow_damage_bonuses"));
    }

    @NullMarked
    @Override
    protected void apply(Map<Identifier, BowDamageEntry> map, ResourceManager resourceManager, ProfilerFiller profiler) {
        this.entries = map.values().stream()
                .sorted(Comparator.comparingDouble(BowDamageEntry::damageBonus))
                .toList();

        this.bowEntries = this.entries.stream()
                .flatMap(entry -> entry.bows().stream())
                .map(Holder::value)
                .collect(Collectors.toSet());
    }
    
    public double getDamage(ItemStack stack){
        for (BowDamageEntry entry : entries){
            if (stack.is(entry.bows())){
                return entry.damageBonus();
            }
        }
        
        return 0.0D; // Default fallback
    }

    public Set<Item> getBowEntries() {
        return this.bowEntries;
    }
}
