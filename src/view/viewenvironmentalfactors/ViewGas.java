package view.viewenvironmentalfactors;

import model.environmentalfactors.Gas;
import view.utils.Coordinates;
import view.utils.ImageReader;

import java.awt.*;

/**
 * A gáz grafikus osztálya.
 */
@SuppressWarnings("serial")
public class ViewGas extends ViewEnvironmentalFactor {
	
	/**
	 * Konstruktor egy gáz nézet létrehozásához.
	 * @param gas a modellbeli gáz
	 * @param c a koordináták
	 */
	public ViewGas(Gas gas, Coordinates c){
		super(gas,c);
		image = ImageReader.loadImage(ImageReader.path+envFactorsPath+"gas.png");
	}
	
	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
	}
	
	/**
	 * A gáz kirajzolása.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		g2D.drawImage(image,coordinates.getX()-15,coordinates.getY()-15,size.getWidth(),size.getHeight(),null);
	}
}
