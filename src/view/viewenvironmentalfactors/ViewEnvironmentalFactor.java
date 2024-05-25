package view.viewenvironmentalfactors;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import model.environmentalfactors.EnvironmentalFactor;
import view.utils.Containers;
import view.utils.Coordinates;
import view.utils.GameFrame;
import view.utils.Size;
import view.utils.Subscriber;

/**
 * A környezeti változók grafikus osztályainak ősosztálya.
 */
@SuppressWarnings("serial")
public abstract class ViewEnvironmentalFactor extends JComponent implements Subscriber {
	
	/**
	 * A modellbeli környezeti változó, amit reprezentál.
	 */
	protected EnvironmentalFactor environmentalFactor;
	
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
	public ViewEnvironmentalFactor(EnvironmentalFactor environmentalFactor, Coordinates coor) {
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
