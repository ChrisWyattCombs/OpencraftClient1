package opencraft.graphics.ui;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.xml.stream.events.StartDocument;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.Drawable;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.SharedDrawable;
import org.lwjgl.util.glu.GLU;

import com.google.common.io.Files;

import opencraft.Block;
import opencraft.Chunk;
import opencraft.NormalWorldGenerator;
import opencraft.Player;
import opencraft.World;
import opencraft.graphics.DisplayUtills;
import opencraft.graphics.DisplayVariables;
import opencraft.graphics.ResourceManager;
import opencraft.graphics.ShaderProgram;
import org.newdawn.slick.opengl.Texture;
import opencraft.graphics.Vector2i;
import opencraft.graphics.Vector3f;
import opencraft.graphics.models.ModelCube;
import opencraft.graphics.models.ModelPlayer;
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
		playButton.drawAndUpdate();
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
			/*
			new Thread() {
				public void run() {
					World.worldLoadProgress = 0;
					World.loadWorld();
					
				}
			}.start();
		*/
			
			//currentScreen = createWorld;
			currentScreen = createWorld;
		}
		
	};
	
	ArrayList<Button> worldButtons = new ArrayList<>();
	boolean loaded = false;
	@Override
	public void drawScreen() {
	if(!loaded) {
		File WorldDir = new File("C:\\Opencraft\\worlds\\");
		String[] worlds = WorldDir.list();
		float yOffset = 0.003f;
		for(String world : worlds) {
			
			Button worldButton = new Button(world,0,yOffset,0.3f,0.1f,-0.0005f,-0.0001f,0.000006f) {
				
				@Override
				public void action() throws LWJGLException {
					Drawable context = new SharedDrawable(Display.getDrawable());
					new Thread() {
						public void run() {
							World.worldName = getText();
							World.worldLoadProgress = 0;
							World.loadWorld(context);
							
							
						}
					}.start();
				
					
			
				currentScreen = loadingWorld;
					//currentScreen = worlds;
					
				}
			};
			worldButtons.add(worldButton);
			yOffset+=-0.002f;
		}
		loaded = true;
	}
	
	
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
	 //drawOuutline();
	 createButton.drawAndUpdate();
	 for(Button worldButton : worldButtons) {
			worldButton.drawAndUpdate();
		}
		Cursor.updateAndDrawMouse();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)&& !DisplayVariables.pressedEsc) {
			currentScreen = mainMenu;
			DisplayVariables.pressedEsc = true;
        }
		
	}
	

};
public static Screen createWorld = new Screen() {
	TextField worldName = new TextField("", 0, 0, 0.3f,12);
	TextField worldSeed = new TextField("", 0, -0.001f, 0.3f,12);
	Button createButton = new Button("Create",0,-0.002f,0.3f,0.1f,-0.0005f,-0.0001f,0.000006f) {
		
		@Override
		public void action() throws LWJGLException {
			Drawable context = new SharedDrawable(Display.getDrawable());
			new Thread() {
				
				public void run() {
					
					World.worldName = worldName.text;
					World.worldLoadProgress = 0;
					World.loadWorld(context);
					
				}
			}.start();
		
			
		currentScreen = loadingWorld;
			//currentScreen = createWorld;
			
		}
	};
	@Override
	public void drawScreen() {
		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);   
		worldName.drawAndUpdate();
		worldSeed.drawAndUpdate();
		createButton.drawAndUpdate();
		Cursor.updateAndDrawMouse();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)&& !DisplayVariables.pressedEsc) {
			currentScreen = mainMenu;
			DisplayVariables.pressedEsc = true;
        }
		
	}
	
	
};
public static Screen loadingWorld = new Screen() {
	
	@Override
	public void drawScreen() {
		
		glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		glLoadIdentity();
		//DisplayUtills.shader.bind();
		GL11.glClearColor(131f/255f,101f/255f,57f/255f,1);
		glColor3f(128f/255f,128f/255f,128f/255f);
		
		DisplayUtills.drawSqaure(3, 0.05f, 0, 0, -0.02f);
		glColor3f(0,1,0);
		DisplayUtills.drawSqaureFromLeft(2.5f*(float)World.worldLoadProgress, 0.05f, -2f * 0.005f, 0, -0.02f);
		// TODO Auto-generated method stub
		glColor3f(1,1,1);
((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText("Loading World", -0.0035f, 0.002f, 0.00002f);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		if(World.doneLoading) {
			GL11.glClearColor(135f/255f, 206f/255f, 235f/255f,1);
			GL11.glEnable(GL11.GL_FOG);
		      {
		    	  GL11.glFogi(GL11.GL_FOG_MODE, GL11.GL_EXP);
			    	FloatBuffer color = BufferUtils.createFloatBuffer(4);
			    	color.put(135f/255f);
			    	color.put(206f/255f);
			    	color.put(235f/255f);
			    	color.put(1);	
			    	color.flip();
			    	//color.put()
		        GL11.glFog(GL11.GL_FOG_COLOR, color);
		        GL11. glFogf(GL11.GL_FOG_DENSITY, 0.005f);
		      }
			try {
			
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_LIGHT0);

			    // Create light components
			    float ambientLight[] = { 0.3f, 0.3f,0.3f, 1.0f };
			    float diffuseLight[] = { 1f, 1f, 1f, 1.0f };
			    float specularLight[] = { 0.5f, 0.5f, 0.5f, 1.0f };
			    float position[] = { 0, 256.0f, 0, 0.0f };

			    // Assign created components to GL_LIGHT0
			    FloatBuffer ambientLightBuffer = BufferUtils.createFloatBuffer(4);
			    for(float f : ambientLight) {
			    	ambientLightBuffer.put(f);
			    }
			    ambientLightBuffer.flip();
			    
			    FloatBuffer diffuseLightBuffer = BufferUtils.createFloatBuffer(4);
			    for(float f : diffuseLight) {
			    	diffuseLightBuffer.put(f);
			    }
			    diffuseLightBuffer.flip();
			    FloatBuffer specularLightBuffer = BufferUtils.createFloatBuffer(4);
			    for(float f : specularLight) {
			    	specularLightBuffer.put(f);
			    }
			    specularLightBuffer.flip();
			    FloatBuffer positionBuffer = BufferUtils.createFloatBuffer(4);
			    for(float f : position) {
			    	positionBuffer.put(f);
			    }
			    positionBuffer.flip();
			  GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, ambientLightBuffer);
			    GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, diffuseLightBuffer);
			  GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, specularLightBuffer);
			   GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, positionBuffer);
			  GL11.glShadeModel(GL11.GL_SMOOTH);
			  GL11.glEnable(GL11.GL_COLOR_MATERIAL);
			currentScreen = inGame;
			//DisplayUtills.shader.bind();
			/*
			new Thread() {
				public void run() {
					float x = DisplayVariables.camX;
					float z = DisplayVariables.camZ;
					
					while(!DisplayVariables.close) {
					
						if(Math.sqrt(Math.pow(DisplayVariables.camX-x, 2)+Math.pow(DisplayVariables.camZ-z, 2))>(16*8)/2) {
							x = DisplayVariables.camX;
							System.out.println("works23");
							z = DisplayVariables.camZ;
							ArrayList<Vector2i> chunks = new ArrayList<>();
							for(int i = 0; i <World.realRegionListLength; i++) {
								for(int cx = 0; cx<16; cx++) {
									for(int cz = 0; cz<16; cz++) {
										if(World.regions[i].chunks[cx][cz] != null) {
											if(Math.abs(World.regions[i].chunks[cx][cz].getGlobalX() -x)> (16*8)/2 || Math.abs(World.regions[i].chunks[cx][cz].getGlobalZ() -z)> (16*8)/2) {
												World.regions[i].chunks[cx][cz] = null;
											}
										}
										
									}
								}
								}
							System.out.println("works23");
							
							for(int cx = (int)x -8; cx <(int)x+8;cx++) {
								
								for(int cz = (int)z -8; cz <(int)z+8;cz++) {
								if(World.getChunk(cx, cz) == null) {
									try {
										
										World.loadChunk(cx,cz);
										}catch (Exception e) {
											e.printStackTrace();
											System.out.println("a1");
											System.exit(0);
										}

									chunks.add(new Vector2i(cx, cz));
									
								}
								
								System.out.println(cz);
								}
							}
							World.chunksToSetup = chunks;
						}
					}
					
				}
			};
			*/
			DisplayUtills.shader.unbind();
			
				
		}
	}
	
};
public static Screen inGame = new Screen() {
	boolean RbuttonDownLast = false;
	boolean LbuttonDownLast = false;
	@Override
	public void drawScreen() {
	
		glDepthMask(true);
		//DisplayUtills.worldShader.bind();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_FOG);
glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);   
GL11.glEnable(GL11.GL_TEXTURE_2D);
glLoadIdentity();

GL11.glRotatef(DisplayVariables.camYaw,0.0f,1.0f,0.0f);
GL11.glRotatef(-DisplayVariables.CamPitch,(float)(Math.cos(Math.toRadians(DisplayVariables.camYaw))),0.0f,(float)(Math.sin(Math.toRadians(DisplayVariables.camYaw))));
GL11.glTranslatef (-DisplayVariables.camX, -DisplayVariables.camY, -DisplayVariables.camZ);  

//DisplayUtills.shader.bind();
GL30.glUniform1ui(DisplayUtills.worldShader.uniforms.get("tex"),((Texture) ResourceManager.getObjectForResource("Opencraft:BlockTextures")).getTextureID());
GL20.glUniform3f(DisplayUtills.worldShader.uniforms.get("viewPos"),DisplayVariables.camX,-	DisplayVariables.camY,-DisplayVariables.camZ);
//GL13.glActiveTexture(GL13.GL_TEXTURE2);
//System.out.println("TID: "+World.blockTextures.getTextureID());
GL11.glBindTexture(GL11.GL_TEXTURE_2D, ((Texture) ResourceManager.getObjectForResource("Opencraft:BlockTextures")).getTextureID());

float position[] = { -1, 100.0f, -1, 0.0f };
FloatBuffer positionBuffer = BufferUtils.createFloatBuffer(4);
for(float f : position) {
	positionBuffer.put(f);
}
positionBuffer.flip();
GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, positionBuffer);


