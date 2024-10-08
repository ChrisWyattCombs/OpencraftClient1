package opencraft.physics;

import opencraft.Block;
import opencraft.Entity;
import opencraft.Player;
import opencraft.World;
import opencraft.graphics.DisplayVariables;
import opencraft.graphics.Vector3f;

public class physicsUtils {
public static int convertFloatCoordToBlockCoord(float c) {
	int coord  =(int) Math.floor(c);
	
	
	
	return coord;
}
public static Block getNextBlockInDirection(float startX,float startY,float startZ, float directionX,float directionY,float directionZ,int range) {
	float x = startX;
	float y =  startY;
	float z = startZ;
	////System.out.println(x+" "+y+" "+z);
	Block b = null;
	int count = 0;
	while(count < range) {
		x+=directionX;
		y+=directionY;
		z+=directionZ;
				////System.out.println(x+" "+y+" "+z);
		
		if(World.CheckForBlock(convertFloatCoordToBlockCoord(x), convertFloatCoordToBlockCoord(y), convertFloatCoordToBlockCoord(z))) {
			b = World.getBlock(convertFloatCoordToBlockCoord(x), convertFloatCoordToBlockCoord(y), convertFloatCoordToBlockCoord(z));
			if(!b.isFluid()) {
			return b;
			}
		}
		count++;
	}
	return null;
}
public static Block getNextBlockInDirection(float startX,float startY,float startZ, float directionX,float directionY,float directionZ,int range,float step) {
	float x = startX;
	float y =  startY;
	float z = startZ;
	////System.out.println(x+" "+y+" "+z);
	Block b = null;
	int count = 0;
	while( count < range/step ) {
		x+=directionX*step;
		y+=directionY*step;
		z+=-directionZ*step;
				////System.out.println(x+" "+y+" "+z);
		
		b = World.getBlock(convertFloatCoordToBlockCoord(x), convertFloatCoordToBlockCoord(y), convertFloatCoordToBlockCoord(z));
		//
		if(b != null) {
			if(!b.isFluid()) {
			//System.out.println(b.getGlobalZ()+":"+ convertFloatCoordToBlockCoord(z));
			return b;
			}
		}
		count++;
	}
	return null;
}

public static Vector3f getLastPosBeforeNextBlockInDirection(float startX,float startY,float startZ, float directionX,float directionY,float directionZ,int range,float step) {
	float x = startX;
	float y =  startY;
	float z = startZ;
	////System.out.println(x+" "+y+" "+z);
	Block b = null;
	int count = 0;
	while( count < range/step ) {
		float lastX = x;
		float lastY = y;
		float lastZ = z;
		x+=directionX*step;
		y+=directionY*step;
		z+=-directionZ*step;
				////System.out.println(x+" "+y+" "+z);
		
		b = World.getBlock(convertFloatCoordToBlockCoord(x), convertFloatCoordToBlockCoord(y), convertFloatCoordToBlockCoord(z));
		//
		if(b != null) {
			if(!b.isFluid()) {
			////System.out.println(b.getGlobalZ()+":"+ convertFloatCoordToBlockCoord(z));
			return new Vector3f(convertFloatCoordToBlockCoord(lastX), convertFloatCoordToBlockCoord(lastY), convertFloatCoordToBlockCoord(lastZ));
			}
			}
		count++;
	}
	return null;
}
public static Vector3f getLastPosBeforeNextBlockInDirectionIncludingNull(float startX,float startY,float startZ, float directionX,float directionY,float directionZ,int range,float step) {
	float x = startX;
	float y =  startY;
	float z = startZ;
	////System.out.println(x+" "+y+" "+z);
	Block b = null;
	int count = 0;
	while( count < range/step ) {
		float lastX = x;
		float lastY = y;
		float lastZ = z;
		x+=directionX*step;
		y+=directionY*step;
		z+=-directionZ*step;
				////System.out.println(x+" "+y+" "+z);
		
		b = World.getBlock(convertFloatCoordToBlockCoord(x), convertFloatCoordToBlockCoord(y), convertFloatCoordToBlockCoord(z));
		//
		if(b != null) {
			if(!b.isFluid()) {
			////System.out.println(b.getGlobalZ()+":"+ convertFloatCoordToBlockCoord(z));
			return new Vector3f(lastX, lastY, lastZ);
			}
			}
		count++;
	}
	return new Vector3f(x, y, z);
}
public static Block getBlockLookingAt() {
	float nz = (float) (Math.cos(Math.toRadians(Player.yaw))*Math.cos(Math.toRadians(Player.pitch)));
			float nx = (float) (Math.sin(Math.toRadians(Player.yaw))*Math.cos(Math.toRadians(Player.pitch)));
					float ny = (float) Math.sin(Math.toRadians(Player.pitch));
	return getNextBlockInDirection(Player.x,Player.y,Player.z,nx,ny,nz,11,0.001f);
}
public static Vector3f getBlockPlacePos() {
	float nz = (float) (Math.cos(Math.toRadians(Player.yaw))*Math.cos(Math.toRadians(Player.pitch)));
			float nx = (float) (Math.sin(Math.toRadians(Player.yaw))*Math.cos(Math.toRadians(Player.pitch)));
					float ny = (float) Math.sin(Math.toRadians(Player.pitch));
	return getLastPosBeforeNextBlockInDirection(Player.x,Player.y,Player.z,nx,ny,nz,10,0.001f);
}
public static int getEnitityHiting(float startX,float startY,float startZ, float directionX,float directionY,float directionZ,float range,float step) {
	float x = startX;
	float y =  startY;
	float z = startZ;
	////System.out.println(x+" "+y+" "+z);
	Block b = null;
	int count = 0;
	while( count < range/step ) {
		x+=directionX*step;
		y+=directionY*step;
		z+=-directionZ*step;
				////System.out.println(x+" "+y+" "+z);
		for(int i = 0; i < World.entities.size(); i++) {
			Entity entity = World.entities.get(i);
			if(entity.pointInHitRadius(x, y, z)) {
				return i;
			}
		}
		
		count++;
	}
	return -1;
	
}
}
