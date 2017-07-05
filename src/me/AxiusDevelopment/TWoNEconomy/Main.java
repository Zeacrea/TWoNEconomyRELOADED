package me.AxiusDevelopment.TWoNEconomy;

import java.io.File;
import java.util.HashMap;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	DataHandler data;
	ConfigHandler config;
	Messages messages;
	Economy econ;

	public HashMap<String, String> configData = new HashMap<String, String>();
	public HashMap<String, String> messageData = new HashMap<String, String>();
	
	public void onEnable() {

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        econ = rsp.getProvider();
		
		this.data = new DataHandler(this);
		this.config = new ConfigHandler(this);
		this.messages = new Messages(this);

		File data = new File(this.getDataFolder().getParentFile() + File.separator + "TWoN");
	    File thisone = new File(data + File.separator + "TWoNEconomy");
	    if(!data.exists()) {
	    	data.mkdir();
	    	thisone.mkdir();
	    }

	    this.configData = config.getMessageData();
	    this.messageData = messages.getMessageData();
	    
	    if(!configData.get("version").equals(getDescription().getVersion())) {
	    	config.updateVersion();
	    }

	    this.configData = config.getMessageData();
	    
	    if(!thisone.exists()) thisone.mkdir();
	    
	    getServer().getPluginManager().registerEvents(new JoinEvent(this, this.data), this);
	    getCommand("Balance").setExecutor(new CommandBalance(this));
	    getCommand("Economy").setExecutor(new CommandEconomy(this));
	    getCommand("nDgErmg7ft8DSNXD").setExecutor(new CommandConvert(this));
	    getCommand("Pay").setExecutor(new CommandPay(this));
	    getCommand("Pay").setTabCompleter(new CommandPayCompleter());
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
}
