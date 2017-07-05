package me.AxiusDevelopment.TWoNEconomy;

import java.util.HashMap;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPay
  implements CommandExecutor
{
  DataHandler d;
  HashMap<String, Integer> data = new HashMap<String, Integer>();
  ConfigHandler c;
  HashMap<String, String> config = new HashMap<String, String>();
  Messages m;
  HashMap<String, String> messages = new HashMap<String, String>();
  Main plugin;
  Economy eco;
  
  public CommandPay(Main main) {
    plugin = main;
    m = main.messages;
    messages = main.messageData;
    c = main.config;
    config = main.configData;
    d = main.data;
    data = d.loadMessages();
    eco = main.econ;
  }
  


  public boolean onCommand(CommandSender sndr, Command arg1, String arg2, String[] args)
  {
    Player p = Bukkit.getPlayer(sndr.getName());
    if (args.length == 0) {
      sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', (String)messages.get("paySyntax")));
      return true;
    }
    

    if (args.length == 1) {
      sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', ((String)messages.get("paySyntax")).replaceFirst("\\<player\\>", args[1])));


    }
    else if (args.length == 2) {
      sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', ((String)messages.get("paySyntax")).replaceFirst("\\<gold\\|emeralds\\>", args[0]).replaceFirst("\\<player\\>", args[1])));


    }
    else if (isInt(args[2])) {
      Player t = Bukkit.getPlayer(args[0]);
      if (t == null) {
        sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', (String)messages.get("playerNotFound")));
      }
      else {
        switch (args[1].toLowerCase()) {
        default:
        	sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', ((String)messages.get("paySyntax")).replaceFirst("\\<player\\>", args[1])));
        	break;
        	
        case "emeralds":
        	int em = ((Integer)data.get(p.getUniqueId().toString())).intValue();
        	int em2 = ((Integer)data.get(t.getUniqueId().toString())).intValue();
        
        	if (em < Integer.parseInt(args[2])) {
        		sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', (String)messages.get("notEnough")));
        	}
        	else
        	{
        		d.saveMessages(p, Boolean.valueOf(true), Integer.valueOf(em - Integer.parseInt(args[2])));
        		d.saveMessages(t, Boolean.valueOf(true), Integer.valueOf(em2 + Integer.parseInt(args[2])));
          
        		sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', ((String)messages.get("paySent")).replaceAll("%amount%", args[2] + " " + args[1]).replaceAll("%player%", args[0])));
        		t.sendMessage(ChatColor.translateAlternateColorCodes('&', ((String)messages.get("payReceived")).replaceAll("%amount%", args[2] + " " + args[1]).replaceAll("%player%", sndr.getName())));
        	}
        	break;
        	
        case "gold":
          double bal = eco.getBalance(Bukkit.getOfflinePlayer(p.getUniqueId()));
          
          if (bal < Double.parseDouble(args[2])) {
            sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', (String)messages.get("notEnough")));
          }
          else
          {
            eco.withdrawPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()), Double.parseDouble(args[2]));
            eco.depositPlayer(Bukkit.getOfflinePlayer(t.getUniqueId()), Double.parseDouble(args[2]));
            
            sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', ((String)messages.get("paySent")).replaceAll("%amount%", args[2] + " " + args[1]).replaceAll("%player%", args[0])));
            t.sendMessage(ChatColor.translateAlternateColorCodes('&', ((String)messages.get("payReceived")).replaceAll("%amount%", args[2] + " " + args[1]).replaceAll("%player%", sndr.getName())));
          }
          break;
        }
      }
    }
    else
    {
      sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', ((String)messages.get("paySyntax")).replaceFirst("\\<gold\\|emeralds\\>", args[0]).replaceFirst("\\<player\\>", args[1])));
      sndr.sendMessage(ChatColor.translateAlternateColorCodes('&', (String)messages.get("notInt")));
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