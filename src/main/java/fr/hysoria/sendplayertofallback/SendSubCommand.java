package fr.hysoria.sendplayertofallback;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class SendSubCommand implements ISubCommand,SubTabCompleter {

	private Main main;

	public SendSubCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onSubCommand(CommandExecutor exec, CommandSender sender, Command cmd, List<String> args) {
		
		if(args.size() != 1) {
			return new HelpSubCommand().onSubCommand(exec, sender, cmd);
		}
		
		String playerName = args.get(0);
		if(!this.onTabComplete(exec, null, cmd, null, null).contains(playerName)) {
			sender.sendMessage("§cJoueur introuvable !");
			return false;
		}
		
		Player target = main.getServer().getPlayer(playerName);
		if(target == null) {
			sender.sendMessage("§cErreur joueur !");
			return false;
			
		}
		
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(main.getServerName());
		
		target.sendPluginMessage(main, "BungeeCord", out.toByteArray());	
		
		return true;
		
	}

	@Override
	public List<String> onTabComplete(CommandExecutor exec, CommandSender sender, Command command, String alias,String[] args) {
		List<String> l = new ArrayList<String>();
		for (Player player : this.main.getServer().getOnlinePlayers()) {
			l.add(player.getName());
		}
		return l;		
	}

}
