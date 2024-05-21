package View.ViewItem;

import Items.Beer;
import View.Utils.Coordinates;
import View.Utils.ImageReader;

/**
 * A sör kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewBeer extends ViewDecayingItem {
	
	/**
	 * Konstruktor egy sör nézet létrehozásához.
	 * @param beer a modellbeli sör
	 * @param coor a koordináták
	 */
	public ViewBeer(Beer beer, Coordinates coor) {
		super(beer, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"beer.png");
	}
}
