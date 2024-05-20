package View.ViewItem;

import Items.FakeBatSkin;
import View.Controller.Containers;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.Size;

import java.awt.*;

/**
 * A hamis denevérbőr kirajzolásáért felelős.
 */
public class ViewFakeBatSkin extends ViewBatSkin {
	
	public ViewFakeBatSkin(FakeBatSkin fbs, Coordinates coor) {
		super(fbs, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"batskin.png");
	}
	
	@Override
	public void setItemImage() {
		if(this.size.equals(ViewItem.inventorySize)) {
			image = ImageReader.loadImage(ImageReader.path+itemsPath+"batskin_fake.png");
		}else if(this.size.equals(ViewItem.roomSize)) {
			image=ImageReader.loadImage(ImageReader.path+itemsPath+"batskin.png");
		}
	}
}

