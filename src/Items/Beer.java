package Items;

import ProtoUtil.ProtoUtil;

/**
 * Osztály a Beer tárgy reprezentálására
 */
public class Beer extends DecayingItems {
	
	/**
     * Konstruktor egy tárgy létrehozásához. Beer-ra állítja a nevet.
     */
	
	/**
     * Konstruktor egy tárgy létrehozásához.
     * @param n A tárgy neve
     */
	
	
	/**
     * Sör használatakor (felvételekor) végrehajtandó műveleteket végzi
     * Immunitást ad a használónak,
     * Minden másodpercben csökkenti a hatás időtartamát
     */
	@Override
    public void use() {
        // Implementation
    	
		ProtoUtil.printLog("use");
		Owner.setHasDefense(true);
		setisactive(true);
		
    }
	/**
	 * A tárgy felvételekor elindítja a használatot
	 */

	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
		ProtoUtil.increaseIndent();
		use();
    	ProtoUtil.decreaseIndent();
	}
	
	public void update() {
		ProtoUtil.printLog("update");
		reduceDuration();
		if(!usable) {
			Owner.setHasDefense(false);
		}
		
	}

}