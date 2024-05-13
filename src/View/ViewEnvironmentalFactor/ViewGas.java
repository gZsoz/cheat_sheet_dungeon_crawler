package View.ViewEnvironmentalFactor;

import EnvironmentalFactor.Gas;
import View.Utils.Coordinates;

import java.awt.*;

/**
 * A gáz grafikus osztálya.
 */
public class ViewGas extends ViewEnvironmentalFactors {

	/**
	 * A modellbeli gáz, amit reprezentál.
	 */
	private Gas gas;
	
	public ViewGas(Gas g, Coordinates c){
		gas=g;
		coordinates=c;
	}

	/**
	 * Gáz kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		// TODO document why this method is empty
	}
}
