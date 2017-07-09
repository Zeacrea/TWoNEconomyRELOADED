package me.AxiusDevelopment.TWoNEconomy.API;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.AxiusDevelopment.TWoNEconomy.TWoNEconomy;

public class Configuration {

	/*
	 * @param useKeys if useKeys is set to true, the entries can include "a.b", if not the entries are limited to "a"
	 * @return The HashMap of configuration entries.
	 */
	public HashMap<String, String> getEntries(boolean useKeys) {
		TWoNEconomy plugin = new TWoNEconomy();
		HashMap<String, String> configData = new HashMap<String, String>();
		File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "config.yml");
	    FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	    for (String message : config.getConfigurationSection("").getKeys(useKeys)) {
	      configData.put(message, config.getString(message));
	    }
	    return configData;
	}
	
	/*
	 * @param name the name specified, for example "a", or "a.b"
	 * @param message the message you're setting after "name", this can be anything as long as its a string
	 */
	public void setMessage(String name, String message)
	  {
		TWoNEconomy plugin = new TWoNEconomy();
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
