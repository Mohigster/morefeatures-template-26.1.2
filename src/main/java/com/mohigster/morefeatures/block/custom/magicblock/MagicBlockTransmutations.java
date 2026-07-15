package com.mohigster.morefeatures.block.custom.magicblock;

import com.mojang.serialization.Codec;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Map;

public class MagicBlockTransmutations extends SimpleJsonResourceReloadListener<TransmutationEntry> {
    public static final MagicBlockTransmutations INSTANCE =
            new MagicBlockTransmutations();

    private List<TransmutationEntry> entries = List.of();

    protected MagicBlockTransmutations() {
        super(TransmutationEntry.CODEC, FileToIdConverter.json("magic_block_transmutations"));
    }

    @NullMarked
    @Override
    protected void apply(Map<Identifier, TransmutationEntry> map, ResourceManager resourceManager, ProfilerFiller profiler) {
        this.entries = List.copyOf(map.values());
    }

    public ItemStack getResult(ItemStack input) {
        for (TransmutationEntry entry : entries) {
            if (input.is(entry.inputTag())) {
                ItemStack result = new ItemStack(entry.output(), input.getCount());
                if (entry.copyComponents()) {
                    result.applyComponents(input.getComponents()); // preserve components
                }
                return result;
            }
        }
        return ItemStack.EMPTY;
    }
}
