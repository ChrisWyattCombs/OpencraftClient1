package opencraft.entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import opencraft.Block;
import opencraft.Entity;
import opencraft.Player;
import opencraft.World;
import opencraft.graphics.DisplayVariables;
import opencraft.graphics.ResourceManager;
import opencraft.graphics.models.ModelPlayer;
import opencraft.physics.physicsUtils;

public class EntityZombie extends Entity {

	public boolean beingHit = false;
	public long beingHitStartTime = 0;
	private boolean grounded = true;
	public float leftVelocity = 0;
	public float rightVelocity = 0;
	public float forwardVelocity = 0;
	public float backwardVelocity = 0;
	
	public EntityZombie(float x, float y, float z) {
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getMaxHealth() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public void update() {
		if(health <= 0) {
			World.entities.remove(this);
		}
		if(!grounded && ( World.getBlock(physicsUtils.convertFloatCoordToBlockCoord(x), physicsUtils.convertFloatCoordToBlockCoord(y-1), physicsUtils.convertFloatCoordToBlockCoord(z))==null || !World.getBlock(physicsUtils.convertFloatCoordToBlockCoord(x), physicsUtils.convertFloatCoordToBlockCoord(y-1), physicsUtils.convertFloatCoordToBlockCoord(z)).isFluid())) {
			velocityY -= 0.00003f*DisplayVariables.deltaTime;
			//SpeedMultiplyer =0.5f;
		}else if(( World.getBlock(physicsUtils.convertFloatCoordToBlockCoord(x), physicsUtils.convertFloatCoordToBlockCoord(y-1), physicsUtils.convertFloatCoordToBlockCoord(z))!=null && World.getBlock(physicsUtils.convertFloatCoordToBlockCoord(x), physicsUtils.convertFloatCoordToBlockCoord(y-1), physicsUtils.convertFloatCoordToBlockCoord(z)).isFluid())){
			SpeedMultiplyer =0.75f;
			if(velocityY > -0.01f) {
			float lastV = velocityY;
			velocityY -= 0.00003f*DisplayVariables.deltaTime;
			if(velocityY < -0.01f) {
				velocityY = lastV;
			}
			}else {
				velocityY += 0.001f*DisplayVariables.deltaTime;
			}
		}
		
		
		float lastX =x;
		float lastZ =z;
		int chunkX = physicsUtils.convertFloatCoordToBlockCoord(x) >> 4;
		int chunkZ = physicsUtils.convertFloatCoordToBlockCoord(z) >> 4;
		if(DisplayVariables.fps > 5 && World.getChunk(chunkX, chunkZ) != null && World.getChunk(chunkX, chunkZ).fullyLoaded ) {
		x += ((forwardVelocity-backwardVelocity )*Math.sin(Math.toRadians(yaw)) * DisplayVariables.deltaTime)+((rightVelocity - leftVelocity)*Math.cos(Math.toRadians(yaw)) * DisplayVariables.deltaTime);
		z-=((forwardVelocity-backwardVelocity)  * Math.cos(Math.toRadians(yaw)) * DisplayVariables.deltaTime) - ((rightVelocity - leftVelocity)*Math.sin(Math.toRadians(yaw)) * DisplayVariables.deltaTime);
		
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
					float temp = forwardVelocity;
					forwardVelocity = backwardVelocity;
					backwardVelocity = temp;
					temp = rightVelocity;
					rightVelocity  = leftVelocity;
					leftVelocity = temp;
				}
			}
			if(block2  != null) {	
				if(y < block2.getY() +2.5) {
					x = lastX;
					z = lastZ;
					float temp = forwardVelocity;
					forwardVelocity = backwardVelocity;
					backwardVelocity = temp;
					temp = rightVelocity;
					rightVelocity  = leftVelocity;
					leftVelocity = temp;
				}
			}
			if(block3  != null) {	
				if(y < block3.getY() +2.5) {
					x = lastX;
					z = lastZ;
					float temp = forwardVelocity;
					forwardVelocity = backwardVelocity;
					backwardVelocity = temp;
					temp = rightVelocity;
					rightVelocity  = leftVelocity;
					leftVelocity = temp;
				}
			}
			if(block4  != null) {	
				if(y < block4.getY() +2.5) {
					x = lastX;
					z = lastZ;
					float temp = forwardVelocity;
					forwardVelocity = backwardVelocity;
					backwardVelocity = temp;
					temp = rightVelocity;
					rightVelocity  = leftVelocity;
					leftVelocity = temp;
				}
			}
			if(block5  != null) {	
				if(y < block5.getY() +2.5) {
					x = lastX;
					z = lastZ;
					float temp = forwardVelocity;
					forwardVelocity = backwardVelocity;
					backwardVelocity = temp;
					temp = rightVelocity;
					rightVelocity  = leftVelocity;
					leftVelocity = temp;
				}
			}
			if(block6  != null) {	
				if(y < block6.getY() +2.5) {
					x = lastX;
					z = lastZ;
					float temp = forwardVelocity;
					forwardVelocity = backwardVelocity;
					backwardVelocity = temp;
					temp = rightVelocity;
					rightVelocity  = leftVelocity;
					leftVelocity = temp;
				}
			}
			if(block7  != null) {	
				if(y < block7.getY() +2.5) {
					x = lastX;
					z = lastZ;
					float temp = forwardVelocity;
					forwardVelocity = backwardVelocity;
					backwardVelocity = temp;
					temp = rightVelocity;
					rightVelocity  = leftVelocity;
					leftVelocity = temp;
				}
			}
			if(block8  != null) {	
				if(y < block8.getY() +2.5) {
					x = lastX;
					z = lastZ;
					float temp = forwardVelocity;
					forwardVelocity = backwardVelocity;
					backwardVelocity = temp;
					temp = rightVelocity;
					rightVelocity  = leftVelocity;
					leftVelocity = temp;
				}
			}
			
			Block downblock = physicsUtils.getNextBlockInDirection(x, y, z, 0, -1, 0, 2, 0.01f);
			if(downblock  != null) {
				float lvy = velocityY;
				if(y < downblock.getY() +3) {
					y = downblock.getY() +3;
					
					velocityY = 0;
					if(( World.getBlock(physicsUtils.convertFloatCoordToBlockCoord(x), physicsUtils.convertFloatCoordToBlockCoord(y-1), physicsUtils.convertFloatCoordToBlockCoord(z))==null || !World.getBlock(physicsUtils.convertFloatCoordToBlockCoord(x), physicsUtils.convertFloatCoordToBlockCoord(y-1), physicsUtils.convertFloatCoordToBlockCoord(z)).isFluid())){
					SpeedMultiplyer =1f;
					
					}
					
				}
				if(y == downblock.getY() +3) {
					
					if(lvy < -0.02f) {
				    	 health -= 4*Math.pow(17f * lvy,2);
				     }
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
				 velocityY *=-1;
			 }
			}
		
	}

	@Override
	public void draw() {
		if(System.currentTimeMillis() - beingHitStartTime > 500) {
			beingHit = false;
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ((Texture) ResourceManager.getObjectForResource("Opencraft:SkinTexture")).getTextureID());
		
		ModelPlayer.drawModel(x, y-1, z,-yaw,0,0,pitch,0,25,0,25,0,0,0,-0,true,null,beingHit);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}

	@Override
	public boolean pointInHitRadius(float x, float y, float z) {
		
		return Math.abs(x-this.x) < 0.2f && y-this.y > -2.5f && y-this.y < 0 && Math.abs(z-this.z)< 0.2f;
		
	}

	@Override
	public void dealDamage(float damage) {
		health-=damage;
		beingHit = true;
		beingHitStartTime = System.currentTimeMillis();
		
	}
	
	

}
