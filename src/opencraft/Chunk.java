package opencraft;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import opencraft.blocks.BlockDirt;
import opencraft.blocks.BlockGrass;
import opencraft.blocks.BlockStone;
import opencraft.graphics.DisplayUtills;

public class Chunk {
	private int x;
	private int z;
	private int regionX;
	private int regionZ;
	private int id = -1;
	public Block[][][] blocks;

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

					int endHeight = FlatWorldGenerator.generateHeight(x * (this.x * 16), z * (this.z * 16));
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
					fw.append("BlockGrass " + x + " " + endHeight + " " + z + " " + 0+"\n");
					fw.append("BlockDirt " + x + " " + (endHeight - 4) + " " + z + " " + 3+"\n");

					fw.append("BlockStone " + x + " " + (endHeight - endHeight) + " " + z + " " + (endHeight - 5)+"\n");
				}
			}
			fw.close();

		} else {
			Scanner chunkReader = new Scanner(chunkFile);

			while (chunkReader.hasNextLine()) {
				String string = chunkReader.nextLine();
				
				String[] data = string.split(" ");
				
				
				int x = Integer.valueOf(data[1]);
				int z = Integer.valueOf(data[3]);

				int endY = Integer.valueOf(data[4]);
				int startPos = Integer.valueOf(data[2]);
				for (int y = startPos; y <=startPos+ endY; y++) {

					blocks[x][y][z] = (Block) Class.forName("opencraft.blocks." + data[0]).getConstructors()[0]
							.newInstance(x, y, z, this.x, this.z, regionX, regionZ);
				
				}
				
			}
			chunkReader.close();

		}

	}

	public void setup() {
		id = GL11.glGenLists(1);
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
					/*
						block1 = World.getBlock((int)blocks[x][y][z].getGlobalX(), y+1, (int)blocks[x][y][z].getGlobalZ());
						block2 = World.getBlock((int)blocks[x][y][z].getGlobalX(), y-1, (int)blocks[x][y][z].getGlobalZ());
						block3 = World.getBlock((int)blocks[x][y][z].getGlobalX()+1, y, (int)blocks[x][y][z].getGlobalZ());
						block4 = World.getBlock((int)blocks[x][y][z].getGlobalX()-1, y, (int)blocks[x][y][z].getGlobalZ());
						block5 = World.getBlock((int)blocks[x][y][z].getGlobalX(), y, (int)blocks[x][y][z].getGlobalZ()+1);
						block6 = World.getBlock((int)blocks[x][y][z].getGlobalX(), y, (int)blocks[x][y][z].getGlobalZ()-1);
					
						*/
						block1 = blocks[x+1][y][z];
						block2 = blocks[x-1][y][z];
						block3 = blocks[x][y+1][z];
						block4 = blocks[x][y-1][z];
						block5 = blocks[x][y][z+1];
						block6 = blocks[x][y][z-1];
					
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
		System.out.println("DID: "+id);
		GL11.glCallList(id);
		
	}

}
