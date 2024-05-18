package EnvironmentalFactor;

import java.util.ArrayList;
import java.util.List;

import Map.Room;
import ProtoUtil.ProtoUtil;
import Time.iTask;
import View.Utils.Subscriber;

/**
 * Absztrakt osztály a környezeti változók reprezentálására
 */
public abstract class EnvironmentalFactors implements iTask {
	/**
	 * Melyik szobában van
	 */
	protected Room location;

	/** A környezeti változó változásaira feliratkozott osztályok*/
	public List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	/**
     * meghívja a konstruktorában beállított feliratkozóira a propertyChanged(String)
     *  függvényüket a paraméterként kapott Stringgel
     * @param str
     */
    public void notifySubsribers(String str) {
    	for(Subscriber sub : new ArrayList<>(subscribers))
    		sub.propertyChanged(str); // lehetséges értékek: "factorremoved"
    }
	
    /**
     * hozzáadja a paraméterként kapott Subscriber objektumot a feliratkózók listájához
     * ezzentúl a propertyChanged függvénye meghívásával jelzi, ha belső állapota megváltozik
     * @param sub
     */
    public void subscribe(Subscriber sub) {
    	subscribers.add(sub);
    }
    
    /**
     * eltávolítja a paraméterként kapott Subscriber objektumot a feliratkózók listájából
     * ezzentúl nem kap értesítést, ha az osztály belső állapota megváltozik
     * @param sub
     */
    public void unsubscribe(Subscriber sub) {
    	subscribers.remove(sub);
    }
    
	
	/**
	 * Beállítja a környezeti változó helyét egy adott szobába
	 * @param room a szoba ahová a környezeti változó elhelyezendő
	 */
	public void setLocation(Room room) {
		ProtoUtil.printLog("setLocation");
		location = room;
	}

	/**
	 * Lekérdezi a környezeti változó helyét, melyik szobában van
	 * @return A szoba, ha van
	 */
	public Room getLocation() {
		ProtoUtil.printLog("getLocation");
		return location;
	}

	/**
	 * Az időérzékeny műveleteket végrehajtja
	 */
	public abstract void update();

}
