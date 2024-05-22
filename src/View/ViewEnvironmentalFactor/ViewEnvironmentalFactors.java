package View.ViewEnvironmentalFactor;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import Model.EnvironmentalFactors.EnvironmentalFactors;
import View.Utils.Containers;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.Size;
import View.Utils.Subscriber;

/**
 * A környezeti változók grafikus osztályainak ősosztálya.
 */
@SuppressWarnings("serial")
public abstract class ViewEnvironmentalFactors extends JComponent implements Subscriber {
	
	/**
	 * A modellbeli környezeti változó, amit reprezentál.
	 */
	protected EnvironmentalFactors environmentalFactor;
	
	/**
	 * A környezeti változók képeinek elérési útja.
	 */
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
	
	/**
	 * Konstruktor egy környezeti változó nézet létrehozásához.
	 * @param environmentalFactor a modellbeli környezeti változó
	 * @param coor a koordináták
	 */
	public ViewEnvironmentalFactors(EnvironmentalFactors environmentalFactor, Coordinates coor) {
		this.environmentalFactor = environmentalFactor;
		this.environmentalFactor.subscribe(this);
		size = new Size(360+30,	250);
		coordinates=coor;
		GameFrame.mainPanel.add(this);
		GameFrame.viewEnvs.add(this);
		Containers.envs.put(this.environmentalFactor, this);
	}
	
	/**
	 * A koordináták beállítása.
	 * @param coordinates a koordináták
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates=coordinates;
	}
	
	/**
	 * A következőkről kap értesítést: a környezeti változót eltávolították a modellből.
	 */
	public void propertyChanged(String property) {
		if(property.equals("factorremoved")) { // küldő: Room
			Containers.envs.remove(environmentalFactor);
			GameFrame.viewEnvs.remove(this);
			GameFrame.mainPanel.remove(this);
			environmentalFactor.unsubscribe(this);
		}
	}
	
	/**
	 * A környezeti változó kirajzolása.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
