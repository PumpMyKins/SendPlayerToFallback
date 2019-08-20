package fr.hysoria.sendplayertofallback;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpSubCommand implements ISubCommand{

	@Override
	public boolean onSubCommand(CommandExecutor exec, CommandSender sender, Command cmd, List<String> args) {
		
		sender.sendMessage("Unvalid uses of this commande : playertofallback [reload/help/send [player]]");
		
		return true;
	}

	public boolean onSubCommand(CommandExecutor sdaCommandExecutor, CommandSender p, Command cmd) {
		return this.onSubCommand(sdaCommandExecutor, p, cmd,Collections.emptyList());
	}

}
