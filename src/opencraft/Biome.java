package opencraft;

public abstract class Biome {
public abstract Block crateBlock1(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ);
public abstract Block crateBlock2(int x, int y, int z, int chunkX, int chunkZ, int regionX, int regionZ);
public abstract void checkForStruture(int x,int y, int z, float genValue);
}
