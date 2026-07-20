package com.mohigster.morefeatures.mixin;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowItem.class)
public class BowItemMixin {
    @Redirect(
            method = "releaseUsing",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getProjectile(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;"
            )
    )
    private ItemStack nearrowarrow$getClosestArrow(Player player, ItemStack heldWeapon) {
        // Find which hotbar slot the bow is in
        int bowSlot = -1;
        Inventory inv = player.getInventory();
        for (int i = 0; i < Inventory.getSelectionSize(); i++) { // hotbar slots 0-8
            if (inv.getItem(i) == heldWeapon) {
                bowSlot = i;
                break;
            }
        }
        if (bowSlot == -1) {
            return player.getProjectile(heldWeapon);
        }

        // Predicate: valid ammo for this bow
        java.util.function.Predicate<ItemStack> ammoPredicate =
                ProjectileWeaponItem.ARROW_ONLY;

        // Search hotbar (slots 0-8) first, prioritising closest to bowSlot
        ItemStack found = modBlockItemTags_java$findClosestArrow(inv, bowSlot, 0, Inventory.getSelectionSize(), ammoPredicate);

        // Then search the rest of the main inventory (slots 9-35)
        if (found.isEmpty()) {
            found = modBlockItemTags_java$findClosestArrow(inv, bowSlot, Inventory.getSelectionSize(), inv.getContainerSize(), ammoPredicate);
        }

        // Offhand last
        if (found.isEmpty()) {
            ItemStack offhand = player.getOffhandItem();
            if (ammoPredicate.test(offhand)) {
                found = offhand;
            }
        }

        // Final fallback: vanilla logic (handles creative mode, Infinity, etc.)
        if (found.isEmpty()) {
            found = player.getProjectile(heldWeapon);
        }

        return found;
    }

    @Unique
    private ItemStack modBlockItemTags_java$findClosestArrow(
            Inventory inv, int bowSlot,
            int slotStart, int slotEnd,
            java.util.function.Predicate<ItemStack> predicate) {

        int bowCol = modBlockItemTags_java$getColumn(bowSlot);
        int bowRow = modBlockItemTags_java$getRow(bowSlot);
        int bestColDist = Integer.MAX_VALUE;
        int bestRowDist = Integer.MAX_VALUE;
        ItemStack best = ItemStack.EMPTY;

        for (int i = slotStart; i < slotEnd; i++) {
            if (i == bowSlot) continue;

            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && predicate.test(stack)) {
                int colDist = Math.abs(modBlockItemTags_java$getColumn(i) - bowCol);
                int rowDist = Math.abs(modBlockItemTags_java$getRow(i) - bowRow);

                if (colDist < bestColDist || (colDist == bestColDist && rowDist < bestRowDist)) {
                    bestColDist = colDist;
                    bestRowDist = rowDist;
                    best = stack;
                }
            }
        }
        return best;
    }

    @Unique
    private int modBlockItemTags_java$getColumn(int slot) {
        if (slot < 9) return slot;                    // hotbar: column = slot
        return (slot - 9) % 9;                        // main inv: column = position within row
    }

    @Unique
    private int modBlockItemTags_java$getRow(int slot) {
        if (slot < 9) return 4;                       // hotbar is the bottom row
        return (slot - 9) / 9;                        // rows 0-2 above hotbar (top to bottom)
    }
}
