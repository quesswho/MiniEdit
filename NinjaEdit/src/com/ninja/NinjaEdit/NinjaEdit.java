package com.ninja.NinjaEdit;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.ninja.NinjaEdit.Exceptions.UnknownItemException;
import com.ninja.NinjaEdit.commands.CommandCopy;
import com.ninja.NinjaEdit.commands.CommandPaste;
import com.ninja.NinjaEdit.commands.CommandPos1;
import com.ninja.NinjaEdit.commands.CommandPos2;
import com.ninja.NinjaEdit.commands.CommandRedo;
import com.ninja.NinjaEdit.commands.CommandReplace;
import com.ninja.NinjaEdit.commands.CommandSet;
import com.ninja.NinjaEdit.commands.CommandUndo;
import com.ninja.NinjaEdit.commands.CommandUp;

public class NinjaEdit extends JavaPlugin {

	// All the players
	private HashMap<String, PlayerSession> sessions = new HashMap<String, PlayerSession>();

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new NinjaEditListener(this), this);
		initCommands();
	}

	@Override
	public void onDisable() {

	}

	@SuppressWarnings("deprecation")
	public DataBlock getBlock(String id, boolean allAllowed) throws UnknownItemException {
		
		DataBlock blocktype;
		
        String[] args0 = id.split("\\|");
        String[] args1 = args0[0].split(":", 2);
        String testID = args1[0];
        int typeid = 0;
        int data;
		try {
			data = args1.length > 1 ? Integer.parseInt(args1[1]) : 0;
			if (data > 15 || data < 0) {
                data = 0;
            }
			
		} catch (NumberFormatException e) {
			data = 0;
		}
		
		try {
			typeid = Integer.parseInt(id);
			blocktype = new DataBlock(typeid, data);
		} catch (NumberFormatException e) {
			blocktype = new DataBlock(Bukkit.getUnsafe().getMaterialFromInternalName(testID).getId(), data);
			if(blocktype.equals(null)) {
				throw new UnknownItemException();
			}
		}
		return blocktype;
		
	}

	public DataBlock getBlock(String id) throws UnknownItemException {
		return getBlock(id, false);
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
		this.getCommand("/up").setExecutor(new CommandUp(this));
		this.getCommand("/copy").setExecutor(new CommandCopy(this));
		this.getCommand("/paste").setExecutor(new CommandPaste(this));
	}
}
