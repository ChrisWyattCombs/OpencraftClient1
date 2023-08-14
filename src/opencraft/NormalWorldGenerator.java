package opencraft;

public class NormalWorldGenerator {
	static Gen g = new Gen(0);
	public static int generateHeight(int x, int z) {
		
		
		return ((int)(g.generateHeight(x, z)+60));
	}
}
