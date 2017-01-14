package com.ninja.NinjaEdit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ninja.NinjaEdit.EditHistory;
import com.ninja.NinjaEdit.NinjaEdit;
import com.ninja.NinjaEdit.PlayerSession;
import com.ninja.NinjaEdit.maths.Vec3;

public class CommandStack implements CommandExecutor {

NinjaEdit inst;
	
	public CommandStack(NinjaEdit inst) {
		this.inst = inst;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("MiniEdit.stack")) {
				Vec3 dir = new Vec3();
				// no dir set. will use where ever the player is looking
				if(args.length == 1) {
					 
					 double yaw = p.getLocation().getYaw();
					 
					 double pitch = p.getLocation().getPitch();
					 
					 if(pitch > 60) dir.add(0, -1, 0); // down
					 else if(pitch < -60) dir.add(0, 1, 0); // up
					 else if(yaw > -45 && yaw < 45) dir.add(0, 0, 1); //south
					 else if(yaw < 135 && yaw > 45) dir.add(-1, 0, 0); //West
					 else if((yaw > 135) || (yaw < -135)) dir.add(0, 0, -1); //north
					 else if((yaw > -135 && yaw < -45)) dir.add(1, 0, 0); //East
					 
				} else if(args.length == 2) {
					String dirstr = args[1];
					if(dirstr.equalsIgnoreCase("u") || dirstr.equalsIgnoreCase("up")) dir.add(0, 1, 0);
					else if(dirstr.equalsIgnoreCase("d") || dirstr.equalsIgnoreCase("down")) dir.add(0, -1, 0);
					else if(dirstr.equalsIgnoreCase("s") || dirstr.equalsIgnoreCase("south")) dir.add(0, 0, 1);
					else if(dirstr.equalsIgnoreCase("n") || dirstr.equalsIgnoreCase("north")) dir.add(0, 0, -1);
					else if(dirstr.equalsIgnoreCase("w") || dirstr.equalsIgnoreCase("west")) dir.add(-1, 0, 0);
					else if(dirstr.equalsIgnoreCase("e") || dirstr.equalsIgnoreCase("east")) dir.add(1, 0, 0);
				} else if(args.length == 0) {
					p.sendMessage(ChatColor.RED + "Too few arguments!");
					return true;
				} else {
					p.sendMessage(ChatColor.RED + "Too many arguments!");
					return true;
				}
				PlayerSession session = inst.getSession(p.getName());
				EditHistory editHistory = new EditHistory();
				
				int count = Integer.parseInt(args[0]);
				
				int affected = editHistory.stackClipboard(p.getWorld(), session.getRegion(),dir, count);
				p.sendMessage(ChatColor.LIGHT_PURPLE + "" + affected + " block(s) changed. Undo with //undo");
				session.remember(editHistory);
				return true;
			}
		}
		return false;
	}

}
