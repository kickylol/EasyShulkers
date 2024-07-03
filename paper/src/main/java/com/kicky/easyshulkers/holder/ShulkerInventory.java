package com.kicky.easyshulkers.holder;

import com.kicky.easyshulkers.ShulkerApi;
import com.kicky.easyshulkers.ShulkerSize;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public class ShulkerInventory implements InventoryHolder {

    private final Inventory inventory;
    private final int size;

    public ShulkerInventory(ShulkerApi shulkerApi, ShulkerSize shulkerSize) {
        this.size = shulkerSize.toInt();
        this.inventory = shulkerApi.getServer().createInventory(this, this.size, "Shulker Box");
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
