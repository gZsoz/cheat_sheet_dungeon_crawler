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
	
	public ViewBeer(Beer beer, String path,Coordinates coor) {
		item = beer;
		ImageReader ir=new ImageReader();
		image=ir.loadImage(path);
		size= new Size(64,64);
		coordinates = coor;
		selected= SelectionColor.Empty;
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
