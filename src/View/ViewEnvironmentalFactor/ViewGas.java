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
	
	public ViewGas(Gas gas, Coordinates c){
		super(gas,c);
		image = ImageReader.loadImage(ImageReader.path+envFactorsPath+"gas.png");
	}

	@Override
	public void propertyChanged(String property) {
		super.propertyChanged(property);
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
