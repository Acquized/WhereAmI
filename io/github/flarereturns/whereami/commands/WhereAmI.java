package io.github.flarereturns.whereami.commands;

import io.github.flarereturns.whereami.Main;
import io.github.flarereturns.whereami.misc.ConfigManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class WhereAmI extends Command {

    public WhereAmI() {
        super("whereami", null, new String[] {"whereis", "findme", "servercurrent", "currentserver", "connectedserver"});
    }

    @SuppressWarnings("deprecation")
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer) sender;
            if(args.length == 0) {
                if((p.hasPermission("whereami.use")) || (p.hasPermission("whereami.*"))) {
                    String server = p.getServer().getInfo().getName();
                    p.sendMessage(Main.pr + Main.getConfigManager().successOwn.replaceAll("%server%", server));
                } else {
                    p.sendMessage(Main.pr + Main.getConfigManager().noPermission.replaceAll("%perm%", "whereami.use"));
                }
            } else if(args.length == 1) {
                if(!(args[0].equalsIgnoreCase("reload"))) {
                    if ((p.hasPermission("whereami.others")) || (p.hasPermission("whereami.*"))) {
                        ProxiedPlayer t = ProxyServer.getInstance().getPlayer(args[0]);
                        if (t != null) {
                            String server = t.getServer().getInfo().getName();
                            p.sendMessage(Main.pr + Main.getConfigManager().successOther.replaceAll("%server%", server).replaceAll("%player%", t.getName()));
                        } else {
                            p.sendMessage(Main.pr + Main.getConfigManager().notOnline.replaceAll("%player%", args[0]));
                        }
                    } else {
                        p.sendMessage(Main.pr + Main.getConfigManager().noPermission.replaceAll("%perm%", "whereami.others"));
                    }
                } else {
                    if((p.hasPermission("whereami.admin")) || (p.hasPermission("whereami.*"))) {
                        long now = System.currentTimeMillis();
                        Main.getConfigManager().setupConfig();
                        long newmilis = now - System.currentTimeMillis();
                        p.sendMessage(Main.pr + Main.getConfigManager().reloadSuccess.replaceAll("%time%", newmilis + "ms").replaceAll("-", ""));
                    } else {
                        p.sendMessage(Main.pr + Main.getConfigManager().noPermission.replaceAll("%perm%", "whereami.admin"));
                    }
                }
            } else {
                p.sendMessage(Main.pr + "§rUsage: §7/whereami [Player | Reload]");
            }
        } else {
            sender.sendMessage(Main.pr + "You must be a player to use this command.");
        }
    }
}
