package View.ViewItem;

import Items.SlideRule;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A logarléc kirajzolásáért felelős.
 */
public class ViewSlideRule extends ViewNumberOfUsesItem {
	
	public ViewSlideRule(SlideRule sr, Coordinates coor) {
		super(sr, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule.png");
	}
	
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
		// TODO Auto-generated method stub
	}

}
