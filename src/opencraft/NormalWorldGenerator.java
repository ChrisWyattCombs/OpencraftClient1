package opencraft;

public class NormalWorldGenerator {
//  static int seed =(int)(Math.random()*(1000000-0+1)+0);
	static int seed =0;
	public static Gen g = new Gen(seed);
	public static Gen MoutainGen = new Gen(seed);
	static {
		MoutainGen.OCTAVES=7;
		MoutainGen.AMPLITUDE=400;
		MoutainGen.ROUGHNESS = 0.3f;
	}public static Gen BiomeGen = new Gen(seed);
	static {
		BiomeGen .OCTAVES=9;
		BiomeGen.AMPLITUDE=150;
		BiomeGen.ROUGHNESS = 0.3f;
	}

	public static int generateHeight(int x, int z) {
		
		int h = (int)(g.generateHeight(x, z)+75);
		int mh = (int) (MoutainGen.generateHeight(x, z)+50);
		int wh = (int) (MoutainGen.generateHeight(x, z)+350);
		if(wh < h) {
			if(wh < 30) {
				return 30;
			}
					
			return wh;
		}
		if(mh > h) {
			if(mh > 250) {
				return 250;
			}
			return mh;
		}
		return h;
		//return (int) BiomeGen.generateHeight(x, z)+50;
	}
}
