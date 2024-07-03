package com.kicky.easyshulkers.core;

import com.google.common.base.Preconditions;
import com.kicky.easyshulkers.Shulker;
import com.kicky.easyshulkers.ShulkerApi;
import com.kicky.easyshulkers.ShulkerManager;
import com.kicky.easyshulkers.ShulkerSize;
import com.kicky.easyshulkers.commands.CmdManager;
import com.kicky.easyshulkers.holder.ShulkerHolder;
import com.kicky.easyshulkers.holder.ShulkerInventory;
import com.kicky.easyshulkers.listener.PlayerListener;
import com.kicky.easyshulkers.listener.ShulkerListener;
import io.papermc.paper.plugin.configuration.PluginMeta;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

final class EasyShulkerCore implements ShulkerApi {

    private final Plugin plugin;
    private final CorePluginFactory backpackFactory;
    private final ShulkerManager shulkerManager;
    private CmdManager cmdManager;
    private Logger log;

    private final PlayerListener playerListener;
    private final ShulkerListener shulkerListener;

    public EasyShulkerCore(final Plugin plugin) {
        Preconditions.checkNotNull(plugin, "plugin");

        this.plugin = plugin;
        backpackFactory = new CorePluginFactory(this);
        shulkerManager = backpackFactory.createBackpackManager();

        playerListener = new PlayerListener(this);
        shulkerListener = new ShulkerListener(this);

        Bukkit.getLogger().info("core created");
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("core enabled");

        cmdManager = backpackFactory.createShulkerCommands();

        getServer().getPluginManager().registerEvents(playerListener, this);
        getServer().getPluginManager().registerEvents(shulkerListener, this);

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public ShulkerManager getBackpackManager() {
        return null;
    }

    @Override
    public Shulker makeShulker(ShulkerHolder holder) {
        return null;
    }

    @Override
    public void openShulkerInventory(Player player, Shulker shulker) {
        shulkerManager.openShulkerInventory(player, shulker);
    }

    @Override
    public void closeShulkerInventory(String uid, ShulkerInventory shulkerInventory, Shulker shulker, ItemStack item) {
        shulkerManager.closeShulkerInventory(uid, shulkerInventory, shulker, item);
    }

    @Override
    public void dropShulkerInventory(String uid, ShulkerInventory shulkerInventory) {
        shulkerManager.dropShulkerInventory(uid, shulkerInventory);
    }

    @Override
    public Shulker makeShulker(Player player, ShulkerSize shulkerSize) {
        return shulkerManager.makeShulker(player, shulkerSize);
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
    public List<ItemStack> getShulkerItems(String uid) {
        return shulkerManager.getShulkerItems(uid);
    }

    @Override
    public Map<String, ItemStack[]> getShulkerInventoryData() {
        return shulkerManager.getShulkerInventoryData();
    }

    @Override
    public boolean doesPlayerHaveShulker(Player player) {
        return shulkerManager.doesPlayerHaveShulker(player);
    }

    @Override
    public boolean doesPlayerHaveShulker(String uid) {
        return shulkerManager.doesPlayerHaveShulker(uid);
    }

    @Override
    public ItemStack makeShulkerItemStack(Shulker shulker) {
        return shulkerManager.makeShulkerItemStack(shulker);
    }

    @Override
    public boolean getShulkerFromItemStack(ItemStack is) {
        return shulkerManager.getShulkerFromItemStack(is);
    }

    @Override
    public PersistentDataContainer shulkerContainer(ItemStack is) {
        return shulkerManager.shulkerContainer(is);
    }

    @Override
    public NamespacedKey shulkerKey() {
        return shulkerManager.shulkerKey();
    }

    @Override
    public @NotNull File getDataFolder() {
        return plugin.getDataFolder();
    }

    @Override
    public @NotNull PluginDescriptionFile getDescription() {
        return plugin.getDescription();
    }

    @Override
    public @NotNull PluginMeta getPluginMeta() {
        return plugin.getPluginMeta();
    }

    @Override
    public @NotNull FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    @Override
    public @Nullable InputStream getResource(@NotNull String s) {
        return plugin.getResource(s);
    }

    @Override
    public void saveConfig() {
        plugin.saveConfig();
    }

    @Override
    public void saveDefaultConfig() {
        plugin.saveDefaultConfig();
    }

    @Override
    public void saveResource(@NotNull String s, boolean b) {
        plugin.saveResource(s, b);
    }

    @Override
    public void reloadConfig() {
        plugin.reloadConfig();
    }

    @Override
    public PluginLoader getPluginLoader() {
        return plugin.getPluginLoader();
    }

    @Override
    public @NotNull Server getServer() {
        return plugin.getServer();
    }

    @Override
    public boolean isEnabled() {
        return plugin.isEnabled();
    }

    @Override
    public boolean isNaggable() {
        return plugin.isNaggable();
    }

    @Override
    public void setNaggable(boolean b) {
        plugin.setNaggable(b);
    }

    @Override
    public @Nullable ChunkGenerator getDefaultWorldGenerator(@NotNull String s, @Nullable String s1) {
        return plugin.getDefaultWorldGenerator(s, s1);
    }

    @Override
    public @Nullable BiomeProvider getDefaultBiomeProvider(@NotNull String s, @Nullable String s1) {
        return plugin.getDefaultBiomeProvider(s, s1);
    }

    @Override
    public @NotNull Logger getLogger() {
        return plugin.getLogger();
    }

    @Override
    public @NotNull String getName() {
        return plugin.getName();
    }

    @Override
    public @NotNull LifecycleEventManager<Plugin> getLifecycleManager() {
        return plugin.getLifecycleManager();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
