package opencraft.graphics.ui;

public abstract class UIComponent {
	protected float x;
	protected float y;
	
	public UIComponent(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public abstract void drawAndUpdate();
	
	
	
}
