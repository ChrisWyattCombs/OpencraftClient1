package opencraft.blocks;

import opencraft.Block;
import opencraft.graphics.models.ModelCube;

public class BlockStone extends Block {

	
	
	public BlockStone(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
		super(x, y, z, chunkX, chunkZ, regionX, regionZ);
		
	}

	@Override
	public void draw() {
		ModelCube.drawModel(getGlobalX(), getY(), getGlobalZ(),new float[] {0.3f,0,0.3f,0,0.3f,0,0.3f,0,0.3f,0,0.3f,0});
	}

}
