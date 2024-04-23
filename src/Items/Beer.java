package Items;

import ProtoUtil.ProtoUtil;

/**
 * Osztály a Beer tárgy reprezentálására
 */
public class Beer extends DecayingItems {
	
	/**
     * Sör használatakor (felvételekor) végrehajtandó műveleteket végzi, 
     * immunitást ad a használónak, minden másodpercben csökkenti a hatás időtartamát.
     */
	@Override
    public void use() {
		ProtoUtil.printLog("use");
		owner.setHasDefense(true);
		setIsActive(true);
    }
	
	/**
	 * A tárgy felvételekor elindítja a használatot.
	 */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
		use();
	}
	
	/**
	 * Ugyanaz mint a DecayingItems-nek, plusz még 
	 */
	@Override
	public void update() {
		super.update();
		if(!usable) {
			owner.setHasDefense(false);
		}
	}

}