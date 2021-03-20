//Initial file creator: https://github.com/dadope
//Other contributors:
// https://github.com/SomeOtherGod

package src.util;

import src.models.Settings;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class SoundController extends Thread{

    //Game working-directory, should correspond to the project root dir
    final Path _workingDir = Paths.get(System.getProperty("user.dir"));
    //Path to the game soundtracks => .../resources/sound
    final Path _soundDir = Paths.get(_workingDir.toString(), "resources", "sound");

    //Path zu den unterordenern ins sound
    final Path _backgroundMusicDir = Paths.get(_soundDir.toString(), "background");
    final Path _effectsMusicDir = Paths.get(_soundDir.toString(), "effects");
    final Path _effectsGhostsMusicDir = Paths.get(_effectsMusicDir.toString(), "ghosts");
    final Path _effectsPacmanMusicDir = Paths.get(_effectsMusicDir.toString(), "pacman");

    //Hintergund clip
    private static Clip mainMenuMusic;
    //Ghost clip
    private static Clip chased;
    //Pacman clips
    private static Clip chaseGhost, eatsCherry, eatsGhost, levelUp, moves, onDeath, onLifeUp;
    //Extra clip
    private static Clip finishedLevel;

    public boolean cherryActive = false;

    final double musicMultiplier = 1;
    final double effectsMultiplier = 1;

    int musicVol;
    int effectsVol;

    final SettingsController settingsController;

    public SoundController(SettingsController settingsController){
        this.settingsController = settingsController;

        this.musicVol = settingsController.settings.sound.get("music");
        this.effectsVol = settingsController.settings.sound.get("effects");
    }

    public void run() {
        //Load all sound files into there clip
        try {
            mainMenuMusic = AudioSystem.getClip();
            chased = AudioSystem.getClip();
            chaseGhost = AudioSystem.getClip();
            eatsCherry = AudioSystem.getClip();
            eatsGhost = AudioSystem.getClip();
            levelUp = AudioSystem.getClip();
            moves = AudioSystem.getClip();
            onDeath = AudioSystem.getClip();
            onLifeUp = AudioSystem.getClip();
            finishedLevel = AudioSystem.getClip();
            loadMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //set volume of all clips to 50%
        setMusicVolume(musicVol);
        setEffectVolume(effectsVol);
    }

    public void loadMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_backgroundMusicDir.toString(), "MainMenuMusic1.wav"));
        mainMenuMusic.open(inputStream);
        inputStream = AudioSystem.getAudioInputStream(new File(_effectsGhostsMusicDir.toString(), "Chased.wav"));
        chased.open(inputStream);
        inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "ChaseGhost.wav"));
        chaseGhost.open(inputStream);
        inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "EatsCherry.wav"));
        eatsCherry.open(inputStream);
        inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "EatsGhost.wav"));
        eatsGhost.open(inputStream);
        inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "LevelUp.wav"));
        levelUp.open(inputStream);
        inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "Moves.wav"));
        moves.open(inputStream);
        inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "OnDeath.wav"));
        onDeath.open(inputStream);
        inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "OnLifeUp.wav"));
        onLifeUp.open(inputStream);
        inputStream = AudioSystem.getAudioInputStream(new File(_effectsMusicDir.toString(), "FinishedLevel.wav"));
        finishedLevel.open(inputStream);
    }

    public void setEffectVolume(int volume) {
        settingsController.SaveSettings(new Settings(Map.of(
                "music", musicVol,
                "effects", volume
        )));

        effectsVol = volume;
        settingsController.settings.sound.put("effects", volume);

        FloatControl control;
        control = (FloatControl) chased.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) ((float) Math.log(volume*effectsMultiplier/100) / Math.log(10.0) * 20.0));//Volume value gets changed to db and applied to the clip
        control = (FloatControl) chaseGhost.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) ((float) Math.log(volume*effectsMultiplier/100) / Math.log(10.0) * 20.0));
        control = (FloatControl) eatsCherry.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) ((float) Math.log(volume*effectsMultiplier/100) / Math.log(10.0) * 20.0));
        control = (FloatControl) eatsGhost.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) ((float) Math.log(volume*effectsMultiplier/100) / Math.log(10.0) * 20.0));
        control = (FloatControl) levelUp.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) ((float) Math.log(volume*effectsMultiplier/100) / Math.log(10.0) * 20.0));
        control = (FloatControl) moves.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) ((float) Math.log(volume*effectsMultiplier/100) / Math.log(10.0) * 20.0));
        control = (FloatControl) onDeath.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) ((float) Math.log(volume*effectsMultiplier/100) / Math.log(10.0) * 20.0));
        control = (FloatControl) onLifeUp.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) ((float) Math.log(volume*effectsMultiplier/100) / Math.log(10.0) * 20.0));
        control = (FloatControl) finishedLevel.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) ((float) Math.log(volume*effectsMultiplier/100) / Math.log(10.0) * 20.0));
    }

    public void setMusicVolume(int volume) {
        settingsController.SaveSettings(new Settings(Map.of(
                "music", volume,
                "effects", effectsVol
        )));

        musicVol = volume;
        settingsController.settings.sound.put("music", volume);

        FloatControl control;
        control = (FloatControl) mainMenuMusic.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) ((float) Math.log(volume*musicMultiplier/200) / Math.log(10.0) * 20.0));
    }

    public void pacmanDied() {
        //all sounds stop except onDeath when you go gameover
        chaseGhost.stop();
        moves.stop();
        mainMenuMusic.stop();
        onDeath.loop(1);
    }

    public void pacmanLosesHealth() {
        onDeath.loop(1);
    }

    public void pacmanMoves() {
        //plays different sounds when pacman moves and the cherry is active at the same time
        if (cherryActive) {
            if (!chaseGhost.isRunning()) {
                moves.stop();
                chaseGhost.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } else {
            if (!moves.isRunning()) {
                chaseGhost.stop();
                moves.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    public void pacmanCantMove() {
        moves.stop();
    }

    public void pacmanEatsCherry() {
        eatsCherry.loop(1);
    }

    public void stageCleared() {
        finishedLevel.loop(1);
    }

    public void startMusic() {
        //background music starts and is looped until the game ends
        mainMenuMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopEffects() {
        moves.stop();
        chaseGhost.stop();
    }
}
