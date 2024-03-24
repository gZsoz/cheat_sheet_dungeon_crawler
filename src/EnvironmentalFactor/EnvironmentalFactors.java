package EnvironmentalFactor;

import Map.Room;
import SkeletonUtil.SkeletonUtil;

/**
 * Absztrakt osztály a környezeti változók reprezentálására
 */
public abstract class EnvironmentalFactors {
	
	public String name;

	/**
	 * Létrehoz egy környezeti változót
	 */
	public void create() {
		SkeletonUtil.printLog(name+".create()");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();
	}
    /**
     * Beállítja a környezeti változó helyét egy adott szobába
     * @param room a szoba ahová a környezeti változó elhelyezendő
     */
	
	public void setLocation(Room room) {
		SkeletonUtil.printLog("setLocation("+room.name+")");
		SkeletonUtil.increaseIndent();
    	SkeletonUtil.decreaseIndent();

	}
	

}