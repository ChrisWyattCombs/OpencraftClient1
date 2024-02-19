package opencraft;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.util.ResourceLoader;

import opencraft.graphics.DisplayUtills;
import opencraft.graphics.DisplayVariables;
import opencraft.graphics.ResourceManager;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
public class Main {
	
	public static void main(String[] args) throws Exception {
		runOpencraft("Beta");

	}
	public static void runOpencraft(String version) throws Exception {
		DisplayUtills.createWindow("opencraft "+version, 1920, 1080, true);
		DisplayUtills.setupOpenGl();
	DisplayUtills.loadResources();
		
		while (!Display.isCloseRequested() && !DisplayVariables.close) {
			long startTime = System.currentTimeMillis();
			long lastTime = System.nanoTime();
			
			DisplayUtills.renderNextFrame();
			
            Display.update();
            DisplayVariables.deltaTime = System.currentTimeMillis() - startTime;
            DisplayVariables.fps = -(1000000000.0 / (lastTime - (lastTime = System.nanoTime())));
            if(!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            	DisplayVariables.pressedEsc = false;
            }
           
           
		}
	}

}
