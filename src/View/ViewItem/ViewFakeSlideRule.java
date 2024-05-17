package View.ViewItem;

import Items.AirFreshener;
import Items.FakeSlideRule;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

/**
 * A hamis logarléc kirajzolásáért felelős.
 */
public class ViewFakeSlideRule extends ViewNumberOfUsesItem {
	
	public ViewFakeSlideRule(FakeSlideRule fsr, Coordinates coor) {
		super(fsr, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule.png");
	}
}
