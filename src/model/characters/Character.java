package model.characters;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import model.environmentalfactors.EnvironmentalFactor;
import model.environmentalfactors.Sticky;
import model.items.Item;
import model.items.decayingitems.Beer;
import model.items.specialitems.Transistor;
import model.map.Room;
import model.modelupdate.iTask;
import view.utils.Subscriber;

/**
 * A Model.Character egy absztrakt osztály, amely a hallgatók és az oktatók közös tulajdonságait
 * foglalja magába a kód duplikációjának elkerülése végett. Ez az osztály felelős a karakterek
 * inventory-jának (eszköztárának) nyilvántartásáért, életállapotuk figyeléséért (aktív vagy
 * elájult), a pálya keretein belüli szabad mozgásukért, valamint tárgyak felvételéért és letételéért
 * a játék környezetében. A Model.Character leszármazottai a Teacher és a Student.
 */
public abstract class Character implements iTask {
	
	/**
	 * A karakterek kábultsági állapotának hossza másodpercben.
	 */
	public static int stunTime=5 * Main.fps;
	
	/**
	 * A karakterek kábultsági állapotai között eltelt idő hossza másodpercben.
	 * Arra használjuk, hogy ha a karakter folyamatosan kábulva lenne, legyen ideje kiszabadulni.
	 */
	public static int restTime=10 * Main.fps;
	
	/*
	 * A karakter változásaira feliratkozott osztályok.
	 */
	private List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	/**
	 * A karakter birtokában található tárgyak listája. Maximum 5 Item lehet benne. Nem lehet benne logarléc.
	 */
	protected ArrayList<Item> inventory;
	
	/**
	 * Melyik szobában van éppen a karakter.
	 */
	protected Room currentRoom;
	
	/**
	 * El van-e kábítva a karakter.
	 */
	protected int stunned;
	
	/**
	 * Van-e védelme a karakternek kábítás ellen.
	 */
	protected boolean hasDefense;
	
	/**
	 * Kábultsági állapot lekérdezése.
	 * @return a karakter kábultsági állapota
	 */
	public boolean isStunned() {
		boolean a=stunned > 0 && stunned <= stunTime;
		if(getHasDefense() && a) System.out.println("A karakter egyszerre stunolt is és nem is!!");
		return a;
	}
	
	/**
	 * Inventory lekérdezése.
	 * @return a karakter birtokában lévő tárgyak listája
	 */
	public ArrayList<Item> getInventory(){
		Main.printLog("getInventory");
		return inventory;
	}
	
	/**
	 * Szoba lekérdezése.
	 * @return a karakter tartózkodási helye
	 */
	public Room getRoom() {
		Main.printLog("getRoom");
		return currentRoom;
	}
	
	/**
	 * Jelenlegi szoba beállítása.
	 * @param s ha kábult a karakter, akkor igaz, egyébként hamis
	 */
	public void setRoom(Room r) {
		currentRoom=r;
	}
	
	/**
	 * Kábultsági állapot lekérdezése.
	 * @return a karakter kábultsági állapota
	 */
	public int getStunned() {
		Main.printLog("getStunned");
		return stunned;
	}
	
	/**
	 * Kábultsági állapot beállítása.
	 * @param s ha kábult a karakter, akkor igaz, egyébként hamis
	 */
	public void setStunned(int s) {
		Main.printLog("setStunned");
		stunned = s;
		notifySubscribers("stun");	// jelzi, hogy a stunolás állapota megváltozhat
	}
	
	/**
	 * Kábultsági állapot csökkentése.
	 */
	public void reduceStunned() {
		Main.printLog("reduceStunned");
		stunned--;
		notifySubscribers("stun");	// jelzi, hogy a stunolás állapota megváltozhat
	}
	
	/**
	 * Kábítás elleni védelem lekérdezése.
	 * @return ha van védelme a karakternek kábítás ellen, akkor igaz, egyébként hamis
	 */
	public boolean getHasDefense() {
		return hasDefense;
	}
	
