package me.AxiusDevelopment.TWoNEconomy;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.AxiusDevelopment.TWoNEconomy.Commands.CommandBalance;
import me.AxiusDevelopment.TWoNEconomy.Commands.CommandEconomy;
import me.AxiusDevelopment.TWoNEconomy.Commands.CommandPay;
import me.AxiusDevelopment.TWoNEconomy.Events.JoinEvent;
import me.AxiusDevelopment.TWoNEconomy.Events.showUpdates;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.ConfigHandler;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.DataHandler;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.Messages;
import net.milkbowl.vault.economy.Economy;

public class TWoNEconomy extends JavaPlugin {
	
	public DataHandler data;
	public ConfigHandler config;
	public Messages messages;
	public Economy econ;
	public String resource = "43639";

	public HashMap<String, String> configData = new HashMap<String, String>();
	public HashMap<String, String> messageData = new HashMap<String, String>();
	
	public void onEnable() {

		if (!setupEconomy() ) {
            System.out.print(String.format("[%s] Plugin disabled -- No vault, or economy plugin, found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
		
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
	    getCommand("Pay").setExecutor(new CommandPay(this));
	    
	    if(Double.parseDouble(getUpdate()) > Double.parseDouble(getDescription().getName())) {
	    	System.out.print(String.format("[%s] update found!", getDescription().getVersion()));
	    	this.getServer().getPluginManager().registerEvents(new showUpdates(this, this.data), this);
	    }
	    else
	    {
	    	System.out.print(String.format("[%s] all up to date!", getDescription().getName()));
	    }
	    
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
	
	public String getUpdate() {
		String v = "";
		System.out.print(String.format("[%s] Checking for updates...", getDescription().getName()));
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
