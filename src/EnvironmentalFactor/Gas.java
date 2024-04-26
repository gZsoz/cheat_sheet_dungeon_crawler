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

	/**
	 * Kontruktor létrehozza, a gáz objektumot
	 * @param r Adott szoba
	 */
	public Gas(Room r){
		location = r;
	}

	/**
	 * Egy karakter elkábítása, ha nincs védelmi eszköze
	 * @param character - Kábítandó karakter
	 */
	public void stun(Character character) {
		ProtoUtil.printLog("stun");
		if(!character.getHasDefense()) {
			if(character.getStunned()==0) character.setStunned(4);
			for(Item currentItem : new ArrayList<Item>(character.getInventory())){
				if(currentItem instanceof Transistor) { // ha tranzisztor, annak az értékeit default-ra állítjuk
					Transistor t = (Transistor) currentItem;
					t.getPair().setLocation(null);
					t.getPair().setActive(false);
					t.getPair().setPair(null);
					t.setPair(null);
					t.setActive(false);
					t.setLocation(null);
				}
				character.putdownItem(currentItem);
			}
			return;
		}
		for (Item item: character.getInventory()) {
			if(item instanceof Mask) {
				item.use();
				break;
			}
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