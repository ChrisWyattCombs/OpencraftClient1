package opencraft.graphics.ui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import opencraft.graphics.DisplayUtills;

public class TextField {
	public String text;
	private float x;
	private float y;
	public float width;
	private int insertPos = 0;
	private float textOffset = 0;
	private int maxCharacterrs;
	
	
	public TextField(String text, float x, float y, float width, int maxCharacterrs) {
		super();
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.maxCharacterrs = maxCharacterrs;
	}


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
		DisplayUtills.font.drawText(text, x-width*0.005f + 0.0001f+textOffset, y-0.0001f, 0.000006f, width*0.005f - 0.0001f , -width*0.005f + 0.0001f , insertPos);
		while (Keyboard.next())
        {
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
