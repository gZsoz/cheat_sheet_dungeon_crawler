package Model.EnvironmentalFactors;

import Model.Characters.Character;
import Model.Characters.Cleaner;
import Model.Items.*;
import Main.Main;
import Model.Items.DecayingItems.FakeMask;
import Model.Items.DecayingItems.Mask;
import Model.Items.SpecialItems.Transistor;
import Model.Map.Room;

import java.util.ArrayList;

/**
 * Osztály a gáz környezeti változó reprezentálására.
 */
public class Gas extends EnvironmentalFactors {
	
	/**
	 * Kontruktor gáz létrehozásához.
	 * @param r a szoba, ahol a környezeti változó van
	 */
	public Gas(Room r){
		location = r;
	}
	
	/**
	 * Egy karakter elkábítása, ha nincs védelmi eszköze.
	 * @param character az elkábítandó karakter
	 */
	public void stun(Character character) {
		Main.printLog("stun");
		if(character instanceof Cleaner)
			return;
		if(!character.getHasDefense()) {
			if(character.getStunned()==0) {
				character.setStunned(Character.stunTime);
				for(Item currentItem : new ArrayList<Item>(character.getInventory())){
					if(currentItem instanceof Transistor) { // ha tranzisztor, annak az értékeit default-ra állítjuk
						Transistor t = (Transistor) currentItem;
						if (t.getPair() != null) {
							t.getPair().setLocation(null);
							t.getPair().setActive(false);
							t.getPair().setPair(null);
						}
						t.setPair(null);
						t.setActive(false);
						t.setLocation(null);
					}
					character.putdownItem(currentItem);
				}
				return;
			}
		}
		for (Item item: character.getInventory()) {
			if(item instanceof Mask && !(item instanceof FakeMask)) {
				item.use();
				break;
			}
		}
	}
	
	
	/**
	 * Minden adott időben megpróbálja elkábítani a szobájában lévő karaktereket.
	 */
	@Override
	public void update() {
		for(Character character: getLocation().getCharacters()){
			stun(character);
		}
	}
}