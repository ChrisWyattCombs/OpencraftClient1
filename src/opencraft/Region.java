package opencraft;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Region {
private int x;
private int z;
public Chunk[][] chunks;
DataInputStream reader;
DataOutputStream writer;


public Region(int x, int z, Chunk[][] chunks) throws FileNotFoundException {
	super();
	this.x = x;
	this.z = z;
	this.chunks = chunks;
	
}
public int getX() {
	return x;
}
public int getZ() {
	return z;
}

public void draw() {
	for(int x = 0; x < 16; x++) {
		for(int z = 0; z < 16; z++) {
			if(chunks[x][z] != null) {
				chunks[x][z].draw();
			}
		}
	}
}

public void drawWater() {
	for(int x = 0; x < 16; x++) {
		for(int z = 0; z < 16; z++) {
			if(chunks[x][z] != null) {
				chunks[x][z].drawWater();
			}
		}
	}
}
public void delete() {
	try {
		reader.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		writer.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
