package View.ViewItem;

import Items.AirFreshener;
import Items.FakeMask;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

/**
 * A hamis maszk kirajzolásáért felelős.
 */
public class ViewFakeMask extends ViewMask {
	
	/**
	 * A modellbeli hamis maszk, amit reprezentál.
	 */
	private FakeMask item;
	
	public ViewFakeMask(FakeMask fm, String path,Coordinates coor) {
		super(fm, path, coor);
		item = fm;
		ImageReader ir=new ImageReader();
		image=ir.loadImage(path);
		size= new Size(64,64);
		coordinates = coor;
		selected= SelectionColor.Empty;
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
