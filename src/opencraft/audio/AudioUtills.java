package opencraft.audio;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.urish.openal.ALException;
import org.urish.openal.OpenAL;
import org.urish.openal.Source;

import opencraft.Player;
import paulscode.sound.SoundSystem;
import paulscode.sound.SoundSystemConfig;
import paulscode.sound.SoundSystemException;
import paulscode.sound.codecs.CodecJOgg;
import paulscode.sound.codecs.CodecJOrbis;
import paulscode.sound.libraries.LibraryJavaSound;
import paulscode.sound.libraries.LibraryLWJGLOpenAL;

public class AudioUtills {
	public static Source source = null;
static {
	try {
	
		SoundSystemConfig.addLibrary(LibraryLWJGLOpenAL.class);
		SoundSystemConfig.setCodec("ogg", CodecJOrbis.class);
		SoundSystemConfig.setSoundFilesPackage("sounds");
	} catch (SoundSystemException e) {
		System.err.println("error linking with the pluggins");
	}
}
public static void loadSounds() throws ALException, IOException, UnsupportedAudioFileException {//AudioVaribles.soundSystem.loadSound("C:\\Opencraft\\assests\\sounds\\stone1.ogg");
	AudioVaribles.soundSystem = new SoundSystem();
/*
	try {
		AudioVaribles.openal = new OpenAL();
	} catch (ALException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	*/
	String path = "/stone1.ogg";
URL url = new File(path).toURL();
AudioVaribles.soundSystem.newSource(false, "BlockBreak", path, false, 0, 0, 0, 0, 0);
//	Source source = AudioVaribles.openal.createSource(new File("example.wav"));
	
	//AudioVaribles.soundSystem.newSource(false, "BlockBreak", url, path, false, 0, 0, 0, 0, 0);

}

public static void updateSoundSystem() {
	
	
}
public static void playSound(String sourceName, float x, float y, float z) throws ALException {
	
AudioVaribles.soundSystem.setListenerAngle(Player.yaw);
	AudioVaribles.soundSystem.setListenerPosition(Player.x, Player.y, Player.z);
	AudioVaribles.soundSystem.setListenerVelocity(Player.velocityX, Player.velocityY, Player.velocityZ);
	AudioVaribles.soundSystem.setPosition(sourceName, x, y, z);
	AudioVaribles.soundSystem.quickPlay(false, "/stone1.ogg", false, x, y, z, 0, z);

//	source.setPosition(x-Player.x, y-Player.y, z-Player.z);
//	AudioVaribles.soundSystem.setVolume(sourceName,1 );
//	source.play();

}
}
