import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {

    public static void playSound(String soundPath) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(soundPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }

    public static void playLoop(String soundPath) {
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(soundPath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
        }
    }
}
