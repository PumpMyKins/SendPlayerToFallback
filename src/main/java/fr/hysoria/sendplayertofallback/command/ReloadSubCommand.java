package fr.hysoria.sendplayertofallback.command;

import java.util.List;

import fr.hysoria.sendplayertofallback.MainFallback;
import fr.hysoria.sendplayertofallback.command.utils.ISubCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

public class ReloadSubCommand implements ISubCommand {

	private MainFallback main;

	public ReloadSubCommand(MainFallback main) {
		this.main = main;
	}

	@Override
	public void onSubCommand(Command exec, CommandSender sender, List<String> args) {
		try {
			this.main.configSetup();
			sender.sendMessage(new ComponentBuilder("Configuration reloaded !").color(ChatColor.AQUA).create());
		} catch (Exception e) {
			sender.sendMessage(new ComponentBuilder("Configuration reload ERROR !").color(ChatColor.RED).create());
			e.printStackTrace();
		}
	}

}
