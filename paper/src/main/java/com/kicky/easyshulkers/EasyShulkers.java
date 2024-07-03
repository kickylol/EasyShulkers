package com.kicky.easyshulkers;

import com.kicky.easyshulkers.commands.CmdManager;
import com.kicky.easyshulkers.core.CorePluginFactory;
import vg.civcraft.mc.civmodcore.ACivMod;

public class EasyShulkers extends ACivMod {

    private static ShulkerApi core;

    public static ShulkerApi getApi() {
        return core;
    }

    public EasyShulkers() {
        core = CorePluginFactory.createCore(this);
    }

    private EasyShulkers plugin;
    private CmdManager cmdManager;
    private ShulkerApi shulkerApi;

    @Override
    public void onEnable() {
        core.onEnable();
    }

    @Override
    public void onDisable() {
        core.onDisable();
    }
}
