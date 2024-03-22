package Items;

import SkeletonUtil.SkeletonUtil;

/**
 * Class representing a mask item in the game.
 */
public class Mask extends DecayingItems {
    @Override
	
    public void use() {
    	SkeletonUtil.printLog("use()");
		SkeletonUtil.increaseIndent();
		reduceDuration();
    	SkeletonUtil.decreaseIndent();
    }

    @Override
    public void update() {
        // Implementation
    }
}