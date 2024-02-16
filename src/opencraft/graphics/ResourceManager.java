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

public static void addResource(String resource, Object object) throws IOException {
	resourceToObejct.put(resource, object);
}
public static Object getObjectForResource(String resource) {
	return resourceToObejct.get(resource);
}
}
