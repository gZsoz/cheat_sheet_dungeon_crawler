package Items;

import ProtoUtil.ProtoUtil;

/**
 * Absztrakt osztály, ebből származnak le a különböző tárgyak.
 */
public abstract class Item {
	/**
	 * Az inicializálás tesztjéhez két azonos típusú változó megkülönböztetésére.
	 */
	public String name;
	
	/**
     * Az inicializálás tesztjéhez a konstruktor szimulálására.
     */
	public void create() {
		ProtoUtil.printLog(name+".create()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
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