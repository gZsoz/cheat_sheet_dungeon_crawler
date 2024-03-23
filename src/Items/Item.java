package Items;

import SkeletonUtil.SkeletonUtil;

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
		SkeletonUtil.printLog(name+".create()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
	
	/**
     * Egy tárgy használata. A tárgyak a saját,egyedi módján kerül használatba, 
     * ezért ezt külön-külön valósítják meg a  leszármazottak.
     */
    abstract void use();
}