try {
	World.drawWorld();
} catch (LWJGLException e2) {
	// TODO Auto-generated catch block
	e2.printStackTrace();
}

FloatBuffer model = BufferUtils.createFloatBuffer(16);
FloatBuffer projection = BufferUtils.createFloatBuffer(16);
IntBuffer viewport = BufferUtils.createIntBuffer(16);
        
GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, model);
GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, projection);
GL11.glGetInteger(GL11.GL_VIEWPORT, viewport);
        


            
FloatBuffer pos = BufferUtils.createFloatBuffer(16);

FloatBuffer z1 = BufferUtils.createFloatBuffer(1);

GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);


Vector3f b = physicsUtils.getBlockPlacePos();
GL11.glDisable(GL11.GL_LIGHTING);
GL11.glEnable(GL11.GL_FOG);
//DisplayUtills.worldShader.unbind();
DisplayUtills.shader.bind();
World.drawAndUpdateItems();
DisplayUtills.shader.unbind();
GL11.glDisable(GL11.GL_TEXTURE_2D);
GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
GL11.glColor4f(1,1,1,1);
//GL11.glDisable(GL11.GL_BLEND);
if(Player.view != 0) {
ModelPlayer.drawModel(Player.x, Player.y-1, Player.z,-Player.yaw,0,0,Player.pitch,Player.handXrotation,25,0,25,0,25,0,-25,true);
}
GL11.glBegin(GL11.GL_QUADS);

