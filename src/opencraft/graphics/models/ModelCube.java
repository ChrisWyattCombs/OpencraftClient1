package opencraft.graphics.models;

import static org.lwjgl.opengl.GL11.*;



public class ModelCube {

	
	public static void drawModel(float x, float y, float z, float[] textureCoords) {
		
		//top
		glTexCoord2f(0+textureCoords[0],1-(0.0f + textureCoords[1]));glVertex3f(x+0.5f, y+0.5f, z+0.5f);
		
		//glNormal3f(0, 1,0);
		glTexCoord2f(0.1f+textureCoords[0],1- +textureCoords[1]);glVertex3f(x-0.5f, y+0.5f, z+0.5f);
		
		//glNormal3f(0, 1,0);
		glTexCoord2f(0.1f +0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x-0.5f, y+0.5f, z-0.5f);
		
		//glNormal3f(0, 1,0);
		glTexCoord2f(0+textureCoords[0],1-(0.1f +textureCoords[1]));glVertex3f(x+0.5f, y+0.5f, z-0.5f);
		;
		//glNormal3f(0, 1,0);
		
		//bottom
		
		glTexCoord2f(textureCoords[2],1- +textureCoords[3]);glVertex3f(x+0.5f, y-0.5f, z+0.5f);
		
		//glNormal3f(0, -1,0);
		glTexCoord2f(0.1f +textureCoords[2],1-textureCoords[3]);glVertex3f(x-0.5f, y-0.5f, z+0.5f);
		
		//glNormal3f(0, -1,0);
		glTexCoord2f(0.1f +textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x-0.5f, y-0.5f, z-0.5f);
		
		//glNormal3f(0, -1,0);
		glTexCoord2f(textureCoords[2],1-(0.1f +textureCoords[3]));glVertex3f(x+0.5f, y-0.5f, z-0.5f);
		;
		//glNormal3f(0, -1,0);
		
		//front
		glTexCoord2f(textureCoords[4],0.0f + 1-textureCoords[5]);glVertex3f(x+0.5f, y+0.5f, z-0.5f);
		
		//glNormal3f(0, 0,-1);
		glTexCoord2f(0.1f +textureCoords[4],1-textureCoords[5]);glVertex3f(x-0.5f, y+0.5f, z-0.5f);
		
		//glNormal3f(0, 0,-1);
		glTexCoord2f(0.1f +textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x-0.5f, y-0.5f, z-0.5f);
		
		//glNormal3f(0, 0,-1);
		glTexCoord2f(textureCoords[4],1-(0.1f +textureCoords[5]));glVertex3f(x+0.5f, y-0.5f, z-0.5f);
		;
		//glNormal3f(0, 0,-1);
		
		//back
		glTexCoord2f(textureCoords[6],1- textureCoords[7]);glVertex3f(x+0.5f, y+0.5f, z+0.5f);
		
		//glNormal3f(0, 0,1);
		glTexCoord2f(0.1f +textureCoords[6],1-textureCoords[7]);glVertex3f(x-0.5f, y+0.5f, z+0.5f);
		
		//glNormal3f(0, 0,1);
		glTexCoord2f(0.1f +textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x-0.5f, y-0.5f, z+0.5f);
		
		//glNormal3f(0, 0,1);
		glTexCoord2f(textureCoords[6],1-(0.1f +textureCoords[7]));glVertex3f(x+0.5f, y-0.5f, z+0.5f);
		;
		//glNormal3f(0, 0,1);
		
		//right
		glTexCoord2f(textureCoords[8],0.0f +1- textureCoords[9]);glVertex3f(x+0.5f,y+0.5f, z+0.5f);
		
		//glNormal3f(1, 0,0);
		glTexCoord2f(0.1f +textureCoords[8],1-textureCoords[9]);glVertex3f(x+0.5f,y-0.5f, z+0.5f);
		
		//glNormal3f(1, 0,0);
		glTexCoord2f(0.1f +textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+0.5f,y-0.5f, z-0.5f);
		
		////glNormal3f(1, 0,0);
		glTexCoord2f(textureCoords[8],1-(0.1f +textureCoords[9]));glVertex3f(x+0.5f,y+0.5f, z-0.5f);
		;
		////glNormal3f(1, 0,0);
		
		//left
		glTexCoord2f(textureCoords[10],0.0f +1-textureCoords[11]);glVertex3f(x-0.5f,y-+0.5f, z-0.5f);
		
		////glNormal3f(-1, 0,0);
		glTexCoord2f(0.1f +textureCoords[10],1-textureCoords[11]);glVertex3f(x-0.5f,y-0.5f, z+0.5f);
		
		////glNormal3f(-1, 0,0);
		glTexCoord2f(0.1f +textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x-0.5f,y+0.5f, z+0.5f);
		
		////glNormal3f(-1, 0,0);
		glTexCoord2f(textureCoords[10],1-(0.1f +textureCoords[11]));glVertex3f(x-0.5f,y+0.5f, z-0.5f);
		
		;
		////glNormal3f(-1, 0,0);
	}

}
