package opencraft.graphics;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Drawable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.opengl.PixelFormatLWJGL;
import org.lwjgl.opengl.SharedDrawable;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.PNGDecoder;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import static org.lwjgl.opengl.GL13.*;

import opencraft.Chunk;
import opencraft.World;
import opencraft.graphics.models.ModelCube;
import opencraft.graphics.ui.Cursor;
import opencraft.graphics.ui.Screens;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_TEXTURE_MAX_LEVEL;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DisplayUtills {
private static int[] skyboxTextures = new int[6];


//private static Texture skybox;
private static HashMap<Character, Vector2f> charToCoord = new HashMap<>(); 
public static ShaderProgram fontShader;
public static ShaderProgram shader;
public static ShaderProgram worldShader;
public static void createWindow(String title, int width, int height, boolean isFullscreen) {
	try {
		 DisplayMode displayMode = null;
	        DisplayMode[] modes = Display.getAvailableDisplayModes();

	         for (int i = 0; i < modes.length; i++)
	         {
	             if (modes[i].getWidth() == width
	             && modes[i].getHeight() == height
	             && modes[i].isFullscreenCapable())
	               {
	                    displayMode = modes[i];
	               }
	         }
        Display.setDisplayMode(displayMode);
        DisplayVariables.width = width;
        DisplayVariables.height = height;
        Display.setFullscreen(isFullscreen);
        
        Display.create();
        GL11.glEnable(GL13.GL_MULTISAMPLE);
        //GL11.glEnable(GL13.GL_SAMPLE_ALPHA_TO_COVERAGE);
        
    } catch (LWJGLException e) {
        e.printStackTrace();
        System.exit(0);
    }
  
}
public static void setupOpenGl() throws Exception {
	DisplayVariables.openglContex = GLContext.getCapabilities();
	glMatrixMode (GL_PROJECTION);                               // Select The Projection Matrix
    glLoadIdentity ();
    
// Reset The Projection Matrix
    GLU.gluPerspective (50f,(float)DisplayVariables.width/(float)DisplayVariables.height, 0.01f, 10000.0f);        // Calculate The Aspect Ratio Of The Window 
    
    
    glMatrixMode (GL_MODELVIEW);                                // Select The Modelview Matrix
    glLoadIdentity ();   
    //glClearDepth (1.0f);    
    //glEnable(GL13.GL_TEXTURE0);
    //glEnable(GL13.GL_TEXTURE1);
    ///GL11.glCullFace(4);
    glEnable (GL_DEPTH_TEST); 
    
    //glDepthFunc (GL_); 
    //glClearColor(0, 0,0, 1);
    //GL11.glEnable(GL11.GL_ALPHA_TEST);
    //glFrontFace(GL_CCW);
  //  glEnable(GL_CULL_FACE); 
    //glCullFace(GL_FRONT);
    //GL11.glFrontFace(GL11.GL_CW);  
    GL11.glShadeModel (GL11.GL_SMOOTH);
GL11.glEnable(GL11.GL_BLEND);
  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
  
	glEnable(GL_ALPHA_TEST);
	//GL11.glAlphaFunc(GL_GREATER, 0.7f);
	fontShader =new ShaderProgram();
	Scanner f = new Scanner(new File("shader/FragShader"));
	String fc = "";
	while(f.hasNextLine()) {
		fc+=f.nextLine()+"\n";
	}
	f.close();
	
	fontShader.createFragmentShader(fc);
	Scanner v = new Scanner(new File("shader/VertexShader"));
	String vc = "";
	while(v.hasNextLine()) {
		vc+=v.nextLine()+"\n";
	}
	v.close();
	
	fontShader.createVertexShader(vc);
	
	fontShader.link();
	fontShader.createUniform("tex");
	shader =new ShaderProgram();
	Scanner f2 = new Scanner(new File("shader/NormalFragShader"));
	String f2c = "";
	while(f2.hasNextLine()) {
		f2c+=f2.nextLine()+"\n";
	}
	f2.close();
	
	shader.createFragmentShader(f2c);
	Scanner v2 = new Scanner(new File("shader/VertexShader"));
	String v2c = "";
	while(v2.hasNextLine()) {
		v2c+=v2.nextLine()+"\n";
	}
	v.close();
	
	shader.createVertexShader(vc);
	
	shader.link();
	shader.createUniform("tex");
	
	
	
	
	worldShader =new ShaderProgram();
	Scanner f3 = new Scanner(new File("shader/WorldFragShader"));
	String f3c = "";
	while(f3.hasNextLine()) {
		f3c+=f3.nextLine()+"\n";
	}
	f2.close();
	
	worldShader.createFragmentShader(f3c);

	
	worldShader.createVertexShader(vc);
	
	worldShader.link();
	worldShader.createUniform("tex");
	worldShader.createUniform("viewPos");
	Cursor.setup();
	Drawable context = new SharedDrawable(Display.getDrawable());
	
	new Thread() {
		@Override
		public void run() {
			
			try {
				context.makeCurrent();
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(true) {
				ArrayList<Vector2i> chunks = World.getChunksToSetup();
			if(chunks.size() >0) {
				
				
				Vector2i chunkPosition = chunks.get(0);
				
				World.setupChunk(chunkPosition.getX(), chunkPosition.getY());
			World.removeChunkToSetup(chunkPosition);
			}
				
			
			}
		
	}
	}.start();

}
public static void loadResources() throws IOException {
	//glBindTexture(GL_TEXTURE_2D, 0);
	//ResourceManager.loadTexture("Textures/font.png", "fontTexture");
	//skybox = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/image.png"));
	
	//"C:\Opencraft\assests\textures\waterOverlay.png"
	ResourceManager.addResource("Opencraft:WaterOverlayTexture", TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("C:\\Opencraft\\assests\\textures\\waterOverlay.png")));
	opencraft.graphics.ui.Font font = new opencraft.graphics.ui.Font("C:\\Opencraft\\assests\\font.ttf");
	ResourceManager.addResource("Opencraft:Font", font);
	Texture blockTextures = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("C:\\Opencraft\\assests\\textures\\TexturePack.png"));
	ResourceManager.addResource("Opencraft:BlockTextures", blockTextures);
	glBindTexture(GL_TEXTURE_2D, blockTextures.getTextureID());
	GL30.glGenerateMipmap(GL_TEXTURE_2D);
	glTexParameteri (GL_TEXTURE_2D, GL_TEXTURE_MAX_LEVEL, 4);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	glBindTexture(GL_TEXTURE_2D, 0);

}

