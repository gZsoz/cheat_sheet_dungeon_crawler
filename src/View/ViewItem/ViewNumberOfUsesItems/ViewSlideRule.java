package View.ViewItem.ViewNumberOfUsesItems;

import Model.Items.NumberOfUsesItems.SlideRule;
import View.Utils.Coordinates;
import View.Utils.ImageReader;

/**
 * A logarléc kirajzolásáért felelős.
 */
@SuppressWarnings("serial")
public class ViewSlideRule extends ViewNumberOfUsesItem {
	
	/**
	 * Konstruktor egy logarléc nézet létrehozásához.
	 * @param sr a modellbeli logarléc
	 * @param coor a koordináták
	 */
	public ViewSlideRule(SlideRule sr, Coordinates coor) {
		super(sr, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule.png");
	}
	
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
	}
}
