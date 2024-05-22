package Model.Items;

import java.util.ArrayList;
import java.util.List;

import Model.Characters.Character;
import Main.Main;
import View.Utils.Subscriber;

/**
 * Absztrakt osztály, ebből származnak le a különböző tárgyak.
 */
public abstract class Item {
	
	/**
	 * A tárgy változásaira feliratkozott osztályok.
	 */
	public List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	/**
	 * A tárgy tulajdonosa.
	 */
	protected Character owner;
	
	/**
	 * Ragacsos-e a tárgy.
	 */
	protected boolean sticky;
	
	/**
	 * Tárgy ragacsosságának lekérdezése.
	 * @return ragacsos-e a tárgy
	 */
	public boolean getSticky(){
		Main.printLog("getSticky");
		return sticky;
	}
	
	/**
	 * Tárgy ragacsosságának beállítása.
	 * @param s a beállítandó állapot
	 */
	public void setSticky(boolean s){
		sticky=s;
		notifySubsribers("sticky");
	}
	
	/**
	 * Lekérdezi a tárgy tulajdonosát.
	 * @return a tárgy tulajdonosa
	 */
	public Character getOwner() {
		Main.printLog("getOwner");
		return owner;
	}
	
	/**
	 * Beállítja a tárgy tulajdonosát.
	 * @param o a tárgy tulajdonosa
	 */
	public void setOwner(Character o) {
		Main.printLog("setOwner");
		owner=o;
	}
	
	/**
	 * Meghívja a konstruktorában beállított feliratkozóira a propertyChanged(String)
	 * függvényüket a paraméterként kapott Stringgel.
	 * @param str üzenet arról, hogy mi változott meg, lehetséges értékek: "sticky", "remaininguses", "isactive", "pair", "itemexpired"
	 */
	public void notifySubsribers(String str) {
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
	 * Egy tárgy felvételekor hívódik meg. A tárgyak a saját, egyedi módján kerül használatba,
	 * ezért ezt külön-külön valósítják meg a leszármazottak.
	 */
	public abstract void onPickUp();
	
	/**
	 * Egy tárgy letételekor hívódik meg, ekkor a tárgynak nem lesz tulajdonosa.
	 */
	public void onDrop() {
		Main.printLog("Dropped");
		owner=null;
	}
	
	/**
	 * Egy tárgy aktiválásakor hívódik meg, amelyet a játékosok végezhetnek.
	 */
	public void onActivate() {}
	
	/**
	 * Egy tárgy használata. A tárgyak a saját,egyedi módján kerül használatba, 
	 * ezért ezt külön-külön valósítják meg a  leszármazottak.
	 */
	public abstract void use();
}