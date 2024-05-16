package View.ViewItem;

import Items.AirFreshener;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

/**
 * A légfrissítő kirajzolásáért felelős.
 */
public class ViewAirFreshener extends ViewNumberOfUsesItem {
	
	/**
	 * A modellbeli légfrissítő, amit reprezentál.
	 */
	private AirFreshener item;

	public ViewAirFreshener(AirFreshener air, Coordinates coor) {
		super("airfreshener.png", coor);
		item = air;
		item.subscribe(this);
	}

	/**
	 * Kirajzoló függvény, megfelelő koordinátákra helyezi a tárgy képét.
	 */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2D = (Graphics2D) g;
    	g2D.drawImage(image,coordinates.getX(),coordinates.getY(), size.getWidth(),size.getHeight(),this);	}
	
	@Override
	public void propertyChanged(String property) {
		// TODO Auto-generated method stub
		
	}
}
