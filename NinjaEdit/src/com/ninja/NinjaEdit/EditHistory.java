package com.ninja.NinjaEdit;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.World;

import com.ninja.NinjaEdit.maths.Vec3;


public class EditHistory {

	//Stores The changes made
	private Map<Vec3, DataBlock> before = new HashMap<Vec3,DataBlock>();
	private Map<Vec3, DataBlock> after = new HashMap<Vec3, DataBlock>();
	
	@SuppressWarnings("deprecation")
	public DataBlock getBlock(World world, Vec3 vec) {
		int type = world.getBlockAt((int)vec.getX(), (int)vec.getY(), (int)vec.getZ()).getTypeId();
		int data = world.getBlockAt((int)vec.getX(), (int)vec.getY(), (int)vec.getZ()).getData();
		return new DataBlock(type, data);
	}
	
	//No history change
	@SuppressWarnings("deprecation")
	public void setBlock(World world, Vec3 vec, DataBlock block) {
		world.getBlockAt((int)vec.getX(), (int)vec.getY(), (int)vec.getZ()).setTypeIdAndData(block.getTypeId(), (byte) block.getNBTValue(), false);
	}
	
	//History change
	public void hSetBlock(World world, Vec3 vec, DataBlock block) {
        if (!before.containsKey(vec)) {
            before.put(vec, getBlock(world, vec));
        }
        after.put(vec, block);
        setBlock(world, vec, block);
    }
	
	 public int setBlocks(World world, Region region, DataBlock blocktype) {
	 	 
         int affected = 0;
     
         if (region instanceof CuboidRegion) {
             Vec3 min = region.getMinimumPoint();
             Vec3 max = region.getMaximumPoint();
         
             for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
                 for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
                     for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
                         Vec3 vec = new Vec3(x, y, z);
                         hSetBlock(world, vec, blocktype);
                         affected++;
                     }
                 }
             }
         } 
        
        return affected;
	 }

	
	 
	 public int replaceBlocks(World world, Region region, int fromBlockType, DataBlock toBlock) {
	 	 
	 	 int affected = 0;
	 	 if (region instanceof CuboidRegion) {
	            
	            Vec3 min = region.getMinimumPoint();
	            Vec3 max = region.getMaximumPoint();
     
	            for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
	                for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
	                    for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
	                        Vec3 vec = new Vec3(x, y, z);
	                        int curBlockType = getBlock(world, vec).getTypeId();
	                        if (fromBlockType == -1 && curBlockType != 0 || curBlockType == fromBlockType) {
	                            	hSetBlock(world, vec, toBlock);
	                                affected++;
	                        }
	                    }
	                }
	            }
	        
	 	 }
	 	 return affected;
	 }
	 
	 
	 
	 
	 
	public void undo(World world) {
		for(Map.Entry<Vec3, DataBlock> beforedata : before.entrySet()) {
			Vec3 vec = beforedata.getKey();
			setBlock(world, vec, beforedata.getValue());
		}
	}
	
	
	//undo's what you have undoed
	public void redo(World world) {
        for (Map.Entry<Vec3,DataBlock> afterdata : after.entrySet()) {
            Vec3 vec = afterdata.getKey();
            setBlock(world, vec, afterdata.getValue());
        }
    }
	
	public int size() {
        return before.size();
    }
}
