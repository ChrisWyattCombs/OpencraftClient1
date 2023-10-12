package opencraft.blocks;

import opencraft.Block;
import opencraft.graphics.models.ModelCube;

public class BlockDirt extends Block {

	
	
	public BlockDirt(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
		super(x, y, z, chunkX, chunkZ, regionX, regionZ);
		
	}

	@Override
	public void draw() {
		ModelCube.drawModel(getGlobalX(), getY(), getGlobalZ(),new float[] {0.2f,0,0.2f,0,0.2f,0,0.2f,0,0.2f,0,0.2f,0},topLight, bottomLight, frontLight, backLight, rightLight, leftLight);
	}

	@Override
	public int getID() {
	
		return 2;
	}

}
