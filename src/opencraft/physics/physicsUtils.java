package opencraft.physics;

import opencraft.Block;
import opencraft.World;
import opencraft.graphics.DisplayVariables;

public class physicsUtils {
public static int convertFloatCoordToBlockCoord(float c) {
	return (int)(c+0.5f);
}
public static Block getNextBlockInDirection(float startX,float startY,float startZ, float directionX,float directionY,float directionZ,int range) {
	float x = startX;
	float y =  startY;
	float z = startZ;
	System.out.println(x+" "+y+" "+z);
	Block b = null;
	int count = 0;
	while(b == null && count < range) {
		x+=directionX;
		y+=directionY;
		z+=directionZ;
				System.out.println(x+" "+y+" "+z);
		b = World.getBlock(convertFloatCoordToBlockCoord(x), convertFloatCoordToBlockCoord(y), convertFloatCoordToBlockCoord(z));
		count++;
	}
	return b;
}
public static Block getBlockLookingAt() {
	float nx = (float) (Math.cos(Math.toRadians(DisplayVariables.camYaw))*Math.cos(Math.toRadians(DisplayVariables.CamPitch)));
			float nz = (float) (Math.sin(Math.toRadians(DisplayVariables.camYaw))*Math.cos(Math.toRadians(DisplayVariables.CamPitch)));
					float ny = (float) Math.sin(Math.toRadians(DisplayVariables.CamPitch));
	return getNextBlockInDirection( DisplayVariables.camX,DisplayVariables.camY,DisplayVariables.camZ,nx,ny,nz,10);
}
}
