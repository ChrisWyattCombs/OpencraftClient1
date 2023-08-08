package opencraft;

public class Region {
private int x;
private int z;
public Chunk[][] chunks;



public Region(int x, int z, Chunk[][] chunks) {
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


}
