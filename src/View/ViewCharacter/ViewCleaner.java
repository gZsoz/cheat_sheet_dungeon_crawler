package View.ViewCharacter;

import Character.Cleaner;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A takarító kirajzolásáért felelős osztály.
 */
public class ViewCleaner extends ViewCharacter {

	public ViewCleaner(Cleaner cleaner, Coordinates pos){
		super(cleaner, pos);
		image = ImageReader.loadImage(ImageReader.path+charactersPath+"cleaner.png");
		GameFrame.container.add(this);
    	GameFrame.viewCharacters.add(this);
	}
	
	@Override
	public void setItemPositions() {}

	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		// példa
		g2D.drawImage(image, coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);

	}

}
