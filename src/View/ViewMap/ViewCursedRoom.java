package View.ViewMap;

import Map.Room;
import View.Utils.Coordinates;
import View.Utils.Subscriber;

import java.awt.*;

/**
 * Az elátkozott szoba grafikus osztálya.
 */
public class ViewCursedRoom extends ViewRoom implements Subscriber {

	public ViewCursedRoom(Room r, Coordinates pos) {
		super(r, pos);
	}

	@Override
	public void propertyChanged(String property) {
	    super.propertyChanged(property);
	}
	
	/**
	 * Az elátkozott szoba, benne lévő tárgyak, környezeti változók és karakterek kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
	    super.paint(g);
	}
}
