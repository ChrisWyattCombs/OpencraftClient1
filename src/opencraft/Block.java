package opencraft;

public abstract class Block {
private int x;
private int y;
private int z;
private int chunkX;
private int chunkZ; 
private int regionX;
private int regionZ;
public float topLight = 1f;
public float bottomLight = 1f;
public float frontLight = 1f;
public float backLight = 1f;
public float rightLight = 1f;
public float leftLight = 1f;
public float height = 1f;
public boolean visible = false;
//public float[] nextBlockCoords = new float[12];

public Block(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
	super();
	this.x = x;
	this.y = y;
	this.z = z;
	this.chunkX = chunkX;
	this.chunkZ = chunkZ;
	this.regionX = regionX;
	this.regionZ = regionZ;
}

public int getX() {
	return x;
}
public int getY() {
	return y;
}
public int getZ() {
	return z;
}
public int getChunkX() {
	return chunkX;
}
public int getChunkZ() {
	return chunkZ;
}
public int getRegionX() {
	return regionX;
}
public int getRegionZ() {
	return regionZ;
}
public float getGlobalX() {
	return x+((chunkX+(regionX*16))*16);
}
public float getGlobalZ() {
	return z+((chunkZ+(regionZ*16))*16);
}
public abstract void draw(boolean top,boolean bottom,boolean front,boolean back,boolean right, boolean left);

public abstract int getID();

public abstract boolean isFluid();

public abstract Item getDrop();


}
