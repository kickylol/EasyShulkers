package com.kicky.easyshulkers;

import com.kicky.easyshulkers.holder.ShulkerHolder;
import com.kicky.easyshulkers.holder.ShulkerInventory;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ShulkerAccess {

    Shulker makeShulker(ShulkerHolder holder);

    void openShulkerInventory(Player player, Shulker shulker);

    void closeShulkerInventory(String uid, ShulkerInventory shulkerInventory, Shulker shulker, ItemStack item);

    void dropShulkerInventory(String uid, ShulkerInventory shulkerInventory);

    Shulker makeShulker(Player player, ShulkerSize shulkerSize);

    Shulker getShulker(Player player);

    Shulker getShulker(UUID uid);

    Collection<Shulker> getShulkers();

    List<ItemStack> getShulkerItems(String uid);

    Map<String, ItemStack[]> getShulkerInventoryData();

    boolean doesPlayerHaveShulker(Player player);

    boolean doesPlayerHaveShulker(String uid);

    ItemStack makeShulkerItemStack(Shulker shulker);

    boolean getShulkerFromItemStack(ItemStack is);

    PersistentDataContainer shulkerContainer(ItemStack is);

    NamespacedKey shulkerKey();

}
