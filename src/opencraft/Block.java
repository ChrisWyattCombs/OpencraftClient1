package opencraft;

public abstract class Block {
private int x;
private int y;
private int z;
private int chunkX;
private int chunkZ; 
private int regionX;
private int regionZ;



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
public abstract void draw();


}
