package Items;

import ProtoUtil.ProtoUtil;

/**
 * Class representing a Mask item in the game.
 */
public class Mask extends DecayingItems {
    
	
	
	
	
	/**
     * A gáz által aktivált maszk elvégezendő műveleteit végzi
     * Immunitást ad a használónak a gázzal szemben
     * Minden másodpercben csökkenti a hatás időtartamát
     */
	
	@Override
    public void use() {
    	ProtoUtil.printLog("use()");
    	isactive=true;
		
    }
	/**
	 * A trágy felvételekor elvégezendő feladatok
	 */

	@Override
	public void onPickUp() {}
	

	
	
}
