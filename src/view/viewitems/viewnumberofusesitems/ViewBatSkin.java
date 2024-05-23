package view.viewitems.viewnumberofusesitems;

import model.items.numberofusesitems.BatSkin;
import view.utils.Coordinates;
import view.utils.ImageReader;

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

