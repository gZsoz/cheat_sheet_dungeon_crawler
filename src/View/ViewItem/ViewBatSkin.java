package View.ViewItem;

import Items.BatSkin;
import View.Utils.Coordinates;
import View.Utils.ImageReader;

/**
 * A denevérbőr kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewBatSkin extends ViewNumberOfUsesItem {
	
	/**
	 * Konstruktor egy denevérbőr nézet létrehozásához.
	 * @param bat a modellbeli denevérbőr
	 * @param coor a koordináták
	 */
	public ViewBatSkin(BatSkin bat, Coordinates coor) {
		super(bat, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"batskin.png");
	}
}

