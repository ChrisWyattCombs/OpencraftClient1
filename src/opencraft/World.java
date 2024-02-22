package opencraft;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import opencraft.blocks.BlockWater;
import opencraft.graphics.DisplayVariables;
import opencraft.graphics.Vector2i;
import opencraft.graphics.Vector3f;
import opencraft.items.ItemGrass;
import opencraft.physics.physicsUtils;


public class World {
	//public static int[] chunkDrawIDs = new int[257];
	public static ArrayList<Item> items = new ArrayList<>();
	static {
		Item grass =new ItemGrass();
		grass.y = 200;
		items.add(grass);
		
	}
	
	public static int realRegionListLength = 0;
	public static Region[] regions = new Region[256];
	public static double worldLoadProgress = 0;
	public static int lastID = 0;
	public static boolean firstTime = true;
	public static int lastRegionX = 0;
	public static int lastRegionZ = 0;
	public static float x = Player.x;
	public static float z = Player.z;
	public static String worldName = "";
	public static int renderDistance = 8;
	public static ArrayList<Vector2i> chunksToSetup = null;
	private static  int setupIndex= 0;
	public static boolean rendering = false;
	public static int currentRegionIndex = -1;
	public static boolean loadingChunks = false;
	public static long checkFluidTime = 0;
	public static ArrayList<Vector3f> uncheckedFluids = new ArrayList<>();
	public static void loadWorld() {
		int chunkX =(int)x >> 4;
		int chunkZ =(int)z >> 4;
		for(int x = -renderDistance; x <  renderDistance; x++) {
			for(int z =-renderDistance; z < renderDistance ; z++) {
				try {
				
				loadChunk(x,z);
				
				}catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
				worldLoadProgress = (float)((x-chunkX)+renderDistance)/(renderDistance *2) + (float)((z-chunkZ)+renderDistance)/((renderDistance *2)*(renderDistance *2));
			}
		}
		//calculateLighting();
		worldLoadProgress = 1;
	}
	
