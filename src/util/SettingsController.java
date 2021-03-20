package src.util;

import com.google.gson.Gson;
import src.models.Settings;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SettingsController {
    final Path _workingDir = Paths.get(System.getProperty("user.dir"));
    final Path _settingsFile = Paths.get(_workingDir.toString(), "resources", "data", "settings.json");

    final Gson gson = new Gson();
    public final Settings settings = _loadSettings();

    //loads the settings from json file
    private Settings _loadSettings() {
        try {
            return gson.fromJson(Files.readString(_settingsFile, StandardCharsets.UTF_8), Settings.class);
        } catch (IOException e) {
            return new Settings(
                    java.util.Map.of(
                    "music", 50,
                    "effects", 50
            ));
        }
    }

    //saves the settings in json file
    public void SaveSettings(Settings settings){
        try (Writer writer = new FileWriter(String.valueOf(Paths.get(String.valueOf(_settingsFile))))) {
            gson.toJson(settings, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
