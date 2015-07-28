package io.github.flarereturns.whereami.misc;

import io.github.flarereturns.whereami.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ConfigManager {

    public String noPermission;
    public String successOwn;
    public String successOther;
    public String reloadSuccess;
    public String notOnline;

    public void setupConfig() {
        try {
            if (!(Main.getInstance().getDataFolder().exists())) {
                Main.getInstance().getDataFolder().mkdir();
            }
            File f = new File(Main.getInstance().getDataFolder(), "config.yml");
            if (!(f.exists())) {
                Files.copy(Main.getInstance().getResourceAsStream("config.yml"), f.toPath());
            }
            Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(Main.getInstance().getDataFolder(), "config.yml"));
            Main.pr = ChatColor.translateAlternateColorCodes('&', cfg.getString("WhereAmI.Prefix"));
            noPermission = ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages.NoPermission"));
            successOwn = ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages.Success"));
            successOther = ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages.Others"));
            reloadSuccess = ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages.Reload"));
            notOnline = ChatColor.translateAlternateColorCodes('&', cfg.getString("Messages.NotOnline"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
