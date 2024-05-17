package View.ViewItem;

import Items.Transistor;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A tranzisztor kirajzolásáért felelős.
 */
public class ViewTransistor extends ViewItem {
	
	public ViewTransistor(Transistor tr, Coordinates coor) {
		super(tr, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"transistor_on.png");
	}
	
	//@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		// TODO Auto-generated method stub
	}

}
