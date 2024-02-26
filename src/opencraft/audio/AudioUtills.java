package opencraft.audio;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import opencraft.Player;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOgg;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class AudioUtills {
static {
	try {
	
		SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
		SoundSystemConfig.setCodec("ogg", CodecJOgg.class);
		SoundSystemConfig.setSoundFilesPackage("sounds");
	} catch (SoundSystemException e) {
		System.err.println("error linking with the pluggins");
	}
}
public static void loadSounds() throws MalformedURLException {//AudioVaribles.soundSystem.loadSound("C:\\Opencraft\\assests\\sounds\\stone1.ogg");
	AudioVaribles.soundSystem = new SoundSystem();
	String path = "/stone1.ogg";
URL url = new File(path).toURL();
	AudioVaribles.soundSystem.newSource(false, "BlockBreak", path, false, 0, 0, 0, 0, 0);
//AudioVaribles.soundSystem.newSource(false, "BlockBreak", url, path, false, 0, 0, 0, 0, 0);
}

public static void updateSoundSystem() {
	
	
}
public static void playSound(String sourceName, float x, float y, float z) {
//	AudioVaribles.soundSystem.setListenerAngle(Player.yaw);
//	AudioVaribles.soundSystem.setListenerPosition(Player.x, Player.y, Player.z);
//	AudioVaribles.soundSystem.setListenerVelocity(Player.velocityX, Player.velocityY, Player.velocityZ);
//	AudioVaribles.soundSystem.setPosition(sourceName, x, y, z);
	//AudioVaribles.soundSystem.setVolume(sourceName,1 );
	//AudioVaribles.soundSystem.play(sourceName);

}
}
