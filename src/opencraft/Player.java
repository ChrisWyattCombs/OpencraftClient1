package opencraft;

import java.awt.RenderingHints.Key;
import java.lang.reflect.InvocationTargetException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import opencraft.graphics.DisplayUtills;
import opencraft.graphics.DisplayVariables;
import opencraft.graphics.Vector3f;
import opencraft.graphics.models.ModelPlayer;
import opencraft.items.ItemDirt;
import opencraft.items.ItemStone;
import opencraft.items.itemGrass;
import opencraft.physics.physicsUtils;

public class Player {
	public static boolean playHandSwingAnimation = false;
	private static int handXrotation = 0;
	private static float handXrotationChnage = 0.30f;
	public static float x =0;
	public static float y = 100;
	public static float z = 0;
	public static float velocityY = 0;
	public static float yaw = 0;
	public static float pitch = 0;
	public static float forwardVelocity = 0;
	public static float backwardVelocity = 0;

	public static float leftVelocity = 0;
	public static float rightVelocity = 0;
	public static boolean grounded = false;
	public static boolean qKeyPressed = false;
	public static int hotBarIndex = 0;
	public static Item[] hotbar = new Item[9];
	public static itemGrass test = new itemGrass();
	public static void updatePostitionAndRotation() {
		
		yaw+= Mouse.getDX()/4f;
		pitch += Mouse.getDY()/4f;
		if(pitch > 90) {
			pitch = 90;
		}
		if(pitch < -90) {
			pitch = -90;
		}
	
	
		boolean keyBeingHeld1 = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			keyBeingHeld1 = true;
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
		if(Keyboard.isKeyDown(Keyboard.KEY_S) && !keyBeingHeld1) {
			keyBeingHeld1 = true;
			if(forwardVelocity == 0) {
				if(backwardVelocity < 0.01f) {
					backwardVelocity+=0.00005f*DisplayVariables.deltaTime;
				}
				
			}else {
				forwardVelocity -= 0.0005f*DisplayVariables.deltaTime;
			}
		
		}else {
			if(backwardVelocity > 0) {
				backwardVelocity-=0.0001f*DisplayVariables.deltaTime;
			}
			if(backwardVelocity < 0) {
				backwardVelocity = 0;
			}
		}
		boolean keyBeingHeld = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_D) && !keyBeingHeld) {
			keyBeingHeld = true;
			if(leftVelocity == 0) {
				if(rightVelocity < 0.01f) {
					rightVelocity+=0.00005f*DisplayVariables.deltaTime;
				}
				
			}else {
				leftVelocity -= 0.0005f*DisplayVariables.deltaTime;
			}
		
		}else {
			if(rightVelocity > 0) {
				rightVelocity-=0.0001f*DisplayVariables.deltaTime;
			}
			if(rightVelocity < 0) {
				rightVelocity = 0;
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A) && !keyBeingHeld) {
			keyBeingHeld = true;
			if(rightVelocity == 0) {
				if(leftVelocity < 0.01f) {
					leftVelocity+=0.00005f*DisplayVariables.deltaTime;
				}
				
			}else {
				rightVelocity -= 0.0005f*DisplayVariables.deltaTime;
			}
		
		}else {
			if(leftVelocity > 0) {
				leftVelocity-=0.0001f*DisplayVariables.deltaTime;
			}
			if(leftVelocity < 0) {
				leftVelocity = 0;
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
		if(DisplayVariables.fps > 5) {
		x += ((forwardVelocity-backwardVelocity )*Math.sin(Math.toRadians(yaw)) * DisplayVariables.deltaTime)+((rightVelocity - leftVelocity)*Math.cos(Math.toRadians(yaw)) * DisplayVariables.deltaTime);
		z+=((forwardVelocity-backwardVelocity)  * Math.cos(Math.toRadians(yaw)) * DisplayVariables.deltaTime) - ((rightVelocity - leftVelocity)*Math.sin(Math.toRadians(yaw)) * DisplayVariables.deltaTime);
		
		y+=velocityY * DisplayVariables.deltaTime;
		}
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
			
			Block downblock = physicsUtils.getNextBlockInDirection(x, y, z, 0, -1, 0, 2, 0.01f);
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
			Block upblock = physicsUtils.getNextBlockInDirection(x, y, z, 0, 1, 0, 2, 0.01f);
			if(upblock != null) {
			 if(y > upblock.getY() - 0.3) {
				 y = upblock.getY() - 0.4f;
				 velocityY = -velocityY;
			 }
			}
	}
	public static void checkForActions() throws InstantiationException, IllegalAccessException {  		
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			if(!qKeyPressed) {
			if(hotbar[hotBarIndex] != null) {
				Item item = hotbar[hotBarIndex].getClass().newInstance();
				item.x = x;
				item.y = y+4;
				item.z = z;
				
				item.vx = (float)(Math.sin(Math.toRadians(yaw))*0.01f);
				item.vy = 0.010f;
				item.vz = (float)(Math.cos(Math.toRadians(yaw))*0.01f);
				
				item.stack = 1;
				World.items.add(item);
				 
				
				hotbar[hotBarIndex].stack--;
				qKeyPressed=true;
				}
			}
		}else {
			qKeyPressed = false;
		}
	}
	public static void drawPlayerHUD() {
		GL11.glPushMatrix();
		GL11.glTranslatef(0.4f, -0.3f, -0.5f);
		GL11.glRotatef(180.0f+handXrotation , 0, 1, 0);
		GL11.glRotatef(-90, 1, 0, 0);
		GL11.glBegin(GL11.GL_QUADS);
		ModelPlayer.drawArm(0, 0, 0);
		GL11.glEnd();
		GL11.glPopMatrix();
		System.out.println("hrc:"+handXrotationChnage);
		if(playHandSwingAnimation) {
			handXrotation += handXrotationChnage*DisplayVariables.deltaTime;
			if(handXrotation > 25) {
				handXrotation = 25;
				handXrotationChnage*=-1f;
			}
			else if(handXrotation < 0) {
				handXrotationChnage*=-1;
				handXrotation=0;
				playHandSwingAnimation = false;
			}
		}
		float x = -0.005f;
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
		if(hotbar[i]!= null) {
		if(hotbar[i].stack == 0) {
			hotbar[i] = null;
		}
		}
		if(hotBarIndex == i) {
			if(hotbar[i] != null) {
			hotbar[i].drawIcon(0.35f, -0.13f, 1f,0.1f);
			}
			GL11.glColor3f(0, 0, 0);
		}
		drawHotbarSquare(x, -0.008f);
		if(hotbar[i] != null) {
			hotbar[i].drawIcon(x, -0.008f,0.02f,0.0004f);
			
		}
		if(hotbar[i] != null) {
			DisplayUtills.font.drawText(String.valueOf(hotbar[i].stack), x-0.0004f,-0.0079f,0.0000075f );
		}else {
		DisplayUtills.font.drawText("0", x-0.0004f,-0.0079f,0.0000075f  );
		}
		x+=0.01f/9;
		GL11.glColor3f(1, 1, 1);
	}
	
	}
public static void leftHandAction() {
	if(!Player.playHandSwingAnimation) {
		Player.playHandSwingAnimation = true;
	Block b = physicsUtils.getBlockLookingAt();
	if(b != null) {
	World.items.add(b.getDrop());
	try {
		
		World.setBlock("air", (int)b.getGlobalX(),(int)b.getY(), (int)b.getGlobalZ());
	
	} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
			| SecurityException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	}


}
public static void rightHandAction() {
	
}
public static boolean addItemToHotbar(Item item) {
	for(int i = 0; i < hotbar.length; i++) {
		if(hotbar[i] != null){
		if(hotbar[i].getID() == item.getID() && hotbar[i].stack < hotbar[i].getMaxStack()) {
			hotbar[i].stack++;
			return true;
		}
		}
	}
	for(int i = 0; i < hotbar.length; i++) {
		if(hotbar[i] == null) {
			hotbar[i] = item;
			return true;
		}
	}
	return false;
	
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
