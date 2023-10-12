package opencraft;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Scanner;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import opencraft.blocks.BlockDirt;
import opencraft.blocks.BlockGrass;
import opencraft.blocks.BlockStone;
import opencraft.graphics.DisplayUtills;
import opencraft.physics.physicsUtils;

public class Chunk {
	private int x;
	private int z;
	private int regionX;
	private int regionZ;
	private int id = -1;
	public Block[][][] blocks;
	public static String[] BlockTypes = {"air","opencraft.blocks.BlockGrass","opencraft.blocks.BlockDirt","opencraft.blocks.BlockStone"};
	public Chunk(int x, int z, int regionX, int regionZ) {
		super();
		this.x = x;
		this.z = z;
		this.regionX = regionX;
		this.regionZ = regionZ;
		
	}

	public int getX() {
		return x;
	}

	public int getZ() {
		return z;
	}

	public int getRegionX() {
		return regionX;
	}

	public int getRegionZ() {
		return regionZ;
	}

	public int getGlobalX() {
		return x + (regionX * 16);
	}

	public int getGlobalZ() {
		return z + (regionZ * 16);
	}

	public void load() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		File chunkFile = new File("C:\\Opencraft\\worlds\\Test\\chunks\\chunk(" +(x+(16*regionX)) + "," + (z+(16*regionZ)) + ").opencraftChunk");
		blocks = new Block[16][256][16];
		if (!chunkFile.exists()) {
			try {
				chunkFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileWriter fw = new FileWriter(chunkFile);

			for (int x = 0; x < 16; x++) {

				for (int z = 0; z < 16; z++) {
					
					int endHeight = NormalWorldGenerator.generateHeight(x + (this.getGlobalX() * 16), z + (this.getGlobalZ() * 16));
					for (int height = 0; height <= endHeight; height++) {
						if (endHeight - height == 0) {
							blocks[x][height][z] = new BlockGrass(x, height, z, this.x, this.z, regionX, regionZ);
						} else if (height >= endHeight - 4) {
							blocks[x][height][z] = new BlockDirt(x, height, z, this.x, this.z, regionX, regionZ);
						}else {
							blocks[x][height][z] = new BlockStone(x, height, z, this.x, this.z, regionX, regionZ);
						}

						// fw.write();
					}
					int startY = 0;
					int lastBlockType = blocks[x][0][z].getID();
					int blockType = 0;
					for (int y = 1; y < 256; y++) {
						
						if(blocks[x][y][z] != null) {
							blockType = blocks[x][y][z].getID();
						}else {
							blockType = 0;
						}
						if(blockType != lastBlockType) {
							//fw.append(lastBlockType + " " + x + " " + startY + " " + z + " " + ((y-1)-startY)+"\n");
							fw.append(new String(new int[] {lastBlockType, x, startY,z,((y-1)-startY)}, 0, 5));
							startY = y;
							lastBlockType = blockType;
						}
					}
					
					//fw.append(blockType + " " + x + " " + startY + " " + z + " " + (255-startY)+"\n");
					fw.append(new String(new int[] {blockType, x, startY,z,(255-startY)}, 0, 5));
				}
			}
			fw.close();

		} else {
			BufferedReader chunkReader = new BufferedReader(new FileReader(chunkFile));
			int code = 0;
			while ((code = chunkReader.read()) != -1) {
				System.out.println("c " + code);
				int code2 = chunkReader.read();
				System.out.println("c2 " + code2);
				int code3 = chunkReader.read();
				System.out.println("c3 " + code3);
				int code4 = chunkReader.read();
				System.out.println("c4 " + code4);
				int code5 = chunkReader.read();
				System.out.println("c5 " + code5);
				int data[] = {code, code2, code3, code4,code5};
				
				
				int x = data[1];
				int z = data[3];
				
				int endY = data[4];
				int startPos =data[2];
				if(data[0] != 0) {
					
				for (int y = startPos; y <=startPos+ endY; y++) {
					
					blocks[x][y][z] = (Block) Class.forName(BlockTypes[data[0]]).getConstructors()[0]
							.newInstance(x, y, z, this.x, this.z, regionX, regionZ);
				
					}}
				
			}
			chunkReader.close();

		}

	}
public void calculateLighting() {
	for(int localX = 0; localX < 16; localX++) {
		for(int y = 0; y < 256; y++) {
			for(int localZ = 0; localZ < 16; localZ++) {
				if(blocks[localX][y][localZ] != null) {
				int x = (int) blocks[localX][y][localZ].getGlobalX();
				int z = (int) blocks[localX][y][localZ].getGlobalZ();
				
				if(physicsUtils.getNextBlockInDirection(x, y, z, 0, 1, 0,100- (int)y)!=null &&physicsUtils.getNextBlockInDirection(x, y+1, z, 1, 0, 0,100- (int)y)!=null && physicsUtils.getNextBlockInDirection(x, y+1, z, -1, 0, 0,100- (int)y)!=null &&physicsUtils.getNextBlockInDirection(x, y+1, z, 0, 0, 1,100- (int)y)!=null&&physicsUtils.getNextBlockInDirection(x, y+1, z, 0, 0, -1,100- (int)y)!=null ) {
					 blocks[localX][y][localZ].topLight = 0.1f;
				}
				if(physicsUtils.getNextBlockInDirection(x, y, z, 0, -1, 0,(int)y)!=null &&physicsUtils.getNextBlockInDirection(x, y-1, z, 1, 0, 0,(int)y)!=null && physicsUtils.getNextBlockInDirection(x, y-1, z, -1, 0, 0,(int)y)!=null &&physicsUtils.getNextBlockInDirection(x, y-1, z, 0, 0, 1,(int)y)!=null&&physicsUtils.getNextBlockInDirection(x, y-1, z, 0, 0, -1,(int)y)!=null ) {
					 blocks[localX][y][localZ].bottomLight = 0.1f;
				}
				if(physicsUtils.getNextBlockInDirection(x,y,z, 1,0,0,100)!=null && physicsUtils.getNextBlockInDirection(x+1,y,z, 0,1,0,100)!=null && physicsUtils.getNextBlockInDirection(x+1,y,z, 0,-1,0,100) !=null && physicsUtils.getNextBlockInDirection(x+1,y,z, 0,0,1,100)!=null && physicsUtils.getNextBlockInDirection(x+1,y,z, 0,0,-1,100) !=null) {
					 blocks[localX][y][localZ].rightLight = 0.1f;
				}
		if(physicsUtils.getNextBlockInDirection(x,y,z, -1,0,0,100)!=null && physicsUtils.getNextBlockInDirection(x-1,y,z, 0,1,0,100)!=null && physicsUtils.getNextBlockInDirection(x-1,y,z, 0,-1,0,100) !=null && physicsUtils.getNextBlockInDirection(x-1,y,z, 0,0,1,100)!=null && physicsUtils.getNextBlockInDirection(x-1,y,z, 0,0,-1,100) !=null) {
			 blocks[localX][y][localZ].leftLight = 0.1f;
				}
		if(physicsUtils.getNextBlockInDirection(x,y,z, 0,0,-1,100)!=null && physicsUtils.getNextBlockInDirection(x,y,z-1, 0,1,0,100)!=null && physicsUtils.getNextBlockInDirection(x,y,z-1, 0,-1,0,100) !=null && physicsUtils.getNextBlockInDirection(x,y,z-1, 1,0,0,100)!=null && physicsUtils.getNextBlockInDirection(x,y,z-1, -1,0,0,100) !=null) {
			 blocks[localX][y][localZ].frontLight = 0.1f;
		}
		if(physicsUtils.getNextBlockInDirection(x,y,z, 0,0,1,100)!=null && physicsUtils.getNextBlockInDirection(x,y,z+1, 0,1,0,100)!=null && physicsUtils.getNextBlockInDirection(x,y,z+1, 0,-1,0,100) !=null && physicsUtils.getNextBlockInDirection(x,y,z+1, 1,0,0,100)!=null && physicsUtils.getNextBlockInDirection(x,y,z+1, -1,0,0,100) !=null) {
			 blocks[localX][y][localZ].backLight = 0.1f;
		}
				}
			}
		}
	}
}
	public void save() throws IOException {
		File chunkFile = new File("C:\\Opencraft\\worlds\\Test\\chunks\\chunk(" +(x+(16*regionX)) + "," + (z+(16*regionZ)) + ").opencraftChunk");
		
		
			FileWriter fw = new FileWriter(chunkFile);
			
			fw.write("");
			for (int x = 0; x < 16; x++) {

				for (int z = 0; z < 16; z++) {
				
				int startY = 0;
				int lastBlockType = blocks[x][0][z].getID();
				int blockType = 0;
				for (int y = 1; y < 256; y++) {
					
					if(blocks[x][y][z] != null) {
						blockType = blocks[x][y][z].getID();
					}
					if(blockType != lastBlockType) {
						//fw.append(lastBlockType + " " + x + " " + startY + " " + z + " " + ((y-1)-startY)+"\n");
						fw.append(new String(new int[] {lastBlockType, x, startY,z,((y-1)-startY)}, 0, 5));
						startY = y;
						lastBlockType = blockType;
					}
				}
				
				//fw.append(blockType + " " + x + " " + startY + " " + z + " " + (255-startY)+"\n");
				fw.append(new String(new int[] {blockType, x, startY,z,(255-startY)}, 0, 5));
			
				}
				}
			
	}
	public void setup() {
		//if(physicsUtils.getNextBlockInDirection(x, y, z, 0, 1, 0,255- (int)y)!=null &&physicsUtils.getNextBlockInDirection(x, y+1, z, 1, 0, 0,255- (int)y)!=null && physicsUtils.getNextBlockInDirection(x, y+1, z, -1, 0, 0,255- (int)y)!=null &&physicsUtils.getNextBlockInDirection(x, y+1, z, 0, 0, 1,255- (int)y)!=null&&physicsUtils.getNextBlockInDirection(x, y+1, z, 0, 0, -1,255- (int)y)!=null )
		if(id == -1) {
		id = GL11.glGenLists(1);
		}else {
			GL11.glDeleteLists(id,1);
		}
		GL11.glNewList(id, GL11.GL_COMPILE);
		
		GL11.glBegin(GL11.GL_QUADS);
		//DisplayUtills.shader.bind();
		//GL30.glUniform1ui(DisplayUtills.shader.uniforms.get("tex"),World.blockTextures.getTextureID());
		//GL11.glBindTexture(GL11.GL_TEXTURE_2D, World.blockTextures.getTextureID());
		//GL11.glBegin(GL11.GL_QUADS);
		
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 256; y++) {

				for (int z = 0; z < 16; z++) {
					if(blocks[x][y][z] != null) {
					Block block1 = null;
					Block block2 = null;
					Block block3 = null;
					Block block4 = null;
					Block block5 = null;
					Block block6 = null;
					try {
					
						block1 = World.getBlock((int)blocks[x][y][z].getGlobalX(), y+1, (int)blocks[x][y][z].getGlobalZ());
						block2 = World.getBlock((int)blocks[x][y][z].getGlobalX(), y-1, (int)blocks[x][y][z].getGlobalZ());
						block3 = World.getBlock((int)blocks[x][y][z].getGlobalX()+1, y, (int)blocks[x][y][z].getGlobalZ());
						block4 = World.getBlock((int)blocks[x][y][z].getGlobalX()-1, y, (int)blocks[x][y][z].getGlobalZ());
						block5 = World.getBlock((int)blocks[x][y][z].getGlobalX(), y, (int)blocks[x][y][z].getGlobalZ()+1);
						block6 = World.getBlock((int)blocks[x][y][z].getGlobalX(), y, (int)blocks[x][y][z].getGlobalZ()-1);
					
						
						/*
						block1 = blocks[x+1][y][z];
						block2 = blocks[x-1][y][z];
						block3 = blocks[x][y+1][z];
						block4 = blocks[x][y-1][z];
						block5 = blocks[x][y][z+1];
						block6 = blocks[x][y][z-1];
					*/
					}catch(Exception e) {
						
					}
					if(block1 == null || block2 == null || block3 == null || block4 == null || block5 == null || block6 == null) {
						blocks[x][y][z].draw();
					}
				}
			}
			}
		}
		GL11.glEnd();
		GL11.glEndList();
		//World.chunkDrawIDs[id] = id;
	
		

}

	public void draw() {
		//.out.println("DID: "+id);
		GL11.glCallList(id);
		
	}
	public void delete() {
		if(id != -1) {
		GL11.glDeleteLists(id,1);
		}
	}

}
