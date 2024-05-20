package View.ViewItem;

import Items.Mask;
import View.Controller.Containers;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A maszk kirajzolásáért felelős.
 */
public class ViewMask extends ViewDecayingItem {
	
	public ViewMask(Mask m, Coordinates coor) {
		super(m, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"mask.png");
	}
}
