package Items;

import Character.Character;
import ProtoUtil.ProtoUtil;

/**
 * Absztrakt osztály, ebből származnak le a különböző tárgyak.
 */
public abstract class Item {
	
	protected Character owner;
	
	/**
	 * A tárgy ragacsosságát jelzi
	 */
	protected boolean sticky;

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
		ProtoUtil.printLog("setSticky");
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