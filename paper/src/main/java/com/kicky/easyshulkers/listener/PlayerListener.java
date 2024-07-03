package com.kicky.easyshulkers.listener;

import com.kicky.easyshulkers.Shulker;
import com.kicky.easyshulkers.ShulkerApi;
import com.kicky.easyshulkers.core.ShulkerDataType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

    private final ShulkerApi shulkerApi;

    public PlayerListener(ShulkerApi shulkerApi) {
        this.shulkerApi = shulkerApi;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!e.hasItem())
            return;
        if (e.getItem().getType() != Material.SHULKER_BOX)
            return;
        if (!e.getItem().hasItemMeta())
            return;

        if(shulkerApi.getShulkerFromItemStack(e.getItem())) {
            if (e.getAction().isRightClick()) {
                Shulker info = shulkerApi.shulkerContainer(e.getItem()).get(shulkerApi.shulkerKey(), new ShulkerDataType());

                shulkerApi.openShulkerInventory(e.getPlayer(), info);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDrop(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().getType() != Material.SHULKER_BOX)
            return;
        if (!e.getItemDrop().getItemStack().hasItemMeta())
            return;

        if(shulkerApi.getShulkerFromItemStack(e.getItemDrop().getItemStack())) {
            Shulker info = shulkerApi.shulkerContainer(e.getItemDrop().getItemStack()).get(shulkerApi.shulkerKey(), new ShulkerDataType());
            if (info.getUUID() != null) {
                e.getPlayer().sendMessage("You cannot drop a shulker with items!");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Bukkit.getLogger().info("player ded");
        List<ItemStack> drops = e.getDrops();
        String uid = e.getPlayer().getUniqueId().toString();

        for (ItemStack item : drops) {
            if (shulkerApi.getShulkerFromItemStack(item)) {
                Bukkit.getLogger().info("shulker detected");
                if (shulkerApi.doesPlayerHaveShulker(uid)) {
                    Bukkit.getLogger().info("player had shulker");
                    Shulker info = shulkerApi.shulkerContainer(item).get(shulkerApi.shulkerKey(), new ShulkerDataType());
                    ItemMeta meta = item.getItemMeta();
                    List<ItemStack> shulkerItems = shulkerApi.getShulkerItems(uid);
                    info.removeUUID();
                    meta.getPersistentDataContainer().set(shulkerApi.shulkerKey(), new ShulkerDataType(), info);
                    item.setItemMeta(meta);
                    shulkerApi.getShulkerInventoryData().remove(uid);
                    drops.addAll(shulkerItems);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockDispensed(BlockDispenseEvent e) {
        if (e.getItem().getType() != Material.SHULKER_BOX)
            return;
        if (!e.getItem().hasItemMeta())
            return;

        if(shulkerApi.getShulkerFromItemStack(e.getItem())) {
            e.setCancelled(true);
        }
    }
}
