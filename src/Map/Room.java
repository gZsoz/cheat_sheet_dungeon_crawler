package Map;
import Items.Item;
import ProtoUtil.ProtoUtil;
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
		ProtoUtil.printLog(name+".create()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
	}
	
	/**
     * Lerakja a paraméterként kapott tárgyat a szobába.
     * @param a A lerakandó tárgy
     */
    public void spawnItem(Item a) {
    	ProtoUtil.printLog(name+".spawnItem("+a.name+")");
		ProtoUtil.increaseIndent();
		addItem(a);
    	ProtoUtil.decreaseIndent();
    }
    
    /**
     * Hozzáadja a paraméterként kapott karaktert a szobához.
     * @param c A hozzáadandó karakter
     */
    public void addCharacter(Character character) {
    	ProtoUtil.printLog(name+".addCharacter("+character.name+")");
		ProtoUtil.increaseIndent();
		if(ProtoUtil.binaryQuestion("Gázos-e a szoba?")) {
			new Gas().stun(character);
		}else {
		}
    	ProtoUtil.decreaseIndent();
    }
    
    /**
     * Hozzáadja a paraméterként kapott tárgyat a szobában található tárgyak listájához.
     * @param i A hozzáadandó tárgy
     */
    public void addItem(Item i) {
    	ProtoUtil.printLog(name+".addItem("+i.name+")");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
    }
    
    /**
     * Eltávolítja a paraméterként kapott karaktert a szobából.
     * @param c Az eltávolítandó karakter
     */
    public void removeCharacter(Character character) {
		ProtoUtil.printLog(name+".removeCharacter("+character.name+")");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
    }
    
    /**
     * Eltávolítja a paraméterként kapott tárgyat a szobából.
     * @param i Az eltávolítandó tárgy
     */
    public void removeItem(Item i) {
    	ProtoUtil.printLog(name+".removeItem("+i.name+")");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
    }
    
    /**
     * Hozzáadja a paraméterként kapott szomszédos szobát a szoba szomszédok listájához.
     * @param r A hozzáadandó szomszédos szoba
     */
    public void addNeighbour(Room r) {
    	ProtoUtil.printLog(name+".addNeighbour("+r.name+")");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
    }
    
    /**
     * Eltávolítja a paraméterként kapott szomszédos szobát a szoba szomszédok listájából.
     * @param r Az eltávolítandó szomszédos szoba
     */
    public void removeNeighbour(Room r) {
    	ProtoUtil.printLog(name+".removeNeighbour("+r.name+")");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
    }
    
    /**
     * Hozzáadja a paraméterként kapott környezeti tényezőt a szobához.
     * @param ef A hozzáadandó környezeti tényező
     */
    public void addEnvironmentalFactor(EnvironmentalFactors ef) {
    	ProtoUtil.printLog(name+".addEnvironmentalFactor("+ef.name+")");
		ProtoUtil.increaseIndent();
		ef.setLocation(this);
    	ProtoUtil.decreaseIndent();
    }
    
    /**
     * Visszaadja, hogy éppen hányan vannak a szobában.
     * @return A szobában tartózkodó karakterek száma
     */
    public int currentNumOfPlayers() {
    	ProtoUtil.printLog(name+".currentNumOfPlayers()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
		return 0;
    }
    
    /**
     * Összeolvasztja a paraméterként kapott szobát a jelenlegivel.
     * @param r Az összeolvasztandó szoba
     */
    public void merge(Room r) {
    	ProtoUtil.printLog(name+".merge("+r.name+")");
		ProtoUtil.increaseIndent();
		r.getNeighbours();
		if(ProtoUtil.binaryQuestion("Legyen Szoba_Új a szomszédja "+r.name+"-nek?")) {
			Room temp = new Room("Szoba_Új");
			addNeighbour(temp);
			temp.addNeighbour(this);
			temp.removeNeighbour(r);
		}else {
		}
    	ProtoUtil.decreaseIndent();
    }

    /**
     * Visszaadja, a szoba szomszédait.
     * @return A szoba szomszédai egy listában.
     */
	public List<Room> getNeighbours() {
		ProtoUtil.printLog(name+".getNeighbours()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
		return null;
	}
	
	/**
     * Visszaadja, a szoba kapcitását.
     * @return A szoba kapacitása.
     */
	public int getCapacity() {
		ProtoUtil.printLog(name+".getCapacity()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
		return 0;
	}

	/**
     * Visszaadja, a szobában található karaktereket.
     * @return A szobában található karakterek egy listában.
     */
	public List<Character> getCharacters() {
		ProtoUtil.printLog(name+".getCarachters()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
		return null;
	}
}