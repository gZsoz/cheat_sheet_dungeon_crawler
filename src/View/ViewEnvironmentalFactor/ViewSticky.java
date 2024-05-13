package View.ViewEnvironmentalFactor;

import EnvironmentalFactor.Sticky;
import View.Utils.Coordinates;

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
	}

	/**
	 * Ragacs kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
	    // TODO document why this method is empty
	}
}
