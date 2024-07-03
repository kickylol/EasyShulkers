package com.kicky.easyshulkers;

import com.kicky.easyshulkers.holder.ShulkerInventory;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface ShulkerManager extends ShulkerAccess {

    void loadShulkers();

    ItemStack makeShulkerItemStack(Shulker shulker);

}
