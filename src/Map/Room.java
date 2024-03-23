package Map;
import Items.Item;

import SkeletonUtil.SkeletonUtil;
import EnvironmentalFactor.EnvironmentalFactors;
import EnvironmentalFactor.Gas;

import java.util.List;

import Character.*;
import Character.Character;
/**
 * A Room osztály reprezentálja a játék világában található szobákat.
 * Tárolja a szomszédos szobák listáját (azok a szobák, melyekbe vezet ajtó az adott szobából),
 * valamint a szobában található tárgyakat és karaktereket.
 * Nyilvántartja a szoba befogadóképességét, ami alapján ellenőrzi, hogy a szoba befogad-e további karaktereket.
 * Kezeli a szoba állapotát (gázos-e).
 */
public class Room {
	
	/** A szoba neve csak a Skeletonban lesz szerepe.*/
    public String name;

    /**
     * Konstruktor egy szoba létrehozásához. Room-ra állítja a nevet.
     */
    public Room() {
    	name="Room";
    }
    
    /**
     * Konstruktor egy szoba létrehozásához.
     * @param name A szoba neve
     */
	public Room(String name) {
		this.name = name;
	}
	
	/**
    * A konstruktort szimbolizálja a skeleton programban.
    */
	public void create() {
		SkeletonUtil.printLog(name+".create()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
	
	/**
     * Lerakja a paraméterként kapott tárgyat a szobába.
     * @param a A lerakandó tárgy
     */
    public void spawnItem(Item a) {
    	SkeletonUtil.printLog(name+".spawnItem(Item)");
		SkeletonUtil.increaseIndent();
		addItem(a);
    	SkeletonUtil.decreaseIndent();
    }
    
    /**
     * Hozzáadja a paraméterként kapott karaktert a szobához.
     * @param c A hozzáadandó karakter
     */
    public void addCharacter(Character character) {
    	SkeletonUtil.printLog(name+".addCharacter(Character)");
		SkeletonUtil.increaseIndent();
		if(SkeletonUtil.binaryQuestion("Gázos-e a szoba?")) {
			new Gas().stun(character);
		}else {
		}
    	SkeletonUtil.decreaseIndent();
    }
    
    /**
     * Hozzáadja a paraméterként kapott tárgyat a szobában található tárgyak listájához.
     * @param i A hozzáadandó tárgy
     */
    public void addItem(Item i) {
    	SkeletonUtil.printLog(name+".addItem(Item)");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
    }
    
    /**
     * Eltávolítja a paraméterként kapott karaktert a szobából.
     * @param c Az eltávolítandó karakter
     */
    public void removeCharacter(Character character) {
		SkeletonUtil.printLog(name+".removeCharacter(Character)");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
    }
    
    /**
     * Eltávolítja a paraméterként kapott tárgyat a szobából.
     * @param i Az eltávolítandó tárgy
     */
    public void removeItem(Item i) {
    	SkeletonUtil.printLog(name+".removeCharacter(Item)");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
    }
    
    /**
     * Hozzáadja a paraméterként kapott szomszédos szobát a szoba szomszédok listájához.
     * @param r A hozzáadandó szomszédos szoba
     */
    public void addNeighbour(Room r) {
    	SkeletonUtil.printLog(name+".addNeighbour(Room)");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
    }
    
    /**
     * Eltávolítja a paraméterként kapott szomszédos szobát a szoba szomszédok listájából.
     * @param r Az eltávolítandó szomszédos szoba
     */
    public void removeNeighbour(Room r) {
    	SkeletonUtil.printLog(name+".removeNeighbour(Room)");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
    }
    
    /**
     * Hozzáadja a paraméterként kapott környezeti tényezőt a szobához.
     * @param ef A hozzáadandó környezeti tényező
     */
    public void addEnvironmentalFactor(EnvironmentalFactors ef) {
    	SkeletonUtil.printLog(name+".addEnvironmentalFactor(EnvironmentalFactors)");
		SkeletonUtil.increaseIndent();
		ef.setRoom(this);
    	SkeletonUtil.decreaseIndent();
    }
    
    /**
     * Visszaadja, hogy éppen hányan vannak a szobában.
     * @return A szobában tartózkodó karakterek száma
     */
    public int currentNumOfPlayers() {
    	SkeletonUtil.printLog(name+".currentNumOfPlayers()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
		return 0;
    }
    
    /**
     * Összeolvasztja a paraméterként kapott szobát a jelenlegivel.
     * @param r Az összeolvasztandó szoba
     */
    public void merge(Room r) {
    	SkeletonUtil.printLog(name+".merge()");
		SkeletonUtil.increaseIndent();
		r.getNeighbours();
		if(SkeletonUtil.binaryQuestion("Legyen Szoba_Új a szomszédja "+r.name+"-nek?")) {
			Room temp = new Room("Szoba_Új");
			addNeighbour(temp);
			temp.addNeighbour(this);
			temp.removeNeighbour(r);
		}else {
		}
    	SkeletonUtil.decreaseIndent();
    }

    /**
     * Visszaadja, a szoba szomszédait.
     * @return A szoba szomszédai egy listában.
     */
	public List<Room> getNeighbours() {
		SkeletonUtil.printLog(name+".getNeighbours()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
		return null;
	}
	
	/**
     * Visszaadja, a szoba kapcitását.
     * @return A szoba kapacitása.
     */
	public int getCapacity() {
		SkeletonUtil.printLog(name+".getCapacity()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
		return 0;
	}

	/**
     * Visszaadja, a szobában található karaktereket.
     * @return A szobában található karakterek egy listában.
     */
	public List<Character> getCharacters() {
		SkeletonUtil.printLog(name+".getCarachters()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
		return null;
	}
}