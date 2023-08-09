package opencraft;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class World {
	public static int[] chunkDrawIDs = new int[257];
	public static Texture blockTextures;
	public static int realRegionListLength = 0;
	public static Region[] regions = new Region[16];
	public static double worldLoadProgress = 0;
	public static int lastID = 0;
	public static boolean firstTime = true;
	public static int lastRegionX = 0;
	public static int lastRegionZ = 0;
	public static int currentRegionIndex = -1;
	public static void loadWorld() {
		for(int x = -8; x < 8; x++) {
			for(int z = -8; z <8 ; z++) {
				try {
				
				loadChunk(x,z);
				}catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
				worldLoadProgress = (float)(x+8f)/16.00f + (float)(z+8f)/(16*16);
			}
		}
		worldLoadProgress = 1;
	}
	public static void setupWorld() {
		for(int i = 0; i < realRegionListLength; i++) {
			if(regions[i] != null) {
			for(int x = 0; x < 16; x++) {
				for(int z = 0; z < 16; z++) {
					if(regions[i].chunks[x][z] != null) {
					regions[i].chunks[x][z].setup();
					
					}
				}
				}
			}
		}
	}
	public static void drawWorld() {
		
		for(int i = 0; i < realRegionListLength; i++) {
			regions[i].draw();
			}
		
	}
	public static void loadChunk(int cx, int cz) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		int regionX = cx >> 4;
		int regionZ = cz >> 4;

		 int x = cx -(regionX * 16);
		 int z = cz-(regionZ * 16);
		//Region region = null;
		int regionIndex = getRegionIndex(regionX, regionZ);
		if(regionIndex == -1) {
			regionIndex = realRegionListLength;
			regions[realRegionListLength] = new Region(regionX, regionZ, new Chunk[16][16]);
			realRegionListLength++;
		}
		
		
		if(regions[regionIndex].chunks[x][z]==null) {
			regions[regionIndex].chunks[x][z] = new Chunk(x, z, regionX, regionZ);
			
		}
		
			regions[regionIndex].chunks[x][z].load();
	
	
	}
	public static Block getBlock(int x, int y, int z) {
		
		int chunkX = x >> 4;
		int chunkZ = z >> 4;
		
			int regionX = chunkX >> 4;
			int regionZ = chunkZ >> 4;
			int localCX =chunkX-(regionX*16);
			int localCZ =chunkZ-(regionZ*16);
			int localX = x - (localCX*16);
			int localZ = z - (localCZ*16);
			if(x < 0) {
				localX += 256;
			}
			if(z < 0) {
				localZ += 256;
			}
			System.out.println("X: "+localX);
		try {
			
			return regions[getRegionIndex(regionX, regionZ)].chunks[chunkX-(regionX*16)][chunkZ-(regionZ*16)].blocks[localX][y][localZ];
		}catch( Exception e){
			e.printStackTrace();
			
		}
		return null;
	}

	public static void setupChunk(int cx, int cz) {
		int regionX = cx >> 4;
		int regionZ = cz >> 4;
		 int x = cx -(regionX * 16);
		 int z = cz-(regionZ * 16);
		Region region = null;
		
			int regionIndex = getRegionIndex(regionX, regionZ);
			
		System.out.println("x "+ x);
		System.out.println("z "+ z);
		System.out.println("ri "+ regionIndex);
		regions[regionIndex].chunks[x][z].setup();
		
		
		
	}
	public static int getRegionIndex(int x, int z) {
		for(int i = 0; i < regions.length; i++) {
			if(regions[i] != null) {
			if(regions[i].getX() == x && regions[i].getZ() == z ) {
				return i;
			}
			}
		}
		return -1;
	}
}
