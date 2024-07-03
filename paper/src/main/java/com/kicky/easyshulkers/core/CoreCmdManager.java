package com.kicky.easyshulkers.core;

import com.kicky.easyshulkers.ShulkerApi;
import com.kicky.easyshulkers.commands.CmdManager;
import com.kicky.easyshulkers.commands.CreateCmd;
import vg.civcraft.mc.civmodcore.commands.CommandManager;

public final class CoreCmdManager extends CommandManager implements CmdManager {

    private final ShulkerApi shulkerApi;

    public CoreCmdManager(ShulkerApi shulkerApi) {
        super(shulkerApi);
        this.shulkerApi = shulkerApi;
        registerCommands();
    }

    public void registerCommands() {
        registerCommand(new CreateCmd(shulkerApi));
    }


}
