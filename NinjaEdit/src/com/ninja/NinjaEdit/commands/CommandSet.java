package com.ninja.NinjaEdit.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ninja.NinjaEdit.EditHistory;
import com.ninja.NinjaEdit.NinjaEdit;
import com.ninja.NinjaEdit.PlayerSession;

import net.md_5.bungee.api.ChatColor;

public class CommandSet implements CommandExecutor {

	NinjaEdit inst;
	
	
	public CommandSet(NinjaEdit inst) {
		this.inst = inst;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("MiniEdit.set")) {
				if(args[0] != null) {
					String name = p.getName();
					PlayerSession session = inst.getSession(name);
					EditHistory editHistory = new EditHistory();
					int id = 0;
					try {
					id = Integer.parseInt(args[0]);
					} catch(NumberFormatException e) {
						p.sendMessage(ChatColor.DARK_RED + "You have to put in an id!");
						return true;
					}
					if(inst.getSession(name).pos1 != null && inst.getSession(name).pos1 != null) {
						
						Location pos2 = inst.getSession(name).pos2;
						Location pos1 = inst.getSession(name).pos1;
						//Note i set low and high to pos1 because of error saying that they need to be initialized
						Location low = new Location(pos1.getWorld(), 0,0,0);
						Location high = new Location(pos1.getWorld(), 0,0,0);
						//sorting...
						low.setX(
								Math.min(
										pos1.getBlockX(), 
										pos2.getBlockX()));
						low.setY(Math.min(pos1.getBlockY(), pos2.getBlockY()));
						low.setZ(Math.min(pos1.getBlockZ(), pos2.getBlockZ()));
						
						high.setX(Math.max(pos1.getBlockX(), pos2.getBlockX()));
						high.setY(Math.max(pos1.getBlockY(), pos2.getBlockY()));
						high.setZ(Math.max(pos1.getBlockZ(), pos2.getBlockZ()));
						
						int count = 0;
						
						//Nested loop for setting blocks
						for(int x = low.getBlockX();  x <= high.getX(); x++) {
							for(int y = low.getBlockY(); y <= high.getY(); y++) {
								for(int z = low.getBlockZ(); z <= high.getZ(); z++) {
									editHistory.hSetBlock(p.getWorld(), x,y,z,id);
									count++;
								}
							}
						}
						session.remember(editHistory);
						p.sendMessage(ChatColor.LIGHT_PURPLE + "Operation completed ("  + count + " blocks affected).");
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
		}
		return false;
		
	}
}
