package opencraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import opencraft.graphics.DisplayVariables;
import opencraft.physics.physicsUtils;

public class Player {
	public static float x = 0;
	public static float y = 150;
	public static float z = 0;
	public static float velocityY = 0;
	public static float yaw = 0;
	public static float pitch = 0;
	public static float forwardVelocity = 0;
	public static float backwardVelocity = 0;
	public static float leftVelocity = 0;
	public static float rightVelocity = 0;
	public static boolean grounded = false;
	
	public static void updatePostitionAndRotation() {
		
		yaw+= Mouse.getDX()/4f;
		pitch += Mouse.getDY()/4f;
		if(pitch > 90) {
			pitch = 90;
		}
		if(pitch < -90) {
			pitch = -90;
		}
	
	
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			if(backwardVelocity == 0) {
				if(forwardVelocity < 0.01f) {
					forwardVelocity+=0.002f;
				}
				
			}else {
				backwardVelocity -= 0.002f;
			}
		
		}else {
			if(forwardVelocity > 0) {
				forwardVelocity-=0.001f;
			}
			if(forwardVelocity < 0) {
				forwardVelocity = 0;
			}
		}
		
		if(!grounded && velocityY > -0.03f) {
			velocityY -= 0.001f;
		}
		
		x += (forwardVelocity )*Math.sin(Math.toRadians(yaw)) * DisplayVariables.deltaTime;
		z+=(forwardVelocity)  * Math.cos(Math.toRadians(yaw)) * DisplayVariables.deltaTime;
		
		y+=velocityY * DisplayVariables.deltaTime;
		//z+=(rightVelocity)  * Math.cos(Math.toRadians(yaw)) * DisplayVariables.deltaTime;
		Block downblock = physicsUtils.getNextBlockInDirection(x, y, z, 0, -1, 0, 2, 0.1f);
		if(downblock  != null) {
			if(y < downblock.getY() +3) {
				y = downblock.getY() +3;
				velocityY = 0;
				grounded = false;
			}
			
					}
		
	}
	public static void setCamToPlayer() {
		DisplayVariables.camX = x;
		DisplayVariables.camY = y;
		DisplayVariables.camZ = z;
		DisplayVariables.CamPitch = pitch;
		DisplayVariables.camYaw = yaw;
	}
}
