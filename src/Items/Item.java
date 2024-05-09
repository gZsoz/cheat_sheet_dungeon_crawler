package Items;

import java.util.ArrayList;
import java.util.List;

import Character.Character;
import ProtoUtil.ProtoUtil;
import View.Utils.Subscriber;

/**
 * Absztrakt osztály, ebből származnak le a különböző tárgyak.
 */
public abstract class Item {
	
	/** Az item változásaira feliratkozott osztályok*/
	public List<Subscriber> subscribers = new ArrayList<Subscriber>();
	
	protected Character owner;
	
	/**
	 * A tárgy ragacsosságát jelzi
	 */
	protected boolean sticky;

	/**
     * meghívja a konstruktorában beállított feliratkozóira a propertyChanged(String)
     *  függvényüket a paraméterként kapott Stringgel
     * @param str
     */
    public void notifySubsribers(String str) {
    	for(Subscriber sub : subscribers)
    		sub.propertyChanged(str);
    }
	
	/**
	 * Tárgy ragacsosságának lekérdezése
	 * @return ragacsos-e a tárgy
	 */
	public boolean getSticky(){
		ProtoUtil.printLog("getSticky");
		return sticky;
	}

	/**
	 * Beállítja a tárgy tulajdonosát
	 * @param o - tárgy tulajdonosa
	 */
	public void setOwner(Character o) {
		ProtoUtil.printLog("setOwner");
		owner=o;
	}
	/**
	 * Lekérdezi a tárgy tulajdonosát
	 * tárgy tulajdonosa
	 */
	public Character getOwner() {
		ProtoUtil.printLog("getOwner");
		return owner;
	}
	/**
	 * Tárgy ragacsosságának beállítása
	 * @param s A beállítandó állapot
	 */
	public void setSticky(boolean s){
		sticky=s;
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
		ProtoUtil.printLog("Dropped");
		owner=null;
	}
	
	/**
     * Egy tárgy használata. A tárgyak a saját,egyedi módján kerül használatba, 
     * ezért ezt külön-külön valósítják meg a  leszármazottak.
     */
    public abstract void use();
}