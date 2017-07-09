package me.AxiusDevelopment.TWoNEconomy.Commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.AxiusDevelopment.TWoNEconomy.TWoNEconomy;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.ConfigHandler;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.DataHandler;
import me.AxiusDevelopment.TWoNEconomy.YAMLHandlers.Messages;

public class CommandConvert implements CommandExecutor {

	TWoNEconomy main;
	DataHandler data;
	ConfigHandler config;
	Messages messages;
	HashMap<String, String> msg = new HashMap<String, String>();
	HashMap<String, String> cfg = new HashMap<String, String>();
	HashMap<String, Integer> dta = new HashMap<String, Integer>();
	
	public CommandConvert(TWoNEconomy main) {
		this.main = main;
		this.data = main.data;
		this.config = main.config;
		this.messages = main.messages;
		
		msg = messages.getMessageData();
		cfg = config.getMessageData();
	}

	@Override
	public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args) {
		dta = data.getMessageData(Bukkit.getPlayer(sndr.getName()));
		if(args.length < 1) {
			sndr.sendMessage(msg.get("error"));
			return true;
		}
		else {
			switch(args[0].toLowerCase()) {
			default:
				sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.get("error")));
				break;
				
			case "gold":
				break;
				
			case "emeralds":
				break;
				
			case "debug":
				if(args.length < 2) {
					sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.get("debugMain")));
				}
				else
				{
					switch(args[1].toLowerCase()) {
					default:
						sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.get("debugMain")));
						break;
						
					case "item":
						if(args.length < 3) {
							sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.get("debugItem")));
						}
						else
						{
							switch(args[2].toLowerCase()) {
							default:
								sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.get("debugItem")));
								break;
								
							case "":
							}
						}
						break;
						
					case "reload":
						break;
					}
				}
				break;
			}
			return true;
		}
	}

}
