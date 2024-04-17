package EnvironmentalFactor;

import Character.Character;
import Items.*;
import Map.CursedRoom;
import ProtoUtil.ProtoUtil;

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
		ProtoUtil.printLog(name+".stun()");
		ProtoUtil.increaseIndent();
		character.getInventory();
		if(ProtoUtil.binaryQuestion("Van-e maszkja?")) {
			new Mask().use();
		}else {
			character.setStunned(true);
		}
		ProtoUtil.decreaseIndent();
	}
	
}