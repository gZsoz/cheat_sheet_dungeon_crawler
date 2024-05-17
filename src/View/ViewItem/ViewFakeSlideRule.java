package View.ViewItem;

import Items.AirFreshener;
import Items.FakeSlideRule;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

/**
 * A hamis logarléc kirajzolásáért felelős.
 */
public class ViewFakeSlideRule extends ViewNumberOfUsesItem {
	
	/**
	 * A modellbeli hamis logarléc, amit reprezentál.
	 */
	private FakeSlideRule item;
	
	public ViewFakeSlideRule(FakeSlideRule fsr, Coordinates coor) {
		super("sliderule.png", coor);
		item = fsr;
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
