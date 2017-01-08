package com.ninja.NinjaEdit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;

public class PlayerSession {
	
	
	public static final int MAX_HISTORY_SIZE = 15;
	
	public HashMap<String, Location> pos1 = new HashMap<String, Location>();
	public HashMap<String, Location> pos2 = new HashMap<String, Location>();
	
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
}
