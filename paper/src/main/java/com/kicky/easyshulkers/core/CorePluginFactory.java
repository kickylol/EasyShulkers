package com.kicky.easyshulkers.core;

import com.google.common.base.Preconditions;
import com.kicky.easyshulkers.*;
import com.kicky.easyshulkers.commands.CmdManager;
import org.bukkit.plugin.Plugin;

import java.util.UUID;


public class CorePluginFactory implements ShulkerFactory {

    private final ShulkerApi shulkerApi;

    public static ShulkerApi createCore(final Plugin plugin) {
        return new EasyShulkerCore(plugin);
    }

    public CorePluginFactory(final ShulkerApi plugin) {
        Preconditions.checkNotNull(plugin, "plugin");
        this.shulkerApi = plugin;
    }

    @Override
    public Shulker createShulker(ShulkerSize size, int shulkerId) {

        UUID uid = null;

        return new CoreShulker(
                uid,
                size,
                shulkerId
        );
    }

    public ShulkerManager createBackpackManager() {
        return new CoreShulkerManager(shulkerApi, this);
    }

    public CmdManager createShulkerCommands() {
        return new CoreCmdManager(shulkerApi);
    }
}
