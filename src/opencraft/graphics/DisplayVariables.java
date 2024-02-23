package opencraft.graphics;

import org.lwjgl.opengl.ContextCapabilities;

public class DisplayVariables {
static  int width;
static int height;
public static float camX = -3;
public static float camY=62.5f;
public static float camZ = -3;
public static float camYaw;
public static float CamPitch;
public static double fps;
public static boolean close = false;
public static boolean pressedEsc = false;
public static float deltaTime = 0;
public static float fpsRecord = 0;
public static ContextCapabilities openglContex =null;
}
