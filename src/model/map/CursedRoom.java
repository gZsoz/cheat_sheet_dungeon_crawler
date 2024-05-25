package model.map;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import model.characters.Character;

/**
 * A CursedRoom osztály egy speciális típusú szobát reprezentál a játékban, amely az alap Room osztálytól származik.
 * Felelőssége megegyezik a szoba osztállyal, emellett felelőssége még az ajtók eltüntetése és megjelenítése.
 */
public class CursedRoom extends Room {
	
	/**
	 * Ezzel a változóval állítható be a main-ban az alapértelmezett zárva tartási idő hossza.
	 */
	public static int defaultCloseDuration=7 * Main.fps;
	
	/**
	 * A szoba zárva tartásának ideje.
	 */
	private int closeDuration=0;
	
	/**
	 * A szoba állapota: nyitva vagy zárva.
	 */
	private boolean isOpen = true;
	
	/**
	 * Konstruktor egy elátkozott szoba létrehozásához.
	 */
	public CursedRoom() {
		super();
	}
	
	/**
	 * Konstruktor egy elátkozott szoba létrehozásához.
	 * @param capacity kapacitás
	 */
	public CursedRoom(int capacity) {
		super(capacity);
	}
	
	/**
	 * Konstruktor egy elátkozott szoba létrehozásához.
	 * @param neighbours a szoba szomszédai
	 * @param capacity kapacitás
	 */
	public CursedRoom(List<Room> neighbours, int capacity) {
		super(neighbours, capacity);
	}
	
	/**
	 * Visszaadja, hogy nyitva van-e a szoba.
	 * @return nyitva van-e a szoba
	 */
	public boolean getIsOpen() {
	    return isOpen;
	}
	
	/**
	 * Visszaadja a szoba szomszédos szobáit. Ha a szoba zárva van, üres listát ad vissza.
	 * @return a szomszédos szobák listája
	 */
	@Override
	public List<Room> getNeighbours() {
	    Main.printLog("getNeighbours");
	    if (isOpen) {
	        ArrayList<Room> openNeighbours = new ArrayList<>();
	        for(Room r : new ArrayList<Room>(neighbours)) {
	            if(!((r instanceof CursedRoom) && !((CursedRoom) r).getIsOpen())) {
	                openNeighbours.add(r);
	            }
	        }
	        return openNeighbours;
	    }
	    return new ArrayList<Room>();
	}
	
	/**
	 * Hozzáadja a paraméterként kapott karaktert a listához, majd három másodpercre eltünteti a szoba összes ajtaját (szomszédait),
	 * ha a szoba kapacitása ezt nem engedné meg, akkor Exceptiont dob.
	 * @param character a hozzáadandó karakter
	 * @throws Exception ha a szoba kapacitása nem engedi meg a karakter hozzáadását
	 */
	@Override
	public void addCharacter(Character character) {
	    hideDoors();
	    super.addCharacter(character);
	    notifySubscribers("removedfromneighbours");
	}
	
	/**
	 * Beállítja az ajtók állapotát zártnak és inicializálja a closeDuration-t a definiált kezdőértékre.
	 */
	public void hideDoors() {
	    Main.printLog("hideDoors");
	    removeAllDoors();
	    setCloseDuration(defaultCloseDuration);
	}
	
	/**
	 * A szoba összes ajtaját eltávolítja.
	 */
	public void removeAllDoors() {
	    Main.printLog("removeAllDoors");
	    isOpen = false;
	    notifySubscribers("enteredcursedroom");
	}
	
	/**
	 * Beállítja a szoba zárva tartási ideje.
	 * @param duration a szoba zárva tartási ideje
	 */
	private void setCloseDuration(int duration){
		closeDuration=duration;
		notifySubscribers("closeDuration");
	}
	
	/**
	 * Csökkenti a szoba zárva maradásának idejét.
	 */
	public void reduceDuration() {
	    if (closeDuration > 0) {
	    	Main.printLog("reduceDurationCursed");
	        setCloseDuration(closeDuration-1);
	        if (closeDuration == 0) {
	            isOpen = true;
	            Main.printLog("bringbackDoors");
	        }
	    }
	}
	
	/**
	 * Meghívja a tárolt Model.Character-ek és EnvironmentalFactorok update() metódusát és csökkenti a szoba zárva maradásának idejét.
	 */
	@Override
	public void update() {
	    super.update();
	    if(!isOpen)
	    	reduceDuration();
	}
}
