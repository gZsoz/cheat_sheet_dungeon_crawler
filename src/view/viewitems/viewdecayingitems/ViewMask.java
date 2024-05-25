package view.viewitems.viewdecayingitems;

import model.items.decayingitems.Mask;
import view.utils.Coordinates;
import view.utils.ImageReader;

/**
 * A maszk kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewMask extends ViewDecayingItem {
	
	/**
	 * Konstruktor maszk nézet létrehozásához.
	 * @param m a modellbeli maszk
	 * @param coor a koordináták
	 */
	public ViewMask(Mask m, Coordinates coor) {
		super(m, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"mask.png");
	}
}
