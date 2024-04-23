package Items;

import ProtoUtil.ProtoUtil;

/**
 * Class representing a Mask item in the game.
 */
public class Mask extends DecayingItems {
    
	
	
	
	
	/**
     * A gáz által aktivált maszk elvégezendő műveleteit végzi
     * A tárgyat aktívvá teszi, ezzel lehetővé téve a védelmet és az időtartam csökkenését
     */
	
	@Override
    public void use() {
    	ProtoUtil.printLog("use");
    	isactive=true;
		
    }
	/**
	 * A trágy felvételekor elvégezendő feladatok
	 */

	@Override
	public void onPickUp() {}
	

	public void update(){
		reduceDuration();
		
	}
	
}
