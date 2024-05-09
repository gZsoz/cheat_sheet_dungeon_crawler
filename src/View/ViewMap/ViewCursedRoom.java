package View.ViewMap;

import View.Utils.Subscriber;
import View.Utils.View;

/**
 * Az elátkozott szoba grafikus osztálya.
 */
public class ViewCursedRoom extends ViewRoom implements View, Subscriber {
	
	@Override
	public void propertyChanged(String property) {
	    // TODO document why this method is empty
	}
	
	/**
	 * Az elátkozott szoba, benne lévő tárgyak, környezeti változók és karakterek kirajzolása.
	 */
	@Override
	public void paint() {
	    // TODO document why this method is empty
	}
}
