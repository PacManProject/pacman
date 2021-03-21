//Initial file creator: https://github.com/dadope

package src.models;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public enum Items {

    none (0, "No item is present"),
    score (1,4,175, 47, 13, 13,
            "A score item, adds to your score"),
    antiGhostPowerUp(2,1.5, 160, 49 , 13, 13,
            "Gives you the power to eat ghosts for a specific amount of time",
            java.util.Map.of(
                    "actionTime", 10 //time of action of the item, in seconds
            )
    ),
    cherry(3, 1.5, 32, 48, 13, 13,
            "Adds 10 Points to your score"
    );


    // Code to identify the item in the maps
    public final int itemCode;

    // Scale of the item on screen (< 1.0)
    public final double scale;

    // Image to be displayed on the map
    public BufferedImage image;

    // Description of the item for a later help screen
    public final String itemDescription;

    // Variables for extra options
    public final java.util.Map extraVariables;

    Items(int itemCode, String itemDescription, java.util.Map... extraVariables){
        this.scale = 1;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;

        this.extraVariables = (extraVariables.length >= 1) ? extraVariables[0] : Map.of();
        this.image = null;
    }

    Items(int itemCode, double scale, int x, int y, int height, int width, String description, java.util.Map... extraVariables){
        this.scale = scale;
        this.itemCode = itemCode;
        this.itemDescription = description;

        this.extraVariables = (extraVariables.length >= 1) ? extraVariables[0] : Map.of();

        Path spritePath = Paths.get(System.getProperty("user.dir"), "resources", "img", "General Sprites.png");

        try {
            this.image= ImageIO.read(new File(spritePath.toString())).getSubimage(x, y, width, height);
        } catch (IOException e) {
            this.image = null;
            e.printStackTrace();
        }
    }
}