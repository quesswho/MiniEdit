package com.ninja.NinjaEdit;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class NinjaEditListener implements Listener {

	NinjaEdit inst;
	
	public NinjaEditListener(NinjaEdit inst) {
		this.inst = inst;
	}
	
	@EventHandler
    public void onInteract(PlayerInteractEvent e) {
		if(e.getItem().getType() == Material.WOOD_AXE) {
			if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Player p = (Player) e.getPlayer();
				if(p.hasPermission("MiniEdit.Pos2")) {
					Location loc = e.getClickedBlock().getLocation();
					inst.getSession(p.getName()).setPos2(p.getName(), loc);
					p.sendMessage(ChatColor.LIGHT_PURPLE + "Second position set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ") (" + inst.getSession(p.getName()).getSelectionSize(p.getName()) + ")");
					e.setCancelled(true);
					return;
				}
            } else if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
				Player p = (Player) e.getPlayer();
				if(p.hasPermission("MiniEdit.Pos1")) {
					Location loc = e.getClickedBlock().getLocation();
					inst.getSession(p.getName()).setPos1(p.getName(), loc);
					p.sendMessage(ChatColor.LIGHT_PURPLE + "First position set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ") (" + inst.getSession(p.getName()).getSelectionSize(p.getName()) + ")");
					e.setCancelled(true);
					return;
				}
            }
        }
    }
}
