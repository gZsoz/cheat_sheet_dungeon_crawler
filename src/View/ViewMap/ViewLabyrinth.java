package View.ViewMap;

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
public class ViewLabyrinth extends JComponent implements Subscriber {
	
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
	private ArrayList<ViewRoom> roomsInLabyrinth = new ArrayList<>();

	/**
	 * Az összes lehetséges szoba pozíciója
	 */
	private Coordinates[] fixedRoomPositions = {new Coordinates(30,20), new Coordinates(220,10), new Coordinates(420,10), new Coordinates(840,20),
														new Coordinates(200,250), new Coordinates(400,220), new Coordinates(700,250),
													new Coordinates(200,250), new Coordinates(400,220), new Coordinates(700,250)
	};

	/**
	 * A szomszédos szobák közti összeköttetés koordinátái.
	 */
	private ArrayList<ArrayList<Coordinates>> routesBetweenRooms;

	public ViewLabyrinth(Labyrinth lab){
		labyrinth = lab;
		initLab();
		// lab.subscribe(this);
	}

	/**
	 * Kezdő map generálás view szinten
	 */
	private void initLab() {
		for(int i = 0; i < labyrinth.getRooms().size(); i++){
			roomsInLabyrinth.add(new ViewRoom(labyrinth.getRooms().get(i), fixedRoomPositions[i]));
		}
	}

	@Override
	public void propertyChanged(String property) {
	    // TODO document why this method is empty
	}
	
	/**
	 * A labirintus háttere és a szobák közti utak kirajzolása.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	    for(ViewRoom vroom : roomsInLabyrinth){
			vroom.paint(g);
		}
	}
}
