package Map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import EnvironmentalFactor.Gas;
import EnvironmentalFactor.Sticky;
import Items.*;
import ProtoUtil.ProtoUtil;
import Time.iTask;
import View.Utils.Subscriber;
import Character.*;

// Labyrinth

/**
 * Osztály, mely reprezentálja a labirintust a játékban.
 */
public class Labyrinth implements iTask{

	/** A labirintus változásaira feliratkozott osztályok*/
	public List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	private List<Room> Rooms;

	public Labyrinth(){
		Rooms=new ArrayList<Room>();
	}
	
    /**
     * meghívja a konstruktorában beállított feliratkozóira a propertyChanged(String)
     *  függvényüket a paraméterként kapott Stringgel
     * @param str
     */
    public void notifySubsribers(String str) {
    	for(Subscriber sub : subscribers)
    		sub.propertyChanged(str);
    }

	public List<Room> getRooms(){
		return Rooms;
	}

    /**
     * Szobát add a labirintushoz.
     * @param r A hozzáadandó szoba.
     */
	public void addRoom(Room r) {
		ProtoUtil.printLog("addRoom");
		Rooms.add(r);
    }

	/**
	 * Egy szoba kitörlése a labirintusból.
	 * @param r - A törlendő szoba.
	 */
	public void removeRoom(Room r) {
		ProtoUtil.printLog("removeRoom");
		Rooms.remove(r);
	}

    /**
     * Összeolvaszt egy szobát egy másikkal a labirintusban.
     * @param result Az összeolvadt szobák eredménye.
     * @param merging A beolvasztandó szoba.
     */
	public void mergeRoom(Room result, Room merging) {
		ProtoUtil.printLog("mergeRoom");
		int bigger;
		if(result.getCapacity()>merging.getCapacity()) bigger=result.getCapacity();
		else bigger=merging.getCapacity();
		if(result.getCharacters().size()+merging.getCharacters().size()>bigger){
			return;
		}else{
			result.setCapacity(bigger);
			result.merge(merging);
			this.removeRoom(merging);
		}
    }
	
    /**
     * Egy szoba kettéosztása a labirintusban a tesztprogramhoz determisztikus
     * @param r A szétosztandó szoba.
     */
	public void splitRoomTest(Room old, int neighbourcnt, int itemcnt, int charactercnt) {
		ProtoUtil.printLog("splitRoom");
		Room n;
		// Az új szoba típusa a régi szobától függ
		if(old instanceof CursedRoom) {
			n=new CursedRoom(old.getCapacity());
		}
		else {
			n=new Room(old.getCapacity());
		}
		// Szomszédok hozzáadása
		for(int i=0; i<neighbourcnt; i++){
			n.addNeighbour(old.getNeighbours().get(0));
			old.getNeighbours().get(0).addNeighbour(n);
			old.getNeighbours().get(0).removeNeighbour(old);
			old.removeNeighbour(old.getNeighbours().get(0));
		}
		old.addNeighbour(n);
		n.addNeighbour(old);
		// Tárgyak hozzáadása
		for(int i=0; i<itemcnt; i++){
			n.addItem(old.getItems().get(0));
			old.removeItem(old.getItems().get(0));
		}
		// Karakterek hozzáadása
		for(int i=0; i<charactercnt; i++){
			old.getCharacters().get(i).enterRoom(n);
		}
		// Környezeti tényezők hozzáadása
		for(int i=0; i<old.getEnvironmentalFactors().size(); i++){
			if(old.getEnvironmentalFactors().get(i) instanceof Gas) n.addEnvironmentalFactor(new Gas(n));
			else if(old.getEnvironmentalFactors().get(i) instanceof Sticky) n.addEnvironmentalFactor(new Sticky(n));
		}
		Rooms.add(Rooms.indexOf(old), n);
		ProtoUtil.printLog("Neighbours of old room: "+old.getNeighbours().size());
		ProtoUtil.printLog("Neighbours of new room: "+n.getNeighbours().size());
		ProtoUtil.printLog("Items of old room: "+old.getItems().size());
		ProtoUtil.printLog("Items of new room: "+n.getItems().size());
		ProtoUtil.printLog("Characters of old room: "+old.getCharacters().size());
		ProtoUtil.printLog("Characters of new room: "+n.getCharacters().size());
		ProtoUtil.printLog("EnvironmentalFactors of old room: "+old.getEnvironmentalFactors().size());
		ProtoUtil.printLog("EnvironmentalFactors of new room: "+n.getEnvironmentalFactors().size());
		
    }

