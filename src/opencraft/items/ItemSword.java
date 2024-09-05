package opencraft.items;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;

import opencraft.Item;
import opencraft.graphics.models.ModelSword;

public class ItemSword extends Item {

	@Override
	public void drawIcon(float x, float y, float z, float size) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		glBegin(GL_QUADS);
		ModelSword.drawModel(x, y, z, null, 1, 1, size*10);
		glEnd();
	}

	@Override
	public void rightClickAction() {
		

	}

	@Override
	public int getMaxStack() {
	
		return 1;
	}

	@Override
	public int getID() {
	
		return 6;
	}

}
