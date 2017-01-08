package com.ninja.MiniEdit.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ninja.MiniEdit.EditHistory;
import com.ninja.MiniEdit.MiniEdit;
import com.ninja.MiniEdit.PlayerSession;
import com.ninja.MiniEdit.SelectionManager;

import net.md_5.bungee.api.ChatColor;

public class CommandReplace implements CommandExecutor {

	SelectionManager sm;
	MiniEdit inst;
	
	
	public CommandReplace(SelectionManager sm, MiniEdit inst) {
		this.sm = sm;
		this.inst = inst;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("MiniEdit.replace")) {
				if(args.length >= 1) {
					PlayerSession session = inst.getSession(p);
					EditHistory editHistory = new EditHistory();
					int id = Integer.parseInt(args[0]);
					int id2 = Integer.parseInt(args[1]);
					if(sm.pos1.containsKey(p.getName()) && sm.pos2.containsKey(p.getName())) {
						
						Location pos2 = sm.pos2.get(p.getName());
						Location pos1 = sm.pos1.get(p.getName());
						
						Location low = sm.pos1.get(p.getName());
						Location high = sm.pos1.get(p.getName());
						
						//sorting...
						low.setX(Math.min((int)sm.pos1.get(p.getName()).getX(), (int)sm.pos2.get(p.getName()).getBlockX()));
						low.setY(Math.min((int)sm.pos1.get(p.getName()).getY(), (int)sm.pos2.get(p.getName()).getBlockY()));
						low.setZ(Math.min((int)sm.pos1.get(p.getName()).getZ(), (int)sm.pos2.get(p.getName()).getBlockZ()));
						
						high.setX(Math.max(sm.pos1.get(p.getName()).getX(), sm.pos2.get(p.getName()).getBlockX()));
						high.setY(Math.max(sm.pos1.get(p.getName()).getY(), sm.pos2.get(p.getName()).getBlockY()));
						high.setZ(Math.max(sm.pos1.get(p.getName()).getZ(), sm.pos2.get(p.getName()).getBlockZ()));
						
						
						
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
		}
		return false;
	}
	
}
