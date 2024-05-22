package View.ViewItem.ViewNumberOfUsesItems;

import Model.Items.NumberOfUsesItems.CabbageCamembert;
import View.Utils.Coordinates;
import View.Utils.ImageReader;

/**
 * A káposztás camembert kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewCabbageCamembert extends ViewNumberOfUsesItem {
	
	/**
	 * Konstruktor egy káposztás camembert nézet létrehozásához.
	 * @param cc a modellbeli káposztás camembert
	 * @param coor a koordináták
	 */
	public ViewCabbageCamembert(CabbageCamembert cc, Coordinates coor) {
		super(cc, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"camambert.png");
	}
}
