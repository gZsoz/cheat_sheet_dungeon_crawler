package View.ViewMap;

import Map.CursedRoom;
import Map.Labyrinth;
import Map.Room;
import View.Controller.Controller;
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

	private Image pinImage;
	
	/**
	 * A labirintus háttérképének mérete.
	 */
	private Size size;
	
	/**
	 * A képernyőn megjelenítendő x és y koordináták.
	 */
	private Coordinates coordinates;

	/**
	 * Az összes lehetséges szoba pozíciója
	 */
	private Coordinates[] fixedRoomPositions = {new Coordinates(71,50), new Coordinates(542,30), new Coordinates(964,30), new Coordinates(1410,50),
														new Coordinates(209,300), new Coordinates(772,300), new Coordinates(1272,300),
													new Coordinates(322,620), new Coordinates(722,556), new Coordinates(1132,620)
	};

	public void paintRoutes(Graphics2D g2D) {

		// legközelebbi pin kiszámolása
		for(ViewRoom vroom : GameFrame.viewRooms){
			for(Room neighbour : vroom.getRoom().getNeighbours()){
				double shortestDistance = Double.MAX_VALUE;
				int pinIdx1 = -1;
				int pinIdx2 = -1;
				for(int i = 0; i<4; i++){
					for(int j = 0; j<4; j++){
						double currDistance = Coordinates.distanceBetweenCoords(vroom.getFixedRoutePins()[i],Controller.rooms.get(neighbour).getFixedRoutePins()[j]);
						if(currDistance < shortestDistance){
							shortestDistance = currDistance;
							pinIdx1 = i;
							pinIdx2 = j;
						}
					}
				}
				g2D.setColor(Color.RED);
				g2D.setStroke(new BasicStroke(8));
				g2D.drawLine(vroom.getFixedRoutePins()[pinIdx1].getX() + 20, vroom.getFixedRoutePins()[pinIdx1].getY() + 25,
						Controller.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getX() + 20,Controller.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getY() + 25
						);
				g2D.drawImage(pinImage,vroom.getFixedRoutePins()[pinIdx1].getX(),vroom.getFixedRoutePins()[pinIdx1].getY(),40,40,null);
				g2D.drawImage(pinImage,Controller.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getX(),Controller.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getY(),40,40,null);
			}
		}

		this.routesBetweenRooms = routesBetweenRooms;
	}

	/**
	 * A szomszédos szobák közti összeköttetés koordinátái.
	 */
	private ArrayList<ArrayList<Coordinates>> routesBetweenRooms;

	public ViewLabyrinth(Labyrinth lab){
		labyrinth = lab;
		this.setBackground(null);
		pinImage = ImageReader.loadImage("res/images/room/pin.png");
		// lab.subscribe(this);
	}

	@Override
	public void propertyChanged(String property) {
	    // TODO document why this method is empty
	}
	
	/**
	 * Kezdő map generálás view szinten
	 */
	public void initLab() {
		for(int i = 0; i < labyrinth.getRooms().size(); i++){
			Room room = labyrinth.getRooms().get(i);
			ViewRoom r=null;
			if(room instanceof CursedRoom){
				r=new ViewCursedRoom( (CursedRoom) room, fixedRoomPositions[i]);
			}
			else if(room instanceof Room){
				r=new ViewRoom( (Room) room, fixedRoomPositions[i]);
			}
			r.addview();
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
		g2D.drawImage(ImageReader.loadImage("res/images/test/testroom.png"),1820/2 - 240 + 40, 800, 400, 200,null);
		g2D.fillRect(1820/2 - 200 + 40,
				815,
				400 - 80,112);
		g2D.setColor(Color.black);
		g2D.drawString("Valami",1820/2 - 200,815);


		for(ViewRoom vroom : GameFrame.viewRooms){

		}
		paintRoutes(g2D);
	}
}
