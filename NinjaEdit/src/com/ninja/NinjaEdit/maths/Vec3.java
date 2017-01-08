package com.ninja.NinjaEdit.maths;

public class Vec3 {
	
	private final double x,y,z;
	
	public Vec3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	public double getZ() { return z; }
	
	public boolean equals(Vec3 other) {
        return other.x == x && other.y == y && other.z == z;
    }
}
