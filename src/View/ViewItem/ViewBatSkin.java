package View.ViewItem;

import Items.BatSkin;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A denevérbőr kirajzolásáért felelős.
 */
public class ViewBatSkin extends ViewNumberOfUsesItem {
	
	public ViewBatSkin(BatSkin bat, Coordinates coor) {
		super(bat, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"batskin.png");
	}
}

