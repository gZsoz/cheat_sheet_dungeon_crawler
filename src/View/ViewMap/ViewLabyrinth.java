package View.ViewMap;

import Map.CursedRoom;
import Map.Labyrinth;
import Map.Room;
import View.Utils.Coordinates;
import View.Utils.Size;
import View.Utils.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A labirintus grafikus osztálya.
 */
public class ViewLabyrinth extends JPanel implements Subscriber {
	
	/**
	 * A modellbeli, reprezentálandó labirintus.
	 */
	private Labyrinth labyrinth;
	
	/**
	 * A labirintus hátterének megjelenítendő képe.
	 */
	private Image background;
	
	/**
	 * A labirintus háttérképének mérete.
	 */
	private Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	private Coordinates coordinates;
	
	/**
	 * A labirintusban megjelenítendő szobák.
	 */
	public ArrayList<ViewRoom> roomsInLabyrinth = new ArrayList<>();

	/**
	 * Az összes lehetséges szoba pozíciója
	 */
	private Coordinates[] fixedRoomPositions = {new Coordinates(40,70), new Coordinates(486,50), new Coordinates(932,50), new Coordinates(1378,70),
														new Coordinates(220,340), new Coordinates(720,300), new Coordinates(1220,340),
													new Coordinates(300,620), new Coordinates(720,556), new Coordinates(1140,620)
	};

	/**
	 * A szomszédos szobák közti összeköttetés koordinátái.
	 */
	private ArrayList<ArrayList<Coordinates>> routesBetweenRooms;

	public ViewLabyrinth(Labyrinth lab){
		labyrinth = lab;
		initLab();
		this.setBackground(null);
		// lab.subscribe(this);
	}

	/**
	 * Kezdő map generálás view szinten
	 */
	private void initLab() {
		roomsInLabyrinth.clear();
		for(int i = 0; i < labyrinth.getRooms().size(); i++){
			Room room = labyrinth.getRooms().get(i);
			if(room instanceof CursedRoom){
				roomsInLabyrinth.add(new ViewCursedRoom( (CursedRoom) room, fixedRoomPositions[i]));
			}
			else if(room instanceof Room){
				roomsInLabyrinth.add(new ViewRoom( (Room) room, fixedRoomPositions[i]));
			}
		}
	}

	@Override
	public void propertyChanged(String property) {
	    // TODO document why this method is empty
	}
	
	public void addview() {
		for(ViewRoom r:roomsInLabyrinth) {
			r.addview();
			add(r);
			
		}
	}
	
	/**
	 * A labirintus háttere és a szobák közti utak kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	    //for(ViewRoom vroom : roomsInLabyrinth){
		//	vroom.paint(g);
		//}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    for(ViewRoom vroom : roomsInLabyrinth){
			vroom.paint(g);
		}
	}
}
