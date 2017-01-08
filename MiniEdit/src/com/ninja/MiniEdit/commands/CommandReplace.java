package com.ninja.MiniEdit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ninja.MiniEdit.SelectionManager;

import net.md_5.bungee.api.ChatColor;

public class CommandReplace implements CommandExecutor {

SelectionManager sm;
	
	public CommandReplace(SelectionManager sm) {
		this.sm = sm;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length >= 1) {
				int id = Integer.parseInt(args[0]);
				int id2 = Integer.parseInt(args[1]);
				if(sm.pos1.containsKey(p.getName()) && sm.pos2.containsKey(p.getName())) {
					int count = sm.replaceBlocks(sm.pos1.get(p.getName()), sm.pos2.get(p.getName()), id, id2);
					p.sendMessage(ChatColor.LIGHT_PURPLE + "Successfully changed "  + count + " blocks.");
					return true;
				} else {
					p.sendMessage(ChatColor.RED + "You need to make a selection first.");
					return true;
				}
			} else {
				p.sendMessage(ChatColor.RED + "Too few arguments");
				return true;
			}
		}
		return false;
	}
	
}
