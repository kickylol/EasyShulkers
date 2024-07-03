package com.kicky.easyshulkers.holder;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerHolder implements ShulkerHolder {

    private final Player player;
    private boolean hasShulker;

    public PlayerHolder(Player player) {
        Preconditions.checkNotNull(player, "player");
        this.player = player;
    }

    public void setHasShulker(boolean hasShulker) {
        this.hasShulker = hasShulker;
    }


    @Override
    public String getName() {
        return "";
    }

    @Override
    public Location getLocation() {
        return null;
    }
}
