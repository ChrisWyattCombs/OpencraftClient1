package opencraft.graphics.ui;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import opencraft.graphics.DisplayUtills;
import opencraft.graphics.ResourceManager;

public abstract class Button extends UIComponent {
private String text;

public float width;
public float heght;
public float reltiveTextX;
public float reltiveTextY;
public float textScale;



public Button(String text, float x, float y, float width, float heght, float reltiveTextX, float reltiveTextY,
		float textScale) {
	super(x,y);
	this.text = text;
	this.width = width;
	this.heght = heght;
	this.reltiveTextX = reltiveTextX;
	this.reltiveTextY = reltiveTextY;
	this.textScale = textScale;
}
@Override
public void drawAndUpdate() {
	GL11.glColor3f(169f/255f,169f/255f,169f/255f);
	DisplayUtills.drawSqaure(width, heght,x, y, -0.02f);
	GL11.glColor3f(0,0,0);
	((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText(text, x+reltiveTextX, y+reltiveTextY, textScale);
	GL11.glColor3f(1,1,1);
	////System.out.println(Mouse.getNativeCursor());
	if(Cursor.x <= 0.005f * width+x && Cursor.x  >= -0.005f * width+x &&Cursor.y <= 0.005f * heght+y && Cursor.y >= -0.005f * heght+y) {
		GL11.glColor4f(251f/255f, 247f/255f, 25f/255f,0.25f);
		DisplayUtills.drawSqaure(width, heght,x, y, -0.02f);
		GL11.glColor4f(1,1,1,1);
		if(Mouse.isButtonDown(0)) {
			try {
				action();
			} catch (LWJGLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
public String getText() {
	return text;
}
public abstract void action() throws LWJGLException;
}