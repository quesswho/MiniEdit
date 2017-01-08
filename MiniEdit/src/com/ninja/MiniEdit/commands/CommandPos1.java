package com.ninja.MiniEdit.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ninja.MiniEdit.SelectionManager;

public class CommandPos1 implements CommandExecutor {
	
	SelectionManager sm;
	
	public CommandPos1(SelectionManager sm) {
		this.sm = sm;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("MiniEdit.Pos1")) {
				Location pos1 = p.getLocation();
				sm.setPos1(p.getName(), pos1);
				p.sendMessage(ChatColor.LIGHT_PURPLE + "First position set to (" + pos1.getBlockX() + ", " + pos1.getBlockY() + ", " + pos1.getBlockZ() + ") (" + sm.getSelectionSize(p.getName()) + ")");
				return true;
			} else {
				p.sendMessage(ChatColor.DARK_RED + "I'm sorry but you don't have the permission to use this command!");
				return true;
			}
		}
		
		
		
		return false;
	}
	
}
