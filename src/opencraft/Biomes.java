package opencraft;

import java.lang.reflect.InvocationTargetException;

import opencraft.blocks.BlockDirt;
import opencraft.blocks.BlockGrass;
import opencraft.blocks.BlockSand;
import opencraft.structures.StructureTree;

public class Biomes {

	public static Biome plainsBiome = new Biome() {
		
		@Override
		public Block crateBlock1(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
			
			return new BlockGrass(x, y, z, chunkX, chunkZ, regionX, regionZ);
		}
		
		@Override
		public Block crateBlock2(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
			
			return new BlockDirt(x, y, z, chunkX, chunkZ, regionX, regionZ);
		}
		
		@Override
		public void checkForStruture(int x,int y, int z, float genValue) {
			// TODO Auto-generated method stub
			
		}
	};
	public static Biome MoutainBiome = new Biome() {
		
		@Override
		public Block crateBlock1(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
			
			return new BlockGrass(x, y, z, chunkX, chunkZ, regionX, regionZ);
		}
		
		@Override
		public Block crateBlock2(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
			
			return new BlockDirt(x, y, z, chunkX, chunkZ, regionX, regionZ);
		}
		
		@Override
		public void checkForStruture(int x,int y,int z, float genValue) {
			// TODO Auto-generated method stub
			
		}
	};
	public static Biome WaterBiome = new Biome() {
		
		@Override
		public Block crateBlock1(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
			
			return new BlockGrass(x, y, z, chunkX, chunkZ, regionX, regionZ);
		}
		
		@Override
		public Block crateBlock2(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
			
			return new BlockDirt(x, y, z, chunkX, chunkZ, regionX, regionZ);
		}
		
		@Override
		public void checkForStruture(int x,int y, int z, float genValue) {
			// TODO Auto-generated method stub
			
		}
	};
	public static Biome forestBiome = new Biome() {
		int placeCount = 0;
		@Override
		public Block crateBlock1(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
			
			return new BlockGrass(x, y, z, chunkX, chunkZ, regionX, regionZ);
		}
		
		@Override
		public Block crateBlock2(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
			
			return new BlockDirt(x, y, z, chunkX, chunkZ, regionX, regionZ);
		}
		
		@Override
		public void checkForStruture(int x,int y, int z, float genValue) {
			if(placeCount %50 == 0) {
				try {
					StructureTree.place(x, y+1, z);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
				
			}
			placeCount++;
			
		}
	};
	public static Biome desertBiome = new Biome() {
		
		@Override
		public Block crateBlock1(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
			
			return new BlockSand(x, y, z, chunkX, chunkZ, regionX, regionZ);
		}
		
		@Override
		public Block crateBlock2(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ) {
			
			return new BlockSand(x, y, z, chunkX, chunkZ, regionX, regionZ);
		}
		
		@Override
		public void checkForStruture(int x, int y,int z, float genValue) {
			// TODO Auto-generated method stub
			
		}
	};
		
		
		
	
	
}
