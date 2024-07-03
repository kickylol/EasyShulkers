package com.kicky.easyshulkers;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface ShulkerFactory {

    Shulker createShulker(ShulkerSize size, int shulkerId);
}
