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
    final Path _effectsMusicDir = Paths.get(_soundDir.toString(), "effects");
    final Path _effectsGhostsMusicDir = Paths.get(_effectsMusicDir.toString(), "ghosts");
    final Path _effectsPacmanMusicDir = Paths.get(_effectsMusicDir.toString(), "pacman");

    private static Clip mainMenuMusic;
    private static Clip chased;
    private static Clip chaseGhost, eatsCherry, eatsGhost, levelUp, moves, onDeath, onLifeUp;
    private static Clip finishedLevel;

    public boolean cherryActive = false;

    int masterVolume;
    double musicMultiplier = 1, effectsMultiplier = 1;

    public void run() {
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
            loadBackgroundMusic();
            loadChasedMusic();
            loadChaseGhostMusic();
            loadEatsCherryMusic();
            loadEatsGhostMusic();
            loadLevelUpMusic();
            loadMovesMusic();
            loadOnDeathMusic();
            loadOnLifeUpMusic();
            loaFinishedLevelMusic();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setMasterVolume(50);
        mainMenuMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    //TODO: add more variety to the music selection
    public void loadBackgroundMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_backgroundMusicDir.toString(), "MainMenuMusic1.wav"));
        mainMenuMusic.open(inputStream);
    }
    public void loadChasedMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_effectsGhostsMusicDir.toString(), "Chased.wav"));
        chased.open(inputStream);
    }
    public void loadChaseGhostMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "ChaseGhost.wav"));
        chaseGhost.open(inputStream);
    }
    public void loadEatsCherryMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "EatsCherry.wav"));
        eatsCherry.open(inputStream);
    }
    public void loadEatsGhostMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "EatsGhost.wav"));
        eatsGhost.open(inputStream);
    }
    public void loadLevelUpMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "LevelUp.wav"));
        levelUp.open(inputStream);
    }
    public void loadMovesMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "Moves.wav"));
        moves.open(inputStream);
    }
    public void loadOnDeathMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "OnDeath.wav"));
        onDeath.open(inputStream);
    }
    public void loadOnLifeUpMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_effectsPacmanMusicDir.toString(), "OnLifeUp.wav"));
        onLifeUp.open(inputStream);
    }
    public void loaFinishedLevelMusic() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(_effectsMusicDir.toString(), "FinishedLevel.wav"));
        finishedLevel.open(inputStream);
    }

    public void setEffectVolume() {
        double volume = Math.log(masterVolume*effectsMultiplier/100) / Math.log(10.0) * 20.0;
        FloatControl control;
        control = (FloatControl) chased.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) volume);
        control = (FloatControl) chaseGhost.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) volume);
        control = (FloatControl) eatsCherry.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) volume);
        control = (FloatControl) eatsGhost.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) volume);
        control = (FloatControl) levelUp.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) volume);
        control = (FloatControl) moves.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) volume);
        control = (FloatControl) onDeath.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) volume);
        control = (FloatControl) onLifeUp.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) volume);
        control = (FloatControl) finishedLevel.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) volume);
    }
    public void setEffectVolume(int volume) {
        effectsMultiplier = volume/100;
        setEffectVolume();
    }

    public void setMusicVolume() {
        double volume = Math.log(masterVolume*musicMultiplier/100) / Math.log(10.0) * 20.0;
        FloatControl control;
        control = (FloatControl) mainMenuMusic.getControl(FloatControl.Type.MASTER_GAIN);
        control.setValue((float) volume);
    }
    public void setMusicVolume(int volume) {
        musicMultiplier = volume/100;
        setMusicVolume();
    }

    public void setMasterVolume(int volume) {
        this.masterVolume = volume;
        setMusicVolume();
        setEffectVolume();
    }

    public void pacmanDied() {
        chaseGhost.stop();
        moves.stop();
        mainMenuMusic.stop();
        onDeath.loop(1);
    }

    public void pacmanLosesHealth() {
        onDeath.loop(1);
    }

    public void pacmanMoves() {
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
}
