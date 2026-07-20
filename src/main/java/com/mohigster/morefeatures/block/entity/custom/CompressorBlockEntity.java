package com.mohigster.morefeatures.block.entity.custom;

import com.mohigster.morefeatures.block.custom.CompressorBlock;
import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import com.mohigster.morefeatures.datacomponent.MFDataComponentTypes;
import com.mohigster.morefeatures.menu.custom.CompressorMenu;
import com.mohigster.morefeatures.recipe.MFRecipes;
import com.mohigster.morefeatures.recipe.custom.CompressionRecipe;
import com.mohigster.morefeatures.recipe.custom.CompressorRecipeInput;
import com.mohigster.morefeatures.tag.MFItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.RangedResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.item.ItemStacksResourceHandler;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public class CompressorBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStacksResourceHandler inventory = new ItemStacksResourceHandler(4) {
        @Override
        protected void onContentsChanged(int index, @NonNull ItemStack previousContents) {
            super.onContentsChanged(index, previousContents);
            CompressorBlockEntity.this.setChanged();
        }
    };
    private final ResourceHandler<ItemResource> leftHandler = RangedResourceHandler.of(inventory, FLUID_ITEM_SLOT, FLUID_ITEM_SLOT + 1);
    private final ResourceHandler<ItemResource> topHandler = RangedResourceHandler.of(inventory, INPUT_SLOT, INPUT_SLOT + 1);
    private final ResourceHandler<ItemResource> bottomHandler = RangedResourceHandler.of(inventory, OUTPUT_SLOT, OUTPUT_SLOT + 1);
    private final ResourceHandler<ItemResource> rightHandler = RangedResourceHandler.of(inventory, ENERGY_ITEM_SLOT, ENERGY_ITEM_SLOT + 1);

    private final ResourceHandler<ItemResource> frontBackHandler = RangedResourceHandler.of(inventory, INPUT_SLOT, OUTPUT_SLOT + 1);

    private static final int FLUID_ITEM_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    private static final int ENERGY_ITEM_SLOT = 3;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 300;

    private static final int ENERGY_CRAFT_AMOUNT = 25;      // per tick
    private static final int FLUID_CRAFT_AMOUNT = 1000;     // per craft

    private final SimpleEnergyHandler ENERGY_STORAGE = new SimpleEnergyHandler(64000, 12800) {
        @Override
        protected void onEnergyChanged(int previousAmount) {
            super.onEnergyChanged(previousAmount);
            assert getLevel() != null;
            getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
        }
    };

    private final FluidStacksResourceHandler FLUID_TANK = new FluidStacksResourceHandler(1, 16000) {
        @Override
        protected void onContentsChanged(int index, @NonNull FluidStack previousContents) {
            setChanged();
            assert getLevel() != null;
            if(!getLevel().isClientSide()) {
                getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Contract(pure = true)
        @Override
        public boolean isValid(int index, @NonNull FluidResource resource) {
            return true;
        }
    };

    public CompressorBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MFBlockEntities.COMPRESSOR_BE.get(), worldPosition, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int dataId) {
                return switch (dataId) {
                    case 0 -> CompressorBlockEntity.this.progress;
                    case 1 -> CompressorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int dataId, int value) {
                switch (dataId) {
                    case 0: CompressorBlockEntity.this.progress = value;
                    case 1: CompressorBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }
    @Override
    public @NonNull Component getDisplayName() {
        return Component.translatable("block.morefeatures.compressor");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, @NonNull Inventory inventory, @NonNull Player player) {
        return new CompressorMenu(containerId, inventory, this, this.inventory, this.data);
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        super.saveAdditional(output);
        output.putInt("compressor.progress", progress);
        output.putInt("compressor.max_progress", maxProgress);

        output.putChild("inventory", inventory);

        ENERGY_STORAGE.serialize(output);
        FLUID_TANK.serialize(output);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);
        progress = input.getIntOr("compressor.progress", 0);
        maxProgress = input.getIntOr("compressor.max_progress", 72);

        input.child("inventory").ifPresent(inventory::deserialize);

        ENERGY_STORAGE.deserialize(input);
        FLUID_TANK.deserialize(input);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.size());
        for (int i = 0; i < inventory.size(); i++) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, 0);
            inv.setItem(i, new ItemStack(itemAccess.getResource().getItem(), itemAccess.getAmount()));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public ResourceHandler<ItemResource> getItemHandler(Direction direction) {
        if (direction == null)
            return inventory;

        Direction facing = this.getBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING)
                ? this.getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING)
                : Direction.NORTH;

        return switch (getRelativeSide(facing, direction)) {
            case UP -> topHandler;
            case DOWN -> bottomHandler;
            case WEST -> leftHandler;
            case EAST -> rightHandler;
            case NORTH, SOUTH -> frontBackHandler;
        };
    }

    private Direction getRelativeSide(Direction facing, Direction absoluteSide) {
        if (absoluteSide.getAxis().isVertical()) return absoluteSide;

        if (absoluteSide == facing) return Direction.NORTH;
        if (absoluteSide == facing.getOpposite()) return Direction.SOUTH;
        if (absoluteSide == facing.getClockWise()) return Direction.WEST;
        if (absoluteSide == facing.getCounterClockWise()) return Direction.EAST;

        return absoluteSide;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if(hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            increaseCraftingProgress();
            useEnergyForCrafting();
            setChanged(level, pos, state);
            level.setBlockAndUpdate(pos, state.setValue(CompressorBlock.LIT, true));

            if(hasCraftingFinished()) {
                craftItem();
                extractFluidForCrafting();
                resetProgress();
            }
        } else {
            resetProgress();
            level.setBlockAndUpdate(pos, state.setValue(CompressorBlock.LIT, false));
        }
        if (hasFluidItemStackInSlot()) {
            transferFluidFromItemToTank();
        }

        if(hasItemInEnergySlot()) {
            fillUpOnEnergy();
        }
    }



    private boolean hasRecipe() {
        Optional<RecipeHolder<CompressionRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().assemble(new CompressorRecipeInput(inventory.getResource(INPUT_SLOT).toStack()));

        boolean outputSlotAmount = canInsertAmountIntoOutputSlot(output.getCount());
        boolean outputSlotItem = canInsertItemIntoOutputSlot(output);
        boolean hasEnoughEnergy = hasEnoughEnergyToCraft();
        boolean hasEnoughFluid = hasEnoughFluidToCraft();

        return outputSlotItem && outputSlotAmount && hasEnoughEnergy && hasEnoughFluid;
    }

    private Optional<RecipeHolder<CompressionRecipe>> getCurrentRecipe() {
        assert level != null;
        return ((ServerLevel) level).recipeAccess()
                .getRecipeFor(MFRecipes.COMPRESSOR_TYPE.get(),
                        new CompressorRecipeInput(inventory.getResource(INPUT_SLOT).toStack()), level);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return inventory.getResource(OUTPUT_SLOT).isEmpty() ||
                inventory.getResource(OUTPUT_SLOT).is(output.getItem());
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = inventory.getResource(OUTPUT_SLOT).isEmpty() ? 64 : inventory.getResource(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = inventory.getAmountAsInt(OUTPUT_SLOT);

        return maxCount >= currentCount + count;
    }

    private void craftItem() {
        Optional<RecipeHolder<CompressionRecipe>> recipe = getCurrentRecipe();
        @SuppressWarnings("OptionalGetWithoutIsPresent") ItemStack output = recipe.get().value().output().create();

        try(Transaction transaction = Transaction.openRoot()) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, OUTPUT_SLOT);

            inventory.extract(inventory.getResource(INPUT_SLOT), 1, transaction);
            inventory.set(OUTPUT_SLOT, ItemResource.of(output), itemAccess.getAmount() + output.getCount());

            transaction.commit();
        }

        if(hasItemInEnergySlot()) {
            fillUpOnEnergy();
        }
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return inventory.getResource(OUTPUT_SLOT).isEmpty() ||
                inventory.getResource(OUTPUT_SLOT).test(stack -> stack.count() < stack.getMaxStackSize());
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    /* ENERGY */

    @SuppressWarnings("unused")
    public EnergyHandler getEnergyStorage(@Nullable Direction direction) {
        return this.ENERGY_STORAGE;
    }

    private boolean hasEnoughEnergyToCraft() {
        // Starting a new craft
        if (progress == 0) {
            return this.ENERGY_STORAGE.getAmountAsInt() >= ENERGY_CRAFT_AMOUNT * maxProgress;
        }

        // Continuing an existing craft
        return this.ENERGY_STORAGE.getAmountAsInt() >= ENERGY_CRAFT_AMOUNT;
    }

    private void useEnergyForCrafting() {
        try(Transaction transaction = Transaction.openRoot()) {
            this.ENERGY_STORAGE.extract(ENERGY_CRAFT_AMOUNT, transaction);
            transaction.commit();
        }
    }

    private void fillUpOnEnergy() {
        ItemStack stack = inventory.getResource(ENERGY_ITEM_SLOT).toStack();

        @SuppressWarnings("DataFlowIssue")
        int energyToInsert = stack.get(MFDataComponentTypes.COMPRESSOR_FUEL_VALUE.get());

        if (energyToInsert <= 0) return;

        int currentEnergy = this.ENERGY_STORAGE.getAmountAsInt();
        int maxEnergy = this.ENERGY_STORAGE.getCapacityAsInt();


        if (maxEnergy - currentEnergy >= energyToInsert) {
            try (Transaction transaction = Transaction.openRoot()) {
                // 1. Consume exactly 1 Azurite item from the energy slot
                long extracted = inventory.extract(inventory.getResource(ENERGY_ITEM_SLOT), 1, transaction);

                // 2. If an item was successfully extracted, insert the energy
                if (extracted == 1) {
                    this.ENERGY_STORAGE.insert(energyToInsert, transaction);
                    transaction.commit(); // Apply changes permanently
                }
            }
        }
    }

    private boolean hasItemInEnergySlot() {
        return (inventory.getResource(ENERGY_ITEM_SLOT).is(MFItemTags.COMPRESSOR_FUEL)
                && inventory.getAmountAsInt(ENERGY_ITEM_SLOT) > 0);
    }

    /* FLUID */
    @SuppressWarnings("unused")
    public FluidStacksResourceHandler getFluidTank(@Nullable Direction direction) {
        return this.FLUID_TANK;
    }

    public FluidStack getFluid() {
        return new FluidStack(FLUID_TANK.getResource(0).getFluid(), FLUID_TANK.getAmountAsInt(0));
    }

    @SuppressWarnings("unused")
    private void transferFluidFromItemToTank() {
        try(Transaction transaction = Transaction.openRoot()) {
            ItemAccess itemAccess = ItemAccess.forHandlerIndex(inventory, FLUID_ITEM_SLOT);
            var itemCapability = itemAccess.getCapability(Capabilities.Fluid.ITEM);

            int fluidMoved = ResourceHandlerUtil.move(itemCapability, FLUID_TANK, fluidResource -> true,
                    FluidType.BUCKET_VOLUME, transaction);

            if(fluidMoved == FluidType.BUCKET_VOLUME) {
                transaction.commit();
            }
        }
    }

    private boolean hasFluidItemStackInSlot() {
        return !inventory.getResource(FLUID_ITEM_SLOT).isEmpty()
                && ItemAccess.forHandlerIndex(inventory, FLUID_ITEM_SLOT).getCapability(Capabilities.Fluid.ITEM) != null
                && Objects.requireNonNull(ItemAccess.forHandlerIndex(inventory, FLUID_ITEM_SLOT).getCapability(Capabilities.Fluid.ITEM)).getAmountAsInt(0) != 0;
    }

    private void extractFluidForCrafting() {
        try(Transaction transaction = Transaction.openRoot()) {
            FLUID_TANK.extract(FLUID_TANK.getResource(0), FLUID_CRAFT_AMOUNT, transaction);
            transaction.commit();
        }
    }

    private boolean hasEnoughFluidToCraft() {
        return FLUID_TANK.getAmountAsInt(0) >= FLUID_CRAFT_AMOUNT;
    }


    /* BLOCK ENTITY SYNC STUFF */

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
