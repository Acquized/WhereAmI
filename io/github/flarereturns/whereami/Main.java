package io.github.flarereturns.whereami;

import io.github.flarereturns.whereami.commands.WhereAmI;
import io.github.flarereturns.whereami.listeners.PostLogin;
import io.github.flarereturns.whereami.misc.ConfigManager;
import io.github.flarereturns.whereami.misc.MetricsLite;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public class Main extends Plugin  {

    public static String pr = "§8[§7Where§8Am§rI§8] §r";
    private static Main instance;
    private static ConfigManager cfgmanager;

    @Override
    public void onEnable() {
        instance = this;
        cfgmanager = new ConfigManager();
        getConfigManager().setupConfig();
        registerListeners();
        registerCommands();
        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        cfgmanager = null;
        instance = null;
    }

    private void registerCommands() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new WhereAmI());
    }

    private void registerListeners() {
        ProxyServer.getInstance().getPluginManager().registerListener(this, new PostLogin());
    }

    public static Main getInstance() {
        return instance;
    }

    public static ConfigManager getConfigManager() {
        return cfgmanager;
    }
}
