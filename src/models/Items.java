//Initial file creator: https://github.com/dadope

package src.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public enum Items {
    //TODO: add more items

    none (0,0,0,1,1,
            "No item is present"),
    score (1,16, 32, 13, 13,
            "A score item, adds to your score"),
    cherry (2,32, 48, 13, 13,
            "Gives you the power to eat ghosts for a specific amount of time",
            java.util.Map.of(
                    "actionTime", 10 //time of action of the item, in seconds
            )
    );


    // Code to identify the item in the maps
    public final int itemCode;

    // Image to be displayed on the map
    public BufferedImage image;

    // Description of the item for a later help screen
    public final String itemDescription;

    // Variables that might be needed later on
    // FIXME: DO NOT edit java.util.Map to Map, as there are import conflicts with .Map
    public final java.util.Map[] extraVariables;

    Items(int itemCode, int x, int y, int height, int width, String description, java.util.Map... extraVariables){
        this.itemCode = itemCode;
        this.itemDescription = description;

        this.extraVariables = extraVariables;

        Path spritePath = Paths.get(System.getProperty("user.dir"), "resources", "img", "General Sprites.png");

        try {
            this.image= ImageIO.read(new File(spritePath.toString())).getSubimage(x, y, width, height);
        } catch (IOException e) {
            this.image = null;
            e.printStackTrace();
        }
    }
}