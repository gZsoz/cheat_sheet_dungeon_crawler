package View.ViewEnvironmentalFactor;

import EnvironmentalFactor.Gas;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;

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
		GameFrame.container.add(this);
    	GameFrame.viewEnvs.add(this);
    	Controller.envs.put(gas, this);
	}

	/**
	 * Gáz kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		// TODO document why this method is empty
	}
}
