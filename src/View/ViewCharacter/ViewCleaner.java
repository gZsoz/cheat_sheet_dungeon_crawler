package View.ViewCharacter;

import Character.Cleaner;
import View.Controller.Controller;
import View.Utils.Coordinates;
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
		super("cleaner.png", pos);
		character = clean;
		character.subscribe(this);
		Controller.characters.put(character, this);
	}

	@Override
	public void propertyChanged(String property) {


	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		// példa
		g2D.drawImage(image, coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);

	}

}
