package Items;

import SkeletonUtil.SkeletonUtil;

/**
 * Class representing a Mask item in the game.
 */
public class Mask extends DecayingItems {
    
	/**
     * Konstruktor egy tárgy létrehozásához. Mask-ra állítja a nevet.
     */
	public Mask() {
		name="Mask";
	}
	
	/**
     * Konstruktor egy tárgy létrehozásához.
     * @param n A tárgy neve
     */
	public Mask(String n) {
		name=n;
	}
	
	/**
     * A gáz által aktivált maszk elvégezendő műveleteit végzi
     * Immunitást ad a használónak a gázzal szemben
     * Minden másodpercben csökkenti a hatás időtartamát
     */
	
	@Override
    public void use() {
    	SkeletonUtil.printLog(name+".use()");
		SkeletonUtil.increaseIndent();
		reduceDuration();
    	SkeletonUtil.decreaseIndent();
    }
	/**
	 * A trágy felvételekor elvégezendő feladatok
	 */
	@Override
	public void onPickUp() {
		SkeletonUtil.printLog(name+".onPickUp()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
	
}
