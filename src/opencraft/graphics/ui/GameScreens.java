package opencraft.graphics.ui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import opencraft.Item;
import opencraft.Player;
import opencraft.graphics.DisplayUtills;
import opencraft.graphics.ResourceManager;

public class GameScreens {
	public static GameScreen inventory = new GameScreen() {
		Item mouseItem = null;
		boolean mouseHeld = false;
		@Override
		public void drawScreen() {
			
			if(!Mouse.isButtonDown(0)&& !Mouse.isButtonDown(1)) {
				mouseHeld=false;
			}
			
			DisplayUtills.drawSqaure(1.5f, 1f, 0, 0, -0.02f);
			
			for(int x  = 0; x < 9; x++) {
				for(int y  = 0; y <4; y++) {
					GL11.glColor3f(0, 0, 0);
					Player.drawHotbarSquare(0.005f * -1f+(x*((0.005f * 1f *2)/9)), 0.005f * -0.8f + (y*0.005f *0.2f));
					GL11.glColor3f(1, 1, 1);
					if(y == 0) {
						if(Player.hotbar[x] != null) {
							DisplayUtills.shader.bind();
							Player.hotbar[x].drawIcon(0.005f * -1f+(x*((0.005f * 1f *2)/9)), 0.005f * -0.8f + (y*0.005f *0.2f),-0.02f,0.0004f);
							DisplayUtills.shader.unbind();
							GL11.glColor3f(0, 0, 0);
							((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText(String.valueOf(Player.hotbar[x].stack), (0.005f * -1f+(x*((0.005f * 1f *2)/9)))-0.0004f,(0.005f * -0.8f + (y*0.005f *0.2f))+0.0001f,0.0000075f );
							GL11.glColor3f(1, 1, 1);
							if(Mouse.isButtonDown(0) && Math.abs((0.005f * -1f+(x*((0.005f * 1f *2)/9)))-Cursor.x)<0.0002f && Math.abs((0.005f * -0.8f + (y*0.005f *0.2f))-Cursor.y) < 0.0002f && !mouseHeld) {
								if(mouseItem == null) {
								mouseItem = Player.hotbar[x];
								Player.hotbar[x] = null;
								}else if(mouseItem.getID() == Player.hotbar[x].getID()) {
									mouseItem.stack += Player.hotbar[x].stack;
									if(mouseItem.stack > mouseItem.getMaxStack()) {
										Player.hotbar[x].stack = mouseItem.stack - mouseItem.getMaxStack();
										mouseItem.stack = mouseItem.getMaxStack();
									}
									mouseItem= null;
								}
								mouseHeld =true;
							}else if(Mouse.isButtonDown(1)&& Math.abs((0.005f * -1f+(x*((0.005f * 1f *2)/9)))-Cursor.x)<0.0002f && Math.abs((0.005f * -0.8f + (y*0.005f *0.2f))-Cursor.y) < 0.0002f && !mouseHeld) {
								if(mouseItem == null) {
									try {
										mouseItem =Player.hotbar[x].getClass().newInstance();
									} catch (InstantiationException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									mouseItem.stack = 0;
								}
								if(mouseItem.getID() ==Player.hotbar[x].getID()) {
									
									if(mouseItem.stack < mouseItem.getMaxStack()) {
										mouseItem.stack += 1;
										Player.hotbar[x].stack -= 1;
										
									}
								}
								mouseHeld = true;
							}
						}else {
							if(Mouse.isButtonDown(0) && Math.abs((0.005f * -1f+(x*((0.005f * 1f *2)/9)))-Cursor.x)<0.0002f && Math.abs((0.005f * -0.8f + (y*0.005f *0.2f))-Cursor.y) < 0.0002f && !mouseHeld) {
								Player.hotbar[x] = mouseItem;
								mouseItem = null;
								mouseHeld = true;
							}
						}
					}else {
						if(Player.Inventory[x][y-1] != null) {
							DisplayUtills.shader.bind();
							Player.Inventory[x][y-1].drawIcon(0.005f * -1f+(x*((0.005f * 1f *2)/9)), 0.005f * -0.8f + (y*0.005f *0.2f),-0.02f,0.0004f);
							DisplayUtills.shader.unbind();
							GL11.glColor3f(0, 0, 0);
							((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText(String.valueOf(Player.Inventory[x][y-1].stack), (0.005f * -1f+(x*((0.005f * 1f *2)/9)))-0.0004f,(0.005f * -0.8f + (y*0.005f *0.2f))+0.0001f,0.0000075f );
							GL11.glColor3f(1, 1, 1);
							if(Mouse.isButtonDown(0) && Math.abs((0.005f * -1f+(x*((0.005f * 1f *2)/9)))-Cursor.x)<0.0002f && Math.abs((0.005f * -0.8f + (y*0.005f *0.2f))-Cursor.y) < 0.0002f && !mouseHeld) {
								
								if(mouseItem == null) {
								mouseItem = Player.Inventory[x][y-1];
								Player.Inventory[x][y-1] = null;
								}else if(mouseItem.getID() == Player.Inventory[x][y-1].getID()) {
									Player.Inventory[x][y-1].stack += mouseItem.stack;
									
									if(Player.Inventory[x][y-1].stack > Player.Inventory[x][y-1].getMaxStack()) {
										mouseItem.stack = Player.Inventory[x][y-1].stack - Player.Inventory[x][y-1].getMaxStack();
										Player.Inventory[x][y-1].stack = Player.Inventory[x][y-1].getMaxStack();
									}
									mouseItem= null;
								}
								mouseHeld =true;
							}else if(Mouse.isButtonDown(1)&& Math.abs((0.005f * -1f+(x*((0.005f * 1f *2)/9)))-Cursor.x)<0.0002f && Math.abs((0.005f * -0.8f + (y*0.005f *0.2f))-Cursor.y) < 0.0002f && !mouseHeld) {
								if(mouseItem == null) {
									try {
										mouseItem =Player.Inventory[x][y-1].getClass().newInstance();
									} catch (InstantiationException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (IllegalAccessException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									mouseItem.stack = 0;
								}
								if(mouseItem.getID() == Player.Inventory[x][y-1].getID()) {
									
									if(mouseItem.stack < mouseItem.getMaxStack()) {
										mouseItem.stack += 1;
										Player.Inventory[x][y-1].stack -= 1;
										
									}
								}
								mouseHeld = true;
							}
						}else {
							if(Mouse.isButtonDown(0) && Math.abs((0.005f * -1f+(x*((0.005f * 1f *2)/9)))-Cursor.x)<0.0002f && Math.abs((0.005f * -0.8f + (y*0.005f *0.2f))-Cursor.y) < 0.0002f && !mouseHeld) {
								Player.Inventory[x][y-1] = mouseItem;
								mouseItem = null;
								mouseHeld = true;
							}
						}
					}
				}
			}
			if(mouseItem != null) {
			DisplayUtills.shader.bind();
			mouseItem.drawIcon(Cursor.x, Cursor.y, -0.02f,0.0004f);
			DisplayUtills.shader.unbind();
			GL11.glColor3f(0, 0, 0);
			((Font) ResourceManager.getObjectForResource("Opencraft:Font")).drawText(String.valueOf(mouseItem.stack), Cursor.x-0.0004f,Cursor.y+0.0001f,0.0000075f );
			GL11.glColor3f(1, 1, 1);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_E) && !Player.ekeyHeld) {
				Player.currentGameScreen = null;
				Player.ekeyHeld = true;
			}
		}
	
	};
}
