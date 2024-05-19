package View.ViewEnvironmentalFactor;

import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.Size;
import View.Utils.Subscriber;

import javax.swing.*;

import EnvironmentalFactor.EnvironmentalFactors;
import EnvironmentalFactor.Gas;

import java.awt.*;

/**
 * A környezeti változók grafikus osztályának ősosztálya.
 */
public abstract class ViewEnvironmentalFactors extends JComponent implements Subscriber {
	
	/**
	 * A modellbeli környezeti változó, amit reprezentál.
	 */
	protected EnvironmentalFactors environmentalFactor;
	
	protected final String envFactorsPath = "Envfactors/"; 
	
	/**
	 * A környezeti változó képe, ami megjelenik.
	 */
	protected Image image;
	
	/**
	 * A környezeti változó képének mérete.
	 */
	protected Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	protected Coordinates coordinates;
	
	public ViewEnvironmentalFactors(EnvironmentalFactors environmentalFactor, Coordinates c) {
		this.environmentalFactor = environmentalFactor;
		this.environmentalFactor.subscribe(this);
		size = new Size(360+30,	250);
		coordinates=c;
		GameFrame.container.add(this);
    	GameFrame.viewEnvs.add(this);
    	Controller.envs.put(this.environmentalFactor, this);
	}
	
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates=coordinates;
	}
	
	public void propertyChanged(String property) {
		if(property.equals("factorremoved")) {
			Controller.envs.remove(environmentalFactor);
			GameFrame.viewEnvs.remove(this);
			GameFrame.container.remove(this);
			environmentalFactor.unsubscribe(this);
		}
	}
	
	/**
	 * A környezeti változó kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}
