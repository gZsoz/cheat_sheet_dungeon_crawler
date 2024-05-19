package View.ViewMap;

import Map.CursedRoom;
import Map.Labyrinth;
import Map.Room;
import ProtoUtil.MyRandom;
import ProtoUtil.ProtoUtil;
import View.Controller.Controller;
import View.Utils.Coordinates;
import View.Utils.GameFrame;
import View.Utils.ImageReader;
import View.Utils.Size;
import View.Utils.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
	 * A fixedRoomPositions tömbbel azonos indexü tagja 1, ha van ott szoba. 0, ha nincs.
	 */
	private int roomsInPosition [] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	
	private int kickedStudents = 0;
	
	/**
	 * Az összes lehetséges szoba pozíciója
	 */
	private ArrayList<Coordinates> fixedRoomPositions = new ArrayList<>(Arrays.asList(
				new Coordinates(71,50), new Coordinates(542,30), new Coordinates(964,30), new Coordinates(1410,50),
				new Coordinates(209,300), new Coordinates(772,300), new Coordinates(1272,300),
				new Coordinates(322,620), new Coordinates(722,556), new Coordinates(1132,620)
	));
	
	private boolean gameWon = false;
	private boolean gameLost = false;

	public ViewLabyrinth(Labyrinth lab){
		labyrinth = lab;
		this.setBackground(null);
		pinImage = ImageReader.loadImage("res/images/room/pin.png");
		lab.subscribe(this);
	}
	
	/**
	 * Kezdő map generálás view szinten
	 */
	public void initLab() {
		if(ProtoUtil.random.nextInt(1, -1)==0)
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
	public void propertyChanged(String property) {
		if(property.contains("merge")) {
			int idx=Integer.parseInt(property.split(" ")[2]);
			roomsInPosition[idx]=0;
		}else if(property.contains("split")) {
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
		}else if(property.equals("gamewon")){
			gameWon = true;
		}else if(property.equals("gamelost")) {
			kickedStudents += 1;
			if(kickedStudents >= 2)
				gameLost = true;
		}
	}

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
				g2D.setStroke(new BasicStroke(5));
				g2D.drawLine(vroom.getFixedRoutePins()[pinIdx1].getX() + 20, vroom.getFixedRoutePins()[pinIdx1].getY() + 25,
						Controller.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getX() + 20,Controller.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getY() + 25
						);
				g2D.drawImage(pinImage,vroom.getFixedRoutePins()[pinIdx1].getX(),vroom.getFixedRoutePins()[pinIdx1].getY(),40,40,null);
				g2D.drawImage(pinImage,Controller.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getX(),Controller.rooms.get(neighbour).getFixedRoutePins()[pinIdx2].getY(),40,40,null);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		// Mode textbox
		g2D.setColor(new Color(221,221,221));
		g2D.drawImage(ImageReader.loadImage("res/images/test/testroom.png"),1820/2 - 240 + 40, 800, 400, 200,null);
		g2D.fillRect(1820/2 - 220 + 40,
				815-4,
				400 - 40,120);
		
		// key bindings
		g2D.setFont(new Font("Monospaced", Font.BOLD, 18));

		g2D.setColor(Color.black);
		g2D.drawString("Kijelölő mozgatása",1820/2-95,826);
		g2D.drawString("Kiválasztás",1820/2-56,846);
		g2D.drawString("Tárgy eldobása",1820/2-74,866);
		g2D.drawString("Szoba váltó mód",1820/2-80,886);
		g2D.drawString("Inventory mód",1820/2-70,906);
		g2D.drawString("Szobatárgy jelölő mód",1820/2-118,926);

		g2D.setColor(Color.RED);
		g2D.drawString("A/D",1820/2-174,826);
		g2D.drawString("Space",1820/2-174,846);
		g2D.drawString("Q",1820/2-174,866);
		g2D.drawString("W",1820/2-174,886);
		g2D.drawString("S",1820/2-174,906);
		g2D.drawString("E",1820/2-174,926);

		g2D.setColor(Color.BLUE);
		g2D.drawString("⮜/⮞",1820/2+136,828);
		g2D.drawString("Enter",1820/2+120,846);
		g2D.drawString("Shift",1820/2+120,866);
		g2D.drawString("⮝",1820/2+158,886);
		g2D.drawString("⮟",1820/2+158,906);
		g2D.drawString("Ctrl",1820/2+132,926);

		paintRoutes(g2D);

		if(gameWon || gameLost){
			if(gameWon){
				g2D.drawImage(ImageReader.loadImage(ImageReader.path+"wonscreen.png"),0,0,1820,980,null);
			} else {
				g2D.drawImage(ImageReader.loadImage(ImageReader.path+"lostscreen.png"),0,0,1820,980,null);
			}
			ProtoUtil.timer.stop();
		}
		
	}


}
