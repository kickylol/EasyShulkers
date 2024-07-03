package com.kicky.easyshulkers.events;

import com.kicky.easyshulkers.Shulker;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class ShulkerInteractEvent extends Event implements Cancellable {

    private final Shulker shulker;
    private final Player player;
    private boolean cancelled;
    private static final HandlerList handlers = new HandlerList();

    public ShulkerInteractEvent(Shulker shulker, Player player) {
        this.shulker = shulker;
        this.player = player;
    }

    public Shulker getShulker() {
        return shulker;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
