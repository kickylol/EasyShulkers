package com.kicky.easyshulkers.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.kicky.easyshulkers.ShulkerApi;
import com.kicky.easyshulkers.ShulkerSize;
import com.kicky.easyshulkers.events.ShulkerCreationEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CreateCmd extends BaseCommand {

    private final ShulkerApi shulkerApi;

    public CreateCmd(ShulkerApi shulkerApi) {
        this.shulkerApi = shulkerApi;
    }

    @CommandAlias("createshulker")
    @CommandPermission("easyshulkers.op")
    @Syntax("<size>")
    @Description("Generates a new shulker in your hand")
    @CommandCompletion("@ES_sizes")
    public void onCommand(CommandSender sender, @Single String size) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return;
        }

        Player player = (Player) sender;
        UUID uid = player.getUniqueId();

        ShulkerSize shulkerSize = ShulkerSize.fromString(size);
        if (shulkerSize == null) {
            player.sendMessage("Invalid shulker size.");
            return;
        }
        shulkerApi.makeShulker(player, shulkerSize);
        player.sendMessage("A shulker has been created.");

    }
}
