package com.mohigster.morefeatures.block.custom.magicblock;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.tag.MFItemTags;
import com.mohigster.morefeatures.util.Directories;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MagicBlockTransmutations extends SimpleJsonResourceReloadListener<TransmutationEntry> {
    public static final MagicBlockTransmutations INSTANCE = new MagicBlockTransmutations();

    private List<TransmutationEntry> rawEntries = List.of();

    private List<TransmutationEntry> entries = List.of();

    protected MagicBlockTransmutations() {
        super(TransmutationEntry.CODEC, FileToIdConverter.json(Directories.MAGIC_BLOCK_PATH));
    }

    @NullMarked
    @Override
    protected void apply(Map<Identifier, TransmutationEntry> map, ResourceManager resourceManager, ProfilerFiller profiler) {
        this.rawEntries = List.copyOf(map.values());
    }

    /**
     * TagsUpdatedEvent is used to delay the check of whether or not a block is in the results tag to after the tag is populated.
     * Despite this, the event parameter is actually never used in the method. Still, it is the correct event to use.
     */
    @SuppressWarnings({"deprecation", "unused"})
    public void ignoreResultsNotInTag(TagsUpdatedEvent event) {
        List<TransmutationEntry> validEntries = new ArrayList<>();

        for (TransmutationEntry entry : this.rawEntries) {
            if (!entry.output().builtInRegistryHolder().is(MFItemTags.MAGIC_BLOCK_TRANSMUTATION_RESULTS)) {
                MoreFeatures.LOGGER.warn(
                        "Skipping magic block transmutation: output item '{}' is not in the '{}' tag. Add it to that tag if this transmutation should be allowed.",
                        entry.output(),
                        MFItemTags.MAGIC_BLOCK_TRANSMUTATION_RESULTS.location()
                );
                continue;
            }

            validEntries.add(entry);
        }

        this.entries = List.copyOf(validEntries);
    }

    public ItemStack getResult(ItemStack input) {
        for (TransmutationEntry entry : this.entries) {
            if (input.is(entry.inputValue())) {
                ItemStack result = new ItemStack(entry.output(), input.getCount());
                if (entry.copyComponents()) {
                    result.applyComponents(input.getComponentsPatch()); // Use getComponentsPatch instead of getComponents so that the model can still change (models are a component as of 1.21.2 so getComponents will copy the model)
                }
                return result;
            }
        }
        return ItemStack.EMPTY;
    }

    // This method is separate so that the extra amounts are applied to individual input items separately from the input value itself
    public ItemStack applyExtraAmounts(ItemStack input) {
        for (TransmutationEntry entry : this.entries){
            if (input.is(entry.inputValue())) input.setCount(entry.extraItems() + 1);
        }

        return input;
    }
}
