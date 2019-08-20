package fr.hysoria.sendplayertofallback;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadSubCommand implements ISubCommand {	
	
	private Main main;

	public ReloadSubCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onSubCommand(CommandExecutor exec, CommandSender sender, Command cmd, List<String> args) {
		
		sender.sendMessage("§eConfiguration reloaded !");
		main.setServerName(this.main.getConfig().getString("fallback_server_name"));	
		return true;
		
	}

}
