package com.kicky.easyshulkers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.Serializable;
import java.util.UUID;

public interface Shulker extends Serializable {

    UUID getUUID();

    void setUUID(UUID uuid);

    void removeUUID();

    Player getPlayer();

    boolean isEasyShulker();

    void markAsEasyShulker(ItemMeta meta);

    int getShulkerId();

    ShulkerSize getShulkerSize();

    void setShulkerContents();

    Location getLocation();

    ItemStack createItemStack();

    void addShulkerContents(ItemStack[] is);

}
