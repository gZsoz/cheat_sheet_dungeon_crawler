package View.ViewItem;

import Items.WetCloth;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;

import java.awt.*;

/**
 * A nedves törlőrongy kirajzolásáért felelős.
 */
public class ViewWetCloth extends ViewDecayingItem {
    
    public ViewWetCloth(WetCloth wc, Coordinates coor) {
		super(wc, coor);
		image=ImageReader.loadImage(ImageReader.path+itemsPath+"wetcloth.png");
	} 
}
