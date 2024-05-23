package view.viewcharacters;

import model.characters.Cleaner;
import view.utils.Coordinates;
import view.utils.GameFrame;
import view.utils.ImageReader;

import java.awt.*;

/**
 * A takarító kirajzolásáért felelős osztály.
 */
@SuppressWarnings("serial")
public class ViewCleaner extends ViewCharacter {

	/**
	 * Konstruktor egy takarító nézet létrehozásához.
	 * @param character a modellbeli takarító
	 * @param coor a koordináták
	 */
	public ViewCleaner(Cleaner cleaner, Coordinates coor){
		super(cleaner, coor);
		image = ImageReader.loadImage(ImageReader.path+charactersPath+"cleaner.png");
		GameFrame.mainPanel.add(this);
    	GameFrame.viewCharacters.add(this);
	}
	
	/**
	 * A takarító birtokában lévő tárgyak pozíciójának beállítása.
	 */
	@Override
	public void setItemPositions() {}

	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
	}

	/**
	 * A takarító kirajzolása.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image, coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);
	}

}
