package Items;

import SkeletonUtil.SkeletonUtil;

/**
 * Class representing Beer item.
 */
public class Beer extends DecayingItems {
	/**
     * Actions to perform when the beer is used (picked up) by a character.
     * Gives immunity to the user,
     * Lowers the duration of the effect each second
     */
	@Override
    public void use() {
        // Implementation
    	SkeletonUtil.printLog("use()");
		SkeletonUtil.increaseIndent();
		reduceDuration();
    	SkeletonUtil.decreaseIndent();
    }
	/**
	 * Keeps data up to date
	 */
    @Override
    public void update() {
        SkeletonUtil.printLog("update()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();// Implementation
    }
}