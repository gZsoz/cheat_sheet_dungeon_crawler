package view.viewitems.viewdecayingitems;

import model.items.decayingitems.WetCloth;
import view.utils.Coordinates;
import view.utils.ImageReader;

/**
 * A nedves törlőrongy kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewWetCloth extends ViewDecayingItem {
	
	/**
	 * Konstruktor egy nedves törlőrongy nézet létrehozásához.
	 * @param wc a modellbeli nedves törlőrongy
	 * @param coor a koordináták
	 */
	public ViewWetCloth(WetCloth wc, Coordinates coor) {
		super(wc, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"wetcloth.png");
	}
}
