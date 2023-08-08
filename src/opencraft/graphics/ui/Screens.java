package opencraft.graphics.ui;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import javax.xml.stream.events.StartDocument;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

import opencraft.Block;
import opencraft.World;
import opencraft.graphics.DisplayUtills;
import opencraft.graphics.DisplayVariables;
import opencraft.physics.physicsUtils;

public class Screens {

public static Screen mainMenu = new Screen() {
	Button playButton = new Button("Play",0,0,0.3f,0.1f,-0.0005f,-0.0001f,0.000006f) {
		
		@Override
		public void action() {
			currentScreen = worlds;
			
		}
	};
	@Override
	public void drawScreen() {
		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);   
		
		glLoadIdentity();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		glDepthMask(false);
	 //glColor3f(	135f/255f, 206f/255f, 235f/255f);
		
		GL11.glClearColor(135f/255f, 206f/255f, 235f/255f,1);
		//glColor3f(	1,1, 1);
		//glBindTexture(GL_TEXTURE_2D, 0);
	//glDepthMask(true);
	GL11.glEnable(GL11.GL_TEXTURE_2D);
		playButton.drawButton();
		Cursor.updateAndDrawMouse();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)&& !DisplayVariables.pressedEsc) {
        	DisplayVariables.close = true;
        }
		
	}
};
public static Screen worlds = new Screen() {
	Button createButton = new Button("Create new world",0,-0.0055f,0.3f,0.1f,-0.0015f,-0.0001f,0.000006f) {

		@Override
		public void action() {
			new Thread() {
				public void run() {
					World.loadWorld();
					
				}
			}.start();
			currentScreen = loadingWorld;
			
		}
		
	};
	@Override
	public void drawScreen() {
glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);   
		
		glLoadIdentity();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		/*
		glDepthMask(false);
	 glColor3f(	135f/255f, 206f/255f, 235f/255f);
	
		DisplayUtills.drawSkybox();
		glColor3f(	1,1, 1);
		*/
		//glBindTexture(GL_TEXTURE_2D, 0);
	//glDepthMask(true);
		GL11.glClearColor(135f/255f, 206f/255f, 235f/255f,1);
	GL11.glEnable(GL11.GL_TEXTURE_2D);
		//playButton.drawButton();
	 drawOuutline();
	 createButton.drawButton();
		Cursor.updateAndDrawMouse();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)&& !DisplayVariables.pressedEsc) {
			currentScreen = mainMenu;
			DisplayVariables.pressedEsc = true;
        }
		
	}
	
