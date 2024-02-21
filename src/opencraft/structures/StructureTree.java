package opencraft.structures;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;

import opencraft.World;

public class StructureTree {

	public static void place(int centerX, int bottomY,int centerZ) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException, FileNotFoundException {
		for(int y = 0; y < 5; y++) {
			World.setBlock("BlockWood", centerX, bottomY+y, centerZ,false);
		}
		for(int y = 0; y <= 3; y++) {
			for(int x = -2; x <= 2; x++) {
				for(int z = -2; z <= 2; z++) {
					World.setBlock("BlockLeaf", centerX+x, bottomY+5+y, centerZ+z,false);
				}
				
			}
			
		}
		
		
	}

}
