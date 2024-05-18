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
	
	public ViewSticky(Sticky s, Coordinates c){
		sticky=s;
		coordinates=c;
		image = ImageReader.loadImage("res/images/envfactors/sticky.png");
		size = new Size(/*capacity * */90+30,250);
		GameFrame.container.add(this);
    	GameFrame.viewEnvs.add(this);
    	Controller.envs.put(sticky, this);
	}

	/**
	 * Ragacs kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
	    // TODO document why this method is empty
	}
}
