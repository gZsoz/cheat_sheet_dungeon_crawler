package View.ViewItem;

import Items.Transistor;
import View.Controller.Controller;
import View.Utils.Coordinates;

import java.awt.*;

/**
 * A tranzisztor kirajzolásáért felelős.
 */
public class ViewTransistor extends ViewItem {
	
	/**
	 * A modellbeli tranzisztor, amit reprezentál.
	 */
	private Transistor item;
	
	public ViewTransistor(Transistor tr, Coordinates coor) {
		super("transistor_on.png", coor);
		System.out.println("Hellolololol");
		item = tr;
		item.subscribe(this);
		Controller.items.put(item, this);
	}
	
	@Override
	public void propertyChanged(String property) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2D = (Graphics2D) g;
		System.out.println("paint");
    	g2D.drawImage(image,coordinates.getX(),coordinates.getY(), size.getWidth(),size.getHeight(),this);
	}

}