    /**
     * Egy szoba kettéosztása a labirintusban.
     * @param r A szétosztandó szoba.
     */
	public void splitRoom(Room old) {
		ProtoUtil.printLog("splitRoom");
		Room n;
		// Az új szoba típusa a régi szobától függ
		if(old instanceof CursedRoom) {
			n=new CursedRoom(old.getCapacity());
		}
		else {
			n=new Room(old.getCapacity());
		}
		// Szomszédok hozzáadása
		Random random=new Random();
		int rand=random.nextInt(old.getNeighbours().size());
		for(int i=0; i<rand; i++){
			n.addNeighbour(old.getNeighbours().get(0));
			old.getNeighbours().get(0).addNeighbour(n);
			old.getNeighbours().get(0).removeNeighbour(old);
			old.removeNeighbour(old.getNeighbours().get(0));
		}
		old.addNeighbour(n);
		n.addNeighbour(old);
		// Tárgyak hozzáadása
		rand=random.nextInt(old.getItems().size());
		for(int i=0; i<rand; i++){
			n.addItem(old.getItems().get(0));
			old.removeItem(old.getItems().get(0));
		}
		// Karakterek hozzáadása
		rand=random.nextInt(old.getCharacters().size());
		for(int i=0; i<rand; i++){
			old.getCharacters().get(i).enterRoom(n);
		}
		// Környezeti tényezők hozzáadása
		for(int i=0; i<old.getEnvironmentalFactors().size(); i++){
			if(old.getEnvironmentalFactors().get(i) instanceof Gas) n.addEnvironmentalFactor(new Gas(n));
			else if(old.getEnvironmentalFactors().get(i) instanceof Sticky) n.addEnvironmentalFactor(new Sticky(n));
		}
		Rooms.add(Rooms.indexOf(old), n);
		ProtoUtil.printLog("Neighbours of old room: "+old.getNeighbours().size());
		ProtoUtil.printLog("Neighbours of new room: "+n.getNeighbours().size());
		ProtoUtil.printLog("Items of old room: "+old.getItems().size());
		ProtoUtil.printLog("Items of new room: "+n.getItems().size());
		ProtoUtil.printLog("Characters of old room: "+old.getCharacters().size());
		ProtoUtil.printLog("Characters of new room: "+n.getCharacters().size());
		ProtoUtil.printLog("EnvironmentalFactors of old room: "+old.getEnvironmentalFactors().size());
		ProtoUtil.printLog("EnvironmentalFactors of new room: "+n.getEnvironmentalFactors().size());
    }

	
	/**
	 * Véletlenszerűen kiválaszt egy tárgyat a megadott típusok közül.
	 * 
	 * @return A véletlenszerűen kiválasztott tárgy.
	 */
	private Item itemPicker(){
		int rand=new Random().nextInt(11);
		switch(rand){
		case 1:
			return new AirFreshener();
		case 2:
			return new BatSkin();
		case 3:
			return new Beer();
		case 4:
			return new CabbageCamembert();
		case 5:
			return new FakeBatSkin();
		case 6:
			return new FakeMask();
		case 7:
			return new FakeSlideRule();
		case 8:
			return new Mask();
		case 9:
			return new SlideRule();
		case 10:
			return new Transistor();
		case 11:
			return new WetCloth();
		default:
			return null;
		}
	}

    /**
     * A szobák generálása a labirintusban.
     */
	public void generateRooms() {
		ProtoUtil.printLog("generateRooms");
		Room r1 = new Room(4);
		Room r2 = new Room(4);
		Room r3 = new Room(4);
		Room r4 = new Room(4);
		Room r5 = new Room(4);
		Room r6 = new Room(4);
		Room r7 = new Room(4);
		Room r8 = new Room(4);
		Room r9 = new Room(4);
		Room r10 = new Room(4);
		Rooms.add(r1);
		Rooms.add(r2);
		Rooms.add(r3);
		Rooms.add(r4);
		Rooms.add(r5);
		Rooms.add(r6);
		Rooms.add(r7);
		Rooms.add(r8);
		Rooms.add(r9);
		Rooms.add(r10);
		Student s = new Student(r4);
		r4.addCharacter(s);
    }

	@Override
	public void update() {
		// A random események végrehajtása pl.: merge, split
		Random rand=new Random();
		if(ProtoUtil.random.nextInt(1000, -1)==0)
			mergeRoom(Rooms.get(rand.nextInt(Rooms.size())),Rooms.get(rand.nextInt(Rooms.size())));
		if(ProtoUtil.random.nextInt(1000, -1)==0)
			splitRoom(Rooms.get(rand.nextInt(Rooms.size())));
		for(Room r : Rooms){
			// Random item generálása
			if(ProtoUtil.random.nextInt(1000, -1)==0)
				r.addItem(itemPicker());
			r.update();
		}
	}
}
