package opencraft.blocks;

import opencraft.Block;
import opencraft.Item;
import opencraft.graphics.models.ModelCube;
import static org.lwjgl.opengl.GL11.*;
public class BlockWater extends Block {
	
	public BlockWater(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
		super(x, y, z, chunkX, chunkZ, regionX, regionZ);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(boolean top,boolean bottom,boolean front,boolean back,boolean right, boolean left) {
		//glDepthMask(false);
		visible = ModelCube.drawModel(getGlobalX(), getY(), getGlobalZ(),new float[] {0.5f,0,0.5f,0,0.5f,0,0.5f,0,0.5f,0,0.5f,0},topLight, bottomLight, frontLight, backLight, rightLight, leftLight,0.8f,top,bottom,front,back,right,left,height);
		//glDepthMask(true);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public boolean isFluid() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Item getDrop() {
		// TODO Auto-generated method stub
		return null;
	}

}
