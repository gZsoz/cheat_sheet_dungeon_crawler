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
	
	/**
	 * A modellbeli ragacs, amit reprezentál.
	 */
	private Sticky sticky;
	
	public ViewSticky(Sticky s, Coordinates c, int capacity){
		sticky=s;
		coordinates=c;
		sticky.subscribe(this);
		image = ImageReader.loadImage("res/images/envfactors/sticky.png");
		size = new Size(/*capacity * 90*/ 360+30,250);
		GameFrame.container.add(this);
    	GameFrame.viewEnvs.add(this);
    	Controller.envs.put(sticky, this);
	}
	
	@Override
	public void propertyChanged(String property) {
		if(property.equals("factorremoved")) {
			Controller.envs.remove(sticky);
			GameFrame.viewEnvs.remove(this);
			GameFrame.container.remove(this);
			sticky.unsubscribe(this);
		}
	}

	/**
	 * Ragacs kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		if(sticky.getRemainingEntries() <= 0) {
			Graphics2D g2D = (Graphics2D)g;
			g2D.drawImage(image,coordinates.getX()-15,coordinates.getY()-15,size.getWidth(),size.getHeight(),null);
		}
	}
}
