package Items;

import SkeletonUtil.SkeletonUtil;

/**
 * Class representing Beer item.
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
     * Actions to perform when the beer is used (picked up) by a character.
     * Gives immunity to the user,
     * Lowers the duration of the effect each second
     */
	@Override
    public void use() {
        // Implementation
    	SkeletonUtil.printLog(name+".use()");
		SkeletonUtil.increaseIndent();
		reduceDuration();
    	SkeletonUtil.decreaseIndent();
    }

	@Override
	public void onPickUp() {
		SkeletonUtil.printLog(name+".onPickUp()");
		SkeletonUtil.increaseIndent();
		use();
    	SkeletonUtil.decreaseIndent();
	}

}