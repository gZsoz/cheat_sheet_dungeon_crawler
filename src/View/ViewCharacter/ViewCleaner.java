package View.ViewCharacter;

import Character.Cleaner;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.Size;

import java.awt.*;

/**
 * A takarító kirajzolásáért felelős osztály.
 */
public class ViewCleaner extends ViewCharacter {
	
	/**
	 * A modellbeli takarító, amit reprezentál.
	 */
	private Cleaner character;

	public ViewCleaner(Cleaner clean, Coordinates pos){
		super("testpic.png", pos);
		character = clean;
		character.subscribe(this);
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
