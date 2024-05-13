package View.ViewCharacter;

import Character.Cleaner;
import View.Utils.Coordinates;
import View.Utils.Size;

import java.awt.*;

import javax.imageio.ImageReader;

/**
 * A takarító kirajzolásáért felelős osztály.
 */
public class ViewCleaner extends ViewCharacter {
	
	/**
	 * A modellbeli takarító, amit reprezentál.
	 */
	private Cleaner character;

	public ViewCleaner(Cleaner clean, Coordinates pos){
		character = clean;
		image = ImageReader.loadImage("res/images/test/testpic.png");
		size = new Size(60,200);
		coordinates = new Coordinates(pos.getX() + 15, pos.getY() - 20);
	}

	@Override
	public void propertyChanged(String property) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

}
