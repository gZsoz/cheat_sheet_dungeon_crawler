package model.environmentalfactors;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import model.map.Room;
import model.modelupdate.iTask;
import view.utils.Subscriber;

/**
 * Absztrakt osztály a környezeti változók reprezentálására.
 */
public abstract class EnvironmentalFactor implements iTask {
	
	/**
	 * Melyik szobában van a környezeti változó.
	 */
	protected Room location;
	
	/**
	 * A környezeti változó változásaira feliratkozott osztályok.
	 */
	private List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	/**
	 * Lekérdezi a környezeti változó helyét, melyik szobában van.
	 * @return a szoba, ahol a környezeti változó van
	 */
	public Room getLocation() {
		Main.printLog("getLocation");
		return location;
	}
	
	/**
	 * Beállítja a környezeti változó helyét egy adott szobába.
	 * @param room a szoba ahová a környezeti változó elhelyezendő
	 */
	public void setLocation(Room room) {
		Main.printLog("setLocation");
		location = room;
	}
	
	/**
	 * Meghívja a konstruktorában beállított feliratkozóira a propertyChanged(String)
	 * függvényüket a paraméterként kapott Stringgel.
	 * @param str üzenet arról, hogy mi változott meg, lehetséges értékek: "factorremoved"
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
	 * Az időérzékeny műveleteket végrehajtja.
	 */
	public abstract void update();

}
