package me.AxiusDevelopment.TWoNEconomy.Commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.AxiusDevelopment.TWoNEconomy.TWoNEconomy;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.ConfigHandler;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.DataHandler;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.Messages;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;

public class CommandBalance implements CommandExecutor {

	TWoNEconomy main;
	DataHandler data;
	ConfigHandler config;
	Messages messages;
	Economy econ;
	
	public CommandBalance(TWoNEconomy main) {
		this.main = main;
		this.data = main.data;
		this.config = main.config;
		this.messages = main.messages;
		this.econ = main.econ;
	}

	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args) {
		HashMap<String, String> a = messages.getMessageData();
		HashMap<String, Integer> b = data.getMessageData(Bukkit.getPlayer(sndr.getName()));
		if(args.length < 1) {
			String balanceHeader = a.get("balanceHeader");
			String headerYours = a.get("headerYours");
			String goldFormat = a.get("goldFormat");
			String emeraldFormat = a.get("emeraldFormat");
			
			String header = ChatColor.translateAlternateColorCodes('&', balanceHeader.replaceAll("%text%", headerYours));
			String gold = ChatColor.translateAlternateColorCodes('&', goldFormat.replaceAll("%gold%", Double.toString(econ.getBalance(Bukkit.getOfflinePlayer(Bukkit.getPlayer(sndr.getName()).getUniqueId())))));
			String emerald = ChatColor.translateAlternateColorCodes('&', emeraldFormat.replaceAll("%emerald%", b.get(Bukkit.getPlayer(sndr.getName()).getUniqueId().toString()).toString()));
			
			sndr.sendMessage(header);
			sndr.sendMessage(gold);
			sndr.sendMessage(emerald);
			return true;
		}
		else
		{
			if(!sndr.hasPermission(config.messageData.get("balanceOthersPerm"))) {
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', a.get("noPermission")));
				return true;
			}
			else
			{
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) {
					sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', a.get("playerNotFound")));
					return true;
				}
				else
				{
					String balanceHeader = a.get("balanceHeader");
					String headerYours = a.get("headerOthers");
					String goldFormat = a.get("goldFormat");
					String emeraldFormat = a.get("emeraldFormat");
					
					String header = ChatColor.translateAlternateColorCodes('&', balanceHeader.replaceAll("%text%", headerYours.replaceAll("%player%", args[0])));
					String gold = ChatColor.translateAlternateColorCodes('&', goldFormat.replaceAll("%gold%", Double.toString(econ.getBalance(Bukkit.getOfflinePlayer(target.getUniqueId())))));
					String emerald = ChatColor.translateAlternateColorCodes('&', emeraldFormat.replaceAll("%emerald%", b.get(target.getUniqueId().toString()).toString()));
					
					sndr.sendMessage(header);
					sndr.sendMessage(gold);
					sndr.sendMessage(emerald);
					return true;
				}
			}
		}
	}

	/*
	 * balanceHeader
	 * headerYours
	 * headerOthers
	 * goldFormat
	 * emeraldFormat
	 */

}
