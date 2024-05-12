package View.ViewItem;

import Items.AirFreshener;
import Items.Transistor;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

/**
 * A tranzisztor kirajzolásáért felelős.
 */
public class ViewTransistor extends ViewItem {
	
	/**
	 * A modellbeli tranzisztor, amit reprezentál.
	 */
	private Transistor item;
	public ViewTransistor(Transistor tr, String path,Coordinates coor) {
		item = tr;
		ImageReader ir=new ImageReader();
		image=ir.loadImage(path);
		size= new Size(64,64);
		coordinates = coor;
		selected= SelectionColor.Empty;
		}
	
	@Override
	public void propertyChanged(String property) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2D = (Graphics2D) g;
    	g2D.drawImage(image,coordinates.getX(),coordinates.getY(), size.getWidth(),size.getHeight(),this);
	}

}
