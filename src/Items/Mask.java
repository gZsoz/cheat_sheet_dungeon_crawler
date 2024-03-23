package Items;

import SkeletonUtil.SkeletonUtil;

/**
 * Class representing a Mask item in the game.
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

	
}
