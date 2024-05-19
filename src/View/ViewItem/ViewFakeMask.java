package View.ViewItem;

import Items.FakeMask;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.Size;

import java.awt.*;

/**
 * A hamis maszk kirajzolásáért felelős.
 */
public class ViewFakeMask extends ViewMask {
	
	public ViewFakeMask(FakeMask fm, Coordinates coor) {
		super(fm, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"mask.png");
	}
	
	@Override
	public void setItemImage() {
		if(this.size.getHeight()==78) {
			image = ImageReader.loadImage(ImageReader.path+itemsPath+"mask_fake.png");
		} else if(this.size.getHeight()!=78) {
			image=ImageReader.loadImage(ImageReader.path+itemsPath+"mask.png");
		}
	}
}
