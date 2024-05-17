package View.ViewMap;

import Map.CursedRoom;
import Map.Labyrinth;
import Map.Room;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
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

	public ArrayList<ViewRoom> getRoomsInLabyrinth() {
		return roomsInLabyrinth;
	}

	/**
	 * A labirintusban megjelenítendő szobák.
	 */
	public ArrayList<ViewRoom> roomsInLabyrinth = new ArrayList<>();

	/**
	 * Az összes lehetséges szoba pozíciója
	 */
	private Coordinates[] fixedRoomPositions = {new Coordinates(71,50), new Coordinates(542,30), new Coordinates(964,30), new Coordinates(1410,50),
														new Coordinates(252,300), new Coordinates(752,300), new Coordinates(1252,300),
													new Coordinates(322,560), new Coordinates(742,556), new Coordinates(1132,560)
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
			GameFrame.container.add(r);
		}
	}
	
	/**
	 * A labirintus háttere és a szobák közti utak kirajzolása.
	 */
	/*
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	    //for(ViewRoom vroom : roomsInLabyrinth){
		//	vroom.paint(g);
		//}
	}*/


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    //for(ViewRoom vroom : roomsInLabyrinth){
		//	vroom.paint(g);
		//}

		Graphics2D g2D = (Graphics2D)g;
		// Mode textbox
		g2D.setColor(Color.white.darker());
		g2D.drawImage(ImageReader.loadImage("res/images/test/testroom.png"),1820/2 - 240, 800, 480, 200,null);
		g2D.fillRect(1820/2 - 200,
				815,
				400,112);
		g2D.setColor(Color.black);
		g2D.drawString("Valami",1820/2 - 200,815);
	}
}
