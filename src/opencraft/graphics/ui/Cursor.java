package opencraft.graphics.ui;


import static org.lwjgl.opengl.GL11.glColor3f;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import opencraft.graphics.DisplayUtills;

public class Cursor {
public static float x;
public static float y;
public static Texture t;
public static void setup() {
	try {
		t = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("C:\\Opencraft\\assests\\textures\\Cursor.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Mouse.setGrabbed(true);
}
public static void updateAndDrawMouse() {
DisplayUtills.shader.bind();

	x += Mouse.getDX()/50000f;
	y += Mouse.getDY()/50000f;
	//org.newdawn.slick.Color.white.bind();

	GL30.glUniform1ui(DisplayUtills.shader.uniforms.get("tex"), t.getTextureID());
	//GL13.glActiveTexture(GL13.GL_TEXTURE0);
	GL11.glBindTexture(GL11.GL_TEXTURE_2D, t.getTextureID());
	DisplayUtills.drawSqaure(0.06f, 0.06f, x, y, -0.02f);
	GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	//GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	DisplayUtills.shader.unbind();
}
}
