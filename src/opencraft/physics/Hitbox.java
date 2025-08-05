package opencraft.physics;

import opencraft.Block;
import opencraft.graphics.Vector3f;

public class Hitbox {
public float minX;
public float minY;
public float minZ;
public float maxX;
public float maxY;
public float maxZ;
public float x;
public float y;
public float z;





public Hitbox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ, float x, float y, float z) {
	super();
	this.minX = minX;
	this.minY = minY;
	this.minZ = minZ;
	this.maxX = maxX;
	this.maxY = maxY;
	this.maxZ = maxZ;
	this.x = x;
	this.y = y;
	this.z = z;
	
}




public boolean checkkForIntersectionWithBlock(Hitbox hb) {
	Vector3f[] vertices = {new Vector3f(x+minX, y+minY, z+minZ),new Vector3f(x+maxX, y+minY, z+minZ),new Vector3f(x+maxX, y+minY, z+maxZ),new Vector3f(x+minX, y+minY, z+maxZ)   , new Vector3f(x+minX, y+maxY, z+minZ),new Vector3f(x+maxX, y+maxY, z+minZ),new Vector3f(x+maxX, y+maxY, z+maxZ),new Vector3f(x+minX, y+maxY, z+maxZ)};
	for(Vector3f vertex : vertices) {
		if((vertex.getX() > hb.minX && vertex.getX() < hb.maxX)&&(vertex.getY() > hb.minY && vertex.getY() < hb.maxY)&&(vertex.getZ() > hb.minZ && vertex.getZ() < hb.maxZ)) {
			return true;
		}
	}
	return false;
	
}
}