if(b != null) {
	
ModelCube.drawModel(b.getX(), b.getY(), b.getZ(), new float[]{0,0,0.2f,0,0.1f,0,0.1f,0,0.1f,0,0.1f,0}, 1,1,1,1,1,1,0.25f);
}
if(Mouse.isButtonDown(1)) {
	if(!RbuttonDownLast) {
	if(Player.hotbar[Player.hotBarIndex] != null) {
	Player.hotbar[Player.hotBarIndex].rightClickAction();
	}else {
		Player.rightHandAction();
		}
	}
	RbuttonDownLast = true;
}else {
	RbuttonDownLast = false;
}
if(b != null) {
ModelCube.drawModel(b.getX(), b.getY(), b.getZ(), new float[]{0,0,0.2f,0,0.1f,0,0.1f,0,0.1f,0,0.1f,0}, 1,1,1,1,1,1,0.25f);
}
if(Mouse.isButtonDown(0)) {
	if(!LbuttonDownLast) {
	if(Player.hotbar[Player.hotBarIndex] != null) {
	Player.hotbar[Player.hotBarIndex].leftClickAction();
	}else {
		Player.leftHandAction();
		}
	}
	LbuttonDownLast = true;
}else {
	LbuttonDownLast = false;
}
GL11.glEnd();
//GL11.glEnable(GL11.GL_BLEND);
//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
GL11.glReadPixels(1920/2, 1080/2, 1, 1, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, z1);    
GLU.gluUnProject(0, 0, z1.get(0), model, projection, viewport, pos);
glLoadIdentity();

