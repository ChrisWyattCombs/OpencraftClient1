package opencraft.graphics;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ResourceManager {
private static HashMap<String, Object> resourceToObejct = new HashMap<>();

public static void loadTexture(String path, String resource) throws IOException {
	resourceToObejct.put(resource, TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path)));
}
public static Object getObjectForResource(String resource) {
	return resourceToObejct.get(resource);
}
}
