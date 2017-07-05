package me.AxiusDevelopment.TWoNEconomy;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.AxiusDevelopment.TWoNEconomy.Main;

public class DataHandler {

	Main plugin;
	public HashMap<String, Integer> balanceData;
	ConfigHandler config;
	
	public DataHandler(Main instance) {
		plugin = instance;
		this.balanceData = new HashMap<String, Integer>();
	}
	
	public HashMap<String, Integer> getMessageData(Player p)
	  {
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "UUIDs.yml");

	    if (!f.exists()) {
	      try
	      {
	        f.createNewFile();
	        saveMessages(p, true, 0);
	      }
	      catch (IOException e)
	      {
	        e.printStackTrace();
	      }
	    }
	    return loadMessages();
	  }

	public HashMap<String, Integer> loadMessages()
	  {
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "UUIDs.yml");

	    FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	    for (String message : config.getConfigurationSection("").getKeys(false)) {
	      this.balanceData.put(message, config.getInt(message));
	    }
	    return this.balanceData;
	  }
	  
	  public void saveMessages(Player p, Boolean a, Integer i)
	  {
		  if(a) setMessage(p.getUniqueId().toString(), i);
	  }
	  
	  private void setMessage(String name, Integer message)
	  {
		    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "UUIDs.yml");

		   FileConfiguration config = YamlConfiguration.loadConfiguration(f);
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
