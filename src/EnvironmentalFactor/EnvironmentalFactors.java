package EnvironmentalFactor;

import Map.Room;
import SkeletonUtil.SkeletonUtil;

/**
 * Abstract class representing environmental factors in the game.
 */
public abstract class EnvironmentalFactors {

    /**
     * Sets the location of the environmental factor to a given room
     * @param room the location that is to be set
     */
	
	public void setRoom(Room room) {
		SkeletonUtil.printLog("setRoom()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();

	}
	

}