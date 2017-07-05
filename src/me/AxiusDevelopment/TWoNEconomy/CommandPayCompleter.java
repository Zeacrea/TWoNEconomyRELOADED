package me.AxiusDevelopment.TWoNEconomy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class CommandPayCompleter implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sndr, Command arg1, String arg2, String[] args) {
		List<String> players = new ArrayList<String>();
		if(sndr instanceof Player) {
			if(args.length == 0) {
				players.add("gold");
				players.add("emeralds");
			}
			
			if(args.length >= 2) {
				for(Player t : Bukkit.getOnlinePlayers()) {
					players.add(t.getName());
				}
			}
			
		}
		return players;
	}

}
