package Items;

import ProtoUtil.ProtoUtil;

/**
 * Osztály a Beer tárgy reprezentálására
 */
public class Beer extends DecayingItems {
	
	/**
     * Konstruktor egy tárgy létrehozásához. Beer-ra állítja a nevet.
     */
	public Beer() {
		name="Beer";
	}
	
	/**
     * Konstruktor egy tárgy létrehozásához.
     * @param n A tárgy neve
     */
	public Beer(String n) {
		name=n;
	}
	
	/**
     * Sör használatakor (felvételekor) végrehajtandó műveleteket végzi
     * Immunitást ad a használónak,
     * Minden másodpercben csökkenti a hatás időtartamát
     */
	@Override
    public void use() {
        // Implementation
    	ProtoUtil.printLog(name+".use()");
		ProtoUtil.increaseIndent();
		reduceDuration();
    	ProtoUtil.decreaseIndent();
    }
	/**
	 * A tárgy felvételekor elindítja a használatot
	 */

	@Override
	public void onPickUp() {
		ProtoUtil.printLog(name+".onPickUp()");
		ProtoUtil.increaseIndent();
		use();
    	ProtoUtil.decreaseIndent();
	}

}