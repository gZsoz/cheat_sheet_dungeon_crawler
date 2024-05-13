package View.ViewItem;

import Items.AirFreshener;
import Items.Beer;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

/**
 * A sör kirajzolásáért felelős.
 */
public class ViewBeer extends ViewDecayingItem {
	
	/**
	 * A modellbeli sör, amit reprezentál.
	 */
	private Beer item;
	
	public ViewBeer(Beer beer, Coordinates coor) {
		super("testitem.png", coor);
		item = beer;
		item.subscribe(this);
	}
	/**
	 * Sör kirajzolása a megadott koordinátákra.
	 */
	@Override
	public void paint(Graphics g) {
	    // TODO Auto-generated method stub
		Graphics2D g2D = (Graphics2D) g;
    	g2D.drawImage(image,coordinates.getX(),coordinates.getY(), size.getWidth(),size.getHeight(),this);
	}
	
	@Override
	public void propertyChanged(String property) {
	    // TODO Auto-generated method stub
	}

}
