package View.ViewMap;

import Model.Map.Room;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.Subscriber;

/**
 * Az elátkozott szoba grafikus osztálya.
 */
@SuppressWarnings("serial")
public class ViewCursedRoom extends ViewRoom implements Subscriber {
	
	/**
	 * Konstruktor egy elátkozott szoba nézet létrehozásához.
	 * @param r a modellbeli szoba
	 * @param coor a koordináták
	 */
	public ViewCursedRoom(Room r, Coordinates coor) {
		super(r, coor);
		image = ImageReader.loadImage(ImageReader.path + roomPath + "seethrough_cursedroom.png");
	}
	
	/**
	 * A következőkről kap értesítést: a modellbeli elátkozott szoba nem látszik a többi szobából, mivel eltűntek az ajtói
	 */
	@Override
	public void propertyChanged(String property) {
	    super.propertyChanged(property);
	    if(property.startsWith("removedfromneighbours")) { // küldő: CursedRoom 
	    	for(Room r : room.neighbours)
	    		r.notifySubscribers("cursedremovedfromneighbours "+GameFrame.vl.getLabyrinth().getRooms().indexOf(room));
	    }
	}
}
