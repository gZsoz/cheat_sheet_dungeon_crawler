package View.ViewItem;

import Items.Beer;
import View.Controller.Containers;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A sör kirajzolásáért felelős.
 */
public class ViewBeer extends ViewDecayingItem {
	
	public ViewBeer(Beer beer, Coordinates coor) {
		super(beer, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"beer.png");
	}
}
