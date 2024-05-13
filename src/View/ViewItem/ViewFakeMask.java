package View.ViewItem;

import Items.FakeMask;
import View.Utils.Coordinates;

import java.awt.*;

/**
 * A hamis maszk kirajzolásáért felelős.
 */
public class ViewFakeMask extends ViewMask {
	
	/**
	 * A modellbeli hamis maszk, amit reprezentál.
	 */
	//private FakeMask item;
	
	public ViewFakeMask(FakeMask fm, Coordinates coor) {
		super(fm, coor);
	}
	/**
	 * Hamis maszk kirajzolása a megadott koordinátákra.
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
