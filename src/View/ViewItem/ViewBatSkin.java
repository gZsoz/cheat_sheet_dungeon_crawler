package View.ViewItem;

import Items.BatSkin;
import View.Controller.Controller;
import View.Utils.Coordinates;
import java.awt.*;

/**
 * A denevérbőr kirajzolásáért felelős.
 */
public class ViewBatSkin extends ViewNumberOfUsesItem {
	
	/**
	 * A modellbeli denevérbőr, amit reprezentál.
	 */
	private BatSkin item;
	
	public ViewBatSkin(BatSkin bat, Coordinates coor) {
		super("batskin.png", coor);
		item = bat;
		item.subscribe(this);
		Controller.items.put(item, this);
	}
	
	/**
	 * Kirajzoló függvény, megfelelő koordinátákra helyezi a tárgy képét.
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

