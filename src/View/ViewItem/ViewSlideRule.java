package View.ViewItem;

import Items.AirFreshener;
import Items.SlideRule;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

/**
 * A logarléc kirajzolásáért felelős.
 */
public class ViewSlideRule extends ViewNumberOfUsesItem {
	 
	/*
	 * A modellbeli logarléc, amit reprezentál.
	 */
	private SlideRule item;
	
	public ViewSlideRule(SlideRule sr, String path,Coordinates coor) {
		item = sr;
		ImageReader ir=new ImageReader();
		image=ir.loadImage(path);
		size= new Size(64,64);
		coordinates = coor;
		selected= SelectionColor.Empty;
		}
	/*
	 * Kirajzoló függvény, megfelelő koordinátákra helyezi a tárgy képét.
	 */

	public ViewSlideRule(SlideRule sr, Coordinates pos){
		item = sr;
		image = ImageReader.loadImage("res/images/test/items/testitem.png");
		coordinates = pos;
		size = new Size(40,40);
	}
	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image,coordinates.getX(),coordinates.getY(),size.getWidth(),size.getHeight(),null);
	}
	
	@Override
	public void propertyChanged(String property) {
		// TODO Auto-generated method stub
		
	}

}
