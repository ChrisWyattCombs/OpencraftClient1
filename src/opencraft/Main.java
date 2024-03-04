package opencraft;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.util.ResourceLoader;

import opencraft.audio.AudioUtills;
import opencraft.audio.AudioVaribles;
import opencraft.graphics.DisplayUtills;
import opencraft.graphics.DisplayVariables;
import opencraft.graphics.ResourceManager;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
public class Main {
	///public static int userID = -1;
	//public static int code = -1;
	public static void main(String[] args) throws Exception {
		//userID = Integer.valueOf(args[0]);
		//code = Integer.valueOf(args[1]);
		
		runOpencraft("Beta");

	}
	public static void runOpencraft(String version) throws Exception {
		DisplayUtills.createWindow("opencraft "+version, 1280, 720, false);
		DisplayUtills.setupOpenGl();
	DisplayUtills.loadResources();
	AudioUtills.loadSounds();
		while (!Display.isCloseRequested() && !DisplayVariables.close) {
			long startTime = System.currentTimeMillis();
			long lastTime = System.nanoTime();
			DisplayUtills.renderNextFrame();
			AudioUtills.updateSoundSystem();
            Display.update();
            //DisplayUtills.FPS(60,System.currentTimeMillis() - startTime);
            DisplayVariables.deltaTime = System.currentTimeMillis() - startTime;
            DisplayVariables.fps = -(1000000000.0 / (lastTime - (lastTime = System.nanoTime())));
            if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            	DisplayVariables.pressedEsc = false;
            }
           
           
		}
		AudioVaribles.soundSystem.cleanup();
		System.exit(0);
	}

}
