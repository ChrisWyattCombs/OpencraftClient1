package opencraft.blocks;

import opencraft.Block;
import opencraft.Item;
import opencraft.graphics.models.ModelCube;
import opencraft.items.ItemGrass;

public class BlockLeaf extends Block {

	
	
	public BlockLeaf(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
		super(x, y, z, chunkX, chunkZ, regionX, regionZ);
		
	}

	@Override
	public void draw(boolean top,boolean bottom,boolean front,boolean back,boolean right, boolean left) {
		visible = ModelCube.drawModel(getGlobalX(), getY(), getGlobalZ(),new float[] {0.6f,0,0.6f,0,0.6f,0,0.6f,0,0.6f,0,0.6f,0},topLight, bottomLight, frontLight, backLight, rightLight, leftLight,1,top,bottom,front,back,right,left);
	}

	@Override
	public int getID() {
		return 5;
	}

	@Override
	public Item getDrop() {
		// TODO Auto-generated method stub
		ItemGrass drop = new ItemGrass();
		drop.x = getGlobalX();
		drop.y = getY() + 3f;
		drop.z = getGlobalZ();
		return  drop;
	}

	@Override
	public boolean isFluid() {
		// TODO Auto-generated method stub
		return false;
	}

}
