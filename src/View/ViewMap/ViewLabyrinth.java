package View.ViewMap;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JComponent;

import Main.Main;
import Map.CursedRoom;
import Map.Labyrinth;
import Map.Room;
import View.Utils.Containers;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.Subscriber;

/**
 * A labirintus grafikus osztálya.
 */
@SuppressWarnings("serial")
public class ViewLabyrinth extends JComponent implements Subscriber {
	
	/**
	 * A modellbeli, reprezentálandó labirintus.
	 */
	private Labyrinth labyrinth;
	
	/**
	 * A labirintus szobáihoz tartozó pin-ek képe.
	 */
	private Image pinImage;
	
	/**
	 * A fixedRoomPositions tömbbel azonos indexü tagja 1, ha van ott szoba. 0, ha nincs.
	 */
	private int roomsInPosition [] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	/**
	 * Az összes lehetséges szoba pozíciója
	 */
	private ArrayList<Coordinates> fixedRoomPositions = new ArrayList<>(Arrays.asList(
				new Coordinates(71,50), new Coordinates(542,30), new Coordinates(964,30), new Coordinates(1410,50),
				new Coordinates(209,300), new Coordinates(772,300), new Coordinates(1272,300),
				new Coordinates(322,620), new Coordinates(722,556), new Coordinates(1132,620)
	));
	
	/**
	 * Ennyi diákot rúgott ki a tanár.
	 */
	private int kickedStudents = 0;
	
	/**
	 * Nyertek a diákok.
	 */
	private boolean gameWon = false;
	
	/**
	 * Vesztettek a diákok.
	 */
	private boolean gameLost = false;
	
	/**
	 * Konstruktor egy labirintus nézrt létrehozásához.
	 * @param lab a modellbeli labirintus
	 */
	public ViewLabyrinth(Labyrinth lab){
		labyrinth = lab;
		this.setBackground(null);
		pinImage = ImageReader.loadImage("res/images/Rooms/pin.png");
		lab.subscribe(this);
	}
	
	/**
	 * Kezdő map generálás view szinten
	 */
	public void initLab() {
		if(Main.random.nextInt(1, -1)==0)
			Collections.shuffle(fixedRoomPositions);
		for(int i = 0; i < labyrinth.getRooms().size(); i++){
			roomsInPosition[i]=1;
			Room room = labyrinth.getRooms().get(i);
			ViewRoom r=null;
			if(room instanceof CursedRoom){
				r=new ViewCursedRoom( (CursedRoom) room, fixedRoomPositions.get(i));
			}
			else if(room instanceof Room){
				r=new ViewRoom( (Room) room, fixedRoomPositions.get(i));
			}
			r.addview();
		}
	}
	
	/**
	 * A modellbeli labirintus lekérdezése.
	 * @return a modellbeli labirintus
	 */
	public Labyrinth getLabyrinth() {
		return labyrinth;
	}
	
	/**
	 * A következőkről kap értesítést:
	 * szobaösszeolvasztás történt,
	 * szobaszétválasztás történt,
	 * a diákok megnyerték a játékot,
	 * a diákok elvesztették a játékot
	 */
	@Override
	public void propertyChanged(String property) {
		if(property.contains("merge")) { // küldő: Labyrinth
			int idx=Integer.parseInt(property.split(" ")[2]);
			ViewRoom vr = Containers.rooms.get(labyrinth.getRooms().get(idx));
			for(int i=0;i<fixedRoomPositions.size();i++) {
				if(fixedRoomPositions.get(i).equals(vr.coordinates)) {
					roomsInPosition[i]=0;
					break;
				}
			}
		}else if(property.contains("split")) { // küldő: Labyrinth
			int idx=Integer.parseInt(property.split(" ")[1])+1;
			int posidx=-1;
			for(int i=0;i<roomsInPosition.length;i++) {
				if(roomsInPosition[i]==0) {
					posidx=i;
					break;
				}
			}
			roomsInPosition[posidx]=1;
			Room room = labyrinth.getRooms().get(idx);
			if(room instanceof CursedRoom){
				new ViewCursedRoom( (CursedRoom) room, fixedRoomPositions.get(posidx));
			}
			else if(room instanceof Room){
				new ViewRoom( (Room) room, fixedRoomPositions.get(posidx));
			}
		}else if(property.equals("gamewon")){ // küldő: PlayerController
			gameWon = true;
		}else if(property.equals("gamelost")) { // küldő: PlayerController
			kickedStudents += 1;
			if(kickedStudents >= 2)
				gameLost = true;
		}
	}
	
	/**
	 * A szobák szomszédosságát jelző vonalak kirajzolása.
	 * @param g2D a Graphics2D, amire rajzolunk
	 */
	public void paintRoutes(Graphics2D g2D) {
		// legközelebbi pin kiszámolása
		for(ViewRoom vroom : GameFrame.viewRooms){
			for(Room neighbour : vroom.getRoom().getNeighbours()){
				double shortestDistance = Double.MAX_VALUE;
				int pinIdx1 = -1;
				int pinIdx2 = -1;
				for(int i = 0; i<4; i++){
					for(int j = 0; j<4; j++){
						double currDistance = Coordinates.distanceBetweenCoords(vroom.getFixedRoutePins()[i],Containers.rooms.get(neighbour).getFixedRoutePins()[j]);
						if(currDistance < shortestDistance){
							shortestDistance = currDistance;
							pinIdx1 = i;
							pinIdx2 = j;
						}
					}
				}
				g2D.setColor(Color.RED);
				g2D.setStroke(new BasicStroke(6));
				g2D.drawLine(vroom.getFixedRoutePins()[pinIdx1].getX() + 20, vroom.getFixedRoutePins()[pinIdx1].getY() + 25,
						Containers.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getX() + 20,Containers.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getY() + 25
						);
				g2D.drawImage(pinImage,vroom.getFixedRoutePins()[pinIdx1].getX(),vroom.getFixedRoutePins()[pinIdx1].getY(),40,40,null);
				g2D.drawImage(pinImage,Containers.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getX(),Containers.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getY(),40,40,null);
			}
		}
	}
	
	/**
	 * A labirintus útjainak kirajzolása, játék vége esetén a végső kép kirajzolása és a timer megállítása.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		paintRoutes(g2D);
	
		if(gameWon || gameLost){
			if(gameWon){
				g2D.drawImage(ImageReader.loadImage(ImageReader.path+"wonscreen.png"),0,0,1820,980,null);
			} else {
				g2D.drawImage(ImageReader.loadImage(ImageReader.path+"lostscreen.png"),0,0,1820,980,null);
			}
			Main.timer.stop();
		}
	}
}
