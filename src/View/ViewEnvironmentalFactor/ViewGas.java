package View.ViewEnvironmentalFactor;

import EnvironmentalFactor.Gas;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.Size;

import java.awt.*;

/**
 * A gáz grafikus osztálya.
 */
public class ViewGas extends ViewEnvironmentalFactors {

	/**
	 * A modellbeli gáz, amit reprezentál.
	 */
	private Gas gas;
	
	public ViewGas(Gas g, Coordinates c, int capacity){
		gas=g;
		coordinates=c;
		image = ImageReader.loadImage("res/images/envfactors/gas.png");
		size = new Size(capacity * 90+30,	250);
		GameFrame.container.add(this);
    	GameFrame.viewEnvs.add(this);
    	Controller.envs.put(gas, this);
	}

	/**
	 * Gáz kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.drawImage(image,coordinates.getX()-15,coordinates.getY()-15,size.getWidth(),size.getHeight(),null);
	}
}
