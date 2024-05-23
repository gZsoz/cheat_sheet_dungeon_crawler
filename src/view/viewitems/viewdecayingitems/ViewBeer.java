package view.viewitems.viewdecayingitems;

import model.items.decayingitems.Beer;
import view.utils.Coordinates;
import view.utils.ImageReader;

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
