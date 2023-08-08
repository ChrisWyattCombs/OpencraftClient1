package opencraft.graphics.ui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import opencraft.graphics.DisplayUtills;

public abstract class Button {
private String text;
private float x;
private float y;
public float width;
public float heght;
public float reltiveTextX;
public float reltiveTextY;
public float textScale;



public Button(String text, float x, float y, float width, float heght, float reltiveTextX, float reltiveTextY,
		float textScale) {
	super();
	this.text = text;
	this.x = x;
	this.y = y;
	this.width = width;
	this.heght = heght;
	this.reltiveTextX = reltiveTextX;
	this.reltiveTextY = reltiveTextY;
	this.textScale = textScale;
}
public void drawButton() {
	GL11.glColor3f(169f/255f,169f/255f,169f/255f);
	DisplayUtills.drawSqaure(width, heght,x, y, -0.02f);
	GL11.glColor3f(0,0,0);
	DisplayUtills.font.drawText(text, x+reltiveTextX, y+reltiveTextY, textScale);
	GL11.glColor3f(1,1,1);
	//System.out.println(Mouse.getNativeCursor());
	if(Cursor.x <= 0.005f * width+x && Cursor.x  >= -0.005f * width+x &&Cursor.y <= 0.005f * heght+y && Cursor.y >= -0.005f * heght+y) {
		GL11.glColor4f(251f/255f, 247f/255f, 25f/255f,0.25f);
		DisplayUtills.drawSqaure(width, heght,x, y, -0.02f);
		GL11.glColor4f(1,1,1,1);
		if(Mouse.isButtonDown(0)) {
			action();
		}
	}
}
public abstract void action();
}