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
     * Actions to perform when the mask is triggered by gas.
     * Gives immunity to the user,
     * Lowers the duration of the effect each second
     */
	
	@Override
    public void use() {
    	SkeletonUtil.printLog(name+".use()");
		SkeletonUtil.increaseIndent();
		reduceDuration();
    	SkeletonUtil.decreaseIndent();
    }

	@Override
	public void onPickUp() {
		SkeletonUtil.printLog(name+".onPickUp()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
	
}