	public static Chunk getChunk(int cx, int cz) {
		int regionX = cx >> 4;
		int regionZ = cz >> 4;

		 int x = cx & 0xF;
		 int z = cz& 0xF;
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
		
		//System.out.println("d"+Math.sqrt(Math.pow(DisplayVariables.camX-x, 2)+Math.pow(DisplayVariables.camZ-z, 2)));
		if(Math.sqrt(Math.pow(Player.x-x, 2)+Math.pow(Player.z-z, 2))>16) {
			x = Player.x;
			//System.out.println("works23");
			z = Player.z;
			//System.out.println();
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
				//System.out.println("cou");
				
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
			//System.out.println("works23");
			
			new Thread() {
				public void run() {
					loadingChunks = true;
					for(int cx = (int)chunkX -renderDistance;cx <(int)chunkX+renderDistance;cx++) {
						
						for(int cz = (int)chunkZ -renderDistance; cz <(int)chunkZ+renderDistance;cz++) {
						if(World.getChunk(cx, cz) == null || !World.getChunk(cx, cz).fullyLoaded) {
							
							try {
								World.loadChunk(cx, cz);
							} catch (Exception e) {
								
							}
							chunks.add(new Vector2i(cx, cz));
							
						}
						
						System.out.println(cz);
						}
						
					}
					//calculateLighting();
					for(Vector2i chunkPostion : chunks) {
					//lightChunk(chunkPostion.getX(), chunkPostion.getY());
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
			//System.out.println("works 500 " + chunksToSetup.size());
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
		for(int i = 0; i < realRegionListLength; i++) {
			regions[i].drawWater();
			}
		rendering = false;
		
	}
	public static void loadChunk(int cx, int cz) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		int regionX = cx >> 4;
		int regionZ = cz >> 4;

		 int x = cx & 0xF;
		 int z = cz & 0xF;
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
			int lcx = chunkX  & 0xF;
			int lcz = chunkZ  & 0xF;
			int localX = x  & 0xF;
			int localZ = z  & 0xF;
			//System.out.println("lcx: " + lcx);
			//System.out.println("lcz " + lcz);
			
			try {
			return regions[getRegionIndex(regionX, regionZ)].chunks[lcx][lcz].blocks[localX][y][localZ];
		}catch( Exception e){
			//e.printStackTrace();
			//System.out.println("a2");
			
		}
		return null;
	}public static boolean CheckForBlock(int x, int y, int z) {
		
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
			int lcx = chunkX  & 0xF;
			int lcz = chunkZ  & 0xF;
			int localX = x  & 0xF;
			int localZ = z  & 0xF;
			//System.out.println("lcx: " + lcx);
			//System.out.println("lcz " + lcz);
			try {
				
			
			if(regions[getRegionIndex(regionX, regionZ)].chunks[lcx][lcz].blocks[localX][y][localZ] != null) {
				return true;
			}
			}catch (Exception e) {
				return false;
			}
			return false;
		
	}
	
	public static void setBlock(String blockType, int x, int y, int z) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException, FileNotFoundException {
		int chunkX = x >> 4;
		int chunkZ = z >> 4;
		int regionX = chunkX >> 4;
		int reigonZ = chunkZ >> 4;
		int lcx = chunkX  & 0xF;
		int lcz = chunkZ  & 0xF;
		int localX = x  & 0xF;
		int localZ = z  & 0xF;
		int regionIndex = getRegionIndex(regionX, reigonZ);
		if(regionIndex == -1) {
			regionIndex = realRegionListLength;
			regions[realRegionListLength] = new Region(regionX, reigonZ, new Chunk[16][16]);
			realRegionListLength++;
		}
		if(regions[regionIndex].chunks[lcx][lcz]==null) {
			regions[regionIndex].chunks[lcx][lcz] = new Chunk(lcx, lcz, regionX, reigonZ);
			
		}
		
		if(!blockType.equals("air")) {
		regions[regionIndex].chunks[lcx][lcz].blocks[localX][y][localZ] = (Block) Class.forName("opencraft.blocks."+blockType).getConstructors()[0].newInstance(localX,y,localZ,lcx,lcz,regionX,reigonZ);
		}else {
		regions[regionIndex].chunks[lcx][lcz].blocks[localX][y][localZ] = null;
		
		Block block1 = getBlock(x+1, y,z);
		if(block1 != null) {
		if( block1.isFluid()) {
			uncheckedFluids.add(new Vector3f(block1.getGlobalX(), block1.getY(), block1.getGlobalZ()));
		}
		}
		
		Block block2 = getBlock(x-1, y,z);
		if(block2 != null) {
		if( block2.isFluid()) {
			uncheckedFluids.add(new Vector3f(block2.getGlobalX(), block2.getY(), block2.getGlobalZ()));
		}
		}
		
		Block block3 = getBlock(x, y+1,z);
		if(block3 != null) {
		if( block3.isFluid()) {
			uncheckedFluids.add(new Vector3f(block3.getGlobalX(), block3.getY(), block3.getGlobalZ()));
		}
		}
		
		Block block4 = getBlock(x, y-1,z);
		if(block4 != null) {
		if( block4.isFluid()) {
			uncheckedFluids.add(new Vector3f(block4.getGlobalX(), block4.getY(), block4.getGlobalZ()));
		}
		}
		
		Block block5 = getBlock(x, y,z+1);
		if(block5 != null) {
		if( block5.isFluid()) {
			uncheckedFluids.add(new Vector3f(block5.getGlobalX(), block5.getY(), block5.getGlobalZ()));
		}
		}
		
		Block block6 = getBlock(x, y,z-1);
		if(block6 != null) {
		if( block6.isFluid()) {
			uncheckedFluids.add(new Vector3f(block6.getGlobalX(), block6.getY(), block6.getGlobalZ()));
		}
		}
		}
		
		for(int ox = chunkX-1; ox < chunkX+1;ox++) {
			for(int oz = chunkZ-1; oz < chunkZ+1;oz++) {
				regionX = ox >> 4;
				 reigonZ = oz >> 4;
				 regionIndex = getRegionIndex(regionX, reigonZ);
				// regions[regionIndex].chunks[ox& 0xF][oz& 0xF].calculateLighting();
				 int oxl = ox& 0xF;
				 int ozl =oz& 0xF;
				 if(regionIndex != -1) {
					 if(regions[regionIndex].chunks != null) {
						 if(regions[regionIndex].chunks[oxl][ozl] != null){
						 regions[regionIndex].chunks[oxl][ozl].delete();
					 }
					 }
				 }
				
				 if (chunksToSetup == null) {
					 chunksToSetup = new ArrayList<>();
					 
				 }
				 chunksToSetup.add(new Vector2i(ox,oz));
			}
		}
		
	}
	public static void setBlock(String blockType, int x, int y, int z,boolean reloadChunks) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException, FileNotFoundException {
		int chunkX = x >> 4;
		int chunkZ = z >> 4;
		int regionX = chunkX >> 4;
		int reigonZ = chunkZ >> 4;
		int lcx = chunkX  & 0xF;
		int lcz = chunkZ  & 0xF;
		int localX = x  & 0xF;
		int localZ = z  & 0xF;
		int regionIndex = getRegionIndex(regionX, reigonZ);
		if(regionIndex == -1) {
			regionIndex = realRegionListLength;
			regions[realRegionListLength] = new Region(regionX, reigonZ, new Chunk[16][16]);
			realRegionListLength++;
		}
		if(regions[regionIndex].chunks[lcx][lcz]==null) {
			regions[regionIndex].chunks[lcx][lcz] = new Chunk(lcx, lcz, regionX, reigonZ);
			
		}
		
		if(!blockType.equals("air")) {
		regions[regionIndex].chunks[lcx][lcz].blocks[localX][y][localZ] = (Block) Class.forName("opencraft.blocks."+blockType).getConstructors()[0].newInstance(localX,y,localZ,lcx,lcz,regionX,reigonZ);
		}else {
		regions[regionIndex].chunks[lcx][lcz].blocks[localX][y][localZ] = null;
		if(reloadChunks) {
		Block block1 = getBlock(x+1, y,z);
		if(block1 != null) {
		if( block1.isFluid()) {
			uncheckedFluids.add(new Vector3f(block1.getGlobalX(), block1.getY(), block1.getGlobalZ()));
		}
		}
		
		Block block2 = getBlock(x-1, y,z);
		if(block2 != null) {
		if( block2.isFluid()) {
			uncheckedFluids.add(new Vector3f(block2.getGlobalX(), block2.getY(), block2.getGlobalZ()));
		}
		}
		
		Block block3 = getBlock(x, y+1,z);
		if(block3 != null) {
		if( block3.isFluid()) {
			uncheckedFluids.add(new Vector3f(block3.getGlobalX(), block3.getY(), block3.getGlobalZ()));
		}
		}
		
		Block block4 = getBlock(x, y-1,z);
		if(block4 != null) {
		if( block4.isFluid()) {
			uncheckedFluids.add(new Vector3f(block4.getGlobalX(), block4.getY(), block4.getGlobalZ()));
		}
		}
		
		Block block5 = getBlock(x, y,z+1);
		if(block5 != null) {
		if( block5.isFluid()) {
			uncheckedFluids.add(new Vector3f(block5.getGlobalX(), block5.getY(), block5.getGlobalZ()));
		}
		}
		
		Block block6 = getBlock(x, y,z-1);
		if(block6 != null) {
		if( block6.isFluid()) {
			uncheckedFluids.add(new Vector3f(block6.getGlobalX(), block6.getY(), block6.getGlobalZ()));
		}
		}
		
		
		for(int ox = chunkX-1; ox < chunkX+1;ox++) {
			for(int oz = chunkZ-1; oz < chunkZ+1;oz++) {
				regionX = ox >> 4;
				 reigonZ = oz >> 4;
				 regionIndex = getRegionIndex(regionX, reigonZ);
				// regions[regionIndex].chunks[ox& 0xF][oz& 0xF].calculateLighting();
				 int oxl = ox& 0xF;
				 int ozl =oz& 0xF;
				 if(regionIndex != -1) {
					 if(regions[regionIndex].chunks != null) {
						 if(regions[regionIndex].chunks[oxl][ozl] != null){
						 regions[regionIndex].chunks[oxl][ozl].delete();
					 }
					 }
				 }
				
				 if (chunksToSetup == null) {
					 chunksToSetup = new ArrayList<>();
					 
				 }
				 chunksToSetup.add(new Vector2i(ox,oz));
			}
		}
		}
		}
	}
	public static void setupChunk(int cx, int cz) {
		int regionX = cx >> 4;
		int regionZ = cz >> 4;
		 int x = cx& 0xF;
		 int z = cz& 0xF;
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
			
			if(World.regions[i] != null) {
			for(int cx = 0; cx<16; cx++) {
				for(int cz = 0; cz<16; cz++) {
					if(World.regions[i].chunks[cx][cz] != null) {
						World.regions[i].chunks[cx][cz].calculateLighting();
					
					}
				}
			}
		}
		}
	}
	public static void lightChunk(int cx, int cz) {
		int regionX = cx >> 4;
		int regionZ = cz >> 4;
		 int x = cx & 0xF;
		 int z = cz& 0xF;
		Region region = null;
		
			int regionIndex = getRegionIndex(regionX, regionZ);
			
	
		try {
		regions[regionIndex].chunks[x][z].calculateLighting();;
		}catch(Exception e) {
			
		}		
	}
	public static void saveAndUnloadAllLoadedChunks() throws IOException {
		for(int i = 0; i <World.realRegionListLength; i++) {
			for(int cx = 0; cx<16; cx++) {
				for(int cz = 0; cz<16; cz++) {
					if(World.regions[i].chunks[cx][cz] != null) {
						World.regions[i].chunks[cx][cz].save();
						World.regions[i].chunks[cx][cz].delete();
						World.regions[i].chunks[cx][cz] = null;
					}
					
	}
			}
			World.regions[i] = null;
			
		}
		realRegionListLength = 0;
}
	public static void drawAndUpdateItems() {
		for(int i = 0; i < items.size();i++){
			Item item = items.get(i);
			item.updatePosition();
		
			item.drawIcon(item.x, item.y, item.z, 0.4f);
		}
	}
	public static void checkFluids() {
		if(System.currentTimeMillis() - checkFluidTime > 2000) {
			checkFluidTime = System.currentTimeMillis() ;
		ArrayList<Vector3f> newUncheckedFluids = new ArrayList<>();
		for(Vector3f postition : uncheckedFluids) {
			Block fluid = getBlock((int)postition.getX(), (int)postition.getY(), (int)postition.getZ());
			
			Block block1 = null;
			Block block2 = null;
			Block block3 = null;
			Block block4 = null;
			Block block5 = null;
			Block block6 = null;	
			
			block1 = getBlock((int)fluid.getGlobalX()+1, (int)fluid.getY(),(int) fluid.getGlobalZ());
			block2 = getBlock((int)fluid.getGlobalX()-1, (int)fluid.getY(),(int) fluid.getGlobalZ());
			block3 = getBlock((int)fluid.getGlobalX(), (int)fluid.getY()-1,(int) fluid.getGlobalZ());
			block4 = getBlock((int)fluid.getGlobalX(), (int)fluid.getY(),(int) fluid.getGlobalZ()+1);
			block5 = getBlock((int)fluid.getGlobalX(), (int)fluid.getY(),(int) fluid.getGlobalZ()-1);
			
			if(block3 == null) {
				try {
					setBlock("BlockWater", (int)fluid.getGlobalX(), (int)fluid.getY()-1, (int)fluid.getGlobalZ());
					newUncheckedFluids.add(new Vector3f((int)fluid.getGlobalX(), (int)fluid.getY()-1, (int)fluid.getGlobalZ()));
				}catch (Exception e) {
					
					
					// TODO: handle exception
				}
				
			}else {
				if(block1 == null) {
					try {
						setBlock("BlockWater", (int)fluid.getGlobalX()+1, (int)fluid.getY(), (int)fluid.getGlobalZ());
						int chunkX = (int)(fluid.getGlobalX()+1) >> 4;
						int chunkZ = (int)(fluid.getGlobalZ()) >> 4;
						int regionX = chunkX >> 4;
						int regionZ = chunkZ >> 4;
						int regionsIndex = getRegionIndex(regionX,regionZ );
						int lcx = chunkX  & 0xF;
						int lcz = chunkZ  & 0xF;
						int localX =  (int)(fluid.getGlobalX()+1)& 0xF;
						int localZ = (int)(fluid.getGlobalZ()) & 0xF;
						regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ].height=fluid.height - 0.20f;
						if(regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ].height <= 0) {
							regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ]=null;
						}else {
						newUncheckedFluids.add(new Vector3f((int)fluid.getGlobalX()+1, (int)fluid.getY(), (int)fluid.getGlobalZ()));
						}
					}catch (Exception e) {
						
						
						// TODO: handle exception
					}
				}
				if(block2 == null) {
					try {
						setBlock("BlockWater", (int)fluid.getGlobalX()-1, (int)fluid.getY(), (int)fluid.getGlobalZ());
						int chunkX = (int)(fluid.getGlobalX()-1) >> 4;
						int chunkZ = (int)(fluid.getGlobalZ()) >> 4;
						int regionX = chunkX >> 4;
						int regionZ = chunkZ >> 4;
						int regionsIndex = getRegionIndex(regionX,regionZ );
						int lcx = chunkX  & 0xF;
						int lcz = chunkZ  & 0xF;
						int localX =  (int)(fluid.getGlobalX()-1)& 0xF;
						int localZ = (int)(fluid.getGlobalZ()) & 0xF;
						regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ].height=fluid.height - 0.20f;
						if(regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ].height <= 0) {
							regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ]=null;
						}else {
						newUncheckedFluids.add(new Vector3f((int)fluid.getGlobalX()-1, (int)fluid.getY(), (int)fluid.getGlobalZ()));
						}
					}catch (Exception e) {
						
						
						// TODO: handle exception
					}
				}
				if(block4 == null) {
					try {
						setBlock("BlockWater", (int)fluid.getGlobalX(), (int)fluid.getY(), (int)fluid.getGlobalZ()+1);
						int chunkX = (int)(fluid.getGlobalX()) >> 4;
						int chunkZ = (int)(fluid.getGlobalZ()+1) >> 4;
						int regionX = chunkX >> 4;
						int regionZ = chunkZ >> 4;
						int regionsIndex = getRegionIndex(regionX,regionZ );
						int lcx = chunkX  & 0xF;
						int lcz = chunkZ  & 0xF;
						int localX =  (int)(fluid.getGlobalX())& 0xF;
						int localZ = (int)(fluid.getGlobalZ()+1) & 0xF;
						regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ].height=fluid.height - 0.20f;
						if(regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ].height <= 0) {
							regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ]=null;
						}else {
						newUncheckedFluids.add(new Vector3f((int)fluid.getGlobalX(), (int)fluid.getY(), (int)fluid.getGlobalZ()+1));
						}
					}catch (Exception e) {
						
						
						// TODO: handle exception
					}
				}
				if(block5 == null) {
					try {
						setBlock("BlockWater", (int)fluid.getGlobalX(), (int)fluid.getY(), (int)fluid.getGlobalZ()-1);
						int chunkX = (int)(fluid.getGlobalX()) >> 4;
						int chunkZ = (int)(fluid.getGlobalZ()-1) >> 4;
						int regionX = chunkX >> 4;
						int regionZ = chunkZ >> 4;
						int regionsIndex = getRegionIndex(regionX,regionZ );
						int lcx = chunkX  & 0xF;
						int lcz = chunkZ  & 0xF;
						int localX =  (int)(fluid.getGlobalX())& 0xF;
						int localZ = (int)(fluid.getGlobalZ()-1) & 0xF;
						regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ].height=fluid.height - 0.20f;
						if(regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ].height <= 0) {
							regions[regionsIndex].chunks[lcx][lcz].blocks[localX][ (int)fluid.getY()][localZ]=null;
						}else {
						newUncheckedFluids.add(new Vector3f((int)fluid.getGlobalX(), (int)fluid.getY(), (int)fluid.getGlobalZ()-1));
						}
					}catch (Exception e) {
						
						
						// TODO: handle exception
					}
				}
			}
			
		}
		uncheckedFluids.clear();
		uncheckedFluids.addAll(newUncheckedFluids);
	}
	}
}
