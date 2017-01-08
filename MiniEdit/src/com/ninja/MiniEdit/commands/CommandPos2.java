package com.ninja.MiniEdit.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ninja.MiniEdit.SelectionManager;
import com.ninja.MiniEdit.maths.Vec3;

public class CommandPos2 implements CommandExecutor {
	
	SelectionManager sm;
	
	public CommandPos2(SelectionManager sm) {
		this.sm = sm;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("MiniEdit.Pos2")) {
				Location pos2 = p.getLocation();
				sm.setPos2(p.getName(), pos2);
				p.sendMessage(ChatColor.LIGHT_PURPLE + "Second position set to (" + pos2.getBlockX() + ", " + pos2.getBlockY() + ", " + pos2.getBlockZ() + ") (" + sm.getSelectionSize(p.getName()) + ")");
				return true;
			} else {
				p.sendMessage(ChatColor.DARK_RED + "I'm sorry but you don't have the permission to use this command!");
				return true;
			}
		}
		
		
		
		return false;
	}
	
}
