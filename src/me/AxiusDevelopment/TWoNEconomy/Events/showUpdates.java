package me.AxiusDevelopment.TWoNEconomy.Events;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.AxiusDevelopment.TWoNEconomy.TWoNEconomy;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.ConfigHandler;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.DataHandler;

public class showUpdates implements Listener {

	TWoNEconomy plugin;
	ConfigHandler config;
	DataHandler data;
	Logger log = Logger.getLogger("TWoNEconomy");
	HashMap<String, String> cfg = new HashMap<String, String>();
	String resource = "";
	
	public showUpdates(TWoNEconomy main, DataHandler data) {
		this.plugin = main;
		this.config = main.config;
		this.data = data;
		this.cfg = main.configData;
		this.resource = main.resource;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.hasPermission(cfg.get("viewUpdate"))) {
			p.sendMessage("§c§lTWoNEconomy §7new version available: §c" + getUpdate() + "§7. current version:§c " + plugin.getDescription().getVersion() + "§7.");
		}
	}
		
	public String getUpdate() {
			String v = "";
			System.out.print("[TWoNResourcePackHandler] Checking for updates...");
			try {
	            HttpURLConnection con = (HttpURLConnection) new URL(
	                    "http://www.spigotmc.org/api/general.php").openConnection();
	            con.setDoOutput(true);
	            con.setRequestMethod("POST");
	            con.getOutputStream()
	                    .write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=" + resource)
	                            .getBytes("UTF-8"));
	            String version = new BufferedReader(new InputStreamReader(
	                    con.getInputStream())).readLine();
	            v = version;
	        } catch (Exception ex) {
	            System.out.print("Failed to check for a update on spigot.");
	        }
			
			return v;
			
	}
}
