package Items;

import Character.Student;
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
    	isActive=true;
    	reduceDuration();
    }
	
	/**
	 * A tárgy felvételekor elvégezendő feladatok
	 */
	@Override
	public void onPickUp() {
		ProtoUtil.printLog("onPickUp");
		owner.setHasDefense(true);
	}
	
	@Override
	public void onDrop() {
		ProtoUtil.printLog("onDrop");
		isActive=false;
		boolean mask=false;
		for(Item i : owner.getInventory()) {
			if(i instanceof Mask) {
				mask=true;
				break;
			}
		}
		if(!mask) owner.setHasDefense(false);
		
		super.onDrop();
	}
	
	@Override
	public void update(){}
	
}
