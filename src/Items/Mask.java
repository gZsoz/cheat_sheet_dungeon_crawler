package Items;

import SkeletonUtil.SkeletonUtil;

/**
 * Class representing a mask item in the game.
 */
public class Mask extends DecayingItems {
    
	/**
     * Actions to perform when the mask is triggered by gas.
     * Gives immunity to the user,
     * Lowers the duration of the effect each second
     */
	
	@Override
    public void use() {
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
    	SkeletonUtil.decreaseIndent();
    }
}