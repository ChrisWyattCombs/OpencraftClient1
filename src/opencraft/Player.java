package opencraft;

import java.awt.RenderingHints.Key;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import opencraft.graphics.DisplayUtills;
import opencraft.graphics.DisplayVariables;
import opencraft.physics.physicsUtils;

public class Player {
	public static float x = -265;
	public static float y = 150;
	public static float z = -725;
	public static float velocityY = 0;
	public static float yaw = 0;
	public static float pitch = 0;
	public static float forwardVelocity = 0;
	public static float backwardVelocity = 0;

	public static float leftVelocity = 0;
	public static float rightVelocity = 0;
	public static boolean grounded = false;
	public static int hotBarIndex = 0;
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
					forwardVelocity+=0.00005f*DisplayVariables.deltaTime;
				}
				
			}else {
				backwardVelocity -= 0.0005f*DisplayVariables.deltaTime;
			}
		
		}else {
			if(forwardVelocity > 0) {
				forwardVelocity-=0.0001f*DisplayVariables.deltaTime;
			}
			if(forwardVelocity < 0) {
				forwardVelocity = 0;
			}
		}
		
		if(!grounded && velocityY > -0.12f) {
			velocityY -= 0.00003f*DisplayVariables.deltaTime;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && grounded){
			velocityY = 0.010f;
			
		}
		float lastX =x;
		float lastZ =z;
		x += (forwardVelocity )*Math.sin(Math.toRadians(yaw)) * DisplayVariables.deltaTime;
		z+=(forwardVelocity)  * Math.cos(Math.toRadians(yaw)) * DisplayVariables.deltaTime;
		
		y+=velocityY * DisplayVariables.deltaTime;
		//z+=(rightVelocity)  * Math.cos(Math.toRadians(yaw)) * DisplayVariables.deltaTime;
		
		
		//Block block2 = physicsUtils.getNextBlockInDirection(x, y-1, z, 0, 0, -1, 2, 0.001f);
		Block block1 = physicsUtils.getNextBlockInDirection(x+0.25f, y, z, 0, -1, 0, 2, 0.1f);
		Block block2 = physicsUtils.getNextBlockInDirection(x, y, z+0.25f, 0, -1, 0, 2, 0.1f);
		Block block3 = physicsUtils.getNextBlockInDirection(x-0.25f, y, z, 0, -1, 0, 2, 0.1f);
		Block block4 = physicsUtils.getNextBlockInDirection(x, y, z-0.25f, 0, -1, 0, 2, 0.1f);
		Block block5 = physicsUtils.getNextBlockInDirection(x+0.25f, y, z+0.25f, 0, -1, 0, 2, 0.1f);
		Block block6 = physicsUtils.getNextBlockInDirection(x-0.25f, y, z+0.25f, 0, -1, 0, 2, 0.1f);
		Block block7 = physicsUtils.getNextBlockInDirection(x-0.25f, y, z-0.25f, 0, -1, 0, 2, 0.1f);
		Block block8 = physicsUtils.getNextBlockInDirection(x+0.25f, y, z-0.25f, 0, -1, 0, 2, 0.1f);
			
			if(block1  != null) {	
				if(y < block1.getY() +2.5) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block2  != null) {	
				if(y < block2.getY() +2.5) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block3  != null) {	
				if(y < block3.getY() +2.5) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block4  != null) {	
				if(y < block4.getY() +2.5) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block5  != null) {	
				if(y < block5.getY() +2.5) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block6  != null) {	
				if(y < block6.getY() +2.5) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block7  != null) {	
				if(y < block7.getY() +2.5) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block8  != null) {	
				if(y < block8.getY() +2.5) {
					x = lastX;
					z = lastZ;
				}
			}
			
			Block downblock = physicsUtils.getNextBlockInDirection(x, y, z, 0, -1, 0, 2, 0.1f);
			if(downblock  != null) {
				
				if(y < downblock.getY() +3) {
					y = downblock.getY() +3;
					velocityY = 0;
				
				}
				if(y == downblock.getY() +3) {
					
				     
					grounded = true;
				}else {
					grounded = false;
				}
				
						}else {
							grounded = false;
						}
			Block upblock = physicsUtils.getNextBlockInDirection(x, y, z, 0, 1, 0, 2, 0.1f);
			if(upblock != null) {
			 if(y > upblock.getY() - 0.3) {
				 y = upblock.getY() - 0.4f;
				 velocityY = -velocityY;
			 }
			}
	}
	public static void checkForActions() {  		
	}
	public static void drawPlayerHUD() {
		float x = -0.008f;
	int scroll = Mouse.getDWheel();
	if(scroll> 0) {
	hotBarIndex -= 1;
	
	}else if(scroll < 0) {
		hotBarIndex += 1;
		
		}
	
	if(hotBarIndex > 8) {
		hotBarIndex = 0;
	}else if(hotBarIndex < 0) {
		hotBarIndex = 8;
	}
	for(int i = 0; i < 9; i++) {
		if(hotBarIndex == i) {
			GL11.glColor3f(0, 0, 0);
		}
		drawHotbarSquare(x, -0.008f);
		x+=0.016f/9;
		GL11.glColor3f(1, 1, 1);
	}
	
	}
public static void drawHotbarSquare(float x, float y) {
	DisplayUtills.drawSqaure(0.1f, 0.01f, x, y+(0.005f *0.1f), -0.02f);
	DisplayUtills.drawSqaure( 0.01f, 0.11f, x+(0.005f *0.1f), y, -0.02f);
	DisplayUtills.drawSqaure( 0.01f, 0.11f, x-(0.005f *0.1f), y, -0.02f);
	DisplayUtills.drawSqaure(0.1f, 0.01f, x, y-(0.005f *0.1f), -0.02f);
	}
	public static void setCamToPlayer() {
		DisplayVariables.camX = x;
		DisplayVariables.camY = y;
		DisplayVariables.camZ = z;
		DisplayVariables.CamPitch = pitch;
		DisplayVariables.camYaw = yaw;
	}
}