public static void renderNextFrame() {

	Screens.currentScreen.drawScreen();
    

}

public static void DrawLetterQuad(float x, float y, float z, float width, float height) {
	
	glBegin(GL11.GL_QUADS);
	
	
	glVertex3f(x +(width), y+(height), z);
	glTexCoord2f(0, 0);
	
	
	
	glVertex3f(x,y+(height), z);
	glTexCoord2f(0, 1);
	
	
	
	glVertex3f(x, y, z);
	glTexCoord2f(1, 1);
	
	
	glVertex3f(x+width, y, z);
	glTexCoord2f(1, 0);
	glEnd();
	
}
public static void drawSqaure(float width,float height, float x,float y,float z) {
	glBegin(GL_QUADS);
	glTexCoord2f(1, 0);glVertex3f(0.005f * width+x, 0.005f * height+y, z);
	
	glTexCoord2f(0, 0f);glVertex3f(-0.005f * width+x, 0.005f * height+y, z);
	
	glTexCoord2f(0, 1f);glVertex3f(-0.005f * width+x, -0.005f * height+y, z);
	
	glTexCoord2f(1, 1f);glVertex3f(0.005f * width+x, -0.005f * height+y, z);
	
	glEnd();
}
public static void drawSqaureFromLeft(float width,float height, float x,float y,float z) {
	glBegin(GL_QUADS);
	glVertex3f(0.005f * (width*2)+x, 0.005f * height+y, z);
	//glTexCoord2f(0, 0);
	glVertex3f(-0.005f+x, 0.005f * height+y, z);
	//glTexCoord2f(0, 1f);
	glVertex3f(-0.005f +x, -0.005f * height+y, z);
	//glTexCoord2f(1f, 1f);
	glVertex3f(0.005f * (width*2)+x, -0.005f * height+y, z);
	//glTexCoord2f(1f, 0);
	glEnd();
}
public static void drawSqaureFromTop(float width,float height, float x,float y,float z) {
	glBegin(GL_QUADS);
	glTexCoord2f(1, 0);glVertex3f(0.005f * width+x, 0.005f +y, z);
	
	glTexCoord2f(0, 0f);glVertex3f(-0.005f * width+x, 0.005f +y, z);
	
	glTexCoord2f(0, 1f);glVertex3f(-0.005f * width+x, -0.005f * (height*2)+y, z);
	
	glTexCoord2f(1, 1f);glVertex3f(0.005f * width+x, -0.005f * (height*2)+y, z);
	
	glEnd();
}

}
