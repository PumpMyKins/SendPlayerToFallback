package fr.hysoria.sendplayertofallback.command;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LobbyCommand extends Command {

    private String serverName;

    public LobbyCommand(String name) {
        super("lobby");
        this.serverName = name;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new ComponentBuilder("Vous devez etre un joueur pour utiliser cette commande !").color(ChatColor.RED).create());
            return;
        }     
        
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (player.getServer().getInfo().getName().equalsIgnoreCase(this.serverName)) {
            player.sendMessage(new ComponentBuilder("Vous etes déjà sur le lobby !").color(ChatColor.RED).create());
            return;
        }

        sender.sendMessage(new ComponentBuilder("Vous allez etre envoyé sur le lobby !").color(ChatColor.AQUA).create());
        ServerInfo target = ProxyServer.getInstance().getServerInfo(this.serverName);
        player.connect(target);

    }

}