	/**
	 * Kábítás elleni védelem beállítása.
	 * @param b ha van védelme a karakternek kábítás ellen, akkor igaz, egyébként hamis
	 */
	public void setHasDefense(boolean b) {
		hasDefense = b;
	}
	
	/**
	 * Meghívja a konstruktorában beállított feliratkozóira a propertyChanged(String) függvényüket a paraméterként kapott Stringgel.
	 * @param str üzenet arról, hogy mi változott meg, lehetséges értékek: "stun", "inventory", "invincible", "kicked", "studentwon", "angry" 
	 */
	public void notifySubscribers(String str) {
		for(Subscriber sub : new ArrayList<>(subscribers))
			sub.propertyChanged(str);
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
	 * Akkor hívódik meg, amikor egy karakter át akar menni egyik szobából a másikba.
	 * @param r a szoba, amelybe be akar menni
	 * @return ha a karakter befér az új szobába, akkor a művelet sikeres és igaz értékkel tér vissza a függvény, egyébként hamissal
	 */
	public boolean enterRoom(Room r) {
		Main.printLog("enterRoom");
		if(r.getCharacters().size() < r.getCapacity() && !(stunned > 0 && stunned <= stunTime)) {
			Room temp=currentRoom;
			currentRoom = r;
			temp.removeCharacter(this);
			r.addCharacter(this);
			setStunned(0);
			for(EnvironmentalFactor ef: currentRoom.getEnvironmentalFactors()) {
				if(ef instanceof Sticky) {
					((Sticky) ef).reduceRemainingEntries();
					break;
				}
			}
			
			boolean hasBeer=false;
			for(Item i : inventory) {
				if(i instanceof Beer) {
					hasBeer=true;
					break;
				}
			}
			if((!hasBeer) && this instanceof Student) ((Student)this).setInvincible(false); // a denevérbőr ne védjen tovább
			return true;
		}
		return false;
	}
	
	/**
	 * A paraméterül kapott tárgy felvételét valósítja meg.
	 * @param i a felvevendő tárgy
	 * @return ha az inventory-ban van hely, akkor a művelet sikeres és igaz értékkel tér vissza a függvény, ha egy párral rendelkező tranzisztort venne fel, illetve egyéb esetben hamissal
	 */
	public boolean pickupItem(Item i) {
		Main.printLog("pickupItem");
		if(inventory.size() < 5 && !(stunned > 0 && stunned <= stunTime) && !i.getSticky()) {
			if(i instanceof Transistor) {
				Transistor transistorInRoom = (Transistor) i;
				if(transistorInRoom.getPair() != null) {
					Main.printLog("Could not pick up item");
					return false; // párosított tranzisztort nem vehetünk fel
				}
			}
			currentRoom.removeItem(i);
			inventory.add(i);
			notifySubscribers("inventory");
			i.setOwner(this);
			Main.printLog("Successfully picked up item");
			i.onPickUp();
			return true;
		}
		Main.printLog("Could not pick up item, as it is a paired transistor.");
		return false;
	}
	
	/**
	 * A paraméterül kapott tárgy letételét valósítja meg.
	 * @param i a tárgy, amit le akar tenni
	 */
	public void putdownItem(Item i) {
		Main.printLog("putdownItem");
		int idx = inventory.indexOf(i);
		if(!inventory.remove(i)) {
			System.out.println("Olyan Item-re lett meghívva a putdownItem, ami nincs benne az inventoryban!");
		}
		notifySubscribers("inventory removed "+idx);
		if(currentRoom.addItem(i))
			i.onDrop();
		else {
			if(i instanceof Beer) {
				i.setOwner(this);
				i.onDrop();
			}
		}
	}
	
	/**
	 * A karakter időérzékeny műveleteit végzi.
	 */
	public void update() {
		if(0 < stunned && stunned <= stunTime) {
			reduceStunned();
			if(stunned==0) setStunned(restTime);
		}
		if(stunTime<stunned) {
			reduceStunned();
			if(stunned==stunTime) setStunned(0);
		}
		for(Item temp : new ArrayList<Item>(inventory)) {
			if(temp instanceof iTask) {
				((iTask) temp).update();
			}
		}
	}
}
