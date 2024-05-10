package View.ViewCharacter;

import Character.Cleaner;

import java.awt.*;

/**
 * A takarító kirajzolásáért felelős osztály.
 */
public class ViewCleaner extends ViewCharacter {
	
	/**
	 * A modellbeli takarító, amit reprezentál.
	 */
	private Cleaner charachter;

	public ViewCleaner(Cleaner clean){
		charachter = clean;
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
