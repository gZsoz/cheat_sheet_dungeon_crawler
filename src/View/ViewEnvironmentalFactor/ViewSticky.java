package View.ViewEnvironmentalFactor;

import EnvironmentalFactor.Sticky;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.Size;

import java.awt.*;

/**
 * A ragacs grafikus osztálya.
 */
public class ViewSticky extends ViewEnvironmentalFactors {
	
	public ViewSticky(Sticky sticky, Coordinates c){
		super(sticky, c);
		image = ImageReader.loadImage(ImageReader.path+envFactorsPath+"sticky.png");
	}
	
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
	}

	/**
	 * Ragacs kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		if(((Sticky)environmentalFactor).getRemainingEntries() <= 0) {
			Graphics2D g2D = (Graphics2D)g;
			g2D.drawImage(image,coordinates.getX()-15,coordinates.getY()-15,size.getWidth(),size.getHeight(),null);
		}
	}
}
