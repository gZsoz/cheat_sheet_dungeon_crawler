package EnvironmentalFactor;

import Map.Room;
import ProtoUtil.ProtoUtil;
import Time.iTask;

/**
 * Absztrakt osztály a környezeti változók reprezentálására
 */
public abstract class EnvironmentalFactors implements iTask {
	/**
	 * Melyik szobában van
	 */
	private Room location;

	/**
	 * Beállítja a környezeti változó helyét egy adott szobába
	 * @param room a szoba ahová a környezeti változó elhelyezendő
	 */
	public void setLocation(Room room) {
		ProtoUtil.printLog("setLocation");
		location = room;
	}

	/**
	 * Lekérdezi a környezeti változó helyét, melyik szobában van
	 * @return A szoba, ha van
	 */
	public Room getLocation() {
		ProtoUtil.printLog("getLocation");
		return location;
	}

	/**
	 * Az időérzékeny műveleteket végrehajtja
	 */
	public abstract void update();

}
