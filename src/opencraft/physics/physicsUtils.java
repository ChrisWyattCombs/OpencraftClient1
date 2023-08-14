package opencraft.physics;

import opencraft.Block;
import opencraft.World;
import opencraft.graphics.DisplayVariables;

public class physicsUtils {
public static int convertFloatCoordToBlockCoord(float c) {
	int coord  =(int) Math.floor(c);
	
	
	
	return coord;
}
public static Block getNextBlockInDirection(float startX,float startY,float startZ, float directionX,float directionY,float directionZ,int range) {
	float x = startX;
	float y =  startY;
	float z = -startZ;
	//System.out.println(x+" "+y+" "+z);
	Block b = null;
	int count = 0;
	while(b == null && count < range) {
		x+=directionX;
		y+=directionY;
		z+=-directionZ;
				//System.out.println(x+" "+y+" "+z);
		b = World.getBlock(convertFloatCoordToBlockCoord(x), convertFloatCoordToBlockCoord(y), convertFloatCoordToBlockCoord(z));
		if(b != null) {
			return b;
		}
		count++;
	}
	return b;
}
public static Block getNextBlockInDirection(float startX,float startY,float startZ, float directionX,float directionY,float directionZ,int range,float step) {
	float x = startX;
	float y =  startY;
	float z = -startZ;
	//System.out.println(x+" "+y+" "+z);
	Block b = null;
	int count = 0;
	while(b == null && count < range/step ) {
		x+=directionX*step;
		y+=directionY*step;
		z+=-directionZ*step;
				//System.out.println(x+" "+y+" "+z);
		
		b = World.getBlock(convertFloatCoordToBlockCoord(x), convertFloatCoordToBlockCoord(y), convertFloatCoordToBlockCoord(z));
		//
		if(b != null) {
			
			System.out.println(b.getGlobalZ()+":"+ convertFloatCoordToBlockCoord(z));
			return b;
		}
		count++;
	}
	return b;
}
public static Block getBlockLookingAt() {
	float nz = (float) (Math.cos(Math.toRadians(DisplayVariables.camYaw))*Math.cos(Math.toRadians(DisplayVariables.CamPitch)));
			float nx = (float) (Math.sin(Math.toRadians(DisplayVariables.camYaw))*Math.cos(Math.toRadians(DisplayVariables.CamPitch)));
					float ny = (float) Math.sin(Math.toRadians(DisplayVariables.CamPitch));
	return getNextBlockInDirection( DisplayVariables.camX,DisplayVariables.camY,DisplayVariables.camZ,nx,ny,nz,10,0.001f);
}
}
