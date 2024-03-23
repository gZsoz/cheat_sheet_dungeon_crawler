package EnvironmentalFactor;

import Character.Character;
import SkeletonUtil.SkeletonUtil;
import Items.*;
import Map.CursedRoom;

/**
 * Class representing gas environmental factor in the game.
 */
public class Gas extends EnvironmentalFactors {
	
	/**
     * Konstruktor egy EnvironmentalFactors létrehozásához. EnvironmentalFactors-ra állítja a nevet.
     */
	public Gas() {
		name="Gas";
	}
	
	/**
     * Konstruktor egy tárgy létrehozásához.
     * @param n A tárgy neve
     */
	public Gas(String n) {
		name=n;
	}
	
	public void stun(Character character) {
		SkeletonUtil.printLog(name+".stun()");
		SkeletonUtil.increaseIndent();
		character.getInventory();
		if(SkeletonUtil.binaryQuestion("Van-e maszkja?")) {
			new Mask().use();
		}else {
			character.setStunned(true);
		}
		SkeletonUtil.decreaseIndent();
	}
	
}