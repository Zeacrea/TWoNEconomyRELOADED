package me.AxiusDevelopment.TWoNEconomy;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

	Main plugin;
	ConfigHandler config;
	DataHandler data;
	Logger log = Logger.getLogger("TWoNEconomy");
	
	public JoinEvent(Main main, DataHandler data) {
		this.plugin = main;
		this.config = main.config;
		this.data = data;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {
		HashMap<String, Integer> a = data.loadMessages();
		if(!a.containsKey(e.getPlayer().getUniqueId().toString())) {
			data.saveMessages(e.getPlayer(), true, Integer.parseInt(config.messageData.get("defaultEmeralds")));
			a = data.loadMessages();
			log.info("Created data file for " + e.getPlayer().getName() + " -- #" + a.size());
		}
	}

}
