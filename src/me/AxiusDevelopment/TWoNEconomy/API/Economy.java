package me.AxiusDevelopment.TWoNEconomy.API;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.AxiusDevelopment.TWoNEconomy.TWoNEconomy;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.ConfigHandler;

public class Economy {

	/*
	 * @return HashMap<String, Integer> string being players UUID, integer being players balance.
	 */
	public HashMap<String, Integer> getBalances() {
		HashMap<String, Integer> balanceData = new HashMap<String, Integer>();
		TWoNEconomy plugin = new TWoNEconomy();
	    File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "UUIDs.yml");
	    FileConfiguration config = YamlConfiguration.loadConfiguration(f);
	    for (String message : config.getConfigurationSection("").getKeys(false)) {
	      balanceData.put(message, config.getInt(message));
	    }
	    return balanceData;
	}
	
	/*
	 * @param p Player object of whom you're getting the balance of
	 * @return integer balance of player "p"
	 */
	public int getBalance(Player p) {
		HashMap<String, Integer> balanceRaw = getBalances();
		if(balanceRaw.containsKey(p.getUniqueId().toString())) return balanceRaw.get(p.getUniqueId().toString());
		else return 0;
	}
	
	/*
	 * @param player Player object of whom you're setting the balance of
	 * @param amount Integer object of which is the amount your setting
	 */
	public void setBalance(Player player, Integer amount) {
		TWoNEconomy plugin = new TWoNEconomy();
		File f = new File(plugin.getDataFolder().getParentFile() + File.separator + "TWoN" + File.separator + "TWoNEconomy" + File.separator + "UUIDs.yml");

		   FileConfiguration config = YamlConfiguration.loadConfiguration(f);
		   config.set(player.getUniqueId().toString(), amount);
	  	   try
	  	   {
	  		   config.save(f);
	  	   }
	  	   catch (IOException e)
	  	   {
	  		   e.printStackTrace();
	  	   }
	}
	
	/*
	 * @param player Player object of whom you're depositing money to
	 * @param amount Integer object of which is the amount your depositing
	 */
	public void depositPlayer(Player player, Integer amount) {
		HashMap<String, Integer> balanceRaw = getBalances();
		if(balanceRaw.containsKey(player.getUniqueId().toString())) {
			int bal = balanceRaw.get(player.getUniqueId().toString());
			bal = bal + amount;
			setBalance(player, bal);
		}
		else
		{
			TWoNEconomy plugin = new TWoNEconomy();
			ConfigHandler cfg = new ConfigHandler(plugin);
			setBalance(player, Integer.parseInt(cfg.messageData.get("defaultEmeralds")) + amount);
		}
	}
	
	/*
	 * @param player Player object of whom you're withdrawing money from
	 * @param amount Integer object of which is the amount your withdrawing
	 */
	public void withdrawPlayer(Player player, Integer amount) {
		HashMap<String, Integer> balanceRaw = getBalances();
		if(balanceRaw.containsKey(player.getUniqueId().toString())) {
			int bal = balanceRaw.get(player.getUniqueId().toString());
			bal = bal - amount;
			setBalance(player, bal);
		}
		else
		{
			TWoNEconomy plugin = new TWoNEconomy();
			ConfigHandler cfg = new ConfigHandler(plugin);
			setBalance(player, Integer.parseInt(cfg.messageData.get("defaultEmeralds")) - amount);
		}
	}
	
	/*
	 * @param player Player object of whom your checking
	 * @param amount Integer object of which youre checking the player has
	 * @return true/false (boolean)
	 */
	public boolean playerHas(Player player, Integer amount) {
		HashMap<String, Integer> balanceRaw = getBalances();
		int bal = balanceRaw.get(player.getUniqueId().toString());
		if(bal > amount) return true;
		else return false;
	}
	
	/*
	 * @param player Player object of whom you're creating the balance of
	 */
	public void createBank(Player player) {
		TWoNEconomy plugin = new TWoNEconomy();
		ConfigHandler cfg = new ConfigHandler(plugin);
		setBalance(player, Integer.parseInt(cfg.messageData.get("defaultEmeralds")));
	}
}
