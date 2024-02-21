package opencraft.items;

import opencraft.Item;
import opencraft.World;
import opencraft.graphics.ResourceManager;
import org.newdawn.slick.opengl.Texture;
import opencraft.graphics.Vector3f;
import opencraft.graphics.models.ModelCube;
import opencraft.physics.physicsUtils;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import org.lwjgl.opengl.GL11;
public class ItemStone extends Item{

	@Override
	public void drawIcon(float x, float y, float z,float size) {
		// TODO Auto-generated method stub
		glPushMatrix();
		//glScalef(0.4f, 0.5f, 0.5f);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,((Texture) ResourceManager.getObjectForResource("Opencraft:BlockTextures")).getTextureID());
		glBegin(GL_QUADS);
		ModelCube.drawModel(x, y, z,new float[] {0.3f,0,0.3f,0,0.3f,0,0.3f,0,0.3f,0,0.3f,0},1f,1f,1f,1f,1f,1f,1f,size);
		glEnd();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		glPopMatrix();
	}

	@Override
	public void rightClickAction() {
		Vector3f b = physicsUtils.getBlockPlacePos();
		if(b != null) {
		try {
			
			World.setBlock("BlockStone", (int)b.getX(),(int)b.getY(), (int)b.getZ());
			stack--;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException | ClassNotFoundException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}
	@Override
	public void leftClickAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxStack() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
