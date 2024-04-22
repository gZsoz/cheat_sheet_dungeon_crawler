package Items;

import ProtoUtil.ProtoUtil;

/**
 * Absztrakt osztály, ebből származnak le a különböző tárgyak.
 */
public abstract class Item {
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
	 * Tárgy ragacsosságának beállítása
	 * @param s A beállítandó állapot
	 */
	public void setSticky(boolean s){
		ProtoUtil.printLog("setSticky");
		sticky=s;
	}

	/**
     * Egy tárgy felvételekor hívodik meg. A tárgyak a saját,egyedi módján kerül használatba,
     * ezért ezt külön-külön valósítják meg a  leszármazottak.
     */
	public abstract void onPickUp();
	
	/**
     * Egy tárgy használata. A tárgyak a saját,egyedi módján kerül használatba, 
     * ezért ezt külön-külön valósítják meg a  leszármazottak.
     */
    public abstract void use();
}