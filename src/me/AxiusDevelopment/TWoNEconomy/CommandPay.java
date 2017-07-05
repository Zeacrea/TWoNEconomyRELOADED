package me.AxiusDevelopment.TWoNEconomy;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandPay implements CommandExecutor {

	DataHandler d;
	HashMap<String, Integer> data = new HashMap<String, Integer>();
	ConfigHandler c;
	HashMap<String, String> config = new HashMap<String, String>();
	Messages m;
	HashMap<String, String> messages = new HashMap<String, String>();
	Main plugin;
	
	public CommandPay(Main main) {
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
		if(args.length == 0) {
			sndr.sendMessage(messages.get("paySyntax"));
			return true;
		}
		else
		{
			if(args.length == 1) {
				sndr.sendMessage(messages.get("paySyntax").replaceFirst("\\<gold\\|emeralds\\>", args[0]));
			}
		}
		return false;
	}

}