private void drawOuutline() {
	GL11.glColor3f(131f/255f,101f/255f,57f/255f);
	DisplayUtills.drawSqaure(3, 0.00775f/0.005f, 0, 0, -0.02f);
	glColor3f(	0,0, 0);
	DisplayUtills.drawSqaure(3, 0.05f, 0, 0.0075f, -0.02f);
	DisplayUtills.drawSqaure(3, 0.05f, 0, -0.0075f, -0.02f);
	DisplayUtills.drawSqaure(0.05f,0.00775f/0.005f, 0.005f*3, 0, -0.02f);
	DisplayUtills.drawSqaure(0.05f,0.00775f/0.005f, -0.005f*3, 0, -0.02f);
	glColor3f(	1,1, 1);
	
	
}
};
public static Screen loadingWorld = new Screen() {
	
	@Override
	public void drawScreen() {
		DisplayUtills.shader.unbind();
		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		glLoadIdentity();
		GL11.glClearColor(131f/255f,101f/255f,57f/255f,1);
		glColor3f(128f/255f,128f/255f,128f/255f);
		
		DisplayUtills.drawSqaure(3, 0.05f, 0, 0, -0.02f);
		glColor3f(0,1,0);
		DisplayUtills.drawSqaureFromLeft(2.5f*(float)World.worldLoadProgress, 0.05f, -2f * 0.005f, 0, -0.02f);
		// TODO Auto-generated method stub
		glColor3f(1,1,1);
		DisplayUtills.font.drawText("Loading World", -0.0035f, 0.002f, 0.00002f);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		if(World.worldLoadProgress == 1) {
			GL11.glClearColor(135f/255f, 206f/255f, 235f/255f,1);
			try {
			World.setupWorld();
			}catch (Exception e) {
				e.printStackTrace();
			}
			currentScreen = inGame;
			//DisplayUtills.shader.bind();
			
			DisplayUtills.shader.unbind();
		}
	}
	
};
public static Screen inGame = new Screen() {

	@Override
	public void drawScreen() {
		glDepthMask(true);
glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);   
GL11.glEnable(GL11.GL_TEXTURE_2D);
glLoadIdentity();

GL11.glRotatef(DisplayVariables.camYaw,0.0f,1.0f,0.0f);
GL11.glRotatef(-DisplayVariables.CamPitch,(float)(Math.cos(Math.toRadians(DisplayVariables.camYaw))),0.0f,(float)(Math.sin(Math.toRadians(DisplayVariables.camYaw))));
GL11.glTranslatef (-DisplayVariables.camX, -DisplayVariables.camY, DisplayVariables.camZ);  

//DisplayUtills.shader.bind();
GL30.glUniform1ui(DisplayUtills.shader.uniforms.get("tex"),World.blockTextures.getTextureID());
//GL13.glActiveTexture(GL13.GL_TEXTURE2);
System.out.println("TID: "+World.blockTextures.getTextureID());
GL11.glBindTexture(GL11.GL_TEXTURE_2D, World.blockTextures.getTextureID());

World.drawWorld();
glLoadIdentity();
//DisplayUtills.drawSqaure(2, 2, 0,0, -0.02f);
GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

DisplayUtills.shader.unbind();
glDepthMask(false);
DisplayUtills.font.drawText("Fps: "+String.valueOf(Math.round(DisplayVariables.fps)), -0.0165f, 0.0080f, 0.00002f);
int x = physicsUtils.convertFloatCoordToBlockCoord(DisplayVariables.camX);
int y =  physicsUtils.convertFloatCoordToBlockCoord(DisplayVariables.camY-2);
int z = physicsUtils.convertFloatCoordToBlockCoord(DisplayVariables.camZ);
DisplayUtills.font.drawText("Standing on Block: "+ x+" "+y+" "+z, -0.0165f, 0.0070f, 0.00002f);
float bx = 0;
float by = 0;
float bz = 0;
try {
	Block b = physicsUtils.getBlockLookingAt();
	bx = b.getGlobalX();
	by = b.getY();
	bz = b.getGlobalZ();
}catch(Exception e) {
	
}
float nx = (float) (Math.cos(Math.toRadians(DisplayVariables.camYaw))*Math.cos(Math.toRadians(DisplayVariables.CamPitch)));
float nz = (float) (Math.sin(Math.toRadians(DisplayVariables.camYaw))*Math.cos(Math.toRadians(DisplayVariables.CamPitch)));
		float ny = (float) Math.sin(Math.toRadians(DisplayVariables.CamPitch));
DisplayUtills.font.drawText("looking at Block: "+bx+" "+" "+by+" "+bz, -0.0165f, 0.0060f, 0.00002f);
DisplayUtills.font.drawText("looking at: "+nx+" "+" "+ny+" "+nz, -0.0165f, 0.0050f, 0.00002f);
GL11.glDisable(GL11.GL_TEXTURE_2D);
controls();

	}
	public void controls() {
		DisplayVariables.camYaw += Mouse.getDX()/2;
		DisplayVariables.CamPitch += Mouse.getDY()/2;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			DisplayVariables.camZ += 0.01f*Math.cos(Math.toRadians( DisplayVariables.camYaw))*DisplayVariables.deltaTime;
			DisplayVariables.camX += 0.01f*Math.sin(Math.toRadians( DisplayVariables.camYaw))*DisplayVariables.deltaTime;
		}
		if(DisplayVariables.CamPitch > 90) {
			DisplayVariables.CamPitch = 90;
		}
		if(DisplayVariables.CamPitch < -90) {
			DisplayVariables.CamPitch = -90;
		}
	}
	
};

public static Screen currentScreen = mainMenu;
}
