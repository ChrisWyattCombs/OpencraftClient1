package opencraft.items;

import opencraft.Item;
import opencraft.World;
import opencraft.graphics.ResourceManager;
import org.newdawn.slick.opengl.Texture;
import opencraft.graphics.Vector3f;
import opencraft.graphics.models.ModelCube;
import opencraft.physics.physicsUtils;

import static org.lwjgl.opengl.GL11.*;

import java.lang.reflect.InvocationTargetException;

import org.lwjgl.opengl.GL11;
public class ItemGrass extends Item{

	@Override
	public void drawIcon(float x, float y, float z,float size) {
		// TODO Auto-generated method stub
		
		//glScalef(0.4f, 0.5f, 0.5f);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ((Texture) ResourceManager.getObjectForResource("Opencraft:BlockTextures")).getTextureID());
		glBegin(GL_QUADS);
		ModelCube.drawModel(x, y, z,new float[] {0,0,0.2f,0,0.1f,0,0.1f,0,0.1f,0,0.1f,0},1f,1f,1f,1f,1f,1f,1f,size);
		glEnd();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
	}

	@Override
	public void rightClickAction() {
		Vector3f b = physicsUtils.getBlockPlacePos();
		if(b != null) {
		try {
			
			World.setBlock("BlockGrass", (int)b.getX(),(int)b.getY(), (int)b.getZ());
			//World.uncheckedFluids.add(new Vector3f((int)b.getX(),(int)b.getY(), (int)b.getZ()));
			stack--;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	}


	@Override
	public int getMaxStack() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

}
