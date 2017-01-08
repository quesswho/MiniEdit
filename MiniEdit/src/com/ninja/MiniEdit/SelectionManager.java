package com.ninja.MiniEdit;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class SelectionManager {

	public HashMap<String, Location> pos1 = new HashMap<String, Location>();
	public HashMap<String, Location> pos2 = new HashMap<String, Location>();
	
	public void setPos1(String name, Location loc) {
		if(Bukkit.getPlayer(name) != null) {
			pos1.put(name, loc);
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + "Tried to set pos1 to player: " + name + " But the player wasn't online.");
		}
	}
	
	
	public void setPos2(String name, Location loc) {
		if(Bukkit.getPlayer(name) != null) {
			pos2.put(name, loc);
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + "Tried to set pos2 to player: " + name + " But the player wasn't online.");
		}
	}
	
	public void setBlock(World world, int x, int y, int z, int id) {
		world.getBlockAt(x, y, z).setTypeId(id);
	}
	
	public Block getBlock(World world, int x, int y, int z) {
		return world.getBlockAt(x, y, z);
	}
	
	
	
	public int setBlocks(Location pos1, Location pos2, int id) {
		//Note i set low and high to pos1 because of error saying that they need to be initialized
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
		for(int x = low.getBlockX();  x <= high.getX(); x++) {
			for(int y = low.getBlockY(); y <= high.getY(); y++) {
				for(int z = low.getBlockZ(); z <= high.getZ(); z++) {
					setBlock(pos1.getWorld(), x,y,z,id);
					count++;
				}
			}
		}
		return count;
	}
	
	public int replaceBlocks(Location pos1, Location pos2, int id, int id2) {
		//Note i set low and high to pos1 because of error saying that they need to be initialized
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
		for(int x = low.getBlockX();  x <= high.getX(); x++) {
			for(int y = low.getBlockY(); y <= high.getY(); y++) {
				for(int z = low.getBlockZ(); z <= high.getZ(); z++) {
					if(getBlock(pos1.getWorld(), x,y,z).getTypeId() == id) {
						setBlock(pos1.getWorld(), x,y,z,id2);
						count++;
					}
				}
			}
		}
		return count;
	}
	
	
	public int getSelectionSize(String name) {
		if(Bukkit.getPlayer(name) != null) {
			Location temp = Bukkit.getPlayer(name).getLocation();
			if(pos1.isEmpty() || pos2.isEmpty()) {
				//Not all position set
				return 0;
			}
			
			temp.setX(1+Math.max(pos1.get(name).getBlockX(), pos2.get(name).getBlockX()) - Math.min(pos1.get(name).getBlockX(), pos2.get(name).getBlockX()));
			temp.setY(1+Math.max(pos1.get(name).getBlockY(), pos2.get(name).getBlockY()) - Math.min(pos1.get(name).getBlockY(), pos2.get(name).getBlockY()));
			temp.setZ(1+Math.max(pos1.get(name).getBlockZ(), pos2.get(name).getBlockZ()) - Math.min(pos1.get(name).getBlockZ(), pos2.get(name).getBlockZ()));
			
			//If this below is used. It is successful
			return temp.getBlockX() * temp.getBlockY() * temp.getBlockZ();
			
		} else {
			//Error
			return 0;
		}
	}
}
