package View.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageReader {
	
	public static final String path = "res/images/test/";
	
    public static BufferedImage loadImage(String path){
        BufferedImage image;
        try {
            image = ImageIO.read(new File((path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
