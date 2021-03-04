//Initial file creator: https://github.com/dadope
//Other contributors:
// https://github.com/SomeOtherGod

package src.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SoundController extends Thread{

    //Game working-directory, should correspond to the project root dir
    final Path _workingDir = Paths.get(System.getProperty("user.dir"));
    //Path to the game soundtracks => .../resources/sound
    final Path _soundDir = Paths.get(_workingDir.toString(), "resources", "sound");

    final Path _backgroundMusicDir = Paths.get(_soundDir.toString(), "background");

    private static Clip clip;

    public void run() {
        try {
            clip = AudioSystem.getClip();
            startBackgroundMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVolume(50);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    //TODO: add more variety to the music selection
    public void startBackgroundMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_backgroundMusicDir.toString(), "background.wav"));
        clip.open(inputStream);
    }

    public void setVolume(int volume) {
        FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue(control.getMinimum() * (1 - volume / 100.0f));
    }
}
