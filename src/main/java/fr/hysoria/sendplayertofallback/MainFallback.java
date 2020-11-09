package fr.hysoria.sendplayertofallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import fr.hysoria.sendplayertofallback.command.FallbackCommandExecutor;
import fr.hysoria.sendplayertofallback.command.HelpSubCommand;
import fr.hysoria.sendplayertofallback.command.LobbyCommand;
import fr.hysoria.sendplayertofallback.command.ReloadSubCommand;
import fr.hysoria.sendplayertofallback.command.SendSubCommand;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class MainFallback extends Plugin {

	private String serverName;

	public void setServerName(String s) {
		this.serverName = s;
	}

	public String getServerName() {
		return this.serverName;
	}

	public void configSetup() throws Exception {
		final String configFileName = "config.yml";
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}

		File file = new File(getDataFolder(), configFileName);

		if (!file.exists()) {
			try (InputStream in = getResourceAsStream(configFileName)) {
				Files.copy(in, file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		String server = configuration.getString("fallback_server_name");
		if (!this.getProxy().getServers().keySet().contains(server)) {
			this.getLogger().severe("Fallback server invalide !");
			throw new Exception("Fallback server invalide !");
		}
		this.setServerName(server);
	}

	@Override
	public void onEnable() {
		try {
			this.configSetup();
		} catch (Exception e) {
			e.printStackTrace();
			this.getProxy().stop(e.getMessage());
		}
		this.getProxy().getPluginManager().registerCommand(this, new LobbyCommand(this.getServerName()));

		FallbackCommandExecutor exec = new FallbackCommandExecutor(this);
		exec.addSubCommand("help", new HelpSubCommand(this));
		exec.addSubCommand("reload", "playertofallback.command.reload", new ReloadSubCommand(this));
		this.getProxy().getPluginManager().registerCommand(this, exec);
		
	}
	
}
