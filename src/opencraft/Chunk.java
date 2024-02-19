package opencraft;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import opencraft.blocks.BlockDirt;
import opencraft.blocks.BlockGrass;
import opencraft.blocks.BlockSand;
import opencraft.blocks.BlockStone;
import opencraft.blocks.BlockWater;
import opencraft.graphics.DisplayUtills;
import opencraft.physics.physicsUtils;

public class Chunk {
	private int x;
	private int z;
	private int regionX;
	private int regionZ;
	private int id = -1;
	private int waterID = -1;
	public boolean fullyLoaded = false;
	public Block[][][] blocks = new Block[16][256][16];
	public static String[] BlockTypes = {"air","opencraft.blocks.BlockGrass","opencraft.blocks.BlockDirt","opencraft.blocks.BlockStone","opencraft.blocks.BlockWater","opencraft.blocks.BlockLeaf","opencraft.blocks.BlockWood","opencraft.blocks.BlockSand"};
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
		File chunkFile = new File("C:\\Opencraft\\worlds\\"+World.worldName+"\\chunks\\chunk(" +(x+(16*regionX)) + "," + (z+(16*regionZ)) + ").opencraftChunk");
		
		File chunkDir = new File("C:\\Opencraft\\worlds\\"+World.worldName+"\\chunks");
		chunkDir.mkdirs();
		
