package opencraft.graphics.ui;

import opencraft.graphics.Vector2i;

public class CharacterData {
	 int TextureID;  // ID handle of the glyph texture
	 Vector2i  Size;       // Size of glyph
	 Vector2i   Bearing;    // Offset from baseline to left/top of glyph
	 int Advance;    // Offset to advance to next glyph
	public CharacterData(int textureID, Vector2i size, Vector2i bearing, int advance) {
		super();
		TextureID = textureID;
		Size = size;
		Bearing = bearing;
		Advance = advance;
	}
	 
	 
	

}
