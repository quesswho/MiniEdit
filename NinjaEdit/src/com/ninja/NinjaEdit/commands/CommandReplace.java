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

public class CommandReplace implements CommandExecutor {

	NinjaEdit inst;
	
	
	public CommandReplace(NinjaEdit inst) {
		this.inst = inst;
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("MiniEdit.replace")) {
				if(args.length >= 1) {
					String name = p.getName();
					PlayerSession session = inst.getSession(name);
					EditHistory editHistory = new EditHistory();
					int id = 0;
					int id2 = 0;
					try {
					id = Integer.parseInt(args[0]);
					id2 = Integer.parseInt(args[1]);
					} catch(NumberFormatException e) {
						p.sendMessage(ChatColor.DARK_RED + "You have to put in an id!");
						return true;
					}
					if(inst.getSession(name).pos1 != null && inst.getSession(name).pos1 != null) {
						
						Location pos2 = inst.getSession(name).pos2;
						Location pos1 = inst.getSession(name).pos1;
						
						Location low = new Location(pos1.getWorld(), 0,0,0);
						Location high = new Location(pos1.getWorld(), 0,0,0);
						
						//sorting...
						low.setX(Math.min(pos1.getBlockX(), pos2.getBlockX()));
						low.setY(Math.min(pos1.getBlockY(), pos2.getBlockY()));
						low.setZ(Math.min(pos1.getBlockZ(), pos2.getBlockZ()));
						
						high.setX(Math.max(pos1.getBlockX(), pos2.getBlockX()));
						high.setY(Math.max(pos1.getBlockY(), pos2.getBlockY()));
						high.setZ(Math.max(pos1.getBlockZ(), pos2.getBlockZ()));
						
						
						
						int count = 0;
						
						//Nested loop for setting blocks
						for(int x = (int) low.getX();  x <= high.getX(); x++) {
							for(int y = (int) low.getY(); y <= high.getY(); y++) {
								for(int z = (int) low.getZ(); z <= high.getZ(); z++) {
									if(editHistory.getBlock(p.getWorld(), x,y,z) == id) {
										editHistory.hSetBlock(p.getWorld(), x,y,z,id2);
										count++;
									}
								}
							}
						}
						session.remember(editHistory);
						p.sendMessage(ChatColor.LIGHT_PURPLE + "" + count + " blocks(s) have been replaced.");
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
