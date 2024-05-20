package View.ViewItem;

import Items.AirFreshener;
import Items.FakeSlideRule;
import View.Controller.Containers;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

import javax.swing.text.View;

/**
 * A hamis logarléc kirajzolásáért felelős.
 */
public class ViewFakeSlideRule extends ViewSlideRule {
	
	public ViewFakeSlideRule(FakeSlideRule fsr, Coordinates coor) {
		super(fsr, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule.png");
	}
	
	@Override
	public void setItemImage() {
		if(this.size.equals(ViewItem.inventorySize)) {
			image = ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule_fake.png");
		}else if(this.size.equals(ViewItem.roomSize)) {
			image=ImageReader.loadImage(ImageReader.path+itemsPath+"sliderule.png");
		}
	}
}
