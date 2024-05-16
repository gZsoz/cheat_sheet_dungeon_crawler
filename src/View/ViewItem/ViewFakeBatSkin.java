package View.ViewItem;

import Items.FakeBatSkin;
import View.Utils.Coordinates;
import java.awt.*;

/**
 * A hamis denevérbőr kirajzolásáért felelős.
 */
public class ViewFakeBatSkin extends ViewNumberOfUsesItem {
	 
	/**
	 * A modellbeli hamis denevérbőr, amit reprezentál.
	 */
	private FakeBatSkin item;
	
	public ViewFakeBatSkin(FakeBatSkin fbs, Coordinates coor) {
		super("testitem.png", coor);
		item = fbs;
		item.subscribe(this);
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

