package com.kicky.easyshulkers.core;

import com.kicky.easyshulkers.Shulker;
import com.kicky.easyshulkers.ShulkerSize;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 *  The shulker data type.
 */

final class CoreShulker implements Shulker {

    @Serial
    private static final long serialVersionUID = 1L;
    private UUID uid;
    private final ShulkerSize shulkerSize;
    private final int shulkerId;

    public CoreShulker(
            UUID uid,
            final ShulkerSize shulkerSize,
            int shulkerId)
    {
        this.uid = uid;
        this.shulkerSize = shulkerSize;
        this.shulkerId = shulkerId;
    }


    @Override
    public UUID getUUID() {
        return uid;
    }

    @Override
    public void setUUID(UUID uuid) {
        Bukkit.getLogger().info("uuid saved");
        this.uid = uuid;
    }

    @Override
    public void removeUUID() {
        Bukkit.getLogger().info("uuid removed");
        this.uid = null;
    }

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public boolean isEasyShulker() {
        return false;
    }


    @Override
    public void markAsEasyShulker(
        final @NotNull ItemMeta meta) {

    }

    @Override
    public int getShulkerId() {
        return shulkerId;
    }

    @Override
    public ShulkerSize getShulkerSize() {
        return shulkerSize;
    }

    @Override
    public void setShulkerContents() {

    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public ItemStack createItemStack() {
        return null;
    }

    @Override
    public void addShulkerContents(ItemStack[] is) {

    }
}
