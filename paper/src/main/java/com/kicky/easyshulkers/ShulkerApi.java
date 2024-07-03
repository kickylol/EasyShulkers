package com.kicky.easyshulkers;

import org.bukkit.plugin.Plugin;

public interface ShulkerApi extends Plugin, ShulkerAccess {

    ShulkerManager getBackpackManager();
}
