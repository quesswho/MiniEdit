package com.ninja.NinjaEdit;

public class DataBlock {

	
	private short id = 0;
	
	private char nbtvalue = 0;
	
	
	public DataBlock(int id) {
		this.id = (short) id;
	}
	
	public DataBlock(int id, int nbtvalue) {
		this.id = (short) id;
		this.nbtvalue = (char) nbtvalue;
	}
	
	public int getTypeId() { return id; }
	public int getNBTValue() { return nbtvalue; }
	
	public void setType(int id) {
        this.id = (short)id;
    }
	
	public void setNBTValue(int nbtvalue) {
        this.nbtvalue = (char)nbtvalue;
    }
	
	
	public boolean isAir() {
		return (id == 0);
	}
}
