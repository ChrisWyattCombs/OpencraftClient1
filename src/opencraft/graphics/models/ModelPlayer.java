package opencraft.graphics.models;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import opencraft.Item;
import opencraft.graphics.ResourceManager;
public class ModelPlayer {
public static void drawModel(float x, float y, float z,float fullBodyYaw,float fullBodyPitch, float headYaw, float headPitch,float rightArmYaw, float rightArmPitch,float leftArmYaw, float leftArmPitch,float rightLegYaw, float rightLegPitch,float leftLegYaw, float leftLegPitch,boolean drawHead,Item item, boolean beingHit) {
glPushMatrix();
glTranslatef(x,y,z);
glRotatef(fullBodyYaw, 0, 1, 0);
glRotatef(fullBodyPitch, 1, 0, 0);


if(drawHead) {
glPushMatrix();
glTranslatef(0, 1f, 0);
glRotatef(headYaw, 0, 1, 0);
glRotatef(headPitch, 1, 0, 0);
glBegin(GL_QUADS);
if(beingHit) {
	glColor3f(1f, 0, 0);
}
drawHead(0,0,0);
glColor3f(1f, 1f, 1f);
glEnd();
glPopMatrix();
}

glPushMatrix();
glTranslatef(0, 0.4f, 0);
glBegin(GL_QUADS);
if(beingHit) {
	glColor3f(1f, 0, 0);
}
drawbody(0,0,0);
glColor3f(1f, 1f, 1f);
glEnd();
glPopMatrix();

glPushMatrix();
glTranslatef(0.35f, 0.75f, 0);
glRotatef(rightArmYaw, 0, 1, 0);
glRotatef(rightArmPitch, 1, 0, 0);
if(beingHit) {
	glColor3f(1f, 0, 0);
}
drawArm(0,0,0,item);
glColor3f(1f, 1f, 1f);
glPopMatrix();

glPushMatrix();
glTranslatef(-0.35f, 0.75f, 0);
glRotatef(leftArmYaw, 0, 1, 0);
glRotatef(leftArmPitch, 1, 0, 0);
if(beingHit) {
	glColor3f(1f, 0, 0);
}
drawArm(0,0,0,null);	
glColor3f(1f, 1f, 1f);
glPopMatrix();

glPushMatrix();
glTranslatef(0.25f/2, 0f, 0);
glRotatef(rightLegYaw, 0, 1, 0);
glRotatef(rightLegPitch, 1, 0, 0);
glBegin(GL_QUADS);
if(beingHit) {
	glColor3f(1f, 0, 0);
}
drawLeg(0,0,0);
glColor3f(1f, 1f, 1f);
glEnd();
glPopMatrix();

glPushMatrix();
glTranslatef(-0.25f/2, 0f, 0);
glRotatef(leftLegYaw, 0, 1, 0);
glRotatef(leftLegPitch, 1, 0, 0);
glBegin(GL_QUADS);
if(beingHit) {
	glColor3f(1f, 0, 0);
}
drawLeg(0,0,0);
glColor3f(1f, 1f, 1f);
glEnd();

glPopMatrix();


glPopMatrix();

	
}
public static void drawHead(float x, float y, float z) {

	//font of head
	
	glTexCoord2f(16f/64f,(16f)/64f); glVertex3f(x-0.2f,y-0.2f,z+-0.2f);
	glTexCoord2f(8f/64f,(16f)/64f);glVertex3f(x+0.2f,y-0.2f,z+-0.2f);
	glTexCoord2f(8f/64f,(8f)/64f);glVertex3f(x+0.2f,y+0.2f,z+-0.2f);
	glTexCoord2f(16f/64f,(8f)/64f);glVertex3f(x+-0.2f,y+0.2f,z+-0.2f);
	
	//back of head
	glTexCoord2f(24f/64f,(16f)/64f);glVertex3f(x+-0.2f,y-0.2f,z+0.2f);
	glTexCoord2f(32f/64f,(16f)/64f);glVertex3f(x+0.2f,y-0.2f,z+0.2f);
	glTexCoord2f(32f/64f,(8f)/64f);glVertex3f(x+0.2f,y+0.2f,z+0.2f);
	glTexCoord2f(24f/64f,(8f)/64f);glVertex3f(x+-0.2f,y+0.2f,z+0.2f);
	
	//top of head
	glTexCoord2f(8f/64f,(8f)/64f);glVertex3f(x+-0.2f,y-0.2f,z+-0.2f);
	glTexCoord2f(16f/64f,(8f)/64f);glVertex3f(x+0.2f,y-0.2f,z+-0.2f);
	glTexCoord2f(16f/64f,(0f)/64f);glVertex3f(x+0.2f,y-0.2f,z+0.2f);
	glTexCoord2f(8f/64f,(0f)/64f);glVertex3f(x+-0.2f,y-0.2f,z+0.2f);
	
	//bottom of head
	glTexCoord2f(16f/64f,(8f)/64f);glVertex3f(x+-0.2f,y+0.2f,z+-0.2f);
	glTexCoord2f(23f/64f,(8f)/64f);glVertex3f(x+0.2f,y+0.2f,z+-0.2f);
	glTexCoord2f(23f/64f,(0f)/64f);glVertex3f(x+0.2f,y+0.2f,z+0.2f);
	glTexCoord2f(16f/64f,(0f)/64f);glVertex3f(x+-0.2f,y+0.2f,z+0.2f);
	
	//right of head
	glTexCoord2f(0f/64f,(15f+1f)/64f);glVertex3f(x+0.2f,y-0.2f,z+-0.2f);
	glTexCoord2f(0f/64f,(15f+1f)/64f);glVertex3f(x+0.2f,y-0.2f,z+0.2f);
	glTexCoord2f(7f/64f,(8f)/64f);glVertex3f(x+0.2f,y+0.2f,z+0.2f);
	glTexCoord2f(7f/64f,(8f)/64f);glVertex3f(x+0.2f,y+0.2f,z-0.2f);
	
	//left of head
	glTexCoord2f(16f/64f,(15f+1f)/64f);glVertex3f(x+-0.2f,y-0.2f,z+-0.2f);
	glTexCoord2f(16f/64f,(15f+1f)/64f);glVertex3f(x+-0.2f,y-0.2f,z+0.2f);
	glTexCoord2f((21f+1f)/64f,(8f)/64f);glVertex3f(x+-0.2f,y+0.2f,z+0.2f);
	glTexCoord2f(16f/64f,(8f)/64f);glVertex3f(x+-0.2f,y+0.2f,z-0.2f);
}
public static void drawbody(float x, float y, float z) {

	float bodyWidth = 0.509f;
	float bodyHeight = 0.8f;
	
	//front of body
	glTexCoord2f(20f/64f,(20f)/64f);glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z+-0.1f); 
	glTexCoord2f(28f/64f,(20f)/64f);glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z+-0.1f);
	glTexCoord2f(28f/64f,(32f)/64f);glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z+-0.1f);
	glTexCoord2f(20f/64f,(32f)/64f);glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z+-0.1f);
		
	//back of body
	glTexCoord2f(39f/64f,(20f)/64f);glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z+0.1f); 
	glTexCoord2f(32f/64f,(20f)/64f);glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z+0.1f);
	glTexCoord2f(32f/64f,(32f)/64f);glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z+0.1f);
	glTexCoord2f(39f/64f,(32f)/64f);glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z+0.1f);
		
	//top of body
	glTexCoord2f(28f/64f,(16f)/64f);glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z+0.1f); 
	glTexCoord2f(20f/64f,(16f)/64f);glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z+0.1f);
	glTexCoord2f(20f/64f,(20f)/64f);glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z-0.1f);
	glTexCoord2f(28f/64f,(20f)/64f);glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z-0.1f);
		
	//bottom of body
	glTexCoord2f(36f/64f,(16f)/64f);glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z+0.1f); 
	glTexCoord2f(28f/64f,(16f)/64f);glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z+0.1f);
	glTexCoord2f(28f/64f,(20f)/64f);glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z-0.1f);
	glTexCoord2f(36f/64f,(20f)/64f);glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z-0.1f);
		
	//right of body 
	glTexCoord2f(20f/64f,(20f)/64f);glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z+0.1f); 
	glTexCoord2f(20f/64f,(32f)/64f);glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z+0.1f); 
	glTexCoord2f(16f/64f,(32f)/64f);glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z-0.1f); 
	glTexCoord2f(16f/64f,(20f)/64f);glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z-0.1f); 
		
	//left of body 
	glTexCoord2f(32f/64f,(20f)/64f);glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z+0.1f); 
	glTexCoord2f(32f/64f,(32f)/64f);glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z+0.1f); 
	glTexCoord2f(28f/64f,(32f)/64f);glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z-0.1f); 
	glTexCoord2f(28f/64f,(20f)/64f);glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z-0.1f);
}
public static void drawArm(float x,float y,float z,Item item) {
	float armLength = 0.8f;
	//front of arm
	glBegin(GL_QUADS);
	glTexCoord2f(44f/64f,(32f)/64f);glVertex3f(x+0.1f,y,z+0.1f);
	glTexCoord2f(44f/64f,(20f)/64f);glVertex3f(x-0.1f,y,z+0.1f);
	glTexCoord2f(48f/64f,(20f)/64f);glVertex3f(x-0.1f,y-armLength,z+0.1f);
	glTexCoord2f(48f/64f,(32f)/64f);glVertex3f(x+0.1f,y-armLength,z+0.1f);
	//back of arm
	glTexCoord2f(52f/64f,(32f)/64f);glVertex3f(x+0.1f,y,z-0.1f);
	glTexCoord2f(52f/64f,(20f)/64f);glVertex3f(x-0.1f,y,z-0.1f);
	glTexCoord2f(55f/64f,(20f)/64f);glVertex3f(x-0.1f,y-armLength,z-0.1f);
	glTexCoord2f(55f/64f,(32f)/64f);glVertex3f(x+0.1f,y-armLength,z-0.1f);
	
	//top of arm
	glVertex3f(x+0.1f,y,z+0.1f);
	glVertex3f(x-0.1f,y,z+0.1f);
	glVertex3f(x-0.1f,y,z-0.1f);
	glVertex3f(x+0.1f,y,z-0.1f);
	
	//bottom of arm
	glVertex3f(x+0.1f,y-armLength,z+0.1f);
	glVertex3f(x-0.1f,y-armLength,z+0.1f);
	glVertex3f(x-0.1f,y-armLength,z-0.1f);
	glVertex3f(x+0.1f,y-armLength,z-0.1f);
	
	//right of arm
	glTexCoord2f(40f/64f,(32f)/64f);glVertex3f(x+0.1f,y,z+0.1f);
	glTexCoord2f(40f/64f,(32f)/64f);glVertex3f(x+0.1f,y-armLength,z+0.1f);
	glTexCoord2f(44f/64f,(20f)/64f);glVertex3f(x+0.1f,y-armLength,z-0.1f);
	glTexCoord2f(44f/64f,(20f)/64f);glVertex3f(x+0.1f,y,z-0.1f);
	
	//left of arm
	glTexCoord2f(48f/64f,(32f)/64f);glVertex3f(x-0.1f,y,z+0.1f);
	glTexCoord2f(52f/64f,(32f)/64f);glVertex3f(x-0.1f,y-armLength,z+0.1f);
	glTexCoord2f(52f/64f,(20f)/64f);glVertex3f(x-0.1f,y-armLength,z-0.1f);
	glTexCoord2f(48f/64f,(20f)/64f);glVertex3f(x-0.1f,y,z-0.1f);
	glEnd();
	GL11.glBindTexture(GL11.GL_TEXTURE_2D, ((Texture) ResourceManager.getObjectForResource("Opencraft:BlockTextures")).getTextureID());
	if(item != null) {
	glPushMatrix();
	glTranslatef(0, -0.7f,-0.13f);
	glRotatef(-90, 1, 0, 0);
	item.drawIcon(0,0,0,0.1f);
	
	glPopMatrix();
	}
	GL11.glBindTexture(GL11.GL_TEXTURE_2D, ((Texture) ResourceManager.getObjectForResource("Opencraft:SkinTexture")).getTextureID());

}
public static void drawLeg(float x,float y,float z) {
	float legLength = 1f;
	float legWidth = 0.35f;
	//front of leg
	glVertex3f(x+(legWidth/2),y,z+0.1f);
	glVertex3f(x-(legWidth/2),y,z+0.1f);
	glVertex3f(x-(legWidth/2),y-legLength,z+0.1f);
	glVertex3f(x+(legWidth/2),y-legLength,z+0.1f);
	//back of leg
	glVertex3f(x+(legWidth/2),y,z+0.1f);
	glVertex3f(x-(legWidth/2),y,z+0.1f);
	glVertex3f(x-(legWidth/2),y-legLength,z+0.1f);
	glVertex3f(x+(legWidth/2),y-legLength,z+0.1f);
	
	//top of leg
	glVertex3f(x+(legWidth/2),y,z+0.1f);
	glVertex3f(x-(legWidth/2),y,z+0.1f);
	glVertex3f(x-(legWidth/2),y,z-0.1f);
	glVertex3f(x+(legWidth/2),y,z-0.1f);
	
	//bottom of leg
	glVertex3f(x+(legWidth/2),y-legLength,z+0.1f);
	glVertex3f(x-(legWidth/2),y-legLength,z+0.1f);
	glVertex3f(x-(legWidth/2),y-legLength,z-0.1f);
	glVertex3f(x+(legWidth/2),y-legLength,z-0.1f);
	
	//right of leg
	glVertex3f(x+(legWidth/2),y,z+0.1f);
	glVertex3f(x+(legWidth/2),y-legLength,z+0.1f);
	glVertex3f(x+(legWidth/2),y-legLength,z-0.1f);
	glVertex3f(x+(legWidth/2),y,z-0.1f);
	
	//left of leg
	glVertex3f(x-(legWidth/2),y,z+0.1f);
	glVertex3f(x-(legWidth/2),y-legLength,z+0.1f);
	glVertex3f(x-(legWidth/2),y-legLength,z-0.1f);
	glVertex3f(x-(legWidth/2),y,z-0.1f);
	
}
}
