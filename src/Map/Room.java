package Map;

import Items.Item;
import ProtoUtil.ProtoUtil;
import Time.iTask;
import EnvironmentalFactor.EnvironmentalFactors;

import java.util.ArrayList;
import java.util.List;

import Character.Character;

/**
 * A Room osztály reprezentálja a játék világában található szobákat.
 * Tárolja a szomszédos szobák listáját (azok a szobák, melyekbe vezet ajtó az adott szobából),
 * valamint a szobában található tárgyakat és karaktereket.
 * Nyilvántartja a szoba befogadóképességét, ami alapján ellenőrzi, hogy a szoba befogad-e további karaktereket.
 * Kezeli a szoba állapotát (gázos-e).
 */
public class Room implements iTask {
    
    /** A szoba kapacitása */
    protected int capacity=1;
    
    /** A szomszéd szobák listája */
    protected List<Room> neighbours = new ArrayList<Room>();
    
    /** A szobában található tárgyak listája */
    protected List<Item> items = new ArrayList<Item>();
    
    /** A szobában található karakterek listája */
    protected List<Character> characters = new ArrayList<Character>();
    
    /** A szoba környezeti tényezőinek listája */
    protected List<EnvironmentalFactors> envFactors = new ArrayList<EnvironmentalFactors>();

    /**
     * Konstruktor egy szoba létrehozásához.
     */
    public Room() {
    }
    
    /**
     * Konstruktor egy szoba létrehozásához.
     * @param neighbours a szoba szomszédai
     * @param capacity kapacitás
     */
    public Room(List<Room> neighbours, int capacity) {
    	this.neighbours = neighbours;
    	this.capacity = capacity;
    }
    
    /**
     * Konstruktor egy szoba létrehozásához.
     * @param capacity kapacitás
     */
    public Room(int capacity) {
    	this.capacity = capacity;
    }

    /**
     * Lerakja a paraméterként kapott tárgyat a szobába.
     * @param a A lerakandó tárgy
     */
    public void spawnItem(Item a) {
        ProtoUtil.printLog("spawnItem");
        addItem(a);
    }
    
    /**
     * Hozzáadja a paraméterként kapott karaktert a szobához.
     * @param c A hozzáadandó karakter
     */
    public void addCharacter(Character character) {
        ProtoUtil.printLog("addCharacter");
        characters.add(character);
    }
    
    /**
     * Hozzáadja a paraméterként kapott tárgyat a szobában található tárgyak listájához.
     * @param i A hozzáadandó tárgy
     */
    public void addItem(Item i) {
        ProtoUtil.printLog("addItem");
        items.add(i);
    }
    
    /**
     * Eltávolítja a paraméterként kapott karaktert a szobából.
     * @param c Az eltávolítandó karakter
     */
    public void removeCharacter(Character character) {
        ProtoUtil.printLog("removeCharacter");
        if (!characters.remove(character))
            System.out.println("Olyan karakterre lett meghívva a removeCharacter, ami nincs a listában!!!");
    }
    
    /**
     * Eltávolítja a paraméterként kapott tárgyat a szobából.
     * @param i Az eltávolítandó tárgy
     */
    public void removeItem(Item i) {
        ProtoUtil.printLog("removeItem");
        if (!items.remove(i))
            System.out.println("Olyan Itemre lett meghívva a removeItem, ami nincs a listában!!!");
    }
    
    /**
     * Hozzáadja a paraméterként kapott szomszédos szobát a szoba szomszédok listájához.
     * @param r A hozzáadandó szomszédos szoba
     */
    public void addNeighbour(Room r) {
        ProtoUtil.printLog("addNeighbour");
        neighbours.add(r);
    }
    
    /**
     * Eltávolítja a paraméterként kapott szomszédos szobát a szoba szomszédok listájából.
     * @param r Az eltávolítandó szomszédos szoba
     */
    public void removeNeighbour(Room r) {
        ProtoUtil.printLog("removeNeighbour");
        if (!neighbours.remove(r))
            System.out.println("Olyan Roomra lett meghívva a removeNeighbour ami nincs a listában!!!");
    }
    
    /**
     * Hozzáadja a paraméterként kapott környezeti tényezőt a szobához.
     * @param ef A hozzáadandó környezeti tényező
     */
    public void addEnvironmentalFactor(EnvironmentalFactors ef) {
        ProtoUtil.printLog("addEnvironmentalFactor");
        ef.setLocation(this);
        for (EnvironmentalFactors e : envFactors )
            if (e.getClass().equals(ef.getClass()))
                return;
        envFactors.add(ef);
    }
    
    /**
     * Visszaadja a szoba környezeti tényezőit.
     * @return A szoba környezeti tényezőinek listája
     */
    public List<EnvironmentalFactors> getEnvironmentalFactors() {
        return envFactors;
    }
    
    /**
     * Visszaadja, hogy éppen hányan vannak a szobában.
     * @return A szobában tartózkodó karakterek száma
     */
    public int currentNumOfPlayers() {
        ProtoUtil.printLog("currentNumOfPlayers");
        return characters.size();
    }
    
    /**
     * Összeolvasztja a paraméterként kapott szobát a jelenlegivel.
     * @param r Az összeolvasztandó szoba
     */
    public void merge(Room r) {
        ProtoUtil.printLog("merge");
        for (Room current : r.getNeighbours()) {
            if (!neighbours.contains(current)) {
                addNeighbour(current);
                current.addNeighbour(this);
                current.removeNeighbour(r);
            }
        }
        for (Item i : r.getItems()) {
            addItem(i);
        }
        for (Character c : r.getCharacters()) {
            addCharacter(c);
        }
    }

    /**
     * Visszaadja, a szoba szomszédait.
     * @return A szoba szomszédai egy listában.
     */
    public List<Room> getNeighbours() {
        ProtoUtil.printLog("getNeighbours");
        return neighbours;
    }
    
    /**
     * Visszaadja, a szoba kapcitását.
     * @return A szoba kapacitása.
     */
    public int getCapacity() {
        ProtoUtil.printLog("getCapacity");
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        ProtoUtil.printLog("setCapacity");
        this.capacity=capacity;
    }

    /**
     * Visszaadja, a szobában található karaktereket.
     * @return A szobában található karakterek egy listában.
     */
    public List<Character> getCharacters() {
        ProtoUtil.printLog("getCarachters");
        return characters;
    }

    /**
     * Visszaadja, a szobában található tárgyakat.
     * @return A szobában található tárgyak egy listában.
     */
    public List<Item> getItems() {
        ProtoUtil.printLog("getItems");
        return items;
    }

    /**
     * Meghívja a tárolt Character-ek és EnvironmentalFactorok update() metódusát
     */
    @Override
    public void update() {
        for (Character c : characters)
            c.update();
        for (EnvironmentalFactors env : envFactors)
            env.update();
    }
}
