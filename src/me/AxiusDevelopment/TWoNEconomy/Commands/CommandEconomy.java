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

public class CommandEconomy implements CommandExecutor {

	DataHandler d;
	HashMap<String, Integer> data = new HashMap<String, Integer>();
	ConfigHandler c;
	HashMap<String, String> config = new HashMap<String, String>();
	Messages m;
	HashMap<String, String> messages = new HashMap<String, String>();
	TWoNEconomy plugin;
	
	public CommandEconomy(TWoNEconomy main) {
		this.plugin = main;
		this.m = main.messages;
		this.messages = main.messageData;
		this.c = main.config;
		this.config = main.configData;
		this.d = main.data;
		this.data = d.loadMessages();
	}

	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args) {
		if(!sndr.hasPermission(config.get("econPerm"))) {
			sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("noPermissionEco")));
			return true;
		}
		else 
		{
			if(args.length < 1) {
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax")));
				return true;
			}
			else 
			{
				switch(args[0].toLowerCase()) {
				case "give":
					if(args.length < 2) {
						sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax")));
						break;
					}
					else
					{
						Player t = Bukkit.getPlayer(args[1]);
						if(t == null) {
							sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("playerNotFound")));
							break;
						}
						else 
						{
							if(args.length < 3) {
								sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax").replaceAll("<player>", args[1])));
								break;
							}
							else
							{
								int amount = data.get(t.getUniqueId().toString());
								if(!isInt(args[2])) {
									sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax").replaceAll("<player>", args[1])));
									sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("notInt")));
									break;
								}
								else
								{
									amount = amount + Integer.parseInt(args[2]);
									sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoUpdate").replaceAll("%player%", t.getName()).replaceAll("%balance%", Integer.toString(amount))));
									t.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("playerUpdate").replaceAll("%player%", sndr.getName()).replaceAll("%balance%", Integer.toString(amount))));
									d.saveMessages(t, true, amount);
									d.loadMessages();
									break;
								}
							}
						}
					}
					
				case "take":
					if(args.length < 2) {
						sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax")));
						break;
					}
					else
					{
						Player t = Bukkit.getPlayer(args[1]);
						if(t == null) {
							sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("playerNotFound")));
							break;
						}
						else 
						{
							if(args.length < 3) {
								sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax").replaceAll("<player>", args[1])));
								break;
							}
							else
							{
								int amount = data.get(t.getUniqueId().toString());
								if(!isInt(args[2])) {
									sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax").replaceAll("<player>", args[1])));
									sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("notInt")));
									break;
								}
								else
								{
									amount = amount - Integer.parseInt(args[2]);
									sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoUpdate").replaceAll("%player%", t.getName()).replaceAll("%balance%", Integer.toString(amount))));
									t.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("playerUpdate").replaceAll("%player%", sndr.getName()).replaceAll("%balance%", Integer.toString(amount))));
									d.saveMessages(t, true, amount);
									d.loadMessages();
									break;
								}
							}
						}
					}
					
				case "set":
					if(args.length < 2) {
						sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax")));
						break;
					}
					else
					{
						Player t = Bukkit.getPlayer(args[1]);
						if(t == null) {
							sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("playerNotFound")));
							break;
						}
						else 
						{
							if(args.length < 3) {
								sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax").replaceAll("<player>", args[1])));
								break;
							}
							else
							{
								int amount = data.get(t.getUniqueId().toString());
								if(!isInt(args[2])) {
									sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax").replaceAll("<player>", args[1])));
									sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("notInt")));
									break;
								}
								else
								{
									amount = Integer.parseInt(args[2]);
									sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoUpdate").replaceAll("%player%", t.getName()).replaceAll("%balance%", Integer.toString(amount))));
									t.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("playerUpdate").replaceAll("%player%", sndr.getName()).replaceAll("%balance%", Integer.toString(amount))));
									d.saveMessages(t, true, amount);
									d.loadMessages();
									break;
								}
							}
						}
					}
					
				case "reset":
					if(args.length < 2) {
						sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoSyntax")));
						break;
					}
					else
					{
						Player t = Bukkit.getPlayer(args[1]);
						if(t == null) {
							sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("playerNotFound")));
							break;
						}
						else 
						{
									int amount = Integer.parseInt(config.get("defaultEmeralds"));
									sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("ecoUpdate").replaceAll("%player%", t.getName()).replaceAll("%balance%", Integer.toString(amount))));
									t.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.get("playerUpdate").replaceAll("%player%", sndr.getName()).replaceAll("%balance%", Integer.toString(amount))));
									d.saveMessages(t, true, amount);
									d.loadMessages();
									break;
						}
					}
					
				}
			}
		}
		return false;
	}

	public static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
}
