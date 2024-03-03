package opencraft;

import java.io.IOException;

import opencraft.graphics.DisplayVariables;
import opencraft.network.NetworkUtills;
import opencraft.physics.physicsUtils;

public abstract class Item {

	
	
	public int stack = 1;
	public int serverEntityID = -1;
	public float x = 0, y = 0, z = 0;
	public float vx = 0, vy = 0, vz = 0;
	public boolean grounded;
	public abstract void drawIcon(float x, float y, float z,float size);
	
	public abstract void rightClickAction();
	
	public void leftClickAction() {
		Player.leftClickAction();
	}
	
	public abstract int getMaxStack();
	
	public abstract int getID();

	public void updatePosition() {
		if(World.server) {
		/*
		NetworkUtills.send(2);
		NetworkUtills.send(serverEntityID);
		
		try {
			int code = NetworkUtills.readInt();
			if(code == 0) {
			x = NetworkUtills.readFloat();
			y = NetworkUtills.readFloat();
			z = NetworkUtills.readFloat();
			}else if(code == 1) {
				World.items.remove(this);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		}else {
		if(!grounded && vy > -0.12f) {
			vy -= 0.00003f*DisplayVariables.deltaTime;
		}
		float lastX =x;
		float lastZ =z;
		
		x+=vx*DisplayVariables.deltaTime;
		y+=vy*DisplayVariables.deltaTime;
		z+=vz*DisplayVariables.deltaTime;
		Block block1 = physicsUtils.getNextBlockInDirection(x+0.25f, y, z, 0, -1, 0, 2, 0.1f);
		Block block2 = physicsUtils.getNextBlockInDirection(x, y, z+0.25f, 0, -1, 0, 2, 0.1f);
		Block block3 = physicsUtils.getNextBlockInDirection(x-0.25f, y, z, 0, -1, 0, 2, 0.1f);
		Block block4 = physicsUtils.getNextBlockInDirection(x, y, z-0.25f, 0, -1, 0, 2, 0.1f);
		Block block5 = physicsUtils.getNextBlockInDirection(x+0.25f, y, z+0.25f, 0, -1, 0, 2, 0.1f);
		Block block6 = physicsUtils.getNextBlockInDirection(x-0.25f, y, z+0.25f, 0, -1, 0, 2, 0.1f);
		Block block7 = physicsUtils.getNextBlockInDirection(x-0.25f, y, z-0.25f, 0, -1, 0, 2, 0.1f);
		Block block8 = physicsUtils.getNextBlockInDirection(x+0.25f, y, z-0.25f, 0, -1, 0, 2, 0.1f);
			
			if(block1  != null) {	
				if(y < block1.getY() +1.2f) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block2  != null) {	
				if(y < block2.getY() +1.2f) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block3  != null) {	
				if(y < block3.getY() +1.2f) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block4  != null) {	
				if(y < block4.getY() +1.2f) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block5  != null) {	
				if(y < block5.getY() +1.2f) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block6  != null) {	
				if(y < block6.getY() +1.2f) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block7  != null) {	
				if(y < block7.getY() +1.2f) {
					x = lastX;
					z = lastZ;
				}
			}
			if(block8  != null) {	
				if(y < block8.getY() +1.2f) {
					x = lastX;
					z = lastZ;
				}
			}
			
			Block downblock = physicsUtils.getNextBlockInDirection(x, y, z, 0, -1, 0, 2, 0.1f);
			if(downblock  != null) {
				
				if(y < downblock.getY() +1.2f) {
					y = downblock.getY() +1.2f;
					vx =0;
					vy = 0;
					vz = 0;
				
				}
				if(y == downblock.getY() +1.2f) {
					
				     
					grounded = true;
				}else {
					grounded = false;
					
				}
				
						}else {
							grounded = false;
						}
			Block upblock = physicsUtils.getNextBlockInDirection(x, y, z, 0, 1, 0, 2, 0.1f);
			if(upblock != null) {
			 if(y > upblock.getY() - 1.2f) {
				 y = upblock.getY() - 1.2f;
				 vy = -vy;
			 }
			}
			if(Math.sqrt(Math.pow(x-Player.x, 2)+Math.pow(y-Player.y, 2)+Math.pow(z-Player.z, 2)) < 3f) {
				if(Player.addItemToInventory(this)) {
				World.items.remove(this);
				
				}
			}
	}
	}

	
}
