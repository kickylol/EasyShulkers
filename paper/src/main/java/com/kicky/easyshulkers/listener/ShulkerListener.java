package com.kicky.easyshulkers.listener;

import com.kicky.easyshulkers.Shulker;
import com.kicky.easyshulkers.ShulkerApi;
import com.kicky.easyshulkers.core.ShulkerDataType;
import com.kicky.easyshulkers.events.ShulkerCreationEvent;
import com.kicky.easyshulkers.events.ShulkerInteractEvent;
import com.kicky.easyshulkers.holder.ShulkerInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.UUID;

public class ShulkerListener implements Listener {

    private final ShulkerApi shulkerApi;

    public ShulkerListener(ShulkerApi shulkerApi) {
        this.shulkerApi = shulkerApi;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onShulkerCreation(ShulkerCreationEvent e) {
        Player player = e.getPlayer();

        player.getInventory().addItem(shulkerApi.makeShulkerItemStack(e.getShulker()));
    }

    @EventHandler
    public void onShulkerInteract(ShulkerInteractEvent e) {
        shulkerApi.openShulkerInventory(e.getPlayer(), e.getShulker());
    }

    @EventHandler
    public void onShulkerClose(InventoryCloseEvent e) {
        Inventory inventory = e.getInventory();

        if (!(inventory.getHolder(false) instanceof ShulkerInventory shulkerInventory)) {
            return;
        }

        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        UUID uid = e.getPlayer().getUniqueId();

        // gets shulker datatype in the players hand
        Shulker shulker = shulkerApi.shulkerContainer(e.getPlayer().getInventory().getItemInMainHand()).get(shulkerApi.shulkerKey(), new ShulkerDataType());
        if (!inventory.isEmpty()) {
            // Sets player ownership over shulker and updates the data container if there is something inside of it
            shulker.setUUID(uid);
            meta.getPersistentDataContainer().set(shulkerApi.shulkerKey(), new ShulkerDataType(), shulker);
            item.setItemMeta(meta);
        }

        shulkerApi.closeShulkerInventory(e.getPlayer().getUniqueId().toString(), shulkerInventory, shulker, item);
    }

}
