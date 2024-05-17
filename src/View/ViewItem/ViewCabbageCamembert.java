package View.ViewItem;

import Items.CabbageCamembert;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A káposztás camembert kirajzolásáért felelős.
 */
public class ViewCabbageCamembert extends ViewNumberOfUsesItem {
	
	public ViewCabbageCamembert(CabbageCamembert cc, Coordinates coor) {
		super(cc, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"camambert.png");
	}
}
