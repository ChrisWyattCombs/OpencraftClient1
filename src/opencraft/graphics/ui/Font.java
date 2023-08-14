package opencraft.graphics.ui;
import com.mlomb.freetypejni.*;

import opencraft.graphics.DisplayUtills;
import opencraft.graphics.Vector2i;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import static org.lwjgl.opencl.CL11.*;
import static org.lwjgl.opencl.KHRICD.*;

import static com.mlomb.freetypejni.FreeType.*;
import static org.lwjgl.opengl.GL11.*;
import static com.mlomb.freetypejni.FreeTypeConstants.*;
public class Font {
	private HashMap<Character, CharacterData> Characters = new HashMap<>();
	public Font(String path) {
		
		
		
		Library ft =  FreeType.newLibrary();
	
		Face face = ft.newFace(path, 0);
		face.setPixelSizes( 0, 48);  
		
		//System.out.println("works");
		
	glPixelStorei(GL_UNPACK_ALIGNMENT, 1); // disable byte-alignment restriction
		  
		for (char c = 0; c < 128; c++)
		{
			//System.out.println("works1.5");
		    // load character glyph 
			
		    face.loadChar(c, FT_LOAD_RENDER);
		   
		    
		    
		    // generate texture
		    System.out.println("works2");
		    int texture = GL11.glGenTextures();
		    glBindTexture(GL_TEXTURE_2D, texture);
		    glTexImage2D(
		        GL_TEXTURE_2D,
		        0,
		        GL_RED,
		        face.getGlyphSlot().getBitmap().getWidth(),
		        face.getGlyphSlot().getBitmap().getRows(),
		        0,
		        GL_RED,
		        GL_UNSIGNED_BYTE,
		        face.getGlyphSlot().getBitmap().getBuffer()
		    );
		    
	
		    
		   

		    
		    
		   

		   // glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(),
		    //             0, GL_RGBA, GL_UNSIGNED_BYTE, buffer2);
		  

		    System.out.println("works3");
		    // set texture options
		    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		    // now store character for later use
		    CharacterData character = new CharacterData(
		        texture, 
		        new Vector2i(face.getGlyphSlot().getBitmap().getWidth(), face.getGlyphSlot().getBitmap().getRows()),
		        new Vector2i(face.getGlyphSlot().getBitmapLeft(), face.getGlyphSlot().getBitmapTop()),
		        face.getGlyphSlot().getAdvance().getX()
		    );
		    Characters.put(c, character);
		}
		//System.out.println("works4");
	face.delete();
	ft.delete();
	//glBindTexture(GL_TEXTURE_2D,0);
		
	}
	
	public void drawText(String text, float x, float y, float scale) {
		DisplayUtills.fontShader.bind();
		text = " " + text;
		for(char c : text.toCharArray() )
	    {
	        CharacterData ch = Characters.get(c);
	        //System.out.println("ID: "+ ch.TextureID);
	        float xpos = x + ch.Bearing.getX() * scale;
	        float ypos = y - (ch.Size.getY() - ch.Bearing.getY()) * scale;

	        float w = ch.Size.getX() * scale;
	        float h = ch.Size.getY() * scale;
	        //System.out.println("w: "+w);
	        GL30.glUniform1ui(DisplayUtills.fontShader.uniforms.get("tex"), ch.TextureID);
	        //GL13.glActiveTexture(GL13.GL_TEXTURE0);
	        glBindTexture(GL_TEXTURE_2D, ch.TextureID);
	        DisplayUtills.DrawLetterQuad(xpos, ypos, -0.02f, w, h);
	        x += (ch.Advance >> 6) * scale;
	        glBindTexture(GL_TEXTURE_2D, 0);
	}
		DisplayUtills.fontShader.unbind();
	}
		public void drawText(String text, float x, float y, float scale,float textFieldRightPos, float textFieldLeftPos, int textFieldInsertPos) {
			DisplayUtills.fontShader.bind();
			text = " " + text;
			boolean firstTime = true;
			//x -= textFieldInsertPos * scale;
			int n = 0;
			for(char c : text.toCharArray() )
		    {
		        CharacterData ch = Characters.get(c);
		        //System.out.println("ID: "+ ch.TextureID);
		        float xpos = x + ch.Bearing.getX() * scale;
		        float ypos = y - (ch.Size.getY() - ch.Bearing.getY()) * scale;
		        if(n == textFieldInsertPos) {
		        	float insertXpos = x + (ch.Bearing.getX()*2) * scale;
			        
			        
		        }
		        float w = ch.Size.getX() * scale;
		        float h = ch.Size.getY() * scale;
		        //System.out.println("w: "+w);
		        GL30.glUniform1ui(DisplayUtills.fontShader.uniforms.get("tex"), ch.TextureID);
		        //GL13.glActiveTexture(GL13.GL_TEXTURE0);
		        glBindTexture(GL_TEXTURE_2D, ch.TextureID);
		        if((xpos > textFieldLeftPos && xpos < textFieldRightPos)||firstTime) {
		        DisplayUtills.DrawLetterQuad(xpos, ypos, -0.02f, w, h);
		        firstTime = false;
		        }
		        glBindTexture(GL_TEXTURE_2D, 0);
		        n++;
		        
		        
		        	
		        x += (ch.Advance >> 6) * scale;
		        
		        
		}
		DisplayUtills.fontShader.unbind();
		 
}
}
