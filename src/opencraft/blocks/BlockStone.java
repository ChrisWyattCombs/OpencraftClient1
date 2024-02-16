package opencraft.blocks;

import opencraft.Block;
import opencraft.Item;
import opencraft.graphics.models.ModelCube;
import opencraft.items.ItemStone;
import opencraft.items.itemGrass;

public class BlockStone extends Block {

	
	
	public BlockStone(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
		super(x, y, z, chunkX, chunkZ, regionX, regionZ);
		
	}

	@Override
	public void draw(boolean top,boolean bottom,boolean front,boolean back,boolean right, boolean left) {
		visible =  ModelCube.drawModel(getGlobalX(), getY(), getGlobalZ(),new float[] {0.3f,0,0.3f,0,0.3f,0,0.3f,0,0.3f,0,0.3f,0},topLight, bottomLight, frontLight, backLight, rightLight, leftLight,1f,top,bottom,front,back,right,left);
	}
	public int getID() {
		
		return 3;
	}

	@Override
	public Item getDrop() {
		ItemStone drop = new ItemStone();
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
