package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30]; // Create array of sound urls

    public Sound () {

        // Load sound files into sound array
        soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
    }

    // Inject element from sound array to open file
    public void setFile(int i) {

        try {
            // Java formatting to open audio file
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);  // Pass url into audio stream
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e) {
        }
    }

    // COMMANDS FOR THE SOUNDS
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
