package View.ViewEnvironmentalFactor;

import View.Utils.Coordinates;
import View.Utils.Size;

import javax.swing.*;
import java.awt.*;

/**
 * A környezeti változók grafikus osztályának ősosztálya.
 */
public abstract class ViewEnvironmentalFactors extends JComponent {
	
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
		super.paint(g);
	}
}
