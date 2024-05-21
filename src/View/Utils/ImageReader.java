package View.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Ez az osztály végzi a játékban használt összes kép beolvasását a res/images mappából.
 */
public class ImageReader {
	
	/**
	 * A képek elérési útja.
	 */
	public static final String path = "res/images/";
	
	/**
	 * Egy kép betöltése.
	 * @param path elérési út az /images/ mappán belül
	 * @return a betöltött kép
	 */
	public static BufferedImage loadImage(String path) {
	    BufferedImage image;
	    try {
	        image = ImageIO.read(new File((path)));
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	    return image;
	}
}
