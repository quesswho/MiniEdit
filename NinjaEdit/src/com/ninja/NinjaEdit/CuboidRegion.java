package com.ninja.NinjaEdit;

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
        return new Vec3(Math.min(pos1.getX(), pos2.getX()),
                         Math.min(pos1.getY(), pos2.getY()),
                         Math.min(pos1.getZ(), pos2.getZ()));
    }

    @Override
    public Vec3 getMaximumPoint() {
        return new Vec3(Math.max(pos1.getX(), pos2.getX()),
                         Math.max(pos1.getY(), pos2.getY()),
                         Math.max(pos1.getZ(), pos2.getZ()));
    }

    public int getSize() {
    	Vec3 min = getMinimumPoint();
        Vec3 max = getMaximumPoint();

        return (int)((max.getX() - min.getX() + 1) *
                     (max.getY() - min.getY() + 1) *
                     (max.getZ() - min.getZ() + 1));
    }

    public int getWidth() {
    	Vec3 min = getMinimumPoint();
        Vec3 max = getMaximumPoint();

        return (int)(max.getX() - min.getX() + 1);
    }

    public int getHeight() {
    	Vec3 min = getMinimumPoint();
    	Vec3 max = getMaximumPoint();

        return (int)(max.getY() - min.getY() + 1);
    }


    public int getLength() {
    	Vec3 min = getMinimumPoint();
        Vec3 max = getMaximumPoint();

        return (int)(max.getZ() - min.getZ() + 1);
    }


    public Iterator<Vec3> iterator() {
        throw new UnsupportedOperationException("Not implemented");
    }

}
