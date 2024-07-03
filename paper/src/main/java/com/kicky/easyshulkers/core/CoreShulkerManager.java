package com.kicky.easyshulkers.core;

import com.google.common.base.Preconditions;
import com.kicky.easyshulkers.*;
import com.kicky.easyshulkers.events.ShulkerCreationEvent;
import com.kicky.easyshulkers.holder.ShulkerHolder;
import com.kicky.easyshulkers.holder.ShulkerInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.*;

final class CoreShulkerManager implements ShulkerManager {

    private final ShulkerApi shulkerApi;
    private final ShulkerFactory shulkerFactory;

    private final Map<String, ItemStack[]> shulkerInventoryData = new HashMap<String, ItemStack[]>();


    public CoreShulkerManager(final ShulkerApi shulkerApi, final ShulkerFactory shulkerFactory) {
        Preconditions.checkNotNull(shulkerApi, "shulkerApi");
        Preconditions.checkNotNull(shulkerFactory, "shulkerFactory");

        this.shulkerApi = shulkerApi;
        this.shulkerFactory = shulkerFactory;
    }

    @Override
    public void loadShulkers() {

    }

    @Override
    public void openShulkerInventory(Player player, Shulker shulker) {

        ShulkerInventory shulkerInventory = new ShulkerInventory(shulkerApi, shulker.getShulkerSize());

        if (shulkerInventoryData.containsKey(player.getUniqueId().toString()) && (shulker.getUUID().equals(player.getUniqueId()))) {
            shulkerInventory.getInventory().setContents(shulkerInventoryData.get(player.getUniqueId().toString()));
            player.openInventory(shulkerInventory.getInventory());
            Bukkit.getLogger().info("inventory open with key");
            return;
        }
        player.openInventory(shulkerInventory.getInventory());
        Bukkit.getLogger().info("inventory open");
    }

    @Override
    public void closeShulkerInventory(String uid, ShulkerInventory shulkerInventory, Shulker shulker, ItemStack item) {

        ItemMeta meta = item.getItemMeta();

        if (shulkerInventory.getInventory().isEmpty()){
            shulkerInventoryData.remove(uid);
            shulker.removeUUID();
            meta.getPersistentDataContainer().set(shulkerApi.shulkerKey(), new ShulkerDataType(), shulker);
            item.setItemMeta(meta);
            return;
        }

        shulkerInventoryData.put(uid, shulkerInventory.getInventory().getContents());
    }

    @Override
    public void dropShulkerInventory(String uid, ShulkerInventory shulkerInventory) {

    }

    @Override
    public List<ItemStack> getShulkerItems(String uid) {
        ItemStack[] itemStacksArray = shulkerInventoryData.get(uid);
        if (itemStacksArray != null) {
            return Arrays.asList(itemStacksArray);
        } else {
            return Collections.emptyList(); // Return an empty list if the uid does not exist
        }
    }

    @Override
    public Map<String, ItemStack[]> getShulkerInventoryData() {
        return shulkerInventoryData;
    }

    @Override
    public Shulker makeShulker(ShulkerHolder holder) {
        return null;
    }


    @Override
    public Shulker makeShulker(Player player, ShulkerSize shulkerSize) {

        final Shulker shulker = shulkerFactory.createShulker(shulkerSize, createUniqueShulkerId());

        ShulkerCreationEvent e = new ShulkerCreationEvent(shulker, player);
        Bukkit.getPluginManager().callEvent(e);
        if (e.isCancelled()) {
            return null;
        }

        return shulker;
    }

    @Override
    public Shulker getShulker(Player player) {
        return null;
    }

    @Override
    public Shulker getShulker(UUID uid) {
        return null;
    }

    @Override
    public Collection<Shulker> getShulkers() {
        return List.of();
    }

    @Override
    public boolean doesPlayerHaveShulker(Player player) {
        return shulkerInventoryData.containsKey(player.getUniqueId().toString());
    }

    @Override
    public boolean doesPlayerHaveShulker(String uid) {
        return shulkerInventoryData.containsKey(uid);
    }

    @Override
    public ItemStack makeShulkerItemStack(Shulker shulker) {
        ItemStack easyShulker = new ItemStack(Material.SHULKER_BOX);
        ItemMeta meta = easyShulker.getItemMeta();
        ShulkerSize shulkerSize = shulker.getShulkerSize();

        if (shulkerSize == ShulkerSize.SMALL) {
            meta.setDisplayName(ChatColor.GOLD + "Small Shulker");
        }
        if (shulkerSize == ShulkerSize.MEDIUM) {
            meta.setDisplayName(ChatColor.GOLD + "Medium Shulker");
        }
        if (shulkerSize == ShulkerSize.LARGE) {
            meta.setDisplayName(ChatColor.GOLD + "Large Shulker");
        }

        NamespacedKey key = new NamespacedKey(shulkerApi, "easy_shulker");
        meta.getPersistentDataContainer().set(key, new ShulkerDataType(), shulker);

        easyShulker.setItemMeta(meta);
        return easyShulker;
    }

    @Override
    public boolean getShulkerFromItemStack(ItemStack is) {
        return shulkerContainer(is).has(shulkerKey(), new ShulkerDataType());
    }

    @Override
    public PersistentDataContainer shulkerContainer(ItemStack is) {
        return is.getItemMeta().getPersistentDataContainer();
    }

    @Override
    public NamespacedKey shulkerKey() {
        return new NamespacedKey(shulkerApi, "easy_shulker");
    }

    private int createUniqueShulkerId() {
        Random rand = new Random();
        int shulkerId = 0;

        while(shulkerId == 0) {
            shulkerId = rand.nextInt(Integer.MAX_VALUE >> 1);
        }

        return shulkerId;
    }

}
