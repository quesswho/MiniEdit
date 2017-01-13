package com.ninja.NinjaEdit;

import com.ninja.NinjaEdit.maths.Vec3;

public interface Region extends Iterable<Vec3> {

	
    public Vec3 getMinimumPoint();

    public Vec3 getMaximumPoint();

    
    public int getSize();

    public int getWidth();

    public int getHeight();

    public int getLength();
    
}