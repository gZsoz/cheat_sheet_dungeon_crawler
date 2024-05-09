package View.ViewMap;

import Map.Labyrinth;
import View.Utils.Coordinates;
import View.Utils.Size;
import View.Utils.Subscriber;
import View.Utils.View;

import java.awt.*;
import java.util.ArrayList;

/**
 * A labirintus grafikus osztálya.
 */
public class ViewLabyrinth implements View, Subscriber {
	
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
	private ArrayList<ViewRoom> roomsInLabyrinth;
	
	/**
	 * A szomszédos szobák közti összeköttetés koordinátái.
	 */
	private ArrayList<ArrayList<Coordinates>> routesBetweenRooms;
	
	@Override
	public void propertyChanged(String property) {
	    // TODO document why this method is empty
	}
	
	/**
	 * A labirintus háttere és a szobák közti utak kirajzolása.
	 */
	@Override
	public void paint() {
	    // TODO document why this method is empty
	}
}