		if (!chunkFile.exists()) {
			try {
				chunkFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  FileOutputStream fos = new FileOutputStream(chunkFile);
			  BufferedOutputStream bos=new BufferedOutputStream(fos);
			  DataOutputStream dos=new DataOutputStream(bos); 
			  Biome currentBiome = Biomes.plainsBiome;
			for (int x = 0; x < 16; x++) {

				for (int z = 0; z < 16; z++) {
					
					int endHeight = NormalWorldGenerator.generateHeight(x + (this.getGlobalX() * 16), z + (this.getGlobalZ() * 16));
					if(endHeight < 30) {
						endHeight =30;
					}
					int realHeight  = endHeight;
				
					//float genValue = NormalWorldGenerator.BiomeGen.generateHeight(x, z);
					
					float genValue = NormalWorldGenerator.BiomeGen.generateHeight(x + (this.getGlobalX() * 16), z + (this.getGlobalZ() * 16))+50;
					if(realHeight > 70 && realHeight < 90) {
						if(genValue < 25) {
							currentBiome = Biomes.plainsBiome;
						}else if(genValue < 50) {
							currentBiome = Biomes.desertBiome;
						}else if(genValue >= 50) {
							currentBiome = Biomes.forestBiome;
						}
					}else if(realHeight < 70 ) {
						
							currentBiome = Biomes.WaterBiome;
						
					}
					else if(realHeight > 90 ) {
						
						currentBiome = Biomes.MoutainBiome;
					
				}
					for (int height = 0; height <= endHeight; height++) {
						if(endHeight >70) {
						if (endHeight - height == 0) {
							blocks[x][height][z] = currentBiome.crateBlock1(x, height, z, this.x, this.z, regionX, regionZ);
						} else if (height >= endHeight - 4) {
							blocks[x][height][z] = currentBiome.crateBlock2(x, height, z, this.x, this.z, regionX, regionZ);
						
						}else {
							blocks[x][height][z] = new BlockStone(x, height, z, this.x, this.z, regionX, regionZ);
						}
						}else {
							endHeight = 70;
								
									
								if (height > realHeight) {
									blocks[x][height][z] = new BlockWater(x, height, z, this.x, this.z, regionX, regionZ);
								
								}else {
									blocks[x][height][z] = new BlockSand(x, height, z, this.x, this.z, regionX, regionZ);
								}
								
						}
						// fw.write();
					}
					currentBiome.checkForStruture(x+(this.getGlobalX() *16), realHeight, z+(this.getGlobalZ() *16), genValue);
					int startY = 0;
					int lastBlockType = 0;
					if(blocks[x][0][z] != null) {
						lastBlockType = blocks[x][0][z].getID();
					}  
					int blockType = 0;
					for (int y = 1; y < 256; y++) {
						
						if(blocks[x][y][z] != null) {
							blockType = blocks[x][y][z].getID();
						}else {
							blockType = 0;
						}
						if(blockType != lastBlockType) {
							//fw.append(lastBlockType + " " + x + " " + startY + " " + z + " " + ((y-1)-startY)+"\n");
							//dos.append(new String(new int[] {lastBlockType, x, startY,z,((y-1)-startY)}, 0, 5));
							dos.writeInt(lastBlockType);
							dos.writeInt(x);
							dos.writeInt(startY);
							dos.writeInt(z);
							dos.writeInt(((y-1)-startY));
							
							startY = y;
							lastBlockType = blockType;
						}
					}
					
					//fw.append(blockType + " " + x + " " + startY + " " + z + " " + (255-startY)+"\n");
					///fw.append(new String(new int[] {blockType, x, startY,z,(255-startY)}, 0, 5));
					dos.writeInt(blockType);
					dos.writeInt(x);
					dos.writeInt(startY);
					dos.writeInt(z);
					dos.writeInt((255-startY));
					//dos.writeFloat(1f);	
				}
			}
			dos.close();
			fullyLoaded = true;

		} else {
			DataInputStream chunkReader = new DataInputStream(new FileInputStream(chunkFile));
			int code = 0;
			while (chunkReader.available() > 0) {
				code = chunkReader.readInt();
				int code2 = chunkReader.readInt();
				
				int code3 = chunkReader.readInt();
			
				int code4 = chunkReader.readInt();
		
				int code5 = chunkReader.readInt();
				
				
		
				int data[] = {code, code2, code3, code4,code5};
				
				
				int x = data[1];
				int z = data[3];
				
				int endY = data[4];
				int startPos =data[2];
				if(data[0] != 0) {
					
				for (int y = startPos; y <=startPos+ endY; y++) {
					
					blocks[x][y][z] = (Block) Class.forName(BlockTypes[data[0]]).getConstructors()[0]
							.newInstance(x, y, z, this.x, this.z, regionX, regionZ);
					//blocks[x][y][z].height = height;
				
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
					if(blocks[localX][y][localZ].visible) {
					Block block1 = null;
					Block block2 = null;
					Block block3 = null;
					Block block4 = null;
					Block block5 = null;
					Block block6 = null;
					try {
					
						block1 = World.getBlock((int)blocks[localX][y][localZ].getGlobalX(), y+1, (int)blocks[localX][y][localZ].getGlobalZ());
						block2 = World.getBlock((int)blocks[localX][y][localZ].getGlobalX(), y-1, (int)blocks[localX][y][localZ].getGlobalZ());
						block3 = World.getBlock((int)blocks[localX][y][localZ].getGlobalX()+1, y, (int)blocks[localX][y][localZ].getGlobalZ());
						block4 = World.getBlock((int)blocks[localX][y][localZ].getGlobalX()-1, y, (int)blocks[localX][y][localZ].getGlobalZ());
						block5 = World.getBlock((int)blocks[localX][y][localZ].getGlobalX(), y, (int)blocks[localX][y][localZ].getGlobalZ()+1);
						block6 = World.getBlock((int)blocks[localX][y][localZ].getGlobalX(), y, (int)blocks[localX][y][localZ].getGlobalZ()-1);
					
						
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
						
				int x = (int) blocks[localX][y][localZ].getGlobalX();
				int z = ((int) blocks[localX][y][localZ].getGlobalZ());
				
				if(physicsUtils.getNextBlockInDirection(x, y, z, 0, 1, 0,256)!=null && physicsUtils.getNextBlockInDirection(x, y+1, z, 1, 0, 0,256)!=null && physicsUtils.getNextBlockInDirection(x, y+1, z, -1, 0, 0,256)!=null &&physicsUtils.getNextBlockInDirection(x, y+1, z, 0, 0, 1,256)!=null&&physicsUtils.getNextBlockInDirection(x, y+1, z, 0, 0, -1,256)!=null ) {
					System.out.println("works");
					
					 blocks[localX][y][localZ].topLight = 0.3f;
				}
				if(physicsUtils.getNextBlockInDirection(x, y, z, 0, -1, 0,(int)256)!=null &&physicsUtils.getNextBlockInDirection(x, y-1, z, 1, 0, 0,256)!=null && physicsUtils.getNextBlockInDirection(x, y-1, z, -1, 0, 0,256)!=null &&physicsUtils.getNextBlockInDirection(x, y-1, z, 0, 0, 1,(int)256)!=null&&physicsUtils.getNextBlockInDirection(x, y-1, z, 0, 0, -1,256)!=null ) {
					 blocks[localX][y][localZ].bottomLight = 0.3f;
				}
				if(physicsUtils.getNextBlockInDirection(x,y,z, 1,0,0,256)!=null && physicsUtils.getNextBlockInDirection(x+1,y,z, 0,1,0,256)!=null && physicsUtils.getNextBlockInDirection(x+1,y,z, 0,-1,0,256) !=null && physicsUtils.getNextBlockInDirection(x+1,y,z, 0,0,1,256)!=null && physicsUtils.getNextBlockInDirection(x+1,y,z, 0,0,-1,256) !=null) {
					 blocks[localX][y][localZ].rightLight = 0.3f;
				}
		if(physicsUtils.getNextBlockInDirection(x,y,z, -1,0,0,256)!=null && physicsUtils.getNextBlockInDirection(x-1,y,z, 0,1,0,256)!=null && physicsUtils.getNextBlockInDirection(x-1,y,z, 0,-1,0,256) !=null && physicsUtils.getNextBlockInDirection(x-1,y,z, 0,0,1,256)!=null && physicsUtils.getNextBlockInDirection(x-1,y,z, 0,0,-1,256) !=null) {
			 blocks[localX][y][localZ].leftLight = 0.3f;
				}
		if(physicsUtils.getNextBlockInDirection(x,y,z, 0,0,1,256)!=null && physicsUtils.getNextBlockInDirection(x,y,z+1, 0,1,0,256)!=null && physicsUtils.getNextBlockInDirection(x,y,z+1, 0,-1,0,256) !=null && physicsUtils.getNextBlockInDirection(x,y,z+1, 1,0,0,256)!=null && physicsUtils.getNextBlockInDirection(x,y,z+1, -1,0,0,256) !=null) {
			 blocks[localX][y][localZ].frontLight = 0.3f;
		}
		if(physicsUtils.getNextBlockInDirection(x,y,z, 0,0,-1,256)!=null && physicsUtils.getNextBlockInDirection(x,y,z-1, 0,1,0,256)!=null && physicsUtils.getNextBlockInDirection(x,y,z-1, 0,-1,0,256) !=null && physicsUtils.getNextBlockInDirection(x,y,z-1, 1,0,0,256)!=null && physicsUtils.getNextBlockInDirection(x,y,z-1, -1,0,0,256) !=null) {
			 blocks[localX][y][localZ].backLight = 0.3f;
		}}
				}
				}
			}
		}
	}
}
	public void save() throws IOException {
		File chunkFile = new File("C:\\Opencraft\\worlds\\"+World.worldName+"\\chunks\\chunk(" +(x+(16*regionX)) + "," + (z+(16*regionZ)) + ").opencraftChunk");
		
		
		  FileOutputStream fos = new FileOutputStream(chunkFile);
		  BufferedOutputStream bos=new BufferedOutputStream(fos);
		  DataOutputStream dos=new DataOutputStream(bos); 
		 
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
				int lastBlockType = 0;
				if(blocks[x][0][z] != null) {
					lastBlockType = blocks[x][0][z].getID();
				}
				int blockType = 0;
				for (int y = 1; y < 256; y++) {
					
					if(blocks[x][y][z] != null) {
						blockType = blocks[x][y][z].getID();
					}else {
						blockType = 0;
					}
					if(blockType != lastBlockType) {
						//fw.append(lastBlockType + " " + x + " " + startY + " " + z + " " + ((y-1)-startY)+"\n");
						//dos.append(new String(new int[] {lastBlockType, x, startY,z,((y-1)-startY)}, 0, 5));
						dos.writeInt(lastBlockType);
						dos.writeInt(x);
						dos.writeInt(startY);
						dos.writeInt(z);
						dos.writeInt(((y-1)-startY));
						
						startY = y;
						lastBlockType = blockType;
					}
				}
				
				//fw.append(blockType + " " + x + " " + startY + " " + z + " " + (255-startY)+"\n");
				///fw.append(new String(new int[] {blockType, x, startY,z,(255-startY)}, 0, 5));
				dos.writeInt(blockType);
				dos.writeInt(x);
				dos.writeInt(startY);
				dos.writeInt(z);
				dos.writeInt((255-startY));
				
			}
		}
		dos.close();
			
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
		ArrayList<Block> water = new ArrayList<>();
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
					///if(block1 != null || block2 == null)
					///blocks[x][y][z].visible = true;
						if(!blocks[x][y][z].isFluid()) {
							int localX = x ;
							int localZ = z;
							if(physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(), y,blocks[x][y][z].getGlobalZ(), 0, 1, 0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(), y+1,blocks[x][y][z].getGlobalZ(), 1, 0, 0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(), y+1,blocks[x][y][z].getGlobalZ(), -1, 0, 0,64)!=null &&physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(), y+1,blocks[x][y][z].getGlobalZ(), 0, 0, 1,64)!=null&&physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(), y+1,blocks[x][y][z].getGlobalZ(), 0, 0, -1,64)!=null ) {
								
								
								 blocks[localX][y][localZ].topLight = 0.3f;
							}else {
								blocks[localX][y][localZ].topLight = 1f;
							}
							if(physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(), y,blocks[x][y][z].getGlobalZ(), 0, -1, 0,(int)64)!=null &&physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(), y-1,blocks[x][y][z].getGlobalZ(), 1, 0, 0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(), y-1,blocks[x][y][z].getGlobalZ(), -1, 0, 0,64)!=null &&physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(), y-1,blocks[x][y][z].getGlobalZ(), 0, 0, 1,(int)64)!=null&&physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(), y-1,blocks[x][y][z].getGlobalZ(), 0, 0, -1,64)!=null ) {
								 blocks[localX][y][localZ].bottomLight = 0.3f;
							}else {
								blocks[localX][y][localZ].bottomLight = 1f;
							}
							if(physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ(), 1,0,0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX()+1,y,blocks[x][y][z].getGlobalZ(), 0,1,0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX()+1,y,blocks[x][y][z].getGlobalZ(), 0,-1,0,64) !=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX()+1,y,blocks[x][y][z].getGlobalZ(), 0,0,1,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX()+1,y,blocks[x][y][z].getGlobalZ(), 0,0,-1,64) !=null) {
								 blocks[localX][y][localZ].rightLight = 0.3f;
							}else {
								blocks[localX][y][localZ].rightLight = 1f;
							}
					if(physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ(), -1,0,0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX()-1,y,blocks[x][y][z].getGlobalZ(), 0,1,0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX()-1,y,blocks[x][y][z].getGlobalZ(), 0,-1,0,64) !=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX()-1,y,blocks[x][y][z].getGlobalZ(), 0,0,1,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX()-1,y,blocks[x][y][z].getGlobalZ(), 0,0,-1,64) !=null) {
						 blocks[localX][y][localZ].leftLight = 0.3f;
							}else {
								blocks[localX][y][localZ].leftLight = 1f;
							}
					if(physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ(), 0,0,1,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ()+1, 0,1,0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ()+1, 0,-1,0,64) !=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ()+1, 1,0,0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ()+1, -1,0,0,64) !=null) {
						 blocks[localX][y][localZ].frontLight = 0.3f;
					}else {
						 blocks[localX][y][localZ].frontLight = 1f;
					}
					if(physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ(), 0,0,-1,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ()-1, 0,1,0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ()-1, 0,-1,0,64) !=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ()-1, 1,0,0,64)!=null && physicsUtils.getNextBlockInDirection(blocks[x][y][z].getGlobalX(),y,blocks[x][y][z].getGlobalZ()-1, -1,0,0,64) !=null) {
						 blocks[localX][y][localZ].backLight = 0.3f;
					}else {
						blocks[localX][y][localZ].backLight = 1f;
					}
								blocks[x][y][z].draw(block1 == null || block1.isFluid(),block2 == null|| block2.isFluid(),block6 == null || block6.height < 1f|| block6.isFluid(),block5 == null || block5.height < 1f|| block5.isFluid(),block3 == null || block3.height < 1f|| block3.isFluid(),block4 == null || block4.height < 1f|| block4.isFluid());
							}else {
								water.add(blocks[x][y][z]);
							}
						
						
					
				}
			}
			
			}
		}
		GL11.glEnd();
		GL11.glEndList();
		
		if(waterID == -1) {
			waterID = GL11.glGenLists(1);
			}else {
				GL11.glDeleteLists(waterID,1);
			}
			GL11.glNewList(waterID, GL11.GL_COMPILE);
			GL11.glBegin(GL11.GL_QUADS);
			for(Block waterBlock : water) {
				int x = (int) waterBlock.getX();
				int y = (int) waterBlock.getY();
				int z = (int) waterBlock.getZ();
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
				waterBlock.draw(block1 == null,block2 == null,block6 == null || block6.height < 1f,block5 == null || block5.height < 1f,block3 == null || block3.height < 1f,block4 == null || block4.height < 1f);
			}
			GL11.glEnd();
			GL11.glEndList();
		//World.chunkDrawIDs[id] = id;
	
		

}

	public void draw() {
		//.out.println("DID: "+id);
		GL11.glCallList(id);
		
	}
	public void drawWater() {
		//.out.println("DID: "+id);
		GL11.glCallList(waterID);
		
	}
	public void delete() {
		if(id != -1) {
		GL11.glDeleteLists(id,1);
		}
		if(waterID != -1) {
			GL11.glDeleteLists(waterID,1);
			}
	}

}
