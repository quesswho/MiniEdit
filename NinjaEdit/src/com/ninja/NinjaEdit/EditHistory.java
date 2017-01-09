package com.ninja.NinjaEdit;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.World;

import com.ninja.NinjaEdit.maths.Vec3;


public class EditHistory {

	//Stores The changes made
	private HashMap<Vec3, Integer> before = new HashMap<Vec3,Integer>();
	private HashMap<Vec3, Integer> after = new HashMap<Vec3, Integer>();
	
	@SuppressWarnings("deprecation")
	public int getBlock(World world, int x, int y, int z) {
		return world.getBlockAt(x, y, z).getTypeId();
	}
	
	//No history change
	@SuppressWarnings("deprecation")
	public void setBlock(World world, int x, int y, int z, int id) {
		world.getBlockAt(x, y, z).setTypeId(id);
	}
	
	//History change
	public void hSetBlock(World world, int x, int y, int z, int blockType) {
        Vec3 pt = new Vec3(x, y, z);
        if (!before.containsKey(pt)) {
            before.put(pt, getBlock(world, x, y, z));
        }
        after.put(pt, blockType);
        setBlock(world, x, y, z, blockType);
    }
	
	public void undo(World world) {
		for(Map.Entry<Vec3, Integer> beforedata : before.entrySet()) {
			Vec3 vec = beforedata.getKey();
			setBlock(world, (int)vec.getX(), (int)vec.getY(), (int)vec.getZ(), beforedata.getValue());
		}
	}
	
	
	//undo's what you have undoed
	public void redo(World world) {
        for (Map.Entry<Vec3,Integer> afterdata : after.entrySet()) {
            Vec3 vec = afterdata.getKey();
            setBlock(world, (int)vec.getX(), (int)vec.getY(),(int)vec.getZ(), afterdata.getValue());
        }
    }
	
	public int size() {
        return before.size();
    }
}
