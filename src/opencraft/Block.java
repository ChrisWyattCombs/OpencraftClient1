package opencraft;

import java.util.Vector;

import com.flowpowered.react.collision.shape.AABB;
import com.flowpowered.react.math.Vector3;

import opencraft.physics.Hitbox;

public abstract class Block {
public AABB hitbox;
private int x;
public int y;
private int z;
private int chunkX;
private int chunkZ; 
private int regionX;
private int regionZ;
public boolean lightingCalculated = false;
public float topLight = 0.3f;
public float bottomLight = 0.3f;
public float frontLight = 0.3f;
public float backLight = 0.3f;
public float rightLight = 0.3f;
public float leftLight = 0.3f;
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
	hitbox = new AABB(new Vector3(0+getGlobalX(), 0+y, 0+getGlobalZ()),new Vector3(1+getGlobalX(),1+y,1+getGlobalZ()));
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

public abstract boolean isAir();

public abstract int getID();

public abstract boolean isFluid();

public abstract Item getDrop();

public abstract float getStrength();
}
