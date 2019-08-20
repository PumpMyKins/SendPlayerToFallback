package fr.hysoria.sendplayertofallback;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface SubTabCompleter {

	public List<String> onTabComplete(CommandExecutor exec, CommandSender sender, Command command, String alias, String[] args);
	
}
