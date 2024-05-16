package View.ViewItem;

import Items.AirFreshener;
import Items.SlideRule;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

/**
 * A logarléc kirajzolásáért felelős.
 */
public class ViewSlideRule extends ViewNumberOfUsesItem {
	 
	/*
	 * A modellbeli logarléc, amit reprezentál.
	 */
	private SlideRule item;
	
	public ViewSlideRule(SlideRule sr, Coordinates coor) {
		super("sliderule.png", coor);
		item = sr;
		item.subscribe(this);
	}
	
	/*
	 * Kirajzoló függvény, megfelelő koordinátákra helyezi a tárgy képét.
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image,coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);
	}
	
	@Override
	public void propertyChanged(String property) {
		// TODO Auto-generated method stub
		
	}

}
