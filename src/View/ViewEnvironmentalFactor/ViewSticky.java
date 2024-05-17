package View.ViewEnvironmentalFactor;

import EnvironmentalFactor.Sticky;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;

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
