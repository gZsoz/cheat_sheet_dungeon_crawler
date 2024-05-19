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
public class ViewFakeSlideRule extends ViewSlideRule {
	
	public ViewFakeSlideRule(FakeSlideRule fsr, Coordinates coor) {
		super(fsr, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule.png");
	}
	
	@Override
	public void setItemSize(Size size) {
		if(size.getHeight()==78&&this.size.getHeight()!=78) {
			image = ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule_fake.png");
		} else if(size.getHeight()!=78&&this.size.getHeight()==78) {
			image=ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule.png");
		}
		super.setItemSize(size);
	}
}
