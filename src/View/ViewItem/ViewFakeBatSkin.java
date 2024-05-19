package View.ViewItem;

import Items.FakeBatSkin;
import View.Controller.Controller;
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
	public void setItemSize(Size size) {
		if(size.getHeight()==78&&this.size.getHeight()!=78) {
			image = ImageReader.loadImage(ImageReader.path+itemsPath+"batskin_fake.png");
			System.out.println("okokokokokokok");
		} else if(size.getHeight()!=78&&this.size.getHeight()==78) {
			image=ImageReader.loadImage(ImageReader.path+itemsPath+"batskin.png");
		}
		super.setItemSize(size);
	}
}

