package EnvironmentalFactor;

import Map.Room;
import SkeletonUtil.SkeletonUtil;

/**
 * Abstract class representing environmental factors in the game.
 */
public abstract class EnvironmentalFactors {
	
	public String name;

	public void create() {
		SkeletonUtil.printLog(name+".create()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
    /**
     * Sets the location of the environmental factor to a given room
     * @param room the location that is to be set
     */
	
	public void setLocation(Room room) {
		SkeletonUtil.printLog("setLocation("+room.name+")");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();

	}
	

}