package View.ViewItem.ViewDecayingItems;

import Model.Items.DecayingItems.WetCloth;
import View.Utils.Coordinates;
import View.Utils.ImageReader;

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
