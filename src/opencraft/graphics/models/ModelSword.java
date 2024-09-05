package opencraft.graphics.models;
import static org.lwjgl.opengl.GL11.*;
public class ModelSword {
	public static void drawModel(float x, float y, float z, float[] textureCoords,float light, float alpha,float size) {
		float bladeLength = -1.5f*size;
		float bladeWidth = 0.125f*size;
		float bladeHeight = 0.01f*size;
		
		float handleLength = -0.2f*size;
		float handleWidth = 0.05f*size;
		float handleHeight = 0.02f*size;
		
		//top of blade
		glVertex3f(x-(bladeWidth/2), y+(bladeHeight/2), z);
		glVertex3f(x-(bladeWidth/2), y+(bladeHeight/2), z+bladeLength);
		glVertex3f(x+(bladeWidth/2), y+(bladeHeight/2), z+bladeLength);
		glVertex3f(x+(bladeWidth/2), y+(bladeHeight/2), z);
		//bottom of blade
		glVertex3f(x-(bladeWidth/2), y-(bladeHeight/2), z);
		glVertex3f(x-(bladeWidth/2), y-(bladeHeight/2), z+bladeLength);
		glVertex3f(x+(bladeWidth/2), y-(bladeHeight/2), z+bladeLength);
		glVertex3f(x+(bladeWidth/2), y-(bladeHeight/2), z);
		//front of blade
		glVertex3f(x-(bladeWidth/2), y-(bladeHeight/2), z+bladeLength);
		glVertex3f(x+(bladeWidth/2), y-(bladeHeight/2), z+bladeLength);
		glVertex3f(x+(bladeWidth/2), y+(bladeHeight/2), z+bladeLength);
		glVertex3f(x-(bladeWidth/2), y+(bladeHeight/2), z+bladeLength);
		//back of blade
		glVertex3f(x-(bladeWidth/2), y-(bladeHeight/2), z);
		glVertex3f(x+(bladeWidth/2), y-(bladeHeight/2), z);
		glVertex3f(x+(bladeWidth/2), y+(bladeHeight/2), z);
		glVertex3f(x-(bladeWidth/2), y+(bladeHeight/2), z);
		//top of blade
		glVertex3f(x-(bladeWidth/2), y+(bladeHeight/2), z);
		glVertex3f(x+(bladeWidth/2), y+(bladeHeight/2), z);
		glVertex3f(x+(bladeWidth/2), y+(bladeHeight/2), z+bladeLength);
		glVertex3f(x-(bladeWidth/2), y+(bladeHeight/2), z+bladeLength);
		//bottom of blade
		glVertex3f(x-(bladeWidth/2), y+(bladeHeight/2), z);
		glVertex3f(x+(bladeWidth/2), y+(bladeHeight/2), z);
		glVertex3f(x+(bladeWidth/2), y+(bladeHeight/2), z+bladeLength);
		glVertex3f(x-(bladeWidth/2), y+(bladeHeight/2), z+bladeLength);
		
		//top of handle
		glVertex3f(x-(handleWidth/2), y+(handleHeight/2), z);
		glVertex3f(x-(handleWidth/2), y+(handleHeight/2), z-handleLength);
		glVertex3f(x+(handleWidth/2), y+(handleHeight/2), z-handleLength);
		glVertex3f(x+(handleWidth/2), y+(handleHeight/2), z);
		//bottom of handle
		glVertex3f(x-(handleWidth/2), y-(handleHeight/2), z);
		glVertex3f(x-(handleWidth/2), y-(handleHeight/2), z-handleLength);
		glVertex3f(x+(handleWidth/2), y-(handleHeight/2), z-handleLength);
		glVertex3f(x+(handleWidth/2), y-(handleHeight/2), z);
		//front of handle
		glVertex3f(x-(handleWidth/2), y-(handleHeight/2), z-handleLength);
		glVertex3f(x+(handleWidth/2), y-(handleHeight/2), z-handleLength);
		glVertex3f(x+(handleWidth/2), y+(handleHeight/2), z-handleLength);
		glVertex3f(x-(handleWidth/2), y+(handleHeight/2), z-handleLength);
		//back of handle
		glVertex3f(x-(handleWidth/2), y-(handleHeight/2), z);
		glVertex3f(x+(handleWidth/2), y-(handleHeight/2), z);
		glVertex3f(x+(handleWidth/2), y+(handleHeight/2), z);
		glVertex3f(x-(handleWidth/2), y+(handleHeight/2), z);
		//top of handle
		glVertex3f(x-(handleWidth/2), y+(handleHeight/2), z);
		glVertex3f(x+(handleWidth/2), y+(handleHeight/2), z);
		glVertex3f(x+(handleWidth/2), y+(handleHeight/2), z-handleLength);
		glVertex3f(x-(handleWidth/2), y+(handleHeight/2), z-handleLength);
		//bottom of handle
		glVertex3f(x-(handleWidth/2), y-(handleHeight/2), z);
		glVertex3f(x+(handleWidth/2), y-(handleHeight/2), z);
		glVertex3f(x+(handleWidth/2), y-(handleHeight/2), z-handleLength);
		glVertex3f(x-(handleWidth/2), y-(handleHeight/2), z-handleLength);
		
		
	}
}
