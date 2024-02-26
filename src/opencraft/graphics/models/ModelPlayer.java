package opencraft.graphics.models;
import static org.lwjgl.opengl.GL11.*;
public class ModelPlayer {
public static void drawModel(float x, float y, float z,float fullBodyYaw,float fullBodyPitch, float headYaw, float headPitch,float rightArmYaw, float rightArmPitch,float leftArmYaw, float leftArmPitch,float rightLegYaw, float rightLegPitch,float leftLegYaw, float leftLegPitch,boolean drawHead) {
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
drawHead(0,0,0);
glEnd();
glPopMatrix();
}
glPushMatrix();
glTranslatef(0, 0.4f, 0);
glBegin(GL_QUADS);
drawbody(0,0,0);
glEnd();
glPopMatrix();

glPushMatrix();
glTranslatef(0.45f, 0.75f, 0);
glRotatef(rightArmYaw, 0, 1, 0);
glRotatef(rightArmPitch, 1, 0, 0);
glBegin(GL_QUADS);
drawArm(0,0,0);
glEnd();
glPopMatrix();

glPushMatrix();
glTranslatef(-0.45f, 0.75f, 0);
glRotatef(leftArmYaw, 0, 1, 0);
glRotatef(leftArmPitch, 1, 0, 0);
glBegin(GL_QUADS);
drawArm(0,0,0);	
glEnd();
glPopMatrix();

glPushMatrix();
glTranslatef(0.35f/2, 0f, 0);
glRotatef(rightLegYaw, 0, 1, 0);
glRotatef(rightLegPitch, 1, 0, 0);
glBegin(GL_QUADS);
drawLeg(0,0,0);
glEnd();
glPopMatrix();

glPushMatrix();
glTranslatef(-0.35f/2, 0f, 0);
glRotatef(leftLegYaw, 0, 1, 0);
glRotatef(leftLegPitch, 1, 0, 0);
glBegin(GL_QUADS);
drawLeg(0,0,0);
glEnd();

glPopMatrix();


glPopMatrix();

	
}
public static void drawHead(float x, float y, float z) {

	//font of head
	glVertex3f(x-0.2f,y-0.2f,z+-0.2f);
	glVertex3f(x+0.2f,y-0.2f,z+-0.2f);
	glVertex3f(x+0.2f,y+0.2f,z+-0.2f);
	glVertex3f(x+-0.2f,y+0.2f,z+-0.2f);
	
	//back of head
	glVertex3f(x+-0.2f,y-0.2f,z+0.2f);
	glVertex3f(x+0.2f,y-0.2f,z+0.2f);
	glVertex3f(x+0.2f,y+0.2f,z+0.2f);
	glVertex3f(x+-0.2f,y+0.2f,z+0.2f);
	
	//top of head
	glVertex3f(x+-0.2f,y-0.2f,z+-0.2f);
	glVertex3f(x+0.2f,y-0.2f,z+-0.2f);
	glVertex3f(x+0.2f,y-0.2f,z+0.2f);
	glVertex3f(x+-0.2f,y-0.2f,z+0.2f);
	
	//bottom of head
	glVertex3f(x+-0.2f,y+0.2f,z+-0.2f);
	glVertex3f(x+0.2f,y+0.2f,z+-0.2f);
	glVertex3f(x+0.2f,y+0.2f,z+0.2f);
	glVertex3f(x+-0.2f,y+0.2f,z+0.2f);
	
	//right of head
	glVertex3f(x+0.2f,y-0.2f,z+-0.2f);
	glVertex3f(x+0.2f,y+0.2f,z+-0.2f);
	glVertex3f(x+0.2f,y+0.2f,z+0.2f);
	glVertex3f(x+0.2f,y-0.2f,z+0.2f);
	
	//left of head
	glVertex3f(x+-0.2f,y-0.2f,z+-0.2f);
	glVertex3f(x+-0.2f,y+0.2f,z+-0.2f);
	glVertex3f(x+-0.2f,y+0.2f,z+0.2f);
	glVertex3f(x+-0.2f,y-0.2f,z+0.2f);
}
public static void drawbody(float x, float y, float z) {

	float bodyWidth = 0.7f;
	float bodyHeight = 0.8f;
	
	//front of body
	glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z+-0.1f); 
	glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z+-0.1f);
	glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z+-0.1f);
	glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z+-0.1f);
		
	//back of body
	glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z+0.1f); 
	glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z+0.1f);
	glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z+0.1f);
	glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z+0.1f);
		
	//top of body
	glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z+0.1f); 
	glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z+0.1f);
	glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z-0.1f);
	glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z-0.1f);
		
	//bottom of body
	glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z+0.1f); 
	glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z+0.1f);
	glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z-0.1f);
	glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z-0.1f);
		
	//right of body 
	glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z+0.1f); 
	glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z+0.1f); 
	glVertex3f(x+(bodyWidth/2),y-(bodyHeight/2),z-0.1f); 
	glVertex3f(x+(bodyWidth/2),y+(bodyHeight/2),z-0.1f); 
		
	//left of body 
	glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z+0.1f); 
	glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z+0.1f); 
	glVertex3f(x-(bodyWidth/2),y-(bodyHeight/2),z-0.1f); 
	glVertex3f(x-(bodyWidth/2),y+(bodyHeight/2),z-0.1f);
}
public static void drawArm(float x,float y,float z) {
	float armLength = 0.8f;
	//front of arm
	glVertex3f(x+0.1f,y,z+0.1f);
	glVertex3f(x-0.1f,y,z+0.1f);
	glVertex3f(x-0.1f,y-armLength,z+0.1f);
	glVertex3f(x+0.1f,y-armLength,z+0.1f);
	//back of arm
	glVertex3f(x+0.1f,y,z+0.1f);
	glVertex3f(x-0.1f,y,z+0.1f);
	glVertex3f(x-0.1f,y-armLength,z+0.1f);
	glVertex3f(x+0.1f,y-armLength,z+0.1f);
	
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
	glVertex3f(x+0.1f,y,z+0.1f);
	glVertex3f(x+0.1f,y-armLength,z+0.1f);
	glVertex3f(x+0.1f,y-armLength,z-0.1f);
	glVertex3f(x+0.1f,y,z-0.1f);
	
	//left of arm
	glVertex3f(x-0.1f,y,z+0.1f);
	glVertex3f(x-0.1f,y-armLength,z+0.1f);
	glVertex3f(x-0.1f,y-armLength,z-0.1f);
	glVertex3f(x-0.1f,y,z-0.1f);
	
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
