package me.AxiusDevelopment.TWoNEconomy;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {
	
	Main plugin;
	public HashMap<String, String> messageData;
	
	public Messages(Main instance) {
		plugin = instance;
		this.messageData = new HashMap<String, String>();
	}
	
	public HashMap<String, String> getMessageData()
	  {
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "messages.yml");

        saveMessages();
	    if (!f.exists()) {
	      try
	      {
	        f.createNewFile();
	      }
	      catch (IOException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    return loadMessages();
	  }

	public HashMap<String, String> loadMessages()
	  {
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "messages.yml");
	    FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	    for (String message : config.getConfigurationSection("").getKeys(false)) {
	      this.messageData.put(message, config.getString(message));
	    }
	    return this.messageData;
	  }
	  
	  private void saveMessages()
	  {
	    setMessage("balanceHeader", "&8&m+---------+&6&l %text% &8&m+---------+");
	    setMessage("headerYours", "YOUR BALANCE");
	    setMessage("headerOthers", "BALANCE OF %player%");
	    setMessage("goldFormat", "&c%gold%&7 Gold Coins");
	    setMessage("emeraldFormat", "&c%emerald%&7 Emeralds");
	    setMessage("playerNotFound", "&7That player couldn't be found!");
	    setMessage("noPermission", "&7You must be rank &b&lVIP&7 or above to do this!");
	    //ECONOMY
	    setMessage("noPermissionEco", "&7You must be rank &c&lADMIN&7 or above to do this!");
	    setMessage("ecoSyntax", "&7Usage: /eco <give|take|set|reset> <player> [amount]");
	    setMessage("notInt", "&7Hmm.. that doesn't seem right.. try again.");
	    setMessage("ecoUpdate", "&7Updated balance of %player%. new balance: %balance% Emeralds");
	    setMessage("playerUpdate", "&7%player% updated your balance. new balance: %balance% Emeralds");
	    //CONVERT
	    /*setMessage("error", "&4&lERROR&c Contact an administrator or developer. This isn't meant to happen!");
	    setMessage("debugMain", "&4&lDEBUG&c /nDgErmg7ft8DSNXD debug <ITEM|RELOAD>");
	    setMessage("debugItem", "&4&lDEBUG&c /nDgErmg7ft8DSNXD debug Item <VIEW|PREVIEW>");*/
	    //PAY
	    setMessage("paySyntax", "&7Usage: /pay <gold|emeralds> <player> <amount>");
	  }
	  
	  private void setMessage(String name, String message)
	  {
		    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "messages.yml");
		   FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		   if(!config.isSet(name)) {
			   config.set(name, message);
			   try
		  	   {
		  		   config.save(f);
		  	   }
		  	   catch (IOException e)
		  	   {
		  		   e.printStackTrace();
		  	   }
		   }
	  	   
	  }
}
