package View.ViewMap;

import Map.Room;
import View.Utils.Coordinates;
import View.Utils.ImageReader;
import View.Utils.Subscriber;

import java.awt.*;

/**
 * Az elátkozott szoba grafikus osztálya.
 */
public class ViewCursedRoom extends ViewRoom implements Subscriber {

	public ViewCursedRoom(Room r, Coordinates pos) {
		super(r, pos);
		image = ImageReader.loadImage("res/images/room/cursedroom.png");
	}

	@Override
	public void propertyChanged(String property) {
	    // TODO document why this method is empty
	}
	
	/**
	 * Az elátkozott szoba, benne lévő tárgyak, környezeti változók és karakterek kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
	    // TODO document why this method is empty
	}
}
