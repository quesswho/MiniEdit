package com.ninja.MiniEdit;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.ninja.MiniEdit.commands.CommandPos1;
import com.ninja.MiniEdit.commands.CommandPos2;
import com.ninja.MiniEdit.commands.CommandReplace;
import com.ninja.MiniEdit.commands.CommandSet;
import com.ninja.MiniEdit.commands.CommandUndo;

public class MiniEdit extends JavaPlugin {
	
	// selectionmanager instance
	public SelectionManager selecmanager;
	public PlayerSession playerSession;
	private HashMap<String,PlayerSession> sessions = new HashMap<String,PlayerSession>();
	
	@Override
	public void onEnable() {
		selecmanager = new SelectionManager();
		initCommands();
	}
	
	
	
	@Override
	public void onDisable() {
		
	}
	
	public PlayerSession getSession(Player player) {
        if (sessions.containsKey(player.getName())) {
            return sessions.get(player.getName());
        } else {
            PlayerSession session = new PlayerSession();
            sessions.put(player.getName(), session);
            return session;
        }
    }
	
	private void initCommands() {
		this.getCommand("/pos1").setExecutor(new CommandPos1(selecmanager));
		this.getCommand("/pos2").setExecutor(new CommandPos2(selecmanager));
		this.getCommand("/set").setExecutor(new CommandSet(selecmanager, this));
		this.getCommand("/replace").setExecutor(new CommandReplace(selecmanager, this));
		this.getCommand("/undo").setExecutor(new CommandUndo(this));
		this.getCommand("/redo").setExecutor(new CommandUndo(this));
	}
}
