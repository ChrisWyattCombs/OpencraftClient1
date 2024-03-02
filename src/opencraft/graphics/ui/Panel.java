package opencraft.graphics.ui;

import java.util.ArrayList;

import opencraft.graphics.DisplayUtills;

public class Panel extends UIComponent {
	private float width;
	private float height;
	private float realHeight = height;
	private float start = 0f;
	private float end = 1f;
	private float scroll = 0f;
	public ArrayList<UIComponent> uiComponents;
	public Panel(float x, float y, float width, float height) {
		super(x, y);
		this.width = width;
		this.height = height;
		
	}

	@Override
	public void drawAndUpdate() {
		DisplayUtills.drawSqaure(width/2, height/2, realHeight, height, end);
		
		
	}

}
