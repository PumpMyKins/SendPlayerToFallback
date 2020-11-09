package fr.hysoria.sendplayertofallback.command;

import java.util.ArrayList;
import java.util.List;

import fr.hysoria.sendplayertofallback.MainFallback;
import fr.hysoria.sendplayertofallback.command.utils.ISubCommand;
import fr.hysoria.sendplayertofallback.command.utils.ISubTabCompleter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SendSubCommand implements ISubCommand,ISubTabCompleter {

	private MainFallback main;

	public SendSubCommand(MainFallback main) {
		this.main = main;
	}

	@Override
	public void onSubCommand(Command exec, CommandSender sender, List<String> args) {
		
		if(args.size() != 1) {
			sender.sendMessage(new ComponentBuilder("Invalid synthax !").color(ChatColor.RED).create());
		}
		
		String playerName = args.get(0);
		if(!this.onTabComplete(exec, sender, args).contains(playerName)) {
			sender.sendMessage(new ComponentBuilder("Player not found !").color(ChatColor.RED).create());
		}
		
		ProxiedPlayer player = this.main.getProxy().getPlayer(playerName);
        if (player.getServer().getInfo().getName().equalsIgnoreCase(this.main.getServerName())) {
            sender.sendMessage(new ComponentBuilder("Ce joueur est déjà sur le lobby !").color(ChatColor.RED).create());
            return;
        }

		sender.sendMessage(new ComponentBuilder("Vous allez etre envoyé sur le lobby !").color(ChatColor.AQUA).create());
        ServerInfo target = ProxyServer.getInstance().getServerInfo(this.main.getServerName());
        player.connect(target);
		
	}

	@Override
	public List<String> onTabComplete(Command command, CommandSender sender, List<String> args) {
		List<String> l = new ArrayList<String>();
		for (ProxiedPlayer player : this.main.getProxy().getPlayers()) {
			l.add(player.getName());
		}
		return l;		
	}

}
