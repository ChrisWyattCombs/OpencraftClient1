package opencraft.graphics;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.PNGDecoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;
public class Texture {
    private String filepath;
    private int texID;
    private int width,height;


    public Texture(){

    }

    public void init(String filepath) throws IOException {
        this.filepath = filepath;


        //Generate Texture on GPU
        texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texID);
        glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE );
        glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE );
        //Set texture Parameters
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        //when shrinking, pixelate
        
        
       // glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        
        ByteBuffer image = null;
        InputStream in = new FileInputStream(filepath);
        try {
           PNGDecoder decoder = new PNGDecoder(in);
         
           //System.out.println("width="+decoder.getWidth());
           //System.out.println("height="+decoder.getHeight());
         
           image = ByteBuffer.allocateDirect(4*decoder.getWidth()*decoder.getHeight());
           try {
			decoder.decode(image, decoder.getWidth()*4, PNGDecoder.RGBA);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           image.flip();
        } finally {
           try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        if(image!=null){
            this.width = width.get(0);
            this.height = height.get(0);
            if(channels.get(0) == 3) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            } else if(channels.get(0) == 4) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            }
        } 

       
       
        
       
    }
    public void bind(){
    	glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D,texID);
        
    }

    public void unbind(){
    
        glBindTexture(GL_TEXTURE_2D,0);
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getTextureID(){
        return texID;
    }
}