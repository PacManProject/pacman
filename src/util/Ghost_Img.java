//Initial file creator: https://github.com/SomeOtherGod

package src.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Ghost_Img {
    //Game working-directory, should correspond to the project root dir
    Path workingDir = Paths.get(System.getProperty("user.dir"));
    //Path to the general sprite, where the game models are stored .../resources/img/General Sprites.png
    Path spritePath = Paths.get(workingDir.toString(), "resources", "img", "General Sprites.png");

    BufferedImage sprite;

    ArrayList<BufferedImage> ghostImg;

    public Ghost_Img() {

        try {
            sprite = ImageIO.read(new File(spritePath.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<BufferedImage> fillBlinky() {
        try {
            ghostImg.add(sprite.getSubimage(0, 64, 14, 14));
            ghostImg.add(sprite.getSubimage(16, 64, 14, 14));
            ghostImg.add(sprite.getSubimage(32, 64, 14, 14));
            ghostImg.add(sprite.getSubimage(48, 64, 14, 14));
            ghostImg.add(sprite.getSubimage(64, 64, 14, 14));
            ghostImg.add(sprite.getSubimage(80, 64, 14, 14));
            ghostImg.add(sprite.getSubimage(96, 64, 14, 14));
            ghostImg.add(sprite.getSubimage(112, 64, 14, 14));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ghostImg;
    }

    private ArrayList<BufferedImage> fillPinky() {
        try {
            ghostImg.add(sprite.getSubimage(0, 80, 14, 14));
            ghostImg.add(sprite.getSubimage(16, 80, 14, 14));
            ghostImg.add(sprite.getSubimage(32, 80, 14, 14));
            ghostImg.add(sprite.getSubimage(48, 80, 14, 14));
            ghostImg.add(sprite.getSubimage(64, 80, 14, 14));
            ghostImg.add(sprite.getSubimage(80, 80, 14, 14));
            ghostImg.add(sprite.getSubimage(96, 80, 14, 14));
            ghostImg.add(sprite.getSubimage(112, 80, 14, 14));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ghostImg;
    }

    private ArrayList<BufferedImage> fillInky() {
        try {
            ghostImg.add(sprite.getSubimage(0, 96, 14, 14));
            ghostImg.add(sprite.getSubimage(16, 96, 14, 14));
            ghostImg.add(sprite.getSubimage(32, 96, 14, 14));
            ghostImg.add(sprite.getSubimage(48, 96, 14, 14));
            ghostImg.add(sprite.getSubimage(64, 96, 14, 14));
            ghostImg.add(sprite.getSubimage(80, 96, 14, 14));
            ghostImg.add(sprite.getSubimage(96, 96, 14, 14));
            ghostImg.add(sprite.getSubimage(112, 96, 14, 14));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ghostImg;
    }

    private ArrayList<BufferedImage> fillClyde() {
        try {
            ghostImg.add(sprite.getSubimage(0, 112, 14, 14));
            ghostImg.add(sprite.getSubimage(16, 112, 14, 14));
            ghostImg.add(sprite.getSubimage(32, 112, 14, 14));
            ghostImg.add(sprite.getSubimage(48, 112, 14, 14));
            ghostImg.add(sprite.getSubimage(64, 112, 14, 14));
            ghostImg.add(sprite.getSubimage(80, 112, 14, 14));
            ghostImg.add(sprite.getSubimage(96, 112, 14, 14));
            ghostImg.add(sprite.getSubimage(112, 112, 14, 14));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ghostImg;
    }

    public ArrayList getImgs() {
        int randomInt = (int)(Math.random()*4);
        switch (randomInt) {
            case 0:
                return fillBlinky();
            case 1:
                return fillClyde();
            case 2:
                return fillInky();
            case 3:
                return fillPinky();
            default:
                return fillInky();
        }
    }
}
