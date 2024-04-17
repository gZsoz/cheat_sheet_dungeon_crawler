package EnvironmentalFactor;

import Map.Room;
import ProtoUtil.ProtoUtil;

/**
 * Absztrakt osztály a környezeti változók reprezentálására
 */
public abstract class EnvironmentalFactors {
	
	public String name;

	/**
	 * Létrehoz egy környezeti változót
	 */
	public void create() {
		ProtoUtil.printLog(name+".create()");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();
	}
    /**
     * Beállítja a környezeti változó helyét egy adott szobába
     * @param room a szoba ahová a környezeti változó elhelyezendő
     */
	
	public void setLocation(Room room) {
		ProtoUtil.printLog(name+".setLocation("+room.name+")");
		ProtoUtil.increaseIndent();
    	ProtoUtil.decreaseIndent();

	}
	

}
