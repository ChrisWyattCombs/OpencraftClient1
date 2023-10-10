package opencraft;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import opencraft.graphics.DisplayVariables;
import opencraft.graphics.Vector2i;
import opencraft.graphics.Vector3f;
import opencraft.physics.physicsUtils;


public class World {
	public static int[] chunkDrawIDs = new int[257];
	public static Texture blockTextures;
	public static int realRegionListLength = 0;
	public static Region[] regions = new Region[256];
	public static double worldLoadProgress = 0;
	public static int lastID = 0;
	public static boolean firstTime = true;
	public static int lastRegionX = 0;
	public static int lastRegionZ = 0;
	public static float x;
	public static float z;
	
	public static int renderDistance = 16;
	public static ArrayList<Vector2i> chunksToSetup = null;
	private static  int setupIndex= 0;
	public static boolean rendering = false;
	public static int currentRegionIndex = -1;
	public static boolean loadingChunks = false;
	public static void loadWorld() {
		for(int x = -renderDistance; x < renderDistance; x++) {
			for(int z = -renderDistance; z <renderDistance ; z++) {
				try {
				
				loadChunk(x,z);
				}catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
				worldLoadProgress = (float)(x+renderDistance)/(renderDistance *2) + (float)(z+renderDistance)/((renderDistance *2)*(renderDistance *2));
			}
		}
		calculateLighting();
		worldLoadProgress = 1;
	}
	
	public static Chunk getChunk(int cx, int cz) {
		int regionX = cx >> 4;
		int regionZ = cz >> 4;

		 int x = cx -(regionX * 16);
		 int z = cz-(regionZ * 16);
		 try {
		 return regions[getRegionIndex(regionX, regionZ)].chunks[x][z];
		 }catch(Exception e) {
			 return null;
		 }
		
	}
	public static void setupWorld() {
		ArrayList<Vector2i> chunks = new ArrayList<>();
		for(int i = 0; i < realRegionListLength; i++) {
			if(regions[i] != null) {
			for(int x = 0; x < 16; x++) {
				for(int z = 0; z < 16; z++) {
					if(regions[i].chunks[x][z] != null) {
					 chunks.add(new Vector2i( regions[i].chunks[x][z].getGlobalX(), regions[i].chunks[x][z].getGlobalZ()));
					
					}
				}
				}
			}
		}
		chunksToSetup = chunks;
	}
	public static void drawWorld() {
		if(Math.sqrt(Math.pow(DisplayVariables.camX-x, 2)+Math.pow(-DisplayVariables.camZ-z, 2))>(16*2)/2) {
			x = DisplayVariables.camX;
			System.out.println("works23");
			z = -DisplayVariables.camZ;
			System.out.println();
			ArrayList<Vector2i> chunks = new ArrayList<>();
			int chunkX =(int)x >> 4;
			int chunkZ =(int)z >> 4;
			for(int i = 0; i <World.realRegionListLength; i++) {
				boolean allNull = true;
				
				for(int cx = 0; cx<16; cx++) {
					for(int cz = 0; cz<16; cz++) {
						if(World.regions[i].chunks[cx][cz] != null) {
							if(Math.abs(World.regions[i].chunks[cx][cz].getGlobalX() -chunkX)> renderDistance || Math.abs(World.regions[i].chunks[cx][cz].getGlobalZ() -chunkZ)> renderDistance) {
								World.regions[i].chunks[cx][cz].delete();
								World.regions[i].chunks[cx][cz] = null;
								
							}else {
								allNull = false;
							}
						}
						
					}
				}
				if(allNull) {
					World.regions[i] = null;
					
					
				}
				System.out.println("cou");
				
				}
			Region[] newRegions = new Region[256];
			int index = 0;
			for(int i = 0; i < World.realRegionListLength; i++) {
				if(regions[i] != null) {
				newRegions[index] = regions[i];
				index++;
				}
			}
			realRegionListLength = index;
			regions = newRegions;
			System.out.println("works23");
			
			new Thread() {
				public void run() {
					loadingChunks = true;
					for(int cx = (int)chunkX -renderDistance;cx <(int)chunkX+renderDistance;cx++) {
						
						for(int cz = (int)chunkZ -renderDistance; cz <(int)chunkZ+renderDistance;cz++) {
						if(World.getChunk(cx, cz) == null) {
							
							try {
								World.loadChunk(cx, cz);
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							chunks.add(new Vector2i(cx, cz));
							
						}
						
						System.out.println(cz);
						}
					}
					//calculateLighting();
					for(Vector2i chunkPostion : chunks) {
						lightChunk(chunkPostion.getX(), chunkPostion.getY());
					}
					if(chunks.size() > 0 && chunksToSetup == null) {
					World.chunksToSetup = chunks;
					}else if(chunks.size() > 0 ) {
						World.chunksToSetup.addAll(chunks);
					}
					
					loadingChunks = false;
				}
			}.start();
		}
	

		if(chunksToSetup != null && !loadingChunks) {
			System.out.println("works 500 " + chunksToSetup.size());
			Vector2i chunkPosition = chunksToSetup.get(setupIndex);
			
			setupChunk(chunkPosition.getX(), chunkPosition.getY());
			setupIndex++;
			if(setupIndex == chunksToSetup.size()) {
				chunksToSetup = null;
				setupIndex=0;
			}
			
			
		}
		rendering = true;
		for(int i = 0; i < realRegionListLength; i++) {
			regions[i].draw();
			}
		rendering = false;
		
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
			
			//if(localCX < 0) {
				//localCX += 256;
			//}
			//if(localCZ < 0) {
				//localCZ += 256;
			//}
			int localX = x  & 0xF;
			int localZ = z  & 0xF;
			
			
			try {
			return regions[getRegionIndex(regionX, regionZ)].chunks[chunkX-(regionX*16)][chunkZ-(regionZ*16)].blocks[localX][y][localZ];
		}catch( Exception e){
			//e.printStackTrace();
			//System.out.println("a2");
			
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
			
	
		try {
		regions[regionIndex].chunks[x][z].setup();
		}catch(Exception e) {
			
		}
		
		
		
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
	public static void unloadChunk(int cx, int cz) {
		int regionX = cx >> 4;
		int regionZ = cz >> 4;

		 int x = cx -(regionX * 16);
		 int z = cz-(regionZ * 16);
		 
		 
	}
	public static void setNextBlocks() {
		for(int i = 0; i <World.realRegionListLength; i++) {
		for(int cx = 0; cx<16; cx++) {
			for(int cz = 0; cz<16; cz++) {
				if(World.regions[i].chunks[cx][cz] != null) {
				for(int x = 0; x<16; x++) {
					for(int y = 0; y<256; y++) {
						for(int z = 0; z<16; z++) {
							
						}
					}
						}
				}
				}
				}
			}
				}
	
	public static void calculateLighting() {
		for(int i = 0; i <World.realRegionListLength; i++) {
			
			
			for(int cx = 0; cx<16; cx++) {
				for(int cz = 0; cz<16; cz++) {
					if(World.regions[i].chunks[cx][cz] != null) {
						World.regions[i].chunks[cx][cz].calculateLighting();
					
					}
				}
			}
		}
	}
	public static void lightChunk(int cx, int cz) {
		int regionX = cx >> 4;
		int regionZ = cz >> 4;
		 int x = cx -(regionX * 16);
		 int z = cz-(regionZ * 16);
		Region region = null;
		
			int regionIndex = getRegionIndex(regionX, regionZ);
			
	
		try {
		regions[regionIndex].chunks[x][z].calculateLighting();;
		}catch(Exception e) {
			
		}		
	}
	
}

