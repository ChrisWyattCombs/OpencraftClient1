package opencraft.graphics.models;

import static org.lwjgl.opengl.GL11.*;

import opencraft.physics.physicsUtils;



public class ModelCube {

	
	public static void drawModel(float x, float y, float z, float[] textureCoords,float topLight,float bottomLight,float frontLight,float backLight,float rightLight,float leftLight,float alpha) {
		// {
		//glDisable(GL_LIGHT0);
	
		
		//}
		//top
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0+textureCoords[0],1-(0.0f + textureCoords[1]));glVertex3f(x+1, y+1, z+1);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0.1f+textureCoords[0],1- +textureCoords[1]);glVertex3f(x+0, y+1, z+1);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0.1f +0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x+0, y+1, z+0);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x+1, y+1, z+0);
		;
		//glNormal3f(0, 1,0);
		//glColor4f(1f, 1f, 1f);
		//bottom
	
			//glColor4f(0.1f, 0.1f, 0.1f);
			
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(textureCoords[2],1- +textureCoords[3]);glVertex3f(x+1, y+0, z+1);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(0.1f +textureCoords[2],1-textureCoords[3]);glVertex3f(x+0, y+0, z+1);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(0.1f +textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x+0, y+0, z+0);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x+1, y+0, z+0);
		;
		//glNormal3f(0, -1,0);
		//glColor4f(1f, 1f, 1f);
		//front
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(textureCoords[4],0.0f + 1-textureCoords[5]);glVertex3f(x+0, y+0, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(0.1f +textureCoords[4],1-textureCoords[5]);glVertex3f(x+1, y+0, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(0.1f +textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x+1, y+1, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x+0, y+1, z+0);
		;
		//glNormal3f(0, 0,-1);
		
		//back
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(textureCoords[6],1- textureCoords[7]);glVertex3f(x+0, y+0, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(0.1f +textureCoords[6],1-textureCoords[7]);glVertex3f(x+1, y+0, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(0.1f +textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x+1, y+1, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight,backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x+0, y+1, z+1);
		;
		//glNormal3f(0, 0,1);
		
		//right
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(textureCoords[8],0.0f +1- textureCoords[9]);glVertex3f(x+1,y+0, z+0);
		
		//glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(0.1f +textureCoords[8],1-textureCoords[9]);glVertex3f(x+1,y+0, z+1);
		
		//glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(0.1f +textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+1,y+1, z+1);
		
		////glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+1,y+1, z+0);
		;
		////glNormal3f(1, 0,0);
		
		//left
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(textureCoords[10],0.0f +1-textureCoords[11]);glVertex3f(x+0,y+0, z+0);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(0.1f +textureCoords[10],1-textureCoords[11]);glVertex3f(x+0,y+0, z+1);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(0.1f +textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x+0,y+1, z+1);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x+0,y+1, z+0);
		
		;
		//glEnable(GL_LIGHT0);
		////glNormal3f(-1, 0,0);
	}
	public static void drawModel(float x, float y, float z, float[] textureCoords,float topLight,float bottomLight,float frontLight,float backLight,float rightLight,float leftLight,float alpha, boolean top,boolean bottom,boolean front,boolean back,boolean right, boolean left) {
		// {
		//glDisable(GL_LIGHT0);
	
		
		//}
		if(top) {
		//top
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0+textureCoords[0],1-(0.0f + textureCoords[1]));glVertex3f(x+1, y+1, z+1);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0.1f+textureCoords[0],1- +textureCoords[1]);glVertex3f(x+0, y+1, z+1);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0.1f +0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x+0, y+1, z+0);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x+1, y+1, z+0);
		;
		//glNormal3f(0, 1,0);
		//glColor4f(1f, 1f, 1f);
		}
		if(bottom) {
		//bottom
	
			//glColor4f(0.1f, 0.1f, 0.1f);
			
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(textureCoords[2],1- +textureCoords[3]);glVertex3f(x+1, y+0, z+1);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(0.1f +textureCoords[2],1-textureCoords[3]);glVertex3f(x+0, y+0, z+1);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(0.1f +textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x+0, y+0, z+0);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x+1, y+0, z+0);
		;
		}
		if(front) {
		//glNormal3f(0, -1,0);
		//glColor4f(1f, 1f, 1f);
		//front
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(textureCoords[4],0.0f + 1-textureCoords[5]);glVertex3f(x+0, y+0, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(0.1f +textureCoords[4],1-textureCoords[5]);glVertex3f(x+1, y+0, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(0.1f +textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x+1, y+1, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x+0, y+1, z+0);
		;
		}
		//glNormal3f(0, 0,-1);
		if(back) {
		//back
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(textureCoords[6],1- textureCoords[7]);glVertex3f(x+0, y+0, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(0.1f +textureCoords[6],1-textureCoords[7]);glVertex3f(x+1, y+0, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(0.1f +textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x+1, y+1, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight,backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x+0, y+1, z+1);
		;
		}
		//glNormal3f(0, 0,1);
		
		if(right) {
		//right
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(textureCoords[8],0.0f +1- textureCoords[9]);glVertex3f(x+1,y+0, z+0);
		
		//glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(0.1f +textureCoords[8],1-textureCoords[9]);glVertex3f(x+1,y+0, z+1);
		
		//glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(0.1f +textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+1,y+1, z+1);
		
		////glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+1,y+1, z+0);
		;
		}
		////glNormal3f(1, 0,0);
		
		//left
		if(left) {
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(textureCoords[10],0.0f +1-textureCoords[11]);glVertex3f(x+0,y+0, z+0);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(0.1f +textureCoords[10],1-textureCoords[11]);glVertex3f(x+0,y+0, z+1);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(0.1f +textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x+0,y+1, z+1);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x+0,y+1, z+0);
		
		;
		}
		//glEnable(GL_LIGHT0);
		////glNormal3f(-1, 0,0);
	}
	/*
	public static void drawModel(float x, float y, float z, float[] textureCoords,float topLight,float bottomLight,float frontLight,float backLight,float rightLight,float leftLight, float alpha) {
		// {
		//glDisable(GL_LIGHT0);
	
		
		//}
		//top
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0+textureCoords[0],1-(0.0f + textureCoords[1]));glVertex3f(x+1, y+1, z+1);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0.1f+textureCoords[0],1- +textureCoords[1]);glVertex3f(x+0, y+1, z+1);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0.1f +0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x+0, y+1, z+0);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x+1, y+1, z+0);
		;
		//glNormal3f(0, 1,0);
		//glColor4f(1f, 1f, 1f);
		//bottom
	
			//glColor4f(0.1f, 0.1f, 0.1f);
			
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(textureCoords[2],1- +textureCoords[3]);glVertex3f(x+1, y+0, z+1);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(0.1f +textureCoords[2],1-textureCoords[3]);glVertex3f(x+0, y+0, z+1);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(0.1f +textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x+0, y+0, z+0);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x+1, y+0, z+0);
		;
		//glNormal3f(0, -1,0);
		//glColor4f(1f, 1f, 1f);
		//front
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(textureCoords[4],0.0f + 1-textureCoords[5]);glVertex3f(x+0, y+0, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(0.1f +textureCoords[4],1-textureCoords[5]);glVertex3f(x+1, y+0, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(0.1f +textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x+1, y+1, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x+0, y+1, z+0);
		;
		//glNormal3f(0, 0,-1);
		
		//back
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(textureCoords[6],1- textureCoords[7]);glVertex3f(x+0, y+0, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(0.1f +textureCoords[6],1-textureCoords[7]);glVertex3f(x+1, y+0, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(0.1f +textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x+1, y+1, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight,backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x+0, y+1, z+1);
		;
		//glNormal3f(0, 0,1);
		
		//right
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(textureCoords[8],0.0f +1- textureCoords[9]);glVertex3f(x+1,y+0, z+0);
		
		//glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(0.1f +textureCoords[8],1-textureCoords[9]);glVertex3f(x+1,y+0, z+1);
		
		//glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(0.1f +textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+1,y+1, z+1);
		
		////glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+1,y+1, z+0);
		;
		////glNormal3f(1, 0,0);
		
		//left
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(textureCoords[10],0.0f +1-textureCoords[11]);glVertex3f(x+0,y+0, z+0);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(0.1f +textureCoords[10],1-textureCoords[11]);glVertex3f(x+0,y+0, z+1);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(0.1f +textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x+0,y+1, z+1);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x+0,y+1, z+0);
		
		;
		//glEnable(GL_LIGHT0);
		////glNormal3f(-1, 0,0);
	}
	
	*/
	public static void drawModel(float x, float y, float z, float[] textureCoords,float topLight,float bottomLight,float frontLight,float backLight,float rightLight,float leftLight, float alpha,float size) {
		// {
		//glDisable(GL_LIGHT0);
	
		
		//}
		//top
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0+textureCoords[0],1-(0.0f + textureCoords[1]));glVertex3f(x+(0.5f*size), y+(0.5f*size), z+(0.5f*size));
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0.1f+textureCoords[0],1- +textureCoords[1]);glVertex3f(x-(0.5f*size), y+(0.5f*size), z+(0.5f*size));
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0.1f +0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x-(0.5f*size), y+(0.5f*size), z-(0.5f*size));
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x+(0.5f*size), y+(0.5f*size), z-(0.5f*size));
		;
		//glNormal3f(0, 1,0);
		//glColor4f(1f, 1f, 1f);
		//bottom
	
			//glColor4f(0.1f, 0.1f, 0.1f);
			
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(textureCoords[2],1- +textureCoords[3]);glVertex3f(x+(0.5f*size), y-(0.5f*size), z+(0.5f*size));
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(0.1f +textureCoords[2],1-textureCoords[3]);glVertex3f(x-(0.5f*size), y-(0.5f*size), z+(0.5f*size));
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(0.1f +textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x-(0.5f*size), y-(0.5f*size), z-(0.5f*size));
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x+(0.5f*size), y-(0.5f*size), z-(0.5f*size));
		;
		//glNormal3f(0, -1,0);
		//glColor4f(1f, 1f, 1f);
		//front
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(textureCoords[4],0.0f + 1-textureCoords[5]);glVertex3f(x-(0.5f*size), y-(0.5f*size), z-(0.5f*size));
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(0.1f +textureCoords[4],1-textureCoords[5]);glVertex3f(x+(0.5f*size), y-(0.5f*size), z-(0.5f*size));
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(0.1f +textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x+(0.5f*size), y+(0.5f*size), z-(0.5f*size));
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x-(0.5f*size), y+(0.5f*size), z-(0.5f*size));
		;
		//glNormal3f(0, 0,-1);
		
		//back
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(textureCoords[6],1- textureCoords[7]);glVertex3f(x-(0.5f*size), y-(0.5f*size), z+(0.5f*size));
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(0.1f +textureCoords[6],1-textureCoords[7]);glVertex3f(x+(0.5f*size), y-(0.5f*size), z+(0.5f*size));
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(0.1f +textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x+(0.5f*size), y+(0.5f*size), z+(0.5f*size));
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight,backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x-(0.5f*size), y+(0.5f*size), z+(0.5f*size));
		;
		//glNormal3f(0, 0,1);
		
		//right
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(textureCoords[8],0.0f +1- textureCoords[9]);glVertex3f(x+(0.5f*size),y-(0.5f*size), z-(0.5f*size));
		
		//glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(0.1f +textureCoords[8],1-textureCoords[9]);glVertex3f(x+(0.5f*size),y-(0.5f*size), z+(0.5f*size));
		
		//glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(0.1f +textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+(0.5f*size),y+(0.5f*size), z+(0.5f*size));
		
		////glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+(0.5f*size),y+(0.5f*size), z-(0.5f*size));
		;
		////glNormal3f(1, 0,0);
		
		//left
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(textureCoords[10],0.0f +1f-textureCoords[11]);glVertex3f(x-(0.5f*size),y-(0.5f*size), z-(0.5f*size));
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(0.1f +textureCoords[10],1-textureCoords[11]);glVertex3f(x-(0.5f*size),y-(0.5f*size), z+(0.5f*size));
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(0.1f +textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x-(0.5f*size),y+(0.5f*size), z+(0.5f*size));
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x-(0.5f*size),y+(0.5f*size), z-(0.5f*size));
		
		;
		//glEnable(GL_LIGHT0);
		////glNormal3f(-1, 0,0);
	}
	public static void drawModelWithSize(float x, float y, float z, float[] textureCoords,float topLight,float bottomLight,float frontLight,float backLight,float rightLight,float leftLight,float alpha,float height) {
		// {
		//glDisable(GL_LIGHT0);
	
		
		//}
		//top
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0+textureCoords[0],1-(0.0f + textureCoords[1]));glVertex3f(x+1, y+height, z+1);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0.1f+textureCoords[0],1- +textureCoords[1]);glVertex3f(x+0, y+height, z+1);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0.1f +0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x+0, y+height, z+0);
		
		//glNormal3f(0, 1,0);
		glColor4f(topLight, topLight, topLight,alpha);glNormal3f(0, 1,0);glTexCoord2f(0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x+1, y+height, z+0);
		;
		//glNormal3f(0, 1,0);
		//glColor4f(1f, 1f, 1f);
		//bottom
	
			//glColor4f(0.1f, 0.1f, 0.1f);
			
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(textureCoords[2],1- +textureCoords[3]);glVertex3f(x+1, y+0, z+1);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(0.1f +textureCoords[2],1-textureCoords[3]);glVertex3f(x+0, y+0, z+1);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(0.1f +textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x+0, y+0, z+0);
		
		//glNormal3f(0, -1,0);
		glColor4f(bottomLight, bottomLight, bottomLight,alpha);glNormal3f(0, -1,0);glTexCoord2f(textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x+1, y+0, z+0);
		;
		//glNormal3f(0, -1,0);
		//glColor4f(1f, 1f, 1f);
		//front
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(textureCoords[4],0.0f + 1-textureCoords[5]);glVertex3f(x+0, y+0, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(0.1f +textureCoords[4],1-textureCoords[5]);glVertex3f(x+1, y+0, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(0.1f +textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x+1, y+height, z+0);
		
		//glNormal3f(0, 0,-1);
		glColor4f(frontLight, frontLight, frontLight,alpha);glNormal3f(0, 0,-1);glTexCoord2f(textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x+0, y+height, z+0);
		;
		//glNormal3f(0, 0,-1);
		
		//back
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(textureCoords[6],1- textureCoords[7]);glVertex3f(x+0, y+0, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(0.1f +textureCoords[6],1-textureCoords[7]);glVertex3f(x+1, y+0, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight, backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(0.1f +textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x+1, y+height, z+1);
		
		//glNormal3f(0, 0,1);
		glColor4f(backLight, backLight,backLight,alpha);glNormal3f(0, 0,1);glTexCoord2f(textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x+0, y+height, z+1);
		;
		//glNormal3f(0, 0,1);
		
		//right
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(textureCoords[8],0.0f +1- textureCoords[9]);glVertex3f(x+1,y+0, z+0);
		
		//glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(0.1f +textureCoords[8],1-textureCoords[9]);glVertex3f(x+1,y+0, z+1);
		
		//glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(0.1f +textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+1,y+height, z+1);
		
		////glNormal3f(1, 0,0);
		glColor4f(rightLight, rightLight, rightLight,alpha);glNormal3f(1, 0,0);glTexCoord2f(textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+1,y+height, z+0);
		;
		////glNormal3f(1, 0,0);
		
		//left
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(textureCoords[10],0.0f +1-textureCoords[11]);glVertex3f(x+0,y+0, z+0);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(0.1f +textureCoords[10],1-textureCoords[11]);glVertex3f(x+0,y+0, z+1);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(0.1f +textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x+0,y+height, z+1);
		
		////glNormal3f(-1, 0,0);
		glColor4f(leftLight, leftLight, leftLight,alpha);glNormal3f(-1, 0,0);glTexCoord2f(textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x+0,y+height, z+0);
		
		;
		//glEnable(GL_LIGHT0);
		////glNormal3f(-1, 0,0);
	}
}
