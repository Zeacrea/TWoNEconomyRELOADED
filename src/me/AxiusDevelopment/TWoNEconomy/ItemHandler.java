package me.AxiusDevelopment.TWoNEconomy;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ItemHandler {
	
	Main plugin;
	public HashMap<String, String> messageData;
	
	public ItemHandler(Main instance) {
		plugin = instance;
		this.messageData = new HashMap<String, String>();
	}
	
	public HashMap<String, String> getMessageData()
	  {
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "Items.yml");

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
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "Items.yml");
	    FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	    for (String message : config.getConfigurationSection("").getKeys(false)) {
	      this.messageData.put(message, config.getString(message));
	    }
	    return this.messageData;
	  }
	  
	  private void saveMessages()
	  {
		  setMessage("Emerald.Item", "EMERALD");
		  setMessage("Emerald.Name", "&aEmerald");
		  setMessage("Emerald.Lore.'1'", "&7Take your Emerald items to a &c<NPC NAME>");
		  setMessage("Emerald.Lore.'2'", "&7To convert from items to currency.");
	  }
	  
	  private void setMessage(String name, String message)
	  {
		    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "Items.yml");
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
