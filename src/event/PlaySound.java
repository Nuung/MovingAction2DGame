package event;

import java.io.File;

import javax.sound.sampled.*;

public class PlaySound {
	
	   public static void PlaySoundFile(File Sound)	{ // File object
		   try{ // File object sound file.
				Clip clip = AudioSystem.getClip(); // Clip ~ Audio Format 
				clip.open(AudioSystem.getAudioInputStream(Sound));
				clip.start(); // Start
			} catch(Exception e) {
				// error
				System.out.println("Sound Error : "+e);
			}
		} // try ~ catch
}