package View.ViewItem;

import Items.AirFreshener;
import Items.CabbageCamembert;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

/**
 * A káposztás camembert kirajzolásáért felelős.
 */
public class ViewCabbageCamembert extends ViewNumberOfUsesItem {
	
	/*
	 * A modellbeli káposztás camembert, amit reprezentál.
	 */
	private CabbageCamembert item;
	
	public ViewCabbageCamembert(CabbageCamembert cc, Coordinates coor) {
		super("testitem.png", coor);
		item = cc;
		item.subscribe(this);
	}
	
	/*
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
