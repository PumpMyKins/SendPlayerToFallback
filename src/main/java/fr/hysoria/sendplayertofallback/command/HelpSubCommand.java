package fr.hysoria.sendplayertofallback.command;

import java.util.List;

import fr.hysoria.sendplayertofallback.MainFallback;
import fr.hysoria.sendplayertofallback.command.utils.ISubCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;


public class HelpSubCommand implements ISubCommand{

	private MainFallback main;
	
	public HelpSubCommand(MainFallback main) {
		this.main = main;
	}

	@Override
	public void onSubCommand(Command exec, CommandSender sender, List<String> args) {

		sender.sendMessage(new ComponentBuilder("SendPlayerToFallback[" + this.main.getDescription().getVersion() + "] commmand : \"playertofallback [help/reload]\"").color(ChatColor.AQUA).create());

	}

	public void onSubCommand(Command motdCommandExecutor, CommandSender sender) {

		this.onSubCommand(motdCommandExecutor, sender, null);

	}

}
