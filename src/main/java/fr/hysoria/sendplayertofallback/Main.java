package fr.hysoria.sendplayertofallback;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements CommandExecutor{

	private String serverName;
	
	public void setServerName(String s) {
		this.serverName = s;
	}
	
	public String getServerName() {
		return this.serverName;
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		this.saveDefaultConfig();
		
		serverName = this.getConfig().getString("fallback_server_name");
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		SendCommandExecutor exec = new SendCommandExecutor();
		exec.addSubCommand("help", new HelpSubCommand());
		exec.addSubCommand("reload", "playertofallback.command.reload", new ReloadSubCommand(this));
		exec.addSubCommand("send", "playertofallback.command.send", new SendSubCommand(this));
		
		this.getCommand("playertofallback").setExecutor(exec);
		
	}
	
}
