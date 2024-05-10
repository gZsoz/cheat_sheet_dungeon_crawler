package View.ViewItem;

import Items.AirFreshener;

import java.awt.*;

/**
 * A légfrissítő kirajzolásáért felelős.
 */
public class ViewAirFreshener extends ViewNumberOfUsesItem {
	
	/**
	 * A modellbeli légfrissítő, amit reprezentál.
	 */
	private AirFreshener item;

	public ViewAirFreshener(AirFreshener air) {
		item = air;
	}

	/**
	 * Kirajzoló függvény, megfelelő koordinátákra helyezi a tárgy képét.
	 */



	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void propertyChanged(String property) {
		// TODO Auto-generated method stub
		
	}
}
