package me.AxiusDevelopment.TWoNEconomy.YAMLHandlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.AxiusDevelopment.TWoNEconomy.TWoNEconomy;

public class ConfigHandler {

	TWoNEconomy plugin;
	public HashMap<String, String> messageData;
	Logger log = Logger.getLogger("TWoNEconomy");
	
	public ConfigHandler(TWoNEconomy instance) {
		plugin = instance;
		this.messageData = new HashMap<String, String>();
	}
	
	public HashMap<String, String> getMessageData()
	  {
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "config.yml");
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
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "config.yml");
	    FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	    for (String message : config.getConfigurationSection("").getKeys(false)) {
	      this.messageData.put(message, config.getString(message));
	    }
	    return this.messageData;
	  }
	  
	  private void saveMessages()
	  {
	    setMessage("version", plugin.getDescription().getVersion());
	    setMessage("balanceOthersPerm", "TWoNEconomy.balanceothers");
	    setMessage("econPerm", "TWoNEconomy.economy");
	    setMessage("defaultEmeralds", "10");
	    setMessage("viewUpdate", "TWoNEconomy.viewUpdate");
	  }
	  
	  public void updateVersion() {
		  log.info("Updating configuration file...");
		  	File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "config.yml");
		  	FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		  	config.set("version", plugin.getDescription().getVersion());
		  	try
		  	{
		  		config.save(f);
				  log.info("Complete!");
		  	}
		  	catch (IOException e)
		  	{
		  		e.printStackTrace();
		  	}
	  }
	  
	  private void setMessage(String name, String message)
	  {
		    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "config.yml");
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
