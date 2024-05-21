package Map;

import Items.FakeSlideRule;
import Items.Item;
import Items.SlideRule;
import Items.Transistor;
import ProtoUtil.ProtoUtil;
import Time.iTask;
import View.Utils.Subscriber;
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
	
	/**
	 * Ezzel a változóval állítható be a ProtoUtil-ban a szoba maximális kapacitása.
	 */
	public static int maxItemCapacity=6;
	
	/**
	 * A szoba változásaira feliratkozott osztályok.
	 */
	public List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	/**
	 * A szoba kapacitása.
	 */
	protected int capacity=1;
	
	/**
	 * A szomszéd szobák listája.
	 */
	public List<Room> neighbours = new ArrayList<Room>();
	
	/**
	 * A szobában található tárgyak listája.
	 */
	protected List<Item> items = new ArrayList<Item>();
	
	/**
	 * A szobában található karakterek listája.
	 */
	protected List<Character> characters = new ArrayList<Character>();
	
	/**
	 * A szoba környezeti tényezőinek listája.
	 */
	protected List<EnvironmentalFactors> envFactors = new ArrayList<EnvironmentalFactors>();
	
	/**
	 * Konstruktor egy szoba létrehozásához.
	 */
	public Room() {}
	
	/**
	 * Konstruktor egy szoba létrehozásához.
	 * @param capacity kapacitás
	 */
	public Room(int capacity) {
		this.capacity = capacity;
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
	 * Visszaadja a szoba kapcitását.
	 * @return A szoba kapacitása.
	 */
	public int getCapacity() {
	    ProtoUtil.printLog("getCapacity");
	    return capacity;
	}
	
	/**
	 * Beállítja a szoba kapcitását.
	 * @param capacity a kapacitás
	 */
	public void setCapacity(int capacity) {
	    ProtoUtil.printLog("setCapacity");
	    this.capacity=capacity;
	    notifySubsribers("capacity");
	}
	
	/**
	 * Visszaadja a szoba környezeti tényezőit.
	 * @return a szoba környezeti tényezőinek listája
	 */
	public List<EnvironmentalFactors> getEnvironmentalFactors() {
	    return envFactors;
	}
	
	/**
	 * Visszaadja, hogy éppen hányan vannak a szobában.
	 * @return a szobában tartózkodó karakterek száma
	 */
	public int currentNumOfPlayers() {
	    ProtoUtil.printLog("currentNumOfPlayers");
	    return characters.size();
	}
	
	/**
	 * Visszaadja, hogy éppen hány tárgy van a szobában.
	 * @return a szobában lévő tárgyak száma
	 */
	public int currentNumOfItems() {
	    ProtoUtil.printLog("currentNumOfItems");
	    return items.size();
	}
	
	/**
	 * Visszaadja a szobában található karaktereket.
	 * @return a szobában található karakterek egy listában
	 */
	public List<Character> getCharacters() {
	    ProtoUtil.printLog("getCharacters");
	    return characters;
	}
	
	/**
	 * Visszaadja a szobában található tárgyakat.
	 * @return a szobában található tárgyak egy listában
	 */
	public List<Item> getItems() {
	    ProtoUtil.printLog("getItems");
	    return items;
	}
	
	/**
	 * Visszaadja, a szoba szomszédait.
	 * @return a szoba szomszédai egy listában
	 */
	public List<Room> getNeighbours() {
	    ProtoUtil.printLog("getNeighbours");
	    ArrayList<Room> openNeighbours = new ArrayList<>();
	    for(Room r : new ArrayList<Room>(neighbours)) {
	    	if(!((r instanceof CursedRoom) && r.getNeighbours().isEmpty()) && !r.neighbours.isEmpty()) {
	    		openNeighbours.add(r);
	    	}
	    }
	    return openNeighbours;
	}
	
	/**
	 * Meghívja a konstruktorában beállított feliratkozóira a propertyChanged(String)
	 * függvényüket a paraméterként kapott Stringgel.
	 * @param str üzenet arról, hogy mi változott meg, lehetséges értékek:
	 * "factors", "closeduration", "characters", "items", "capacity", "spawnitem <item pos>", "items removed "+idx", "spawnfactor <factor pos>", "roomremoved", "enteredcursedroom"
	 */
	public void notifySubsribers(String str) {
		for(Subscriber sub : new ArrayList<>(subscribers))
			sub.propertyChanged(str);  // lehetséges értékek: "factors", "closeduration", "characters", "items", "capacity", "spawnitem <item pos>", "items removed "+idx", "spawnfactor <factor pos>", "roomremoved", "enteredcursedroom"
	}
	
	/**
	 * Hozzáadja a paraméterként kapott Subscriber objektumot a feliratkózók listájához,
	 * ezentúl a propertyChanged függvénye meghívásával jelzi, ha belső állapota megváltozik.
	 * @param sub a feliratkozó View objektum
	 */
	public void subscribe(Subscriber sub) {
		subscribers.add(sub);
	}
	
	/**
	 * Eltávolítja a paraméterként kapott Subscriber objektumot a feliratkózók listájából,
	 * ezentúl nem kap értesítést, ha az osztály belső állapota megváltozik.
	 * @param sub a leiratkozó View objektum
	 */
	public void unsubscribe(Subscriber sub) {
		subscribers.remove(sub);
	}
	
	/**
	 * Hozzáadja a paraméterként kapott karaktert a szobához.
	 * @param c A hozzáadandó karakter
	 */
	public void addCharacter(Character character) {
	    ProtoUtil.printLog("addCharacter");
	    characters.add(character);
	    notifySubsribers("characters");
	}
	
	/**
	 * Eltávolítja a paraméterként kapott karaktert a szobából.
	 * @param character az eltávolítandó karakter
	 */
	public void removeCharacter(Character character) {
	    ProtoUtil.printLog("removeCharacter");
	    if (!characters.remove(character))
	        System.out.println("Olyan karakterre lett meghívva a removeCharacter, ami nincs a listában!!!");
	    notifySubsribers("characters");
	}
	
	/**
	 * Hozzáadja a paraméterként kapott tárgyat a szobában található tárgyak listájához. Egy szobában maximum maxItemCapacity tárgy lehet.
	 * @param i A hozzáadandó tárgy
	 * @return befért-e a tárgy
	 */
	public boolean addItem(Item i) {
	    ProtoUtil.printLog("addItem");
	    if(items.size()<maxItemCapacity) {
	    	items.add(i);
	    	notifySubsribers("items");
	    	return true;
	    }else if(i instanceof SlideRule && !(i instanceof FakeSlideRule)){
	    	items.add(i);
	    	Item temp=items.get(maxItemCapacity-1); // utolsó item eltávolítása
	    	removeItem(temp);
	    	temp.notifySubsribers("itemexpired");
	    	notifySubsribers("items");
	    	return true;
	    }else {
	    	if(i instanceof Transistor) {
	    		Transistor t = (Transistor) i;
				if (t.getPair() != null) {
					t.getPair().setLocation(null);
					t.getPair().setActive(false);
					t.getPair().setPair(null);
				}
				t.setPair(null);
				t.setActive(false);
				t.setLocation(null);
	    	}
	    	i.notifySubsribers("itemexpired");
	    	return false;
	    }
	}
	
	/**
	 * Eltávolítja a paraméterként kapott tárgyat a szobából.
	 * @param i az eltávolítandó tárgy
	 */
	public void removeItem(Item i) {
	    ProtoUtil.printLog("removeItem");
	    int idx=items.indexOf(i);
	    if (!items.remove(i))
	        System.out.println("Olyan Itemre lett meghívva a removeItem, ami nincs a listában!!!");
	    notifySubsribers("items removed "+idx);
	}
	
	/**
	 * Hozzáadja a paraméterként kapott szomszédos szobát a szoba szomszédok listájához.
	 * @param r a hozzáadandó szomszédos szoba
	 */
	public void addNeighbour(Room r) {
	    ProtoUtil.printLog("addNeighbour");
	    neighbours.remove(r);
	    neighbours.add(r);
	}
	
	/**
	 * Eltávolítja a paraméterként kapott szomszédos szobát a szoba szomszédok listájából.
	 * @param r az eltávolítandó szomszédos szoba
	 */
	public void removeNeighbour(Room r) {
	    ProtoUtil.printLog("removeNeighbour");
	    if (!neighbours.remove(r))
	        System.out.println("Olyan Roomra lett meghívva a removeNeighbour ami nincs a listában!!!");
	}
	
	/**
	 * Hozzáadja a paraméterként kapott környezeti tényezőt a szobához.
	 * @param ef a leteendő környezeti tényező
	 */
	public void spawnEnvironmentalFactor(EnvironmentalFactors ef) {
	    ProtoUtil.printLog("addEnvironmentalFactor");
	    ef.setLocation(this);
	    EnvironmentalFactors temp=null;
	    for (EnvironmentalFactors e : envFactors ) {
	        if (e.getClass().equals(ef.getClass())) {
	        	temp=e;
	        }
	    }
	    if(temp!=null) removeEnvironmentalFactor(temp);
	    envFactors.add(ef);
		notifySubsribers("spawnfactor "+(envFactors.size()-1));
	    notifySubsribers("factors");
	}
	
	/**
	 * Hozzáadja a paraméterként kapott környezeti tényezőt a szobához.
	 * @param ef a hozzáadandó környezeti tényező
	 */
	public void addEnvironmentalFactor(EnvironmentalFactors ef) {
	    ProtoUtil.printLog("addEnvironmentalFactor");
	    ef.setLocation(this);
	    EnvironmentalFactors temp=null;
	    for (EnvironmentalFactors e : envFactors ) {
	        if (e.getClass().equals(ef.getClass())) {
	        	temp=e;
	        }
	    }
	    if(temp!=null) removeEnvironmentalFactor(temp);
	    envFactors.add(ef);
	    notifySubsribers("factors");
	}
	
	/**
	 * Eltávolítja a paraméterként kapott környezeti tényezőt a szobához.
	 * @param ef az eltávolítandó környezeti tényező
	 */
	public void removeEnvironmentalFactor(EnvironmentalFactors ef) {
	    ef.notifySubsribers("factorremoved");
	    envFactors.remove(ef);
		notifySubsribers("factors");
	}
	
	/**
	 * Összeolvasztja a paraméterként kapott szobát a jelenlegivel.
	 * @param r az összeolvasztandó szoba
	 */
	public void merge(Room r) {
	    ProtoUtil.printLog("merge");
	    for (Room current : new ArrayList<>(r.neighbours)) {
	        if (!neighbours.contains(current)&&!current.equals(this)) {
	            addNeighbour(current);
	            current.addNeighbour(this);
	        }
	        current.removeNeighbour(r);
	    }
	    for (EnvironmentalFactors env : new ArrayList<>(r.getEnvironmentalFactors())) {
	    	addEnvironmentalFactor(env);
	    }
	    for (Character c : new ArrayList<>(r.getCharacters())) {
	        c.setStunned(0);
	    	c.enterRoom(this);
	    }
	    for (Item i : new ArrayList<>(r.getItems())) {
	        addItem(i);
	    }
	    
	    if(neighbours.contains(this))
	    	System.out.println("nagy a baj");
	    for(Room rr : r.neighbours) {
	    	if(rr.neighbours.contains(r)) {
	    		System.out.println("Egy már eltávolított szoba bentmaradt a CursedRoom szomszédai között!!!");
	    	}
	    }
	}
	
	/**
	 * Lerakja a paraméterként kapott tárgyat a szobába.
	 * @param i a lerakandó tárgy
	 */
	public void spawnItem(Item i) {
	    ProtoUtil.printLog("spawnItem");
	    if(items.size()<maxItemCapacity) {
	    	items.add(i);
	    	notifySubsribers("spawnitem "+(items.size()-1));
	    }
	}
	
	/**
	 * Meghívja a tárolt Character-ek és EnvironmentalFactorok update() metódusát.
	 */
	@Override
	public void update() {
	    for(Character character: new ArrayList<Character>(characters)){
	        character.update();
	    }
	    for (EnvironmentalFactors env : envFactors)
	        env.update();
	}
}
