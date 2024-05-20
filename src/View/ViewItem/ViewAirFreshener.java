package View.ViewItem;

import Items.AirFreshener;
import View.Controller.Containers;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A légfrissítő kirajzolásáért felelős.
 */
public class ViewAirFreshener extends ViewNumberOfUsesItem {

	public ViewAirFreshener(AirFreshener air, Coordinates coor) {
		super(air, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"airfreshener.png");
	}
}
