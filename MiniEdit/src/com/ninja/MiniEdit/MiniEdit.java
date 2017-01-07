package com.ninja.MiniEdit;

import org.bukkit.plugin.java.JavaPlugin;

import com.ninja.MiniEdit.commands.CommandPos1;
import com.ninja.MiniEdit.commands.CommandPos2;
import com.ninja.MiniEdit.commands.CommandReplace;
import com.ninja.MiniEdit.commands.CommandSet;

public class MiniEdit extends JavaPlugin {
	
	// selectionmanager instance
	public SelectionManager selecmanager;
	
	
	@Override
	public void onEnable() {
		selecmanager = new SelectionManager();
		initCommands();
	}
	
	
	
	@Override
	public void onDisable() {
		
	}
	
	private void initCommands() {
		this.getCommand("/pos1").setExecutor(new CommandPos1(selecmanager));
		this.getCommand("/pos2").setExecutor(new CommandPos2(selecmanager));
		this.getCommand("/set").setExecutor(new CommandSet(selecmanager));
		this.getCommand("/replace").setExecutor(new CommandReplace(selecmanager));
	}
}
