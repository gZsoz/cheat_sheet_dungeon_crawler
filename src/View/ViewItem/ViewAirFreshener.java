package View.ViewItem;

import Items.AirFreshener;
import View.Utils.Coordinates;
import View.Utils.ImageReader;

/**
 * A légfrissítő kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewAirFreshener extends ViewNumberOfUsesItem {
	
	/**
	 * Konstruktor egy légfrissítő nézet létrehozásához.
	 * @param air a modellbeli légfrissítő
	 * @param coor a koordináták
	 */
	public ViewAirFreshener(AirFreshener air, Coordinates coor) {
		super(air, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"airfreshener.png");
	}
}
