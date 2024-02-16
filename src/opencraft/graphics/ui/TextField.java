package opencraft.graphics.ui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import opencraft.graphics.DisplayUtills;
import opencraft.graphics.ResourceManager;

public class TextField extends UIComponent {
	public String text;
	
	public float width;
	private int insertPos = 0;
	private float textOffset = 0;
	private int maxCharacterrs;
	public static int selectedID = -1;
	public static int lastID = -1;
	public int id;
	public TextField(String text, float x, float y, float width, int maxCharacterrs) {
		super(x,y);
		this.text = text;
		this.width = width;
		this.maxCharacterrs = maxCharacterrs;
		id = lastID+1;
		lastID++;                            
		
	}

	@Override
	public void drawAndUpdate() {
		GL11.glColor3f(0, 0,0);
		DisplayUtills.drawSqaure(width, 0.075f, x, y, -0.02f);
		GL11.glColor3f(1, 1,1);
		GL11.glColor3f(169f/255f,169f/255f,169f/255f);
		DisplayUtills.drawSqaure(width+0.01f, 0.01f, x, y+( 0.075f*0.005f ), -0.02f);
		DisplayUtills.drawSqaure(width+0.01f, 0.01f, x, y-( 0.075f*0.005f ), -0.02f);
		DisplayUtills.drawSqaure(0.01f,  0.075f, x+(width*0.005f), y, -0.02f);
		DisplayUtills.drawSqaure(0.01f,  0.075f, x-(width*0.005f ), y, -0.02f);
		GL11.glColor3f(1, 1,1);
		((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText(text, x-width*0.005f + 0.0001f+textOffset, y-0.0001f, 0.000006f, width*0.005f - 0.0001f , -width*0.005f + 0.0001f , insertPos);
		if(Cursor.x <= 0.005f * width+x && Cursor.x  >= -0.005f * width+x &&Cursor.y <= 0.005f * 0.075f+y && Cursor.y >= -0.005f * 0.075f+y && Mouse.isButtonDown(0)) {
			selectedID = id;
		}
		
		while (Keyboard.next())
        {
			if(selectedID == id) {
            if (Keyboard.getEventKeyState())
            {
                // get key info
                int key = Keyboard.getEventKey();
                char ch = Keyboard.getEventCharacter();
                int ascii = (int) ch;

                // delete case
                if(key == Keyboard.KEY_BACK) {
                    String newText = "";
                    
                    for(int i = 0;i < text.length()-1; i++) {
                    	newText+=text.charAt(i);
                    }
                    text = newText;
                }

                // append if common char
                if(((ascii >= 32 && ascii <= 126) || (ascii >= 128 && ascii <= 255))&&text.length() < maxCharacterrs)
                	text+=ch;
            }
        }
		}
		
	}


}
