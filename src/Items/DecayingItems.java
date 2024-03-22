package Items;

import SkeletonUtil.SkeletonUtil;

/**
 * Abstract class representing items with decaying properties.
 */
public abstract class DecayingItems extends Item {

    /**
     * Reduces the duration of the decaying item.
     */
    public void reduceDuration() {
    	SkeletonUtil.printLog("reduceDuration()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
    }

    /**
     * Abstract method to update the decaying item's properties.
     */
    abstract void update();
}