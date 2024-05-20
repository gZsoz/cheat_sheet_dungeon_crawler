package View.ViewMap;

import Map.Room;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
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
	    super.propertyChanged(property);
	    if(property.startsWith("removedfromneighbours")) {
	    	for(Room r : room.neighbours)
	    		r.notifySubsribers("cursedremovedfromneighbours "+GameFrame.vl.getLabyrinth().getRooms().indexOf(room));
	    }
	}
	
	/**
	 * Az elátkozott szoba, benne lévő tárgyak, környezeti változók és karakterek kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
	    super.paint(g);
	}
}
