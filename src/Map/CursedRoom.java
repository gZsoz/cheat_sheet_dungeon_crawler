package Map;

import java.util.ArrayList;
import java.util.List;

import Character.Character;
import ProtoUtil.ProtoUtil;

/**
 * A CursedRoom osztály egy speciális típusú szobát reprezentál a játékban, amely az alap Room osztálytól származik.
 * Felelőssége megegyezik a szoba osztállyal, emellett felelőssége még az ajtók eltüntetése és megjelenítése.
 */
public class CursedRoom extends Room{
	
	public static int defaultCloseDuration=100;
	
	private int closeDuration=0; // A Szoba zárva tartásának ideje

    public boolean getIsOpen() {
        return isOpen;
    }

    private boolean isOpen=true; // A Szoba állapota: nyitva vagy zárva
	
    /**
     * Konstruktor egy szoba létrehozásához.
     */
    public CursedRoom() {
    	super();
    }
    
    /**
     * Konstruktor egy szoba létrehozásához.
     * @param neighbours a szoba szomszédai
     * @param capacity kapacitás
     */
    public CursedRoom(List<Room> neighbours, int capacity) {
    	super(neighbours, capacity);
    }
    
    /**
     * Konstruktor egy szoba létrehozásához.
     * @param capacity kapacitás
     */
    public CursedRoom(int capacity) {
		super(capacity);
	}

	/**
     * Beállítja az ajtók állapotát zártnak és inicializálja a closeDuration-t a definiált kezdőértékre.
     */
    public void hideDoors() {
        ProtoUtil.printLog("hideDoors");
        removeAllDoors();
        setCloseDuration(defaultCloseDuration);
    }
    
    private void setCloseDuration(int duration){
    	closeDuration=duration;
    	notifySubsribers("closeDuration");
    }
    
    /**
     * A szoba összes ajtaját eltávolítja.
     */
    public void removeAllDoors() {
        ProtoUtil.printLog("removeAllDoors");
        isOpen = false;
        notifySubsribers("enteredcursedroom");
    }
	
    /**
     * Hozzáadja a paraméterként kapott karaktert a listához, majd három másodpercre eltünteti a szoba összes ajtaját (szomszédait),
     * ha a szoba kapacitása ezt nem engedné meg, akkor Exceptiont dob.
     * @param character A hozzáadandó karakter
     * @throws Exception Ha a szoba kapacitása nem engedi meg a karakter hozzáadását
     */
    @Override
    public void addCharacter(Character character) {
        super.addCharacter(character);
        hideDoors();
    }
    
    /**
     * Csökkenti a szoba zárva maradásának idejét.
     */
    public void reduceDuration() {
        if (closeDuration > 0) {
        	ProtoUtil.printLog("reduceDurationCursed");
            setCloseDuration(closeDuration-1);
            if (closeDuration == 0) {
                isOpen = true;
                ProtoUtil.printLog("bringbackDoors");
            }
        }
    }
    
    /**
     * Visszaadja a szoba szomszédos szobáit. Ha a szoba zárva van, üres listát ad vissza.
     * @return A szomszédos szobák listája
     */
    @Override
    public List<Room> getNeighbours() {
        ProtoUtil.printLog("getNeighbours");
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
     * Meghívja a tárolt Character-ek és EnvironmentalFactorok update() metódusát 
     * és csökkenti a szoba zárva maradásának idejét.
     */
    @Override
    public void update() {
        super.update();
        if(!isOpen)
        	reduceDuration();
    }
}
