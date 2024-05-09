package View.ViewEnvironmentalFactor;

import javax.imageio.ImageIO;

import View.Utils.Coordinates;
import View.Utils.Size;
import View.Utils.View;

/**
 * A környezeti változók grafikus osztályának ősosztálya.
 */
public abstract class ViewEnvironmentalFactors implements View {
	
	/**
	 * A környezeti változó képe, ami megjelenik.
	 */
	protected ImageIO image;
	
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
	public void paint() {
	    // TODO document why this method is empty
	}
}
