package EnvironmentalFactor;

import Character.Character;
import Items.*;
import Map.CursedRoom;
import Map.Room;
import ProtoUtil.ProtoUtil;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

/**
 * Class representing gas environmental factor in the game.
 */
public class Gas extends EnvironmentalFactors {

	public Gas(Room r){
		setLocation(r);
	}

	/**
	 * Egy karakter elkábítása, ha nincs védelmi eszköze
	 * @param character - Kábítandó karakter
	 */
	public void stun(Character character) {
		ProtoUtil.printLog("stun");
		boolean hasDefence = false;
		for (Item item: character.getInventory()) {
			if(item instanceof Mask){
				item.use();
				if(((Mask) item).getisactive()){
					hasDefence = true;
				}
			}
		}
		if(!hasDefence) {
			character.setStunned(true);
		}
	}

	/**
	 * Minden adott időben megpróbálja elkábítani, a vele egy szobában lévőket
	 */
	@Override
	public void update() {
		for(Character character: getLocation().getCharacters()){
			stun(character);
		}
	}
}