//DisplayUtills.drawSqaure(2, 2, 0,0, -0.02f);

GL11.glColor3f(1, 1, 1);


//glDepthMask(false);
((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText("FPS: "+Math.round(DisplayVariables.fps), -0.0165f, 0.0080f, 0.00002f);
int x = physicsUtils.convertFloatCoordToBlockCoord(DisplayVariables.camX);
int y =  physicsUtils.convertFloatCoordToBlockCoord(DisplayVariables.camY-2);
int z = physicsUtils.convertFloatCoordToBlockCoord(DisplayVariables.camZ);
//((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText("Standing on Block: "+ x+" "+y+" "+z, -0.0165f, 0.0070f, 0.00002f);




float nz = (float) (Math.cos(Math.toRadians(DisplayVariables.camYaw))*Math.cos(Math.toRadians(DisplayVariables.CamPitch)));
float nx = (float) (Math.sin(Math.toRadians(DisplayVariables.camYaw))*Math.cos(Math.toRadians(DisplayVariables.CamPitch)));
float ny = (float) Math.sin(Math.toRadians(DisplayVariables.CamPitch));
//((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText("looking at Block: "+b.getX()+" "+" "+b.getY()+" "+bz, -0.0165f, 0.0060f, 0.00002f);
//((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText("looking at: "+(int)pos.get(0)+" "+" "+(int)pos.get(1)+" "+(int)pos.get(2), -0.0165f, 0.0050f, 0.00002f);

//((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText("Position: "+Player.x+" "+Player.y+" "+Player.z, -0.0165f, 0.0040f, 0.00002f);
//DisplayUtills.shader.bind();
GL11.glEnable(GL11.GL_TEXTURE_2D);
try {
	Player.checkForActions();
} catch (InstantiationException | IllegalAccessException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}

Player.drawPlayerHUD();
GL11.glDisable(GL11.GL_TEXTURE_2D);
//DisplayUtills.shader.unbind();
GL11.glDisable(GL11.GL_TEXTURE_2D);


Player.updatePostitionAndRotation();

World.checkFluids();
Player.setCamToPlayer();
if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)&& !DisplayVariables.pressedEsc) {
	try {
		World.saveAndUnloadAllLoadedChunks();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	currentScreen = mainMenu;
	DisplayVariables.pressedEsc = true;
}
if(DisplayVariables.fps > DisplayVariables.fpsRecord) {
	DisplayVariables.fpsRecord = (float) DisplayVariables.fps;
}
	}
	public void controls() {
		DisplayVariables.camYaw += Mouse.getDX()/4f;
		DisplayVariables.CamPitch += Mouse.getDY()/4f;
		float nz = (float) (Math.cos(Math.toRadians(DisplayVariables.camYaw))*Math.cos(Math.toRadians(DisplayVariables.CamPitch)));
		float nx = (float) (Math.sin(Math.toRadians(DisplayVariables.camYaw))*Math.cos(Math.toRadians(DisplayVariables.CamPitch)));
				float ny = (float) Math.sin(Math.toRadians(DisplayVariables.CamPitch));
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			DisplayVariables.camZ += 0.01f*nz*DisplayVariables.deltaTime;
			DisplayVariables.camX += 0.01f*nx*DisplayVariables.deltaTime;
			DisplayVariables.camY +=0.01f*ny*DisplayVariables.deltaTime;
		}
		if(DisplayVariables.CamPitch > 90) {
			DisplayVariables.CamPitch = 90;
		}
		if(DisplayVariables.CamPitch < -90) {
			DisplayVariables.CamPitch = -90;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			DisplayVariables.camY +=0.02f*DisplayVariables.deltaTime;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			DisplayVariables.camY -=0.02f*DisplayVariables.deltaTime;
		}
	}
	
};

public static Screen currentScreen = mainMenu;
}
