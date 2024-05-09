package View.ViewEnvironmentalFactor;

import View.Utils.Coordinates;
import View.Utils.Size;
import View.Utils.View;

import java.awt.*;

/**
 * A környezeti változók grafikus osztályának ősosztálya.
 */
public abstract class ViewEnvironmentalFactors implements View {
	
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
	 * A környezeti változó kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
	    // TODO document why this method is empty
	}
}
