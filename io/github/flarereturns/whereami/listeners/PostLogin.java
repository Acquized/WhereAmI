package io.github.flarereturns.whereami.listeners;

import io.github.flarereturns.whereami.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PostLogin implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();
        if((p.getName().equals("FlareReturns")) ||
                (p.getName().equals("Beaconplays")) || p.getName().equals("_JustLisa_")
                || p.getName().equals("MinecraftTagLP")) {
            p.sendMessage(Main.pr + ChatColor.WHITE + "Hey! Diese Proxy nutzt dein Plugin WhereAmI.");
        }
    }

}
