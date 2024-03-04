package opencraft;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.Drawable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.SharedDrawable;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import opencraft.blocks.BlockWater;
import opencraft.entities.EntityZombie;
import opencraft.graphics.DisplayVariables;
import opencraft.graphics.Vector2i;
import opencraft.graphics.Vector3f;
import opencraft.items.ItemGrass;
import opencraft.network.NetworkUtills;
import opencraft.physics.physicsUtils;


public class World {
	public static String[] itemTypes = {"empty","opencraft.items.ItemGrass","opencraft.items.ItemDirt","opencraft.items.ItemStone","opencraft.items.ItemWood","opencraft.items.ItemSand"};
	//public static int[] chunkDrawIDs = new int[257];
	public static ArrayList<Item> items = new ArrayList<>();
	static {
		Item grass =new ItemGrass();
		grass.y = 200;
		items.add(grass);
		
	}
	public static ArrayList<Entity> entities = new ArrayList<>();
	
	static {
	
		entities.add(new EntityZombie(0, 200, 0));
		
		
	}
	
	public static boolean server = false;
	public static boolean addingChunks = false;
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
	public static ArrayList<Vector2i> chunksToSetup = new ArrayList<>();
	public static  int setupIndex= 0;
	public static boolean rendering = false;
	public static int currentRegionIndex = -1;
	public static boolean loadingChunks = false;
	public static long checkFluidTime = 0;
	public static boolean doneLoading = false;
	public static ArrayList<Vector3f> uncheckedFluids = new ArrayList<>();
	public static void loadWorld(Drawable context) throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException {
	
		if(server) {
			/*
			 NetworkUtills.send(0);
			Player.x = NetworkUtills.readFloat();
			Player.y = NetworkUtills.readFloat();
			Player.z = NetworkUtills.readFloat();
			Player.yaw = NetworkUtills.readFloat();
			Player.pitch = NetworkUtills.readFloat();
			Player.forwardVelocity = NetworkUtills.readFloat();
			Player.backwardVelocity = NetworkUtills.readFloat();
			Player.rightVelocity = NetworkUtills.readFloat();
			Player.leftVelocity = NetworkUtills.readFloat();
			Player.velocityY = NetworkUtills.readFloat();
			Player.health = NetworkUtills.readFloat();
			
			for(int x = 0; x < 9; x++) {
				int itemID = NetworkUtills.readInt();
				if(itemID != 0) {
				Player.hotbar[x]=(Item) Class.forName(itemTypes[itemID]).getConstructors()[0]
						.newInstance();
				Player.hotbar[x].stack = NetworkUtills.readInt();
				}else {
					Player.hotbar[x] = null;
				}
			}
			for(int x = 0; x < 9; x++) {
				for(int y = 0; y < 3; y++) {
					int itemID = NetworkUtills.readInt();
					if(itemID != 0) {
					Player.Inventory[x][y]=(Item) Class.forName(itemTypes[itemID]).getConstructors()[0]
							.newInstance();
					Player.Inventory[x][y].stack = NetworkUtills.readInt();
					}else {
						Player.Inventory[x][y] =null;
					}
			}
			}
			
			NormalWorldGenerator.seed = NetworkUtills.readInt();
		*/
		}else {
		try {
			
			
			context.makeCurrent();
		} catch (LWJGLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(0);
		}
		File dataFile = new File("C:\\Opencraft\\worlds\\"+World.worldName+"\\level.opecraftData");
		if(dataFile.exists()) {
		DataInputStream dataReader = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));
		Player.x = dataReader.readFloat();
		Player.y = dataReader.readFloat();
		Player.z = dataReader.readFloat();
		Player.yaw = dataReader.readFloat();
		Player.pitch = dataReader.readFloat();
		Player.forwardVelocity = dataReader.readFloat();
		Player.backwardVelocity = dataReader.readFloat();
		Player.rightVelocity = dataReader.readFloat();
		Player.leftVelocity = dataReader.readFloat();
		Player.velocityY = dataReader.readFloat();
		Player.health = dataReader.readFloat();
		
		for(int x = 0; x < 9; x++) {
			int itemID = dataReader.readInt();
			if(itemID != 0) {
			Player.hotbar[x]=(Item) Class.forName(itemTypes[itemID]).getConstructors()[0]
					.newInstance();
			Player.hotbar[x].stack = dataReader.readInt();
			}else {
				Player.hotbar[x] = null;
			}
		}
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 3; y++) {
				int itemID = dataReader.readInt();
				if(itemID != 0) {
				Player.Inventory[x][y]=(Item) Class.forName(itemTypes[itemID]).getConstructors()[0]
						.newInstance();
				Player.Inventory[x][y].stack = dataReader.readInt();
				}else {
					Player.Inventory[x][y] =null;
				}
		}
		}
		
		NormalWorldGenerator.seed = dataReader.readInt();
		dataReader.close();
		}else {
			if(NormalWorldGenerator.randomSeed) {
			NormalWorldGenerator.seed = (int)(Math.random()*(1000000-(-1000000)+1)-1000000);
			}
		}
		NormalWorldGenerator.g = new Gen(NormalWorldGenerator.seed);
		NormalWorldGenerator.MoutainGen = new Gen(NormalWorldGenerator.seed);
		
		NormalWorldGenerator.MoutainGen.OCTAVES=7;
		NormalWorldGenerator.MoutainGen.AMPLITUDE=400;
		NormalWorldGenerator.MoutainGen.ROUGHNESS = 0.2f;
		
		
		NormalWorldGenerator.WaterGen = new Gen(NormalWorldGenerator.seed);
		
		NormalWorldGenerator.WaterGen.OCTAVES=7;
		NormalWorldGenerator.WaterGen.AMPLITUDE=400;
		NormalWorldGenerator.WaterGen.ROUGHNESS = 0.3f;
		
		NormalWorldGenerator.BiomeGen = new Gen(NormalWorldGenerator.seed);
		
		NormalWorldGenerator.BiomeGen .OCTAVES=9;
		NormalWorldGenerator.BiomeGen.AMPLITUDE=150;
		NormalWorldGenerator.BiomeGen.ROUGHNESS = 0.3f;
		
		NormalWorldGenerator.caveGen1 = new Gen(NormalWorldGenerator.seed);
		
		NormalWorldGenerator.caveGen1.OCTAVES=7;
		NormalWorldGenerator.caveGen1.AMPLITUDE=400;
		NormalWorldGenerator.caveGen1.ROUGHNESS = 0.3f;
		
		NormalWorldGenerator.caveGen2 = new Gen(NormalWorldGenerator.seed);
		
		NormalWorldGenerator.caveGen2.OCTAVES=7;
		NormalWorldGenerator.caveGen2.AMPLITUDE=300;
		NormalWorldGenerator.caveGen2.ROUGHNESS = 0.3f;
		}
		int chunkX =(int)Player.x >> 4;
		int chunkZ =(int)Player.z >> 4;
		for(int x = -renderDistance; x <  renderDistance; x++) {
			for(int z =-renderDistance; z < renderDistance ; z++) {
				try {
				
				loadChunk(x+chunkX,z+chunkZ);
				
				}catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
				
				worldLoadProgress = (float)((x)+renderDistance)/(renderDistance *2) + (float)((z)+renderDistance)/((renderDistance *2)*(renderDistance *2));
			}
		}
		setupWorld();
		//calculateLighting();
		worldLoadProgress = 1;
		doneLoading = true;
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
		
		addChunksToSetup(chunks);
	}
	public static void drawWorld() throws LWJGLException {
		
		////System.out.println("d"+Math.sqrt(Math.pow(DisplayVariables.camX-x, 2)+Math.pow(DisplayVariables.camZ-z, 2)));
		if(Math.sqrt(Math.pow(Player.x-x, 2)+Math.pow(Player.z-z, 2))>16) {
			x = Player.x;
			////System.out.println("works23");
			z = Player.z;
			////System.out.println();
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
				////System.out.println("cou");
				
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
			////System.out.println("works23");
			//Drawable context = new SharedDrawable(Display.getDrawable());
			new Thread() {
				public void run() {
					
					for(int cx = (int)chunkX -renderDistance;cx <(int)chunkX+renderDistance;cx++) {
						
						for(int cz = (int)chunkZ -renderDistance; cz <(int)chunkZ+renderDistance;cz++) {
						if(World.getChunk(cx, cz) == null || !World.getChunk(cx, cz).fullyLoaded) {
							
							try {
								World.loadChunk(cx, cz);
							} catch (Exception e) {
								
							}
							chunks.add(new Vector2i(cx, cz));
							
						}
						
						//System.out.println(cz);
						}
						
					}
					//calculateLighting();
					
					
					
				
					if(chunks.size() > 0 ) {
						World.addChunksToSetup(chunks);
					}
				
					
				}
			}.start();
		}
	

		
		
		///if(!loadingChunks) {
		rendering = true;
		for(int i = 0; i < realRegionListLength; i++) {
			regions[i].draw();
			}
		for(int i = 0; i < realRegionListLength; i++) {
			regions[i].drawWater();
			}
		rendering = false;
		
		//}
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
			////System.out.println("lcx: " + lcx);
			////System.out.println("lcz " + lcz);
			
			try {
			return regions[getRegionIndex(regionX, regionZ)].chunks[lcx][lcz].blocks[localX][y][localZ];
		}catch( Exception e){
			//e.printStackTrace();
			////System.out.println("a2");
			
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
			////System.out.println("lcx: " + lcx);
			////System.out.println("lcz " + lcz);
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
		ArrayList<Vector2i>  chunks = new ArrayList<>();
		 addChunkToSetup(new Vector2i(chunkX,chunkZ));
		for(int ox = chunkX-2; ox < chunkX+2;ox++) {
			for(int oz = chunkZ-2; oz < chunkZ+2;oz++) {
				regionX = ox >> 4;
				 reigonZ = oz >> 4;
				 regionIndex = getRegionIndex(regionX, reigonZ);
				// regions[regionIndex].chunks[ox& 0xF][oz& 0xF].calculateLighting();
				 int oxl = ox& 0xF;
				 int ozl =oz& 0xF;
				 if(regionIndex != -1) {
					 if(regions[regionIndex].chunks != null) {
						 if(regions[regionIndex].chunks[oxl][ozl] != null){
						// regions[regionIndex].chunks[oxl][ozl].delete();
					 }
					 }
				 }
				
				
				 chunks.add(new Vector2i(ox,oz));
			}
		}
		 addChunksToSetup(chunks);
		
		
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
		
		addingChunks = true;
		ArrayList<Vector2i>  chunks = new ArrayList<>();
		addChunkToSetup(new Vector2i(chunkX,chunkZ));
		for(int ox = chunkX-2; ox < chunkX+2;ox++) {
			for(int oz = chunkZ-2; oz < chunkZ+2;oz++) {
				regionX = ox >> 4;
				 reigonZ = oz >> 4;
				 regionIndex = getRegionIndex(regionX, reigonZ);
				// regions[regionIndex].chunks[ox& 0xF][oz& 0xF].calculateLighting();
				 int oxl = ox& 0xF;
				 int ozl =oz& 0xF;
				 if(regionIndex != -1) {
					 if(regions[regionIndex].chunks != null) {
						 if(regions[regionIndex].chunks[oxl][ozl] != null){
						 //regions[regionIndex].chunks[oxl][ozl].delete();
					 }
					 }
				 }
				
				
				 chunks.add(new Vector2i(ox,oz));
			}
		}
		 addChunksToSetup(chunks);
		addingChunks = false;
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
			//e.printStackTrace();
			
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
		File dataFile = new File("C:\\Opencraft\\worlds\\"+World.worldName+"\\level.opecraftData");
		dataFile.createNewFile();
		  FileOutputStream fos = new FileOutputStream(dataFile);
		  BufferedOutputStream bos=new BufferedOutputStream(fos);
		  DataOutputStream dos=new DataOutputStream(bos); 
		  dos.writeFloat(Player.x);
		  dos.writeFloat(Player.y);
		  dos.writeFloat(Player.z);
		  dos.writeFloat(Player.yaw);
		  dos.writeFloat(Player.pitch);
		  dos.writeFloat(Player.forwardVelocity);
		  dos.writeFloat(Player.backwardVelocity);
		  dos.writeFloat(Player.rightVelocity);
		  dos.writeFloat(Player.leftVelocity);
		  dos.writeFloat(Player.velocityY);
		  dos.writeFloat(Player.health);
		  for(Item item : Player.hotbar) {
			  if(item != null) {
			  dos.writeInt(item.getID());
			  dos.writeInt(item.stack);
			  }else {
				  dos.writeInt(0);
			  }
			 
			  
		  }
		  for(int x = 0; x < 9; x++) {
			  for(int y = 0; y < 3; y++) {
				  if(Player.Inventory[x][y] != null) {
				  dos.writeInt(Player.Inventory[x][y].getID());
				  dos.writeInt(Player.Inventory[x][y].stack);
				  }else {
					  dos.writeInt(0);
				  }
			  }
			  
		  }
		  dos.writeInt(NormalWorldGenerator.seed);
		  dos.close();
		  
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
	public static synchronized void addChunkToSetup(Vector2i pos) {
		chunksToSetup.add(pos);
	}
	public static synchronized void addChunksToSetup(ArrayList<Vector2i> chunks) {
		for(Vector2i pos : chunks) {
		chunksToSetup.add(pos);
		}
	}
	public static synchronized ArrayList<Vector2i> getChunksToSetup() {
		return chunksToSetup;
	}
	public static synchronized void removeChunkToSetup(Vector2i pos) {
		for(int i = 0; i < chunksToSetup.size();i++) {
			if(chunksToSetup.get(i).getX() == pos.getX()&& chunksToSetup.get(i).getY() == pos.getY()) {
				chunksToSetup.remove(i);
				break;
			}
		}
	}
	public static void drawAndUpdateEntities() {
		for(int i = 0; i < entities.size();i++){
			Entity entity  = entities.get(i);
			
			entity.update();
			entity.draw();
		}
	}
	
}
