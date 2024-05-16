package View.ViewItem;

import Items.AirFreshener;
import Items.WetCloth;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.SelectionColor;
import View.Utils.Size;

import java.awt.*;

/**
 * A nedves törlőrongy kirajzolásáért felelős.
 */
public class ViewWetCloth extends ViewDecayingItem {

    /**
     * A modellbeli nedves törlőrongy, amit reprezentál.
     */
    private WetCloth item;
    
    public ViewWetCloth(WetCloth wc, Coordinates coor) {
		super("wetcloth.png", coor);
		item = wc;
		item.subscribe(this);
	}

    /**
	 * Nedves törlőrongy kirajzolása a megadott koordinátákra.
	 */
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
    	Graphics2D g2D = (Graphics2D) g;
    	g2D.drawImage(image,coordinates.getX(),coordinates.getY(), size.getWidth(),size.getHeight(),this);
    }

    @Override
    public void propertyChanged(String property) {
        // TODO Auto-generated method stub
    }
    
}
