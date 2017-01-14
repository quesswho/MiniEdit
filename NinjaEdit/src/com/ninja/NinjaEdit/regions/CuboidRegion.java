package com.ninja.NinjaEdit.regions;

import java.util.Iterator;

import com.ninja.NinjaEdit.maths.Vec3;

public class CuboidRegion implements Region {
	
 	private Vec3 pos1;
    private Vec3 pos2;


    public CuboidRegion(Vec3 pos1, Vec3 pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    @Override
    public Vec3 getMinimumPoint() {
        return new Vec3(Math.min(pos1.getBlockX(), pos2.getBlockX()),
                         Math.min(pos1.getBlockY(), pos2.getBlockY()),
                         Math.min(pos1.getBlockZ(), pos2.getBlockZ()));
    }

    @Override
    public Vec3 getMaximumPoint() {
        return new Vec3(Math.max(pos1.getBlockX(), pos2.getBlockX()),
                         Math.max(pos1.getBlockY(), pos2.getBlockY()),
                         Math.max(pos1.getBlockZ(), pos2.getBlockZ()));
    }

    public int getSize() {
    	Vec3 min = getMinimumPoint();
        Vec3 max = getMaximumPoint();

        return (int)((max.getBlockX() - min.getBlockX() + 1) *
                     (max.getBlockY() - min.getBlockY() + 1) *
                     (max.getBlockZ() - min.getBlockZ() + 1));
    }

    public int getWidth() {
    	Vec3 min = getMinimumPoint();
        Vec3 max = getMaximumPoint();

        return (int)(max.getBlockX() - min.getBlockX() + 1);
    }

    public int getHeight() {
    	Vec3 min = getMinimumPoint();
    	Vec3 max = getMaximumPoint();

        return (int)(max.getBlockY() - min.getBlockY() + 1);
    }


    public int getLength() {
    	Vec3 min = getMinimumPoint();
        Vec3 max = getMaximumPoint();

        return (int)(max.getBlockZ() - min.getBlockZ() + 1);
    }


    public Iterator<Vec3> iterator() {
        throw new UnsupportedOperationException("Not implemented");
    }

}
