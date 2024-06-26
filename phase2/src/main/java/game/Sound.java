package game;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;

import java.net.URL;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30]; 
	
	/**
	 * constructor
	 */
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
		soundURL[5] = getClass().getResource("/sound/chipwall.wav");
	}
	
	/** use i to set soundURL[i]
	 * @param i
	 */
	public void setFile(int i) {
		
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
		}catch(Exception e) {
			
			
		}
		
	}
	
	public void play() {
		
		clip.start();
		
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
}
