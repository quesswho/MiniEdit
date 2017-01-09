package com.ninja.NinjaEdit;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

public class PlayerSession {
	
	
	public static final int MAX_HISTORY_SIZE = 15;
	
	public Location pos1;
	public Location pos2;
	
	private List<EditHistory> history = new LinkedList<EditHistory>();
    private int historyPointer = 0;
    
    public void remember(EditHistory editHistory) {
        // Don't store anything if no changes were made
        if (editHistory.size() == 0) { return; }

        // Destroy any sessions after this undo point
        while (historyPointer < history.size()) {
            history.remove(historyPointer);
        }
        history.add(editHistory);
        while (history.size() > MAX_HISTORY_SIZE) {
            history.remove(0);
        }
        historyPointer = history.size();
    }
    
    public boolean undo(World world) {
        historyPointer--;
        if (historyPointer >= 0) {
            history.get(historyPointer).undo(world);
            return true;
        } else {
            historyPointer = 0;
            return false;
        }
    }
    
    public boolean redo(World world) {
        if (historyPointer < history.size()) {
            history.get(historyPointer).redo(world);
            historyPointer++;
            return true;
        }

        return false;
    }
    
    public void setPos1(String name, Location loc) {
		if(Bukkit.getPlayer(name) != null) {
			pos1 = loc;
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + "Tried to set pos1 to player: " + name + " But the player wasn't online.");
		}
	}
	
	
	public void setPos2(String name, Location loc) {
		if(Bukkit.getPlayer(name) != null) {
			pos2 = loc;
		} else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + "Tried to set pos2 to player: " + name + " But the player wasn't online.");
		}
	}
	
	public int getSelectionSize(String name) {
		if(Bukkit.getPlayer(name) != null) {
			Location temp = Bukkit.getPlayer(name).getLocation();
			if(pos1 == null || pos2 == null) {
				//Not all position set
				return 0;
			}
			
			temp.setX(1+Math.max(pos1.getX(), pos2.getX()) - Math.min(pos1.getX(), pos2.getX()));
			temp.setY(1+Math.max(pos1.getY(), pos2.getY()) - Math.min(pos1.getY(), pos2.getY()));
			temp.setZ(1+Math.max(pos1.getZ(), pos2.getZ()) - Math.min(pos1.getZ(), pos2.getZ()));
			
			//If this below is used. It is successful
			return temp.getBlockX() * temp.getBlockY() * temp.getBlockZ();
			
		} else {
			//Error
			return 0;
		}
	}
}
