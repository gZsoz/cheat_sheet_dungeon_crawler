package view.viewitems.viewnumberofusesitems;

import model.items.numberofusesitems.SlideRule;
import view.utils.Coordinates;
import view.utils.ImageReader;

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
