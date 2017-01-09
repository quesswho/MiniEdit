package com.ninja.NinjaEdit;

import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import com.ninja.NinjaEdit.commands.CommandPos1;
import com.ninja.NinjaEdit.commands.CommandPos2;
import com.ninja.NinjaEdit.commands.CommandRedo;
import com.ninja.NinjaEdit.commands.CommandReplace;
import com.ninja.NinjaEdit.commands.CommandSet;
import com.ninja.NinjaEdit.commands.CommandUndo;

public class NinjaEdit extends JavaPlugin {
	
    //All the players
	private HashMap<String,PlayerSession> sessions = new HashMap<String,PlayerSession>();
	
	@Override
	public void onEnable() {
		initCommands();
	}
	
	
	
	@Override
	public void onDisable() {
		
	}
	
	public PlayerSession getSession(String player) {
        if (sessions.containsKey(player)) {
            return sessions.get(player);
        } else {
            PlayerSession session = new PlayerSession();
            sessions.put(player, session);
            return session;
        }
    }
	
	private void initCommands() {
		this.getCommand("/pos1").setExecutor(new CommandPos1(this));
		this.getCommand("/pos2").setExecutor(new CommandPos2(this));
		this.getCommand("/set").setExecutor(new CommandSet(this));
		this.getCommand("/replace").setExecutor(new CommandReplace(this));
		this.getCommand("/undo").setExecutor(new CommandUndo(this));
		this.getCommand("/redo").setExecutor(new CommandRedo(this));
	}
}
