package opencraft;

public class NormalWorldGenerator {
//  static int seed =(int)(Math.random()*(1000000-0+1)+0);
	public static int seed =-2345;
	public static boolean randomSeed = true;
	public static Gen g = new Gen(seed);
	public static Gen MoutainGen = new Gen(seed);
	static {
		MoutainGen.OCTAVES=7;
		MoutainGen.AMPLITUDE=400;
		MoutainGen.ROUGHNESS = 0.2f;
	}
	
	public static Gen WaterGen = new Gen(seed);
	static {
		WaterGen.OCTAVES=7;
		WaterGen.AMPLITUDE=400;
		WaterGen.ROUGHNESS = 0.3f;
	}
	public static Gen BiomeGen = new Gen(seed);
	static {
		BiomeGen .OCTAVES=9;
		BiomeGen.AMPLITUDE=300;
		BiomeGen.ROUGHNESS = 0.3f;
	}
	public static Gen caveGen1 = new Gen(seed);
	static {
		caveGen1.OCTAVES=7;
		caveGen1.AMPLITUDE=400;
		caveGen1.ROUGHNESS = 0.3f;
	}
	public static Gen caveGen2 = new Gen(seed);
	static {
		caveGen2.OCTAVES=7;
		caveGen2.AMPLITUDE=300;
		caveGen2.ROUGHNESS = 0.3f;
	}
	public static NoiseGenerator caveGen = new NoiseGenerator(seed);

	public static int generateHeight(int x, int z) {
		
		int h = (int)(g.generateHeight(x, z)+75);
		int mh = (int) (MoutainGen.generateHeight(x, z)+50);
		int wh = (int) (WaterGen.generateHeight(x, z)+100);
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
	
		
		//return (int) caveGen1.generateHeight(x, z)+70;
	
}
